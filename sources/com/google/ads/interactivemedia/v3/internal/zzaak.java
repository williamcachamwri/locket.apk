package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaak extends zzwj {
    zzaak() {
    }

    public static final URI zza(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        try {
            String zzh = zzacc.zzh();
            if (zzh.equals("null")) {
                return null;
            }
            return new URI(zzh);
        } catch (URISyntaxException e) {
            throw new zzvx((Throwable) e);
        }
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        return zza(zzacc);
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String str;
        URI uri = (URI) obj;
        if (uri == null) {
            str = null;
        } else {
            str = uri.toASCIIString();
        }
        zzace.zzl(str);
    }
}
