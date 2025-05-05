package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.internal.mlkit_vision_barcode.zzra;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrb;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrd;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwe;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwo;
import com.google.android.gms.internal.mlkit_vision_barcode.zzws;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final /* synthetic */ class zza implements zzwo {
    public final /* synthetic */ zzrb zza;

    public /* synthetic */ zza(zzrb zzrb) {
        this.zza = zzrb;
    }

    public final zzwe zza() {
        zzra zzra;
        zzrd zzrd = new zzrd();
        if (zzb.zzf()) {
            zzra = zzra.TYPE_THICK;
        } else {
            zzra = zzra.TYPE_THIN;
        }
        zzrb zzrb = this.zza;
        zzrd.zze(zzra);
        zzrs zzrs = new zzrs();
        zzrs.zzb(zzrb);
        zzrd.zzh(zzrs.zzc());
        return zzws.zzf(zzrd);
    }
}
