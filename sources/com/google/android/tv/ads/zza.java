package com.google.android.tv.ads;

import com.google.android.tv.ads.IconClickFallbackImage;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zza extends IconClickFallbackImage.Builder {
    private int zza;
    private int zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private byte zzf;

    zza() {
    }

    public final IconClickFallbackImage build() {
        if (this.zzf == 3) {
            return new zzd(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }
        StringBuilder sb = new StringBuilder();
        if ((this.zzf & 1) == 0) {
            sb.append(" width");
        }
        if ((this.zzf & 2) == 0) {
            sb.append(" height");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public final IconClickFallbackImage.Builder setAltText(String str) {
        this.zzc = str;
        return this;
    }

    public final IconClickFallbackImage.Builder setCreativeType(String str) {
        this.zzd = str;
        return this;
    }

    public final IconClickFallbackImage.Builder setHeight(int i) {
        this.zzb = i;
        this.zzf = (byte) (this.zzf | 2);
        return this;
    }

    public final IconClickFallbackImage.Builder setStaticResourceUri(String str) {
        this.zze = str;
        return this;
    }

    public final IconClickFallbackImage.Builder setWidth(int i) {
        this.zza = i;
        this.zzf = (byte) (this.zzf | 1);
        return this;
    }
}
