package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Currency;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaan extends zzwj {
    zzaan() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        String zzh = zzacc.zzh();
        try {
            return Currency.getInstance(zzh);
        } catch (IllegalArgumentException e) {
            String zzf = zzacc.zzf();
            throw new zzwe("Failed parsing '" + zzh + "' as Currency; at path " + zzf, e);
        }
    }

    public final /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        zzace.zzl(((Currency) obj).getCurrencyCode());
    }
}
