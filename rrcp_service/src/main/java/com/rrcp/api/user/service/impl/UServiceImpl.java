package com.rrcp.api.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rrcp.api.user.UmengException;
import com.rrcp.api.user.entity.Bean;
import com.rrcp.api.user.entity.UmengBean;
import com.rrcp.encrypt.Encode;
import com.rrcp.encrypt.Field;
import com.rrcp.encrypt.Utils;
import com.rrcp.enums.RrcpExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;
import u.aly.bm;
import u.aly.cf;
import u.aly.cl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Hpw on 2017/3/10.
 */
@Service("uService")
public class UServiceImpl implements UService {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public UmengBean getData(String sdk, String appkey, String signature, Integer serial, String content) {
        return httpUrlConnection(sdk, appkey, signature, serial, content.getBytes());
    }

    @Override
    public byte[] getEncryptData(String sdk, String appkey, String signature, Integer serial, String content, byte[] imprint, byte[] imprintleast, Bean bean) {
        try {
            JSONObject json = JSONObject.parseObject(content);
            JSONObject header = json.getJSONObject("header");
            bm mImprint = new bm();
            cf format = new cf(new u.aly.cu.a());
            if (null != imprintleast) {
                format.a(mImprint, imprintleast);
            } else {
                mImprint = null;
            }
            if (null != mImprint) {//最新
                // 将最新的合并到旧的中间
//                bm temp = new bm();//旧的
//                format.a(temp, imprint);

//                for (Iterator it = mImprint.c.a.entrySet().iterator(); it.hasNext(); ) {
//                    Map.Entry e = (Map.Entry) it.next();
//                    if (!e.getKey().equals("reg_at") && !e.getKey().equals("reg_channel"))
//                        temp.c.a.put((String) e.getKey(), (bl) e.getValue());
//                }
//                temp.c.a.remove("reg_at");
//                temp.c.a.remove("reg_channel");
                //不是第一次 id_tracking字段比较短包含一个好像是以后不变的 imprint字段有值而且返回一个只有一个字段的imprint result
                header.put("id_tracking", Utils.createIdStracking(bean, mImprint));
//                header.put("imprint", Utils.createImprint(temp));//可能有问题
//                LOG.info("\ninvoke----------imprint " + temp);
                Field.getInstance().c(imprint);
                if (null != Field.getInstance().a()) {
                    byte[] var = (new cl().a(Field.getInstance().a()));
                    header.put("imprint", new BASE64Encoder().encode(var));
                } else {
                    header.remove("imprint");
                }
            } else {
                //第一次 id_tracking字段比较长包含两个 imprint字段没有为null 但是返回一个全字段的imprint result
                header.put("id_tracking", Utils.createIdStracking(bean, null));
                header.remove("imprint");

                //重要
                Field.field = null;
            }

            LOG.info("invoke----------json " + json.toString());
            Encode encrypt = Encode.builder(appkey, signature, serial, json.toString().getBytes(), bean);
            return encrypt.c();
        } catch (u.aly.ci ci) {
            throw new UmengException(RrcpExceptionEnum.ENCRYPT_ERROR.getMsg());
        }
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
            Encode encrypt = Encode.builder(appkey, signature, serial, content, new Bean(Utils.getMacAddress(null), Utils.getDevice_id(15)));
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

                InputStream inputStream = httpConn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String liner;
                StringBuffer buffer = new StringBuffer();
                while ((liner = reader.readLine()) != null) {
                    buffer.append(liner);
                }
                return new UmengBean("请求发送成功！！！", buffer.toString());
            } else {
                return new UmengBean("请求发送失败！！！", null);
            }
        } catch (Exception ex) {
            return new UmengBean(ex.getMessage(), null);
        }
    }
}
