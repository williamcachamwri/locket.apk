package com.google.android.gms.internal.pal;

import android.os.Handler;
import com.google.ads.interactivemedia.pal.NonceLoaderException;
import io.sentry.android.core.SentryLogcatAdapter;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzbc extends zzbg {
    public zzbc(Handler handler, ExecutorService executorService) {
        super(handler, executorService, zzagc.zzb(2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
        if (r1 != null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        java.lang.Throwable.class.getDeclaredMethod("addSuppressed", new java.lang.Class[]{java.lang.Throwable.class}).invoke(r6, new java.lang.Object[]{r1});
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0080, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0081, code lost:
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0083, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0086, code lost:
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00aa, code lost:
        r3.disconnect();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0065 */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0080 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0014] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.pal.zzkm zzf() throws com.google.ads.interactivemedia.pal.NonceLoaderException {
        /*
            java.lang.String r0 = "NonceGenerator"
            java.lang.String r1 = "Failed keystore response: "
            java.lang.String r2 = "Failed to read keyset handle: "
            r3 = 0
            java.net.URL r4 = new java.net.URL     // Catch:{ IOException -> 0x008c, GeneralSecurityException -> 0x008a }
            java.lang.String r5 = "https://imasdk.googleapis.com/pal/key/public.json"
            r4.<init>(r5)     // Catch:{ IOException -> 0x008c, GeneralSecurityException -> 0x008a }
            java.net.URLConnection r4 = r4.openConnection()     // Catch:{ IOException -> 0x008c, GeneralSecurityException -> 0x008a }
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch:{ IOException -> 0x008c, GeneralSecurityException -> 0x008a }
            int r3 = com.google.ads.interactivemedia.pal.zzat.zzb     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            r4.setConnectTimeout(r3)     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            int r3 = com.google.ads.interactivemedia.pal.zzat.zzc     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            r4.setReadTimeout(r3)     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            r3 = 1
            r4.setDoInput(r3)     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            r5 = 0
            r4.setUseCaches(r5)     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            r4.connect()     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            int r6 = r4.getResponseCode()     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 != r7) goto L_0x0066
            java.io.InputStream r1 = r4.getInputStream()     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            com.google.android.gms.internal.pal.zzkn r6 = com.google.android.gms.internal.pal.zzjz.zza(r1)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.pal.zzkm r3 = com.google.android.gms.internal.pal.zzkm.zzb(r6)     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x0042
            r1.close()     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
        L_0x0042:
            if (r4 == 0) goto L_0x0047
            r4.disconnect()
        L_0x0047:
            return r3
        L_0x0048:
            r6 = move-exception
            if (r1 == 0) goto L_0x0065
            r1.close()     // Catch:{ all -> 0x004f }
            goto L_0x0065
        L_0x004f:
            r1 = move-exception
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch:{ Exception -> 0x0065, all -> 0x0080 }
            java.lang.Class<java.lang.Throwable> r7 = java.lang.Throwable.class
            r3[r5] = r7     // Catch:{ Exception -> 0x0065, all -> 0x0080 }
            java.lang.Class<java.lang.Throwable> r5 = java.lang.Throwable.class
            java.lang.String r7 = "addSuppressed"
            java.lang.reflect.Method r3 = r5.getDeclaredMethod(r7, r3)     // Catch:{ Exception -> 0x0065, all -> 0x0080 }
            java.lang.Object[] r1 = new java.lang.Object[]{r1}     // Catch:{ Exception -> 0x0065, all -> 0x0080 }
            r3.invoke(r6, r1)     // Catch:{ Exception -> 0x0065, all -> 0x0080 }
        L_0x0065:
            throw r6     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
        L_0x0066:
            int r3 = r4.getResponseCode()     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            r5.append(r3)     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            java.lang.String r1 = r5.toString()     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            r1 = 202(0xca, float:2.83E-43)
            com.google.ads.interactivemedia.pal.NonceLoaderException r1 = com.google.ads.interactivemedia.pal.NonceLoaderException.zzb(r1)     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
            throw r1     // Catch:{ IOException -> 0x0085, GeneralSecurityException -> 0x0083, all -> 0x0080 }
        L_0x0080:
            r0 = move-exception
            r3 = r4
            goto L_0x00a8
        L_0x0083:
            r1 = move-exception
            goto L_0x0086
        L_0x0085:
            r1 = move-exception
        L_0x0086:
            r3 = r4
            goto L_0x008d
        L_0x0088:
            r0 = move-exception
            goto L_0x00a8
        L_0x008a:
            r1 = move-exception
            goto L_0x008d
        L_0x008c:
            r1 = move-exception
        L_0x008d:
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x0088 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0088 }
            r5.<init>(r2)     // Catch:{ all -> 0x0088 }
            r5.append(r4)     // Catch:{ all -> 0x0088 }
            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x0088 }
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r2)     // Catch:{ all -> 0x0088 }
            com.google.ads.interactivemedia.pal.NonceLoaderException r0 = new com.google.ads.interactivemedia.pal.NonceLoaderException     // Catch:{ all -> 0x0088 }
            r2 = 203(0xcb, float:2.84E-43)
            r0.<init>(r2, r1)     // Catch:{ all -> 0x0088 }
            throw r0     // Catch:{ all -> 0x0088 }
        L_0x00a8:
            if (r3 == 0) goto L_0x00ad
            r3.disconnect()
        L_0x00ad:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzbc.zzf():com.google.android.gms.internal.pal.zzkm");
    }

    /* access modifiers changed from: package-private */
    public final zzil zza() throws NonceLoaderException {
        try {
            zznn.zza();
            return zzil.zzf(new zzbb((zzjy) zzf().zzc(zzjy.class)));
        } catch (GeneralSecurityException e) {
            SentryLogcatAdapter.e("NonceGenerator", "Can't access the cryptography library.", e);
            throw new NonceLoaderException(201, e);
        }
    }
}
