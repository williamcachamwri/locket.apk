package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzsk extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzsk zzb;
    private static volatile zzos zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private zzso zzk;
    private zzsi zzl;
    private zzsm zzm;
    private zzsa zzn;

    static {
        zzsk zzsk = new zzsk();
        zzb = zzsk;
        zznf.zzI(zzsk.class, zzsk);
    }

    private zzsk() {
    }

    static /* synthetic */ void zzN(zzsk zzsk, String str) {
        str.getClass();
        zzsk.zze |= 1;
        zzsk.zzf = str;
    }

    static /* synthetic */ void zzO(zzsk zzsk, zzsa zzsa) {
        zzsa.getClass();
        zzsk.zzn = zzsa;
        zzsk.zze |= 256;
    }

    static /* synthetic */ void zzP(zzsk zzsk, zzsi zzsi) {
        zzsi.getClass();
        zzsk.zzl = zzsi;
        zzsk.zze |= 64;
    }

    static /* synthetic */ void zzQ(zzsk zzsk, zzso zzso) {
        zzso.getClass();
        zzsk.zzk = zzso;
        zzsk.zze |= 32;
    }

    static /* synthetic */ void zzR(zzsk zzsk, zzsm zzsm) {
        zzsm.getClass();
        zzsk.zzm = zzsm;
        zzsk.zze |= 128;
    }

    public static zzsj zzf() {
        return (zzsj) zzb.zzq();
    }

    public static zzsk zzi(byte[] bArr) throws zznp {
        return (zzsk) zznf.zzx(zzb, bArr);
    }

    @Deprecated
    public final String zzM() {
        return this.zzh;
    }

    @Deprecated
    public final String zzj() {
        return this.zzi;
    }

    public final String zzk() {
        return this.zzf;
    }

    @Deprecated
    public final String zzl() {
        return this.zzg;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ለ\u0000\u0002ለ\u0001\u0003ለ\u0002\u0004ለ\u0003\u0005ለ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bဉ\u0007\tဉ\b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn"});
        } else if (i2 == 3) {
            return new zzsk();
        } else {
            if (i2 == 4) {
                return new zzsj((zzsp) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzsk.class) {
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
