package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.internal.zzj;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabo extends zzady<Void, zzj> {
    private final MultiFactorAssertion zzu;
    private final String zzv;
    private final String zzw;
    private final String zzx;

    public final String zza() {
        return "finalizeMfaEnrollment";
    }

    public zzabo(MultiFactorAssertion multiFactorAssertion, String str, String str2, String str3) {
        super(2);
        this.zzu = (MultiFactorAssertion) Preconditions.checkNotNull(multiFactorAssertion);
        this.zzv = Preconditions.checkNotEmpty(str);
        this.zzw = str2;
        this.zzx = str3;
    }

    public final void zzb() {
        ((zzj) this.zze).zza(this.zzj, zzabj.zza(this.zzc, this.zzk));
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzu, this.zzv, this.zzw, this.zzx, (zzadf) this.zzb);
    }
}
