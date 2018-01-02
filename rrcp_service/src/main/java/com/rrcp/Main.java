package com.rrcp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rrcp.api.user.UmengException;
import com.rrcp.api.user.entity.Bean;
import com.rrcp.encrypt.Encode;
import com.rrcp.encrypt.Field;
import com.rrcp.encrypt.Utils;
import com.rrcp.enums.RrcpExceptionEnum;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;
import u.aly.bm;
import u.aly.cf;
import u.aly.cl;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/14.
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println(test());
    }

    private static byte[] test() {
        String json = "{\"content\":{\"body\":{\"sessions\":[{\"id\":\"514D100F83EB1AC4E517382D70271761\",\"start_time\":1502230202935,\"end_time\":1502230214281,\"duration\":11346,\"pages\":[{\"page_name\":\"com.zhiwuya.ehome.app.ui.other.welcome.SplashActivity\",\"duration\":2057},{\"page_name\":\"com.zhiwuya.ehome.app.ui.other.welcome.WelcomeActivity\",\"duration\":1007},{\"page_name\":\"com.zhiwuya.ehome.app.ui.other.login.LoginActivity\",\"duration\":1038},{\"page_name\":\"com.zhiwuya.ehome.app.ui.other.register.RegisterActivity\",\"duration\":1015},{\"page_name\":\"com.zhiwuya.ehome.app.ui.other.findpwd.FindPwdActivity\",\"duration\":1028},{\"page_name\":\"com.zhiwuya.ehome.app.ui.other.login.BindingPhoneActivity\",\"duration\":1057},{\"page_name\":\"com.zhiwuya.ehome.app.ui.other.login.PhoneVerifyActivity\",\"duration\":1002},{\"page_name\":\"com.zhiwuya.ehome.app.ui.main.MainActivity\",\"duration\":1087},{\"page_name\":\"com.zhiwuya.ehome.app.ui.home.activity.WebViewActivity\",\"duration\":1006},{\"page_name\":\"com.zhiwuya.ehome.app.ui.home.activity.ApplyBangfuActivity\",\"duration\":1049}],\"autopages\":[{\"page_name\":\"com.zhiwuya.ehome.app.com.zhiwuya.ehome.app.ui.other.welcome.SplashActivity\",\"duration\":1118},{\"page_name\":\"com.zhiwuya.ehome.app.com.zhiwuya.ehome.app.ui.other.welcome.WelcomeActivity\",\"duration\":1014},{\"page_name\":\"com.zhiwuya.ehome.app.com.zhiwuya.ehome.app.ui.other.login.LoginActivity\",\"duration\":1100},{\"page_name\":\"com.zhiwuya.ehome.app.com.zhiwuya.ehome.app.ui.other.register.RegisterActivity\",\"duration\":1105},{\"page_name\":\"com.zhiwuya.ehome.app.com.zhiwuya.ehome.app.ui.other.findpwd.FindPwdActivity\",\"duration\":1126},{\"page_name\":\"com.zhiwuya.ehome.app.com.zhiwuya.ehome.app.ui.other.login.BindingPhoneActivity\",\"duration\":1101},{\"page_name\":\"com.zhiwuya.ehome.app.com.zhiwuya.ehome.app.ui.other.login.PhoneVerifyActivity\",\"duration\":1006},{\"page_name\":\"com.zhiwuya.ehome.app.com.zhiwuya.ehome.app.ui.main.MainActivity\",\"duration\":1184},{\"page_name\":\"com.zhiwuya.ehome.app.com.zhiwuya.ehome.app.ui.home.activity.WebViewActivity\",\"duration\":1043},{\"page_name\":\"com.zhiwuya.ehome.app.com.zhiwuya.ehome.app.ui.home.activity.ApplyBangfuActivity\",\"duration\":1050}]}]},\"header\":{\"appkey\":\"5713382367e58e03fa000a77\",\"channel\":\"QR\",\"display_name\":\"\\u804c\\u5de5E\\u5bb6\",\"package_name\":\"com.zhiwuya.ehome.app\",\"app_version\":\"4.1.1\",\"version_code\":\"411\",\"sdk_type\":\"Android\",\"sdk_version\":\"6.0.8\",\"idmd5\":\"897a40bfae6ccd53afe4865078b7e6d5\",\"cpu\":\"AArch64 Processor rev 0 (aarch64)\",\"os\":\"Android\",\"os_version\":\"5.1.1\",\"resolution\":\"1920*1080\",\"mc\":\"FC:42:88:AB:A2:2B\",\"device_id\":\"86268106787356\",\"device_model\":\"vivo X7\",\"device_board\":\"msm8952\",\"device_brand\":\"vivo\",\"device_manutime\":\"1470747570000\",\"device_manufacturer\":\"vivo\",\"device_manuid\":\"LMY47V\",\"device_name\":\"PD1602\",\"access\":\"wifi\",\"access_subtype\":\"\",\"carrier\":\"\\u4e2d\\u56fd\\u79fb\\u52a8\",\"req_time\":508,\"vertical_type\":\"0\",\"country\":\"CN\",\"language\":\"zh\",\"timezone\":\"8\",\"successful_requests\":\"321\",\"failed_requests\":\"6\",\"app_signature\":\"7D:E4:67:CA:9E:DB:69:2E:A2:EA:95:D0:56:53:43:F5\"}},\"sdk\":\"Android%2F6.0.8+com.zhiwuya.ehome.app%2F4.1.1+vivo+X7%2F5.1.1+897a40bfae6ccd53afe4865078b7e6d5\",\"appkey\":\"5713382367e58e03fa000a77\",\"signature\":\"7D:E4:67:CA:9E:DB:69:2E:A2:EA:95:D0:56:53:43:F5\",\"serial\":\"1\",\"bean\":{\"android_id\":\"50404e11a20666d2\",\"utdid\":\"86268106787356\",\"mac\":\"FC:42:88:AB:A2:2B\",\"serial\":\"nw7z6sysoxj\",\"idfa\":\"3e0c30a4-cac7-c747-a182-7b478ef05654\",\"imei\":\"86268106787356\",\"idmd5\":\"897a40bfae6ccd53afe4865078b7e6d5\"},\"imprint\":\"CAABAAAAAQsAAgAAAAdzdWNjZWVkDAADDQABCwwAAAAIAAAABHVtaWQLAAEAAAAgODk3YTQwYmZhZTZjY2Q1M2FmZTQ4NjUwNzhiN2U2ZDUKAAIAAAFdT5DfFQsAAwAAACg2NWJkZmM3ZjM4ODg0MTRlMTZmYjViYzNiYWViOGMxZTg1OTc3ZmI5AAAAAAdjaGFubmVsCwABAAAAAlFSCgACAAABXU+Q3xULAAMAAAAoNjViZGZjN2Y2NDk4ZjMwMjhkOTBiYzZjMDIwNThiYTA4N2Y0YzEwZQAAAAAGaml0dGVyCwABAAAAATAKAAIAAAFdT5DfFQsAAwAAACg2NWJkZmM3ZjZmN2U4NjkxOGYyYWFjYzk4NzJjNTBmYmZmY2EyOGQ3AAAAAApvc192ZXJzaW9uCwABAAAABTUuMS4xCgACAAABXU+Q3xULAAMAAAAoNjViZGZjN2ZkMTBkNmY0MzYxZjFkMmY5MWFiZjU4YzZmOWQzNTM5OQAAAAABdgsAAQAAAAU1LTUtNQoAAgAAAV1PkN8VCwADAAAAKDY1YmRmYzdmMTE1NGQ0YTczMmM3NmVhYzIxNWE3NmQxOGQwMGY2ZjMAAAAAB3ByZXRpbWULAAEAAAANMTUwMDI3ODQ4MDY2MQoAAgAAAV1PkN8VCwADAAAAKDY1YmRmYzdmMWZmY2I2ZDc0NmMyMDYyMzllNTJkMDRmOWE4NTcxYzcAAAAADXJlcG9ydF9wb2xpY3kLAAEAAAABMQoAAgAAAV1PkN8VCwADAAAAKDY1YmRmYzdmMDk3NDg4YjFhMWVmNjE1OTE4NTlmNDQ4OWQzMDZkYzcAAAAAC2FwcF92ZXJzaW9uCwABAAAABTQuMC4wCgACAAABXU+Q3xULAAMAAAAoNjViZGZjN2Y0YTkzYjQ2YTdkNmEyMjQxNzRiZTk1MDA1YjNjZDc1NgAIAAIAAAABCwADAAAAIDFiNjk3MDdkNDA1N2YxNTBkOWJiNzNhNTg4OTA4YWZhAAA=\",\"imprintleast\":\"CAABAAAAAQsAAgAAAAdzdWNjZWVkDAADDQABCwwAAAAIAAAABHVtaWQLAAEAAAAgODk3YTQwYmZhZTZjY2Q1M2FmZTQ4NjUwNzhiN2U2ZDUKAAIAAAFdT5DfFQsAAwAAACg2NWJkZmM3ZjM4ODg0MTRlMTZmYjViYzNiYWViOGMxZTg1OTc3ZmI5AAAAAAdjaGFubmVsCwABAAAAAlFSCgACAAABXU+Q3xULAAMAAAAoNjViZGZjN2Y2NDk4ZjMwMjhkOTBiYzZjMDIwNThiYTA4N2Y0YzEwZQAAAAAGaml0dGVyCwABAAAAATAKAAIAAAFdT5DfFQsAAwAAACg2NWJkZmM3ZjZmN2U4NjkxOGYyYWFjYzk4NzJjNTBmYmZmY2EyOGQ3AAAAAApvc192ZXJzaW9uCwABAAAABTUuMS4xCgACAAABXU+Q3xULAAMAAAAoNjViZGZjN2ZkMTBkNmY0MzYxZjFkMmY5MWFiZjU4YzZmOWQzNTM5OQAAAAABdgsAAQAAAAU1LTUtNQoAAgAAAV1PkN8VCwADAAAAKDY1YmRmYzdmMTE1NGQ0YTczMmM3NmVhYzIxNWE3NmQxOGQwMGY2ZjMAAAAAB3ByZXRpbWULAAEAAAANMTUwMDI3ODQ4MDY2MQoAAgAAAV1PkN8VCwADAAAAKDY1YmRmYzdmMWZmY2I2ZDc0NmMyMDYyMzllNTJkMDRmOWE4NTcxYzcAAAAADXJlcG9ydF9wb2xpY3kLAAEAAAABMQoAAgAAAV1PkN8VCwADAAAAKDY1YmRmYzdmMDk3NDg4YjFhMWVmNjE1OTE4NTlmNDQ4OWQzMDZkYzcAAAAAC2FwcF92ZXJzaW9uCwABAAAABTQuMC4wCgACAAABXU+Q3xULAAMAAAAoNjViZGZjN2Y0YTkzYjQ2YTdkNmEyMjQxNzRiZTk1MDA1YjNjZDc1NgAIAAIAAAABCwADAAAAIDFiNjk3MDdkNDA1N2YxNTBkOWJiNzNhNTg4OTA4YWZhAAA=\"}";
        Map<String, Object> hashMap = toMap(json);

        String sdk = hashMap.get("sdk").toString();
        if (sdk == null || sdk.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.SDK_NULL.getMsg());
        }
        String appkey = hashMap.get("appkey").toString();
        if (appkey == null || appkey.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.APPKEY_NULL.getMsg());
        }
        String signature = hashMap.get("signature").toString();
        if (signature == null || signature.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.SIGNATURE_NULL.getMsg());
        }
        String serial = hashMap.get("serial").toString();
        if (serial == null || serial.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.SERIAL_NULL.getMsg());
        }
        String content = hashMap.get("content").toString();
        if (content == null || content.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.CONTENT_NULL.getMsg());
        }
        String bean = hashMap.get("bean").toString();
        if (bean == null || bean.equals("")) {
            LOG.error(" 发生错误, 错误信息 :" + RrcpExceptionEnum.BEAN_NULL.getMsg());
        }
        try {
            byte[] result = getEncryptData(sdk, appkey,
                    signature, Integer.parseInt(serial),
                    content, Base64.decodeBase64(hashMap.get("imprint").toString()),
                    Base64.decodeBase64(hashMap.get("imprintleast").toString()), JSON.parseObject(bean, Bean.class));
            if (result == null) {
                LOG.error(" 发生错误, 错误信息 :" + "NULL" + RrcpExceptionEnum.ENCRYPT_ERROR.getMsg());
            }
            LOG.info("invoke----------/service/encrypt " + result.toString());
            return result;
        } catch (UmengException e) {
            LOG.error(" 发生错误, 错误信息 :" + "UmengException" + RrcpExceptionEnum.ENCRYPT_ERROR.getMsg());
        }
        return null;
    }

    public static Map<String, Object> toMap(String string) {
        return JSON.parseObject(string, Map.class);
    }

    public static byte[] getEncryptData(String sdk, String appkey, String signature, Integer serial, String content, byte[] imprint, byte[] imprintleast, Bean bean) {
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
                header.put("id_tracking", Utils.createIdStracking(bean, mImprint));
                Field.getInstance().c(imprint);
                if (null != Field.getInstance().a()) {
                    byte[] var = (new cl().a(Field.getInstance().a()));
                    header.put("imprint", new BASE64Encoder().encode(var));
                } else {
                    header.remove("imprint");
                }
            } else {
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
}
