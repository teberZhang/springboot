package com.example.springboot.common.enums;

/***
 * 订单状态枚举类
 */
public enum OrderStatusEnum {

    WAIT_PAY("wait_pay", "待支付"),
    PAY_SUCCESS("pay_success", "支付成功"),
    COMPLETE("complete", "订单结束");

    private final String code;

    private final String label;

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    OrderStatusEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }

    /***
     * 验证订单状态是否合法
     * @param code
     * @return
     */
    public static boolean isValidStatus(String code) {
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            if (code.equals(statusEnum.getCode())) {
                return true;
            }
        }
        return false;
    }

    /***
     * 根据枚举值获取枚举描述
     * @param code
     * @return
     */
    public static String getStatusLabel(String code) {
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            if (code.equals(statusEnum.getCode())) {
                return statusEnum.getLabel();
            }
        }
        return "";
    }
}
