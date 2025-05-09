package io.grpc.internal;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

public abstract class InUseStateAggregator<T> {
    private final Set<T> inUseObjects = Collections.newSetFromMap(new IdentityHashMap());

    /* access modifiers changed from: protected */
    public abstract void handleInUse();

    /* access modifiers changed from: protected */
    public abstract void handleNotInUse();

    public final void updateObjectInUse(T t, boolean z) {
        int size = this.inUseObjects.size();
        if (z) {
            this.inUseObjects.add(t);
            if (size == 0) {
                handleInUse();
            }
        } else if (this.inUseObjects.remove(t) && size == 1) {
            handleNotInUse();
        }
    }

    public final boolean isInUse() {
        return !this.inUseObjects.isEmpty();
    }

    public final boolean anyObjectInUse(Object... objArr) {
        for (Object contains : objArr) {
            if (this.inUseObjects.contains(contains)) {
                return true;
            }
        }
        return false;
    }
}
