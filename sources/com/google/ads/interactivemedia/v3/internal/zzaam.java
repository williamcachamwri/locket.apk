package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.UUID;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaam extends zzwj {
    zzaam() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        String zzh = zzacc.zzh();
        try {
            return UUID.fromString(zzh);
        } catch (IllegalArgumentException e) {
            String zzf = zzacc.zzf();
            throw new zzwe("Failed parsing '" + zzh + "' as UUID; at path " + zzf, e);
        }
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String str;
        UUID uuid = (UUID) obj;
        if (uuid == null) {
            str = null;
        } else {
            str = uuid.toString();
        }
        zzace.zzl(str);
    }
}
