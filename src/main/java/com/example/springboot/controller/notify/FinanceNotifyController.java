package com.example.springboot.controller.notify;

import com.example.springboot.common.BaseResult;
import com.example.springboot.common.annotation.finance.VerifySigns;
import com.example.springboot.common.annotation.log.SpringLogs;
import com.example.springboot.common.dto.finance.NotifyDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/finance")
@Slf4j
public class FinanceNotifyController {
    /**
     * 财务系统回调
     * @param notifyDto notifyDto
     * @return return
     */
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    @VerifySigns
    @SpringLogs(serviceType="FinanceNotifyController.notify", description="财务系统回调")
    public BaseResult<?> notify(@RequestBody NotifyDTO notifyDto) {
        return BaseResult.ok(notifyDto);
    }
}
