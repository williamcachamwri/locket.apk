package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.auth.internal.zzaq;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabd implements zzael<zzagl> {
    private final /* synthetic */ UserProfileChangeRequest zza;
    private final /* synthetic */ zzade zzb;
    private final /* synthetic */ zzzk zzc;

    zzabd(zzzk zzzk, UserProfileChangeRequest userProfileChangeRequest, zzade zzade) {
        this.zza = userProfileChangeRequest;
        this.zzb = zzade;
        this.zzc = zzzk;
    }

    public final void zza(String str) {
        this.zzb.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzagl zzagl = (zzagl) obj;
        zzahb zzahb = new zzahb();
        zzahb.zzd(zzagl.zzc());
        if (this.zza.zzb() || this.zza.getDisplayName() != null) {
            zzahb.zzb(this.zza.getDisplayName());
        }
        if (this.zza.zzc() || this.zza.getPhotoUri() != null) {
            zzahb.zzg(this.zza.zza());
        }
        zzzk.zza(this.zzc, this.zzb, zzagl, zzahb, (zzaem) this);
    }
}
