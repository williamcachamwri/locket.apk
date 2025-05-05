package com.google.ads.interactivemedia.pal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzad implements OnCompleteListener {
    public final /* synthetic */ NonceLoader zza;

    public /* synthetic */ zzad(NonceLoader nonceLoader) {
        this.zza = nonceLoader;
    }

    public final void onComplete(Task task) {
        this.zza.zzd(task);
    }
}
