package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzat;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabm extends zzady<Void, zzat> {
    public final String zza() {
        return "delete";
    }

    public zzabm() {
        super(5);
    }

    public final void zzb() {
        ((zzat) this.zze).zza();
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzd.zze(), (zzadf) this.zzb);
    }
}
