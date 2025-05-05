package com.brentvatne.react;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaDrm;
import android.media.MediaFormat;
import android.media.UnsupportedSchemeException;
import android.os.Build;
import androidx.media3.common.MimeTypes;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J,\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u0012"}, d2 = {"Lcom/brentvatne/react/VideoDecoderInfoModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "getName", "", "getWidevineLevel", "", "p", "Lcom/facebook/react/bridge/Promise;", "isCodecSupported", "mimeType", "width", "", "height", "isHEVCSupported", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoDecoderInfoModule.kt */
public final class VideoDecoderInfoModule extends ReactContextBaseJavaModule {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String REACT_CLASS = "VideoDecoderInfoModule";
    private static final String SECURITY_LEVEL_PROPERTY = "securityLevel";
    private static final UUID WIDEVINE_UUID = new UUID(-1301668207276963122L, -6645017420763422227L);

    public String getName() {
        return REACT_CLASS;
    }

    public VideoDecoderInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public final void getWidevineLevel(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "p");
        int i = 0;
        try {
            String propertyString = new MediaDrm(WIDEVINE_UUID).getPropertyString(SECURITY_LEVEL_PROPERTY);
            Intrinsics.checkNotNullExpressionValue(propertyString, "getPropertyString(...)");
            switch (propertyString.hashCode()) {
                case 2405:
                    if (propertyString.equals("L1")) {
                        i = 1;
                        break;
                    }
                    break;
                case 2406:
                    if (propertyString.equals("L2")) {
                        i = 2;
                        break;
                    } else {
                        break;
                    }
                case 2407:
                    if (propertyString.equals("L3")) {
                        i = 3;
                        break;
                    } else {
                        break;
                    }
            }
        } catch (UnsupportedSchemeException e) {
            e.printStackTrace();
        }
        promise.resolve(Integer.valueOf(i));
    }

    @ReactMethod
    public final void isCodecSupported(String str, double d, double d2, Promise promise) {
        boolean z = false;
        MediaCodecList mediaCodecList = new MediaCodecList(0);
        Intrinsics.checkNotNull(str);
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat(str, (int) d, (int) d2);
        Intrinsics.checkNotNullExpressionValue(createVideoFormat, "createVideoFormat(...)");
        String findDecoderForFormat = mediaCodecList.findDecoderForFormat(createVideoFormat);
        if (findDecoderForFormat != null) {
            String str2 = "software";
            if (Build.VERSION.SDK_INT >= 29) {
                MediaCodecInfo[] codecInfos = mediaCodecList.getCodecInfos();
                Intrinsics.checkNotNullExpressionValue(codecInfos, "getCodecInfos(...)");
                Object[] objArr = (Object[]) codecInfos;
                int length = objArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) objArr[i];
                    if (StringsKt.equals(mediaCodecInfo.getName(), findDecoderForFormat, true) && mediaCodecInfo.isHardwareAccelerated()) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (promise != null) {
                    if (!z) {
                        str2 = "hardware";
                    }
                    promise.resolve(str2);
                }
            } else if (promise != null) {
                promise.resolve(str2);
            }
        } else if (promise != null) {
            promise.resolve("unsupported");
        }
    }

    @ReactMethod
    public final void isHEVCSupported(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "p");
        isCodecSupported(MimeTypes.VIDEO_H265, 1920.0d, 1080.0d, promise);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/brentvatne/react/VideoDecoderInfoModule$Companion;", "", "()V", "REACT_CLASS", "", "SECURITY_LEVEL_PROPERTY", "WIDEVINE_UUID", "Ljava/util/UUID;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoDecoderInfoModule.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
