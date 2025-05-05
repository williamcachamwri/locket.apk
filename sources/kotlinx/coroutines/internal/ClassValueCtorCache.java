package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0005\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tj\u0002`\b2\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\fH\u0016¢\u0006\u0002\u0010\rR\u0010\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/internal/ClassValueCtorCache;", "Lkotlinx/coroutines/internal/CtorCache;", "<init>", "()V", "cache", "kotlinx/coroutines/internal/ClassValueCtorCache$cache$1", "Lkotlinx/coroutines/internal/ClassValueCtorCache$cache$1;", "get", "Lkotlinx/coroutines/internal/Ctor;", "Lkotlin/Function1;", "", "key", "Ljava/lang/Class;", "(Ljava/lang/Class;)Lkotlin/jvm/functions/Function1;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: ExceptionsConstructor.kt */
final class ClassValueCtorCache extends CtorCache {
    public static final ClassValueCtorCache INSTANCE = new ClassValueCtorCache();
    private static final ClassValueCtorCache$cache$1 cache = new ClassValueCtorCache$cache$1();

    private ClassValueCtorCache() {
    }

    public Function1<Throwable, Throwable> get(Class<? extends Throwable> cls) {
        return (Function1) cache.get(cls);
    }
}
