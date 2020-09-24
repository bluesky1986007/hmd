package auto.test.trade;

import auto.common.data.ExcelProvider;
import auto.common.control.BaseTest;
import auto.common.exception.NoSuchElementByWaitException;
import auto.service.business.WebCommonService;
import auto.service.initial.DriverService;
import auto.service.utils.HomeDoUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.*;
import auto.page.*;
import auto.pojo.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;


/**
 * update by luyi ,
 * Date ： 2020/4/14 11:45
 */
public class NormalOrderTestCase extends BaseTest {

    @Autowired
    public DriverService driverService;
    @Autowired
    public WebCommonService webCommonService;
    @Autowired
    public HomeDoUtils homeDoUtils;

    @Parameters({"browserName"})
    @BeforeClass
//    public void open(@Optional("firefox") String browserName){
    public void open(String browserName){

        //本地测试
//        env = "pro";
//        workMode = "grid";
//        browserName = "chrome";
//        browser = browserName;

        logger.info("env="+env);
        logger.info("workMode="+workMode);
        logger.info("browsename="+browserName);

        //创建driver
        driver = driverService.getDriver(workMode,browserName);
        logger.info("Driver已创建..."+this.getClass().getSimpleName());


    }

    /**
     * @Author : haomingjian , 2019/11/21 14:26
     * @update:  luyi, 2019/12/24
     * @retrun :
     * @Description : 普通订单支付流程
     */
//    @Test(dataProvider = ""+"data",description = "NormalOrderTest(普通订单支付流程)",retryAnalyzer = RetryProcessor.class)
    @Test(dataProvider = ""+"data",description = "NormalOrderTest(普通订单支付流程)")
    public void NormalOrderTest(Map<String, String> data) throws InterruptedException {

//        logInfo.info("------------------------开始执行case ： {}----------------------------",data.get("caseID"));
        logger.info("------------------------开始执行case ： {NormalOrderTestCase}----------------------------\n");

        WebDriverWait webDriverWait = new WebDriverWait(driver,60);
//        登录
        webCommonService.login(env,driver,data.get("username"),data.get("password"));
//        断言是否登录成功
        Assert.assertTrue(webCommonService.isLogin(driver));
//        关闭新首页引导和用户类型选择弹窗
        homeDoUtils.closeHomeDoAlert(driver,"indexGuide","choiceUserType");
//        从首页搜索框搜索商品id进入商品详情页,测试商品100017207
        String productId = data.get("goodsId");
        logger.info("测试商品id="+productId);
        webCommonService.searchProductByGoodsId(driver,productId);

        logger.info("加载商品详情页元素");
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        logger.info("点击立即购买");
//        productDetailsPage.immediatelyBuyBtn.click();
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(productDetailsPage.immediatelyBuyBtn)).click();
        //页面滑动至立即购买按钮并且点击
        homeDoUtils.scrollPageAndClick(driver, productDetailsPage.immediatelyBuyBtn);

        logger.info("进入下单页...");
        ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage(driver);
        webDriverWait.until(ExpectedConditions.visibilityOf(confirmOrderPage.noTicketBtn));
        //     分别滑动至暂不开票，汽车运输，立即发货,普通支付，并点击
        homeDoUtils.scrollPageAndClick(driver,
                confirmOrderPage.noTicketBtn,
//                confirmOrderPage.busTransportBtn,
                confirmOrderPage.immediateDeliveryBtn,
                confirmOrderPage.normalPay);

        logger.info("点击提交订单");
        confirmOrderPage.commitOrderBtn.click();

        logger.info("等待收银台加载并储存订单号和应付金额");
        PaymentPage paymentPage = new PaymentPage(driver);
        webDriverWait.until(ExpectedConditions.visibilityOf(paymentPage.offlinePayMoney));

        // 支付页加载缓慢，需要给些时间做判断，正式上线之前要将等待时间扩长
//        try {
//            homeDoUtils.waitAndFailThrowsException(driver,By.id("formSubmit"));
//        } catch (NoSuchElementByWaitException e) {
//            logger.info("等待收银台加载时元素未找到");
//            e.printStackTrace();
//        }

//        OrderInfo orderInfo = new OrderInfo();
//        orderInfo.setOrderId(paymentPage.orderId.getText());
//        orderInfo.setOrderAmount(paymentPage.offlinePayMoney.getText().substring(1));

        String orderId = paymentPage.orderId.getText();
        String orderAmount = paymentPage.offlinePayMoney.getText().substring(1);

//        进行线下支付
        webCommonService.offlinePay(driver,orderAmount);
//        logInfo.info("线下支付提交成功，订单号为：{}，订单应付金额 ：{}",orderInfo.getOrderId(),orderInfo.getOrderAmount());
        logger.info("线下支付提交成功，订单号为：{"+orderId+"}，订单应付金额 ：{"+orderAmount+"}");

