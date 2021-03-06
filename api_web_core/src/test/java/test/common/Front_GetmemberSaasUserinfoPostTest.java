package test.common;

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

public class Front_GetmemberSaasUserinfoPostTest extends BaseTest {

	@BeforeClass
	public void beforeClass()  {

//		env = BaseTest.envOfFAT;
//		logger.info("env="+env);
		
	}
	
	@Test(enabled = true,dataProvider = "" + "testData",description="PC_公共_前端接口_根据accountId查询用户审批流程信息",timeOut=300000)
	public void getmemberSaasUserinfo(Map<String, String> datadriven)throws Exception {

		logger.info("接口测试开始...........");
		logger.info("封装数据");
		logger.info("env="+env);
		String url=null;
		String name = datadriven.get("name");
		String pwd = datadriven.get("pwd");

		String ticket = helperService.getTicketOfPCLogin(env,name,pwd);
		String cookie = "ticket="+ticket;
		logger.info("cookie:"+cookie);

		if("fat".equals(env)){
			url = "https://b2b.fat.homedo.com/mall-pcweb-compositeservice/member/allorder/getmemberSaasUserinfo";

		}else {
            //生产环境
			url = "https://b2b.homedo.com/mall-pcweb-compositeservice/member/allorder/getmemberSaasUserinfo";
		}

        logger.info("url="+url);

		String dataType = "application/json";

		logger.info("发送请求并获得response");
		JSONObject resp = httpClientService.doPostWithCookieOfJSONObject(url,"",dataType,cookie);

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
