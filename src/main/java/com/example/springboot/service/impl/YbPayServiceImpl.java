package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.common.exception.CustomException;
import com.example.springboot.common.utils.ZASignUtil;
import com.example.springboot.service.YbPayService;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.*;

@Service
public class YbPayServiceImpl implements YbPayService {

    public void ybTest()
    {
        //调用方私钥
        String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIIy92JN4XRrn3ectp2ywkmbv0jSISooRTv2gkX5PSU8UtX5jsUAg2L7K9BRo23ehn3aZQWcBJDOhnhETM0tLOOX4RuJ2L2d4EylYGbKTNcIA5YTUGctzZec51nA7lI7jUaX4TUw9LnAkamMUbQPVMTgwN+PLuuLY9cenNgndFflAgMBAAECgYBaxB0iMxkRiFXLlTzaA1ze4+0eXx8vvJNHkjqPfYnbA4rcEPYu8ISn5GJPABrm5VLtOetjVbj5GoE0JqlEAFJh+MCvHvk4MDv8PnWekMc8wJ0dbZJiECF4d8DJ29qKIgjLxYHYhLDRzV4qASDBueyC9h0BhOSxQlONawjAIrNxoQJBAMOMk7gh8SKeWViMWbB/cKHiw2mzVSJ+Jr2+Lws2ERZbxEouPn0CnOLjZ7Qz0oGZqRE8LFuF+BjKb6uQZCz79qsCQQCqcrLxidfb7GXQ9tpG/8DQOGmH81E8BCFYDIOeQE/eCdm5KOlO5qVTZLOdsXnyPXq8pZqr5Ijm/Bykot7eWCuvAkEAqY0iJ+2GHvYdkeFfguF8z2DG/sc3cgjfnnVgZOAkQLago5ElLU8uqKt0J5MnWqfZJ9B8xX6LdzNPT/qKYVa2sQJAdvkiwIgX3RGQLCDyUVaZjn2h49xJ2puDbsMlZ+O6G+FCUQUmfQVvZttGqI7F8PlK37y0TsFxcdGOaLiq2gYvTwJBAMFYCLi4ux8H5N7Ly3G5JSfkpRX97Iz3gPBlrToSFIB49yGZaZN/o9DPAtaU8aL02P6XzHV/R/ilVMyHfUOuMqY="; // 我方私钥
        //平台公钥
        String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8apaaAKTIuvfGxUmVlS8m4HSd8wALcsaHU7UJIPBuAEEIqyLz7ArXOpyqY3lk4qDE1is0MsdqB8NGQQL9wCN04aMTahHk46wA+4bCl5MvCyKrPNA5NnyVag/+V8uJm/SFgY7GrH8CiRirBaYtvamyEkBjDv9gOc/QZ0uz9IXxkQIDAQAB"; // 对方公钥

        //接口业务入参，以获取自付比例接口为例
        String str = "{'sbjgbh':'370100', 'yyxmbm':'000101010101XZ_SI', 'rq':'20200401000000'}";

        try {
            //签名参数生成
            String paramSign = ZASignUtil.paramSign(private_key, public_key, str);
            //追加医院编号
            JSONObject jo = JSON.parseObject(paramSign);
            jo.put("yybh", "37011234");
            //拼接入参
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("param", jo.toString());


            int timeout = 60000; // 超时时间60s
            CloseableHttpClient httpClient = HttpClients.createDefault();
            RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(timeout)
                    .setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();

            HttpPost httpPost = null;
            List<NameValuePair> nvps = null;
            CloseableHttpResponse response = null;
            HttpEntity resEntity = null;

            try {
                httpPost = new HttpPost("https://jnsybj.darewayhealth.com/onlineDirectoryManage/getZfbl");
                httpPost.setConfig(defaultRequestConfig);

                nvps = new ArrayList<NameValuePair>();
                Iterator it = map.keySet().iterator();

                while (it.hasNext()) {
                    String key = it.next() + "";
                    Object o = map.get(key);
                    if (o != null && o instanceof String) {
                        nvps.add(new BasicNameValuePair(key, o.toString()));
                    }
                    if (o != null && o instanceof String[]) {
                        String[] s = (String[]) o;
                        if (s != null)
                            for (int i = 0; i < s.length; i++) {
                                nvps.add(new BasicNameValuePair(key, s[i]));
                            }
                    }
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

                response = httpClient.execute(httpPost);
                resEntity = response.getEntity();
                String resStr = EntityUtils.toString(resEntity, Consts.UTF_8);
                EntityUtils.consume(resEntity);

                System.out.println("接口调用结果："+resStr);
            } catch (UnsupportedEncodingException e) {
                throw new CustomException(e);
            } catch (ClientProtocolException e) {
                throw new CustomException(e);
            } catch (SocketTimeoutException e) {
                throw new CustomException("调用服务超时", e);
            } catch (ConnectTimeoutException e) {
                throw new CustomException("调用服务超时", e);
            } catch (IOException e) {
                throw new CustomException(e);
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}