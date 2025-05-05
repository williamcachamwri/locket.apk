package expo.modules.devlauncher.helpers;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.devlauncher.helpers.DevLauncherCoroutinesExtensionsKt$runBlockingOnMainThread$1", f = "DevLauncherCoroutinesExtensions.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DevLauncherCoroutinesExtensions.kt */
final class DevLauncherCoroutinesExtensionsKt$runBlockingOnMainThread$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Function0<T> $blockRef;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DevLauncherCoroutinesExtensionsKt$runBlockingOnMainThread$1(Function0<? extends T> function0, Continuation<? super DevLauncherCoroutinesExtensionsKt$runBlockingOnMainThread$1> continuation) {
        super(2, continuation);
        this.$blockRef = function0;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DevLauncherCoroutinesExtensionsKt$runBlockingOnMainThread$1(this.$blockRef, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
        return ((DevLauncherCoroutinesExtensionsKt$runBlockingOnMainThread$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.$blockRef.invoke();
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
