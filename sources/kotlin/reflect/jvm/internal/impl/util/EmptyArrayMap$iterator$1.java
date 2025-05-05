package kotlin.reflect.jvm.internal.impl.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: ArrayMap.kt */
public final class EmptyArrayMap$iterator$1 implements Iterator, KMappedMarker {
    public boolean hasNext() {
        return false;
    }

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    EmptyArrayMap$iterator$1() {
    }

    public Void next() {
        throw new NoSuchElementException();
    }
}
