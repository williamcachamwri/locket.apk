package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final /* synthetic */ class zzwq implements OnFailureListener {
    public final /* synthetic */ zzwr zza;
    public final /* synthetic */ long zzb;

    public /* synthetic */ zzwq(zzwr zzwr, long j) {
        this.zza = zzwr;
        this.zzb = j;
    }

    public final void onFailure(Exception exc) {
        this.zza.zzb(this.zzb, exc);
    }
}
