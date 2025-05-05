package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.internal.zzap;
import com.google.firebase.auth.internal.zzj;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabp extends zzady<SignInMethodQueryResult, zzj> {
    private final String zzu;
    private final String zzv;

    public final String zza() {
        return "fetchSignInMethodsForEmail";
    }

    public zzabp(String str, String str2) {
        super(3);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        this.zzu = str;
        this.zzv = str2;
    }

    public final void zzb() {
        List list;
        if (this.zzl.zza() == null) {
            list = zzal.zzh();
        } else {
            list = (List) Preconditions.checkNotNull(this.zzl.zza());
        }
        zzb(new zzap(list));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zze(this.zzu, this.zzv, this.zzb);
    }
}
