package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthProvider;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzadc extends zzady<Void, PhoneAuthProvider.OnVerificationStateChangedCallbacks> {
    private final zzagz zzu;

    public final String zza() {
        return "verifyPhoneNumber";
    }

    public final void zzb() {
    }

    public zzadc(zzagz zzagz) {
        super(8);
        Preconditions.checkNotNull(zzagz);
        this.zzu = zzagz;
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzu, (zzadf) this.zzb);
    }
}
