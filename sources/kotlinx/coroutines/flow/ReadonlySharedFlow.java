package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u001f\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ&\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0017\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015HAR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017X\u0005¨\u0006\u0018"}, d2 = {"Lkotlinx/coroutines/flow/ReadonlySharedFlow;", "T", "Lkotlinx/coroutines/flow/SharedFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "flow", "job", "Lkotlinx/coroutines/Job;", "<init>", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlinx/coroutines/Job;)V", "fuse", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "replayCache", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: Share.kt */
final class ReadonlySharedFlow<T> implements SharedFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    private final /* synthetic */ SharedFlow<T> $$delegate_0;
    private final Job job;

    public Object collect(FlowCollector<? super T> flowCollector, Continuation<?> continuation) {
        return this.$$delegate_0.collect(flowCollector, continuation);
    }

    public List<T> getReplayCache() {
        return this.$$delegate_0.getReplayCache();
    }

    public ReadonlySharedFlow(SharedFlow<? extends T> sharedFlow, Job job2) {
        this.$$delegate_0 = sharedFlow;
        this.job = job2;
    }

    public Flow<T> fuse(CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow) {
        return SharedFlowKt.fuseSharedFlow(this, coroutineContext, i, bufferOverflow);
    }
}
