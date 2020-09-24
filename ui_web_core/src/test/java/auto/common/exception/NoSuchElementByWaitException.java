package auto.common.exception;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/5 17:20
 * Desc ：未找到元素异常
 */
public class NoSuchElementByWaitException extends Exception {
    public NoSuchElementByWaitException(String message) {
        super(message);
    }
}
