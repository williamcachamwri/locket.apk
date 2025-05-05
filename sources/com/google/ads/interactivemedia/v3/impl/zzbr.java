package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import com.google.ads.interactivemedia.v3.impl.data.zzbn;
import com.google.ads.interactivemedia.v3.impl.data.zzbx;
import com.google.ads.interactivemedia.v3.impl.data.zzby;
import com.google.ads.interactivemedia.v3.impl.data.zzbz;
import com.google.ads.interactivemedia.v3.internal.zznf;
import com.google.ads.interactivemedia.v3.internal.zzng;
import com.google.ads.interactivemedia.v3.internal.zznh;
import com.google.ads.interactivemedia.v3.internal.zznm;
import com.google.ads.interactivemedia.v3.internal.zzpo;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzbr implements zzbq {
    private final zznf zza;
    private final zzbn zzb;

    zzbr(Context context, zzbn zzbn) {
        this.zza = new zznm(context);
        this.zzb = zzbn;
    }

    public final zzbz zza(zzby zzby) {
        Task task;
        try {
            zzbx requestType = zzby.requestType();
            zzbx zzbx = zzbx.GET;
            int i = requestType == zzbx ? 0 : 1;
            String url = zzby.url();
            String content = zzby.content();
            if (url != null) {
                if (requestType == zzbx || content != null) {
                    zznf zznf = this.zza;
                    if (this.zzb.isLimitedAdTracking()) {
                        task = Tasks.forException(new zzng(8));
                    } else {
                        task = ((zznm) zznf).doRead(TaskApiCall.builder().setFeatures(zzpo.zzb).setAutoResolveMissingFeatures(false).run(new zznh((zznm) zznf, url, i, content)).build());
                    }
                    return zzbz.forResponse(zzby.id(), (String) Tasks.await(task, (long) (zzby.connectionTimeoutMs() + zzby.readTimeoutMs()), TimeUnit.MILLISECONDS));
                }
            }
            return zzbz.forError(zzby.id(), 100);
        } catch (InterruptedException | TimeoutException unused) {
            return zzbz.forError(zzby.id(), 101);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof zzng) {
                return zzbz.forError(zzby.id(), ((zzng) cause).zza());
            } else if (cause instanceof ApiException) {
                return zzbz.forError(zzby.id(), 102);
            } else {
                return zzbz.forError(zzby.id(), 100);
            }
        }
    }
}
