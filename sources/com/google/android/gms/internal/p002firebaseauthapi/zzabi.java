package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzj;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabi  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabi extends zzady<Void, zzj> {
    private final String zzu;
    private final String zzv;

    public final String zza() {
        return "applyActionCode";
    }

    public zzabi(String str, String str2) {
        super(7);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        this.zzu = str;
        this.zzv = str2;
    }

    public final void zzb() {
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzu, this.zzv, (zzadf) this.zzb);
    }
}
