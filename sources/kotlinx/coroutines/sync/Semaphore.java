package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0006\u001a\u00020\u0007H¦@¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/sync/Semaphore;", "", "availablePermits", "", "getAvailablePermits", "()I", "acquire", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryAcquire", "", "release", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: Semaphore.kt */
public interface Semaphore {
    Object acquire(Continuation<? super Unit> continuation);

    int getAvailablePermits();

    void release();

    boolean tryAcquire();
}
