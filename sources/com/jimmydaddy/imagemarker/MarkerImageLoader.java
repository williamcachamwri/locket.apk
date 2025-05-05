package com.jimmydaddy.imagemarker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import coil.ComponentRegistry;
import coil.ImageLoader;
import coil.decode.Decoder;
import coil.decode.GifDecoder;
import coil.decode.ImageDecoderDecoder;
import coil.decode.SvgDecoder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.jimmydaddy.imagemarker.base.Constants;
import com.jimmydaddy.imagemarker.base.ImageOptions;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u000fH\u0002J$\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014H@¢\u0006\u0002\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/jimmydaddy/imagemarker/MarkerImageLoader;", "", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "maxSize", "", "(Lcom/facebook/react/bridge/ReactApplicationContext;I)V", "imageLoader", "Lcoil/ImageLoader;", "resources", "Landroid/content/res/Resources;", "getResources", "()Landroid/content/res/Resources;", "getDrawableResourceByName", "name", "", "isCoilImg", "", "uri", "loadImages", "", "Landroid/graphics/Bitmap;", "images", "Lcom/jimmydaddy/imagemarker/base/ImageOptions;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MarkerImageLoader.kt */
public final class MarkerImageLoader {
    /* access modifiers changed from: private */
    public final ReactApplicationContext context;
    /* access modifiers changed from: private */
    public ImageLoader imageLoader;
    private final int maxSize;

    public MarkerImageLoader(ReactApplicationContext reactApplicationContext, int i) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
        this.context = reactApplicationContext;
        this.maxSize = i;
        ImageLoader.Builder builder = new ImageLoader.Builder((Context) reactApplicationContext);
        ComponentRegistry.Builder builder2 = new ComponentRegistry.Builder();
        if (Build.VERSION.SDK_INT >= 28) {
            builder2.add((Decoder.Factory) new ImageDecoderDecoder.Factory(false, 1, (DefaultConstructorMarker) null));
        } else {
            builder2.add((Decoder.Factory) new GifDecoder.Factory(false, 1, (DefaultConstructorMarker) null));
        }
        builder2.add((Decoder.Factory) new SvgDecoder.Factory(false, 1, (DefaultConstructorMarker) null));
        Unit unit = Unit.INSTANCE;
        this.imageLoader = builder.components(builder2.build()).allowHardware(false).build();
    }

    /* access modifiers changed from: private */
    public final Resources getResources() {
        Resources resources = this.context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
        return resources;
    }

    public final Object loadImages(List<ImageOptions> list, Continuation<? super List<Bitmap>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new MarkerImageLoader$loadImages$2(list, this, (Continuation<? super MarkerImageLoader$loadImages$2>) null), continuation);
    }

    /* access modifiers changed from: private */
    public final boolean isCoilImg(String str) {
        Intrinsics.checkNotNull(str);
        if (!StringsKt.startsWith$default(str, "http://", false, 2, (Object) null) && !StringsKt.startsWith$default(str, "https://", false, 2, (Object) null) && !StringsKt.startsWith$default(str, "file://", false, 2, (Object) null)) {
            if (!StringsKt.startsWith$default(str, "data:", false, 2, (Object) null)) {
                return false;
            }
            CharSequence charSequence = str;
            if (!StringsKt.contains$default(charSequence, (CharSequence) Constants.BASE64, false, 2, (Object) null)) {
                return false;
            }
            if (StringsKt.contains$default(charSequence, (CharSequence) "img", false, 2, (Object) null) || StringsKt.contains$default(charSequence, (CharSequence) "image", false, 2, (Object) null)) {
                return true;
            }
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final int getDrawableResourceByName(String str) {
        return getResources().getIdentifier(str, "drawable", this.context.getPackageName());
    }
}
