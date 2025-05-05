package kotlinx.coroutines.channels;

import com.google.common.util.concurrent.Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00000\u0002B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\tH\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\tH\u0000¢\u0006\u0004\b\u001f\u0010\u001dJ\u0015\u0010 \u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\tH\u0000¢\u0006\u0002\b!J\u001a\u0010\"\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\u0011H\u0002J\u0017\u0010$\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0017\u001a\u00020\tH\u0000¢\u0006\u0002\b%J\u001f\u0010&\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\u0011H\u0000¢\u0006\u0002\b'J)\u0010(\u001a\u00020)2\u0006\u0010\u0017\u001a\u00020\t2\b\u0010*\u001a\u0004\u0018\u00010\u00112\b\u0010+\u001a\u0004\u0018\u00010\u0011H\u0000¢\u0006\u0002\b,J!\u0010-\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0017\u001a\u00020\t2\b\u0010.\u001a\u0004\u0018\u00010\u0011H\u0000¢\u0006\u0002\b/J\"\u00100\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u000204H\u0016J\u0016\u00105\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\u0006\u00106\u001a\u00020)R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00078F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010X\u0004R\u0014\u0010\u0012\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u00067"}, d2 = {"Lkotlinx/coroutines/channels/ChannelSegment;", "E", "Lkotlinx/coroutines/internal/Segment;", "id", "", "prev", "channel", "Lkotlinx/coroutines/channels/BufferedChannel;", "pointers", "", "<init>", "(JLkotlinx/coroutines/channels/ChannelSegment;Lkotlinx/coroutines/channels/BufferedChannel;I)V", "_channel", "getChannel", "()Lkotlinx/coroutines/channels/BufferedChannel;", "data", "Lkotlinx/atomicfu/AtomicArray;", "", "numberOfSlots", "getNumberOfSlots", "()I", "storeElement", "", "index", "element", "storeElement$kotlinx_coroutines_core", "(ILjava/lang/Object;)V", "getElement", "getElement$kotlinx_coroutines_core", "(I)Ljava/lang/Object;", "retrieveElement", "retrieveElement$kotlinx_coroutines_core", "cleanElement", "cleanElement$kotlinx_coroutines_core", "setElementLazy", "value", "getState", "getState$kotlinx_coroutines_core", "setState", "setState$kotlinx_coroutines_core", "casState", "", "from", "to", "casState$kotlinx_coroutines_core", "getAndSetState", "update", "getAndSetState$kotlinx_coroutines_core", "onCancellation", "cause", "", "context", "Lkotlin/coroutines/CoroutineContext;", "onCancelledRequest", "receiver", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: BufferedChannel.kt */
public final class ChannelSegment<E> extends Segment<ChannelSegment<E>> {
    private final BufferedChannel<E> _channel;
    private final /* synthetic */ AtomicReferenceArray data = new AtomicReferenceArray(BufferedChannelKt.SEGMENT_SIZE * 2);

    private final /* synthetic */ AtomicReferenceArray getData() {
        return this.data;
    }

    public ChannelSegment(long j, ChannelSegment<E> channelSegment, BufferedChannel<E> bufferedChannel, int i) {
        super(j, channelSegment, i);
        this._channel = bufferedChannel;
    }

    public final BufferedChannel<E> getChannel() {
        BufferedChannel<E> bufferedChannel = this._channel;
        Intrinsics.checkNotNull(bufferedChannel);
        return bufferedChannel;
    }

    public int getNumberOfSlots() {
        return BufferedChannelKt.SEGMENT_SIZE;
    }

    public final void storeElement$kotlinx_coroutines_core(int i, E e) {
        setElementLazy(i, e);
    }

    public final E getElement$kotlinx_coroutines_core(int i) {
        return getData().get(i * 2);
    }

    public final E retrieveElement$kotlinx_coroutines_core(int i) {
        E element$kotlinx_coroutines_core = getElement$kotlinx_coroutines_core(i);
        cleanElement$kotlinx_coroutines_core(i);
        return element$kotlinx_coroutines_core;
    }

    public final void cleanElement$kotlinx_coroutines_core(int i) {
        setElementLazy(i, (Object) null);
    }

    private final void setElementLazy(int i, Object obj) {
        getData().set(i * 2, obj);
    }

    public final Object getState$kotlinx_coroutines_core(int i) {
        return getData().get((i * 2) + 1);
    }

    public final void setState$kotlinx_coroutines_core(int i, Object obj) {
        getData().set((i * 2) + 1, obj);
    }

    public final boolean casState$kotlinx_coroutines_core(int i, Object obj, Object obj2) {
        return Striped$SmallLazyStriped$$ExternalSyntheticBackportWithForwarding0.m(getData(), (i * 2) + 1, obj, obj2);
    }

    public final Object getAndSetState$kotlinx_coroutines_core(int i, Object obj) {
        return getData().getAndSet((i * 2) + 1, obj);
    }

    public void onCancellation(int i, Throwable th, CoroutineContext coroutineContext) {
        Function1<E, Unit> function1;
        Function1<E, Unit> function12;
        boolean z = i >= BufferedChannelKt.SEGMENT_SIZE;
        if (z) {
            i -= BufferedChannelKt.SEGMENT_SIZE;
        }
        Object element$kotlinx_coroutines_core = getElement$kotlinx_coroutines_core(i);
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core(i);
            if ((state$kotlinx_coroutines_core instanceof Waiter) || (state$kotlinx_coroutines_core instanceof WaiterEB)) {
                if (casState$kotlinx_coroutines_core(i, state$kotlinx_coroutines_core, z ? BufferedChannelKt.INTERRUPTED_SEND : BufferedChannelKt.INTERRUPTED_RCV)) {
                    cleanElement$kotlinx_coroutines_core(i);
                    onCancelledRequest(i, !z);
                    if (z && (function1 = getChannel().onUndeliveredElement) != null) {
                        OnUndeliveredElementKt.callUndeliveredElement(function1, element$kotlinx_coroutines_core, coroutineContext);
                        return;
                    }
                    return;
                }
            } else if (state$kotlinx_coroutines_core == BufferedChannelKt.INTERRUPTED_SEND || state$kotlinx_coroutines_core == BufferedChannelKt.INTERRUPTED_RCV) {
                cleanElement$kotlinx_coroutines_core(i);
            } else if (!(state$kotlinx_coroutines_core == BufferedChannelKt.RESUMING_BY_EB || state$kotlinx_coroutines_core == BufferedChannelKt.RESUMING_BY_RCV)) {
                if (state$kotlinx_coroutines_core != BufferedChannelKt.DONE_RCV && state$kotlinx_coroutines_core != BufferedChannelKt.BUFFERED && state$kotlinx_coroutines_core != BufferedChannelKt.getCHANNEL_CLOSED()) {
                    throw new IllegalStateException(("unexpected state: " + state$kotlinx_coroutines_core).toString());
                }
                return;
            }
        }
        cleanElement$kotlinx_coroutines_core(i);
        if (z && (function12 = getChannel().onUndeliveredElement) != null) {
            OnUndeliveredElementKt.callUndeliveredElement(function12, element$kotlinx_coroutines_core, coroutineContext);
        }
    }

    public final void onCancelledRequest(int i, boolean z) {
        if (z) {
            getChannel().waitExpandBufferCompletion$kotlinx_coroutines_core((this.id * ((long) BufferedChannelKt.SEGMENT_SIZE)) + ((long) i));
        }
        onSlotCleaned();
    }
}
