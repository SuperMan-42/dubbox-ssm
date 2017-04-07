package com.rrcp.api.user.service.impl;

import com.rrcp.api.user.entity.UmengBean;
import com.rrcp.encrypt.Encode;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Hpw on 2017/3/10.
 */
@Service("uService")
public class UServiceImpl implements UService {

    @Override
    public UmengBean getData(String sdk, String appkey, String signature, Integer serial, String content) {
        return httpUrlConnection(sdk, appkey, signature, serial, content.getBytes());
    }

    @Override
    public byte[] getEncryptData(String sdk, String appkey, String signature, Integer serial, String content) {
        Encode encrypt = Encode.builder(appkey, signature, serial, content.getBytes());
        return encrypt.c();
    }

    private UmengBean httpUrlConnection(String sdk, String appkey, String signature, Integer serial, byte[] content) {
        try {
            String pathUrl = "http://alog.umeng.com/app_logs";
            // 建立连接
            URL url = new URL(pathUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            //设置连接属性
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            // 设置请求属性
            httpConn.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
            httpConn.setRequestProperty("X-Umeng-Sdk", sdk);
            httpConn.setRequestProperty("Msg-Type", "envelope/json");
            httpConn.setRequestProperty("Content-Type", "envelope/json");
            httpConn.setConnectTimeout(10000);
            httpConn.setReadTimeout(30000);

            // 建立输出流，并写入数据
            OutputStream outputStream = httpConn.getOutputStream();
            //获取日志
            Encode encrypt = Encode.builder(appkey, signature, serial, content);
            outputStream.write(encrypt.c());
            outputStream.flush();
            outputStream.close();
            // 获得响应状态
            int responseCode = httpConn.getResponseCode();
            String headerField = httpConn.getHeaderField("Content-Type");
            boolean var = false;
            if (headerField != null && headerField.equalsIgnoreCase("application/thrift")) {
                var = true;
            }

            if (HttpURLConnection.HTTP_OK == responseCode && var) {// 连接成功
                // 当正确响应时处理数据
                return new UmengBean("请求发送成功！！！", encrypt.c());
            } else {
                return new UmengBean("请求发送失败！！！", null);
            }
        } catch (Exception ex) {
            return new UmengBean(ex.getMessage(), null);
        }
    }
}
