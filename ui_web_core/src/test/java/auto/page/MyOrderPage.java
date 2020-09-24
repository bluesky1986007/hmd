package auto.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/6 8:45
 * Desc : 我的订单页
 */
public class MyOrderPage {
//    public List<WebElement> getOrderListElement() throws NoSuchElementByWaitException {
//        WaitUtils.waitAndFailThrowsException(driver.getDriver(), By.className("table-titles"),10,1);
////        logger.info("找到table-titles");
//        return driver.getDriver().findElements(By.className("table-titles"));
//    }

    //    全部订单列表中的第一个订单的订单号
//    @FindBy(xpath = "//div[@id=\"checklist\"]/div[1]/ul/li[1]/span")
    @FindBy(css = "#checklist > div > ul > li:nth-child(1) > span")
    public WebElement firstOrderIdOfMyOrderPage;

    //    线下支付的订单状态第一行
//    @FindBy(xpath = "//div[@id=\"checklist\"]/div[1]/table/tbody/tr/td[6]/label/span[1]/em")
//    @FindBy(css = "#checklist > div > table > tbody > tr > td:nth-child(6) > label > span.yellowss > em")
//    public WebElement offlinePayOrderStatusFirst;
//
//    //    线下支付的订单状态第二行
//    @FindBy(css = "#checklist > div > table > tbody > tr > td:nth-child(6) > label > span:nth-child(3)")
//    public WebElement offlinePayOrderStatusSecond;

    //线下支付的订单状态
    @FindBy(css = "#checklist > div > table > tbody > tr > td:nth-child(6) > label > span:nth-child(3)")
    public WebElement offlinePayOrderStatus;

    //    总金额
    @FindBy(xpath = "//div[@id=\"checklist\"]/div[1]/table/tbody/tr/td[5]")
    public WebElement orderAmount;

    //    我的订单
    @FindBy(xpath = "//div[@id=\"app\"]/div[1]/div[1]/ul/h2")
    public WebElement myOrder;

    //    合并付款
    @FindBy(id = "checkboxAllPay")
    public WebElement checkboxAllPayBtn;

    //    订单列表
    @FindBy(id = "checklist")
    public WebElement orderCheckList;

    @FindBy(className = "chekbbh")
    public List<WebElement> orderIds;

    // 查询条件--商品名称/订单编号
    @FindBy(id = "selfselect")
    public WebElement searchOfOrderId;

    // 查询按钮
    @FindBy(className = "myord4")
    public WebElement searchButton;

    public MyOrderPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

}
