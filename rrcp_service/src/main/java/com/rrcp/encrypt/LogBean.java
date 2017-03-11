package com.rrcp.encrypt;

import u.aly.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * Created by Hpw on 2017/3/7.
 */

public class LogBean implements Serializable, Cloneable, cc<LogBean, LogBean.hpw> {
    private static final long l = 420342210744516016L;
    private static final dg m = new dg("UMEnvelope");
    private static final cw n = new cw("version", (byte)11, (short)1);
    private static final cw o = new cw("address", (byte)11, (short)2);
    private static final cw p = new cw("signature", (byte)11, (short)3);
    private static final cw q = new cw("serial_num", (byte)8, (short)4);
    private static final cw r = new cw("ts_secs", (byte)8, (short)5);
    private static final cw s = new cw("length", (byte)8, (short)6);
    private static final cw t = new cw("entity", (byte)11, (short)7);
    private static final cw u = new cw("guid", (byte)11, (short)8);
    private static final cw v = new cw("checksum", (byte)11, (short)9);
    private static final cw w = new cw("codex", (byte)8, (short)10);
    private static final Map<Class<? extends dj>, dk> x = new HashMap();
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;
    private static final int y = 0;
    private static final int z = 1;
    private static final int A = 2;
    private static final int B = 3;
    private byte C;
    private hpw[] D;
    public static final Map<hpw, co> k;

    public LogBean() {
        this.C = 0;
        this.D = new hpw[]{hpw.j};
    }

    public LogBean(String var1, String var2, String var3, int var4, int var5, int var6, ByteBuffer var7, String var8, String var9) {
        this();
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.d = var4;
        this.d(true);
        this.e = var5;
        this.e(true);
        this.f = var6;
        this.f(true);
        this.g = var7;
        this.h = var8;
        this.i = var9;
    }

    public LogBean(LogBean var1) {
        this.C = 0;
        this.D = new hpw[]{hpw.j};
        this.C = var1.C;
        if(var1.e()) {
            this.a = var1.a;
        }

        if(var1.h()) {
            this.b = var1.b;
        }

        if(var1.k()) {
            this.c = var1.c;
        }

        this.d = var1.d;
        this.e = var1.e;
        this.f = var1.f;
        if(var1.y()) {
            this.g = cd.d(var1.g);
        }

        if(var1.B()) {
            this.h = var1.h;
        }

        if(var1.E()) {
            this.i = var1.i;
        }

        this.j = var1.j;
    }

