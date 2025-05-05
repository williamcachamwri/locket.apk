package com.google.android.gms.internal.mlkit_vision_barcode;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
public final class zzqo {
    /* access modifiers changed from: private */
    public Long zza;
    /* access modifiers changed from: private */
    public zzrb zzb;
    /* access modifiers changed from: private */
    public Boolean zzc;
    /* access modifiers changed from: private */
    public Boolean zzd;
    /* access modifiers changed from: private */
    public Boolean zze;

    public final zzqo zza(Boolean bool) {
        this.zzd = bool;
        return this;
    }

    public final zzqo zzb(Boolean bool) {
        this.zze = bool;
        return this;
    }

    public final zzqo zzc(Long l) {
        this.zza = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzqo zzd(zzrb zzrb) {
        this.zzb = zzrb;
        return this;
    }

    public final zzqo zze(Boolean bool) {
        this.zzc = bool;
        return this;
    }

    public final zzqq zzf() {
        return new zzqq(this, (zzqp) null);
    }
}
