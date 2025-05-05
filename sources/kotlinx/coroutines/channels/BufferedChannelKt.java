package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b0\u001a,\u0010\u0000\u001a \u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00030\u0001\"\u0004\b\u0000\u0010\u0004H\u0000\u001a*\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0006\u001a\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003H\u0002\u001av\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00102\u0006\u0010\u0011\u001a\u0002H\u000f2O\b\u0002\u0010\u0012\u001aI\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0013H\u0002¢\u0006\u0002\u0010\u001b\u001a\u0010\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u000bH\u0002\u001a\u0018\u0010E\u001a\u00020\u00022\u0006\u0010F\u001a\u00020\u00022\u0006\u0010G\u001a\u00020\u000bH\u0002\u001a\u0018\u0010O\u001a\u00020\u00022\u0006\u0010F\u001a\u00020\u00022\u0006\u0010P\u001a\u00020\u000eH\u0002\"\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0003X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\n\u001a\u00020\u000b8\u0000X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u0010\u0010 \u001a\u00020!8\u0000X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010#\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010$\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010%\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010&\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010'\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010(\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010)\u001a\u00020!X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+\"\u000e\u0010,\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010-\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010.\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010/\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u00100\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u00101\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u00102\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u00103\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u00104\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u00105\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u00106\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u00107\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u00108\u001a\u00020!X\u0004¢\u0006\u0002\n\u0000\"\u000e\u00109\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010:\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010;\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010<\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010=\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010>\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u0019\u0010?\u001a\u00020\u0002*\u00020\u00028Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\b@\u0010A\"\u0019\u0010B\u001a\u00020\u000b*\u00020\u00028Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\bC\u0010D\"\u000e\u0010H\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u000e\u0010I\u001a\u00020\u0002XT¢\u0006\u0002\n\u0000\"\u0019\u0010J\u001a\u00020\u0002*\u00020\u00028Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\bK\u0010A\"\u0019\u0010L\u001a\u00020\u000e*\u00020\u00028Â\u0002X\u0004¢\u0006\u0006\u001a\u0004\bM\u0010N¨\u0006Q"}, d2 = {"createSegmentFunction", "Lkotlin/reflect/KFunction2;", "", "Lkotlinx/coroutines/channels/ChannelSegment;", "E", "createSegment", "id", "prev", "NULL_SEGMENT", "", "SEGMENT_SIZE", "", "EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS", "tryResume0", "", "T", "Lkotlinx/coroutines/CancellableContinuation;", "value", "onCancellation", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/CoroutineContext;", "context", "", "(Lkotlinx/coroutines/CancellableContinuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Z", "BUFFER_END_RENDEZVOUS", "BUFFER_END_UNLIMITED", "initialBufferEnd", "capacity", "BUFFERED", "Lkotlinx/coroutines/internal/Symbol;", "IN_BUFFER", "RESUMING_BY_RCV", "RESUMING_BY_EB", "POISONED", "DONE_RCV", "INTERRUPTED_SEND", "INTERRUPTED_RCV", "CHANNEL_CLOSED", "getCHANNEL_CLOSED", "()Lkotlinx/coroutines/internal/Symbol;", "SUSPEND", "SUSPEND_NO_WAITER", "FAILED", "RESULT_RENDEZVOUS", "RESULT_BUFFERED", "RESULT_SUSPEND", "RESULT_SUSPEND_NO_WAITER", "RESULT_CLOSED", "RESULT_FAILED", "NO_RECEIVE_RESULT", "CLOSE_HANDLER_CLOSED", "CLOSE_HANDLER_INVOKED", "NO_CLOSE_CAUSE", "CLOSE_STATUS_ACTIVE", "CLOSE_STATUS_CANCELLATION_STARTED", "CLOSE_STATUS_CLOSED", "CLOSE_STATUS_CANCELLED", "SENDERS_CLOSE_STATUS_SHIFT", "SENDERS_COUNTER_MASK", "sendersCounter", "getSendersCounter", "(J)J", "sendersCloseStatus", "getSendersCloseStatus", "(J)I", "constructSendersAndCloseStatus", "counter", "closeStatus", "EB_COMPLETED_PAUSE_EXPAND_BUFFERS_BIT", "EB_COMPLETED_COUNTER_MASK", "ebCompletedCounter", "getEbCompletedCounter", "ebPauseExpandBuffers", "getEbPauseExpandBuffers", "(J)Z", "constructEBCompletedAndPauseFlag", "pauseEB", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: BufferedChannel.kt */
public final class BufferedChannelKt {
    public static final Symbol BUFFERED = new Symbol("BUFFERED");
    private static final long BUFFER_END_RENDEZVOUS = 0;
    private static final long BUFFER_END_UNLIMITED = Long.MAX_VALUE;
    private static final Symbol CHANNEL_CLOSED = new Symbol("CHANNEL_CLOSED");
    /* access modifiers changed from: private */
    public static final Symbol CLOSE_HANDLER_CLOSED = new Symbol("CLOSE_HANDLER_CLOSED");
    /* access modifiers changed from: private */
    public static final Symbol CLOSE_HANDLER_INVOKED = new Symbol("CLOSE_HANDLER_INVOKED");
    private static final int CLOSE_STATUS_ACTIVE = 0;
    private static final int CLOSE_STATUS_CANCELLATION_STARTED = 1;
    private static final int CLOSE_STATUS_CANCELLED = 3;
    private static final int CLOSE_STATUS_CLOSED = 2;
    /* access modifiers changed from: private */
    public static final Symbol DONE_RCV = new Symbol("DONE_RCV");
    private static final long EB_COMPLETED_COUNTER_MASK = 4611686018427387903L;
    private static final long EB_COMPLETED_PAUSE_EXPAND_BUFFERS_BIT = 4611686018427387904L;
    /* access modifiers changed from: private */
    public static final int EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 0, 0, 12, (Object) null);
    /* access modifiers changed from: private */
    public static final Symbol FAILED = new Symbol("FAILED");
    /* access modifiers changed from: private */
    public static final Symbol INTERRUPTED_RCV = new Symbol("INTERRUPTED_RCV");
    /* access modifiers changed from: private */
    public static final Symbol INTERRUPTED_SEND = new Symbol("INTERRUPTED_SEND");
    /* access modifiers changed from: private */
    public static final Symbol IN_BUFFER = new Symbol("SHOULD_BUFFER");
    /* access modifiers changed from: private */
    public static final Symbol NO_CLOSE_CAUSE = new Symbol("NO_CLOSE_CAUSE");
    /* access modifiers changed from: private */
    public static final Symbol NO_RECEIVE_RESULT = new Symbol("NO_RECEIVE_RESULT");
    /* access modifiers changed from: private */
    public static final ChannelSegment<Object> NULL_SEGMENT = new ChannelSegment(-1, (ChannelSegment) null, (BufferedChannel) null, 0);
    /* access modifiers changed from: private */
    public static final Symbol POISONED = new Symbol("POISONED");
    private static final int RESULT_BUFFERED = 1;
    private static final int RESULT_CLOSED = 4;
    private static final int RESULT_FAILED = 5;
    private static final int RESULT_RENDEZVOUS = 0;
    private static final int RESULT_SUSPEND = 2;
    private static final int RESULT_SUSPEND_NO_WAITER = 3;
    /* access modifiers changed from: private */
    public static final Symbol RESUMING_BY_EB = new Symbol("RESUMING_BY_EB");
    /* access modifiers changed from: private */
    public static final Symbol RESUMING_BY_RCV = new Symbol("S_RESUMING_BY_RCV");
    public static final int SEGMENT_SIZE = SystemPropsKt__SystemProps_commonKt.systemProp$default("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 0, 0, 12, (Object) null);
    private static final int SENDERS_CLOSE_STATUS_SHIFT = 60;
    private static final long SENDERS_COUNTER_MASK = 1152921504606846975L;
    /* access modifiers changed from: private */
    public static final Symbol SUSPEND = new Symbol("SUSPEND");
    /* access modifiers changed from: private */
    public static final Symbol SUSPEND_NO_WAITER = new Symbol("SUSPEND_NO_WAITER");

    /* access modifiers changed from: private */
    public static final long constructEBCompletedAndPauseFlag(long j, boolean z) {
        return (z ? 4611686018427387904L : 0) + j;
    }

    /* access modifiers changed from: private */
    public static final long constructSendersAndCloseStatus(long j, int i) {
        return (((long) i) << 60) + j;
    }

    private static final long getEbCompletedCounter(long j) {
        return j & 4611686018427387903L;
    }

    private static final boolean getEbPauseExpandBuffers(long j) {
        return (j & 4611686018427387904L) != 0;
    }

    private static final int getSendersCloseStatus(long j) {
        return (int) (j >> 60);
    }

    private static final long getSendersCounter(long j) {
        return j & SENDERS_COUNTER_MASK;
    }

    /* access modifiers changed from: private */
    public static final long initialBufferEnd(int i) {
        if (i == 0) {
            return 0;
        }
        if (i != Integer.MAX_VALUE) {
            return (long) i;
        }
        return Long.MAX_VALUE;
    }

    public static final <E> KFunction<ChannelSegment<E>> createSegmentFunction() {
        return BufferedChannelKt$createSegmentFunction$1.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final <E> ChannelSegment<E> createSegment(long j, ChannelSegment<E> channelSegment) {
        return new ChannelSegment(j, channelSegment, channelSegment.getChannel(), 0);
    }

    static /* synthetic */ boolean tryResume0$default(CancellableContinuation cancellableContinuation, Object obj, Function3 function3, int i, Object obj2) {
        if ((i & 2) != 0) {
            function3 = null;
        }
        return tryResume0(cancellableContinuation, obj, function3);
    }

    /* access modifiers changed from: private */
    public static final <T> boolean tryResume0(CancellableContinuation<? super T> cancellableContinuation, T t, Function3<? super Throwable, ? super T, ? super CoroutineContext, Unit> function3) {
        Object tryResume = cancellableContinuation.tryResume(t, (Object) null, function3);
        if (tryResume == null) {
            return false;
        }
        cancellableContinuation.completeResume(tryResume);
        return true;
    }

    public static final Symbol getCHANNEL_CLOSED() {
        return CHANNEL_CLOSED;
    }
}
