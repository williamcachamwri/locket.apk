package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Method;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzyi extends zzyl {
    final /* synthetic */ Method zza;
    final /* synthetic */ int zzb;

    zzyi(Method method, int i) {
        this.zza = method;
        this.zzb = i;
    }

    public final Object zza(Class cls) throws Exception {
        zzyl.zzb(cls);
        return this.zza.invoke((Object) null, new Object[]{cls, Integer.valueOf(this.zzb)});
    }
}
