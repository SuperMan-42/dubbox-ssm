package com.rrcp.encrypt;

import u.aly.*;

import java.io.ByteArrayOutputStream;

/**
 * Created by Hpw on 2017/3/3.
 */

public class Cl {
    private final ByteArrayOutputStream byteArrayOutputStream;
    private db c;//控制读写
    public byte[] a(cc var1) throws ci {
        this.byteArrayOutputStream.reset();
        var1.b(this.c);//((dk)x.get(var1.D())).b().a(var1, this);
        return this.byteArrayOutputStream.toByteArray();
    }

    public Cl() {
        this(new cv.a());
    }

    public Cl(dd var1) {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.c = var1.a(new dn(this.byteArrayOutputStream));
    }
}
