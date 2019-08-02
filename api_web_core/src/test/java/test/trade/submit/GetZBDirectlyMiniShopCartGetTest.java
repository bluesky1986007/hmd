package test.trade.submit;

import com.alibaba.fastjson.JSONArray;
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

public class GetZBDirectlyMiniShopCartGetTest extends BaseTest {

	@BeforeClass
	public void beforeClass()  {		

		
	}
	
	@Test(enabled = true,dataProvider = "" + "testData",description="接口——众包大型客货单",timeOut=300000)
	public void getZBDirectlyMiniShopCart(Map<String, String> datadriven)throws Exception {

		logger.info("接口测试开始...........");

		logger.info("请求地址&封装数据");
//		String url = "https://api.homedo.com/Product/GetIsVisblePromotionList?callback=jQuery111307348956049462951_1559817232210&promotionIds=5722&_=1559817232217";
		String url = "https://api.homedo.com/Product/GetIsVisblePromotionList?promotionIds=5722&_=1559817232217";

		logger.info("发送请求并获得response");
		JSONArray resp = httpClientService.doGetJSONArray(url);


		logger.info("结果校验");
		for (int i = 0; i < resp.size() ; i++) {

			JSONObject jasonValue = resp.getJSONObject(i);
			System.out.println("IsVisble = "+jasonValue.get("IsVisble"));
			Assert.assertEquals(jasonValue.get("IsVisble").toString(),datadriven.get("isVisble"));
				
		}

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
