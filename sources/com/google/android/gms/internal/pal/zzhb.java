package com.google.android.gms.internal.pal;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzhb extends zzgs {
    final /* synthetic */ TaskCompletionSource zza;

    zzhb(zzhc zzhc, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(int i) {
        this.zza.trySetException(new zzgy(i));
    }

    public final void zzc(Bundle bundle) {
        this.zza.trySetResult(bundle.getString("newToken"));
    }
}