    public LogBean a() {
        return new LogBean(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d(false);
        this.d = 0;
        this.e(false);
        this.e = 0;
        this.f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j(false);
        this.j = 0;
    }

    public String c() {
        return this.a;
    }

    public LogBean a(String var1) {
        this.a = var1;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean var1) {
        if(!var1) {
            this.a = null;
        }

    }

    public String f() {
        return this.b;
    }

    public LogBean b(String var1) {
        this.b = var1;
        return this;
    }

    public void g() {
        this.b = null;
    }

    public boolean h() {
        return this.b != null;
    }

    public void b(boolean var1) {
        if(!var1) {
            this.b = null;
        }

    }

    public String i() {
        return this.c;
    }

    public LogBean c(String var1) {
        this.c = var1;
        return this;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
        return this.c != null;
    }

    public void c(boolean var1) {
        if(!var1) {
            this.c = null;
        }

    }

    public int l() {
        return this.d;
    }

    public LogBean a(int var1) {
        this.d = var1;
        this.d(true);
        return this;
    }

    public void m() {
        this.C = bz.b(this.C, 0);
    }

    public boolean n() {
        return bz.a(this.C, 0);
    }

    public void d(boolean var1) {
        this.C = bz.a(this.C, 0, var1);
    }

    public int o() {
        return this.e;
    }

    public LogBean c(int var1) {
        this.e = var1;
        this.e(true);
        return this;
    }

    public void q() {
        this.C = bz.b(this.C, 1);
    }

    public boolean r() {
        return bz.a(this.C, 1);
    }

    public void e(boolean var1) {
        this.C = bz.a(this.C, 1, var1);
    }

    public int s() {
        return this.f;
    }

    public LogBean d(int var1) {
        this.f = var1;
        this.f(true);
        return this;
    }

    public void t() {
        this.C = bz.b(this.C, 2);
    }

    public boolean u() {
        return bz.a(this.C, 2);
    }

    public void f(boolean var1) {
        this.C = bz.a(this.C, 2, var1);
    }

    public byte[] v() {
        this.a(cd.c(this.g));
        return this.g == null?null:this.g.array();
    }

    public ByteBuffer w() {
        return this.g;
    }

    public LogBean a(byte[] var1) {
        this.a(var1 == null?(ByteBuffer)null:ByteBuffer.wrap(var1));
        return this;
    }

    public LogBean a(ByteBuffer var1) {
        this.g = var1;
        return this;
    }

    public void x() {
        this.g = null;
    }

    public boolean y() {
        return this.g != null;
    }

    public void g(boolean var1) {
        if(!var1) {
            this.g = null;
        }

    }

    public String z() {
        return this.h;
    }

    public LogBean d(String var1) {
        this.h = var1;
        return this;
    }

    public void A() {
        this.h = null;
    }

    public boolean B() {
        return this.h != null;
    }

    public void h(boolean var1) {
        if(!var1) {
            this.h = null;
        }

    }

    public String C() {
        return this.i;
    }

    public LogBean e(String var1) {
        this.i = var1;
        return this;
    }

    public void D() {
        this.i = null;
    }

    public boolean E() {
        return this.i != null;
    }

    public void i(boolean var1) {
        if(!var1) {
            this.i = null;
        }

    }

    public int F() {
        return this.j;
    }

    public LogBean e(int var1) {
        this.j = var1;
        this.j(true);
        return this;
    }

    public void G() {
        this.C = bz.b(this.C, 3);
    }

    public boolean H() {
        return bz.a(this.C, 3);
    }

    public void j(boolean var1) {
        this.C = bz.a(this.C, 3, var1);
    }

    public hpw f(int var1) {
        return hpw.a(var1);
    }

    public void a(db var1) throws ci {
        ((dk)x.get(var1.D())).b().b(var1, this);
    }

    public void b(db var1) throws ci {
        //x.get((new cv.a()).a(new dn(new ByteArrayOutputStream())).D()).b().a((new cv.a()).a(new dn(new ByteArrayOutputStream())),this)
        //(new cv.a()).a(new dn(new ByteArrayOutputStream())).D()取出一个dl类 后应该得到一个LogBean.b 执行其a方法（但是好像原本没有执行）
        ((dk)x.get(var1.D())).b().a(var1, this);
    }

    @Override
    public hpw b(int i) {
        return null;
    }

    @Override
    public cc<LogBean, hpw> p() {
        return null;
    }

    public String toString() {
        StringBuilder var1 = new StringBuilder("UMEnvelope(");
        boolean var2 = true;
        var1.append("version:");
        if(this.a == null) {
            var1.append("null");
        } else {
            var1.append(this.a);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("address:");
        if(this.b == null) {
            var1.append("null");
        } else {
            var1.append(this.b);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("signature:");
        if(this.c == null) {
            var1.append("null");
        } else {
            var1.append(this.c);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("serial_num:");
        var1.append(this.d);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("ts_secs:");
        var1.append(this.e);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("length:");
        var1.append(this.f);
        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("entity:");
        if(this.g == null) {
            var1.append("null");
        } else {
            cd.a(this.g, var1);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("guid:");
        if(this.h == null) {
            var1.append("null");
        } else {
            var1.append(this.h);
        }

        var2 = false;
        if(!var2) {
            var1.append(", ");
        }

        var1.append("checksum:");
        if(this.i == null) {
            var1.append("null");
        } else {
            var1.append(this.i);
        }

        var2 = false;
        if(this.H()) {
            if(!var2) {
                var1.append(", ");
            }

            var1.append("codex:");
            var1.append(this.j);
            var2 = false;
        }

        var1.append(")");
        return var1.toString();
    }

    public void I() throws ci {
        if(this.a == null) {
            throw new dc("Required field \'version\' was not present! Struct: " + this.toString());
        } else if(this.b == null) {
            throw new dc("Required field \'address\' was not present! Struct: " + this.toString());
        } else if(this.c == null) {
            throw new dc("Required field \'signature\' was not present! Struct: " + this.toString());
        } else if(this.g == null) {
            throw new dc("Required field \'entity\' was not present! Struct: " + this.toString());
        } else if(this.h == null) {
            throw new dc("Required field \'guid\' was not present! Struct: " + this.toString());
        } else if(this.i == null) {
            throw new dc("Required field \'checksum\' was not present! Struct: " + this.toString());
        }
    }

    private void a(ObjectOutputStream var1) throws IOException {
        try {
            this.b((db)(new cv(new dn(var1))));
        } catch (ci var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private void a(ObjectInputStream var1) throws IOException, ClassNotFoundException {
        try {
            this.C = 0;
            this.a((db)(new cv(new dn(var1))));
        } catch (ci var3) {
            throw new IOException(var3.getMessage());
        }
    }

    static {
        x.put(dl.class, new b());
        x.put(dm.class, new d());
        EnumMap var0 = new EnumMap(hpw.class);
        var0.put(hpw.a, new co("version", (byte)1, new cp((byte)11)));
        var0.put(hpw.b, new co("address", (byte)1, new cp((byte)11)));
        var0.put(hpw.c, new co("signature", (byte)1, new cp((byte)11)));
        var0.put(hpw.d, new co("serial_num", (byte)1, new cp((byte)8)));
        var0.put(hpw.e, new co("ts_secs", (byte)1, new cp((byte)8)));
        var0.put(hpw.f, new co("length", (byte)1, new cp((byte)8)));
        var0.put(hpw.g, new co("entity", (byte)1, new cp((byte)11, true)));
        var0.put(hpw.h, new co("guid", (byte)1, new cp((byte)11)));
        var0.put(hpw.i, new co("checksum", (byte)1, new cp((byte)11)));
        var0.put(hpw.j, new co("codex", (byte)2, new cp((byte)8)));
        k = Collections.unmodifiableMap(var0);
        co.a(LogBean.class, k);
    }

    private static class c extends dm<LogBean> {
        private c() {
        }

        public void a(db var1, LogBean var2) throws ci {
            dh var3 = (dh)var1;
            var3.a(var2.a);
            var3.a(var2.b);
            var3.a(var2.c);
            var3.a(var2.d);
            var3.a(var2.e);
            var3.a(var2.f);
            var3.a(var2.g);
            var3.a(var2.h);
            var3.a(var2.i);
            BitSet var4 = new BitSet();
            if(var2.H()) {
                var4.set(0);
            }

            var3.a(var4, 1);
            if(var2.H()) {
                var3.a(var2.j);
            }

        }

        public void b(db var1, LogBean var2) throws ci {
            dh var3 = (dh)var1;
            var2.a = var3.z();
            var2.a(true);
            var2.b = var3.z();
            var2.b(true);
            var2.c = var3.z();
            var2.c(true);
            var2.d = var3.w();
            var2.d(true);
            var2.e = var3.w();
            var2.e(true);
            var2.f = var3.w();
            var2.f(true);
            var2.g = var3.A();
            var2.g(true);
            var2.h = var3.z();
            var2.h(true);
            var2.i = var3.z();
            var2.i(true);
            BitSet var4 = var3.b(1);
            if(var4.get(0)) {
                var2.j = var3.w();
                var2.j(true);
            }

        }
    }

    private static class d implements dk {
        private d() {
        }

        public LogBean.c a() {
            return new c();
        }

        @Override
        public LogBean.c b() {
            return new c();
        }
    }

    private static class a extends dl<LogBean> {
        private a() {
        }

        public void a(db var1, LogBean var2) throws ci {
            var1.j();

            while(true) {
                cw var3 = var1.l();
                if(var3.b == 0) {//.b就是size()
                    var1.k();
                    if(!var2.n()) {
                        throw new dc("Required field \'serial_num\' was not found in serialized data! Struct: " + this.toString());
                    }

                    if(!var2.r()) {
                        throw new dc("Required field \'ts_secs\' was not found in serialized data! Struct: " + this.toString());
                    }

                    if(!var2.u()) {
                        throw new dc("Required field \'length\' was not found in serialized data! Struct: " + this.toString());
                    }

                    var2.I();
                    return;
                }

                switch(var3.c) {
                    case 1:
                        if(var3.b == 11) {
                            var2.a = var1.z();
                            var2.a(true);
                        } else {
                            de.a(var1, var3.b);
                        }
                        break;
                    case 2:
                        if(var3.b == 11) {
                            var2.b = var1.z();
                            var2.b(true);
                        } else {
                            de.a(var1, var3.b);
                        }
                        break;
                    case 3:
                        if(var3.b == 11) {
                            var2.c = var1.z();
                            var2.c(true);
                        } else {
                            de.a(var1, var3.b);
                        }
                        break;
                    case 4:
                        if(var3.b == 8) {
                            var2.d = var1.w();
                            var2.d(true);
                        } else {
                            de.a(var1, var3.b);
                        }
                        break;
                    case 5:
                        if(var3.b == 8) {
                            var2.e = var1.w();
                            var2.e(true);
                        } else {
                            de.a(var1, var3.b);
                        }
                        break;
                    case 6:
                        if(var3.b == 8) {
                            var2.f = var1.w();
                            var2.f(true);
                        } else {
                            de.a(var1, var3.b);
                        }
                        break;
                    case 7:
                        if(var3.b == 11) {
                            var2.g = var1.A();
                            var2.g(true);
                        } else {
                            de.a(var1, var3.b);
                        }
                        break;
                    case 8:
                        if(var3.b == 11) {
                            var2.h = var1.z();
                            var2.h(true);
                        } else {
                            de.a(var1, var3.b);
                        }
                        break;
                    case 9:
                        if(var3.b == 11) {
                            var2.i = var1.z();
                            var2.i(true);
                        } else {
                            de.a(var1, var3.b);
                        }
                        break;
                    case 10:
                        if(var3.b == 8) {
                            var2.j = var1.w();
                            var2.j(true);
                        } else {
                            de.a(var1, var3.b);
                        }
                        break;
                    default:
                        de.a(var1, var3.b);
                }

                var1.m();
            }
        }

        public void b(db var1, LogBean var2) throws ci {
            var2.I();
            var1.a(LogBean.m);
            if(var2.a != null) {
                var1.a(LogBean.n);
                var1.a(var2.a);
                var1.c();
            }

            if(var2.b != null) {
                var1.a(LogBean.o);
                var1.a(var2.b);
                var1.c();
            }

            if(var2.c != null) {
                var1.a(LogBean.p);
                var1.a(var2.c);
                var1.c();
            }

            var1.a(LogBean.q);
            var1.a(var2.d);
            var1.c();
            var1.a(LogBean.r);
            var1.a(var2.e);
            var1.c();
            var1.a(LogBean.s);
            var1.a(var2.f);
            var1.c();
            if(var2.g != null) {
                var1.a(LogBean.t);
                var1.a(var2.g);
                var1.c();
            }

            if(var2.h != null) {
                var1.a(LogBean.u);
                var1.a(var2.h);
                var1.c();
            }

            if(var2.i != null) {
                var1.a(LogBean.v);
                var1.a(var2.i);
                var1.c();
            }

            if(var2.H()) {
                var1.a(LogBean.w);
                var1.a(var2.j);
                var1.c();
            }

            var1.d();
            var1.b();
        }
    }

    private static class b implements dk {
        private b() {
        }

        public LogBean.a a() {
            return new a();
        }

        @Override
        public LogBean.a b() {
            return new a();
        }
    }

    public static enum hpw implements cj {
        a((byte)1, "version"),
        b((byte)2, "address"),
        c((byte)3, "signature"),
        d((byte)4, "serial_num"),
        e((byte)5, "ts_secs"),
        f((byte)6, "length"),
        g((byte)7, "entity"),
        h((byte)8, "guid"),
        i((byte)9, "checksum"),
        j((byte)10, "codex");

        private static final Map<String, hpw> k = new HashMap();
        private final short l;
        private final String m;

        public static hpw a(int var0) {
            switch(var0) {
                case 1:
                    return a;
                case 2:
                    return b;
                case 3:
                    return c;
                case 4:
                    return d;
                case 5:
                    return e;
                case 6:
                    return f;
                case 7:
                    return g;
                case 8:
                    return h;
                case 9:
                    return i;
                case 10:
                    return j;
                default:
                    return null;
            }
        }

        public static hpw b(int var0) {
            hpw var1 = a(var0);
            if(var1 == null) {
                throw new IllegalArgumentException("Field " + var0 + " doesn\'t exist!");
            } else {
                return var1;
            }
        }

        public static hpw a(String var0) {
            return (hpw)k.get(var0);
        }

        private hpw(short var3, String var4) {
            this.l = var3;
            this.m = var4;
        }

        public short a() {
            return this.l;
        }

        public String b() {
            return this.m;
        }

        static {
            Iterator var0 = EnumSet.allOf(hpw.class).iterator();

            while(var0.hasNext()) {
                hpw var1 = (hpw)var0.next();
                k.put(var1.b(), var1);
            }
        }
    }
}

