package com.example.springboot.service.impl;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.BaseResult;
import com.example.springboot.common.config.SpringToBootConfig;
import com.example.springboot.common.constant.RedisKeyConstants;
import com.example.springboot.common.dto.LoginDTO;
import com.example.springboot.common.form.AdminUserListForm;
import com.example.springboot.common.form.ModifyPasswordForm;
import com.example.springboot.common.service.RedisService;
import com.example.springboot.common.vo.CommonPageVo;
import com.example.springboot.entity.AdminUser;
import com.example.springboot.mapper.AdminUserMapper;
import com.example.springboot.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements IAdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Autowired
    private SpringToBootConfig springToBootConfig;

    @Autowired
    private RedisService redisService;

    public BaseResult<LoginDTO> login(AdminUser adminUser) {
        if (StrUtil.isBlank(adminUser.getName()) || StrUtil.isBlank(adminUser.getPassword())) {
            return BaseResult.errorMsg("账号密码不匹配");
        }

        // 查找用户是否存在
        AdminUser adminUserFind = adminUserMapper.selectOne(
                new QueryWrapper<AdminUser>().eq("name", adminUser.getName())
        );
        if (Objects.isNull(adminUserFind)) {
            return BaseResult.errorMsg("账号不存在");
        }
        if (!SecureUtil.md5(adminUser.getPassword()).equals(adminUserFind.getPassword())) {
            return BaseResult.errorMsg("密码不正确");
        }

        String token = JWT.create().withAudience(adminUserFind.getId().toString())
                .withExpiresAt(DateUtil.offset(new Date(), DateField.HOUR_OF_DAY, springToBootConfig.getTokenExpireHour()))
                .sign(Algorithm.HMAC256(adminUserFind.getPassword()));

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken(token);

        return BaseResult.ok(loginDTO);
    }

    public BaseResult<?> modifyPassword(AdminUser adminUser, ModifyPasswordForm form) {

        String newPassword = StrUtil.trim(form.getNewPassword());
        String rePassword = StrUtil.trim(form.getRePassword());

        if (newPassword.length() < 6 || rePassword.length() < 6) {
            return BaseResult.errorMsg("请填写至少6位新密码");
        }

        if (!newPassword.equals(rePassword)) {
            return BaseResult.errorMsg("重复密码不匹配");
        }

        if (!SecureUtil.md5(form.getOldPassword()).equals(adminUser.getPassword())) {
            return BaseResult.errorMsg("原密码不正确");
        }

        adminUser.setPassword(SecureUtil.md5(newPassword));
        adminUserMapper.update(adminUser, new QueryWrapper<AdminUser>().eq("id", adminUser.getId()));

        return BaseResult.ok();
    }

    public CommonPageVo adminUserList(AdminUserListForm form) {

        CommonPageVo commonPageVo = new CommonPageVo();
        Page<AdminUser> page = new Page<AdminUser>(form.getPage(), form.getPageSize());
        adminUserMapper.selectPage(page, null);
        commonPageVo.setPage(form.getPage());
        commonPageVo.setSize(form.getPageSize());
        commonPageVo.setTotal(page.getTotal());
        commonPageVo.setList(page.getRecords());
        return commonPageVo;
    }

    public AdminUser findUserById(int id) {
        return adminUserMapper.selectById(id);
    }

    public AdminUser getAdmin(AdminUser adminUser) {
        String name = adminUser.getName();
        String key = RedisKeyConstants.ADMIN_USER_INFO + name;
        return (AdminUser) redisService.get(key);
    }

    public boolean setAdmin(AdminUser adminUser) {
        String name = adminUser.getName();
        String key = RedisKeyConstants.ADMIN_USER_INFO + name;
        redisService.set(key, adminUser);
        redisService.expire(key, RedisKeyConstants.COMMON_EXPIRE);
        return true;
    }
}
