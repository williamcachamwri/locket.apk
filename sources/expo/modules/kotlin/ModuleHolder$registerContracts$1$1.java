package expo.modules.kotlin;

import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.kotlin.activityresult.DefaultAppContextActivityResultCaller;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "Lexpo/modules/kotlin/modules/Module;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.kotlin.ModuleHolder$registerContracts$1$1", f = "ModuleHolder.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ModuleHolder.kt */
final class ModuleHolder$registerContracts$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> $it;
    int label;
    final /* synthetic */ ModuleHolder<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ModuleHolder$registerContracts$1$1(Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> function2, ModuleHolder<T> moduleHolder, Continuation<? super ModuleHolder$registerContracts$1$1> continuation) {
        super(2, continuation);
        this.$it = function2;
        this.this$0 = moduleHolder;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ModuleHolder$registerContracts$1$1(this.$it, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ModuleHolder$registerContracts$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> function2 = this.$it;
            DefaultAppContextActivityResultCaller appContextActivityResultCaller$expo_modules_core_release = this.this$0.getModule().getAppContext().getAppContextActivityResultCaller$expo_modules_core_release();
            this.label = 1;
            if (function2.invoke(appContextActivityResultCaller$expo_modules_core_release, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
