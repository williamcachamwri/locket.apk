package kotlinx.serialization.internal;

import java.lang.ref.SoftReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\u00028\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tR\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlinx/serialization/internal/MutableSoftReference;", "T", "", "()V", "reference", "Ljava/lang/ref/SoftReference;", "getOrSetWithLock", "factory", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Caching.kt */
final class MutableSoftReference<T> {
    public volatile SoftReference<T> reference = new SoftReference<>((Object) null);

    public final synchronized T getOrSetWithLock(Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(function0, "factory");
        T t = this.reference.get();
        if (t != null) {
            return t;
        }
        T invoke = function0.invoke();
        this.reference = new SoftReference<>(invoke);
        return invoke;
    }
}
