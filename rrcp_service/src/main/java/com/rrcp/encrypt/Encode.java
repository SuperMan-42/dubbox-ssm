package com.rrcp.encrypt;

import u.aly.bn;
import u.aly.cl;

/**
 * Created by hpw on 2017/2/14.
 */

public class Encode {
    private final byte[] signatureByte = new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
    private String version = "1.0";
    private String address = null;
    private byte[] signature = null;
    private byte[] guid = null;
    private byte[] checksum = null;
    private int serial = 0;
    private int timestamp = 0;
    private int length = 0;
    private byte[] content = null;
    private byte[] macAndDeviceId = null;
    private boolean codex = false;

    private Encode(byte[] content, String appKey, byte[] macAndDeviceId) throws Exception {
        if (content != null && content.length != 0) {
            this.address = appKey;
            this.length = content.length;
            this.content = Deflate.compress(content);
            this.timestamp = (int) (System.currentTimeMillis() / 1000L);
            this.macAndDeviceId = macAndDeviceId;
        } else {
            throw new Exception("entity is null or empty");
        }
    }

    public void setSignature(String string) {
        this.signature = Utils.getByte(string);
    }

    public void setSerial(int var1) {
        this.serial = var1;
    }

    public void setGuid() {
        if (this.signature == null) {
            this.signature = this.getSignatureByte();//不太确定
        }

        if (this.codex) {
            byte[] buf = new byte[16];

            try {
                System.arraycopy(this.signature, 1, buf, 0, 16);
                this.content = Encrypt.AESEncrypt(this.content, buf);
            } catch (Exception e) {
                ;
            }
        }

        this.guid = this.getGuid(this.signature, this.timestamp);
        this.checksum = this.getCheckSum();
    }

    //建造者设计模式
    public static Encode builder(String appKey, byte[] content) {
        try {
            String mac = Utils.getMacAddress(null);//自己创建的
            String device_id = Utils.getDevice_id(15);//自己创建的
            String signature = "39:A5:4C:18:BC:93:6C:A3:1E:34:36:91:36:F2:6F:DA";//TODO
            int serial = 1;//TODO
            Encode encrypt = new Encode(content, appKey, (device_id + mac).getBytes());
            encrypt.setSignature(signature);
            encrypt.setSerial(serial);
            encrypt.setGuid();
            encrypt.getInstance();
            return encrypt;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getSignatureByte() {
        return this.getGuid(this.signatureByte, (int) (System.currentTimeMillis() / 1000L));
    }

    public byte[] c() {
        bn content = new bn();//Bn就是一个model，放一些内容
//        LogBean content = new LogBean();//Bn就是一个model，放一些内容
        content.a(this.version);
        content.b(this.address);
        content.c(Encrypt.ByteToString(this.signature));
        content.a(this.serial);
        content.c(this.timestamp);
        content.d(this.length);
        content.a(this.content);
        content.e(this.codex ? 1 : 0);
        content.d(Encrypt.ByteToString(this.guid));
        content.e(Encrypt.ByteToString(this.checksum));

        try {
//            return (new Cl()).a(content);//TODO
            return (new cl()).a(content);//TODO
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getGuid(byte[] signature, int timestamp) {
        byte[] macAndDeviceId = Encrypt.MD5Encrypt(this.macAndDeviceId);
        byte[] content = Encrypt.MD5Encrypt(this.content);
        int length = macAndDeviceId.length;
        byte[] buf = new byte[length * 2];

        for (int i = 0; i < length; ++i) {
            buf[i * 2] = content[i];
            buf[i * 2 + 1] = macAndDeviceId[i];
        }

        byte[] Tempsignature = signature;

        for (int i = 0; i < 2; ++i) {
            buf[i] = Tempsignature[i];
            buf[buf.length - i - 1] = Tempsignature[Tempsignature.length - i - 1];
        }

        byte[] secret = new byte[]{(byte) (timestamp & 255), (byte) (timestamp >> 8 & 255), (byte) (timestamp >> 16 & 255), (byte) (timestamp >>> 24)};

        for (int i = 0; i < buf.length; ++i) {
            buf[i] ^= secret[i % 4];
        }

        return buf;
    }

    private byte[] getCheckSum() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Encrypt.ByteToString(this.signature));
        stringBuilder.append(this.serial);
        stringBuilder.append(this.timestamp);
        stringBuilder.append(this.length);
        stringBuilder.append(Encrypt.ByteToString(this.guid));
        return Encrypt.MD5Encrypt(stringBuilder.toString().getBytes());
    }

    public void getInstance() {
        String address = this.address;
        String umid = null;//TODO
        String signature = Encrypt.ByteToString(this.signature);
        byte[] buf = new byte[16];
        System.arraycopy(this.signature, 2, buf, 0, 16);
        String checksum = Encrypt.ByteToString(Encrypt.MD5Encrypt(buf));

        //TODO
    }

    public String getSignature() {
        return Encrypt.ByteToString(this.signature);
    }

    public void setCodex(boolean codex) {
        this.codex = codex;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("version : %s\n", new Object[]{this.version}));
        stringBuilder.append(String.format("address : %s\n", new Object[]{this.address}));
        stringBuilder.append(String.format("signature : %s\n", new Object[]{Encrypt.ByteToString(this.signature)}));
        stringBuilder.append(String.format("serial : %s\n", new Object[]{Integer.valueOf(this.serial)}));
        stringBuilder.append(String.format("timestamp : %d\n", new Object[]{Integer.valueOf(this.timestamp)}));
        stringBuilder.append(String.format("length : %d\n", new Object[]{Integer.valueOf(this.length)}));
        stringBuilder.append(String.format("guid : %s\n", new Object[]{Encrypt.ByteToString(this.guid)}));
        stringBuilder.append(String.format("checksum : %s ", new Object[]{Encrypt.ByteToString(this.checksum)}));
        stringBuilder.append(String.format("codex : %d", new Object[]{Integer.valueOf(this.codex ? 1 : 0)}));
        return stringBuilder.toString();
    }
}
