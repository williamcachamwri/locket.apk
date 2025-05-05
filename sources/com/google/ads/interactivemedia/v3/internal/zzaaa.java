package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaaa extends zzwj {
    zzaaa() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() != 9) {
            return Double.valueOf(zzacc.zza());
        }
        zzacc.zzm();
        return null;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        Number number = (Number) obj;
        if (number == null) {
            zzace.zzg();
        } else {
            zzace.zzh(number.doubleValue());
        }
    }
}
