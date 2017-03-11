package com.rrcp.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by Hpw on 2017/3/3.
 */

public class Encrypt {
    private static final byte[] iv = new byte[]{10, 1, 11, 5, 4, 15, 7, 9, 23, 3, 1, 6, 8, 12, 13, 91};
    public static byte[] AESEncrypt(byte[] content, byte[] signature) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");//"算法/模式/补码方式"
        SecretKeySpec secretKeySpec = new SecretKeySpec(signature, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);//需要一个向量iv，可增加加密算法的强度
        cipher.init(2, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(content);
    }

    public static byte[] MD5Encrypt(byte[] content) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(content);
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String MD5Encrypt(String string) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(string.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();

            for(int i = 0; i < digest.length; ++i) {
                int temp = 255 & digest[i];
                stringBuffer.append(Integer.toHexString(temp));
            }

            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException var6) {
            return "";
        }
    }

    public static String ByteToString(byte[] bytes) {
        if(bytes == null) {
            return null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();

            for(int i = 0; i < bytes.length; ++i) {
                stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(bytes[i])}));
            }

            return stringBuffer.toString().toLowerCase(Locale.US);
        }
    }
}
