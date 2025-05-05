package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.net.URL;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaaj extends zzwj {
    zzaaj() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        String zzh = zzacc.zzh();
        if (!zzh.equals("null")) {
            return new URL(zzh);
        }
        return null;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String str;
        URL url = (URL) obj;
        if (url == null) {
            str = null;
        } else {
            str = url.toExternalForm();
        }
        zzace.zzl(str);
    }
}
