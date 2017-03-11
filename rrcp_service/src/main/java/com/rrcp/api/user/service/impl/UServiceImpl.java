package com.rrcp.api.user.service.impl;

import com.rrcp.encrypt.Encode;
import com.rrcp.encrypt.Encrypt;
import org.springframework.stereotype.Service;

/**
 * Created by Hpw on 2017/3/10.
 */
@Service("uService")
public class UServiceImpl implements UService {

    String json = "{\n" +
            "  \"body\": {\n" +
            "        \"sessions\": [\n" +
            "            {\n" +
            "                \"id\": \"9D87967B9337B51EE81FEB2F180DDE57\",\n" +
            "                \"start_time\": \"1489142061486\",//30秒存活时间\n" +
            "                \"end_time\": \"1489142194256\",\n" +
            "                \"duration\": 132770,\n" +
            "                \"pages\": [//手动统计，我这里是和自动统计少了一个AActivity\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 3044\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 1027\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"BActivity\",\n" +
            "                        \"duration\": 751\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 662\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"CActivity\",\n" +
            "                        \"duration\": 661\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 580\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"CActivity\",\n" +
            "                        \"duration\": 1378\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 406\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"BActivity\",\n" +
            "                        \"duration\": 1033\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 503\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 349\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"BActivity\",\n" +
            "                        \"duration\": 2667\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 614\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"CActivity\",\n" +
            "                        \"duration\": 1074\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 65571\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 3247\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"MainActivity\",\n" +
            "                        \"duration\": 1355\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 1868\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"MainActivity\",\n" +
            "                        \"duration\": 1386\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 2219\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 711\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"BActivity\",\n" +
            "                        \"duration\": 836\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 621\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"CActivity\",\n" +
            "                        \"duration\": 813\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 692\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 650\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"BActivity\",\n" +
            "                        \"duration\": 715\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"AnalyticsHome\",\n" +
            "                        \"duration\": 654\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"CActivity\",\n" +
            "                        \"duration\": 878\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"autopages\": [//自动统计\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 3045\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AActivity\",\n" +
            "                        \"duration\": 1905\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 1027\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.BActivity\",\n" +
            "                        \"duration\": 751\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 662\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.CActivity\",\n" +
            "                        \"duration\": 661\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 580\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.CActivity\",\n" +
            "                        \"duration\": 1378\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 406\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.BActivity\",\n" +
            "                        \"duration\": 1033\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 503\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AActivity\",\n" +
            "                        \"duration\": 1053\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 349\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.BActivity\",\n" +
            "                        \"duration\": 2667\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 614\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.CActivity\",\n" +
            "                        \"duration\": 1073\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 65571\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 3247\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.MainActivity\",\n" +
            "                        \"duration\": 1355\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 1868\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.MainActivity\",\n" +
            "                        \"duration\": 1386\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 2219\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AActivity\",\n" +
            "                        \"duration\": 964\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 712\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.BActivity\",\n" +
            "                        \"duration\": 835\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 621\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.CActivity\",\n" +
            "                        \"duration\": 813\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 692\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AActivity\",\n" +
            "                        \"duration\": 747\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 650\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.BActivity\",\n" +
            "                        \"duration\": 715\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.AnalyticsHome\",\n" +
            "                        \"duration\": 653\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"page_name\": \"com.umeng.example.analytics.CActivity\",\n" +
            "                        \"duration\": 877\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ],\n" +
            "        \"active_user\": {\n" +
            "            \"provider\": \"GetLog\",\n" +
            "            \"puid\": \"GetLog\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"header\": {\n" +
            "        \"appkey\": \"58747d635312dd8e3f000d62\",\n" +
            "        \"channel\": \"hpw\",\n" +
            "        \"display_name\": \"友盟SDK\",\n" +
            "        \"package_name\": \"com.umeng.example\",\n" +
            "        \"app_signature\": \"39:A5:4C:18:BC:93:6C:A3:1E:34:36:91:36:F2:6F:DA\",\n" +
            "        \"app_version\": \"6.0\",\n" +
            "        \"version_code\": 1,\n" +
            "        \"sdk_type\": \"Android\",\n" +
            "        \"sdk_version\": \"6.0.8\",\n" +
            "        \"vertical_type\": 0,\n" +
            "        \"idmd5\": \"d325cdfe543b32d337d9dbadfcd3d3ce\",\n" +
            "        \"cpu\": \"AArch64 Processor rev 0 (aarch64)\",\n" +
            "        \"os\": \"Android\",\n" +
            "        \"os_version\": \"5.1.1\",\n" +
            "        \"resolution\": \"1920*1080\",\n" +
            "        \"mc\": \"20:5d:47:c1:57:99\",\n" +
            "        \"device_id\": \"862689039411654\",\n" +
            "        \"device_model\": \"vivo X7Plus\",\n" +
            "        \"device_board\": \"msm8952\",\n" +
            "        \"device_brand\": \"vivo\",\n" +
            "        \"device_manutime\": 1483970556000,\n" +
            "        \"device_manufacturer\": \"vivo\",\n" +
            "        \"device_manuid\": \"LMY47V\",\n" +
            "        \"device_name\": \"PD1603\",\n" +
            "        \"access\": \"wifi\",\n" +
            "        \"mccmnc\": \"\",\n" +
            "        \"country\": \"CN\",\n" +
            "        \"language\": \"zh\",\n" +
            "        \"timezone\": 8,\n" +
            "        \"carrier\": \"\",\n" +
            "        \"successful_requests\": 11,\n" +
            "        \"failed_requests\": 0,\n" +
            "        \"req_time\": 16,\n" +
            "        \"imprint\": \"GwGMD2ludGVncmF0ZWRfdGVzdBgBMRaInrepyVYYKGY0ODVmYTdhZGM5YTI1MDFjNTcwZWViYWZm\\nOTlkNjg3ZGU3ZGFiZDIAFQIYIDkzZGU0ZWI0YTRiNTQ4ZmU3ZWFhYWFjOWI1NGQ0MmZhAA==\\n\"\n" +
            "    }\n" +
            "}";

    private byte[] encrypt(String data) {
        Encode encrypt = Encode.builder("58747d635312dd8e3f000d62", data.getBytes());
        return encrypt.c();
    }

    @Override
    public String getData(String data) {
        return Encrypt.ByteToString(encrypt(json));
    }
}
