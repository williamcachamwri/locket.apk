package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaak  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaak implements zzael<zzaib> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzaal zzb;

    zzaak(zzaal zzaal, zzade zzade) {
        this.zza = zzade;
        this.zzb = zzaal;
    }

    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzaib zzaib = (zzaib) obj;
        if (TextUtils.isEmpty(zzaib.zza()) || TextUtils.isEmpty(zzaib.zzb())) {
            this.zza.zza(zzaq.zza("INTERNAL_SUCCESS_SIGN_OUT"));
            return;
        }
        this.zzb.zza.zza(new zzagl(zzaib.zzb(), zzaib.zza(), Long.valueOf(zzagn.zza(zzaib.zza())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza, this);
    }
}
