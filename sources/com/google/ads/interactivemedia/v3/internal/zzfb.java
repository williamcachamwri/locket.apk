package com.google.ads.interactivemedia.v3.internal;

import android.graphics.Bitmap;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzfb implements zzug {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ String zzb;

    zzfb(zzfc zzfc, TaskCompletionSource taskCompletionSource, String str) {
        this.zza = taskCompletionSource;
        this.zzb = str;
    }

    public final void zza(Throwable th) {
        this.zza.setException(new Exception("Failed to load image from: ".concat(String.valueOf(this.zzb)), th));
    }

    public final /* synthetic */ void zzb(Object obj) {
        this.zza.trySetResult((Bitmap) obj);
    }
}
