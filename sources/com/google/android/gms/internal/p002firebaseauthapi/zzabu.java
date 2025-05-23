package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.internal.zzad;
import com.google.firebase.auth.internal.zzj;
import com.google.firebase.auth.internal.zzm;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabu extends zzady<AuthResult, zzj> {
    private final zzahr zzu;

    public final String zza() {
        return "linkFederatedCredential";
    }

    public zzabu(AuthCredential authCredential) {
        super(2);
        Preconditions.checkNotNull(authCredential, "credential cannot be null");
        this.zzu = zzm.zza(authCredential, (String) null);
    }

    public final void zzb() {
        zzad zza = zzabj.zza(this.zzc, this.zzk);
        ((zzj) this.zze).zza(this.zzj, zza);
        zzb(new zzx(zza));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzd.zze(), this.zzu, (zzadf) this.zzb);
    }
}
