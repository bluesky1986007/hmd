package test.seltest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestSel {



  @BeforeClass
  public void beforeClass() {
  }

  @Test
  public void function() throws Exception {

//		String filePath =  String.valueOf(new File("./../../").getCanonicalPath()) +"auto.pdf";
//
//		System.out.println(filePath);


//	    WebDriver  driverweb=new FirefoxDriver();
//	    driverweb.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//	    driverweb.get("http://fwgl.yirongbang.net/");


//    System.out.println("test!");
//       hf = new HttpFixture();
//       hf.nextRequest();
//       hf.setUrl("https://b2b.homedo.com/order/GetSettlement?Key=4F84E89B-80E7-4E5F-B385-3B86E7E5FA60");
//       hf.Get();
//
//	  System.out.println("status ="+hf.getStatus());
//
//       System.out.println(hf.getResponseBody());

//    OkHttpClient client = new OkHttpClient();
//
//    MediaType mediaType = MediaType.parse("application/octet-stream");
//    RequestBody body = RequestBody.create(mediaType, "{\"device\":{\"platform\":\"pc\"},\"ext\":{\"filter\":{\"brandId\":[],\"categoryId\":[],\"shopId\":[]},\"search\":{\"brandId\":[],\"categoryId3\":[],\"keyword\":\"\"}},\"ip\":\"101.81.200.244, 101.227.102.170, 120.27.173.37\",\"item\":{\"sku\":[100298566]},\"slot\":{\"id\":\"000102\",\"size\":5,\"type\":\"sku\"},\"user\":{\"accountId\":\"370428\",\"cookie\":\"guest81DCB4A5-25C7-7465-397B-E1CD200DCC6C\"},\"userId\":\"9bfbb27b6e942614e44eef7f4c850a9d\"}");
//    Request request = new Request.Builder()
//            .url("http://api.recommand.homedo.com/recommend/datalist")
//            .post(body)
//            .addHeader("cache-control", "no-cache")
////            .addHeader("postman-token", "9f9aa120-3d51-d68d-bb18-0215e611368a")
//            .build();
//
//    Response response = client.newCall(request).execute();
//
//    String tempResponse =  response.body().string();
//
//    System.out.println("resp ="+tempResponse);
//
//    JSONObject jsonObject= JSON.parseObject(tempResponse);
//
//    String status = jsonObject.getString("status");
//
//    System.out.println("status ="+status);
//    System.out.println("totle ="+jsonObject.getString("total"));
//


  }

  @AfterClass
  public void afterClass() {
  }

}
