package test.oms.member;

import com.alibaba.fastjson.JSONObject;
import common.frame.test.BaseTest;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static common.frame.utils.Utils.getRandomNum;

public class MemberAddPostTest extends BaseTest {

    OkHttpClient client = new OkHttpClient();
    String cookie = null;

//    @Parameters({"cookieValue"})
    @BeforeClass
    public void beforeClass() {

//        this.cookie = cookieValue;
        this.cookie = System.getProperty("cookieValue");
//        String test = System.getProperty("autotest");
//        logger.info("test="+test);

    }


    @Test(enabled = true, dataProvider = "" + "testData", description = "接口——添加会员", timeOut = 300000)
    public void memberAddPostTest(Map<String, String> datadriven) throws Exception {

        logger.info("接口测试开始...........");

        String url = "http://application.oms.uat.homedo.com/FinanceAccountManage/Add?deleteIds=&stauts=";

        logger.info("封装数据");
        String dataType = "application/x-www-form-urlencoded";
//        String cookie = "COOKIE_USERuat.=e9e5b6c6-b189-49b5-b9be-8f75eea26838";//uat cookie
        String Cookie = cookie;
        logger.info("Cookie="+Cookie);

        String name ="autotest"+getRandomNum(4);
        String mobile ="1360666"+getRandomNum(4);

        String data = "account%5BAccountId%5D=&account%5BMemberId%5D=&account%5BName%5D=" + name + "&account%5BMobile%5D=" + mobile + "" +
                "&account%5BPassword%5D=homedo1111&account%5BRePassword%5D=homedo1111&account%5BRealName%5D=&account%5BNickname%5D=" +
                "&account%5BEmail%5D=&account%5BAddress%5D=&account%5BRecommended%5D=&account%5BGender%5D=%E7%94%B7&account%5BIsJiCai%5D=false" +
                "&account%5BJiCaiOverdueTime%5D=&account%5BIsNeiCai%5D=true&account%5BIdCardNumber%5D=&account%5BIsUsed%5D=true" +
                "&account%5BIdCardFileName%5D=&account%5BRemark%5D=&account%5BRegionId%5D=&account%5BRegion%5D=&account%5BCreditAmount%5D=0.00" +
                "&account%5Bflag%5D=0&account%5BRecommendedMobile%5D=&account%5BRecommendedId%5D=&account%5BFollowerMobile%5D=" +
                "&account%5BFollowerId%5D=&account%5BIsQuDao%5D=false&account%5BIsInner%5D=false&account%5BAladdinJobNumber%5D=" +
                "&account%5BXiaoNengSettingId%5D=&account%5BXiaoNengServiceId%5D=&account%5BProvinceId%5D=0&account%5BCityId%5D=&account%5BAreaId%5D=" +
                "&account%5BIsCycleBargain%5D=false&agent=&address%5B0%5D%5BId%5D=&address%5B0%5D%5BRecipient%5D=&address%5B0%5D%5BCompany%5D=" +
                "&address%5B0%5D%5BDistrictId%5D=0&address%5B0%5D%5BAddress%5D=&address%5B0%5D%5BMobile%5D=&address%5B0%5D%5BTelephone%5D=" +
                "&address%5B0%5D%5BEmail%5D=&address%5B0%5D%5BIsDefault%5D=false";


        logger.info("发送请求并获得response");
        JSONObject resp = httpClientService.doPostWithCookie(url,data,dataType,Cookie);

        logger.info("结果校验");
        String success = resp.getString("success");
        String message = resp.getString("message");
        logger.info("success = "+success);
        logger.info("message = "+message);
        logger.info("name = "+name);
        logger.info("mobile = "+mobile);
        Assert.assertEquals(success,datadriven.get("success"));
        Assert.assertEquals(message,datadriven.get("message"));

        logger.info("--------------接口测试结束------------------------");


    }

    @DataProvider(name = "testData")
    public Iterator<Object[]> data1test() throws IOException {
        return ExcelProviderByEnv(this, "testData");
    }

    @AfterClass
    public void afterClass() {

    }

}
