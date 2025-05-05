package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzcg extends zzbs {
    final /* synthetic */ zzci zza;
    private final Object zzb;
    private int zzc;

    zzcg(zzci zzci, int i) {
        this.zza = zzci;
        this.zzb = zzci.zzg(zzci, i);
        this.zzc = i;
    }

    private final void zza() {
        int i = this.zzc;
        if (i == -1 || i >= this.zza.size() || !zzax.zza(this.zzb, zzci.zzg(this.zza, this.zzc))) {
            this.zzc = this.zza.zzw(this.zzb);
        }
    }

    public final Object getKey() {
        return this.zzb;
    }

    public final Object getValue() {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.get(this.zzb);
        }
        zza();
        int i = this.zzc;
        if (i == -1) {
            return null;
        }
        return zzci.zzj(this.zza, i);
    }

    public final Object setValue(Object obj) {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.put(this.zzb, obj);
        }
        zza();
        int i = this.zzc;
        if (i == -1) {
            this.zza.put(this.zzb, obj);
            return null;
        }
        zzci zzci = this.zza;
        Object zzj = zzci.zzj(zzci, i);
        zzci.zzn(zzci, this.zzc, obj);
        return zzj;
    }
}
