package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.NoSuchElementException;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzga extends zzcu {
    final zzge zza;
    zzcw zzb = zzb();
    final /* synthetic */ zzgg zzc;

    zzga(zzgg zzgg) {
        this.zzc = zzgg;
        this.zza = new zzge(zzgg, (zzgd) null);
    }

    private final zzcw zzb() {
        zzge zzge = this.zza;
        if (zzge.hasNext()) {
            return zzge.next().iterator();
        }
        return null;
    }

    public final boolean hasNext() {
        return this.zzb != null;
    }

    public final byte zza() {
        zzcw zzcw = this.zzb;
        if (zzcw != null) {
            byte zza2 = zzcw.zza();
            if (!this.zzb.hasNext()) {
                this.zzb = zzb();
            }
            return zza2;
        }
        throw new NoSuchElementException();
    }
}
