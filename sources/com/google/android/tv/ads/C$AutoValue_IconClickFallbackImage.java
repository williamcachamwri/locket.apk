package com.google.android.tv.ads;

/* renamed from: com.google.android.tv.ads.$AutoValue_IconClickFallbackImage  reason: invalid class name */
/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
abstract class C$AutoValue_IconClickFallbackImage extends IconClickFallbackImage {
    private final int zza;
    private final int zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;

    C$AutoValue_IconClickFallbackImage(int i, int i2, String str, String str2, String str3) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = str;
        this.zzd = str2;
        this.zze = str3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        r1 = r4.zzd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0045, code lost:
        r1 = r4.zze;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        r1 = r4.zzc;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.tv.ads.IconClickFallbackImage
            r2 = 0
            if (r1 == 0) goto L_0x005c
            com.google.android.tv.ads.IconClickFallbackImage r5 = (com.google.android.tv.ads.IconClickFallbackImage) r5
            int r1 = r4.zza
            int r3 = r5.getWidth()
            if (r1 != r3) goto L_0x005c
            int r1 = r4.zzb
            int r3 = r5.getHeight()
            if (r1 != r3) goto L_0x005c
            java.lang.String r1 = r4.zzc
            if (r1 != 0) goto L_0x0026
            java.lang.String r1 = r5.getAltText()
            if (r1 != 0) goto L_0x005c
            goto L_0x0030
        L_0x0026:
            java.lang.String r3 = r5.getAltText()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005c
        L_0x0030:
            java.lang.String r1 = r4.zzd
            if (r1 != 0) goto L_0x003b
            java.lang.String r1 = r5.getCreativeType()
            if (r1 != 0) goto L_0x005c
            goto L_0x0045
        L_0x003b:
            java.lang.String r3 = r5.getCreativeType()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005c
        L_0x0045:
            java.lang.String r1 = r4.zze
            if (r1 != 0) goto L_0x0050
            java.lang.String r5 = r5.getStaticResourceUri()
            if (r5 != 0) goto L_0x005c
            goto L_0x005b
        L_0x0050:
            java.lang.String r5 = r5.getStaticResourceUri()
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x005b
            goto L_0x005c
        L_0x005b:
            return r0
        L_0x005c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.tv.ads.C$AutoValue_IconClickFallbackImage.equals(java.lang.Object):boolean");
    }

    public String getAltText() {
        return this.zzc;
    }

    public String getCreativeType() {
        return this.zzd;
    }

    public int getHeight() {
        return this.zzb;
    }

    public String getStaticResourceUri() {
        return this.zze;
    }

    public int getWidth() {
        return this.zza;
    }

    public final String toString() {
        int i = this.zza;
        int i2 = this.zzb;
        String str = this.zzc;
        String str2 = this.zzd;
        String str3 = this.zze;
        return "IconClickFallbackImage{width=" + i + ", height=" + i2 + ", altText=" + str + ", creativeType=" + str2 + ", staticResourceUri=" + str3 + "}";
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3 = ((this.zza ^ 1000003) * 1000003) ^ this.zzb;
        String str = this.zzc;
        int i4 = 0;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        int i5 = ((i3 * 1000003) ^ i) * 1000003;
        String str2 = this.zzd;
        if (str2 == null) {
            i2 = 0;
        } else {
            i2 = str2.hashCode();
        }
        int i6 = (i5 ^ i2) * 1000003;
        String str3 = this.zze;
        if (str3 != null) {
            i4 = str3.hashCode();
        }
        return i6 ^ i4;
    }
}
