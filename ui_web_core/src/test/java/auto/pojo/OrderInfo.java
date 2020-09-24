package auto.pojo;

import lombok.Data;

/**
 * Created by haomingjian ,
 * Date ： 2019/11/21 19:50
 * Desc ： 订单信息实体
 */

@Data
public class OrderInfo {
    private String orderId;
    private String orderAmount;
    private String orderStatus;
}
