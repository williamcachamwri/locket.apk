package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzwi extends zzwj {
    final /* synthetic */ zzwj zza;

    zzwi(zzwj zzwj) {
        this.zza = zzwj;
    }

    public final Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() != 9) {
            return this.zza.read(zzacc);
        }
        zzacc.zzm();
        return null;
    }

    public final void write(zzace zzace, Object obj) throws IOException {
        if (obj == null) {
            zzace.zzg();
        } else {
            this.zza.write(zzace, obj);
        }
    }
}
