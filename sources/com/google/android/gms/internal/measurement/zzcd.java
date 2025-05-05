package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzcd extends zzcl {
    private String zza;
    private zzco zzb;
    private zzcn zzc;
    private byte zzd;

    /* access modifiers changed from: package-private */
    public final zzcl zza(zzco zzco) {
        if (zzco != null) {
            this.zzb = zzco;
            return this;
        }
        throw new NullPointerException("Null fileChecks");
    }

    public final zzcl zza(String str) {
        this.zza = str;
        return this;
    }

    public final zzcl zza(zzcn zzcn) {
        if (zzcn != null) {
            this.zzc = zzcn;
            return this;
        }
        throw new NullPointerException("Null filePurpose");
    }

    public final zzcl zza(boolean z) {
        this.zzd = (byte) (this.zzd | 1);
        return this;
    }

    public final zzcm zza() {
        if (this.zzd == 1 && this.zza != null && this.zzb != null && this.zzc != null) {
            return new zzce(this.zza, false, this.zzb, (zzcc) null, (zzcb) null, this.zzc, (zzcg) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" fileOwner");
        }
        if ((1 & this.zzd) == 0) {
            sb.append(" hasDifferentDmaOwner");
        }
        if (this.zzb == null) {
            sb.append(" fileChecks");
        }
        if (this.zzc == null) {
            sb.append(" filePurpose");
        }
        throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
    }

    zzcd() {
    }
}
