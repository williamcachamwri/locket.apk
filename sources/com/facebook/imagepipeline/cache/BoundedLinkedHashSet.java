package com.facebook.imagepipeline.cache;

import java.util.LinkedHashSet;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u000bR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/imagepipeline/cache/BoundedLinkedHashSet;", "E", "", "maxSize", "", "(I)V", "linkedHashSet", "Ljava/util/LinkedHashSet;", "add", "", "key", "(Ljava/lang/Object;)Z", "contains", "o", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BoundedLinkedHashSet.kt */
public final class BoundedLinkedHashSet<E> {
    private final LinkedHashSet<E> linkedHashSet;
    private final int maxSize;

    public BoundedLinkedHashSet(int i) {
        this.maxSize = i;
        this.linkedHashSet = new LinkedHashSet<>(i);
    }

    public final synchronized boolean contains(E e) {
        return this.linkedHashSet.contains(e);
    }

    public final synchronized boolean add(E e) {
        if (this.linkedHashSet.size() == this.maxSize) {
            LinkedHashSet<E> linkedHashSet2 = this.linkedHashSet;
            linkedHashSet2.remove(linkedHashSet2.iterator().next());
        }
        this.linkedHashSet.remove(e);
        return this.linkedHashSet.add(e);
    }
}
