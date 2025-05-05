package com.google.ads.interactivemedia.v3.internal;

import java.io.File;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzov {
    private final zzlf zza;
    private final File zzb;
    private final File zzc;
    private final File zzd;
    private byte[] zze;

    public zzov(zzlf zzlf, File file, File file2, File file3) {
        this.zza = zzlf;
        this.zzb = file;
        this.zzc = file3;
        this.zzd = file2;
    }

    public final zzlf zza() {
        return this.zza;
    }

    public final File zzb() {
        return this.zzc;
    }

    public final File zzc() {
        return this.zzb;
    }

    public final boolean zzd(long j) {
        return this.zza.zzb() - (System.currentTimeMillis() / 1000) < 3600;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0031 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zze() {
        /*
            r4 = this;
            byte[] r0 = r4.zze
            r1 = 0
            if (r0 != 0) goto L_0x002d
            java.io.File r0 = r4.zzd
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0026, all -> 0x0021 }
            r2.<init>(r0)     // Catch:{ IOException -> 0x0026, all -> 0x0021 }
            java.io.FileInputStream r0 = io.sentry.instrumentation.file.SentryFileInputStream.Factory.create((java.io.FileInputStream) r2, (java.io.File) r0)     // Catch:{ IOException -> 0x0026, all -> 0x0021 }
            com.google.ads.interactivemedia.v3.internal.zzacw r2 = com.google.ads.interactivemedia.v3.internal.zzacw.zzq(r0)     // Catch:{ IOException -> 0x0027, all -> 0x001c }
            byte[] r2 = r2.zzt()     // Catch:{ IOException -> 0x0027, all -> 0x001c }
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r0)
            goto L_0x002b
        L_0x001c:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x0022
        L_0x0021:
            r0 = move-exception
        L_0x0022:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r1)
            throw r0
        L_0x0026:
            r0 = r1
        L_0x0027:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r0)
            r2 = r1
        L_0x002b:
            r4.zze = r2
        L_0x002d:
            byte[] r0 = r4.zze
            if (r0 != 0) goto L_0x0032
            return r1
        L_0x0032:
            int r1 = r0.length
            byte[] r0 = java.util.Arrays.copyOf(r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzov.zze():byte[]");
    }
}
