package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzvn extends zzwj {
    zzvn(zzvr zzvr) {
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
        float floatValue = number.floatValue();
        zzvr.zzg((double) floatValue);
        if (!(number instanceof Float)) {
            number = Float.valueOf(floatValue);
        }
        zzace.zzk(number);
    }
}
