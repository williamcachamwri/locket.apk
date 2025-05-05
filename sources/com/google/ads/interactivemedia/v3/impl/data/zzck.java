package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzacc;
import com.google.ads.interactivemedia.v3.internal.zzace;
import com.google.ads.interactivemedia.v3.internal.zzwj;
import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzck extends zzwj {
    zzck() {
    }

    public zzcl read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() != 9) {
            return new zzcl(zzacc.zzh());
        }
        zzacc.zzm();
        return null;
    }

    public void write(zzace zzace, zzcl zzcl) throws IOException {
        if (zzcl == null) {
            zzace.zzg();
        } else {
            zzace.zzl(zzcl.getName());
        }
    }
}
