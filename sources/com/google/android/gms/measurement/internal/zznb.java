package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzdh;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zznb extends zzh {
    protected final zznj zza = new zznj(this);
    protected final zznh zzb = new zznh(this);
    /* access modifiers changed from: private */
    public Handler zzc;
    private boolean zzd = true;
    private final zzng zze = new zzng(this);

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzgg zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgo zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzha zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzjq zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzlj zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzls zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    static /* synthetic */ void zza(zznb zznb, long j) {
        zznb.zzt();
        zznb.zzab();
        zznb.zzj().zzp().zza("Activity paused, time", Long.valueOf(j));
        zznb.zze.zza(j);
        if (zznb.zze().zzw()) {
            zznb.zzb.zzb(j);
        }
    }

    static /* synthetic */ void zzb(zznb zznb, long j) {
        zznb.zzt();
        zznb.zzab();
        zznb.zzj().zzp().zza("Activity resumed, time", Long.valueOf(j));
        if (zznb.zze().zza(zzbh.zzco)) {
            if (zznb.zze().zzw() || zznb.zzd) {
                zznb.zzb.zzc(j);
            }
        } else if (zznb.zze().zzw() || zznb.zzk().zzn.zza()) {
            zznb.zzb.zzc(j);
        }
        zznb.zze.zza();
        zznj zznj = zznb.zza;
        zznj.zza.zzt();
        if (zznj.zza.zzu.zzac()) {
            zznj.zza(zznj.zza.zzb().currentTimeMillis(), false);
        }
    }

    zznb(zzhy zzhy) {
        super(zzhy);
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    /* access modifiers changed from: private */
    public final void zzab() {
        zzt();
        if (this.zzc == null) {
            this.zzc = new zzdh(Looper.getMainLooper());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzt();
        this.zzd = z;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaa() {
        zzt();
        return this.zzd;
    }

    public final boolean zza(boolean z, boolean z2, long j) {
        return this.zzb.zza(z, z2, j);
    }
}
