package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzjh implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ zzjj zzb;

    zzjh(zzjj zzjj, int i, boolean z) {
        this.zza = i;
        this.zzb = zzjj;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000c */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r4 = this;
            int r0 = r4.zza
            com.google.ads.interactivemedia.v3.internal.zzjj r1 = r4.zzb
            if (r0 <= 0) goto L_0x000c
            int r0 = r0 * 1000
            long r2 = (long) r0
            java.lang.Thread.sleep(r2)     // Catch:{ InterruptedException -> 0x000c }
        L_0x000c:
            android.content.Context r0 = r1.zza     // Catch:{ all -> 0x002e }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x002e }
            android.content.Context r2 = r1.zza     // Catch:{ all -> 0x002e }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ all -> 0x002e }
            r3 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r2, r3)     // Catch:{ all -> 0x002e }
            android.content.Context r1 = r1.zza     // Catch:{ all -> 0x002e }
            java.lang.String r2 = r1.getPackageName()     // Catch:{ all -> 0x002e }
            int r0 = r0.versionCode     // Catch:{ all -> 0x002e }
            java.lang.String r0 = java.lang.Integer.toString(r0)     // Catch:{ all -> 0x002e }
            com.google.ads.interactivemedia.v3.internal.zzbp r0 = com.google.ads.interactivemedia.v3.internal.zzob.zza(r1, r2, r0)     // Catch:{ all -> 0x002e }
            goto L_0x002f
        L_0x002e:
            r0 = 0
        L_0x002f:
            com.google.ads.interactivemedia.v3.internal.zzjj r1 = r4.zzb
            r1.zzm = r0
            int r1 = r4.zza
            r2 = 4
            if (r1 >= r2) goto L_0x0076
            if (r0 != 0) goto L_0x003c
            goto L_0x006d
        L_0x003c:
            boolean r1 = r0.zzaj()
            if (r1 == 0) goto L_0x006d
            java.lang.String r1 = r0.zzg()
            java.lang.String r2 = "0000000000000000000000000000000000000000000000000000000000000000"
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x006d
            boolean r1 = r0.zzak()
            if (r1 == 0) goto L_0x006d
            com.google.ads.interactivemedia.v3.internal.zzcb r1 = r0.zze()
            boolean r1 = r1.zzd()
            if (r1 == 0) goto L_0x006d
            com.google.ads.interactivemedia.v3.internal.zzcb r0 = r0.zze()
            long r0 = r0.zza()
            r2 = -2
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x006d
            goto L_0x0076
        L_0x006d:
            com.google.ads.interactivemedia.v3.internal.zzjj r0 = r4.zzb
            int r1 = r4.zza
            r2 = 1
            int r1 = r1 + r2
            r0.zzo(r1, r2)
        L_0x0076:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzjh.run():void");
    }
}
