package auto.page;

import auto.common.exception.NoSuchElementByWaitException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import auto.service.utils.HomeDoUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by haomingjian ,
 * Date ： 2019/11/5 11:19
 * Desc :  支付页
 */
public class PaymentPage {

    private Logger logger = LoggerFactory.getLogger(PaymentPage.class);

    //    订单id
    @FindBy(xpath = "//*[@id=\"formSubmit\"]/div[2]/div[1]/span/a")
//    @FindBy(xpath = "//div[@class=\"playtop\"]/span/a")
    public WebElement orderId;

    //    订单form表单
    @FindBy(id = "formSubmit")
    public WebElement pageForm;

    //    支付金额
    @FindBy(xpath = "//*[@id=\"formSubmit\"]/div[2]/div[1]/strong")
    public WebElement payMoneyNum;


    //    选择离线支付按钮
    @FindBy(id = "offline")
    public WebElement offlinePaymentBtn;

    //    汇款单位
    @FindBy(id = "txtBankHolder")
    public WebElement bankHolderInput;

    //    汇款银行
    @FindBy(id = "txtBankName")
    public WebElement bankNameInput;

    //    银行账号
    @FindBy(id = "txtBankNumber")
    public WebElement bankNumberInput;

    //    汇款金额
    @FindBy(id = "txtSourceAmount")
    public WebElement sourceAmountInput;

    //    交易流水号
    @FindBy(id = "txtOriginalNumber")
    public WebElement originalNumberInput;

    // 备注
    @FindBy(id = "txtRemark")
    public WebElement remark;

    // 线下支付 - 实付金额
    @FindBy(css = "#paymethod1 > div > div > div > div.payNum > strong")
    public WebElement offlinePayMoney;

    //    离线支付 - 确认提交
    @FindBy(id = "btnSave")
    public WebElement commitPayBtn;
    //    支付成功弹窗确认按钮
    @FindBy(xpath = "//*[@id=\"message-body\"]/dl/dd/div/a")
    public WebElement paySuccessConfirmBtn;

    //    支付成功弹窗关闭按钮
    @FindBy(xpath = "//*[@id=\"message-body\"]/dl/dt/a")
    public WebElement paySuccessAlertCloseBtn;



    /**
     * @Author : haomingjian , 2019/11/5 13:49
     * @param driver
     * @retrun : {@link null}
     * @Description : 构造器不设置url，只能由提交订单页跳转后获得本页面URL
     */
    public PaymentPage(WebDriver driver) {
            PageFactory.initElements(driver,this);
    }


}
