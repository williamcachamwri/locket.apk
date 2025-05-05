package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzao;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzact  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzact extends zzady<zzahh, Void> {
    private final zzaho zzu;

    public final String zza() {
        return "startMfaEnrollment";
    }

    public zzact(zzao zzao, String str) {
        super(12);
        Preconditions.checkNotNull(zzao);
        this.zzu = zzaho.zza(Preconditions.checkNotEmpty(zzao.zzb()), str);
    }

    public final void zzb() {
        zzb(this.zzt);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzu, (zzadf) this.zzb);
    }
}
