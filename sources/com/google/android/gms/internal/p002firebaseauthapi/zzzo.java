package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzaq;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzo  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzzo implements zzael<zzagl> {
    private final /* synthetic */ EmailAuthCredential zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzade zzc;
    private final /* synthetic */ zzzk zzd;

    zzzo(zzzk zzzk, EmailAuthCredential emailAuthCredential, String str, zzade zzade) {
        this.zza = emailAuthCredential;
        this.zzb = str;
        this.zzc = zzade;
        this.zzd = zzzk;
    }

    public final void zza(String str) {
        this.zzc.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        this.zzd.zza(new zzafn(this.zza, ((zzagl) obj).zzc(), this.zzb), this.zzc);
    }
}
