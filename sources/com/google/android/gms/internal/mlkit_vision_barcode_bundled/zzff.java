package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzff implements zzfm {
    private final zzfm[] zza;

    zzff(zzfm... zzfmArr) {
        this.zza = zzfmArr;
    }

    public final zzfl zzb(Class cls) {
        zzfm[] zzfmArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzfm zzfm = zzfmArr[i];
            if (zzfm.zzc(cls)) {
                return zzfm.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    public final boolean zzc(Class cls) {
        zzfm[] zzfmArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzfmArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
