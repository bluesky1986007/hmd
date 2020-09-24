package auto.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/2 20:32
 * Desc :  商品详情页
 */
public class ProductDetailsPage {


    //    卖加同时还关注
    @FindBy(xpath = "/html/body/div[4]/div[2]/div[1]/div[4]/div[1]/span")
    public WebElement togetherFollow;
    //    立即购买
    @FindBy(css = "body > div.mainer > div.w1190 > div.goodscont > div.goods_itemInfo > div.actionbtn.zyspbtn > a.btnrhpd.buynow")
    public WebElement immediatelyBuyBtn;
    //    加入进货单
    @FindBy(xpath = "/html/body/div[4]/div[2]/div[1]/div[3]/div[7]/a[2]")
    public WebElement addToCartBtn;
    //    热卖推荐
    @FindBy(xpath = "/html/body/div[4]/div[2]/div[4]/div[1]/div[1]/div[1]/h2")
    public WebElement hotSell;

    public ProductDetailsPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


}
