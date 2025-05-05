package com.google.mlkit.vision.common.internal;

import com.google.mlkit.vision.common.InputImage;
import java.util.concurrent.Callable;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public final /* synthetic */ class zza implements Callable {
    public final /* synthetic */ MobileVisionBase zza;
    public final /* synthetic */ InputImage zzb;

    public /* synthetic */ zza(MobileVisionBase mobileVisionBase, InputImage inputImage) {
        this.zza = mobileVisionBase;
        this.zzb = inputImage;
    }

    public final Object call() {
        return this.zza.zza(this.zzb);
    }
}
