package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
abstract class zzdt implements Iterator {
    final Iterator zza;

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final Object next() {
        return zza(this.zza.next());
    }

    public final void remove() {
        this.zza.remove();
    }

    /* access modifiers changed from: package-private */
    public abstract Object zza(Object obj);

    zzdt(Iterator it) {
        it.getClass();
        Iterator it2 = it;
        this.zza = it;
    }
}
