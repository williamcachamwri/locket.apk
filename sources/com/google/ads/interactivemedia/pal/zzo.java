package com.google.ads.interactivemedia.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzo extends Thread {
    final /* synthetic */ String zza;

    zzo(zzs zzs, String str) {
        this.zza = str;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:16|17) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0053 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r5 = this;
            java.lang.String r0 = r5.zza
            com.google.android.gms.internal.pal.zzic r1 = com.google.android.gms.internal.pal.zzhw.zza()     // Catch:{ IOException -> 0x0054 }
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ all -> 0x0037 }
            r3.<init>(r0)     // Catch:{ all -> 0x0037 }
            r0 = 26624(0x6800, float:3.7308E-41)
            java.net.URLConnection r0 = r1.zzb(r3, r0)     // Catch:{ all -> 0x0037 }
            int r3 = com.google.ads.interactivemedia.pal.zzat.zzb     // Catch:{ all -> 0x0037 }
            r4 = r0
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ all -> 0x0037 }
            r4.setConnectTimeout(r3)     // Catch:{ all -> 0x0037 }
            int r3 = com.google.ads.interactivemedia.pal.zzat.zzc     // Catch:{ all -> 0x0037 }
            r4 = r0
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ all -> 0x0037 }
            r4.setReadTimeout(r3)     // Catch:{ all -> 0x0037 }
            r3 = r0
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ all -> 0x0037 }
            r3.setDoInput(r2)     // Catch:{ all -> 0x0037 }
            r3 = r0
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ all -> 0x0037 }
            r3.setUseCaches(r2)     // Catch:{ all -> 0x0037 }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ all -> 0x0037 }
            r0.getResponseCode()     // Catch:{ all -> 0x0037 }
            r1.close()     // Catch:{ IOException -> 0x0054 }
            return
        L_0x0037:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x003c }
            goto L_0x0053
        L_0x003c:
            r1 = move-exception
            r3 = 1
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x0053 }
            java.lang.Class<java.lang.Throwable> r4 = java.lang.Throwable.class
            r3[r2] = r4     // Catch:{ Exception -> 0x0053 }
            java.lang.Class<java.lang.Throwable> r2 = java.lang.Throwable.class
            java.lang.String r4 = "addSuppressed"
            java.lang.reflect.Method r2 = r2.getDeclaredMethod(r4, r3)     // Catch:{ Exception -> 0x0053 }
            java.lang.Object[] r1 = new java.lang.Object[]{r1}     // Catch:{ Exception -> 0x0053 }
            r2.invoke(r0, r1)     // Catch:{ Exception -> 0x0053 }
        L_0x0053:
            throw r0     // Catch:{ IOException -> 0x0054 }
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.pal.zzo.run():void");
    }
}
