package kotlinx.coroutines.tasks;

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
import kotlinx.coroutines.ChildHandle;
import kotlinx.coroutines.ChildJob;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectClause1;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0011\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001J\u000e\u0010\u0006\u001a\u00028\u0000HA¢\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0001J\t\u0010\b\u001a\u00020\fH\u0001J\u0013\u0010\b\u001a\u00020\f2\b\u0010\n\u001a\u0004\u0018\u00010\rH\u0001J<\u0010\u000e\u001a\u0002H\u000f\"\n\b\u0001\u0010\u000f*\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u0002H\u000f2\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u0002H\u000f0\u0013H\u0001¢\u0006\u0002\u0010\u0015J(\u0010\u0016\u001a\u0004\u0018\u0001H\u0017\"\b\b\u0001\u0010\u0017*\u00020\u00142\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0003¢\u0006\u0002\u0010\u001aJ\t\u0010\u001b\u001a\u00020\rH\u0001J\u000e\u0010\u001c\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010\u001dJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u000bH\u0001J.\u0010\u001f\u001a\u00020 2#\u0010!\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\f0\"H\u0001J>\u0010\u001f\u001a\u00020 2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\t2#\u0010!\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\f0\"H\u0001J\u000e\u0010'\u001a\u00020\fHA¢\u0006\u0002\u0010\u0007J\u0015\u0010(\u001a\u00020)2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0001J\u0011\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0003J\u0011\u0010*\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0003J\t\u0010.\u001a\u00020\tH\u0001R\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020,00X\u0005R\t\u00101\u001a\u00020\tX\u0005R\t\u00102\u001a\u00020\tX\u0005R\t\u00103\u001a\u00020\tX\u0005R\r\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019X\u0005R\u000f\u00104\u001a\b\u0012\u0004\u0012\u00028\u000005X\u0005R\t\u00106\u001a\u000207X\u0005R\r\u00108\u001a\u0004\u0018\u00010,8\u0016X\u0005¨\u00069"}, d2 = {"kotlinx/coroutines/tasks/TasksKt$asDeferredImpl$3", "Lkotlinx/coroutines/Deferred;", "attachChild", "Lkotlinx/coroutines/ChildHandle;", "child", "Lkotlinx/coroutines/ChildJob;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "fold", "R", "", "initial", "operation", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext$Element;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "getCancellationException", "getCompleted", "()Ljava/lang/Object;", "getCompletionExceptionOrNull", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onCancelling", "invokeImmediately", "join", "minusKey", "Lkotlin/coroutines/CoroutineContext;", "plus", "context", "Lkotlinx/coroutines/Job;", "other", "start", "children", "Lkotlin/sequences/Sequence;", "isActive", "isCancelled", "isCompleted", "onAwait", "Lkotlinx/coroutines/selects/SelectClause1;", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "parent", "kotlinx-coroutines-play-services"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: Tasks.kt */
public final class TasksKt$asDeferredImpl$3 implements Deferred<T> {
    private final /* synthetic */ CompletableDeferred<T> $$delegate_0;

    public ChildHandle attachChild(ChildJob childJob) {
        return this.$$delegate_0.attachChild(childJob);
    }

    public Object await(Continuation<? super T> continuation) {
        return this.$$delegate_0.await(continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        this.$$delegate_0.cancel();
    }

    public void cancel(CancellationException cancellationException) {
        this.$$delegate_0.cancel(cancellationException);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ boolean cancel(Throwable th) {
        return this.$$delegate_0.cancel(th);
    }

    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return this.$$delegate_0.fold(r, function2);
    }

    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return this.$$delegate_0.get(key);
    }

    public CancellationException getCancellationException() {
        return this.$$delegate_0.getCancellationException();
    }

    public Sequence<Job> getChildren() {
        return this.$$delegate_0.getChildren();
    }

    public T getCompleted() {
        return this.$$delegate_0.getCompleted();
    }

    public Throwable getCompletionExceptionOrNull() {
        return this.$$delegate_0.getCompletionExceptionOrNull();
    }

    public CoroutineContext.Key<?> getKey() {
        return this.$$delegate_0.getKey();
    }

    public SelectClause1<T> getOnAwait() {
        return this.$$delegate_0.getOnAwait();
    }

    public SelectClause0 getOnJoin() {
        return this.$$delegate_0.getOnJoin();
    }

    public Job getParent() {
        return this.$$delegate_0.getParent();
    }

    public DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> function1) {
        return this.$$delegate_0.invokeOnCompletion(function1);
    }

    public DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1<? super Throwable, Unit> function1) {
        return this.$$delegate_0.invokeOnCompletion(z, z2, function1);
    }

    public boolean isActive() {
        return this.$$delegate_0.isActive();
    }

    public boolean isCancelled() {
        return this.$$delegate_0.isCancelled();
    }

    public boolean isCompleted() {
        return this.$$delegate_0.isCompleted();
    }

    public Object join(Continuation<? super Unit> continuation) {
        return this.$$delegate_0.join(continuation);
    }

    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return this.$$delegate_0.minusKey(key);
    }

    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return this.$$delegate_0.plus(coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(Job job) {
        return this.$$delegate_0.plus(job);
    }

    public boolean start() {
        return this.$$delegate_0.start();
    }

    TasksKt$asDeferredImpl$3(CompletableDeferred<T> completableDeferred) {
        this.$$delegate_0 = completableDeferred;
    }
}
