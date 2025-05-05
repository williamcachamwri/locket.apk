package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.internal.zzj;
import com.google.firebase.auth.internal.zzw;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzadd extends zzady<String, zzj> {
    private final String zzu;
    private final String zzv;

    public final String zza() {
        return "verifyPasswordResetCode";
    }

    public zzadd(String str, String str2) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        this.zzu = str;
        this.zzv = str2;
    }

    public final void zzb() {
        if (new zzw(this.zzm).getOperation() != 0) {
            zza(new Status(FirebaseError.ERROR_INTERNAL_ERROR));
        } else {
            zzb(this.zzm.zzb());
        }
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zzd(this.zzu, this.zzv, this.zzb);
    }
}
