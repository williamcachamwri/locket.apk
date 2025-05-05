package com.google.android.gms.internal.atv_ads_framework;

import androidx.media3.common.MimeTypes;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzby {
    private static final zzbi zza = zzbi.zzl("http", "https", "mailto", "ftp");
    private static final zzbi zzb = zzbi.zzm("audio/3gpp2", MimeTypes.AUDIO_AMR_NB, "audio/aac", MimeTypes.AUDIO_MIDI, "audio/mp3", MimeTypes.AUDIO_MP4, MimeTypes.AUDIO_MPEG, "audio/oga", MimeTypes.AUDIO_OGG, MimeTypes.AUDIO_OPUS, "audio/x-m4a", MimeTypes.AUDIO_MATROSKA, "audio/x-wav", MimeTypes.AUDIO_WAV, MimeTypes.AUDIO_WEBM, MimeTypes.IMAGE_BMP, "image/gif", "image/jpeg", "image/jpg", MimeTypes.IMAGE_PNG, "image/svg+xml", "image/tiff", "image/webp", "image/x-icon", MimeTypes.VIDEO_MPEG, MimeTypes.VIDEO_MP4, MimeTypes.VIDEO_OGG, MimeTypes.VIDEO_WEBM, MimeTypes.VIDEO_MATROSKA);
    private static final zzbi zzc = zzbi.zzj();

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a6, code lost:
        if (r0 >= r7.length()) goto L_0x00fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ac, code lost:
        if (r7.charAt(r0) != '=') goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ae, code lost:
        r0 = r0 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zza(java.lang.String r6, java.lang.String r7) {
        /*
            com.google.android.gms.internal.atv_ads_framework.zzbi r7 = zzc
            java.lang.String r0 = com.google.android.gms.internal.atv_ads_framework.zzaf.zza(r6)
            com.google.android.gms.internal.atv_ads_framework.zzbi r1 = zza
            com.google.android.gms.internal.atv_ads_framework.zzbu r1 = r1.iterator()
        L_0x000c:
            boolean r2 = r1.hasNext()
            java.lang.String r3 = ":"
            if (r2 == 0) goto L_0x002a
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r2 = r2.concat(r3)
            boolean r2 = r0.startsWith(r2)
            if (r2 == 0) goto L_0x000c
            goto L_0x00fe
        L_0x002a:
            java.lang.String r1 = "data:"
            boolean r2 = r0.startsWith(r1)
            r4 = 47
            if (r2 == 0) goto L_0x00b1
            java.lang.String r7 = com.google.android.gms.internal.atv_ads_framework.zzaf.zza(r6)
            boolean r0 = r7.startsWith(r1)
            if (r0 != 0) goto L_0x0040
            goto L_0x00fc
        L_0x0040:
            int r0 = r7.length()
            r1 = 5
            if (r0 <= r1) goto L_0x00fc
            r0 = r1
        L_0x0048:
            int r2 = r7.length()
            if (r0 >= r2) goto L_0x005e
            char r2 = r7.charAt(r0)
            r3 = 59
            if (r2 == r3) goto L_0x005e
            r3 = 44
            if (r2 != r3) goto L_0x005b
            goto L_0x005e
        L_0x005b:
            int r0 = r0 + 1
            goto L_0x0048
        L_0x005e:
            com.google.android.gms.internal.atv_ads_framework.zzbi r2 = zzb
            java.lang.String r1 = r7.substring(r1, r0)
            boolean r1 = r2.contains(r1)
            if (r1 == 0) goto L_0x00fc
            java.lang.String r1 = ";base64,"
            boolean r1 = r7.startsWith(r1, r0)
            if (r1 == 0) goto L_0x00fc
            int r0 = r0 + 8
            int r1 = r7.length()
            if (r0 >= r1) goto L_0x00fc
        L_0x007a:
            int r1 = r7.length()
            r2 = 61
            if (r0 >= r1) goto L_0x00a2
            char r1 = r7.charAt(r0)
            if (r1 != r2) goto L_0x0089
            goto L_0x00a2
        L_0x0089:
            r2 = 97
            if (r1 < r2) goto L_0x0091
            r2 = 122(0x7a, float:1.71E-43)
            if (r1 <= r2) goto L_0x009f
        L_0x0091:
            r2 = 48
            if (r1 < r2) goto L_0x0099
            r2 = 57
            if (r1 <= r2) goto L_0x009f
        L_0x0099:
            r2 = 43
            if (r1 == r2) goto L_0x009f
            if (r1 != r4) goto L_0x00fc
        L_0x009f:
            int r0 = r0 + 1
            goto L_0x007a
        L_0x00a2:
            int r1 = r7.length()
            if (r0 >= r1) goto L_0x00fe
            char r1 = r7.charAt(r0)
            if (r1 != r2) goto L_0x00fc
            int r0 = r0 + 1
            goto L_0x00a2
        L_0x00b1:
            java.util.Iterator r7 = r7.iterator()
        L_0x00b5:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x00e0
            java.lang.Object r1 = r7.next()
            com.google.android.gms.internal.atv_ads_framework.zzbx r1 = (com.google.android.gms.internal.atv_ads_framework.zzbx) r1
            java.lang.String r1 = r1.name()
            java.lang.String r1 = com.google.android.gms.internal.atv_ads_framework.zzaf.zza(r1)
            r2 = 95
            r5 = 45
            java.lang.String r1 = r1.replace(r2, r5)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r1 = r1.concat(r3)
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x00b5
            goto L_0x00fe
        L_0x00e0:
            r7 = 0
        L_0x00e1:
            int r0 = r6.length()
            if (r7 >= r0) goto L_0x00fe
            char r0 = r6.charAt(r7)
            r1 = 35
            if (r0 == r1) goto L_0x00fe
            if (r0 == r4) goto L_0x00fe
            r1 = 58
            if (r0 == r1) goto L_0x00fc
            r1 = 63
            if (r0 == r1) goto L_0x00fe
            int r7 = r7 + 1
            goto L_0x00e1
        L_0x00fc:
            java.lang.String r6 = "about:invalid#zTvAdsFrameworkz"
        L_0x00fe:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzby.zza(java.lang.String, java.lang.String):java.lang.String");
    }
}
