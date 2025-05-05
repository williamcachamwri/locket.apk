package com.google.android.gms.internal.pal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.ads.interactivemedia.pal.zzx;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzbh extends zzbg {
    private final zzgx zza;
    private final zzx zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbh(Handler handler, ExecutorService executorService, Context context, zzx zzx) {
        super(handler, executorService, zzagc.zzb(2));
        zzhc zzhc = new zzhc(context);
        this.zza = zzhc;
        this.zzb = zzx;
    }

    /* access modifiers changed from: package-private */
    public final zzil zza() {
        Bundle bundle = new Bundle();
        try {
            zzgx zzgx = this.zza;
            return zzil.zzf((String) Tasks.await(((zzhc) zzgx).doRead(TaskApiCall.builder().setAutoResolveMissingFeatures(false).setFeatures(zzie.zza).run(new zzgz((zzhc) zzgx, bundle)).build()), 5, TimeUnit.SECONDS));
        } catch (InterruptedException | TimeoutException unused) {
            this.zzb.zza(2);
            return zzil.zze();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof zzgy) {
                int zza2 = ((zzgy) cause).zza();
                Log.d("NonceGenerator", "SignalSdk Error code: " + zza2);
                this.zzb.zza(3);
            }
            return zzil.zze();
        }
    }
}
