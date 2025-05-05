package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaag extends zzwj {
    zzaag() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() != 9) {
            return new StringBuilder(zzacc.zzh());
        }
        zzacc.zzm();
        return null;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String str;
        StringBuilder sb = (StringBuilder) obj;
        if (sb == null) {
            str = null;
        } else {
            str = sb.toString();
        }
        zzace.zzl(str);
    }
}
