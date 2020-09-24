package auto.service.initial;

import auto.common.control.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/2 14:33
 *
 */
@Service
public class DriverService extends BaseTest{
    //    定义路径
    private static final String FILE_PATH = "\\screen";
//    private Logger logInfo = LoggerFactory.getLogger(DriverService.class);
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("global");
//    static {
//        resourceBundle = ResourceBundle.getBundle("global");
//    }

    /**
     * @param DriverName
     * @Author : update by luyi , 2020/4/27 15:16
     * @retrun :
     * @Description : 设置driver的驱动
     */
    public WebDriver getDriverByName(String DriverName) {
        if (DriverName.equals("chrome")) {

            String chromeRealDriverPath;

            //如果maven命令里面传了参数优先拿maven参数里面的路径地址
            if (!(null==chromeDriverPath || "".equals(chromeDriverPath))){

                chromeRealDriverPath = chromeDriverPath;

            }else {
                chromeRealDriverPath = resourceBundle.getString("webdriver.chrome.path");
            }

            logger.info("webdriver.chrome.path="+chromeRealDriverPath);
            System.setProperty("webdriver.chrome.driver", chromeRealDriverPath);
            logger.info("启用浏览器"+DriverName);
            return new ChromeDriver();

        }else if (DriverName.equals("firefox")){

            System.setProperty("webdriver.gecko.driver",resourceBundle.getString("webdriver.firefox.path"));
            System.setProperty("webdriver.firefox.bin",resourceBundle.getString("webdriver.firefox.bin"));
            logger.info("启用浏览器"+DriverName);
            return new FirefoxDriver();

        }else if (DriverName.equals("ie")){

            System.setProperty("webdriver.ie.driver", resourceBundle.getString("webdriver.ie.path"));
            logger.info("启用浏览器"+DriverName);
            return new InternetExplorerDriver();

        }
//        logInfo.error("未找到对应浏览器配置，请检查是否存在‘{}’浏览器",DriverName);
        logger.error("未找到对应浏览器配置，请检查是否存在浏览器"+DriverName);
//        后续会考虑设置默认浏览器
        return null;
    }


//    获取远程节点driver
    public WebDriver getGridWebDriver(String browserName){
        DesiredCapabilities capabilities = null;
//        DesiredCapabilities capability = new DesiredCapabilities();
        WebDriver driver = null;
        String hubPort = resourceBundle.getString("grid.hub.port");
        String hubIP = resourceBundle.getString("grid.hub.ip");
        String gridUrl = "http://" + hubIP + ":" + hubPort + "/wd/hub";
        try {
            if (browserName.equals("chrome")) {
                capabilities = DesiredCapabilities.chrome();
                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(Platform.WINDOWS);
//                logInfo.info("节点地址：{}，浏览器：{}",gridUrl,browserName);
                logger.info("节点地址：{"+gridUrl+"}，浏览器：{"+browserName+"}");
                driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
            }
            else if (browserName.equals("ie")){
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setBrowserName("internet explorer");
                capabilities.setPlatform(Platform.WINDOWS);
//                logInfo.info("节点地址：{}，浏览器：{}",gridUrl,browserName);
                logger.info("节点地址：{"+gridUrl+"}，浏览器：{"+browserName+"}");
                driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
            }else if (browserName.equals("firefox")){
//                System.setProperty("webdriver.firefox.bin",resourceBundle.getString("webdriver.firefox.bin"));
                capabilities = DesiredCapabilities.firefox();
                capabilities.setBrowserName("firefox");
                capabilities.setPlatform(Platform.WINDOWS);
//                logInfo.info("节点地址：{}，浏览器：{}",gridUrl,browserName);
                logger.info("节点地址：{"+gridUrl+"}，浏览器：{"+browserName+"}");
                driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }


    public WebDriver getDriver(String workMode, String browserName){
        WebDriver webDriver = null;
//        String webdriverWorkMode = resourceBundle.getString("webdriver.work.mode");
        if (workMode.equals("本地运行")){
            logger.info("本地UI自动化初始化");
            webDriver = getDriverByName(browserName);
        }else{
            logger.info("分布式UI自动化初始化");
            webDriver = getGridWebDriver(browserName);
        }
        return webDriver;
    }



    /**
     * @Author : haomingjian , 2019/12/13 10:26
     * @param driver
     * @retrun :
     * @Description : 安全的关闭driver，防止测试用例执行没有问题时，关闭WebDriver之前， WebDriver crash导致测试用例失败
     */
    public void securityCloseDriver(WebDriver driver){
        try{
//            driver.close();
            driver.quit();
            logger.info("WebDriver已关闭...");
        }catch (NoSuchSessionException e){
            logger.error("未找到可关闭的WebDriver，请查看测试报告结果...");
        }
    }

}
