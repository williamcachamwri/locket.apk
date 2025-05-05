package expo.modules.imagemanipulator;

import android.graphics.Bitmap;
import expo.modules.imagemanipulator.arguments.Actions;
import expo.modules.interfaces.imageloader.ImageLoaderInterface;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"expo/modules/imagemanipulator/ImageManipulatorModule$definition$1$1$1", "Lexpo/modules/interfaces/imageloader/ImageLoaderInterface$ResultListener;", "onFailure", "", "cause", "", "onSuccess", "bitmap", "Landroid/graphics/Bitmap;", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageManipulatorModule.kt */
public final class ImageManipulatorModule$definition$1$1$1 implements ImageLoaderInterface.ResultListener {
    final /* synthetic */ Actions $actions;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ SaveOptions $saveOptions;
    final /* synthetic */ String $uri;
    final /* synthetic */ ImageManipulatorModule this$0;

    ImageManipulatorModule$definition$1$1$1(ImageManipulatorModule imageManipulatorModule, Actions actions, SaveOptions saveOptions, Promise promise, String str) {
        this.this$0 = imageManipulatorModule;
        this.$actions = actions;
        this.$saveOptions = saveOptions;
        this.$promise = promise;
        this.$uri = str;
    }

    public void onSuccess(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.this$0.runActions(bitmap, this.$actions, this.$saveOptions, this.$promise);
    }

    public void onFailure(Throwable th) {
        this.$promise.reject(new ImageDecodeException(this.$uri, th));
    }
}
