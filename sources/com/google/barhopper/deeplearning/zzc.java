package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzei;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzc extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzc zza;
    private int zzd;
    private zzei zze = zzL();
    private zzei zzf = zzL();
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    static {
        zzc zzc = new zzc();
        zza = zzc;
        zzed.zzU(zzc.class, zzc);
    }

    private zzc() {
    }

    public static zzb zza() {
        return (zzb) zza.zzF();
    }

    static /* synthetic */ void zzc(zzc zzc, int i) {
        zzc.zzd |= 2;
        zzc.zzh = i;
    }

    static /* synthetic */ void zzd(zzc zzc, float f) {
        zzei zzei = zzc.zze;
        if (!zzei.zzc()) {
            zzc.zze = zzed.zzM(zzei);
        }
        zzc.zze.zzg(f);
    }

    static /* synthetic */ void zze(zzc zzc, float f) {
        zzei zzei = zzc.zzf;
        if (!zzei.zzc()) {
            zzc.zzf = zzed.zzM(zzei);
        }
        zzc.zzf.zzg(f);
    }

    static /* synthetic */ void zzf(zzc zzc, int i) {
        zzc.zzd |= 1;
        zzc.zzg = i;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001\u0013\u0002\u0013\u0003ဋ\u0000\u0004ဋ\u0001\u0005ဋ\u0002\u0006ဋ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        } else if (i2 == 3) {
            return new zzc();
        } else {
            if (i2 == 4) {
                return new zzb((zza) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
