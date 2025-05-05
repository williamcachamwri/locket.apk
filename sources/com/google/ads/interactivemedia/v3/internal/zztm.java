package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zztm extends zzto {
    zztm(zzuu zzuu, zzgn zzgn) {
        super(zzuu, zzgn);
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zzs(Object obj, Object obj2) throws Exception {
        zzuu zzuu;
        zzgn zzgn = (zzgn) obj;
        zzai zzai = zzgn.zza;
        ExecutorService executorService = zzgn.zzb;
        Context context = zzgn.zzc;
        String str = (String) obj2;
        if (!zzqm.zzc(str) || zzai.zzf()) {
            zzuu = zzuk.zzb(str);
        } else {
            zzuu = zzvb.zza(executorService).zza(new zzgo(context));
        }
        if (zzuu != null) {
            return zzuu;
        }
        throw new NullPointerException(zzqm.zzb("AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", zzgn));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzt(Object obj) {
        zzq((zzuu) obj);
    }
}
