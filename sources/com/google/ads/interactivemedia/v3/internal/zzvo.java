package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzvo extends zzwj {
    final /* synthetic */ zzwj zza;

    zzvo(zzwj zzwj) {
        this.zza = zzwj;
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        return new AtomicLong(((Number) this.zza.read(zzacc)).longValue());
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        this.zza.write(zzace, Long.valueOf(((AtomicLong) obj).get()));
    }
}
