package androidx.lifecycle;

import expo.modules.interfaces.permissions.PermissionsResponse;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B`\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012-\u0010\u0005\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006¢\u0006\u0002\b\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010¢\u0006\u0002\u0010\u0011J\b\u0010\u0016\u001a\u00020\tH\u0007J\b\u0010\u0017\u001a\u00020\tH\u0007R7\u0010\u0005\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006¢\u0006\u0002\b\nX\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/lifecycle/BlockRunner;", "T", "", "liveData", "Landroidx/lifecycle/CoroutineLiveData;", "block", "Lkotlin/Function2;", "Landroidx/lifecycle/LiveDataScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "timeoutInMs", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "onDone", "Lkotlin/Function0;", "(Landroidx/lifecycle/CoroutineLiveData;Lkotlin/jvm/functions/Function2;JLkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function0;)V", "Lkotlin/jvm/functions/Function2;", "cancellationJob", "Lkotlinx/coroutines/Job;", "runningJob", "cancel", "maybeRun", "lifecycle-livedata_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CoroutineLiveData.kt */
public final class BlockRunner<T> {
    /* access modifiers changed from: private */
    public final Function2<LiveDataScope<T>, Continuation<? super Unit>, Object> block;
    private Job cancellationJob;
    /* access modifiers changed from: private */
    public final CoroutineLiveData<T> liveData;
    /* access modifiers changed from: private */
    public final Function0<Unit> onDone;
    /* access modifiers changed from: private */
    public Job runningJob;
    private final CoroutineScope scope;
    /* access modifiers changed from: private */
    public final long timeoutInMs;

    public BlockRunner(CoroutineLiveData<T> coroutineLiveData, Function2<? super LiveDataScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, long j, CoroutineScope coroutineScope, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(coroutineLiveData, "liveData");
        Intrinsics.checkNotNullParameter(function2, "block");
        Intrinsics.checkNotNullParameter(coroutineScope, PermissionsResponse.SCOPE_KEY);
        Intrinsics.checkNotNullParameter(function0, "onDone");
        this.liveData = coroutineLiveData;
        this.block = function2;
        this.timeoutInMs = j;
        this.scope = coroutineScope;
        this.onDone = function0;
    }

    public final void maybeRun() {
        Job job = this.cancellationJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.cancellationJob = null;
        if (this.runningJob == null) {
            this.runningJob = BuildersKt__Builders_commonKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new BlockRunner$maybeRun$1(this, (Continuation<? super BlockRunner$maybeRun$1>) null), 3, (Object) null);
        }
    }

    public final void cancel() {
        if (this.cancellationJob == null) {
            this.cancellationJob = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new BlockRunner$cancel$1(this, (Continuation<? super BlockRunner$cancel$1>) null), 2, (Object) null);
            return;
        }
        throw new IllegalStateException("Cancel call cannot happen without a maybeRun".toString());
    }
}
