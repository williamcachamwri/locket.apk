package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.internal.zzj;
import com.google.firebase.auth.internal.zzw;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabl extends zzady<ActionCodeResult, zzj> {
    private final String zzu;
    private final String zzv;

    public final String zza() {
        return "checkActionCode";
    }

    public zzabl(String str, String str2) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        this.zzu = str;
        this.zzv = str2;
    }

    public final void zzb() {
        zzb(new zzw(this.zzm));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zzd(this.zzu, this.zzv, this.zzb);
    }
}
