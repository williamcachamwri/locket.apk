package com.jimmydaddy.imagemarker;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.core.graphics.drawable.DrawableKt;
import coil.target.Target;
import com.jimmydaddy.imagemarker.base.Constants;
import com.jimmydaddy.imagemarker.base.ErrorCode;
import com.jimmydaddy.imagemarker.base.ImageOptions;
import com.jimmydaddy.imagemarker.base.MarkerError;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H\u0016¨\u0006\n¸\u0006\u0000"}, d2 = {"coil/request/ImageRequest$Builder$target$4", "Lcoil/target/Target;", "onError", "", "error", "Landroid/graphics/drawable/Drawable;", "onStart", "placeholder", "onSuccess", "result", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageRequest.kt */
public final class MarkerImageLoader$loadImages$2$deferredList$1$1$invokeSuspend$$inlined$target$1 implements Target {
    final /* synthetic */ CompletableFuture $future$inlined;
    final /* synthetic */ CompletableFuture $future$inlined$1;
    final /* synthetic */ ImageOptions $img$inlined;
    final /* synthetic */ ImageOptions $img$inlined$1;
    final /* synthetic */ ImageOptions $img$inlined$2;

    public MarkerImageLoader$loadImages$2$deferredList$1$1$invokeSuspend$$inlined$target$1(ImageOptions imageOptions, CompletableFuture completableFuture, ImageOptions imageOptions2, ImageOptions imageOptions3, CompletableFuture completableFuture2) {
        this.$img$inlined = imageOptions;
        this.$future$inlined = completableFuture;
        this.$img$inlined$1 = imageOptions2;
        this.$img$inlined$2 = imageOptions3;
        this.$future$inlined$1 = completableFuture2;
    }

    public void onStart(Drawable drawable) {
        Log.d(Constants.IMAGE_MARKER_TAG, "start to load image: " + this.$img$inlined.getUri());
    }

    public void onError(Drawable drawable) {
        this.$future$inlined.completeExceptionally(new MarkerError(ErrorCode.LOAD_IMAGE_FAILED, "Can't retrieve the file from the src: " + this.$img$inlined$1.getUri()));
    }

    public void onSuccess(Drawable drawable) {
        Bitmap scaleBitmap = ImageProcess.Companion.scaleBitmap(DrawableKt.toBitmap$default(drawable, 0, 0, (Bitmap.Config) null, 7, (Object) null), this.$img$inlined$2.getScale());
        if (scaleBitmap == null) {
            this.$future$inlined$1.completeExceptionally(new MarkerError(ErrorCode.LOAD_IMAGE_FAILED, "Can't retrieve the file from the src: " + this.$img$inlined$2.getUri()));
        }
        this.$future$inlined$1.complete(scaleBitmap);
    }
}
