package kotlinx.coroutines.internal;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a*\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u0003*\u00060\u0001j\u0002`\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0006H\b¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\f0\u000b\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u000eH\b*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001*\u001e\b\u0000\u0010\b\u001a\u0004\b\u0000\u0010\u0003\"\b\u0012\u0004\u0012\u0002H\u00030\t2\b\u0012\u0004\u0012\u0002H\u00030\t¨\u0006\u000f"}, d2 = {"ReentrantLock", "Ljava/util/concurrent/locks/ReentrantLock;", "withLock", "T", "Lkotlinx/coroutines/internal/ReentrantLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "WorkaroundAtomicReference", "Ljava/util/concurrent/atomic/AtomicReference;", "identitySet", "", "E", "expectedSize", "", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: Concurrent.kt */
public final class ConcurrentKt {
    public static /* synthetic */ void ReentrantLock$annotations() {
    }

    public static /* synthetic */ void WorkaroundAtomicReference$annotations() {
    }

    public static final <T> T withLock(ReentrantLock reentrantLock, Function0<? extends T> function0) {
        Lock lock = reentrantLock;
        lock.lock();
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            lock.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <E> Set<E> identitySet(int i) {
        return Collections.newSetFromMap(new IdentityHashMap(i));
    }
}
