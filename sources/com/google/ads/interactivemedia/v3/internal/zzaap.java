package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaap extends zzwj {
    zzaap() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        String str = null;
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(zzacc.zzh(), "_");
        String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
        String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
        if (stringTokenizer.hasMoreElements()) {
            str = stringTokenizer.nextToken();
        }
        if (nextToken2 == null && str == null) {
            return new Locale(nextToken);
        }
        if (str == null) {
            return new Locale(nextToken, nextToken2);
        }
        return new Locale(nextToken, nextToken2, str);
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String str;
        Locale locale = (Locale) obj;
        if (locale == null) {
            str = null;
        } else {
            str = locale.toString();
        }
        zzace.zzl(str);
    }
}
