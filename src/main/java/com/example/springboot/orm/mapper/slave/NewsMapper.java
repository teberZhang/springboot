package com.example.springboot.orm.mapper.slave;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.springboot.orm.entity.slave.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author teber_zhang
 * @since 2021-12-22
 */
@Component
@DS(value = "slave")
public interface NewsMapper extends BaseMapper<News> {

}
