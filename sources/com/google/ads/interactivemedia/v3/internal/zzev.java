package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.net.Uri;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzev implements zzex {
    private final String zza;

    public zzev(String str) {
        this.zza = str;
    }

    public final boolean zza(zzew zzew, Context context, boolean z, boolean z2) {
        String str;
        String host;
        if (z || !z2 || (str = this.zza) == null || !str.contains("GOOGLE_INSTREAM_VIDEO_NONCE")) {
            return false;
        }
        String str2 = this.zza;
        List<String> list = zzew.zzc;
        if (str2 == null || list == null || (host = Uri.parse(str2).getHost()) == null) {
            return true;
        }
        if (host.startsWith("www.")) {
            host = host.substring(4);
        }
        for (String equals : list) {
            if (Objects.equals(host, equals)) {
                return false;
            }
        }
        return true;
    }
}
