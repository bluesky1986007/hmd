package service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import service.HttpClientService;

import java.net.MalformedURLException;

public class HttpClientServiceImpl extends AbstractTestNGSpringContextTests implements HttpClientService {

	@Override
	public JSONArray doGetJSONArray(String url) throws MalformedURLException, Exception {

//		JSONObject[] jsonResult=null;

		JSONArray jsonResult;

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(url)
				.get()
				.addHeader("cache-control", "no-cache")
				.build();

		Response response = client.newCall(request).execute();
		String tempResponse =  response.body().string();

		logger.info("code = "+response.code());
		Assert.assertEquals(response.code(),200);

		logger.info("tempResponseOfGet = "+tempResponse);
		jsonResult= JSON.parseArray(tempResponse);

		return jsonResult;

	}

	@Override
	public JSONObject doPostWithRaw(String data, String dataType, String url) throws MalformedURLException, Exception {

		// TODO Auto-generated method stub

		JSONObject jsonResult;

		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse(dataType);
		RequestBody body = RequestBody.create(mediaType,data);

		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.addHeader("cache-control", "no-cache")
				.build();

		Response response = client.newCall(request).execute();
		String tempResponse =  response.body().string();

		logger.info("code = "+response.code());
		Assert.assertEquals(response.code(),200);

		logger.info("tempResponseOfPost = "+tempResponse);
		jsonResult= JSON.parseObject(tempResponse);

		return jsonResult;
	}



}
