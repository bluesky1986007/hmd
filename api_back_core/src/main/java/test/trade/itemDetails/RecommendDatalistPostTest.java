package test.scm.itemDetails;

import com.alibaba.fastjson.JSONObject;
import common.frame.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class RecommendDatalistPostTest extends BaseTest {

	@BeforeClass
	public void beforeClass()  {
		
	}
	
	@Test(enabled = true,dataProvider = "" + "testData",description="接口——推荐",timeOut=300000)
	public void recommendDatalist(Map<String, String> datadriven)throws Exception {

		logger.info("接口测试开始...........");

		String url = "http://api.recommand.homedo.com/recommend/datalist";

		logger.info("封装数据");
		String dataType = "application/octet-stream";
		String data = "{\"device\":{\"platform\":\"pc\"},\"ext\":{\"filter\":{\"brandId\":[],\"categoryId\":[],\"shopId\":[]},\"search\":{\"brandId\":[],\"categoryId3\":[],\"keyword\":\"\"}},\"ip\":\"101.81.200.244, 101.227.102.170, 120.27.173.37\",\"item\":{\"sku\":[100298566]},\"slot\":{\"id\":\"000102\",\"size\":5,\"type\":\"sku\"},\"user\":{\"accountId\":\"370428\",\"cookie\":\"guest81DCB4A5-25C7-7465-397B-E1CD200DCC6C\"},\"userId\":\"9bfbb27b6e942614e44eef7f4c850a9d\"}";

		logger.info("发送请求并获得response");
		JSONObject resp = httpClientService.doPostWithRaw(data,dataType,url);

		logger.info("结果校验");
		String status = resp.getString("status");
		System.out.println("status = "+status);
		Assert.assertEquals(status,datadriven.get("status"));


		logger.info("--------------接口测试结束------------------------");


	}
	
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return ExcelProviderByEnv(this, "testData");
	}
	
	@AfterClass
	public void afterClass()  {	

	}
	
}		
