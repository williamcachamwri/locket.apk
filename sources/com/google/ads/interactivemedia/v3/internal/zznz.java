package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zznz extends zznv {
    private final String zza;
    private final boolean zzb;
    private final boolean zzc;
    private final long zzd;
    private final long zze;

    /* synthetic */ zznz(String str, boolean z, boolean z2, boolean z3, long j, boolean z4, long j2, zzny zzny) {
        this.zza = str;
        this.zzb = z;
        this.zzc = z2;
        this.zzd = j;
        this.zze = j2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zznv) {
            zznv zznv = (zznv) obj;
            if (this.zza.equals(zznv.zzd()) && this.zzb == zznv.zzh() && this.zzc == zznv.zzg()) {
                zznv.zzf();
                if (this.zzd == zznv.zzb()) {
                    zznv.zze();
                    if (this.zze == zznv.zza()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 1231;
        int hashCode = (((this.zza.hashCode() ^ 1000003) * 1000003) ^ (true != this.zzb ? 1237 : 1231)) * 1000003;
        if (true != this.zzc) {
            i = 1237;
        }
        return ((((((((hashCode ^ i) * 1000003) ^ 1237) * 1000003) ^ ((int) this.zzd)) * 1000003) ^ 1237) * 1000003) ^ ((int) this.zze);
    }

    public final String toString() {
        return "AdShield2Options{clientVersion=" + this.zza + ", shouldGetAdvertisingId=" + this.zzb + ", isGooglePlayServicesAvailable=" + this.zzc + ", enableQuerySignalsTimeout=false, querySignalsTimeoutMs=" + this.zzd + ", enableQuerySignalsCache=false, querySignalsCacheTtlSeconds=" + this.zze + "}";
    }

    public final long zza() {
        return this.zze;
    }

    public final long zzb() {
        return this.zzd;
    }

    public final String zzd() {
        return this.zza;
    }

    public final boolean zze() {
        return false;
    }

    public final boolean zzf() {
        return false;
    }

    public final boolean zzg() {
        return this.zzc;
    }

    public final boolean zzh() {
        return this.zzb;
    }
}
