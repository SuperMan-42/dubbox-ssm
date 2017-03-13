package com.rrcp.encrypt;

import java.util.Random;

/**
 * Created by Hpw on 2017/3/3.
 */

public class Utils {
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
}
