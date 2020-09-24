package auto.service.business.impl;

import auto.common.control.BaseTest;
import auto.common.exception.NoSuchElementByWaitException;
import auto.page.IndexPage;
import auto.page.LoginPage;
import auto.page.PaymentPage;
import auto.service.business.WebCommonService;
import auto.service.utils.HomeDoUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by luyi ,
 * Date ： 2020/4/1 15:48
 * Desc ： 业务公共服务
 */
@Service
public class WebCommonServiceImpl extends BaseTest implements WebCommonService {

    @Autowired
    HomeDoUtils homeDoUtils;

//    public Logger logInfo = LoggerFactory.getLogger(this.getClass());
    public ResourceBundle resourceBundle = ResourceBundle.getBundle("global");
//    static {
//        resourceBundle = ResourceBundle.getBundle("global");
//    }

    @Override
    public void login(String env, WebDriver driver,String username,String password) {

        logger.info("用户开始在登录页进行登陆\n");

        LoginPage loginPage = new LoginPage(driver);
//        使用工具类的toURl，在打开页面之前会先最大化浏览器
        homeDoUtils.toUrl(driver, resourceBundle.getString(env +".page.login"));
        new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(loginPage.loginBtn));
        loginPage.usernameInput.clear();
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.clear();
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginBtn.click();
    }

    @Override
    public void loginAndRemember(String env, WebDriver driver, String username, String password, String isRememberPassword) {
        LoginPage loginPage = new LoginPage(driver);
        driver.get(resourceBundle.getString(env +".page.login"));
        if (Integer.parseInt(isRememberPassword) == 1){
            homeDoUtils.clickByElement(driver,loginPage.rememberPasswordRadio);
            logger.info("已勾选记住密码...");
        }
        loginPage.usernameInput.clear();
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.clear();
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginBtn.click();
    }


    @Override
    public Boolean isLogin(WebDriver driver) {
        logger.info("通过ticket判断用户是否已登陆");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("ticket")){
//                logInfo.info("用户已登录ticket ： {}",cookie.getValue());
                logger.info("用户已登录ticket ："+cookie.getValue());
                return true;
            }
        }
        logger.info("用户未登录");
        return false;
    }


    @Override
    public void clearLoginTicket(WebDriver driver){
        driver.manage().deleteCookieNamed("ticket");
    }

    @Override
    public void searchProductByGoodsId(WebDriver driver,String GoodsId) {

        logger.info("通过商品ID进入商品详情页...");
        IndexPage indexPage = new IndexPage(driver);
        indexPage.searchBox.clear();
        indexPage.searchBox.sendKeys(GoodsId);
        indexPage.searchBtn.click();
    }


    @Override
    public void searchProductByGoodsName(WebDriver driver, String goodsName) {
        IndexPage indexPage = new IndexPage(driver);
        indexPage.searchBox.clear();
        indexPage.searchBox.sendKeys(goodsName);
        indexPage.searchBtn.click();
    }

    @Override
    public void offlinePay(WebDriver driver,String orderAmount) {

        logger.info("开始进行线下支付操作");
        WebDriverWait webDriverWait = new WebDriverWait(driver,60);
        PaymentPage paymentPage = new PaymentPage(driver);

        //切换到线下支付tab并等等tab页加载
        paymentPage.offlinePaymentBtn.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(paymentPage.bankHolderInput));
//        if (HomeDoUtils.wait(driver, By.id("txtBankHolder"))) {

        //输入线下支付信息后进行提交
        paymentPage.bankHolderInput.clear();
        paymentPage.bankHolderInput.sendKeys("河姆渡UI自动化测试专用单位");
        paymentPage.bankNameInput.clear();
        paymentPage.bankNameInput.sendKeys("河姆渡UI自动化测试专用银行");
        paymentPage.bankNumberInput.clear();
        paymentPage.bankNumberInput.sendKeys("123456789123456789");
        paymentPage.sourceAmountInput.clear();
        paymentPage.sourceAmountInput.sendKeys(orderAmount);
        paymentPage.originalNumberInput.clear();
        paymentPage.originalNumberInput.sendKeys("123456789");
        paymentPage.remark.clear();
        paymentPage.remark.sendKeys("河姆渡UI自动化测试专用备注");
        paymentPage.commitPayBtn.click();

        //等待线下审核弹框并且点击确认
//        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"message-body\"]/dl/dd/div/a")));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(paymentPage.paySuccessConfirmBtn)).click();
//        paymentPage.paySuccessConfirmBtn.click();
//            try {
//                clickPaySuccessConfirm(driver);
//            } catch (NoSuchElementByWaitException e) {
//                e.printStackTrace();
//                logger.error("可能失败，请查看订单Id的情况 ： {}", paymentPage.orderId.getText());
//            }
//        }
//        return orderInfo;
    }


    @Override
    public void closeAlertForPayResult(WebDriver driver) throws NoSuchElementByWaitException {
        homeDoUtils.waitAndFailThrowsException(driver,By.xpath("//*[@id=\"message-body\"]/dl/dt/a"));
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.paySuccessAlertCloseBtn.click();
    }

    @Override
    public void clickPaySuccessConfirm(WebDriver driver) throws NoSuchElementByWaitException {
        homeDoUtils.waitAndFailThrowsException(driver, By.xpath("//*[@id=\"message-body\"]/dl/dd/div/a"));
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.paySuccessConfirmBtn.click();
    }


}
