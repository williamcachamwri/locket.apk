package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzre extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzre zzb;
    private static volatile zzos zzd;
    private int zze;
    private int zzf = 0;
    private Object zzg;
    /* access modifiers changed from: private */
    public int zzh;
    private int zzi;
    private String zzj = "";
    private String zzk = "";
    /* access modifiers changed from: private */
    public String zzl = "";
    private String zzm = "";
    /* access modifiers changed from: private */
    public long zzn;
    private zzmn zzo;
    /* access modifiers changed from: private */
    public int zzp;
    private zzqs zzq;
    private zzrq zzr;
    /* access modifiers changed from: private */
    public String zzs = "";
    private zzpl zzt;
    private zzmn zzu;
    private zznk zzv = zzy();
    private int zzw;

    static {
        zzre zzre = new zzre();
        zzb = zzre;
        zznf.zzI(zzre.class, zzre);
    }

    private zzre() {
    }

    static /* synthetic */ void zzO(zzre zzre, int i) {
        zznk zznk = zzre.zzv;
        if (!zznk.zzc()) {
            zzre.zzv = zznf.zzz(zznk);
        }
        zzre.zzv.zzh(0);
    }

    static /* synthetic */ void zzP(zzre zzre, String str) {
        str.getClass();
        zzre.zzj = str;
    }

    static /* synthetic */ void zzR(zzre zzre, zzqs zzqs) {
        zzre.zzq = zzqs;
        zzre.zze |= 2;
    }

    static /* synthetic */ void zzS(zzre zzre, String str) {
        str.getClass();
        zzre.zzk = str;
    }

    static /* synthetic */ void zzT(zzre zzre, zzrq zzrq) {
        zzrq.getClass();
        zzre.zzr = zzrq;
        zzre.zze |= 4;
    }

    static /* synthetic */ void zzU(zzre zzre, int i) {
        zzre.zze |= 32;
        zzre.zzw = i;
    }

    static /* synthetic */ void zzab(zzre zzre, int i) {
        if (i != 1) {
            zzre.zzi = i - 2;
            return;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public static zzrc zzi() {
        return (zzrc) zzb.zzq();
    }

    public static zzre zzk() {
        return zzb;
    }

    public static zzre zzl(byte[] bArr) throws zznp {
        return (zzre) zznf.zzx(zzb, bArr);
    }

    public final String zzM() {
        return this.zzk;
    }

    public final String zzN() {
        return this.zzl;
    }

    public final boolean zzX() {
        return (this.zze & 2) != 0;
    }

    public final int zzY() {
        int i;
        switch (this.zzh) {
            case 0:
                i = 2;
                break;
            case 1:
                i = 3;
                break;
            case 2:
                i = 4;
                break;
            case 3:
                i = 5;
                break;
            case 4:
                i = 6;
                break;
            case 5:
                i = 7;
                break;
            case 6:
                i = 8;
                break;
            case 7:
                i = 9;
                break;
            case 8:
                i = 10;
                break;
            case 9:
                i = 11;
                break;
            case 10:
                i = 12;
                break;
            case 11:
                i = 13;
                break;
            case 12:
                i = 14;
                break;
            case 13:
                i = 15;
                break;
            case 14:
                i = 16;
                break;
            case 15:
                i = 17;
                break;
            case 16:
                i = 18;
                break;
            case 17:
                i = 19;
                break;
            case 18:
                i = 20;
                break;
            case 19:
                i = 21;
                break;
            case 20:
                i = 22;
                break;
            case 21:
                i = 23;
                break;
            case 22:
                i = 24;
                break;
            case 23:
                i = 25;
                break;
            case 24:
                i = 26;
                break;
            case 25:
                i = 27;
                break;
            case 26:
                i = 28;
                break;
            case 27:
                i = 29;
                break;
            case 28:
                i = 30;
                break;
            case 29:
                i = 31;
                break;
            case 30:
                i = 32;
                break;
            case 31:
                i = 33;
                break;
            case 32:
                i = 34;
                break;
            case 33:
                i = 35;
                break;
            case 34:
                i = 36;
                break;
            case 35:
                i = 37;
                break;
            case 36:
                i = 38;
                break;
            case 37:
                i = 39;
                break;
            case 38:
                i = 40;
                break;
            case 39:
                i = 41;
                break;
            case 40:
                i = 42;
                break;
            default:
                i = 0;
                break;
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    public final int zzZ() {
        int i = this.zzp;
        int i2 = 2;
        if (i != 0) {
            i2 = i != 1 ? i != 2 ? 0 : 4 : 3;
        }
        if (i2 == 0) {
            return 1;
        }
        return i2;
    }

    @Deprecated
    public final long zzf() {
        return this.zzn;
    }

    public final zzqs zzg() {
        zzqs zzqs = this.zzq;
        return zzqs == null ? zzqs.zzj() : zzqs;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u0011\u0001\u0001\u0001\u0013\u0011\u0000\u0001\u0000\u0001\f\u0002Ȉ\u0003\u0003\u0004\f\u0005ဉ\u0001\u0006ဉ\u0002\u0007Ȉ\bȈ\tȈ\nဉ\u0000\u000bဉ\u0003\rဉ\u0004\u000eȈ\u000f<\u0000\u0011'\u0012င\u0005\u0013\f", new Object[]{"zzg", "zzf", "zze", "zzh", "zzk", "zzn", "zzp", "zzq", "zzr", "zzs", "zzl", "zzm", "zzo", "zzt", "zzu", "zzj", zzqi.class, "zzv", "zzw", "zzi"});
        } else if (i2 == 3) {
            return new zzre();
        } else {
            if (i2 == 4) {
                return new zzrc((zzrd) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzre.class) {
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
