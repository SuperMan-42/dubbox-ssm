package com.rrcp.encrypt;

import com.rrcp.api.user.entity.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;
import u.aly.*;

import java.util.*;

/**
 * Created by Hpw on 2017/3/3.
 */

public class Utils {
    private static Logger LOG = LoggerFactory.getLogger("hpw");

    //创建device_id
    public static String getDevice_id(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    //创建mc地址
    public static String getMacAddress(String hypervType) {
        String macAddress = null;
        if ("QEMU".equalsIgnoreCase(hypervType)) {
            String prefix = "52:54:00";
            macAddress = prefix.concat(":").concat(getRandChars(3));
        } else {
            macAddress = getRandChars(6);
        }
        return macAddress;
    }

    private static String getRandChars(int len) {
        String multiChars = "";
        for (int i = 0; i < len; i++) {
            multiChars = multiChars.concat(":");
            String chars = getRandTwoChars();
            multiChars = multiChars.concat(chars);
        }
        if (len > 0) {
            multiChars = multiChars.substring(1);
        }
        return multiChars;
    }

    private static String getRandTwoChars() {
        String chars = createRandomChar();
        return chars.concat(createRandomChar());
    }

    private static String createRandomChar() {
        String[] chars = new String[]{
                "a", "b", "c", "d", "e", "f", "0",
                "1", "2", "3", "4", "5", "6", "7", "8", "9",
        };
        Random rand = new Random();
        int rInt = rand.nextInt(chars.length);
        return chars[rInt];
    }

    public static byte[] getByte(String string) {
        if (string == null) {
            return null;
        } else {
            int length = string.length();
            if (length % 2 != 0) {
                return null;
            } else {
                byte[] buf = new byte[length / 2];

                for (int i = 0; i < length; i += 2) {
                    buf[i / 2] = (byte) Integer.valueOf(string.substring(i, i + 2), 16).intValue();
                }

                return buf;
            }
        }
    }

    public static String createIdStracking(Bean bean, bm imprint) throws ci {
        Map<String, bi> a = new HashMap<>();
        a.put("android_id", new bi(bean.getAndroid_id(), System.currentTimeMillis(), 1));//identity ts version  注意里面m值得含义与伪造
        a.put("utdid", new bi(bean.getUtdid(), System.currentTimeMillis(), 1));//identity ts version  注意里面m值得含义与伪造
        a.put("mac", new bi(bean.getMac(), System.currentTimeMillis(), 1));//identity ts version  注意里面m值得含义与伪造
        a.put("serial", new bi(bean.getSerial(), System.currentTimeMillis(), 1));//identity ts version  注意里面m值得含义与伪造
        a.put("idfa", new bi(bean.getIdfa(), System.currentTimeMillis(), 1));//identity ts version  注意里面m值得含义与伪造
        a.put("imei", new bi(bean.getImei(), System.currentTimeMillis(), 1));//identity ts version  注意里面m值得含义与伪造
        a.put("idmd5", new bi(bean.getIdmd5(), System.currentTimeMillis(), 1));//identity ts version  注意里面m值得含义与伪造
        bj result = new bj(a);//a
        if (null != imprint) {
            byte[] var = (new cl()).a(result);
            return new sun.misc.BASE64Encoder().encode(var);
        } else {
            List<bh> b = new ArrayList<>();
            b.add(new bh("android_id", bean.getAndroid_id(), System.currentTimeMillis()));//domian new_id ts n有点上面m的意思
            b.add(new bh("mac", bean.getMac(), System.currentTimeMillis()));//domian new_id ts n有点上面m的意思
            b.add(new bh("idfa", bean.getIdfa(), System.currentTimeMillis()));//domian new_id ts n有点上面m的意思
            b.add(new bh("idmd5", bean.getIdmd5(), System.currentTimeMillis()));//domian new_id ts n有点上面m的意思
            b.add(new bh("utdid", bean.getUtdid(), System.currentTimeMillis()));//domian new_id ts n有点上面m的意思
            b.add(new bh("serial", bean.getSerial(), System.currentTimeMillis()));//domian new_id ts n有点上面m的意思
            b.add(new bh("imei", bean.getImei(), System.currentTimeMillis()));//domian new_id ts n有点上面m的意思
            result.a(b);
            byte[] var = (new cl()).a(result);
            return new sun.misc.BASE64Encoder().encode(var);
        }
    }

    public static String createImprint(bm imprint) throws ci {
        if (null != imprint) {
            LOG.info("\ninvoke----------imprint " + imprint);
            byte[] var = (new cl().a(imprint.c));
            return new BASE64Encoder().encode(var);
        } else {
            return null;
        }
    }
}
