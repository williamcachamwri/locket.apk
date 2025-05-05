package com.google.android.gms.internal.pal;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzabp extends zzabr {
    final /* synthetic */ zzaby zza;
    private int zzb = 0;
    private final int zzc;

    zzabp(zzaby zzaby) {
        this.zza = zzaby;
        this.zzc = zzaby.zzd();
    }

    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    public final byte zza() {
        int i = this.zzb;
        if (i < this.zzc) {
            this.zzb = i + 1;
            return this.zza.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
