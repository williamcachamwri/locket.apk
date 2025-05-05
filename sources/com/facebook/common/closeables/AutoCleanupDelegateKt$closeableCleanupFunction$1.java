package com.facebook.common.closeables;

import java.io.Closeable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Ljava/io/Closeable;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AutoCleanupDelegate.kt */
final class AutoCleanupDelegateKt$closeableCleanupFunction$1 extends Lambda implements Function1<Closeable, Unit> {
    public static final AutoCleanupDelegateKt$closeableCleanupFunction$1 INSTANCE = new AutoCleanupDelegateKt$closeableCleanupFunction$1();

    AutoCleanupDelegateKt$closeableCleanupFunction$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Closeable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Closeable closeable) {
        Intrinsics.checkNotNullParameter(closeable, "it");
        closeable.close();
    }
}
