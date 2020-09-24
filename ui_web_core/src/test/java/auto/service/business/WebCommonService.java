package auto.service.business;

import auto.common.exception.NoSuchElementByWaitException;
import org.openqa.selenium.WebDriver;

/**
 * Created by luyi ,
 * Date ： 2020/4/1 15:47
 */
public interface WebCommonService {


    /**
     * 登录
     * @param env
     * @param driver
     * @param username
     * @param password
     */
    public void login(String env, WebDriver driver, String username, String password);

    /**
     * @Author : haomingjian , 2019/12/2 17:25
     * @param driver
     * @param username
     * @param password
     * @param isRememberPassword 是否记住密码？ 0：不记住，1：记住
     * @retrun :
     * @Description :
     */
    public void loginAndRemember(String env, WebDriver driver, String username, String password, String isRememberPassword);

    /**
     * 是否登录
     * @param driver
     * @return
     */
    Boolean isLogin(WebDriver driver);

    /**
     * 清除登录的ticket
     * @param driver
     */
    public void clearLoginTicket(WebDriver driver);


    public void searchProductByGoodsId(WebDriver driver,String GoodsId);
    public void searchProductByGoodsName(WebDriver driver,String goodsName);

    /**
     * 线下支付
     * @param driver
     * @return
     */
    public void offlinePay(WebDriver driver,String orderAmounts);

    /**
     * @Author : haomingjian , 2019/11/5 18:06
     * @param
     * @retrun :
     * @Description : 关闭支付结果弹窗
     */
    public void closeAlertForPayResult(WebDriver driver) throws NoSuchElementByWaitException;

    /**
     * @Author : haomingjian , 2019/11/5 19:59
     * @param
     * @retrun :
     * @Description : 点击支付结果弹窗的确认
     */
    public void clickPaySuccessConfirm(WebDriver driver) throws NoSuchElementByWaitException;


}
