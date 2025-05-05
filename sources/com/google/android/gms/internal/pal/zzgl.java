package com.google.android.gms.internal.pal;

import android.os.StrictMode;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzgl {
    /* JADX INFO: finally extract failed */
    public static Object zza(zzis zzis) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskReads().permitDiskWrites().build());
            Object zza = zzis.zza();
            StrictMode.setThreadPolicy(threadPolicy);
            return zza;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicy);
            throw th;
        }
    }
}
