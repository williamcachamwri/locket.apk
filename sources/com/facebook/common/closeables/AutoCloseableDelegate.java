package com.facebook.common.closeables;

import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0011\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/facebook/common/closeables/AutoCloseableDelegate;", "T", "Ljava/io/Closeable;", "Lcom/facebook/common/closeables/AutoCleanupDelegate;", "initialValue", "(Ljava/io/Closeable;)V", "fbcore_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AutoCleanupDelegate.kt */
public final class AutoCloseableDelegate<T extends Closeable> extends AutoCleanupDelegate<T> {
    public AutoCloseableDelegate() {
        this((Closeable) null, 1, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AutoCloseableDelegate(Closeable closeable, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : closeable);
    }

    public AutoCloseableDelegate(T t) {
        super(t, AutoCleanupDelegateKt.closeableCleanupFunction);
    }
}
