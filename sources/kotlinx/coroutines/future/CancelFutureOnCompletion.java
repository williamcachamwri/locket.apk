package kotlinx.coroutines.future;

import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlinx.coroutines.JobNode;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0012\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/future/CancelFutureOnCompletion;", "Lkotlinx/coroutines/JobNode;", "future", "Ljava/util/concurrent/Future;", "<init>", "(Ljava/util/concurrent/Future;)V", "onCancelling", "", "getOnCancelling", "()Z", "invoke", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: Future.kt */
final class CancelFutureOnCompletion extends JobNode {
    private final Future<?> future;

    public boolean getOnCancelling() {
        return false;
    }

    public CancelFutureOnCompletion(Future<?> future2) {
        this.future = future2;
    }

    public void invoke(Throwable th) {
        if (th != null && !this.future.isDone()) {
            this.future.cancel(false);
        }
    }
}
