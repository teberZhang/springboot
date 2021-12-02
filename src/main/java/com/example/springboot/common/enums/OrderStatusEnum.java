package com.example.springboot.common.enums;

/***
 * 订单状态枚举类
 */
public enum OrderStatusEnum {

    WAIT_PAY("wait_pay", "待支付"),
    PAY_SUCCESS("pay_success", "支付成功"),
    COMPLETE("complete", "订单结束");

    private final String code;

    private final String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    OrderStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    /***
     * 验证订单状态是否合法
     * @param code
     * @return
     */
    public static boolean isValidStatus(String code) {
        for (OrderStatusEnum value : OrderStatusEnum.values()) {
            if (code.equals(value.getCode())) {
                return true;
            }
        }
        return false;
    }
}
