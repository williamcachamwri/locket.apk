package expo.modules.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.interfaces.imageloader.ImageLoaderInterface;
import java.util.List;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u0016J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u000b\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"Lexpo/modules/imageloader/ImageLoaderModule;", "Lexpo/modules/core/interfaces/InternalModule;", "Lexpo/modules/interfaces/imageloader/ImageLoaderInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getExportedInterfaces", "", "Ljava/lang/Class;", "loadImageForDisplayFromURL", "Ljava/util/concurrent/Future;", "Landroid/graphics/Bitmap;", "url", "", "", "resultListener", "Lexpo/modules/interfaces/imageloader/ImageLoaderInterface$ResultListener;", "loadImageForManipulationFromURL", "normalizeAssetsUrl", "expo-image-loader_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageLoaderModule.kt */
public final class ImageLoaderModule implements InternalModule, ImageLoaderInterface {
    private final Context context;

    public ImageLoaderModule(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final Context getContext() {
        return this.context;
    }

    public List<Class<?>> getExportedInterfaces() {
        return CollectionsKt.listOf(ImageLoaderInterface.class);
    }

    public Future<Bitmap> loadImageForDisplayFromURL(String str) {
        Intrinsics.checkNotNullParameter(str, "url");
        SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        loadImageForDisplayFromURL(str, new ImageLoaderModule$loadImageForDisplayFromURL$1(simpleSettableFuture));
        return simpleSettableFuture;
    }

    public void loadImageForDisplayFromURL(String str, ImageLoaderInterface.ResultListener resultListener) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(resultListener, "resultListener");
        Glide.with(this.context).asBitmap().load(str).into(new ImageLoaderModule$loadImageForDisplayFromURL$2(resultListener));
    }

    public Future<Bitmap> loadImageForManipulationFromURL(String str) {
        Intrinsics.checkNotNullParameter(str, "url");
        SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        loadImageForManipulationFromURL(str, new ImageLoaderModule$loadImageForManipulationFromURL$1(simpleSettableFuture));
        return simpleSettableFuture;
    }

    public void loadImageForManipulationFromURL(String str, ImageLoaderInterface.ResultListener resultListener) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(resultListener, "resultListener");
        ((RequestBuilder) ((RequestBuilder) Glide.with(this.context).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE)).skipMemoryCache(true)).load(normalizeAssetsUrl(str)).into(new ImageLoaderModule$loadImageForManipulationFromURL$2(resultListener));
    }

    private final String normalizeAssetsUrl(String str) {
        if (!StringsKt.startsWith$default(str, "asset:///", false, 2, (Object) null)) {
            return str;
        }
        return "file:///android_asset/" + CollectionsKt.last(StringsKt.split$default((CharSequence) str, new String[]{"/"}, false, 0, 6, (Object) null));
    }
}
