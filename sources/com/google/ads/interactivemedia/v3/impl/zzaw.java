package com.google.ads.interactivemedia.v3.impl;

import android.graphics.Bitmap;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzaw implements OnCompleteListener {
    public final /* synthetic */ zzax zza;

    public /* synthetic */ zzaw(zzax zzax) {
        this.zza = zzax;
    }

    public final void onComplete(Task task) {
        if (task.isSuccessful()) {
            this.zza.setImageBitmap((Bitmap) task.getResult());
        } else {
            zzfk.zzb("Image companion error", task.getException());
        }
    }
}
