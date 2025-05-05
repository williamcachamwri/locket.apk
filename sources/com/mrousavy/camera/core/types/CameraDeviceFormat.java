package com.mrousavy.camera.core.types;

import android.util.Size;
import androidx.camera.video.FallbackStrategy;
import androidx.camera.video.Quality;
import androidx.camera.video.QualitySelector;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.mrousavy.camera.core.InvalidTypeScriptUnionError;
import com.mrousavy.camera.core.types.VideoStabilizationMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 M2\u00020\u0001:\u0001MB{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0013¢\u0006\u0002\u0010\u0016J\t\u00107\u001a\u00020\u0003HÆ\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0003J\t\u00109\u001a\u00020\u0011HÆ\u0003J\t\u0010:\u001a\u00020\u0013HÆ\u0003J\t\u0010;\u001a\u00020\u0013HÆ\u0003J\t\u0010<\u001a\u00020\u0013HÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\bHÆ\u0003J\t\u0010A\u001a\u00020\bHÆ\u0003J\t\u0010B\u001a\u00020\bHÆ\u0003J\t\u0010C\u001a\u00020\bHÆ\u0003J\t\u0010D\u001a\u00020\bHÆ\u0003J\u0001\u0010E\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0013HÆ\u0001J\u0013\u0010F\u001a\u00020\u00132\b\u0010G\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0010\u0010H\u001a\u00020/2\u0006\u0010I\u001a\u00020\"H\u0002J\t\u0010J\u001a\u00020\u0003HÖ\u0001J\t\u0010K\u001a\u00020LHÖ\u0001R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\"8F¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00030'X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0014\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b+\u0010*R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010 R\u0011\u0010.\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b0\u00101R\u0011\u00102\u001a\u00020\"8F¢\u0006\u0006\u001a\u0004\b3\u0010$R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010 ¨\u0006N"}, d2 = {"Lcom/mrousavy/camera/core/types/CameraDeviceFormat;", "", "videoWidth", "", "videoHeight", "photoWidth", "photoHeight", "minFps", "", "maxFps", "minISO", "maxISO", "fieldOfView", "videoStabilizationModes", "", "Lcom/mrousavy/camera/core/types/VideoStabilizationMode;", "autoFocusSystem", "Lcom/mrousavy/camera/core/types/AutoFocusSystem;", "supportsVideoHdr", "", "supportsPhotoHdr", "supportsDepthCapture", "(IIIIDDDDDLjava/util/List;Lcom/mrousavy/camera/core/types/AutoFocusSystem;ZZZ)V", "getAutoFocusSystem", "()Lcom/mrousavy/camera/core/types/AutoFocusSystem;", "getFieldOfView", "()D", "getMaxFps", "getMaxISO", "getMinFps", "getMinISO", "getPhotoHeight", "()I", "photoSize", "Landroid/util/Size;", "getPhotoSize", "()Landroid/util/Size;", "getPhotoWidth", "qualitySizes", "", "Landroidx/camera/video/Quality;", "getSupportsDepthCapture", "()Z", "getSupportsPhotoHdr", "getSupportsVideoHdr", "getVideoHeight", "videoQualitySelector", "Landroidx/camera/video/QualitySelector;", "getVideoQualitySelector", "()Landroidx/camera/video/QualitySelector;", "videoSize", "getVideoSize", "getVideoStabilizationModes", "()Ljava/util/List;", "getVideoWidth", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "getQualitySelector", "size", "hashCode", "toString", "", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraDeviceFormat.kt */
public final class CameraDeviceFormat {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final AutoFocusSystem autoFocusSystem;
    private final double fieldOfView;
    private final double maxFps;
    private final double maxISO;
    private final double minFps;
    private final double minISO;
    private final int photoHeight;
    private final int photoWidth;
    private final Map<Quality, Integer> qualitySizes = MapsKt.mapOf(TuplesKt.to(Quality.SD, 345600), TuplesKt.to(Quality.HD, 921600), TuplesKt.to(Quality.FHD, 2073600), TuplesKt.to(Quality.UHD, 8294400));
    private final boolean supportsDepthCapture;
    private final boolean supportsPhotoHdr;
    private final boolean supportsVideoHdr;
    private final int videoHeight;
    private final List<VideoStabilizationMode> videoStabilizationModes;
    private final int videoWidth;

    public static /* synthetic */ CameraDeviceFormat copy$default(CameraDeviceFormat cameraDeviceFormat, int i, int i2, int i3, int i4, double d, double d2, double d3, double d4, double d5, List list, AutoFocusSystem autoFocusSystem2, boolean z, boolean z2, boolean z3, int i5, Object obj) {
        CameraDeviceFormat cameraDeviceFormat2 = cameraDeviceFormat;
        int i6 = i5;
        return cameraDeviceFormat.copy((i6 & 1) != 0 ? cameraDeviceFormat2.videoWidth : i, (i6 & 2) != 0 ? cameraDeviceFormat2.videoHeight : i2, (i6 & 4) != 0 ? cameraDeviceFormat2.photoWidth : i3, (i6 & 8) != 0 ? cameraDeviceFormat2.photoHeight : i4, (i6 & 16) != 0 ? cameraDeviceFormat2.minFps : d, (i6 & 32) != 0 ? cameraDeviceFormat2.maxFps : d2, (i6 & 64) != 0 ? cameraDeviceFormat2.minISO : d3, (i6 & 128) != 0 ? cameraDeviceFormat2.maxISO : d4, (i6 & 256) != 0 ? cameraDeviceFormat2.fieldOfView : d5, (i6 & 512) != 0 ? cameraDeviceFormat2.videoStabilizationModes : list, (i6 & 1024) != 0 ? cameraDeviceFormat2.autoFocusSystem : autoFocusSystem2, (i6 & 2048) != 0 ? cameraDeviceFormat2.supportsVideoHdr : z, (i6 & 4096) != 0 ? cameraDeviceFormat2.supportsPhotoHdr : z2, (i6 & 8192) != 0 ? cameraDeviceFormat2.supportsDepthCapture : z3);
    }

    public final int component1() {
        return this.videoWidth;
    }

    public final List<VideoStabilizationMode> component10() {
        return this.videoStabilizationModes;
    }

    public final AutoFocusSystem component11() {
        return this.autoFocusSystem;
    }

    public final boolean component12() {
        return this.supportsVideoHdr;
    }

    public final boolean component13() {
        return this.supportsPhotoHdr;
    }

    public final boolean component14() {
        return this.supportsDepthCapture;
    }

    public final int component2() {
        return this.videoHeight;
    }

    public final int component3() {
        return this.photoWidth;
    }

    public final int component4() {
        return this.photoHeight;
    }

    public final double component5() {
        return this.minFps;
    }

    public final double component6() {
        return this.maxFps;
    }

    public final double component7() {
        return this.minISO;
    }

    public final double component8() {
        return this.maxISO;
    }

    public final double component9() {
        return this.fieldOfView;
    }

    public final CameraDeviceFormat copy(int i, int i2, int i3, int i4, double d, double d2, double d3, double d4, double d5, List<? extends VideoStabilizationMode> list, AutoFocusSystem autoFocusSystem2, boolean z, boolean z2, boolean z3) {
        int i5 = i;
        Intrinsics.checkNotNullParameter(list, "videoStabilizationModes");
        Intrinsics.checkNotNullParameter(autoFocusSystem2, "autoFocusSystem");
        return new CameraDeviceFormat(i, i2, i3, i4, d, d2, d3, d4, d5, list, autoFocusSystem2, z, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraDeviceFormat)) {
            return false;
        }
        CameraDeviceFormat cameraDeviceFormat = (CameraDeviceFormat) obj;
        return this.videoWidth == cameraDeviceFormat.videoWidth && this.videoHeight == cameraDeviceFormat.videoHeight && this.photoWidth == cameraDeviceFormat.photoWidth && this.photoHeight == cameraDeviceFormat.photoHeight && Double.compare(this.minFps, cameraDeviceFormat.minFps) == 0 && Double.compare(this.maxFps, cameraDeviceFormat.maxFps) == 0 && Double.compare(this.minISO, cameraDeviceFormat.minISO) == 0 && Double.compare(this.maxISO, cameraDeviceFormat.maxISO) == 0 && Double.compare(this.fieldOfView, cameraDeviceFormat.fieldOfView) == 0 && Intrinsics.areEqual((Object) this.videoStabilizationModes, (Object) cameraDeviceFormat.videoStabilizationModes) && this.autoFocusSystem == cameraDeviceFormat.autoFocusSystem && this.supportsVideoHdr == cameraDeviceFormat.supportsVideoHdr && this.supportsPhotoHdr == cameraDeviceFormat.supportsPhotoHdr && this.supportsDepthCapture == cameraDeviceFormat.supportsDepthCapture;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((Integer.hashCode(this.videoWidth) * 31) + Integer.hashCode(this.videoHeight)) * 31) + Integer.hashCode(this.photoWidth)) * 31) + Integer.hashCode(this.photoHeight)) * 31) + Double.hashCode(this.minFps)) * 31) + Double.hashCode(this.maxFps)) * 31) + Double.hashCode(this.minISO)) * 31) + Double.hashCode(this.maxISO)) * 31) + Double.hashCode(this.fieldOfView)) * 31) + this.videoStabilizationModes.hashCode()) * 31) + this.autoFocusSystem.hashCode()) * 31) + Boolean.hashCode(this.supportsVideoHdr)) * 31) + Boolean.hashCode(this.supportsPhotoHdr)) * 31) + Boolean.hashCode(this.supportsDepthCapture);
    }

    public String toString() {
        int i = this.videoWidth;
        int i2 = this.videoHeight;
        int i3 = this.photoWidth;
        int i4 = this.photoHeight;
        double d = this.minFps;
        double d2 = this.maxFps;
        double d3 = this.minISO;
        double d4 = this.maxISO;
        double d5 = this.fieldOfView;
        List<VideoStabilizationMode> list = this.videoStabilizationModes;
        AutoFocusSystem autoFocusSystem2 = this.autoFocusSystem;
        boolean z = this.supportsVideoHdr;
        return "CameraDeviceFormat(videoWidth=" + i + ", videoHeight=" + i2 + ", photoWidth=" + i3 + ", photoHeight=" + i4 + ", minFps=" + d + ", maxFps=" + d2 + ", minISO=" + d3 + ", maxISO=" + d4 + ", fieldOfView=" + d5 + ", videoStabilizationModes=" + list + ", autoFocusSystem=" + autoFocusSystem2 + ", supportsVideoHdr=" + z + ", supportsPhotoHdr=" + this.supportsPhotoHdr + ", supportsDepthCapture=" + this.supportsDepthCapture + ")";
    }

    public CameraDeviceFormat(int i, int i2, int i3, int i4, double d, double d2, double d3, double d4, double d5, List<? extends VideoStabilizationMode> list, AutoFocusSystem autoFocusSystem2, boolean z, boolean z2, boolean z3) {
        List<? extends VideoStabilizationMode> list2 = list;
        AutoFocusSystem autoFocusSystem3 = autoFocusSystem2;
        Intrinsics.checkNotNullParameter(list2, "videoStabilizationModes");
        Intrinsics.checkNotNullParameter(autoFocusSystem3, "autoFocusSystem");
        this.videoWidth = i;
        this.videoHeight = i2;
        this.photoWidth = i3;
        this.photoHeight = i4;
        this.minFps = d;
        this.maxFps = d2;
        this.minISO = d3;
        this.maxISO = d4;
        this.fieldOfView = d5;
        this.videoStabilizationModes = list2;
        this.autoFocusSystem = autoFocusSystem3;
        this.supportsVideoHdr = z;
        this.supportsPhotoHdr = z2;
        this.supportsDepthCapture = z3;
    }

    public final int getVideoWidth() {
        return this.videoWidth;
    }

    public final int getVideoHeight() {
        return this.videoHeight;
    }

    public final int getPhotoWidth() {
        return this.photoWidth;
    }

    public final int getPhotoHeight() {
        return this.photoHeight;
    }

    public final double getMinFps() {
        return this.minFps;
    }

    public final double getMaxFps() {
        return this.maxFps;
    }

    public final double getMinISO() {
        return this.minISO;
    }

    public final double getMaxISO() {
        return this.maxISO;
    }

    public final double getFieldOfView() {
        return this.fieldOfView;
    }

    public final List<VideoStabilizationMode> getVideoStabilizationModes() {
        return this.videoStabilizationModes;
    }

    public final AutoFocusSystem getAutoFocusSystem() {
        return this.autoFocusSystem;
    }

    public final boolean getSupportsVideoHdr() {
        return this.supportsVideoHdr;
    }

    public final boolean getSupportsPhotoHdr() {
        return this.supportsPhotoHdr;
    }

    public final boolean getSupportsDepthCapture() {
        return this.supportsDepthCapture;
    }

    public final Size getPhotoSize() {
        return new Size(this.photoWidth, this.photoHeight);
    }

    public final Size getVideoSize() {
        return new Size(this.videoWidth, this.videoHeight);
    }

    private final QualitySelector getQualitySelector(Size size) {
        int width = size.getWidth() * size.getHeight();
        Iterator it = this.qualitySizes.entrySet().iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (!it.hasNext()) {
                Quality quality = (Quality) ((Map.Entry) next).getKey();
                QualitySelector from = QualitySelector.from(quality, FallbackStrategy.higherQualityOrLowerThan(quality));
                Intrinsics.checkNotNullExpressionValue(from, "from(...)");
            } else {
                int abs = Math.abs(width - ((Number) ((Map.Entry) next).getValue()).intValue());
                do {
                    Object next2 = it.next();
                    int abs2 = Math.abs(width - ((Number) ((Map.Entry) next2).getValue()).intValue());
                    if (abs > abs2) {
                        next = next2;
                        abs = abs2;
                    }
                } while (it.hasNext());
            }
            Quality quality2 = (Quality) ((Map.Entry) next).getKey();
            QualitySelector from2 = QualitySelector.from(quality2, FallbackStrategy.higherQualityOrLowerThan(quality2));
            Intrinsics.checkNotNullExpressionValue(from2, "from(...)");
            return from2;
        }
        throw new NoSuchElementException();
    }

    public final QualitySelector getVideoQualitySelector() {
        return getQualitySelector(getVideoSize());
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/mrousavy/camera/core/types/CameraDeviceFormat$Companion;", "", "()V", "fromJSValue", "Lcom/mrousavy/camera/core/types/CameraDeviceFormat;", "value", "Lcom/facebook/react/bridge/ReadableMap;", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraDeviceFormat.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CameraDeviceFormat fromJSValue(ReadableMap readableMap) {
            ReadableMap readableMap2 = readableMap;
            Intrinsics.checkNotNullParameter(readableMap2, "value");
            ReadableArray array = readableMap2.getArray("videoStabilizationModes");
            if (array != null) {
                ArrayList<Object> arrayList = array.toArrayList();
                Intrinsics.checkNotNullExpressionValue(arrayList, "toArrayList(...)");
                Iterable iterable = arrayList;
                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Object next : iterable) {
                    VideoStabilizationMode.Companion companion = VideoStabilizationMode.Companion;
                    Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.String");
                    arrayList2.add(companion.fromUnionValue((String) next));
                }
                AutoFocusSystem fromUnionValue = AutoFocusSystem.Companion.fromUnionValue(readableMap2.getString("autoFocusSystem"));
                return new CameraDeviceFormat(readableMap2.getInt("videoWidth"), readableMap2.getInt("videoHeight"), readableMap2.getInt("photoWidth"), readableMap2.getInt("photoHeight"), readableMap2.getDouble("minFps"), readableMap2.getDouble("maxFps"), readableMap2.getDouble("minISO"), readableMap2.getDouble("maxISO"), readableMap2.getDouble("fieldOfView"), (List) arrayList2, fromUnionValue, readableMap2.getBoolean("supportsVideoHdr"), readableMap2.getBoolean("supportsPhotoHdr"), readableMap2.getBoolean("supportsDepthCapture"));
            }
            throw new InvalidTypeScriptUnionError("format", readableMap.toString());
        }
    }
}
