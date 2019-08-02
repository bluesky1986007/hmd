package service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.net.MalformedURLException;

public interface HttpClientService {

	/**
	 * doGet
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 */
	public JSONArray doGetJSONArray(String url) throws MalformedURLException, Exception;


	/**
	 * doPostWithRaw
	 * @param data
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 */
	public JSONObject doPostWithRaw(String data, String url) throws MalformedURLException, Exception;
	



}
