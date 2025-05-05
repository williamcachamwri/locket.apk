package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.math.BigDecimal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaad extends zzwj {
    zzaad() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        String zzh = zzacc.zzh();
        try {
            return zzxz.zza(zzh);
        } catch (NumberFormatException e) {
            String zzf = zzacc.zzf();
            throw new zzwe("Failed parsing '" + zzh + "' as BigDecimal; at path " + zzf, e);
        }
    }

    public final /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        zzace.zzk((BigDecimal) obj);
    }
}
