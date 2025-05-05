package com.google.mlkit.vision.barcode.internal;

import com.google.android.gms.internal.mlkit_vision_barcode.zzxa;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzg {
    private final zzi zza;
    private final ExecutorSelector zzb;
    private final MlKitContext zzc;

    zzg(zzi zzi, ExecutorSelector executorSelector, MlKitContext mlKitContext) {
        this.zza = zzi;
        this.zzb = executorSelector;
        this.zzc = mlKitContext;
    }

    public final zzh zza() {
        return zzb(zzh.zzd);
    }

    public final zzh zzb(BarcodeScannerOptions barcodeScannerOptions) {
        return new zzh(barcodeScannerOptions, (zzl) this.zza.get(barcodeScannerOptions), this.zzb.getExecutorToUse(barcodeScannerOptions.zzc()), zzxa.zzb(zzb.zzd()), this.zzc);
    }
}
