package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0013\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\b\u001a\t\u0010\u0005\u001a\u00020\u0006H\b\u001a\t\u0010\u0007\u001a\u00020\u0006H\b\u001a\u001e\u0010\b\u001a\u00060\nj\u0002`\t2\n\u0010\u000b\u001a\u00060\nj\u0002`\tH\b¢\u0006\u0002\u0010\f\u001a\t\u0010\r\u001a\u00020\u0003H\b\u001a\t\u0010\u000e\u001a\u00020\u0003H\b\u001a\t\u0010\u000f\u001a\u00020\u0003H\b\u001a\t\u0010\u0010\u001a\u00020\u0003H\b\u001a\u0019\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H\b\u001a\u0011\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\b\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"timeSource", "Lkotlinx/coroutines/AbstractTimeSource;", "mockTimeSource", "", "source", "currentTimeMillis", "", "nanoTime", "wrapTask", "Lkotlinx/coroutines/Runnable;", "Ljava/lang/Runnable;", "block", "(Ljava/lang/Runnable;)Ljava/lang/Runnable;", "trackTask", "unTrackTask", "registerTimeLoopThread", "unregisterTimeLoopThread", "parkNanos", "blocker", "", "nanos", "unpark", "thread", "Ljava/lang/Thread;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: AbstractTimeSource.kt */
public final class AbstractTimeSourceKt {
    /* access modifiers changed from: private */
    public static AbstractTimeSource timeSource;

    public static final void mockTimeSource(AbstractTimeSource abstractTimeSource) {
        timeSource = abstractTimeSource;
    }

    private static final long currentTimeMillis() {
        AbstractTimeSource access$getTimeSource$p = timeSource;
        return access$getTimeSource$p != null ? access$getTimeSource$p.currentTimeMillis() : System.currentTimeMillis();
    }

    private static final long nanoTime() {
        AbstractTimeSource access$getTimeSource$p = timeSource;
        return access$getTimeSource$p != null ? access$getTimeSource$p.nanoTime() : System.nanoTime();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.wrapTask(r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.Runnable wrapTask(java.lang.Runnable r1) {
        /*
            kotlinx.coroutines.AbstractTimeSource r0 = timeSource
            if (r0 == 0) goto L_0x000e
            java.lang.Runnable r0 = r0.wrapTask(r1)
            if (r0 != 0) goto L_0x000d
            goto L_0x000e
        L_0x000d:
            r1 = r0
        L_0x000e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.AbstractTimeSourceKt.wrapTask(java.lang.Runnable):java.lang.Runnable");
    }

    private static final void trackTask() {
        AbstractTimeSource access$getTimeSource$p = timeSource;
        if (access$getTimeSource$p != null) {
            access$getTimeSource$p.trackTask();
        }
    }

    private static final void unTrackTask() {
        AbstractTimeSource access$getTimeSource$p = timeSource;
        if (access$getTimeSource$p != null) {
            access$getTimeSource$p.unTrackTask();
        }
    }

    private static final void registerTimeLoopThread() {
        AbstractTimeSource access$getTimeSource$p = timeSource;
        if (access$getTimeSource$p != null) {
            access$getTimeSource$p.registerTimeLoopThread();
        }
    }

    private static final void unregisterTimeLoopThread() {
        AbstractTimeSource access$getTimeSource$p = timeSource;
        if (access$getTimeSource$p != null) {
            access$getTimeSource$p.unregisterTimeLoopThread();
        }
    }

    private static final void parkNanos(Object obj, long j) {
        AbstractTimeSource access$getTimeSource$p = timeSource;
        if (access$getTimeSource$p != null) {
            access$getTimeSource$p.parkNanos(obj, j);
        } else {
            LockSupport.parkNanos(obj, j);
        }
    }

    private static final void unpark(Thread thread) {
        AbstractTimeSource access$getTimeSource$p = timeSource;
        if (access$getTimeSource$p != null) {
            access$getTimeSource$p.unpark(thread);
        } else {
            LockSupport.unpark(thread);
        }
    }
}
