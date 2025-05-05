package com.facebook.common.references;

import com.facebook.common.references.CloseableReference;

public class NoOpCloseableReference<T> extends CloseableReference<T> {
    public CloseableReference<T> clone() {
        return this;
    }

    public CloseableReference<T> cloneOrNull() {
        return this;
    }

    public void close() {
    }

    public boolean isValid() {
        return true;
    }

    NoOpCloseableReference(T t) {
        super(t, (ResourceReleaser) null, (CloseableReference.LeakHandler) null, (Throwable) null, false);
    }
}
