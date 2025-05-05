package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzei;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzht;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzi extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzi zza;
    private int zzd;
    private String zze = "";
    private zzdb zzf = zzdb.zzb;
    private int zzg = 10;
    private float zzh = 0.5f;
    private float zzi = 0.05f;
    private zzf zzj;
    private zzei zzk = zzL();
    private int zzl = 1;
    private zzht zzm;
    private int zzn = 320;
    private int zzo = 4;
    private int zzp = 2;

    static {
        zzi zzi2 = new zzi();
        zza = zzi2;
        zzed.zzU(zzi.class, zzi2);
    }

    private zzi() {
    }

    public static zzh zza() {
        return (zzh) zza.zzF();
    }

    static /* synthetic */ void zzc(zzi zzi2, zzf zzf2) {
        zzf2.getClass();
        zzi2.zzj = zzf2;
        zzi2.zzd |= 32;
    }

    static /* synthetic */ void zzd(zzi zzi2, zzdb zzdb) {
        zzdb.getClass();
        zzi2.zzd |= 2;
        zzi2.zzf = zzdb;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0001\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဋ\u0002\u0004ခ\u0003\u0005ခ\u0004\u0006ဉ\u0005\u0007\u0013\bင\u0006\tဉ\u0007\nင\b\u000bင\t\fင\n", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
        } else if (i2 == 3) {
            return new zzi();
        } else {
            if (i2 == 4) {
                return new zzh((zzg) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zza;
        }
    }
}
