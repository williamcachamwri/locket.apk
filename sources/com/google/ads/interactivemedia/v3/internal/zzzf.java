package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzzf extends zzwj {
    private static final zzwk zza = zzb(2);
    private final int zzb;

    private zzzf(int i) {
        this.zzb = i;
    }

    public static zzwk zza(int i) {
        return i == 2 ? zza : zzb(i);
    }

    private static zzwk zzb(int i) {
        return new zzze(new zzzf(i));
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        int zzr = zzacc.zzr();
        int i = zzr - 1;
        if (i == 5 || i == 6) {
            return zzwh.zza(this.zzb, zzacc);
        }
        if (i == 8) {
            zzacc.zzm();
            return null;
        }
        throw new zzwe("Expecting number, got: " + zzacd.zza(zzr) + "; at path " + zzacc.zze());
    }

    public final /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        zzace.zzk((Number) obj);
    }
}
