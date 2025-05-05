package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaex  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaex extends zzade {
    private final String zza;
    private final /* synthetic */ zzaew zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzaex(zzaew zzaew, zzade zzade, String str) {
        super(zzade);
        this.zzb = zzaew;
        this.zza = str;
    }

    public final void zzb(String str) {
        zzaew.zza.d("onCodeSent", new Object[0]);
        zzaez zzaez = (zzaez) this.zzb.zzd.get(this.zza);
        if (zzaez != null) {
            for (zzade zzb2 : zzaez.zzb) {
                zzb2.zzb(str);
            }
            zzaez.zzg = true;
            zzaez.zzd = str;
            if (zzaez.zza <= 0) {
                this.zzb.zzb(this.zza);
            } else if (!zzaez.zzc) {
                this.zzb.zze(this.zza);
            } else if (!zzag.zzc(zzaez.zze)) {
                zzaew.zza(this.zzb, this.zza);
            }
        }
    }

    public final void zza(Status status) {
        Logger zza2 = zzaew.zza;
        String statusCodeString = CommonStatusCodes.getStatusCodeString(status.getStatusCode());
        zza2.e("SMS verification code request failed: " + statusCodeString + " " + status.getStatusMessage(), new Object[0]);
        zzaez zzaez = (zzaez) this.zzb.zzd.get(this.zza);
        if (zzaez != null) {
            for (zzade zza3 : zzaez.zzb) {
                zza3.zza(status);
            }
            this.zzb.zzc(this.zza);
        }
    }
}
