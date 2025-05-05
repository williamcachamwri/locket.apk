package com.jimmydaddy.imagemarker.base;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0015\"\u0004\b\u001c\u0010\u0017R\u001a\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/Options;", "", "options", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "backgroundImage", "Lcom/jimmydaddy/imagemarker/base/ImageOptions;", "getBackgroundImage", "()Lcom/jimmydaddy/imagemarker/base/ImageOptions;", "setBackgroundImage", "(Lcom/jimmydaddy/imagemarker/base/ImageOptions;)V", "backgroundImageOpts", "filename", "", "getFilename", "()Ljava/lang/String;", "setFilename", "(Ljava/lang/String;)V", "maxSize", "", "getMaxSize", "()I", "setMaxSize", "(I)V", "getOptions", "()Lcom/facebook/react/bridge/ReadableMap;", "quality", "getQuality", "setQuality", "saveFormat", "Lcom/jimmydaddy/imagemarker/base/SaveFormat;", "getSaveFormat", "()Lcom/jimmydaddy/imagemarker/base/SaveFormat;", "setSaveFormat", "(Lcom/jimmydaddy/imagemarker/base/SaveFormat;)V", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Options.kt */
public class Options {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String PROP_ICON_URI = "uri";
    private ImageOptions backgroundImage;
    private ReadableMap backgroundImageOpts;
    private String filename;
    private int maxSize;
    private final ReadableMap options;
    private int quality;
    private SaveFormat saveFormat;

    public Options(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "options");
        this.options = readableMap;
        ReadableMap map = readableMap.getMap("backgroundImage");
        this.backgroundImageOpts = map;
        if (map != null) {
            ReadableMap readableMap2 = this.backgroundImageOpts;
            Intrinsics.checkNotNull(readableMap2);
            this.backgroundImage = new ImageOptions(readableMap2);
            this.quality = readableMap.hasKey("quality") ? readableMap.getInt("quality") : 100;
            this.maxSize = readableMap.hasKey("maxSize") ? readableMap.getInt("maxSize") : 2048;
            this.filename = readableMap.getString("filename");
            this.saveFormat = SaveFormat.Companion.getFormat(readableMap.getString("saveFormat"));
            return;
        }
        throw new MarkerError(ErrorCode.PARAMS_REQUIRED, "backgroundImage is required");
    }

    public final ReadableMap getOptions() {
        return this.options;
    }

    public final ImageOptions getBackgroundImage() {
        return this.backgroundImage;
    }

    public final void setBackgroundImage(ImageOptions imageOptions) {
        Intrinsics.checkNotNullParameter(imageOptions, "<set-?>");
        this.backgroundImage = imageOptions;
    }

    public final int getQuality() {
        return this.quality;
    }

    public final void setQuality(int i) {
        this.quality = i;
    }

    public final String getFilename() {
        return this.filename;
    }

    public final void setFilename(String str) {
        this.filename = str;
    }

    public final SaveFormat getSaveFormat() {
        return this.saveFormat;
    }

    public final void setSaveFormat(SaveFormat saveFormat2) {
        Intrinsics.checkNotNullParameter(saveFormat2, "<set-?>");
        this.saveFormat = saveFormat2;
    }

    public final int getMaxSize() {
        return this.maxSize;
    }

    public final void setMaxSize(int i) {
        this.maxSize = i;
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/Options$Companion;", "", "()V", "PROP_ICON_URI", "", "checkParams", "Lcom/jimmydaddy/imagemarker/base/Options;", "opts", "Lcom/facebook/react/bridge/ReadableMap;", "promise", "Lcom/facebook/react/bridge/Promise;", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Options.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Options checkParams(ReadableMap readableMap, Promise promise) {
            Intrinsics.checkNotNullParameter(readableMap, "opts");
            Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
            try {
                return new Options(readableMap);
            } catch (MarkerError e) {
                promise.reject(e.getErrorCode(), e.functionOfKotlin());
                return null;
            }
        }
    }
}
