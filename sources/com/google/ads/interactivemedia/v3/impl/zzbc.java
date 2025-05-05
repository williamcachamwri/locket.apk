package com.google.ads.interactivemedia.v3.impl;

import android.webkit.ValueCallback;
import com.google.ads.interactivemedia.v3.internal.zzqf;
import com.google.ads.interactivemedia.v3.internal.zzvd;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzbc implements ValueCallback {
    public final /* synthetic */ zzvd zza;

    public /* synthetic */ zzbc(zzvd zzvd) {
        this.zza = zzvd;
    }

    public final void onReceiveValue(Object obj) {
        Void voidR = (Void) obj;
        this.zza.zzc(zzqf.zzf());
    }
}
