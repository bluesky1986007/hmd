package auto.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/5 20:01
 * Desc ： 支付成功页
 */
public class PaySuccessPage {

    //    支付成功图标
    @FindBy(xpath = "/html/body/div[2]/div[2]/dl/dt")
    public WebElement paySuccessIcon;

    //    继续购物按钮
    @FindBy(xpath = "/html/body/div[2]/div[2]/dl/dd/div[1]/div/a[1]")
    public WebElement continueShopBtn;

    //    查看全部订单
    @FindBy(xpath = "/html/body/div[2]/div[2]/dl/dd/div[1]/div/a[2]")
    public WebElement selectAllOrderBtn;

    public PaySuccessPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


}
