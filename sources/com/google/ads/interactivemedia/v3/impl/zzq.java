package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.BaseRequest;
import com.google.ads.interactivemedia.v3.internal.zzez;
import java.util.concurrent.Callable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzq implements Callable {
    public final /* synthetic */ zzy zza;
    public final /* synthetic */ zzez zzb;
    public final /* synthetic */ BaseRequest zzc;
    public final /* synthetic */ String zzd;

    public /* synthetic */ zzq(zzy zzy, zzez zzez, BaseRequest baseRequest, String str) {
        this.zza = zzy;
        this.zzb = zzez;
        this.zzc = baseRequest;
        this.zzd = str;
    }

    public final Object call() {
        return this.zza.zzg(this.zzb, this.zzc, this.zzd);
    }
}
