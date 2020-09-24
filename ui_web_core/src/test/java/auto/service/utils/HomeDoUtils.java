package auto.service.utils;

import auto.common.exception.NoSuchElementByWaitException;
import auto.common.control.BaseTest;
import auto.pojo.FindElementConfig;
import auto.service.initial.DriverService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * updated by luyi ,
 * Date ： 2020/3/31 14:33
 *
 */
@Service
public class HomeDoUtils {

    private static final String FILE_PATH = "\\screen";
    public Logger logInfo = LoggerFactory.getLogger(HomeDoUtils.class);
    public ResourceBundle resourceBundle = ResourceBundle.getBundle("global");
    //    定义路径
//    private static  ResourceBundle resourceBundle;
//    static {
//        resourceBundle = ResourceBundle.getBundle("global");
//    }


    /**
     * @Author : haomingjian , 2019/11/20 14:22
     * @param webElement
     * @retrun :
     * @Description : 有时会有网页的弹窗图层，导致不能直接click元素，使用js方式直接忽略弹窗图层进行点击
     */
    public void clickByElement(WebDriver driver,WebElement webElement) {
        try {
            webElement.click();
        }catch (ElementClickInterceptedException e){
            logInfo.info("找到页面元素，可能有图层覆盖无法点击，尝试用js点击元素");
            jsExecutor(driver,"arguments[0].click();", webElement);
        }
    }


    //    打开浏览器之前先最大化浏览
    public void toUrl(WebDriver driver,String url) {
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }


    public void back(WebDriver driver) {
        driver.navigate().back();
    }


    public void forward(WebDriver driver,String url) {
        driver.navigate().forward();
    }


    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    /**
     * @Author : haomingjian , 2019/12/2 17:15
     * @param startX
     * @param startY
     * @param windowHigh
     * @param windowWidth
     * @retrun :
     * @Description : 设置浏览器大小和起止点
     */
    public void setWindow(WebDriver driver,int startX, int startY, int windowHigh, int windowWidth) {
//        设置起点
        Point point = new Point(startX,startY);
//        设置窗口大小
        Dimension dimension = new Dimension(windowWidth,windowHigh);
        driver.manage().window().setPosition(point);
        driver.manage().window().setSize(dimension);

    }

    //    最大化浏览器
    public void maxWindow(WebDriver driver) {
        driver.manage().window().maximize();

    }


    //    双击
    public void doubleClick(WebDriver driver,WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.doubleClick(webElement).build().perform();
    }

    //    是否是多选框
    public boolean selectIsMultiple(WebElement selectWebElement) {
        Select select = new Select(selectWebElement);
        return select.isMultiple();
    }

    //    通过下标进行选择
    public void selectByIndex(WebElement selectWebElement, int index) {
        Select select = new Select(selectWebElement);
        select.selectByIndex(index);
    }

    //    通过value值进行选择
    public void selectByValue(WebElement selectWebElement, String value) {
        Select select = new Select(selectWebElement);
        select.selectByValue(value);

    }

    //    根据文本内容进行选择
    public void selectByText(WebElement selectWebElement, String text) {
        Select select = new Select(selectWebElement);
        select.selectByVisibleText(text);
    }


    //    根据下标取消选择
    public void deselectByIndex(WebElement selectWebElement, int index) {
        Select select = new Select(selectWebElement);
        select.deselectByIndex(index);
    }

    //    根绝value值取消选择
    public void deselectByValue(WebElement selectWebElement, String value) {
        Select select = new Select(selectWebElement);
        select.deselectByValue(value);
    }

    //    根据文本内容取消选择
    public void deselectByText(WebElement selectWebElement, String text) {
        Select select = new Select(selectWebElement);
        select.deselectByVisibleText(text);
    }

    //    全部取消选择
    public void deselectAll(WebElement selectWebElement) {
        Select select = new Select(selectWebElement);
        select.deselectAll();
    }

    //    单选框是否被选中
    public boolean radioIsSelected(WebElement radioOptionWebElement) {
        return radioOptionWebElement.isSelected();
    }


