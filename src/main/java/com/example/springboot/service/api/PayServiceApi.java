package com.example.springboot.service.api;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.BaseResult;
import com.example.springboot.common.dto.WXPayDto;
import com.example.springboot.entity.Order;
import com.example.springboot.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PayServiceApi {

    @Autowired
    private OrderService orderService;

    public BaseResult<?> wxPay(WXPayDto dto) {
        System.out.println(dto);
        // 查询订单
        Order orderQuery = new Order();
        orderQuery.setOrder_sn(dto.getOrderNo());
        System.out.println(orderQuery);
        Order orderInfo = orderService.sel(orderQuery);
        if (ObjectUtil.isNull(orderInfo)){
            return BaseResult.errorMsg("订单不存在");
        }
        return BaseResult.ok(orderInfo);
    }
}
