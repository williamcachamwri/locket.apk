package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzwe extends zzacv implements zzaeg {
    private zzwe() {
        super(zzwf.zzb);
    }

    public final zzwe zza(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzwf) this.zza).zzg = i;
        return this;
    }

    public final zzwe zzb(String str) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zzwf.zzd((zzwf) this.zza, str);
        return this;
    }

    public final zzwe zzc(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzwf) this.zza).zzh = zzwu.zza(i);
        return this;
    }

    public final zzwe zzd(int i) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        ((zzwf) this.zza).zzf = zzvq.zza(i);
        return this;
    }

    /* synthetic */ zzwe(zzwc zzwc) {
        super(zzwf.zzb);
    }
}
