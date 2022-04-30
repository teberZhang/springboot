package com.example.springboot.service.api;

import com.alibaba.fastjson.JSON;
import com.example.springboot.common.BaseResult;
import com.example.springboot.common.dto.jg.DecryptDto;
import com.example.springboot.common.dto.jg.EncryptDto;
import com.example.springboot.common.exception.CustomException;
import com.example.springboot.common.utils.Sm4Util;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Slf4j
@Service
public class JgSignService {

    final Base64.Decoder decoder = Base64.getDecoder();

    private final static Logger logger = LoggerFactory.getLogger(JgSignService.class);

    /***
     * 监管加密
     * @param encryptDto
     * @return
     */
    public BaseResult<?> encrypt(EncryptDto encryptDto) {
        try {
            logger.info("========================================== 监管加密Start ==========================================");
            logger.info("请求的data: {}", encryptDto.getData());
            String paramStr = new String(decoder.decode(encryptDto.getData()), "UTF-8");
            logger.info("加密前明文: {}", paramStr);
            logger.info("加密的key: {}", encryptDto.getKey());
            String signature = Sm4Util.encrypt(encryptDto.getKey(), paramStr);
            logger.info("加密后密文: {}", signature);
            return BaseResult.ok(signature);
        } catch (Exception e) {
            throw new CustomException("加密失败:" + e.getMessage());
        }
    }

    /***
     * 监管解密
     * @param decryptDto
     * @return
     */
    public BaseResult<?> decrypt(DecryptDto decryptDto) {
        try {
            logger.info("========================================== 监管解密Start ==========================================");
            logger.info("解密前明文: {}", decryptDto.getData());
            logger.info("解密的key: {}", decryptDto.getKey());
            String decryptStr = Sm4Util.decrypt(decryptDto.getKey(),decryptDto.getData());
            logger.info("解密后DATA: {}", decryptStr);
            return BaseResult.ok(JSON.parse(decryptStr));
        } catch (Exception e) {
            throw new CustomException("解密失败:" + e.getMessage());
        }
    }
}
