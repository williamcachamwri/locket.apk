package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.NonDisposableHandle;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B#\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB\u001d\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\t\u0010\u000bJ\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J!\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0014\u001a\u00060\u0016j\u0002`\u0015H\u0016¢\u0006\u0002\u0010\u0017J\u001e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u001cH\u0016J)\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\n\u0010\u0014\u001a\u00060\u0016j\u0002`\u00152\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u001fJ!\u0010 \u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u0014\u001a\u00060\u0016j\u0002`\u0015H\u0002¢\u0006\u0002\u0010\u0017J\b\u0010!\u001a\u00020\u0006H\u0016J\u0013\u0010\"\u001a\u00020\b2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J\b\u0010%\u001a\u00020&H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u0000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006'"}, d2 = {"Lkotlinx/coroutines/android/HandlerContext;", "Lkotlinx/coroutines/android/HandlerDispatcher;", "Lkotlinx/coroutines/Delay;", "handler", "Landroid/os/Handler;", "name", "", "invokeImmediately", "", "<init>", "(Landroid/os/Handler;Ljava/lang/String;Z)V", "(Landroid/os/Handler;Ljava/lang/String;)V", "immediate", "getImmediate", "()Lkotlinx/coroutines/android/HandlerContext;", "isDispatchNeeded", "context", "Lkotlin/coroutines/CoroutineContext;", "dispatch", "", "block", "Lkotlinx/coroutines/Runnable;", "Ljava/lang/Runnable;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "scheduleResumeAfterDelay", "timeMillis", "", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "(JLjava/lang/Runnable;Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/DisposableHandle;", "cancelOnRejection", "toString", "equals", "other", "", "hashCode", "", "kotlinx-coroutines-android"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: HandlerDispatcher.kt */
public final class HandlerContext extends HandlerDispatcher implements Delay {
    private final Handler handler;
    private final HandlerContext immediate;
    private final boolean invokeImmediately;
    private final String name;

    private HandlerContext(Handler handler2, String str, boolean z) {
        super((DefaultConstructorMarker) null);
        this.handler = handler2;
        this.name = str;
        this.invokeImmediately = z;
        this.immediate = z ? this : new HandlerContext(handler2, str, true);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ HandlerContext(Handler handler2, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(handler2, (i & 2) != 0 ? null : str);
    }

    public HandlerContext(Handler handler2, String str) {
        this(handler2, str, false);
    }

    public HandlerContext getImmediate() {
        return this.immediate;
    }

    public boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        return !this.invokeImmediately || !Intrinsics.areEqual((Object) Looper.myLooper(), (Object) this.handler.getLooper());
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        if (!this.handler.post(runnable)) {
            cancelOnRejection(coroutineContext, runnable);
        }
    }

    /* access modifiers changed from: private */
    public static final Unit scheduleResumeAfterDelay$lambda$2(HandlerContext handlerContext, Runnable runnable, Throwable th) {
        handlerContext.handler.removeCallbacks(runnable);
        return Unit.INSTANCE;
    }

    public DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        if (this.handler.postDelayed(runnable, RangesKt.coerceAtMost(j, (long) DurationKt.MAX_MILLIS))) {
            return new HandlerContext$$ExternalSyntheticLambda0(this, runnable);
        }
        cancelOnRejection(coroutineContext, runnable);
        return NonDisposableHandle.INSTANCE;
    }

    /* access modifiers changed from: private */
    public static final void invokeOnTimeout$lambda$3(HandlerContext handlerContext, Runnable runnable) {
        handlerContext.handler.removeCallbacks(runnable);
    }

    private final void cancelOnRejection(CoroutineContext coroutineContext, Runnable runnable) {
        JobKt.cancel(coroutineContext, new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed"));
        Dispatchers.getIO().dispatch(coroutineContext, runnable);
    }

    public String toString() {
        String stringInternalImpl = toStringInternalImpl();
        if (stringInternalImpl != null) {
            return stringInternalImpl;
        }
        HandlerContext handlerContext = this;
        String str = this.name;
        if (str == null) {
            str = this.handler.toString();
        }
        return this.invokeImmediately ? str + ".immediate" : str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof HandlerContext) {
            HandlerContext handlerContext = (HandlerContext) obj;
            return handlerContext.handler == this.handler && handlerContext.invokeImmediately == this.invokeImmediately;
        }
    }

    public int hashCode() {
        return System.identityHashCode(this.handler) ^ (this.invokeImmediately ? 1231 : 1237);
    }

    public void scheduleResumeAfterDelay(long j, CancellableContinuation<? super Unit> cancellableContinuation) {
        Runnable handlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1 = new HandlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1(cancellableContinuation, this);
        if (this.handler.postDelayed(handlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1, RangesKt.coerceAtMost(j, (long) DurationKt.MAX_MILLIS))) {
            cancellableContinuation.invokeOnCancellation(new HandlerContext$$ExternalSyntheticLambda1(this, handlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1));
        } else {
            cancelOnRejection(cancellableContinuation.getContext(), handlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1);
        }
    }
}
