package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzfk {
    public static void zza(String str) {
        if (zze(2)) {
            SentryLogcatAdapter.e("IMASDK", str);
        }
    }

    public static void zzb(String str, Throwable th) {
        if (zze(2)) {
            SentryLogcatAdapter.e("IMASDK", str, th);
        }
    }

    public static void zzc(String str) {
        if (zze(1)) {
            Log.i("IMASDK", str);
        }
    }

    public static void zzd(String str) {
        if (zze(2)) {
            SentryLogcatAdapter.w("IMASDK", str);
        }
    }

    private static boolean zze(int i) {
        return i + -1 > 0;
    }
}
