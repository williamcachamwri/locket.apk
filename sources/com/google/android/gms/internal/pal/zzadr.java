package com.google.android.gms.internal.pal;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzadr extends zzadt {
    private zzadr() {
        super((zzads) null);
    }

    /* synthetic */ zzadr(zzadq zzadq) {
        super((zzads) null);
    }

    /* access modifiers changed from: package-private */
    public final List zza(Object obj, long j) {
        zzadf zzadf = (zzadf) zzafs.zzf(obj, j);
        if (zzadf.zzc()) {
            return zzadf;
        }
        int size = zzadf.size();
        zzadf zzd = zzadf.zzd(size == 0 ? 10 : size + size);
        zzafs.zzs(obj, j, zzd);
        return zzd;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        ((zzadf) zzafs.zzf(obj, j)).zzb();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(Object obj, Object obj2, long j) {
        zzadf zzadf = (zzadf) zzafs.zzf(obj, j);
        zzadf zzadf2 = (zzadf) zzafs.zzf(obj2, j);
        int size = zzadf.size();
        int size2 = zzadf2.size();
        if (size > 0 && size2 > 0) {
            if (!zzadf.zzc()) {
                zzadf = zzadf.zzd(size2 + size);
            }
            zzadf.addAll(zzadf2);
        }
        if (size > 0) {
            zzadf2 = zzadf;
        }
        zzafs.zzs(obj, j, zzadf2);
    }
}
