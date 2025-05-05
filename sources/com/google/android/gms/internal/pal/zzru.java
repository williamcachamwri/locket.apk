package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzru extends zzacv implements zzaeg {
    private zzru() {
        super(zzrv.zzb);
    }

    public final zzru zza(zzsb zzsb) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzrv.zzi((zzrv) this.zza, zzsb);
        return this;
    }

    public final zzru zzb(zzup zzup) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzrv.zzj((zzrv) this.zza, zzup);
        return this;
    }

    public final zzru zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzrv) this.zza).zze = i;
        return this;
    }

    /* synthetic */ zzru(zzrt zzrt) {
        super(zzrv.zzb);
    }
}
