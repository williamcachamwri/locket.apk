package com.google.firebase.auth;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.p002firebaseauthapi.zzadg;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzh;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzl extends PhoneAuthProvider.OnVerificationStateChangedCallbacks {
    private final /* synthetic */ PhoneAuthOptions zza;
    private final /* synthetic */ zzh zzb;
    private final /* synthetic */ PhoneAuthProvider.OnVerificationStateChangedCallbacks zzc;
    private final /* synthetic */ FirebaseAuth zzd;

    zzl(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions, zzh zzh, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        this.zza = phoneAuthOptions;
        this.zzb = zzh;
        this.zzc = onVerificationStateChangedCallbacks;
        this.zzd = firebaseAuth;
    }

    public final void onCodeAutoRetrievalTimeOut(String str) {
        this.zzc.onCodeAutoRetrievalTimeOut(str);
    }

    public final void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        this.zzc.onCodeSent(str, forceResendingToken);
    }

    public final void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        this.zzc.onVerificationCompleted(phoneAuthCredential);
    }

    public final void onVerificationFailed(FirebaseException firebaseException) {
        if (zzadg.zza((Exception) firebaseException)) {
            this.zza.zza(true);
            Log.d("FirebaseAuth", "Re-triggering phone verification with Recaptcha flow forced for phone number " + this.zza.zzh());
            FirebaseAuth.zza(this.zza);
        } else if (TextUtils.isEmpty(this.zzb.zzc())) {
            String zzh = this.zza.zzh();
            Log.d("FirebaseAuth", "Invoking original failure callbacks after phone verification failure for " + zzh + ", error - " + firebaseException.getMessage());
            this.zzc.onVerificationFailed(firebaseException);
        } else if (!zzadg.zzb((Exception) firebaseException) || !this.zzd.zzb().zza("PHONE_PROVIDER") || !TextUtils.isEmpty(this.zzb.zzb())) {
            String zzh2 = this.zza.zzh();
            Log.d("FirebaseAuth", "Invoking original failure callbacks after reCAPTCHA Enterprise + phone verification failure for " + zzh2 + ", error - " + firebaseException.getMessage());
            this.zzc.onVerificationFailed(firebaseException);
        } else {
            this.zza.zzb(true);
            Log.d("FirebaseAuth", "Re-triggering phone verification with non-reCAPTCHA Enterprise flow for phone number " + this.zza.zzh());
            FirebaseAuth.zza(this.zza);
        }
    }
}
