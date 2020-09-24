package auto.common.control;

import auto.HomeDoAutoTestApplication;
import auto.orm.JdbcTemplateUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * update by luyi ,
 * Date ： 2020/4/14 15:31
 */
@SpringBootTest(classes = HomeDoAutoTestApplication.class)
public class BaseTest extends AbstractTestNGSpringContextTests {
//    public Logger logInfo = LoggerFactory.getLogger(this.getClass());

    //环境
    public String env = System.getProperty("envID");
    //浏览器
//    public static String browserName = System.getProperty("browserName");
    //启动模式，本地还是分布式
    public String workMode = System.getProperty("workMode");
    //chrome驱动器路径
    public String chromeDriverPath = System.getProperty("chromeDriverPath");


//=========================================================================================
//    调试用的配置
//    public static String env = "pro";
//    public static String browserName = "firefox";

    //查询时长
    public static final int WAIT_TIME = 10;
    //查询频率（S）
    public static final int WAIT_FREQUENCY = 1;

    public WebDriver driver;

    public WebDriver getDriver() {
       return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    //每个线程内部都有一个ThreadLocal区域，所以可以吧静态变量保存到ThreadLocal
//    private ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();
//
//    public WebDriver getDriver(){
//        return threadLocal.get();
//    }
//
//    public void setDriver(WebDriver driver){
//        threadLocal.set(driver);
//    }

    @Autowired
    public JdbcTemplateUtils jdbcTemplateUtils;






}
