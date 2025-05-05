package com.jimmydaddy.imagemarker.base;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u001e\u001a\u00020\u001fR\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/ImageOptions;", "", "options", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "alpha", "", "getOptions", "()Lcom/facebook/react/bridge/ReadableMap;", "rotate", "", "getRotate", "()F", "setRotate", "(F)V", "scale", "getScale", "setScale", "src", "Lcom/jimmydaddy/imagemarker/base/RNImageSRC;", "getSrc", "()Lcom/jimmydaddy/imagemarker/base/RNImageSRC;", "setSrc", "(Lcom/jimmydaddy/imagemarker/base/RNImageSRC;)V", "uri", "", "getUri", "()Ljava/lang/String;", "setUri", "(Ljava/lang/String;)V", "applyStyle", "Landroid/graphics/Paint;", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageOptions.kt */
public final class ImageOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String PROP_ICON_URI = "uri";
    private int alpha;
    private final ReadableMap options;
    private float rotate;
    private float scale;
    private RNImageSRC src;
    private String uri;

    public ImageOptions(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "options");
        this.options = readableMap;
        if (readableMap.hasKey("src")) {
            ReadableMap map = readableMap.getMap("src");
            this.src = new RNImageSRC(map);
            Intrinsics.checkNotNull(map);
            this.uri = map.getString("uri");
            this.scale = readableMap.hasKey("scale") ? (float) readableMap.getDouble("scale") : 1.0f;
            this.rotate = readableMap.hasKey("rotate") ? (float) readableMap.getInt("rotate") : 0.0f;
            this.alpha = readableMap.hasKey("alpha") ? (int) (readableMap.getDouble("alpha") * ((double) 255)) : 255;
            return;
        }
        throw new MarkerError(ErrorCode.PARAMS_REQUIRED, "image is required");
    }

    public final ReadableMap getOptions() {
        return this.options;
    }

    public final RNImageSRC getSrc() {
        return this.src;
    }

    public final void setSrc(RNImageSRC rNImageSRC) {
        Intrinsics.checkNotNullParameter(rNImageSRC, "<set-?>");
        this.src = rNImageSRC;
    }

    public final String getUri() {
        return this.uri;
    }

    public final void setUri(String str) {
        this.uri = str;
    }

    public final float getScale() {
        return this.scale;
    }

    public final void setScale(float f) {
        this.scale = f;
    }

    public final float getRotate() {
        return this.rotate;
    }

    public final void setRotate(float f) {
        this.rotate = f;
    }

    public final Paint applyStyle() {
        Paint paint = new Paint();
        paint.setAlpha(this.alpha);
        paint.setDither(true);
        paint.setColorFilter(new PorterDuffColorFilter(0, PorterDuff.Mode.OVERLAY));
        return paint;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/ImageOptions$Companion;", "", "()V", "PROP_ICON_URI", "", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ImageOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
