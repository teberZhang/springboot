package com.example.springboot.common.dto.finance;

import com.example.springboot.common.dto.BaseSignDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotifyDTO extends BaseSignDTO {
    private static final long serialVersionUID = 8398377314103596897L;

    // 推送的消息ID
    private String unionKey;

    // 回调类型： 1记账，2分账，9退款
    private Integer apiType;

    // 推送的订单号
    private String orderFlowNo;

    // 推送的记账流水号
    private String recordFlowNo;

    // 财务系统处理完成时间 (yyyy-MM-dd HH:mm:ss)
    private String finishTime;

    // 20000为成功，其他为失败
    private Integer code;

    // 错误描述，失败时该字段有值
    private String error;
}
