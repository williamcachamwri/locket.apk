package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzabf extends zzwj {
    zzabf() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        return new AtomicBoolean(zzacc.zzq());
    }

    public final /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        zzace.zzm(((AtomicBoolean) obj).get());
    }
}
