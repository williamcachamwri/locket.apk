package com.google.android.recaptcha.internal;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
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

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzby implements Deferred {
    private final /* synthetic */ CompletableDeferred zza;

    zzby(CompletableDeferred completableDeferred) {
        this.zza = completableDeferred;
    }

    public final ChildHandle attachChild(ChildJob childJob) {
        return this.zza.attachChild(childJob);
    }

    public final Object await(Continuation continuation) {
        return this.zza.await(continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public final /* synthetic */ void cancel() {
        this.zza.cancel();
    }

    public final void cancel(CancellationException cancellationException) {
        this.zza.cancel(cancellationException);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public final /* synthetic */ boolean cancel(Throwable th) {
        return this.zza.cancel(th);
    }

    public final Object fold(Object obj, Function2 function2) {
        return this.zza.fold(obj, function2);
    }

    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        return this.zza.get(key);
    }

    public final CancellationException getCancellationException() {
        return this.zza.getCancellationException();
    }

    public final Sequence getChildren() {
        return this.zza.getChildren();
    }

    public final Object getCompleted() {
        return this.zza.getCompleted();
    }

    public final Throwable getCompletionExceptionOrNull() {
        return this.zza.getCompletionExceptionOrNull();
    }

    public final CoroutineContext.Key getKey() {
        return this.zza.getKey();
    }

    public final SelectClause1 getOnAwait() {
        return this.zza.getOnAwait();
    }

    public final SelectClause0 getOnJoin() {
        return this.zza.getOnJoin();
    }

    public final Job getParent() {
        return this.zza.getParent();
    }

    public final DisposableHandle invokeOnCompletion(Function1 function1) {
        return this.zza.invokeOnCompletion(function1);
    }

    public final DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1 function1) {
        return this.zza.invokeOnCompletion(z, z2, function1);
    }

    public final boolean isActive() {
        return this.zza.isActive();
    }

    public final boolean isCancelled() {
        return this.zza.isCancelled();
    }

    public final boolean isCompleted() {
        return this.zza.isCompleted();
    }

    public final Object join(Continuation continuation) {
        return this.zza.join(continuation);
    }

    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        return this.zza.minusKey(key);
    }

    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        return this.zza.plus(coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public final Job plus(Job job) {
        return this.zza.plus(job);
    }

    public final boolean start() {
        return this.zza.start();
    }
}
