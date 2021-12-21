package com.example.springboot.service.api;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.BaseResult;
import com.example.springboot.common.dto.WXPayDto;
import com.example.springboot.entity.Order;
import com.example.springboot.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayServiceApi {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    public BaseResult<?> wxPay(WXPayDto dto) {
        System.out.println(dto);
        System.out.println("${spring.jackson.timezone}");
        // 查询订单
        Order orderQuery = new Order();
        orderQuery.setOrderSn(dto.getOrderNo());
        Order orderInfo = orderServiceImpl.sel(orderQuery);
        if (ObjectUtil.isNull(orderInfo)){
            return BaseResult.errorMsg("订单不存在");
        }
//        UnifiedOrderDto unifiedOrderDto = BeanUtil.copyProperties(dto, UnifiedOrderDto.class);
//        unifiedOrderDto.setOrderNo(orderInfo.getOrder_sn());
//        unifiedOrderDto.setAmount(orderInfo.getTotal_fee());
//        unifiedOrderDto.setIpAddr(SpringUtil.getRealIp());
//        unifiedOrderDto.setOrgId(organizationResp.getOrganizationId());
//        unifiedOrderDto.setOrgCode(organizationResp.getOrganizationCode());
//        unifiedOrderDto.setPlatformReceive(ObjectUtil.isNull(organizationResp.getIsSettlement()) || 1==organizationResp.getIsSettlement());
//        unifiedOrderDto.setProfitSharing(orderAskBase.getSharingTag().toString());
//        unifiedOrderDto.setNotifyUrl(weChatPayProperties.getNotifyUrl());
//        unifiedOrderDto.setOrderSource(projectProfilesProperties.getProjectCode());
//        unifiedOrderDto.setOrderSourceDesc(projectProfilesProperties.getProjectName());
//        unifiedOrderDto.setOrderType(OrderTypeEnum.ASK_ORDER.getCode());
//        unifiedOrderDto.setOrderTypeDesc(OrderTypeEnum.ASK_ORDER.getName());
//        unifiedOrderDto.setPayChannel(PayChannelEnum.WX_PAY.getChannel());
        return BaseResult.ok(orderInfo);
    }
}
