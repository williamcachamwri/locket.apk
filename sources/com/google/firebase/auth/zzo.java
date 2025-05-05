package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.zzbq;
import com.google.firebase.auth.internal.zzce;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzo extends zzbq<AuthResult> {
    private final /* synthetic */ FirebaseUser zza;
    private final /* synthetic */ EmailAuthCredential zzb;
    private final /* synthetic */ FirebaseAuth zzc;

    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.auth.internal.zzce, com.google.firebase.auth.FirebaseAuth$zzb] */
    public final Task<AuthResult> zza(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.i("FirebaseAuth", "Linking email account with empty reCAPTCHA token");
        } else {
            Log.i("FirebaseAuth", "Got reCAPTCHA token for linking email account");
        }
        return this.zzc.zze.zza(this.zzc.zza, this.zza, (AuthCredential) this.zzb, str, (zzce) new FirebaseAuth.zzb());
    }

    zzo(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential) {
        this.zza = firebaseUser;
        this.zzb = emailAuthCredential;
        this.zzc = firebaseAuth;
    }
}
