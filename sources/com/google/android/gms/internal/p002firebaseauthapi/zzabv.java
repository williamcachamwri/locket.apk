package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzad;
import com.google.firebase.auth.internal.zzj;
import com.google.firebase.auth.internal.zzx;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabv  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabv extends zzady<AuthResult, zzj> {
    private final EmailAuthCredential zzu;
    private final String zzv;

    public final String zza() {
        return "linkEmailAuthCredential";
    }

    public zzabv(EmailAuthCredential emailAuthCredential, String str) {
        super(2);
        this.zzu = (EmailAuthCredential) Preconditions.checkNotNull(emailAuthCredential, "credential cannot be null");
        Preconditions.checkNotEmpty(emailAuthCredential.zzc(), "email cannot be null");
        Preconditions.checkNotEmpty(emailAuthCredential.zzd(), "password cannot be null");
        this.zzv = str;
    }

    public final void zzb() {
        zzad zza = zzabj.zza(this.zzc, this.zzk);
        ((zzj) this.zze).zza(this.zzj, zza);
        zzb(new zzx(zza));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzu.zzc(), Preconditions.checkNotEmpty(this.zzu.zzd()), this.zzd.zze(), this.zzd.getTenantId(), this.zzv, this.zzb);
    }
}
