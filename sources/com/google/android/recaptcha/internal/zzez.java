package com.google.android.recaptcha.internal;

import com.google.common.net.HttpHeaders;
import java.net.HttpURLConnection;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzez {
    private final zzfo zza;

    public zzez() {
        this((zzfo) null, 1, (DefaultConstructorMarker) null);
    }

    public /* synthetic */ zzez(zzfo zzfo, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this.zza = new zzfo();
    }

    public final zzey zza(String str) {
        try {
            HttpURLConnection zza2 = this.zza.zza(str);
            zza2.setRequestMethod("POST");
            zza2.setDoOutput(true);
            zza2.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-protobuffer");
            return new zzey(zza2);
        } catch (zzbf e) {
            throw e;
        } catch (Exception e2) {
            throw new zzbf(zzbd.zzc, zzbc.zzai, e2.getMessage());
        }
    }
}
