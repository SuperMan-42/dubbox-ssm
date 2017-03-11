package com.rrcp.encrypt;

/**
 * Created by Hpw on 2017/3/3.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

public class Deflate {

    public static byte[] compress(byte[] content) throws IOException {
        if(content != null && content.length > 0) {
            Deflater deflater = new Deflater();
            deflater.setInput(content);
            deflater.finish();
            byte[] buf = new byte[8192];
            ByteArrayOutputStream byteArrayOutputStream = null;

            try {
                byteArrayOutputStream = new ByteArrayOutputStream();

                while(true) {
                    if(deflater.finished()) {
                        deflater.end();
                        break;
                    }

                    int count = deflater.deflate(buf);
                    byteArrayOutputStream.write(buf, 0, count);
                }
            } finally {
                if(byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            }

            return byteArrayOutputStream.toByteArray();
        } else {
            return null;
        }
    }
}

