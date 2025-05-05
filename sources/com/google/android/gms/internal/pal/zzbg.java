package com.google.android.gms.internal.pal;

import android.os.Handler;
import com.google.ads.interactivemedia.pal.NonceLoaderException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzbg {
    private final ExecutorService zza;
    private final zzagc zzb;
    private final Handler zzc;
    private Task zzd = Tasks.forResult(zzil.zze());

    zzbg(Handler handler, ExecutorService executorService, zzagc zzagc) {
        this.zza = executorService;
        this.zzc = handler;
        this.zzb = zzagc;
    }

    /* access modifiers changed from: private */
    public final void zzf() {
        this.zzc.removeCallbacksAndMessages((Object) null);
        this.zzc.postDelayed(new zzbe(this), (this.zzb.zzd() / 1000) * 1000);
        this.zzd = Tasks.call(this.zza, new zzbf(this));
    }

    /* access modifiers changed from: package-private */
    public abstract zzil zza() throws NonceLoaderException;

    public final Task zzb() {
        if (this.zzd.isComplete() && !this.zzd.isSuccessful()) {
            zzf();
        }
        return this.zzd;
    }

    public final void zzd() {
        zzf();
    }

    public final void zze() {
        this.zzc.removeCallbacksAndMessages((Object) null);
    }
}
