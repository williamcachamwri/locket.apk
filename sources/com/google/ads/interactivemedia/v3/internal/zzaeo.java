package com.google.ads.interactivemedia.v3.internal;

import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaeo {
    zzaeo() {
    }

    public static final List zza(Object obj, long j) {
        zzaed zzaed = (zzaed) zzago.zzf(obj, j);
        if (zzaed.zzc()) {
            return zzaed;
        }
        int size = zzaed.size();
        zzaed zzd = zzaed.zzd(size == 0 ? 10 : size + size);
        zzago.zzs(obj, j, zzd);
        return zzd;
    }
}
