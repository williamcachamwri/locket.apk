package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaab extends zzwj {
    zzaab() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        String zzh = zzacc.zzh();
        if (zzh.length() == 1) {
            return Character.valueOf(zzh.charAt(0));
        }
        String zzf = zzacc.zzf();
        throw new zzwe("Expecting character, got: " + zzh + "; at " + zzf);
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String str;
        Character ch = (Character) obj;
        if (ch == null) {
            str = null;
        } else {
            str = ch.toString();
        }
        zzace.zzl(str);
    }
}
