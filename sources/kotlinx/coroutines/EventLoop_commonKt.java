package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007H\u0000\u001a\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000*\u001e\b\u0002\u0010\u000f\u001a\u0004\b\u0000\u0010\u0010\"\b\u0012\u0004\u0012\u0002H\u00100\u00112\b\u0012\u0004\u0012\u0002H\u00100\u0011¨\u0006\u0012"}, d2 = {"DISPOSED_TASK", "Lkotlinx/coroutines/internal/Symbol;", "SCHEDULE_OK", "", "SCHEDULE_COMPLETED", "SCHEDULE_DISPOSED", "MS_TO_NS", "", "MAX_MS", "MAX_DELAY_NS", "delayToNanos", "timeMillis", "delayNanosToMillis", "timeNanos", "CLOSED_EMPTY", "Queue", "T", "Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: EventLoop.common.kt */
public final class EventLoop_commonKt {
    /* access modifiers changed from: private */
    public static final Symbol CLOSED_EMPTY = new Symbol("CLOSED_EMPTY");
    /* access modifiers changed from: private */
    public static final Symbol DISPOSED_TASK = new Symbol("REMOVED_TASK");
    private static final long MAX_DELAY_NS = 4611686018427387903L;
    private static final long MAX_MS = 9223372036854L;
    private static final long MS_TO_NS = 1000000;
    private static final int SCHEDULE_COMPLETED = 1;
    private static final int SCHEDULE_DISPOSED = 2;
    private static final int SCHEDULE_OK = 0;

    public static final long delayToNanos(long j) {
        if (j <= 0) {
            return 0;
        }
        if (j >= MAX_MS) {
            return Long.MAX_VALUE;
        }
        return 1000000 * j;
    }

    public static final long delayNanosToMillis(long j) {
        return j / 1000000;
    }
}
