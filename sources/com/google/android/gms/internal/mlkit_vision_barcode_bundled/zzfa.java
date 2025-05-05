package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzfa extends zzfc {
    private zzfa() {
        super((zzfb) null);
    }

    /* synthetic */ zzfa(zzez zzez) {
        super((zzfb) null);
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        ((zzel) zzhi.zzf(obj, j)).zzb();
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, Object obj2, long j) {
        zzel zzel = (zzel) zzhi.zzf(obj, j);
        zzel zzel2 = (zzel) zzhi.zzf(obj2, j);
        int size = zzel.size();
        int size2 = zzel2.size();
        if (size > 0 && size2 > 0) {
            if (!zzel.zzc()) {
                zzel = zzel.zzd(size2 + size);
            }
            zzel.addAll(zzel2);
        }
        if (size > 0) {
            zzel2 = zzel;
        }
        zzhi.zzs(obj, j, zzel2);
    }
}
