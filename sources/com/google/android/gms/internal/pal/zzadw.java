package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzadw implements zzaed {
    private final zzaed[] zza;

    zzadw(zzaed... zzaedArr) {
        this.zza = zzaedArr;
    }

    public final zzaec zzb(Class cls) {
        zzaed[] zzaedArr = this.zza;
        for (int i = 0; i < 2; i++) {
            zzaed zzaed = zzaedArr[i];
            if (zzaed.zzc(cls)) {
                return zzaed.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    public final boolean zzc(Class cls) {
        zzaed[] zzaedArr = this.zza;
        for (int i = 0; i < 2; i++) {
            if (zzaedArr[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
