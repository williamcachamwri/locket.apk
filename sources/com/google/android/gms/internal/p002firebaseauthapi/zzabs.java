package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabs extends zzady<zzagm, Void> {
    private final zzagj zzu;

    public final String zza() {
        return "getRecaptchaConfig";
    }

    public zzabs(String str, String str2) {
        super(10);
        Preconditions.checkNotEmpty(str2);
        this.zzu = zzagj.zza(str, str2);
    }

    public final void zzb() {
        zzb(this.zzr);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzu, (zzadf) this.zzb);
    }
}
