package com.google.mlkit.vision.barcode.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxa;
import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzi extends LazyInstanceMap {
    private final MlKitContext zza;

    public zzi(MlKitContext mlKitContext) {
        this.zza = mlKitContext;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        zzm zzm;
        BarcodeScannerOptions barcodeScannerOptions = (BarcodeScannerOptions) obj;
        Context applicationContext = this.zza.getApplicationContext();
        zzwp zzb = zzxa.zzb(zzb.zzd());
        if (zzo.zzd(applicationContext) || GoogleApiAvailabilityLight.getInstance().getApkVersion(applicationContext) >= 204500000) {
            zzm = new zzo(applicationContext, barcodeScannerOptions, zzb);
        } else {
            zzm = new zzq(applicationContext, barcodeScannerOptions, zzb);
        }
        return new zzl(this.zza, barcodeScannerOptions, zzm, zzb);
    }
}
