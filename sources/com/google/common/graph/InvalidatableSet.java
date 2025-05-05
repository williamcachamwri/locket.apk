package com.google.common.graph;

import com.google.common.base.Supplier;
import com.google.common.collect.ForwardingSet;
import java.util.Set;

@ElementTypesAreNonnullByDefault
final class InvalidatableSet<E> extends ForwardingSet<E> {
    private final Set<E> delegate;
    private final Supplier<String> errorMessage;
    private final Supplier<Boolean> validator;

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Object, com.google.common.base.Supplier<java.lang.Boolean>] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.common.base.Supplier<java.lang.String>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <E> com.google.common.graph.InvalidatableSet<E> of(java.util.Set<E> r1, com.google.common.base.Supplier<java.lang.Boolean> r2, com.google.common.base.Supplier<java.lang.String> r3) {
        /*
            com.google.common.graph.InvalidatableSet r0 = new com.google.common.graph.InvalidatableSet
            java.lang.Object r1 = com.google.common.base.Preconditions.checkNotNull(r1)
            java.util.Set r1 = (java.util.Set) r1
            java.lang.Object r2 = com.google.common.base.Preconditions.checkNotNull(r2)
            com.google.common.base.Supplier r2 = (com.google.common.base.Supplier) r2
            java.lang.Object r3 = com.google.common.base.Preconditions.checkNotNull(r3)
            com.google.common.base.Supplier r3 = (com.google.common.base.Supplier) r3
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.InvalidatableSet.of(java.util.Set, com.google.common.base.Supplier, com.google.common.base.Supplier):com.google.common.graph.InvalidatableSet");
    }

    /* access modifiers changed from: protected */
    public Set<E> delegate() {
        validate();
        return this.delegate;
    }

    private InvalidatableSet(Set<E> set, Supplier<Boolean> supplier, Supplier<String> supplier2) {
        this.delegate = set;
        this.validator = supplier;
        this.errorMessage = supplier2;
    }

    public int hashCode() {
        return this.delegate.hashCode();
    }

    private void validate() {
        if (!this.validator.get().booleanValue()) {
            throw new IllegalStateException(this.errorMessage.get());
        }
    }
}
