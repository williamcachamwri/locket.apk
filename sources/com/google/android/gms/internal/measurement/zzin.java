package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzin extends zzip {
    private int zza = 0;
    private final int zzb;
    private final /* synthetic */ zzik zzc;

    public final byte zza() {
        int i = this.zza;
        if (i < this.zzb) {
            this.zza = i + 1;
            return this.zzc.zzb(i);
        }
        throw new NoSuchElementException();
    }

    zzin(zzik zzik) {
        this.zzc = zzik;
        this.zzb = zzik.zzb();
    }

    public final boolean hasNext() {
        return this.zza < this.zzb;
    }
}
