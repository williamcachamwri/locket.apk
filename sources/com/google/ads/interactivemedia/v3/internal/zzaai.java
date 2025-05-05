package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaai extends zzwj {
    zzaai() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() != 9) {
            return new StringBuffer(zzacc.zzh());
        }
        zzacc.zzm();
        return null;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String str;
        StringBuffer stringBuffer = (StringBuffer) obj;
        if (stringBuffer == null) {
            str = null;
        } else {
            str = stringBuffer.toString();
        }
        zzace.zzl(str);
    }
}
