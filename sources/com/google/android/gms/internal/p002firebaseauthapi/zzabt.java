package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabt extends zzady<zzagh, Void> {
    private final zzagi zzu = zzagi.zzb();

    public final String zza() {
        return "getRecaptchaParam";
    }

    public zzabt() {
        super(11);
    }

    public final void zzb() {
        zzb(this.zzs);
    }

    public final void zza(TaskCompletionSource<zzagh> taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzu, (zzadf) this.zzb);
    }
}
