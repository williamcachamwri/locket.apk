package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Map;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzgk extends zzgu {
    zzgk(int i) {
        super(i, (zzgt) null);
    }

    public final void zza() {
        if (!zzj()) {
            for (int i = 0; i < zzb(); i++) {
                ((zzds) zzg(i).getKey()).zzg();
            }
            for (Map.Entry key : zzc()) {
                ((zzds) key.getKey()).zzg();
            }
        }
        super.zza();
    }
}
