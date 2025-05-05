package com.google.android.tv.ads;

import android.content.Context;
import com.google.android.gms.internal.atv_ads_framework.zzaa;
import com.google.android.gms.internal.atv_ads_framework.zzad;
import com.google.android.gms.internal.atv_ads_framework.zzf;
import com.google.android.gms.internal.atv_ads_framework.zzx;
import com.google.android.gms.internal.atv_ads_framework.zzz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final /* synthetic */ class zzg implements Runnable {
    public final /* synthetic */ TaskCompletionSource zza;
    public final /* synthetic */ Context zzb;

    public /* synthetic */ zzg(TaskCompletionSource taskCompletionSource, Context context) {
        this.zza = taskCompletionSource;
        this.zzb = context;
    }

    public final void run() {
        TaskCompletionSource taskCompletionSource = this.zza;
        Context context = this.zzb;
        int i = SignalCollector.zza;
        try {
            taskCompletionSource.setResult(zzad.zzc(context));
        } catch (RuntimeException e) {
            zzf zza2 = zzf.zza(context);
            zzz zza3 = zzaa.zza();
            zza3.zzb(zzx.SIGNAL_COLLECTION_ERROR_RUNTIME_EXCEPTION);
            zza2.zzc((zzaa) zza3.zzi());
            taskCompletionSource.setException(e);
        }
    }
}
