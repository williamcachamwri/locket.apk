package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzad;
import com.google.firebase.auth.internal.zzj;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzacf extends zzady<Void, zzj> {
    private final zzzg zzu;

    public final String zza() {
        return "reauthenticateWithPhoneCredential";
    }

    public zzacf(PhoneAuthCredential phoneAuthCredential, String str) {
        super(2);
        Preconditions.checkNotNull(phoneAuthCredential, "credential cannot be null");
        phoneAuthCredential.zza(false);
        this.zzu = new zzzg(phoneAuthCredential, str);
    }

    public final void zzb() {
        zzad zza = zzabj.zza(this.zzc, this.zzk);
        if (this.zzd.getUid().equalsIgnoreCase(zza.getUid())) {
            ((zzj) this.zze).zza(this.zzj, zza);
            zzb(null);
            return;
        }
        zza(new Status(FirebaseError.ERROR_USER_MISMATCH));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzu, (zzadf) this.zzb);
    }
}
