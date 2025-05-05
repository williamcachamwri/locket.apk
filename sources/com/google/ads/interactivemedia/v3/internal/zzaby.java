package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaby extends zzwj {
    static final zzwk zza = new zzabw();
    private final zzwj zzb;

    /* synthetic */ zzaby(zzwj zzwj, zzabx zzabx) {
        this.zzb = zzwj;
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        Date date = (Date) this.zzb.read(zzacc);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    public final /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        this.zzb.write(zzace, (Timestamp) obj);
    }
}
