package test.pc.trade.orderSubmit;

import com.alibaba.fastjson.JSONObject;
import common.frame.data.ExcelProvider;
import common.frame.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Front_CalculationAmountPostTest extends BaseTest {

	@BeforeClass
	public void beforeClass()  {

//		env = BaseTest.envOfFAT;
//		logger.info("env="+env);

	}
	
	@Test(enabled = true,dataProvider = "" + "testData",description="P1_PC交易_下单_应付总额",timeOut=300000)
	public void calculationAmount(Map<String, String> datadriven)throws Exception {

		logger.info("接口测试开始...........");
		logger.info("env="+env);

        logger.info("获取cookie");
        String name = datadriven.get("name");
        String pwd = datadriven.get("pwd");

        String ticket = helperService.getTicketOfPCLogin(env,name,pwd);
        String cookie = "ticket="+ticket;
        logger.info("cookie:"+cookie);

		logger.info("请求地址");
		String url = null;
		if("fat".equals(env)){
			url = "https://b2b.fat.homedo.com/shopping/api/calculationAmount";
		}else {
			url = "https://b2b.homedo.com/shopping/api/calculationAmount";

		}
		logger.info("url="+url);

		logger.info("封装数据");
//		String productIdCounts = datadriven.get("productIdCounts");
		String data = "{\"productTotalAmount\":354,\"discountAmount\":54,\"productKindCount\":1,\"productTotalCount\":3,\"freightAmount\":0,\"freightBaseAmount\":0,\"additionalFee\":0,\"couponFreight\":0,\"hbAmount\":0,\"hebiAmount\":0,\"servicesAmount\":0,\"baseAmount\":0,\"couponService\":0,\"remoteAreaServiceAmount\":0,\"initNECaptcha\":\"\",\"orderAmount\":0,\"hebiCount\":0,\"deposit\":0,\"balanceAmount\":null}";
		String dataType = "application/json";

		logger.info("data:"+data);
		logger.info("dataType:"+dataType);

		logger.info("发送请求并获得response");
		JSONObject resp = httpClientService.doPostWithCookieOfJSONObject(url,data,dataType,cookie);

		logger.info("结果校验");
		String respDesc = resp.getString("respDesc");
		logger.info("respDesc = "+respDesc);
		Assert.assertEquals(respDesc,datadriven.get("respDesc"));

		logger.info("--------------接口测试结束------------------------");

	}
	
	@DataProvider(name = "testData")
	public Iterator<Object[]> data1test() throws IOException {
		return new ExcelProvider(env,this, "testData");	}
	
	@AfterClass
	public void afterClass()  {
	}
	
}		