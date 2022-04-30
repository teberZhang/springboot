package com.example.springboot.orm.mapper.master;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.orm.entity.master.User;
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
@DS(value = "master")
public interface UserMapper extends BaseMapper<User> {

}
