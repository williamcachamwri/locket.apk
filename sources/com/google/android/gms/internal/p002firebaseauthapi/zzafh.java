package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzafh extends zzagx {
    private String zza;
    private String zzb;
    private String zzc;
    private zzaga zzd;
    private String zze;

    public final zzagx zza(String str) {
        if (str != null) {
            this.zze = str;
            return this;
        }
        throw new NullPointerException("Null idToken");
    }

    public final zzagx zzb(String str) {
        if (str != null) {
            this.zza = str;
            return this;
        }
        throw new NullPointerException("Null providerId");
    }

    public final zzagx zzc(String str) {
        this.zzb = str;
        return this;
    }

    public final zzagx zzd(String str) {
        if (str != null) {
            this.zzc = str;
            return this;
        }
        throw new NullPointerException("Null token");
    }

    public final zzagx zza(zzaga zzaga) {
        if (zzaga != null) {
            this.zzd = zzaga;
            return this;
        }
        throw new NullPointerException("Null tokenType");
    }

    public final zzagy zza() {
        if (this.zza != null && this.zzc != null && this.zzd != null && this.zze != null) {
            return new zzafi(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" providerId");
        }
        if (this.zzc == null) {
            sb.append(" token");
        }
        if (this.zzd == null) {
            sb.append(" tokenType");
        }
        if (this.zze == null) {
            sb.append(" idToken");
        }
        throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
    }

    zzafh() {
    }
}
