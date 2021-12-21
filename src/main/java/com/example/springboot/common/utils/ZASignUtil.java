package com.example.springboot.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * 类ZASignUtil的实现描述：加签加密验签解密工具类
 *
 * @author Created by zqc on 2018/11/02.
 */
public class ZASignUtil {

    private final static Logger log = LoggerFactory.getLogger(ZASignUtil.class);

    /**
     * 签名参数生成
     *
     * @param privatekey 我方私钥
     * @param publickey  对方公钥
     * @param param      参数
     * @return 签名之后的JSON格式参数
     * @throws Exception
     */
    public static String paramSign(String privatekey, String publickey, String param) throws Exception {
        //生成AES 16位明文密码
        String encodeRules = getRandomString(16);
        //用生成的AES 密码加密数据内容
        byte[] enContent = AES.Encrypt(param, encodeRules);
        //使用我方私钥生成签名
        String sign = RSAUtils.sign(enContent, privatekey);
        //使用对方公钥对AES密码进行RSA加密
        byte[] enKey = RSAUtils.encryptByPublicKey(encodeRules.getBytes(), publickey);
        //组装成json格式输出
        JSONObject object = new JSONObject();
        object.put("content", BaseUtils.encode(enContent));//aes加密后的数据内容
        object.put("timestamp", System.currentTimeMillis()); //当前时间
        object.put("encodeRules", BaseUtils.encode(enKey)); //rsa 加密后的aes密码
        object.put("sign", sign); //签名
        String json = object.toJSONString();
        log.info(">>>加签加密后：" + json);
        return json;
    }


    /**
     * 签名校验
     *
     * @param byte_content 接收到的数据
     * @param sign         签名
     * @param publickey    对方公钥
     * @param timestamp    时间戳
     * @return 验签结果
     * @throws Exception 验签失败
     */
    public static boolean checkSign(byte[] byte_content, String sign, String publickey, long timestamp) throws Exception {
        long now = System.currentTimeMillis();
        //5分钟之内请求
        if ((now - timestamp) > 300000) {
            //throw new RuntimeException("非法请求");
        }
        boolean verify = RSAUtils.verify(byte_content, publickey, sign);
        return verify;
    }

    /**
     * 验签 + 解密
     *
     * @param data          原始数据
     * @param c_private_key 我方私钥
     * @param s_public_key  对方公钥
     * @return 解密后的内容
     */
    public static String checkSignDecrypt(String data, String c_private_key, String s_public_key) throws Exception {
        JSONObject parse = JSON.parseObject(data);
        byte[] byte_content = BaseUtils.decode(parse.getString("content"));
        String sign = parse.getString("sign");
        Long timestamp = parse.getLong("timestamp");
        boolean signd = ZASignUtil.checkSign(byte_content, sign, s_public_key, timestamp);
        if (!signd) {
            throw new RuntimeException("验签失败");
        }
        log.info(">>> 验签" + (signd ? "成功" : "失败"));
        byte[] encodeRules = BaseUtils.decode(parse.getString("encodeRules"));
        return decryptData(byte_content, encodeRules, c_private_key);
    }

    /**
     * 解密数据
     *
     * @param data        加密内容
     * @param encodeRules RSA加密后的AES密码
     * @param privateKey  RSA私钥
     * @return 解密后的数据
     * @throws Exception
     */
    public static String decryptData(byte[] data, byte[] encodeRules, String privateKey) throws Exception {
        byte[] bytesKey = RSAUtils.decryptByPrivateKey(encodeRules, privateKey);
        byte[] deContent = AES.Decrypt(data, new String(bytesKey));
        String str_de_content = new String(deContent, "utf-8");
        log.info(">>> 解密后：" + str_de_content);
        return str_de_content;
    }

    static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
