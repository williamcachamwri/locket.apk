package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcd;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
import java.util.List;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public final class zzr extends zzed implements zzfp {
    /* access modifiers changed from: private */
    public static final zzr zza;
    private int zzd;
    private zzcd zze;
    private String zzf = "";
    private String zzg = "";
    private zzel zzh = zzO();
    private zzel zzi = zzO();
    private zzel zzj = zzed.zzO();
    private zzel zzk = zzO();
    private String zzl = "";
    private byte zzm = 2;

    static {
        zzr zzr = new zzr();
        zza = zzr;
        zzed.zzU(zzr.class, zzr);
    }

    private zzr() {
    }

    public static zzr zzc() {
        return zza;
    }

    public final zzcd zza() {
        zzcd zzcd = this.zze;
        return zzcd == null ? zzcd.zzb() : zzcd;
    }

    public final String zzd() {
        return this.zzf;
    }

    public final String zze() {
        return this.zzg;
    }

    public final List zzf() {
        return this.zzk;
    }

    public final List zzh() {
        return this.zzi;
    }

    public final List zzi() {
        return this.zzh;
    }

    public final List zzj() {
        return this.zzj;
    }

    /* access modifiers changed from: protected */
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zzm);
        }
        if (i2 == 2) {
            return zzR(zza, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0004\u0001\u0001ဉ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004\u001b\u0005\u001b\u0006\u001a\u0007Л\bဈ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzci.class, "zzi", zzy.class, "zzj", "zzk", zzcb.class, "zzl"});
        } else if (i2 == 3) {
            return new zzr();
        } else {
            if (i2 == 4) {
                return new zzq((zza) null);
            }
            if (i2 == 5) {
                return zza;
            }
            this.zzm = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