        logger.info("点击查看全部订单...");
        PaySuccessPage paySuccessPage = new PaySuccessPage(driver);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(paySuccessPage.selectAllOrderBtn)).click();
//        paySuccessPage.selectAllOrderBtn.click();


        logger.info("查询最新生成的订单");
        MyOrderPage myOrderPage = new MyOrderPage(driver);
        webDriverWait.until(ExpectedConditions.visibilityOf(myOrderPage.firstOrderIdOfMyOrderPage));
//        OrderInfo OrderInfoOfMyOrderPage = new OrderInfo();
        myOrderPage.searchOfOrderId.sendKeys(orderId);
        myOrderPage.searchButton.click();
//        webDriverWait.until(ExpectedConditions.visibilityOf(myOrderPage.offlinePayOrderStatusSecond));
        Thread.sleep(1000);

        logger.info("获取并保存我的订单列表中的订单id，状态和金额");
        String orderIdOfMyOrderPage = myOrderPage.firstOrderIdOfMyOrderPage.getText();
        String orderAmountOfMyOrderPage;
        String orderAmountText = myOrderPage.orderAmount.getText();
        logger.info("orderAmountText="+orderAmountText);
//        有的时候活动免运费，后面会出现（免运费），进行预处理
      logger.info("先对订单列表的金额进行处理");
        if (orderAmountText.contains("（")){
            //储存金额并且去掉免运费几个字
//            OrderInfoOfMyOrderPage.setOrderAmount(myOrderPage.orderAmount.getText().substring(0,myOrderPage.orderAmount.getText().indexOf("（")-1));
            orderAmountOfMyOrderPage = orderAmountText.substring(0,orderAmountText.indexOf("（")-1);

            logger.info("orderAmountOfMyOrderPage="+orderAmountOfMyOrderPage);

        }else {
//            OrderInfoOfMyOrderPage.setOrderAmount(myOrderPage.orderAmount.getText());
            orderAmountOfMyOrderPage = orderAmountText;
            logger.info("orderAmountOfMyOrderPage="+orderAmountOfMyOrderPage);
        }
//        储存订单状态，因为线下支付的订单状态有两行，所以需要做预处理把两行合在一起
//        OrderInfoOfMyOrderPage.setOrderStatus(myOrderPage.offlinePayOrderStatusFirst.getText()+myOrderPage.offlinePayOrderStatusSecond.getText());
//        String orderStatus = myOrderPage.offlinePayOrderStatusFirst.getText()+myOrderPage.offlinePayOrderStatusSecond.getText();
        String orderStatus = myOrderPage.offlinePayOrderStatus.getText();
//        logInfo.info("普通线下支付订单信息，订单号：{}，订单金额：{}，订单状态：{}",OrderInfoOfMyOrderPage.getOrderId(),OrderInfoOfMyOrderPage.getOrderAmount(),OrderInfoOfMyOrderPage.getOrderStatus());
        logger.info("普通线下支付订单信息，订单号：{"+orderIdOfMyOrderPage+"}，订单金额：{"+orderAmountOfMyOrderPage+"}，订单状态：{"+orderStatus+"}");

        logger.info("进行校验");
//        断言：若订单id和订单金额不为空，则代表订单生成成功
//        Assert.assertFalse(orderInfo.getOrderId().isEmpty() && orderInfo.getOrderAmount().isEmpty());
//        断言页面上的第一笔订单号是否是刚才下单的订单号
        Assert.assertEquals(orderIdOfMyOrderPage,orderId);
        logger.info("订单id校验成功！");

//        断言页面上第一笔订单的订单金额
        Assert.assertEquals(orderAmount,orderAmountOfMyOrderPage);
        logger.info("订单金额校验成功！");

//        断言订单状态
        Assert.assertEquals(orderStatus,"已线下付款（待确认）");
        logger.info("订单状态校验成功！");


//        logger.info("------------------------该用例测试完成{"+browser+"}----------------------------");
        logger.info("------------------------该用例测试完成----------------------------");
    }


    @AfterClass
    public void close(){
        //        关闭浏览器
        driver.quit();
        logger.info("WebDriver已关闭...");
    }

    /**
     * @Author : haomingjian , 2019/11/6 15:36
     * @param
     * @Description : 数据驱动，用本类的方法寻找对应的Excel文件，还有对应的sheet页，环境
     */
    @DataProvider(name = "data",parallel = true)
    public Iterator<Object[]> getData() throws IOException {
//        env = "pro";
        return new ExcelProvider(env,this, 0);}

}
