package auto.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by haomingjian ,
 * Date ： 2019/11/4 22:18
 * Desc ： 确认订单页
 */
public class ConfirmOrderPage {

    //    支付金额
    @FindBy(id = "factPrice")
    public WebElement payMoney;

    //    暂不发票
//    @FindBy(partialLinkText = "暂不开票")
    @FindBy(xpath = "//a[text()='暂不开票']")
    public WebElement noTicketBtn;

    //    汽运
//    @FindBy(css = "#app > div > div > div > div:nth-child(2) > div.marb-16.infor-send-floor > div > div.infor-bd > ul > li:nth-child(1) > a")
//    public WebElement busTransportBtn;

    //    立即发货
    @FindBy(css = "#app > div > div > div > div:nth-child(2) > div.marb-16.infor-wait-floor > div.infor-bd > ul > li:nth-child(1) > a")
    public WebElement immediateDeliveryBtn;
//    @FindBy(xpath = "//div[@class='marb-16 infor-wait-floor']/div[2]/ul/li[1]/a")
//    public WebElement immediateDeliveryBtn;
    //    非账期用户支付 - 普通支付
//    @FindBy(xpath = "/html/body/div[2]/div[9]/div[2]/div/label/em/input")
    @FindBy(css = "#app > div > div > div > div:nth-child(2) > div.marb-16.infor-pay-floor > div.infor-bd.infor-payment-floor > div > label > em")
    public WebElement normalPay;

    //    账期用户 - 账期支付
    @FindBy(id = "accountPay")
    public WebElement accountPay;

    //    账期用户 - 普通支付
    @FindBy(xpath = "/html/body/div[2]/div[9]/div[2]/div[1]/label[2]/em/input")
    public WebElement accountForNormalPay;

    //    确认提交按钮
    @FindBy(xpath = "//a[text()='确认提交']")
    public WebElement  commitOrderBtn;

    public ConfirmOrderPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


}
