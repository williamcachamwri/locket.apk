package expo.modules.kotlin;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0002J\t\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\u0007\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\rR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u000e"}, d2 = {"Lexpo/modules/kotlin/FilteredIterator;", "E", "", "iterator", "filter", "Lexpo/modules/kotlin/Filter;", "(Ljava/util/Iterator;Lexpo/modules/kotlin/Filter;)V", "next", "Ljava/lang/Object;", "findNext", "", "hasNext", "", "()Ljava/lang/Object;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilteredIterator.kt */
public final class FilteredIterator<E> implements Iterator<E>, KMappedMarker {
    private final Filter<E> filter;
    private final Iterator<E> iterator;
    private E next;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public FilteredIterator(Iterator<? extends E> it, Filter<E> filter2) {
        Intrinsics.checkNotNullParameter(it, "iterator");
        Intrinsics.checkNotNullParameter(filter2, "filter");
        this.iterator = it;
        this.filter = filter2;
        findNext();
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public E next() {
        E e = this.next;
        Intrinsics.checkNotNull(e);
        findNext();
        return e;
    }

    private final void findNext() {
        while (this.iterator.hasNext()) {
            E next2 = this.iterator.next();
            this.next = next2;
            if (this.filter.apply(next2)) {
                return;
            }
        }
        this.next = null;
    }
}
