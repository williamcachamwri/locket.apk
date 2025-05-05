package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzj;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzacy extends zzady<Void, zzj> {
    private final PhoneAuthCredential zzu;

    public final String zza() {
        return "updatePhoneNumber";
    }

    public zzacy(PhoneAuthCredential phoneAuthCredential) {
        super(2);
        this.zzu = (PhoneAuthCredential) Preconditions.checkNotNull(phoneAuthCredential);
    }

    public final void zzb() {
        ((zzj) this.zze).zza(this.zzj, zzabj.zza(this.zzc, this.zzk));
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(new zzagp(this.zzd.zze(), zzaeq.zza(this.zzu)), (zzadf) this.zzb);
    }
}
