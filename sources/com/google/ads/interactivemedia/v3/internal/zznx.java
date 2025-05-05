package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zznx extends zznu {
    private String zza;
    private boolean zzb;
    private boolean zzc;
    private long zzd;
    private long zze;
    private byte zzf;

    zznx() {
    }

    public final zznu zza(String str) {
        if (str != null) {
            this.zza = str;
            return this;
        }
        throw new NullPointerException("Null clientVersion");
    }

    public final zznu zzb(boolean z) {
        this.zzf = (byte) (this.zzf | 16);
        return this;
    }

    public final zznu zzc(boolean z) {
        this.zzf = (byte) (this.zzf | 4);
        return this;
    }

    public final zznu zzd(boolean z) {
        this.zzc = true;
        this.zzf = (byte) (this.zzf | 2);
        return this;
    }

    public final zznu zze(long j) {
        this.zze = 300;
        this.zzf = (byte) (this.zzf | 32);
        return this;
    }

    public final zznu zzf(long j) {
        this.zzd = 100;
        this.zzf = (byte) (this.zzf | 8);
        return this;
    }

    public final zznu zzg(boolean z) {
        this.zzb = z;
        this.zzf = (byte) (this.zzf | 1);
        return this;
    }

    public final zznv zzh() {
        String str;
        if (this.zzf == 63 && (str = this.zza) != null) {
            return new zznz(str, this.zzb, this.zzc, false, this.zzd, false, this.zze, (zzny) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" clientVersion");
        }
        if ((this.zzf & 1) == 0) {
            sb.append(" shouldGetAdvertisingId");
        }
        if ((this.zzf & 2) == 0) {
            sb.append(" isGooglePlayServicesAvailable");
        }
        if ((this.zzf & 4) == 0) {
            sb.append(" enableQuerySignalsTimeout");
        }
        if ((this.zzf & 8) == 0) {
            sb.append(" querySignalsTimeoutMs");
        }
        if ((this.zzf & 16) == 0) {
            sb.append(" enableQuerySignalsCache");
        }
        if ((this.zzf & 32) == 0) {
            sb.append(" querySignalsCacheTtlSeconds");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
