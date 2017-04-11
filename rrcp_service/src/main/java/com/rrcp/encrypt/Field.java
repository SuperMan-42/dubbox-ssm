package com.rrcp.encrypt;

import u.aly.*;

/**
 * Created by Hpw on 2017/3/3.  af
 */

//用来生成umid
public class Field {
    public static Field field;
    private Track track = new Track();
    private bk e = null;

    public static synchronized Field getInstance() {
        if (field == null) {
            field = new Field();
            field.c(null);
        }

        return field;
    }

    public synchronized bk a() {
        return this.e;
    }

    Field() {

    }

    public void c(byte[] data) {
        if (null != data) {
            byte[] buf = null;

            try {
                bm var = new bm();
                cf var3 = new cf(new u.aly.cu.a());
                var3.a(var, data);
                buf = (new cl()).a(var.c);
            } catch (Exception var10) {
                var10.printStackTrace();
            }

            if (buf != null) {
                try {
                    bk var4 = new bk();
                    (new cf()).a(var4, buf);
                    this.e = var4;
                    this.track.a(var4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Track getTrack() {
        return this.track;
    }

    public static class Track {
        private int a = -1;
        private int b = -1;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private String f = null;
        private int g = -1;
        private String umid = null;
        private int i = -1;
        private int j = -1;
        private String k = null;
        private String l = null;
        private String m = null;
        private String n = null;
        private String o = null;

        Track() {
        }

        Track(bk var1) {
            this.a(var1);
        }

        public void a(bk var1) {
            if (var1 != null) {
                this.a = this.a(var1, "defcon");
                this.b = this.a(var1, "latent");
                this.c = this.a(var1, "codex");
                this.d = this.a(var1, "report_policy");
                this.e = this.a(var1, "report_interval");
                this.f = this.b(var1, "client_test");
                this.g = this.a(var1, "test_report_interval");
                this.umid = this.b(var1, "umid");
                this.i = this.a(var1, "integrated_test");
                this.j = this.a(var1, "latent_hours");
                this.k = this.b(var1, "country");
                this.l = this.b(var1, "domain_p");
                this.m = this.b(var1, "domain_s");
                this.n = this.b(var1, "initial_view_time");
                this.o = this.b(var1, "track_list");
            }
        }

        public String a(String var1) {
            return this.n != null ? this.n : var1;
        }

        public String b(String var1) {
            return this.o != null ? this.o : var1;
        }

        public String c(String var1) {
            return this.m != null ? this.m : var1;
        }

        public String d(String var1) {
            return this.l != null ? this.l : var1;
        }

        public String e(String var1) {
            return this.k != null ? this.k : var1;
        }

        public int a(int var1) {
            return this.a == -1 ? var1 : (this.a <= 3 && this.a >= 0 ? this.a : var1);
        }

        public int b(int var1) {
            return this.b == -1 ? var1 : (this.b >= 0 && this.b <= 1800 ? this.b * 1000 : var1);
        }

        public int c(int var1) {
            return this.c != 0 && this.c != 1 && this.c != -1 ? var1 : this.c;
        }

        public int[] a(int var1, int var2) {
            if (this.d != -1 && bw.a(this.d)) {
                if (this.e == -1 || this.e < 90 || this.e > 86400) {
                    this.e = 90;
                }

                return new int[]{this.d, this.e * 1000};
            } else {
                return new int[]{var1, var2};
            }
        }

//        public String f(String var1) {
//            return this.f != null && bd.a(this.f) ? this.f : var1;
//        }

        public int d(int var1) {
            return this.g != -1 && this.g >= 90 && this.g <= 86400 ? this.g * 1000 : var1;
        }

        public boolean a() {
            return this.g != -1;
        }

        public String getUmid(String string) {
            return this.umid;
        }

        public boolean b() {
            return this.i == 1;
        }

        public long a(long var1) {
            return this.j == -1 ? var1 : (this.j < 48 ? var1 : 3600000L * (long) this.j);
        }

        private int a(bk var1, String var2) {
            try {
                if (var1 == null || !var1.f()) {
                    return -1;
                }

                bl var3 = (bl) var1.d().get(var2);
                if (var3 == null || var3.c() == null) {
                    return -1;
                }

                try {
                    return Integer.parseInt(var3.c().trim());
                } catch (Exception var5) {
                    ;
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            return -1;
        }

        private String b(bk var1, String var2) {
            String var3 = null;

            try {
                if (var1 == null || !var1.f()) {
                    return null;
                }

                bl var4 = (bl) var1.d().get(var2);
                if (var4 == null || var4.c() == null) {
                    return null;
                }

                var3 = var4.c();
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            return var3;
        }
    }
}
