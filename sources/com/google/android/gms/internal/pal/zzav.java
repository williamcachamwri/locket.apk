package com.google.android.gms.internal.pal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import com.google.ads.interactivemedia.pal.zzx;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzav extends zzbg {
    private final zzx zza;
    private final Task zzb;
    private final Context zzc;

    public zzav(Handler handler, ExecutorService executorService, Context context, Task task, zzx zzx) {
        super(handler, executorService, zzagc.zzb(2));
        this.zzc = context;
        this.zzb = task;
        this.zza = zzx;
    }

    /* access modifiers changed from: package-private */
    public final zzil zza() {
        try {
            return zzil.zzf(((zzfm) Tasks.await(this.zzb)).zzb(this.zzc, (byte[]) null));
        } catch (RemoteException | InterruptedException | ExecutionException unused) {
            SentryLogcatAdapter.e("NonceGenerator", "Unexpected exception while gathering request signals.");
            this.zza.zza(1);
            return zzil.zze();
        }
    }
}
