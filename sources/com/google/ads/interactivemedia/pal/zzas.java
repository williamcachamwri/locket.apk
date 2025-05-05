package com.google.ads.interactivemedia.pal;

import android.view.View;
import com.google.android.gms.internal.pal.zzfm;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzas implements Continuation {
    final /* synthetic */ NonceManager zza;

    zzas(NonceManager nonceManager) {
        this.zza = nonceManager;
    }

    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        return ((zzfm) task.getResult()).zzc(this.zza.zzd, (View) null, NonceManager.zza(this.zza));
    }
}
