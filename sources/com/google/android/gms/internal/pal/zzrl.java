package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzrl extends zzacv implements zzaeg {
    private zzrl() {
        super(zzrm.zzb);
    }

    public final zzrl zza(zzaby zzaby) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzrm) this.zza).zzf = zzaby;
        return this;
    }

    public final zzrl zzb(zzrs zzrs) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzrm.zzj((zzrm) this.zza, zzrs);
        return this;
    }

    public final zzrl zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzrm) this.zza).zze = 0;
        return this;
    }

    /* synthetic */ zzrl(zzrk zzrk) {
        super(zzrm.zzb);
    }
}
