package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaz extends zzaw {
    private final String zza;
    private final String zzb;
    private final boolean zzc;

    zzaz(String str, String str2, boolean z) {
        if (str != null) {
            this.zza = str;
            this.zzb = str2;
            this.zzc = z;
            return;
        }
        throw new NullPointerException("Null advertisingId");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzaw) {
            zzaw zzaw = (zzaw) obj;
            return this.zza.equals(zzaw.zza()) && this.zzb.equals(zzaw.zzb()) && this.zzc == zzaw.zzc();
        }
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ (true != this.zzc ? 1237 : 1231);
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        boolean z = this.zzc;
        return "AdvertisingIdInfo{advertisingId=" + str + ", advertisingIdType=" + str2 + ", isLimitAdTracking=" + z + "}";
    }

    public final String zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zzc;
    }
}
