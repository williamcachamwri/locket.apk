package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzj;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzacz extends zzady<Void, zzj> {
    private final String zzu;

    public final String zza() {
        return "updatePassword";
    }

    public zzacz(String str) {
        super(2);
        this.zzu = Preconditions.checkNotEmpty(str, "password cannot be null or empty");
    }

    public final void zzb() {
        ((zzj) this.zze).zza(this.zzj, zzabj.zza(this.zzc, this.zzk));
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zzc(this.zzd.zze(), this.zzu, this.zzb);
    }
}
