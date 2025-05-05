package com.jimmydaddy.imagemarker;

import android.graphics.Bitmap;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.jimmydaddy.imagemarker.base.Constants;
import com.jimmydaddy.imagemarker.base.MarkTextOptions;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.jimmydaddy.imagemarker.ImageMarkerManager$markWithText$1", f = "ImageMarkerManager.kt", i = {}, l = {229}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ImageMarkerManager.kt */
final class ImageMarkerManager$markWithText$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MarkTextOptions $markOpts;
    final /* synthetic */ Promise $promise;
    int label;
    final /* synthetic */ ImageMarkerManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageMarkerManager$markWithText$1(ImageMarkerManager imageMarkerManager, MarkTextOptions markTextOptions, Promise promise, Continuation<? super ImageMarkerManager$markWithText$1> continuation) {
        super(2, continuation);
        this.this$0 = imageMarkerManager;
        this.$markOpts = markTextOptions;
        this.$promise = promise;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImageMarkerManager$markWithText$1(this.this$0, this.$markOpts, this.$promise, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ImageMarkerManager$markWithText$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = new MarkerImageLoader(this.this$0.context, this.$markOpts.getMaxSize()).loadImages(CollectionsKt.listOf(this.$markOpts.getBackgroundImage()), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e) {
                Log.d(Constants.IMAGE_MARKER_TAG, "error：" + e.getMessage());
                e.printStackTrace();
                this.$promise.reject("error", e.getMessage(), (Throwable) e);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String access$generateCacheFilePathForMarker = this.this$0.generateCacheFilePathForMarker(this.$markOpts.getFilename(), this.$markOpts.getSaveFormat());
        this.this$0.markImageByText((Bitmap) ((List) obj).get(0), access$generateCacheFilePathForMarker, this.$markOpts, this.$promise);
        return Unit.INSTANCE;
    }
}
