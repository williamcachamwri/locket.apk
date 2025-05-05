package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zznh {
    protected long zza;
    final /* synthetic */ zznb zzb;
    private long zzc;
    private final zzav zzd;

    /* access modifiers changed from: package-private */
    public final long zza(long j) {
        long j2 = j - this.zza;
        this.zza = j;
        return j2;
    }

    static /* synthetic */ void zza(zznh zznh) {
        zznh.zzb.zzt();
        zznh.zza(false, false, zznh.zzb.zzb().elapsedRealtime());
        zznh.zzb.zzc().zza(zznh.zzb.zzb().elapsedRealtime());
    }

    public zznh(zznb zznb) {
        this.zzb = zznb;
        this.zzd = new zznk(this, zznb.zzu);
        long elapsedRealtime = zznb.zzb().elapsedRealtime();
        this.zzc = elapsedRealtime;
        this.zza = elapsedRealtime;
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zzd.zza();
        if (this.zzb.zze().zza(zzbh.zzdb)) {
            this.zzc = this.zzb.zzb().elapsedRealtime();
        } else {
            this.zzc = 0;
        }
        this.zza = this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(long j) {
        this.zzd.zza();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(long j) {
        this.zzb.zzt();
        this.zzd.zza();
        this.zzc = j;
        this.zza = j;
    }

    public final boolean zza(boolean z, boolean z2, long j) {
        this.zzb.zzt();
        this.zzb.zzu();
        if (this.zzb.zzu.zzac()) {
            this.zzb.zzk().zzk.zza(this.zzb.zzb().currentTimeMillis());
        }
        long j2 = j - this.zzc;
        if (z || j2 >= 1000) {
            if (!z2) {
                j2 = zza(j);
            }
            this.zzb.zzj().zzp().zza("Recording user engagement, ms", Long.valueOf(j2));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j2);
            zzos.zza(this.zzb.zzn().zza(!this.zzb.zze().zzw()), bundle, true);
            if (!z2) {
                this.zzb.zzm().zzc("auto", "_e", bundle);
            }
            this.zzc = j;
            this.zzd.zza();
            this.zzd.zza(zzbh.zzbc.zza(null).longValue());
            return true;
        }
        this.zzb.zzj().zzp().zza("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j2));
        return false;
    }
}
