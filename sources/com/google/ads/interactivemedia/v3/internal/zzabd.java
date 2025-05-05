package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzabd extends zzwj {
    zzabd() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        try {
            return Integer.valueOf(zzacc.zzb());
        } catch (NumberFormatException e) {
            throw new zzwe((Throwable) e);
        }
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        Number number = (Number) obj;
        if (number == null) {
            zzace.zzg();
        } else {
            zzace.zzi((long) number.intValue());
        }
    }
}
