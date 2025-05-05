package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.selects.SelectClause0;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u0000 12\u00020\u0001:\u00011J\u0011\u0010\f\u001a\u00060\u000ej\u0002`\rH'¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\bH&J\u001f\u0010\u0011\u001a\u00020\u00122\u0010\b\u0002\u0010\u0013\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\rH&¢\u0006\u0002\u0010\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0017J\u0014\u0010\u0011\u001a\u00020\b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0015H'J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH'J\u000e\u0010\u001e\u001a\u00020\u0012H¦@¢\u0006\u0002\u0010\u001fJ6\u0010$\u001a\u00020%2'\u0010&\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00120(j\u0002`'H&¢\u0006\u0002\u0010+JJ\u0010$\u001a\u00020%2\b\b\u0002\u0010,\u001a\u00020\b2\b\b\u0002\u0010-\u001a\u00020\b2'\u0010&\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b)\u0012\b\b*\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00120(j\u0002`'H'¢\u0006\u0002\u0010.J\u0011\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u0000H\u0002R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u00008&X§\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0012\u0010\n\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\tR\u0012\u0010\u000b\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00000\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010 \u001a\u00020!X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u00062"}, d2 = {"Lkotlinx/coroutines/Job;", "Lkotlin/coroutines/CoroutineContext$Element;", "parent", "getParent$annotations", "()V", "getParent", "()Lkotlinx/coroutines/Job;", "isActive", "", "()Z", "isCompleted", "isCancelled", "getCancellationException", "Lkotlinx/coroutines/CancellationException;", "Ljava/util/concurrent/CancellationException;", "()Ljava/util/concurrent/CancellationException;", "start", "cancel", "", "cause", "(Ljava/util/concurrent/CancellationException;)V", "", "children", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "attachChild", "Lkotlinx/coroutines/ChildHandle;", "child", "Lkotlinx/coroutines/ChildJob;", "join", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "handler", "Lkotlinx/coroutines/CompletionHandler;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "(ZZLkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "plus", "other", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: Job.kt */
public interface Job extends CoroutineContext.Element {
    public static final Key Key = Key.$$INSTANCE;

    ChildHandle attachChild(ChildJob childJob);

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    /* synthetic */ void cancel();

    void cancel(CancellationException cancellationException);

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    /* synthetic */ boolean cancel(Throwable th);

    CancellationException getCancellationException();

    Sequence<Job> getChildren();

    SelectClause0 getOnJoin();

    Job getParent();

    DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> function1);

    DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1<? super Throwable, Unit> function1);

    boolean isActive();

    boolean isCancelled();

    boolean isCompleted();

    Object join(Continuation<? super Unit> continuation);

    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    Job plus(Job job);

    boolean start();

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: Job.kt */
    public static final class DefaultImpls {
        public static /* synthetic */ void getParent$annotations() {
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        public static Job plus(Job job, Job job2) {
            return job2;
        }

        public static <R> R fold(Job job, R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return CoroutineContext.Element.DefaultImpls.fold(job, r, function2);
        }

        public static <E extends CoroutineContext.Element> E get(Job job, CoroutineContext.Key<E> key) {
            return CoroutineContext.Element.DefaultImpls.get(job, key);
        }

        public static CoroutineContext minusKey(Job job, CoroutineContext.Key<?> key) {
            return CoroutineContext.Element.DefaultImpls.minusKey(job, key);
        }

        public static CoroutineContext plus(Job job, CoroutineContext coroutineContext) {
            return CoroutineContext.Element.DefaultImpls.plus(job, coroutineContext);
        }

        public static /* synthetic */ void cancel$default(Job job, CancellationException cancellationException, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    cancellationException = null;
                }
                job.cancel(cancellationException);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
        }

        public static /* synthetic */ boolean cancel$default(Job job, Throwable th, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    th = null;
                }
                return job.cancel(th);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
        }

        public static /* synthetic */ DisposableHandle invokeOnCompletion$default(Job job, boolean z, boolean z2, Function1 function1, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    z = false;
                }
                if ((i & 2) != 0) {
                    z2 = true;
                }
                return job.invokeOnCompletion(z, z2, function1);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: invokeOnCompletion");
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/Job$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/Job;", "<init>", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: Job.kt */
    public static final class Key implements CoroutineContext.Key<Job> {
        static final /* synthetic */ Key $$INSTANCE = new Key();

        private Key() {
        }
    }
}
