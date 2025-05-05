package com.jimmydaddy.imagemarker.base;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R$\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X.¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/MarkTextOptions;", "Lcom/jimmydaddy/imagemarker/base/Options;", "options", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "watermarkTexts", "", "Lcom/jimmydaddy/imagemarker/base/TextOptions;", "getWatermarkTexts", "()[Lcom/jimmydaddy/imagemarker/base/TextOptions;", "setWatermarkTexts", "([Lcom/jimmydaddy/imagemarker/base/TextOptions;)V", "[Lcom/jimmydaddy/imagemarker/base/TextOptions;", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MarkTextOptions.kt */
public final class MarkTextOptions extends Options {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public TextOptions[] watermarkTexts;

    @JvmStatic
    public static final MarkTextOptions checkParams(ReadableMap readableMap, Promise promise) {
        return Companion.checkParams(readableMap, promise);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MarkTextOptions(ReadableMap readableMap) {
        super(readableMap);
        Intrinsics.checkNotNullParameter(readableMap, "options");
        ReadableArray array = readableMap.getArray("watermarkTexts");
        Intrinsics.checkNotNull(array);
        if (array.size() > 0) {
            setWatermarkTexts(new TextOptions[array.size()]);
            int size = array.size();
            for (int i = 0; i < size; i++) {
                ReadableMap map = array.getMap(i);
                Intrinsics.checkNotNullExpressionValue(map, "getMap(...)");
                getWatermarkTexts()[i] = new TextOptions(map);
            }
        }
    }

    public final TextOptions[] getWatermarkTexts() {
        TextOptions[] textOptionsArr = this.watermarkTexts;
        if (textOptionsArr != null) {
            return textOptionsArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("watermarkTexts");
        return null;
    }

    public final void setWatermarkTexts(TextOptions[] textOptionsArr) {
        Intrinsics.checkNotNullParameter(textOptionsArr, "<set-?>");
        this.watermarkTexts = textOptionsArr;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/MarkTextOptions$Companion;", "", "()V", "checkParams", "Lcom/jimmydaddy/imagemarker/base/MarkTextOptions;", "opts", "Lcom/facebook/react/bridge/ReadableMap;", "promise", "Lcom/facebook/react/bridge/Promise;", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MarkTextOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final MarkTextOptions checkParams(ReadableMap readableMap, Promise promise) {
            Intrinsics.checkNotNullParameter(readableMap, "opts");
            Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
            try {
                return new MarkTextOptions(readableMap);
            } catch (MarkerError e) {
                promise.reject(e.getErrorCode(), e.functionOfKotlin());
                return null;
            }
        }
    }
}
