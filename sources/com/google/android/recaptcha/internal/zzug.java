package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzug extends zznf implements zzol {
    /* access modifiers changed from: private */
    public static final zzug zzb;
    private static volatile zzos zzd;
    private int zze = 0;
    private Object zzf;

    static {
        zzug zzug = new zzug();
        zzb = zzug;
        zznf.zzI(zzug.class, zzug);
    }

    private zzug() {
    }

    public final zzlg zzM() {
        if (this.zze == 3) {
            return (zzlg) this.zzf;
        }
        return zzlg.zzb;
    }

    public final String zzO() {
        return this.zze == 4 ? (String) this.zzf : "";
    }

    public final String zzP() {
        return this.zze == 12 ? (String) this.zzf : "";
    }

    public final boolean zzQ() {
        if (this.zze == 2) {
            return ((Boolean) this.zzf).booleanValue();
        }
        return false;
    }

    public final boolean zzR() {
        return this.zze == 1;
    }

    public final int zzS() {
        switch (this.zze) {
            case 0:
                return 16;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            case 9:
                return 9;
            case 10:
                return 10;
            case 11:
                return 11;
            case 12:
                return 12;
            case 13:
                return 13;
            case 14:
                return 14;
            case 15:
                return 15;
            default:
                return 0;
        }
    }

    public final double zzf() {
        if (this.zze == 11) {
            return ((Double) this.zzf).doubleValue();
        }
        return 0.0d;
    }

    public final float zzg() {
        if (this.zze == 10) {
            return ((Float) this.zzf).floatValue();
        }
        return 0.0f;
    }

    public final int zzi() {
        if (this.zze == 1) {
            return ((Integer) this.zzf).intValue();
        }
        return 0;
    }

    public final int zzj() {
        if (this.zze == 5) {
            return ((Integer) this.zzf).intValue();
        }
        return 0;
    }

    public final int zzk() {
        if (this.zze == 6) {
            return ((Integer) this.zzf).intValue();
        }
        return 0;
    }

    public final long zzl() {
        if (this.zze == 8) {
            return ((Long) this.zzf).longValue();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzF(zzb, "\u0000\u000f\u0001\u0000\u0001\u000f\u000f\u0000\u0000\u0000\u0001>\u0000\u0002:\u0000\u0003=\u0000\u0004Ȼ\u0000\u0005B\u0000\u0006B\u0000\u0007>\u0000\bC\u0000\t6\u0000\n4\u0000\u000b3\u0000\fȻ\u0000\r:\u0000\u000e?\u0000\u000f?\u0000", new Object[]{"zzf", "zze"});
        } else if (i2 == 3) {
            return new zzug();
        } else {
            if (i2 == 4) {
                return new zzuf((zzui) null);
            }
            if (i2 == 5) {
                return zzb;
            }
            if (i2 != 6) {
                return null;
            }
            zzos zzos = zzd;
            if (zzos == null) {
                synchronized (zzug.class) {
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
