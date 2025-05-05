package com.facebook.datasource;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/facebook/datasource/SuccessfulVoidDataSource;", "Lcom/facebook/datasource/AbstractDataSource;", "Ljava/lang/Void;", "()V", "fbcore_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SuccessfulVoidDataSource.kt */
public final class SuccessfulVoidDataSource extends AbstractDataSource<Void> {
    public static final SuccessfulVoidDataSource INSTANCE;

    private SuccessfulVoidDataSource() {
    }

    static {
        SuccessfulVoidDataSource successfulVoidDataSource = new SuccessfulVoidDataSource();
        INSTANCE = successfulVoidDataSource;
        successfulVoidDataSource.setResult(null, true);
    }
}
