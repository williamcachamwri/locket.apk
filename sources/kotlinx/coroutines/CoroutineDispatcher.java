package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.LimitedDispatcher;
import kotlinx.coroutines.internal.LimitedDispatcherKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001c\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0017J!\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\u0010\u001a\u00060\u0012j\u0002`\u0011H&¢\u0006\u0002\u0010\u0013J!\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\u0010\u001a\u00060\u0012j\u0002`\u0011H\u0017¢\u0006\u0002\u0010\u0013J \u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0016\"\u0004\b\u0000\u0010\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0016J\u0012\u0010\u0019\u001a\u00020\u000f2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0016J\u0011\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0000H\u0002J\b\u0010\u001c\u001a\u00020\rH\u0016¨\u0006\u001e"}, d2 = {"Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlin/coroutines/ContinuationInterceptor;", "<init>", "()V", "isDispatchNeeded", "", "context", "Lkotlin/coroutines/CoroutineContext;", "limitedParallelism", "parallelism", "", "name", "", "dispatch", "", "block", "Lkotlinx/coroutines/Runnable;", "Ljava/lang/Runnable;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Runnable;)V", "dispatchYield", "interceptContinuation", "Lkotlin/coroutines/Continuation;", "T", "continuation", "releaseInterceptedContinuation", "plus", "other", "toString", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: CoroutineDispatcher.kt */
public abstract class CoroutineDispatcher extends AbstractCoroutineContextElement implements ContinuationInterceptor {
    public static final Key Key = new Key((DefaultConstructorMarker) null);

    public abstract void dispatch(CoroutineContext coroutineContext, Runnable runnable);

    public boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        return true;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two CoroutineDispatcher objects is meaningless. CoroutineDispatcher is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The dispatcher to the right of `+` just replaces the dispatcher to the left.")
    public final CoroutineDispatcher plus(CoroutineDispatcher coroutineDispatcher) {
        return coroutineDispatcher;
    }

    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return ContinuationInterceptor.DefaultImpls.get(this, key);
    }

    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return ContinuationInterceptor.DefaultImpls.minusKey(this, key);
    }

    public CoroutineDispatcher() {
        super(ContinuationInterceptor.Key);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlinx/coroutines/CoroutineDispatcher$Key;", "Lkotlin/coroutines/AbstractCoroutineContextKey;", "Lkotlin/coroutines/ContinuationInterceptor;", "Lkotlinx/coroutines/CoroutineDispatcher;", "<init>", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: CoroutineDispatcher.kt */
    public static final class Key extends AbstractCoroutineContextKey<ContinuationInterceptor, CoroutineDispatcher> {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
            super(ContinuationInterceptor.Key, new CoroutineDispatcher$Key$$ExternalSyntheticLambda0());
        }

        /* access modifiers changed from: private */
        public static final CoroutineDispatcher _init_$lambda$0(CoroutineContext.Element element) {
            if (element instanceof CoroutineDispatcher) {
                return (CoroutineDispatcher) element;
            }
            return null;
        }
    }

    public static /* synthetic */ CoroutineDispatcher limitedParallelism$default(CoroutineDispatcher coroutineDispatcher, int i, String str, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                str = null;
            }
            return coroutineDispatcher.limitedParallelism(i, str);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: limitedParallelism");
    }

    public CoroutineDispatcher limitedParallelism(int i, String str) {
        LimitedDispatcherKt.checkParallelism(i);
        return new LimitedDispatcher(this, i, str);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated for good. Override 'limitedParallelism(parallelism: Int, name: String?)' instead", replaceWith = @ReplaceWith(expression = "limitedParallelism(parallelism, null)", imports = {}))
    public /* synthetic */ CoroutineDispatcher limitedParallelism(int i) {
        return limitedParallelism(i, (String) null);
    }

    public void dispatchYield(CoroutineContext coroutineContext, Runnable runnable) {
        dispatch(coroutineContext, runnable);
    }

    public final <T> Continuation<T> interceptContinuation(Continuation<? super T> continuation) {
        return new DispatchedContinuation<>(this, continuation);
    }

    public final void releaseInterceptedContinuation(Continuation<?> continuation) {
        Intrinsics.checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        ((DispatchedContinuation) continuation).release$kotlinx_coroutines_core();
    }

    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this);
    }
}
