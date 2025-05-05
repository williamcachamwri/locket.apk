package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.impl.data.zzbo;
import java.util.concurrent.Callable;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzfa implements Callable {
    public final /* synthetic */ zzfc zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzbo zzc;

    public /* synthetic */ zzfa(zzfc zzfc, String str, zzbo zzbo) {
        this.zza = zzfc;
        this.zzb = str;
        this.zzc = zzbo;
    }

    public final Object call() {
        return this.zza.zza(this.zzb, this.zzc);
    }
}
