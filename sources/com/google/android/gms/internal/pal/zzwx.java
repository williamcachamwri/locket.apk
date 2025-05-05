package com.google.android.gms.internal.pal;

import java.util.List;

@Deprecated
/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwx extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzwx zzb;
    private String zze = "";
    private zzadf zzf = zzaz();

    static {
        zzwx zzwx = new zzwx();
        zzb = zzwx;
        zzacz.zzaF(zzwx.class, zzwx);
    }

    private zzwx() {
    }

    public static zzwx zzc() {
        return zzb;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzaE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zze", "zzf", zzvw.class});
        } else if (i2 == 3) {
            return new zzwx();
        } else {
            if (i2 == 4) {
                return new zzww((zzwv) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final List zzd() {
        return this.zzf;
    }
}
