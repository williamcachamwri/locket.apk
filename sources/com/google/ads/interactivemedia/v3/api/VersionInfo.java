package com.google.ads.interactivemedia.v3.api;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class VersionInfo {
    private final int zza;
    private final int zzb;
    private final int zzc;

    public VersionInfo(int i, int i2, int i3) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
    }

    public int getMajorVersion() {
        return this.zza;
    }

    public int getMicroVersion() {
        return this.zzc;
    }

    public int getMinorVersion() {
        return this.zzb;
    }

    public String toString() {
        return this.zza + "." + this.zzb + "." + this.zzc;
    }
}
