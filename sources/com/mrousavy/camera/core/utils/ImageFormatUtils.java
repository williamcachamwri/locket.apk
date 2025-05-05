package com.mrousavy.camera.core.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/mrousavy/camera/core/utils/ImageFormatUtils;", "", "()V", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageFormatUtils.kt */
public final class ImageFormatUtils {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/mrousavy/camera/core/utils/ImageFormatUtils$Companion;", "", "()V", "imageFormatToString", "", "format", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ImageFormatUtils.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String imageFormatToString(int i) {
            if (i == 3) {
                return "RGB_888";
            }
            if (i == 4) {
                return "RGB_565";
            }
            if (i == 16) {
                return "NV16";
            }
            if (i == 17) {
                return "NV21";
            }
            if (i == 20) {
                return "YUY2";
            }
            if (i == 54) {
                return "YCBCR_P010";
            }
            if (i == 256) {
                return "JPEG";
            }
            if (i == 538982489) {
                return "Y8";
            }
            if (i == 842094169) {
                return "YV12";
            }
            if (i == 34) {
                return "PRIVATE";
            }
            if (i == 35) {
                return "YUV_420_888";
            }
            switch (i) {
                case 39:
                    return "YUV_422_888";
                case 40:
                    return "YUV_444_888";
                case 41:
                    return "FLEX_RGB_888";
                case 42:
                    return "FLEX_RGBA_8888";
                default:
                    return "UNKNOWN (" + i + ")";
            }
        }
    }
}
