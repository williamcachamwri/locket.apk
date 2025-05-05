package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaeh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final /* synthetic */ class zzaeh implements Runnable {
    private /* synthetic */ zzaei zza;
    private /* synthetic */ zzaek zzb;
    private /* synthetic */ TaskCompletionSource zzc;

    public /* synthetic */ zzaeh(zzaei zzaei, zzaek zzaek, TaskCompletionSource taskCompletionSource) {
        this.zza = zzaei;
        this.zzb = zzaek;
        this.zzc = taskCompletionSource;
    }

    public final void run() {
        this.zzb.zza(this.zzc, this.zza.zza);
    }
}
