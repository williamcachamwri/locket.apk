package com.google.ads.interactivemedia.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzg extends zzq {
    private final String zza;
    private final String zzb;
    private final String zzc;

    /* synthetic */ zzg(String str, String str2, String str3, zzf zzf) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzq) {
            zzq zzq = (zzq) obj;
            return this.zza.equals(zzq.zzb()) && this.zzb.equals(zzq.zzc()) && this.zzc.equals(zzq.zza());
        }
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode();
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        String str3 = this.zzc;
        return "Gen204LoggerData{palVersion=" + str + ", sdkVersion=" + str2 + ", correlator=" + str3 + "}";
    }

    /* access modifiers changed from: package-private */
    public final String zza() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final String zzb() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final String zzc() {
        return this.zzb;
    }
}
