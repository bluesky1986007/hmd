package auto.common.exception;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/4 16:01
 * Desc ：登录失败异常
 */
public class LoginErrorException extends Exception {
    public LoginErrorException(String message) {
        super(message);
    }
}
