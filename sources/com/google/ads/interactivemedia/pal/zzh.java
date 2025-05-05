package com.google.ads.interactivemedia.pal;

import com.google.android.gms.internal.pal.zzagc;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzh extends zzv {
    private zzagc zza;
    private zzagc zzb;
    private zzagc zzc;
    private zzagc zzd;
    private zzagc zze;
    private int zzf;
    private byte zzg;

    zzh() {
    }

    /* access modifiers changed from: package-private */
    public final zzv zza(int i) {
        this.zzf = i;
        this.zzg = 1;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzv zzb(zzagc zzagc) {
        this.zzc = zzagc;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzv zzc(zzagc zzagc) {
        this.zza = zzagc;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzv zzd(zzagc zzagc) {
        this.zzb = zzagc;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzv zze(zzagc zzagc) {
        this.zze = zzagc;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzv zzf(zzagc zzagc) {
        this.zzd = zzagc;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final zzw zzg() {
        zzagc zzagc;
        zzagc zzagc2;
        zzagc zzagc3;
        zzagc zzagc4;
        zzagc zzagc5;
        if (this.zzg == 1 && (zzagc = this.zza) != null && (zzagc2 = this.zzb) != null && (zzagc3 = this.zzc) != null && (zzagc4 = this.zzd) != null && (zzagc5 = this.zze) != null) {
            return new zzj(zzagc, zzagc2, zzagc3, zzagc4, zzagc5, this.zzf, (zzi) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" nonceLoaderInitTime");
        }
        if (this.zzb == null) {
            sb.append(" nonceRequestTime");
        }
        if (this.zzc == null) {
            sb.append(" nonceLoadedTime");
        }
        if (this.zzd == null) {
            sb.append(" resourceFetchStartTime");
        }
        if (this.zze == null) {
            sb.append(" resourceFetchEndTime");
        }
        if (this.zzg == 0) {
            sb.append(" nonceLength");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
