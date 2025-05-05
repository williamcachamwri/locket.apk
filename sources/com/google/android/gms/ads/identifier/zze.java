package com.google.android.gms.ads.identifier;

import com.google.android.gms.internal.ads_identifier.zzh;
import com.google.android.gms.internal.ads_identifier.zzk;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: com.google.android.gms:play-services-ads-identifier@@18.2.0 */
public final class zze {
    public static final void zza(String str) {
        HttpURLConnection httpURLConnection;
        try {
            zzk.zzb(263);
            URL url = new URL(str);
            int i = zzh.zzb;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                SentryLogcatAdapter.w("HttpUrlPinger", "Received non-success response code " + responseCode + " from pinging URL: " + str);
            }
            httpURLConnection.disconnect();
            zzk.zza();
        } catch (IndexOutOfBoundsException e) {
            String message = e.getMessage();
            SentryLogcatAdapter.w("HttpUrlPinger", "Error while parsing ping URL: " + str + ". " + message, e);
            zzk.zza();
        } catch (IOException | RuntimeException e2) {
            try {
                String message2 = e2.getMessage();
                SentryLogcatAdapter.w("HttpUrlPinger", "Error while pinging URL: " + str + ". " + message2, e2);
            } finally {
                zzk.zza();
            }
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
    }
}
