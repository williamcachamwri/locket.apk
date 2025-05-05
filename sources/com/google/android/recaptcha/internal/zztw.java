package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zztw extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zztw zzb;
    private static volatile zzos zzd;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zztw zztw = new zztw();
        zzb = zztw;
        zznf.zzI(zztw.class, zztw);
    }

    private zztw() {
    }

    public static zztw zzg(InputStream inputStream) throws IOException {
        return (zztw) zznf.zzw(zzb, inputStream);
    }

    public final zztx zzi() {
        zztx zzb2 = zztx.zzb(this.zzg);
        return zzb2 == null ? zztx.UNRECOGNIZED : zzb2;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001", new Object[]{"zze", "zzf", "zzg"});
        } else if (i2 == 3) {
            return new zztw();
        } else {
            if (i2 == 4) {
                return new zztv((zzui) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zztw.class) {
                    zzos = zzd;
                    if (zzos == null) {
                        zzos = new zzna(zzb);
                        zzd = zzos;
                    }
                }
            }
            return zzos;
        }
    }
}
