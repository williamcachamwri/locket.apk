package com.google.ads.interactivemedia.pal;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzaa implements OnFailureListener {
    public final /* synthetic */ NonceLoader zza;

    public /* synthetic */ zzaa(NonceLoader nonceLoader) {
        this.zza = nonceLoader;
    }

    public final void onFailure(Exception exc) {
        this.zza.zzc(exc);
    }
}
