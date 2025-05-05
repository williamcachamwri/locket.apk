package com.jimmydaddy.imagemarker.base;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/MarkImageOptions;", "Lcom/jimmydaddy/imagemarker/base/Options;", "options", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "watermarkImages", "", "Lcom/jimmydaddy/imagemarker/base/WatermarkImageOptions;", "getWatermarkImages", "()[Lcom/jimmydaddy/imagemarker/base/WatermarkImageOptions;", "setWatermarkImages", "([Lcom/jimmydaddy/imagemarker/base/WatermarkImageOptions;)V", "[Lcom/jimmydaddy/imagemarker/base/WatermarkImageOptions;", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MarkImageOptions.kt */
public final class MarkImageOptions extends Options {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private WatermarkImageOptions[] watermarkImages;

    @JvmStatic
    public static final MarkImageOptions checkParams(ReadableMap readableMap, Promise promise) {
        return Companion.checkParams(readableMap, promise);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MarkImageOptions(ReadableMap readableMap) {
        super(readableMap);
        Intrinsics.checkNotNullParameter(readableMap, "options");
        ReadableMap map = readableMap.getMap("watermarkImage");
        ReadableArray array = readableMap.getArray("watermarkImages");
        if ((array == null || array.size() <= 0) && map == null) {
            throw new MarkerError(ErrorCode.PARAMS_REQUIRED, "marker image is required");
        }
        ArrayList arrayList = new ArrayList();
        if (array != null && array.size() > 0) {
            int size = array.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(new WatermarkImageOptions(array.getMap(i)));
            }
        }
        if (map != null) {
            ImageOptions imageOptions = new ImageOptions(map);
            PositionEnum positionEnum = null;
            ReadableMap map2 = readableMap.getMap("watermarkPositions") != null ? readableMap.getMap("watermarkPositions") : null;
            Intrinsics.checkNotNull(map2);
            arrayList.add(new WatermarkImageOptions(imageOptions, map2.hasKey("X") ? Utils.Companion.handleDynamicToString(map2.getDynamic("X")) : null, map2.hasKey("Y") ? Utils.Companion.handleDynamicToString(map2.getDynamic("Y")) : null, map2.getString(ViewProps.POSITION) != null ? PositionEnum.Companion.getPosition(map2.getString(ViewProps.POSITION)) : positionEnum));
        }
        this.watermarkImages = (WatermarkImageOptions[]) arrayList.toArray(new WatermarkImageOptions[0]);
    }

    public final WatermarkImageOptions[] getWatermarkImages() {
        return this.watermarkImages;
    }

    public final void setWatermarkImages(WatermarkImageOptions[] watermarkImageOptionsArr) {
        Intrinsics.checkNotNullParameter(watermarkImageOptionsArr, "<set-?>");
        this.watermarkImages = watermarkImageOptionsArr;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/MarkImageOptions$Companion;", "", "()V", "checkParams", "Lcom/jimmydaddy/imagemarker/base/MarkImageOptions;", "opts", "Lcom/facebook/react/bridge/ReadableMap;", "promise", "Lcom/facebook/react/bridge/Promise;", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MarkImageOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final MarkImageOptions checkParams(ReadableMap readableMap, Promise promise) {
            Intrinsics.checkNotNullParameter(readableMap, "opts");
            Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
            try {
                return new MarkImageOptions(readableMap);
            } catch (MarkerError e) {
                promise.reject(e.getErrorCode(), e.functionOfKotlin());
                return null;
            }
        }
    }
}
