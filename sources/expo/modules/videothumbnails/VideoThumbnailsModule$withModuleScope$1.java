package expo.modules.videothumbnails;

import expo.modules.core.errors.ModuleDestroyedException;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.videothumbnails.VideoThumbnailsModule$withModuleScope$1", f = "VideoThumbnailsModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoThumbnailsModule.kt */
public final class VideoThumbnailsModule$withModuleScope$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Unit> $block;
    final /* synthetic */ Promise $promise;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoThumbnailsModule$withModuleScope$1(Function0<Unit> function0, Promise promise, Continuation<? super VideoThumbnailsModule$withModuleScope$1> continuation) {
        super(2, continuation);
        this.$block = function0;
        this.$promise = promise;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoThumbnailsModule$withModuleScope$1(this.$block, this.$promise, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoThumbnailsModule$withModuleScope$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                this.$block.invoke();
            } catch (CodedException e) {
                this.$promise.reject(e);
            } catch (ModuleDestroyedException e2) {
                this.$promise.reject("ExpoVideoThumbnails", "VideoThumbnails module destroyed", e2);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        try {
            this.$block.invoke();
        } catch (CodedException e) {
            this.$promise.reject(e);
        } catch (ModuleDestroyedException e2) {
            this.$promise.reject("ExpoVideoThumbnails", "VideoThumbnails module destroyed", e2);
        }
        return Unit.INSTANCE;
    }
}