    //    通过value值选择单选框
    public void radioSelectByValue(List<WebElement> radioOptionWebElements, String selectValue) {
        for (WebElement webElement : radioOptionWebElements){
            if (webElement.getAttribute("value").equals(selectValue)){
                if (!radioIsSelected(webElement)){
                    webElement.click();
                    break;
                }
            }
        }
    }



    //    执行JS
    public Object jsExecutor(WebDriver driver,String jsStr,Object ... objects) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
        return javascriptExecutor.executeScript(jsStr,objects);
    }

    //    等待元素出现并点击，若未出现，则略过，用于那种非业务流程中的元素操作
    public void waitOnClick(WebDriver driver,By by) {
        if (wait(driver,by)){
            driver.findElement(by).click();
        }
    }

    /**
     * @Author : haomingjian , 2019/12/2 17:23
     * @param webElement
     * @param x 拖动横坐标
     * @param y 拖动纵坐标
     * @param sleepTime 间隔时间
     * @param count 拖动几次
     * @retrun :
     * @Description : 拖动元素
     */
    public void dragElement(WebDriver driver,WebElement webElement, int x, int y, int sleepTime,int count) {
        Actions actions = new Actions(driver);
        //执行拖拽（先按住，然后移动），中间自定义停顿时间
        if (sleepTime != 0){
            for (int i = count; i > 0; i--) {
                actions.clickAndHold(webElement).moveByOffset(x, y);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else {
            actions.clickAndHold(webElement).moveByOffset(x, y);
        }
        //执行一系列操作
        actions.moveToElement(webElement).release();
        actions.build().perform();
    }


    //    滑动至元素位置
    public void scrollPage(WebDriver driver,WebElement... scrollWebElements) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        for (WebElement element : scrollWebElements) {
            javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //    滑动至元素位置并点击元素,确保元素可以点击才可以使用该方法
    public void scrollPageAndClick(WebDriver driver,WebElement... scrollWebElements) {
        logInfo.info("开始滑动并点击传入的各个页面元素");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        WebDriverWait webDriverWait = new WebDriverWait(driver,60);
        for (WebElement element : scrollWebElements) {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            element.click();
        }
    }

    //    按下键盘
    public void keyDown(WebDriver driver,String keys) {
        Actions actions = new Actions(driver);
        actions.keyDown(keys);

    }

    //    抬起键盘
    public void keyUp(WebDriver driver,String keys) {
        Actions actions = new Actions(driver);
        actions.keyUp(keys);
    }



    /**
     * @Author : haomingjian , 2019/11/20 14:15
     * @param
     * @retrun : {@link List< String>}
     * @Description : 获取全部句柄，把原生的返回Set，改成了返回List，方便get
     */
    public List<String> getAllWindowHandles(WebDriver driver){
        List<String> list = new ArrayList<>();
        Set<String> set = driver.getWindowHandles();
        for (String str : set){
            list.add(str);
        }
        return list;
    }



    /**
     * @Author : haomingjian , 2019/11/21 16:10
     * @param driver
     * @param alertNames
     * @retrun :
     * @Description :关闭前端自己写的弹窗，给多个alertName，参数：配置文件中的 alert.后面的参数名
     */
    public void closeHomeDoAlert(WebDriver driver ,String... alertNames) {

        logInfo.info("跳过新首页引导和用户类型选择");

        FindElementConfig findElementConfig = null;
        for (String alertName : alertNames) {
            findElementConfig = getAlertConfig(alertName);
            logInfo.info("弹窗定位方式：{}，定位表达式：{}",findElementConfig.getWay(),findElementConfig.getExpression());
            boolean isExist = waitByExpression(driver,findElementConfig);
            if (isExist){
                WebElement element = driver.findElement(getByWay(findElementConfig));
                element.click();
            }
        }
    }


    /**
     * @Author : haomingjian , 2019/11/26 16:48
     * @param findElementConfig
     * @retrun : {@link By}
     * @Description : 根据定位方式和表达式，返回一个By对象
     */
    public By getByWay(FindElementConfig findElementConfig){
//        way = way.toLowerCase();
        By by = null;
        switch (findElementConfig.getWay()){
            case "id" : by = By.id(findElementConfig.getExpression());break;
            case "name" : by = By.name(findElementConfig.getExpression());break;
            case "className" : by = By.className(findElementConfig.getExpression());break;
            case "xpath" : by = By.xpath(findElementConfig.getExpression());break;
            case "cssSelector" : by = By.cssSelector(findElementConfig.getExpression());break;
            case "linkText" : by = By.linkText(findElementConfig.getExpression());break;
            case "tagName" : by = By.tagName(findElementConfig.getExpression());break;
            case "partialLinkText" : by = By.partialLinkText(findElementConfig.getExpression());break;
            default:
                logInfo.error("定位方式有误...");
        }
        return by;
    }

    /**
     * @Author : haomingjian , 2019/11/21 15:34
     * @param alertContent
     * @retrun : {@link Map< String, String>}
     * @Description : 获取弹窗的定位方式和定位表达式String [0]: 定位方式，String [1] :表达式
     */

    public FindElementConfig getAlertConfig(String alertContent){
        String alertStr = resourceBundle.getString("alert."+alertContent);
        String [] alertConfig = alertStr.split(":");
        FindElementConfig findElementConfig = new FindElementConfig();
        findElementConfig.setWay(alertConfig[0]);
        findElementConfig.setExpression(alertConfig[1]);
        return findElementConfig;
    }


    /**
     * @Author : haomingjian , 2019/11/21 14:52
     * @param
     * @retrun :
     * @Description : 原生弹窗 -- 点击确认
     */
    public void closeAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }


    /**
     * @Author : haomingjian , 2019/11/5 9:50
     * @param driver
     * @param by 查找元素的方式和表达式
     * @retrun : {@link boolean}
     * @Description : 成功查询到元素 ？ true : false
     */
    public boolean wait(WebDriver driver,By by){
        //计算需要查询的次数
        int count = getFindCount(BaseTest.WAIT_TIME,BaseTest.WAIT_FREQUENCY);
        int findSeconds = count;
        while (count > 0) {
            try {
                try {
                    Thread.sleep(1000 * BaseTest.WAIT_FREQUENCY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logInfo.error("线程等待异常...");
                }
                count--;
                driver.findElement(by);
            } catch (NoSuchElementException e) {
                if (count <= 0){
                    logInfo.error("查找消耗{}秒，每{}秒查询一次，查找{}次,未找到对应元素，请检查页面: '{}'中的元素：{}",findSeconds * BaseTest.WAIT_FREQUENCY,BaseTest.WAIT_FREQUENCY,findSeconds,driver.getCurrentUrl(),by.toString());
                    return false;
                }
                continue;
            }
            break;
        }
        return true;
    }



    /**
     * @Author : haomingjian , 2019/11/26 16:51
     * @param driver
     * @param by
     * @retrun :
     * @Description : 如果规定时间内未找到元素则抛出异常
     */
    public void waitAndFailThrowsException(WebDriver driver,By by) throws NoSuchElementByWaitException {
        //计算需要查询的次数
        int count = getFindCount(BaseTest.WAIT_TIME,BaseTest.WAIT_FREQUENCY);
        int findSeconds = count;
        while (count > 0) {
            try {
                try {
                    Thread.sleep(1000 * BaseTest.WAIT_FREQUENCY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logInfo.error("线程等待异常...");
                }
                count--;
                driver.findElement(by);
            } catch (NoSuchElementException e) {
                if (count <= 0){
                    logInfo.error("查找消耗{}秒，每{}秒查询一次，查找{}次,未找到对应元素，请检查页面: '{}'中的元素：{}",findSeconds * BaseTest.WAIT_FREQUENCY,BaseTest.WAIT_FREQUENCY,findSeconds,driver.getCurrentUrl(),by.toString());
                    throw new NoSuchElementByWaitException("未找到对应元素："+by.toString());
                }
                continue;
            }
            break;
        }
    }



    /**
     * @Author : haomingjian , 2019/11/5 10:42
     * @param driver
     * @param title
     * @retrun : {@link boolean}
     * @Description : 规定时间内查看页面title是否符合预期
     */
    public boolean waitPageLoad(WebDriver driver,String title){
        int count = getFindCount(BaseTest.WAIT_TIME,BaseTest.WAIT_FREQUENCY);
        while (count > 0){
            try {
                Thread.sleep(1000 * BaseTest.WAIT_FREQUENCY);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logInfo.error("线程等待异常...");
            }
            if (driver.getTitle().contains(title) == true){
                logInfo.info("已找到，耗时{}S",getFindCount(BaseTest.WAIT_TIME,BaseTest.WAIT_FREQUENCY) * BaseTest.WAIT_FREQUENCY);
                return true;
            }
            count--;
        }
        logInfo.info("未找到，请检查页面...");
        return false;
    }



    /**
     * @Author : haomingjian , 2019/11/5 10:56
     * @param driver
     * @param url
     * @retrun : {@link boolean}
     * @Description : 规定时间内查看页面url是否符合预期
     */
    public boolean waitPageUrl(WebDriver driver,String url){
        int count = getFindCount(BaseTest.WAIT_TIME,BaseTest.WAIT_FREQUENCY);
        while (count > 0){
            try {
                Thread.sleep(1000 * BaseTest.WAIT_FREQUENCY);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logInfo.error("线程等待异常...");
            }
            if (driver.getCurrentUrl().contains(url) == true){
                logInfo.info("已找到，耗时{}S",getFindCount(BaseTest.WAIT_TIME,BaseTest.WAIT_FREQUENCY) * BaseTest.WAIT_FREQUENCY);
                return true;
            }
            count--;
        }
        logInfo.info("未找到，请检查页面...");
        return false;
    }



    /**
     * @Author : haomingjian , 2019/11/26 16:52
     * @param driver
     * @param findElementConfig
     * @retrun : {@link boolean}
     * @Description : 提供定位元素的实体，进行不同定位方式的寻找元素，并进行等待，超时则抛出异常
     */
    public boolean waitByExpression(WebDriver driver, FindElementConfig findElementConfig){
        //计算需要查询的次数
        int count = getFindCount(BaseTest.WAIT_TIME,BaseTest.WAIT_FREQUENCY);
        int findSeconds = count;
        while (count > 0) {
            try {
                try {
                    Thread.sleep(1000 * BaseTest.WAIT_FREQUENCY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logInfo.error("线程等待异常...");
                }
                count--;
                driver.findElement(getByWay(findElementConfig));
            } catch (NoSuchElementException e) {
                if (count <= 0){
                    logInfo.error("查找消耗{}秒，每{}秒查询一次，查找{}次,未找到对应元素，请检查页面: '{}'中的元素表达式：{}",
                            findSeconds * BaseTest.WAIT_FREQUENCY,BaseTest.WAIT_FREQUENCY,findSeconds,driver.getCurrentUrl(),findElementConfig.getWay() + " : " + findElementConfig.getExpression());
                    return false;
                }
                continue;
            }
            break;
        }
        return true;

    }


    /**
     * @Author : haomingjian , 2019/11/5 10:37
     * @param seconds
     * @param everyFindTime
     * @retrun : {@link int}
     * @Description : 根据查询总时间和查询频率，计算查询的次数
     */
    private int getFindCount(int seconds,int everyFindTime){
        return ((seconds - 1 )/ everyFindTime) + 1;
    }


    /**
     * @Author : haomingjian , 2019/11/26 10:33
     * @param driver
     * @retrun :
     * @Description : 截图路径为screen下 : 日期/时间/url.png
     */
    public void screenByDriver(WebDriver driver) {
        File screen = null;
        String path = null;
        String url = null;
        try {
            screen  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Date date = new Date();
            url = driver.getCurrentUrl();
            path = resourceBundle.getString("webdriver.screen.path")+"\\"+new SimpleDateFormat("yyyyMMdd").format(date)+"\\"+
                     new SimpleDateFormat("HH_mm_ss  ").format(date) + url.substring(url.indexOf("://")+3,url.indexOf(".com/")+4)+".png";
            FileUtils.copyFile(screen,new File(path));
            logInfo.info("截图成功，截图路径：{}，出错的url：{}",path,url);
        }catch (NullPointerException e){
            logInfo.error("截图失败，未获取到Driver，请检查脚本");
        }catch (IOException e) {
            logInfo.error("截图失败,出错的url: {}",url);
            e.printStackTrace();
        }
    }



}
