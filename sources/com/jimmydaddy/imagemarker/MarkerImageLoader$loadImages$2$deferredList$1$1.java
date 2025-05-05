package com.jimmydaddy.imagemarker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import coil.ImageLoader;
import coil.request.ImageRequest;
import coil.size.Size;
import coil.target.Target;
import com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec;
import com.jimmydaddy.imagemarker.base.Constants;
import com.jimmydaddy.imagemarker.base.ErrorCode;
import com.jimmydaddy.imagemarker.base.ImageOptions;
import com.jimmydaddy.imagemarker.base.MarkerError;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.jimmydaddy.imagemarker.MarkerImageLoader$loadImages$2$deferredList$1$1", f = "MarkerImageLoader.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MarkerImageLoader.kt */
final class MarkerImageLoader$loadImages$2$deferredList$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ ImageOptions $img;
    int label;
    final /* synthetic */ MarkerImageLoader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MarkerImageLoader$loadImages$2$deferredList$1$1(MarkerImageLoader markerImageLoader, ImageOptions imageOptions, Continuation<? super MarkerImageLoader$loadImages$2$deferredList$1$1> continuation) {
        super(2, continuation);
        this.this$0 = markerImageLoader;
        this.$img = imageOptions;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MarkerImageLoader$loadImages$2$deferredList$1$1(this.this$0, this.$img, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((MarkerImageLoader$loadImages$2$deferredList$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ImageRequest.Builder builder;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                boolean access$isCoilImg = this.this$0.isCoilImg(this.$img.getUri());
                boolean z = false;
                Log.d(Constants.IMAGE_MARKER_TAG, "isCoilImg: " + (access$isCoilImg));
                if (access$isCoilImg) {
                    CompletableFuture completableFuture = new CompletableFuture();
                    ImageRequest.Builder data = new ImageRequest.Builder((Context) this.this$0.context).data(this.$img.getUri());
                    if (this.$img.getSrc() == null || this.$img.getSrc().getWidth() <= 0 || this.$img.getSrc().getHeight() <= 0) {
                        builder = data.size(Size.ORIGINAL);
                    } else {
                        builder = data.size(this.$img.getSrc().getWidth(), this.$img.getSrc().getHeight());
                        Log.d(Constants.IMAGE_MARKER_TAG, "src.width: " + this.$img.getSrc().getWidth() + " src.height: " + this.$img.getSrc().getHeight());
                    }
                    ImageLoader access$getImageLoader$p = this.this$0.imageLoader;
                    ImageOptions imageOptions = this.$img;
                    access$getImageLoader$p.enqueue(builder.target((Target) new MarkerImageLoader$loadImages$2$deferredList$1$1$invokeSuspend$$inlined$target$1(imageOptions, completableFuture, imageOptions, imageOptions, completableFuture)).build());
                    return completableFuture.get();
                }
                int access$getDrawableResourceByName = this.this$0.getDrawableResourceByName(this.$img.getUri());
                Log.d(Constants.IMAGE_MARKER_TAG, "resId: " + access$getDrawableResourceByName);
                if (access$getDrawableResourceByName != 0) {
                    Resources access$getResources = this.this$0.getResources();
                    Log.d(Constants.IMAGE_MARKER_TAG, "src.width: " + this.$img.getSrc().getWidth() + " src.height: " + this.$img.getSrc().getHeight());
                    Bitmap decodeResource = BitmapFactory.decodeResource(access$getResources, access$getDrawableResourceByName);
                    if (this.$img.getSrc() != null && this.$img.getSrc().getWidth() > 0 && this.$img.getSrc().getHeight() > 0) {
                        decodeResource = Bitmap.createScaledBitmap(decodeResource, this.$img.getSrc().getWidth(), this.$img.getSrc().getHeight(), true);
                    }
                    Intrinsics.checkNotNull(decodeResource);
                    Log.d(Constants.IMAGE_MARKER_TAG, new StringBuilder().append(decodeResource.getHeight()).toString());
                    Bitmap scaleBitmap = ImageProcess.Companion.scaleBitmap(decodeResource, this.$img.getScale());
                    Intrinsics.checkNotNull(scaleBitmap);
                    Log.d(Constants.IMAGE_MARKER_TAG, new StringBuilder().append(scaleBitmap.getHeight()).toString());
                    if (!decodeResource.isRecycled()) {
                        if (this.$img.getScale() == 1.0f) {
                            z = true;
                        }
                        if (!z) {
                            decodeResource.recycle();
                            System.gc();
                        }
                    }
                    return scaleBitmap;
                }
                Log.d(Constants.IMAGE_MARKER_TAG, "cannot find res");
                throw new MarkerError(ErrorCode.GET_RESOURCE_FAILED, "Can't get resource by the path: " + this.$img.getUri());
            } catch (Exception e) {
                SentryLogcatAdapter.e(NativeImageLoaderAndroidSpec.NAME, "Failed to load image: " + this.$img.getUri(), e);
                return null;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
