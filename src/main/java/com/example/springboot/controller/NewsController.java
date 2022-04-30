package com.example.springboot.controller;


import com.example.springboot.common.BaseResult;
import com.example.springboot.common.annotation.log.SpringLogs;
import com.example.springboot.service.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author teber_zhang
 * @since 2021-12-22
 */
@Slf4j
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private INewsService newsService;

    @RequestMapping(value = "/getNews", method = RequestMethod.GET)
    @SpringLogs(serviceType="NewsController.getNews", description="news:获取新闻信息")
    public BaseResult<?> getNews(Integer id) {
        log.info("获取新闻信息");
        return BaseResult.ok(newsService.getById(id));
    }
}
