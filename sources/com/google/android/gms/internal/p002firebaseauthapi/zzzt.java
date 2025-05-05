package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.firebase.auth.zzan;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzzt implements zzael<zzahe> {
    private final /* synthetic */ zzahb zza;
    private final /* synthetic */ zzage zzb;
    private final /* synthetic */ zzade zzc;
    private final /* synthetic */ zzagl zzd;
    private final /* synthetic */ zzaem zze;
    private final /* synthetic */ zzzk zzf;

    zzzt(zzzk zzzk, zzahb zzahb, zzage zzage, zzade zzade, zzagl zzagl, zzaem zzaem) {
        this.zza = zzahb;
        this.zzb = zzage;
        this.zzc = zzade;
        this.zzd = zzagl;
        this.zze = zzaem;
        this.zzf = zzzk;
    }

    public final void zza(String str) {
        this.zze.zza(str);
    }

    public final /* synthetic */ void zza(Object obj) {
        zzahe zzahe = (zzahe) obj;
        if (this.zza.zzi("EMAIL")) {
            this.zzb.zzb((String) null);
        } else if (this.zza.zzc() != null) {
            this.zzb.zzb(this.zza.zzc());
        }
        if (this.zza.zzi("DISPLAY_NAME")) {
            this.zzb.zza((String) null);
        } else if (this.zza.zzb() != null) {
            this.zzb.zza(this.zza.zzb());
        }
        if (this.zza.zzi("PHOTO_URL")) {
            this.zzb.zzc((String) null);
        } else if (this.zza.zze() != null) {
            this.zzb.zzc(this.zza.zze());
        }
        if (!TextUtils.isEmpty(this.zza.zzd())) {
            Preconditions.checkNotEmpty(Base64Utils.encode("redacted".getBytes()));
        }
        if (this.zza.zzi("delete_passkey")) {
            this.zzb.zza((zzal<zzan>) zzal.zzh());
        }
        List zze2 = zzahe.zze();
        if (zze2 == null) {
            zze2 = new ArrayList();
        }
        this.zzb.zza((List<zzagr>) zze2);
        zzade zzade = this.zzc;
        zzagl zzagl = this.zzd;
        Preconditions.checkNotNull(zzagl);
        Preconditions.checkNotNull(zzahe);
        String zzc2 = zzahe.zzc();
        String zzd2 = zzahe.zzd();
        if (!TextUtils.isEmpty(zzc2) && !TextUtils.isEmpty(zzd2)) {
            zzagl = new zzagl(zzd2, zzc2, Long.valueOf(zzahe.zza()), zzagl.zze());
        }
        zzade.zza(zzagl, this.zzb);
    }
}
