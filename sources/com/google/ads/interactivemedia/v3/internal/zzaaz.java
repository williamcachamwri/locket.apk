package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaaz extends zzwj {
    zzaaz() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        int zzr = zzacc.zzr();
        if (zzr == 9) {
            zzacc.zzm();
            return null;
        } else if (zzr == 6) {
            return Boolean.valueOf(Boolean.parseBoolean(zzacc.zzh()));
        } else {
            return Boolean.valueOf(zzacc.zzq());
        }
    }

    public final /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        zzace.zzj((Boolean) obj);
    }
}
