package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaam  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaam implements zzael<zzahh> {
    private final /* synthetic */ zzahi zza;
    private final /* synthetic */ zzade zzb;

    zzaam(zzzk zzzk, zzahi zzahi, zzade zzade) {
        this.zza = zzahi;
        this.zzb = zzade;
    }

    public final void zza(String str) {
        this.zzb.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzahh zzahh = (zzahh) obj;
        zzahi zzahi = this.zza;
        if (zzahi instanceof zzahm) {
            this.zzb.zzb(zzahh.zza());
        } else if (zzahi instanceof zzaho) {
            this.zzb.zza(zzahh);
        } else {
            throw new IllegalArgumentException("startMfaEnrollmentRequest must be an instance of either StartPhoneMfaEnrollmentRequest or StartTotpMfaEnrollmentRequest but was " + this.zza.getClass().getName() + ".");
        }
    }
}
