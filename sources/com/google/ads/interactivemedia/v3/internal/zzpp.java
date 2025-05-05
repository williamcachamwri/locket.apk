package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzpp implements OnCompleteListener {
    public final /* synthetic */ zzpq zza;

    public /* synthetic */ zzpp(zzpq zzpq) {
        this.zza = zzpq;
    }

    public final void onComplete(Task task) {
        zzpq zzpq = this.zza;
        if (task.isCanceled()) {
            zzpq.cancel(false);
        } else if (task.isSuccessful()) {
            zzpq.zzc(task.getResult());
        } else {
            Exception exception = task.getException();
            if (exception != null) {
                zzpq.zzd(exception);
                return;
            }
            throw new IllegalStateException();
        }
    }
}
