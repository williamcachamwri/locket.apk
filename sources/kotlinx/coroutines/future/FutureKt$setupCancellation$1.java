package kotlinx.coroutines.future;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* compiled from: Future.kt */
final class FutureKt$setupCancellation$1 implements Function2 {
    final /* synthetic */ Job $this_setupCancellation;

    FutureKt$setupCancellation$1(Job job) {
        this.$this_setupCancellation = job;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(obj, (Throwable) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Object obj, Throwable th) {
        Job job = this.$this_setupCancellation;
        CancellationException cancellationException = null;
        if (th != null) {
            if (th instanceof CancellationException) {
                cancellationException = (CancellationException) th;
            }
            if (cancellationException == null) {
                cancellationException = ExceptionsKt.CancellationException("CompletableFuture was completed exceptionally", th);
            }
        }
        job.cancel(cancellationException);
    }
}
