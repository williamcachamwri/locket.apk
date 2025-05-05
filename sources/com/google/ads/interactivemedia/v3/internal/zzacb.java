package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzacb extends zzxp {
    zzacb() {
    }

    public final void zza(zzacc zzacc) throws IOException {
        if (zzacc instanceof zzyz) {
            ((zzyz) zzacc).zzn();
            return;
        }
        int i = zzacc.zza;
        if (i == 0) {
            i = zzacc.zzs();
        }
        if (i == 13) {
            zzacc.zza = 9;
        } else if (i == 12) {
            zzacc.zza = 8;
        } else if (i == 14) {
            zzacc.zza = 10;
        } else {
            throw zzacc.zzz("a name");
        }
    }
}
