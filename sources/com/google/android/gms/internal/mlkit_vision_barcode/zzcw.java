package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
enum zzcw implements Iterator {
    INSTANCE;

    public final boolean hasNext() {
        return false;
    }

    public final Object next() {
        throw new NoSuchElementException();
    }

    public final void remove() {
        zzaz.zzf(false, "no calls to next() since the last call to remove()");
    }
}
