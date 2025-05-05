package com.facebook.common.closeables;

import java.io.Closeable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"closeableCleanupFunction", "Lkotlin/Function1;", "Ljava/io/Closeable;", "", "fbcore_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: AutoCleanupDelegate.kt */
public final class AutoCleanupDelegateKt {
    /* access modifiers changed from: private */
    public static final Function1<Closeable, Unit> closeableCleanupFunction = AutoCleanupDelegateKt$closeableCleanupFunction$1.INSTANCE;
}
