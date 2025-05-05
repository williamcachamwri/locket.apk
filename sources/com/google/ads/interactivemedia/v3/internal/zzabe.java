package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzabe extends zzwj {
    zzabe() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        try {
            return new AtomicInteger(zzacc.zzb());
        } catch (NumberFormatException e) {
            throw new zzwe((Throwable) e);
        }
    }

    public final /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        zzace.zzi((long) ((AtomicInteger) obj).get());
    }
}
