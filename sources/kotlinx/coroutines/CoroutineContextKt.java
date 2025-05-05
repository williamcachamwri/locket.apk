package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0007\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0007\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0001H\u0002\u001a \u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0006H\u0002\u001a4\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\u0003\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\f0\u0010H\b¢\u0006\u0002\u0010\u0011\u001a8\u0010\u0012\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00142\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\f0\u0010H\b¢\u0006\u0002\u0010\u0015\u001a(\u0010\u0016\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0017*\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010\u0003\u001a\u00020\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u000eH\u0000\u001a\u0013\u0010\u0019\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0017*\u00020\u001aH\u0010\"\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\u00018@X\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\"\u000e\u0010\u001f\u001a\u00020\u001cXT¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"newCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/CoroutineScope;", "context", "addedContext", "hasCopyableElements", "", "foldCopies", "originalContext", "appendContext", "isNewCoroutine", "withCoroutineContext", "T", "countOrElement", "", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withContinuationContext", "continuation", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "updateUndispatchedCompletion", "Lkotlinx/coroutines/UndispatchedCoroutine;", "oldValue", "undispatchedCompletion", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "coroutineName", "", "getCoroutineName", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/String;", "DEBUG_THREAD_NAME_SEPARATOR", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: CoroutineContext.kt */
public final class CoroutineContextKt {
    private static final String DEBUG_THREAD_NAME_SEPARATOR = " @";

    public static final CoroutineContext newCoroutineContext(CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        CoroutineContext foldCopies = foldCopies(coroutineScope.getCoroutineContext(), coroutineContext, true);
        CoroutineContext plus = DebugKt.getDEBUG() ? foldCopies.plus(new CoroutineId(DebugKt.getCOROUTINE_ID().incrementAndGet())) : foldCopies;
        return (foldCopies == Dispatchers.getDefault() || foldCopies.get(ContinuationInterceptor.Key) != null) ? plus : plus.plus(Dispatchers.getDefault());
    }

    public static final CoroutineContext newCoroutineContext(CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        if (!hasCopyableElements(coroutineContext2)) {
            return coroutineContext.plus(coroutineContext2);
        }
        return foldCopies(coroutineContext, coroutineContext2, false);
    }

    private static final boolean hasCopyableElements(CoroutineContext coroutineContext) {
        return ((Boolean) coroutineContext.fold(false, new CoroutineContextKt$$ExternalSyntheticLambda2())).booleanValue();
    }

    /* access modifiers changed from: private */
    public static final boolean hasCopyableElements$lambda$0(boolean z, CoroutineContext.Element element) {
        return z || (element instanceof CopyableThreadContextElement);
    }

    private static final CoroutineContext foldCopies(CoroutineContext coroutineContext, CoroutineContext coroutineContext2, boolean z) {
        boolean hasCopyableElements = hasCopyableElements(coroutineContext);
        boolean hasCopyableElements2 = hasCopyableElements(coroutineContext2);
        if (!hasCopyableElements && !hasCopyableElements2) {
            return coroutineContext.plus(coroutineContext2);
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = coroutineContext2;
        CoroutineContext coroutineContext3 = (CoroutineContext) coroutineContext.fold(EmptyCoroutineContext.INSTANCE, new CoroutineContextKt$$ExternalSyntheticLambda0(objectRef, z));
        if (hasCopyableElements2) {
            objectRef.element = ((CoroutineContext) objectRef.element).fold(EmptyCoroutineContext.INSTANCE, new CoroutineContextKt$$ExternalSyntheticLambda1());
        }
        return coroutineContext3.plus((CoroutineContext) objectRef.element);
    }

    /* access modifiers changed from: private */
    public static final CoroutineContext foldCopies$lambda$1(Ref.ObjectRef objectRef, boolean z, CoroutineContext coroutineContext, CoroutineContext.Element element) {
        if (!(element instanceof CopyableThreadContextElement)) {
            return coroutineContext.plus(element);
        }
        CoroutineContext.Element element2 = ((CoroutineContext) objectRef.element).get(element.getKey());
        if (element2 == null) {
            return coroutineContext.plus(z ? ((CopyableThreadContextElement) element).copyForChild() : (CopyableThreadContextElement) element);
        }
        objectRef.element = ((CoroutineContext) objectRef.element).minusKey(element.getKey());
        return coroutineContext.plus(((CopyableThreadContextElement) element).mergeForChild(element2));
    }

    /* access modifiers changed from: private */
    public static final CoroutineContext foldCopies$lambda$2(CoroutineContext coroutineContext, CoroutineContext.Element element) {
        if (element instanceof CopyableThreadContextElement) {
            return coroutineContext.plus(((CopyableThreadContextElement) element).copyForChild());
        }
        return coroutineContext.plus(element);
    }

    public static final <T> T withCoroutineContext(CoroutineContext coroutineContext, Object obj, Function0<? extends T> function0) {
        Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T withContinuationContext(Continuation<?> continuation, Object obj, Function0<? extends T> function0) {
        CoroutineContext context = continuation.getContext();
        Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj);
        UndispatchedCoroutine<?> updateUndispatchedCompletion = updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? updateUndispatchedCompletion(continuation, context, updateThreadContext) : null;
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public static final UndispatchedCoroutine<?> updateUndispatchedCompletion(Continuation<?> continuation, CoroutineContext coroutineContext, Object obj) {
        if (!(continuation instanceof CoroutineStackFrame)) {
            return null;
        }
        if (!(coroutineContext.get(UndispatchedMarker.INSTANCE) != null)) {
            return null;
        }
        UndispatchedCoroutine<?> undispatchedCompletion = undispatchedCompletion((CoroutineStackFrame) continuation);
        if (undispatchedCompletion != null) {
            undispatchedCompletion.saveThreadContext(coroutineContext, obj);
        }
        return undispatchedCompletion;
    }

    public static final UndispatchedCoroutine<?> undispatchedCompletion(CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof DispatchedCoroutine) && (coroutineStackFrame = coroutineStackFrame.getCallerFrame()) != null) {
            if (coroutineStackFrame instanceof UndispatchedCoroutine) {
                return (UndispatchedCoroutine) coroutineStackFrame;
            }
        }
        return null;
    }

    public static final String getCoroutineName(CoroutineContext coroutineContext) {
        CoroutineId coroutineId;
        String str;
        if (!DebugKt.getDEBUG() || (coroutineId = (CoroutineId) coroutineContext.get(CoroutineId.Key)) == null) {
            return null;
        }
        CoroutineName coroutineName = (CoroutineName) coroutineContext.get(CoroutineName.Key);
        if (coroutineName == null || (str = coroutineName.getName()) == null) {
            str = "coroutine";
        }
        return str + '#' + coroutineId.getId();
    }
}
