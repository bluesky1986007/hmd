package auto.test.common;
import auto.common.data.ExcelProvider;
import auto.common.control.BaseTest;
import auto.service.business.WebCommonService;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.*;
import auto.service.initial.DriverService;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Updated by luyi ,
 * Date ： 2020/03/27 14:16
 */
public class LoginTestCase extends BaseTest {
//    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public WebCommonService webCommonService;
    @Autowired
    public DriverService driverService;


    @Parameters({"browserName"})
    @BeforeClass
//    public void open(@Optional("firefox") String browserName){
    public void open(String browserName){

        //本地测试
//        env = "pro";
//        workMode = "grid";
//        browserName = "firefox";
//        browser = browserName;

        logger.info("env="+env);
        logger.info("workMode="+workMode);
        logger.info("browsename="+browserName);

        driver = driverService.getDriver(workMode,browserName);
        logger.info("Driver已创建..."+this.getClass().getSimpleName());

    }


//    @Test(dataProvider = ""+"data",description = "LoginTest(登录流程)",retryAnalyzer = RetryProcessor.class)
    @Test(dataProvider = ""+"data",description = "LoginTest(登录流程)")
    public void loginTest(Map<String, String> data){
//        logInfo.info("--------------------------开始执行case ： {}----------------------",data.get("caseID"));

        logger.info("--------------------------开始执行case ： {LoginTestCase}----------------------\n");
        webCommonService.login(env,driver,data.get("username"),data.get("password"));
        Assert.assertTrue(webCommonService.isLogin(driver));
//        logger.info("------------------------该用例测试完成{"+browser+"}----------------------------");
        logger.info("------------------------该用例测试完成----------------------------");
    }

    @AfterClass
    public void close(){
        driver.quit();
        logger.info("WebDriver已关闭...");
    }


    @DataProvider(name="data",parallel = true)
    public Iterator<Object[]> getData() throws IOException {
//        env = "pro";
        return new ExcelProvider(env,this, 0);}

}