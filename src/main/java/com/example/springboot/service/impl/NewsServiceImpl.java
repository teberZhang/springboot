package com.example.springboot.service.impl;

import com.example.springboot.orm.entity.slave.News;
import com.example.springboot.orm.mapper.slave.NewsMapper;
import com.example.springboot.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author teber_zhang
 * @since 2021-12-22
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

}
