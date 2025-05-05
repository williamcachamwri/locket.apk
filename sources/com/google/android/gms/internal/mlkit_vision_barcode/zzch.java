package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzch extends AbstractCollection {
    final /* synthetic */ zzci zza;

    zzch(zzci zzci) {
        this.zza = zzci;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final Iterator iterator() {
        zzci zzci = this.zza;
        Map zzl = zzci.zzl();
        if (zzl != null) {
            return zzl.values().iterator();
        }
        return new zzcb(zzci);
    }

    public final int size() {
        return this.zza.size();
    }
}
