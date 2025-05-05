package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.net.InetAddress;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaal extends zzwj {
    zzaal() {
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() != 9) {
            return InetAddress.getByName(zzacc.zzh());
        }
        zzacc.zzm();
        return null;
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String str;
        InetAddress inetAddress = (InetAddress) obj;
        if (inetAddress == null) {
            str = null;
        } else {
            str = inetAddress.getHostAddress();
        }
        zzace.zzl(str);
    }
}
