package com.facebook.common.internal;

import javax.annotation.Nullable;

public interface Fn<A, R> {
    @Nullable
    R apply(A a2);
}
