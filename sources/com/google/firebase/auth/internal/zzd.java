package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzd implements OnSuccessListener<String> {
    private final /* synthetic */ TaskCompletionSource zza;

    zzd(zza zza2, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        this.zza.setResult(new zzo().zzb((String) obj).zza());
    }
}
