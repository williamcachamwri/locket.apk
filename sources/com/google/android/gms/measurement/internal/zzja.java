package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
class zzja implements zzjc {
    protected final zzhy zzu;

    @Pure
    public Context zza() {
        return this.zzu.zza();
    }

    @Pure
    public Clock zzb() {
        return this.zzu.zzb();
    }

    @Pure
    public zzab zzd() {
        return this.zzu.zzd();
    }

    @Pure
    public zzag zze() {
        return this.zzu.zzf();
    }

    @Pure
    public zzaz zzf() {
        return this.zzu.zzg();
    }

    @Pure
    public zzgh zzi() {
        return this.zzu.zzk();
    }

    @Pure
    public zzgo zzj() {
        return this.zzu.zzj();
    }

    @Pure
    public zzha zzk() {
        return this.zzu.zzn();
    }

    @Pure
    public zzhv zzl() {
        return this.zzu.zzl();
    }

    @Pure
    public zzos zzq() {
        return this.zzu.zzt();
    }

    zzja(zzhy zzhy) {
        Preconditions.checkNotNull(zzhy);
        this.zzu = zzhy;
    }

    public void zzr() {
        this.zzu.zzl().zzr();
    }

    public void zzs() {
        this.zzu.zzy();
    }

    public void zzt() {
        this.zzu.zzl().zzt();
    }
}
