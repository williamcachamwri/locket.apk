package com.google.ads.interactivemedia.pal;

import com.google.android.gms.internal.pal.zzagc;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzj extends zzw {
    private final zzagc zza;
    private final zzagc zzb;
    private final zzagc zzc;
    private final zzagc zzd;
    private final zzagc zze;
    private final int zzf;

    /* synthetic */ zzj(zzagc zzagc, zzagc zzagc2, zzagc zzagc3, zzagc zzagc4, zzagc zzagc5, int i, zzi zzi) {
        this.zza = zzagc;
        this.zzb = zzagc2;
        this.zzc = zzagc3;
        this.zzd = zzagc4;
        this.zze = zzagc5;
        this.zzf = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzw) {
            zzw zzw = (zzw) obj;
            return this.zza.equals(zzw.zzc()) && this.zzb.equals(zzw.zzd()) && this.zzc.equals(zzw.zzb()) && this.zzd.equals(zzw.zzf()) && this.zze.equals(zzw.zze()) && this.zzf == zzw.zza();
        }
    }

    public final int hashCode() {
        return ((((((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode()) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf;
    }

    public final String toString() {
        String obj = this.zza.toString();
        String obj2 = this.zzb.toString();
        String obj3 = this.zzc.toString();
        String obj4 = this.zzd.toString();
        String obj5 = this.zze.toString();
        int i = this.zzf;
        return "NonceTimingData{nonceLoaderInitTime=" + obj + ", nonceRequestTime=" + obj2 + ", nonceLoadedTime=" + obj3 + ", resourceFetchStartTime=" + obj4 + ", resourceFetchEndTime=" + obj5 + ", nonceLength=" + i + "}";
    }

    /* access modifiers changed from: package-private */
    public final int zza() {
        return this.zzf;
    }

    /* access modifiers changed from: package-private */
    public final zzagc zzb() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final zzagc zzc() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final zzagc zzd() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final zzagc zze() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final zzagc zzf() {
        return this.zzd;
    }
}
