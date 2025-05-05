package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final class zzmz {
    /* access modifiers changed from: private */
    public zznl zza;
    /* access modifiers changed from: private */
    public Long zzb;
    /* access modifiers changed from: private */
    public zzmu zzc;
    /* access modifiers changed from: private */
    public Long zzd;
    /* access modifiers changed from: private */
    public zzna zze;
    /* access modifiers changed from: private */
    public Long zzf;

    public final zzmz zzb(Long l) {
        this.zzf = l;
        return this;
    }

    public final zzmz zzc(zzna zzna) {
        this.zze = zzna;
        return this;
    }

    public final zzmz zzd(zzmu zzmu) {
        this.zzc = zzmu;
        return this;
    }

    public final zzmz zze(Long l) {
        this.zzd = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zzmz zzf(zznl zznl) {
        this.zza = zznl;
        return this;
    }

    public final zzmz zzg(Long l) {
        this.zzb = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zznc zzi() {
        return new zznc(this, (zznb) null);
    }
}
