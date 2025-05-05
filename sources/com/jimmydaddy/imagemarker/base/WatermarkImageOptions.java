package com.jimmydaddy.imagemarker.base;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 '2\u00020\u0001:\u0001'B-\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u000f\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u0015\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\r\u001a\u00020\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001c¨\u0006("}, d2 = {"Lcom/jimmydaddy/imagemarker/base/WatermarkImageOptions;", "", "watermarkImage", "Lcom/jimmydaddy/imagemarker/base/ImageOptions;", "x", "", "y", "position", "Lcom/jimmydaddy/imagemarker/base/PositionEnum;", "(Lcom/jimmydaddy/imagemarker/base/ImageOptions;Ljava/lang/String;Ljava/lang/String;Lcom/jimmydaddy/imagemarker/base/PositionEnum;)V", "options", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "imageOption", "getImageOption", "()Lcom/jimmydaddy/imagemarker/base/ImageOptions;", "setImageOption", "(Lcom/jimmydaddy/imagemarker/base/ImageOptions;)V", "getOptions", "()Lcom/facebook/react/bridge/ReadableMap;", "positionEnum", "getPositionEnum", "()Lcom/jimmydaddy/imagemarker/base/PositionEnum;", "setPositionEnum", "(Lcom/jimmydaddy/imagemarker/base/PositionEnum;)V", "getX", "()Ljava/lang/String;", "setX", "(Ljava/lang/String;)V", "getY", "setY", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WatermarkImageOptions.kt */
public final class WatermarkImageOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public ImageOptions imageOption;
    private final ReadableMap options;
    private PositionEnum positionEnum;
    private String x;
    private String y;

    @JvmStatic
    public static final WatermarkImageOptions checkWatermarkImageParams(ReadableMap readableMap, Function3<? super String, ? super String, ? super Throwable, Unit> function3) {
        return Companion.checkWatermarkImageParams(readableMap, function3);
    }

    public static /* synthetic */ WatermarkImageOptions copy$default(WatermarkImageOptions watermarkImageOptions, ReadableMap readableMap, int i, Object obj) {
        if ((i & 1) != 0) {
            readableMap = watermarkImageOptions.options;
        }
        return watermarkImageOptions.copy(readableMap);
    }

    public final ReadableMap component1() {
        return this.options;
    }

    public final WatermarkImageOptions copy(ReadableMap readableMap) {
        return new WatermarkImageOptions(readableMap);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WatermarkImageOptions) && Intrinsics.areEqual((Object) this.options, (Object) ((WatermarkImageOptions) obj).options);
    }

    public int hashCode() {
        ReadableMap readableMap = this.options;
        if (readableMap == null) {
            return 0;
        }
        return readableMap.hashCode();
    }

    public String toString() {
        return "WatermarkImageOptions(options=" + this.options + ")";
    }

    public WatermarkImageOptions(ReadableMap readableMap) {
        this.options = readableMap;
        if (readableMap != null) {
            setImageOption(new ImageOptions(readableMap));
            PositionEnum positionEnum2 = null;
            ReadableMap map = readableMap.getMap(ViewProps.POSITION) != null ? readableMap.getMap(ViewProps.POSITION) : null;
            Intrinsics.checkNotNull(map);
            this.x = map.hasKey("X") ? Utils.Companion.handleDynamicToString(map.getDynamic("X")) : null;
            this.y = map.hasKey("Y") ? Utils.Companion.handleDynamicToString(map.getDynamic("Y")) : null;
            this.positionEnum = map.getString(ViewProps.POSITION) != null ? PositionEnum.Companion.getPosition(map.getString(ViewProps.POSITION)) : positionEnum2;
        }
    }

    public final ReadableMap getOptions() {
        return this.options;
    }

    public final ImageOptions getImageOption() {
        ImageOptions imageOptions = this.imageOption;
        if (imageOptions != null) {
            return imageOptions;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imageOption");
        return null;
    }

    public final void setImageOption(ImageOptions imageOptions) {
        Intrinsics.checkNotNullParameter(imageOptions, "<set-?>");
        this.imageOption = imageOptions;
    }

    public final String getX() {
        return this.x;
    }

    public final void setX(String str) {
        this.x = str;
    }

    public final String getY() {
        return this.y;
    }

    public final void setY(String str) {
        this.y = str;
    }

    public final PositionEnum getPositionEnum() {
        return this.positionEnum;
    }

    public final void setPositionEnum(PositionEnum positionEnum2) {
        this.positionEnum = positionEnum2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WatermarkImageOptions(ImageOptions imageOptions, String str, String str2, PositionEnum positionEnum2) {
        this((ReadableMap) null);
        Intrinsics.checkNotNullParameter(imageOptions, "watermarkImage");
        setImageOption(imageOptions);
        this.x = str;
        this.y = str2;
        this.positionEnum = positionEnum2;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062 \u0010\u0007\u001a\u001c\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u000b0\bH\u0007¨\u0006\f"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/WatermarkImageOptions$Companion;", "", "()V", "checkWatermarkImageParams", "Lcom/jimmydaddy/imagemarker/base/WatermarkImageOptions;", "opts", "Lcom/facebook/react/bridge/ReadableMap;", "reject", "Lkotlin/Function3;", "", "", "", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: WatermarkImageOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final WatermarkImageOptions checkWatermarkImageParams(ReadableMap readableMap, Function3<? super String, ? super String, ? super Throwable, Unit> function3) {
            Intrinsics.checkNotNullParameter(readableMap, "opts");
            Intrinsics.checkNotNullParameter(function3, "reject");
            try {
                return new WatermarkImageOptions(readableMap);
            } catch (Throwable th) {
                String localizedMessage = th.getLocalizedMessage();
                if (localizedMessage != null) {
                    String message = th.getMessage();
                    if (message == null) {
                        message = "";
                    }
                    function3.invoke(message, localizedMessage, null);
                }
                return null;
            }
        }
    }
}
