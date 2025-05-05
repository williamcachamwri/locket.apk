package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzps;
import com.google.ads.interactivemedia.v3.internal.zzqo;

@zzps(zza = zzae.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzbw {
    static zzbw create(String str, String str2, String str3) {
        return new zzae(str, str2, str3);
    }

    public static zzbw create(Throwable th) {
        return new zzae(th.getClass().getName(), th.getMessage(), zzqo.zza(th));
    }

    public abstract String message();

    public abstract String name();

    public abstract String stackTrace();
}
