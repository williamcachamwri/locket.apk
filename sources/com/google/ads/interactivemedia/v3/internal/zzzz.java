package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzzz extends zzwj {
    zzzz() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() != 9) {
            return Float.valueOf((float) zzacc.zza());
        }
        zzacc.zzm();
        return null;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        Number number = (Number) obj;
        if (number == null) {
            zzace.zzg();
            return;
        }
        if (!(number instanceof Float)) {
            number = Float.valueOf(number.floatValue());
        }
        zzace.zzk(number);
    }
}
