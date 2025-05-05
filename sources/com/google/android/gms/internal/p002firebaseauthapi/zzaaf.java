package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaaf implements zzael<zzahz> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzael zzb;
    private final /* synthetic */ zzaac zzc;

    zzaaf(zzaac zzaac, zzade zzade, zzael zzael) {
        this.zza = zzade;
        this.zzb = zzael;
        this.zzc = zzaac;
    }

    public final void zza(String str) {
        this.zzb.zza(str);
    }

    public final /* synthetic */ void zza(Object obj) {
        zzahz zzahz = (zzahz) obj;
        if (!TextUtils.isEmpty(zzahz.zze())) {
            this.zza.zza(new Status(FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE), PhoneAuthCredential.zzb(zzahz.zzc(), zzahz.zze()));
            return;
        }
        this.zzc.zza.zza(new zzagl(zzahz.zzd(), zzahz.zzb(), Long.valueOf(zzahz.zza()), "Bearer"), (String) null, "phone", Boolean.valueOf(zzahz.zzf()), (zze) null, this.zza, this.zzb);
    }
}
