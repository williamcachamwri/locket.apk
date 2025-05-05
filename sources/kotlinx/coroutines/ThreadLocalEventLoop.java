package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadLocalKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\b\rJ\r\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0012R$\u0010\u0004\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0007j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0006`\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0014\u0010\t\u001a\u00020\u00068@X\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/ThreadLocalEventLoop;", "", "<init>", "()V", "ref", "Lkotlinx/coroutines/internal/CommonThreadLocal;", "Lkotlinx/coroutines/EventLoop;", "Ljava/lang/ThreadLocal;", "Ljava/lang/ThreadLocal;", "eventLoop", "getEventLoop$kotlinx_coroutines_core", "()Lkotlinx/coroutines/EventLoop;", "currentOrNull", "currentOrNull$kotlinx_coroutines_core", "resetEventLoop", "", "resetEventLoop$kotlinx_coroutines_core", "setEventLoop", "setEventLoop$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: EventLoop.common.kt */
public final class ThreadLocalEventLoop {
    public static final ThreadLocalEventLoop INSTANCE = new ThreadLocalEventLoop();
    private static final ThreadLocal<EventLoop> ref = ThreadLocalKt.commonThreadLocal(new Symbol("ThreadLocalEventLoop"));

    private ThreadLocalEventLoop() {
    }

    public final EventLoop getEventLoop$kotlinx_coroutines_core() {
        ThreadLocal<EventLoop> threadLocal = ref;
        EventLoop eventLoop = threadLocal.get();
        if (eventLoop != null) {
            return eventLoop;
        }
        EventLoop createEventLoop = EventLoopKt.createEventLoop();
        threadLocal.set(createEventLoop);
        return createEventLoop;
    }

    public final EventLoop currentOrNull$kotlinx_coroutines_core() {
        return ref.get();
    }

    public final void resetEventLoop$kotlinx_coroutines_core() {
        ref.set((Object) null);
    }

    public final void setEventLoop$kotlinx_coroutines_core(EventLoop eventLoop) {
        ref.set(eventLoop);
    }
}
