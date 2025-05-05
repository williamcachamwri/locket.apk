package expo.modules.imageloader;

import android.graphics.Bitmap;
import expo.modules.interfaces.imageloader.ImageLoaderInterface;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"expo/modules/imageloader/ImageLoaderModule$loadImageForManipulationFromURL$1", "Lexpo/modules/interfaces/imageloader/ImageLoaderInterface$ResultListener;", "onFailure", "", "cause", "", "onSuccess", "bitmap", "Landroid/graphics/Bitmap;", "expo-image-loader_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageLoaderModule.kt */
public final class ImageLoaderModule$loadImageForManipulationFromURL$1 implements ImageLoaderInterface.ResultListener {
    final /* synthetic */ SimpleSettableFuture<Bitmap> $future;

    ImageLoaderModule$loadImageForManipulationFromURL$1(SimpleSettableFuture<Bitmap> simpleSettableFuture) {
        this.$future = simpleSettableFuture;
    }

    public void onSuccess(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.$future.set(bitmap);
    }

    public void onFailure(Throwable th) {
        this.$future.setException(new ExecutionException(th));
    }
}
