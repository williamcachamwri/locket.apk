package com.google.mlkit.common.internal.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final /* synthetic */ class zzf implements OnCompleteListener {
    public final /* synthetic */ zzg zza;

    public /* synthetic */ zzf(zzg zzg) {
        this.zza = zzg;
    }

    public final void onComplete(Task task) {
        this.zza.zzd(task);
    }
}
