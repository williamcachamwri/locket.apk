package com.google.ads.interactivemedia.pal;

import android.view.MotionEvent;
import com.google.android.gms.internal.pal.zzfm;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final /* synthetic */ class zzal implements Continuation {
    public final /* synthetic */ MotionEvent zza;

    public /* synthetic */ zzal(MotionEvent motionEvent) {
        this.zza = motionEvent;
    }

    public final Object then(Task task) {
        MotionEvent motionEvent = this.zza;
        int i = NonceManager.zzc;
        ((zzfm) task.getResult()).zzd(motionEvent);
        return null;
    }
}
