package com.mrousavy.camera.core;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Range;
import android.util.Size;
import android.util.SizeF;
import androidx.camera.camera2.internal.Camera2CameraInfoImpl;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.PreviewCapabilities;
import androidx.camera.core.SurfaceOrientedMeteringPointFactory;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.video.Quality;
import androidx.camera.video.VideoCapabilities;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import com.mrousavy.camera.core.types.AutoFocusSystem;
import com.mrousavy.camera.core.types.DeviceType;
import com.mrousavy.camera.core.types.HardwareLevel;
import com.mrousavy.camera.core.types.Orientation;
import com.mrousavy.camera.core.types.Position;
import com.mrousavy.camera.core.types.VideoStabilizationMode;
import com.mrousavy.camera.core.utils.CamcorderProfileUtils;
import com.mrousavy.camera.react.extensions.List_toJSValueKt;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0006\b\u0007\u0018\u0000 L2\u00020\u0001:\u0001LB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J&\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002072\f\u00109\u001a\b\u0012\u0004\u0012\u00020\f0\u0018H\u0002J\b\u0010:\u001a\u00020;H\u0002J\u000e\u0010<\u001a\b\u0012\u0004\u0012\u00020>0=H\u0002J\u0018\u0010?\u001a\u00020\u001c2\u0006\u0010@\u001a\u00020\u001e2\u0006\u0010A\u001a\u00020BH\u0002J\b\u0010C\u001a\u00020;H\u0002J\u000e\u0010D\u001a\b\u0012\u0004\u0012\u00020\f0\u0018H\u0002J\b\u0010E\u001a\u00020\u001cH\u0002J\u0018\u0010E\u001a\u00020\u001c2\u0006\u0010F\u001a\u00020G2\u0006\u0010A\u001a\u00020BH\u0002J\b\u0010H\u001a\u00020\u001cH\u0002J\b\u0010I\u001a\u00020\u0015H\u0002J\b\u0010J\u001a\u00020\u0015H\u0002J\u0006\u0010K\u001a\u000205R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0018X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0019\u001a\n \u001a*\u0004\u0018\u00010\f0\fX\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001f\u001a\n \u001a*\u0004\u0018\u00010\f0\fX\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010 \u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010#\u001a\u0010\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u000f0\u000f0$X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0015XD¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0015XD¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0004¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/mrousavy/camera/core/CameraDeviceDetails;", "", "cameraInfo", "Landroidx/camera/core/CameraInfo;", "extensionsManager", "Landroidx/camera/extensions/ExtensionsManager;", "(Landroidx/camera/core/CameraInfo;Landroidx/camera/extensions/ExtensionsManager;)V", "autoFocusSystem", "Lcom/mrousavy/camera/core/types/AutoFocusSystem;", "camera2Details", "Landroidx/camera/camera2/internal/Camera2CameraInfoImpl;", "cameraHardwareLevel", "", "Ljava/lang/Integer;", "cameraId", "", "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "hardwareLevel", "Lcom/mrousavy/camera/core/types/HardwareLevel;", "hasFlash", "", "isMultiCam", "isoRange", "Landroid/util/Range;", "maxExposure", "kotlin.jvm.PlatformType", "maxFieldOfView", "", "maxZoom", "", "minExposure", "minFocusDistance", "minZoom", "name", "physicalDeviceIds", "", "position", "Lcom/mrousavy/camera/core/types/Position;", "previewCapabilities", "Landroidx/camera/core/PreviewCapabilities;", "sensorOrientation", "Lcom/mrousavy/camera/core/types/Orientation;", "sensorRotationDegrees", "supports10BitHdr", "supportsDepthCapture", "supportsFocus", "supportsHdrExtension", "supportsLowLightBoostExtension", "supportsRawCapture", "videoCapabilities", "Landroidx/camera/video/VideoCapabilities;", "buildFormatMap", "Lcom/facebook/react/bridge/ReadableMap;", "photoSize", "Landroid/util/Size;", "videoSize", "fpsRange", "createStabilizationModes", "Lcom/facebook/react/bridge/ReadableArray;", "getDeviceTypes", "", "Lcom/mrousavy/camera/core/types/DeviceType;", "getFieldOfView", "focalLength", "sensorSize", "Landroid/util/SizeF;", "getFormats", "getIsoRange", "getMaxFieldOfView", "focalLengths", "", "getMinFocusDistanceCm", "getSupports10BitHDR", "getSupportsFocus", "toMap", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraDeviceDetails.kt */
public final class CameraDeviceDetails {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "CameraDeviceDetails";
    private final AutoFocusSystem autoFocusSystem;
    private final Camera2CameraInfoImpl camera2Details;
    private final Integer cameraHardwareLevel;
    private final String cameraId;
    private final CameraInfo cameraInfo;
    private final CameraInfoInternal cameraInfoInternal;
    private final HardwareLevel hardwareLevel;
    private final boolean hasFlash;
    private final boolean isMultiCam;
    private final Range<Integer> isoRange;
    private final Integer maxExposure;
    private final double maxFieldOfView;
    private final float maxZoom;
    private final Integer minExposure;
    private final double minFocusDistance;
    private final float minZoom;
    private final String name;
    private final Set<String> physicalDeviceIds;
    private final Position position;
    private final PreviewCapabilities previewCapabilities;
    private final Orientation sensorOrientation;
    private final int sensorRotationDegrees;
    private final boolean supports10BitHdr;
    private final boolean supportsDepthCapture;
    private final boolean supportsFocus;
    private final boolean supportsHdrExtension;
    private final boolean supportsLowLightBoostExtension;
    private final boolean supportsRawCapture;
    private final VideoCapabilities videoCapabilities;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v38, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.lang.Integer} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CameraDeviceDetails(androidx.camera.core.CameraInfo r6, androidx.camera.extensions.ExtensionsManager r7) {
        /*
            r5 = this;
            java.lang.String r0 = "cameraInfo"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "extensionsManager"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r5.<init>()
            r5.cameraInfo = r6
            java.lang.String r0 = com.mrousavy.camera.core.extensions.CameraInfo_idKt.getId(r6)
            if (r0 == 0) goto L_0x0153
            r5.cameraId = r0
            com.mrousavy.camera.core.types.Position$Companion r1 = com.mrousavy.camera.core.types.Position.Companion
            int r2 = r6.getLensFacing()
            com.mrousavy.camera.core.types.Position r1 = r1.fromLensFacing(r2)
            r5.position = r1
            java.lang.String r2 = r6.getImplementationType()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r3 = " ("
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = ") "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r5.name = r0
            boolean r0 = r6.hasFlashUnit()
            r5.hasFlash = r0
            androidx.lifecycle.LiveData r0 = r6.getZoomState()
            java.lang.Object r0 = r0.getValue()
            androidx.camera.core.ZoomState r0 = (androidx.camera.core.ZoomState) r0
            if (r0 == 0) goto L_0x0061
            float r0 = r0.getMinZoomRatio()
            goto L_0x0062
        L_0x0061:
            r0 = 0
        L_0x0062:
            r5.minZoom = r0
            androidx.lifecycle.LiveData r0 = r6.getZoomState()
            java.lang.Object r0 = r0.getValue()
            androidx.camera.core.ZoomState r0 = (androidx.camera.core.ZoomState) r0
            if (r0 == 0) goto L_0x0075
            float r0 = r0.getMaxZoomRatio()
            goto L_0x0077
        L_0x0075:
            r0 = 1065353216(0x3f800000, float:1.0)
        L_0x0077:
            r5.maxZoom = r0
            androidx.camera.core.ExposureState r0 = r6.getExposureState()
            android.util.Range r0 = r0.getExposureCompensationRange()
            java.lang.Comparable r0 = r0.getLower()
            java.lang.Integer r0 = (java.lang.Integer) r0
            r5.minExposure = r0
            androidx.camera.core.ExposureState r0 = r6.getExposureState()
            android.util.Range r0 = r0.getExposureCompensationRange()
            java.lang.Comparable r0 = r0.getUpper()
            java.lang.Integer r0 = (java.lang.Integer) r0
            r5.maxExposure = r0
            boolean r0 = r5.getSupportsFocus()
            r5.supportsFocus = r0
            if (r0 == 0) goto L_0x00a4
            com.mrousavy.camera.core.types.AutoFocusSystem r0 = com.mrousavy.camera.core.types.AutoFocusSystem.CONTRAST_DETECTION
            goto L_0x00a6
        L_0x00a4:
            com.mrousavy.camera.core.types.AutoFocusSystem r0 = com.mrousavy.camera.core.types.AutoFocusSystem.NONE
        L_0x00a6:
            r5.autoFocusSystem = r0
            androidx.camera.core.PreviewCapabilities r0 = androidx.camera.core.impl.capability.PreviewCapabilitiesImpl.from(r6)
            java.lang.String r1 = "from(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r5.previewCapabilities = r0
            r0 = 0
            androidx.camera.video.VideoCapabilities r1 = androidx.camera.video.Recorder.getVideoCapabilities(r6, r0)
            java.lang.String r2 = "getVideoCapabilities(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r5.videoCapabilities = r1
            boolean r1 = r5.getSupports10BitHDR()
            r5.supports10BitHdr = r1
            int r1 = r6.getSensorRotationDegrees()
            r5.sensorRotationDegrees = r1
            com.mrousavy.camera.core.types.Orientation$Companion r2 = com.mrousavy.camera.core.types.Orientation.Companion
            com.mrousavy.camera.core.types.Orientation r1 = r2.fromRotationDegrees(r1)
            r5.sensorOrientation = r1
            java.lang.String r1 = "null cannot be cast to non-null type androidx.camera.core.impl.CameraInfoInternal"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r1)
            r1 = r6
            androidx.camera.core.impl.CameraInfoInternal r1 = (androidx.camera.core.impl.CameraInfoInternal) r1
            r5.cameraInfoInternal = r1
            boolean r1 = r6 instanceof androidx.camera.camera2.internal.Camera2CameraInfoImpl
            r2 = 0
            if (r1 == 0) goto L_0x00e6
            r1 = r6
            androidx.camera.camera2.internal.Camera2CameraInfoImpl r1 = (androidx.camera.camera2.internal.Camera2CameraInfoImpl) r1
            goto L_0x00e7
        L_0x00e6:
            r1 = r2
        L_0x00e7:
            r5.camera2Details = r1
            if (r1 == 0) goto L_0x00f7
            java.util.Map r3 = r1.getCameraCharacteristicsMap()
            if (r3 == 0) goto L_0x00f7
            java.util.Set r3 = r3.keySet()
            if (r3 != 0) goto L_0x00fb
        L_0x00f7:
            java.util.Set r3 = kotlin.collections.SetsKt.emptySet()
        L_0x00fb:
            r5.physicalDeviceIds = r3
            int r3 = r3.size()
            r4 = 1
            if (r3 <= r4) goto L_0x0105
            r0 = r4
        L_0x0105:
            r5.isMultiCam = r0
            if (r1 == 0) goto L_0x0118
            androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat r0 = r1.getCameraCharacteristicsCompat()
            if (r0 == 0) goto L_0x0118
            android.hardware.camera2.CameraCharacteristics$Key r1 = android.hardware.camera2.CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL
            java.lang.Object r0 = r0.get(r1)
            r2 = r0
            java.lang.Integer r2 = (java.lang.Integer) r2
        L_0x0118:
            r5.cameraHardwareLevel = r2
            com.mrousavy.camera.core.types.HardwareLevel$Companion r0 = com.mrousavy.camera.core.types.HardwareLevel.Companion
            r1 = 2
            if (r2 == 0) goto L_0x0124
            int r2 = r2.intValue()
            goto L_0x0125
        L_0x0124:
            r2 = r1
        L_0x0125:
            com.mrousavy.camera.core.types.HardwareLevel r0 = r0.fromCameraHardwareLevel(r2)
            r5.hardwareLevel = r0
            double r2 = r5.getMinFocusDistanceCm()
            r5.minFocusDistance = r2
            android.util.Range r0 = r5.getIsoRange()
            r5.isoRange = r0
            double r2 = r5.getMaxFieldOfView()
            r5.maxFieldOfView = r2
            androidx.camera.core.CameraSelector r0 = r6.getCameraSelector()
            boolean r0 = r7.isExtensionAvailable(r0, r1)
            r5.supportsHdrExtension = r0
            androidx.camera.core.CameraSelector r6 = r6.getCameraSelector()
            r0 = 3
            boolean r6 = r7.isExtensionAvailable(r6, r0)
            r5.supportsLowLightBoostExtension = r6
            return
        L_0x0153:
            com.mrousavy.camera.core.NoCameraDeviceError r6 = new com.mrousavy.camera.core.NoCameraDeviceError
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.CameraDeviceDetails.<init>(androidx.camera.core.CameraInfo, androidx.camera.extensions.ExtensionsManager):void");
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/CameraDeviceDetails$Companion;", "", "()V", "TAG", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraDeviceDetails.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final ReadableMap toMap() {
        List<DeviceType> deviceTypes = getDeviceTypes();
        ReadableArray formats = getFormats();
        WritableMap createMap = Arguments.createMap();
        createMap.putString("id", this.cameraId);
        createMap.putArray("physicalDevices", List_toJSValueKt.toJSValue(deviceTypes));
        createMap.putString(ViewProps.POSITION, this.position.getUnionValue());
        createMap.putString("name", this.name);
        createMap.putBoolean("hasFlash", this.hasFlash);
        createMap.putBoolean("hasTorch", this.hasFlash);
        createMap.putDouble("minFocusDistance", this.minFocusDistance);
        createMap.putBoolean("isMultiCam", this.isMultiCam);
        createMap.putBoolean("supportsRawCapture", this.supportsRawCapture);
        createMap.putBoolean("supportsLowLightBoost", this.supportsLowLightBoostExtension);
        createMap.putBoolean("supportsFocus", this.supportsFocus);
        createMap.putDouble("minZoom", (double) this.minZoom);
        createMap.putDouble("maxZoom", (double) this.maxZoom);
        createMap.putDouble("neutralZoom", 1.0d);
        Integer num = this.minExposure;
        Intrinsics.checkNotNullExpressionValue(num, "minExposure");
        createMap.putInt("minExposure", num.intValue());
        Integer num2 = this.maxExposure;
        Intrinsics.checkNotNullExpressionValue(num2, "maxExposure");
        createMap.putInt("maxExposure", num2.intValue());
        createMap.putString("hardwareLevel", this.hardwareLevel.getUnionValue());
        createMap.putString("sensorOrientation", this.sensorOrientation.getUnionValue());
        createMap.putArray("formats", formats);
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }

    private final ReadableArray getFormats() {
        DynamicRange dynamicRange;
        Iterator it;
        List<Size> supportedResolutions;
        List<Size> list;
        CameraDeviceDetails cameraDeviceDetails = this;
        WritableArray createArray = Arguments.createArray();
        Set<DynamicRange> supportedDynamicRanges = cameraDeviceDetails.videoCapabilities.getSupportedDynamicRanges();
        Intrinsics.checkNotNullExpressionValue(supportedDynamicRanges, "getSupportedDynamicRanges(...)");
        Iterator it2 = supportedDynamicRanges.iterator();
        while (it2.hasNext()) {
            dynamicRange = (DynamicRange) it2.next();
            try {
                List<Quality> supportedQualities = cameraDeviceDetails.videoCapabilities.getSupportedQualities(dynamicRange);
                Intrinsics.checkNotNullExpressionValue(supportedQualities, "getSupportedQualities(...)");
                Iterable<Quality> iterable = supportedQualities;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Quality quality : iterable) {
                    Intrinsics.checkNotNull(quality, "null cannot be cast to non-null type androidx.camera.video.Quality.ConstantQuality");
                    arrayList.add((Quality.ConstantQuality) quality);
                }
                Collection arrayList2 = new ArrayList();
                for (Quality.ConstantQuality typicalSizes : (List) arrayList) {
                    List<Size> typicalSizes2 = typicalSizes.getTypicalSizes();
                    Intrinsics.checkNotNullExpressionValue(typicalSizes2, "getTypicalSizes(...)");
                    CollectionsKt.addAll(arrayList2, typicalSizes2);
                }
                List<Size> list2 = (List) arrayList2;
                supportedResolutions = cameraDeviceDetails.cameraInfoInternal.getSupportedResolutions(256);
                Intrinsics.checkNotNullExpressionValue(supportedResolutions, "getSupportedResolutions(...)");
                Set<Range<Integer>> supportedFrameRateRanges = cameraDeviceDetails.cameraInfo.getSupportedFrameRateRanges();
                Intrinsics.checkNotNullExpressionValue(supportedFrameRateRanges, "getSupportedFrameRateRanges(...)");
                Iterator it3 = supportedFrameRateRanges.iterator();
                if (it3.hasNext()) {
                    Comparable comparable = (Integer) ((Range) it3.next()).getLower();
                    while (it3.hasNext()) {
                        Comparable comparable2 = (Integer) ((Range) it3.next()).getLower();
                        if (comparable.compareTo(comparable2) > 0) {
                            comparable = comparable2;
                        }
                    }
                    Integer num = (Integer) comparable;
                    Iterator it4 = supportedFrameRateRanges.iterator();
                    if (it4.hasNext()) {
                        Comparable comparable3 = (Integer) ((Range) it4.next()).getUpper();
                        while (it4.hasNext()) {
                            Comparable comparable4 = (Integer) ((Range) it4.next()).getUpper();
                            if (comparable3.compareTo(comparable4) < 0) {
                                comparable3 = comparable4;
                            }
                        }
                        Integer num2 = (Integer) comparable3;
                        for (Size size : list2) {
                            try {
                                CamcorderProfileUtils.Companion companion = CamcorderProfileUtils.Companion;
                                String str = cameraDeviceDetails.cameraId;
                                Intrinsics.checkNotNull(size);
                                Integer maximumFps = companion.getMaximumFps(str, size);
                                if (maximumFps == null) {
                                    maximumFps = num2;
                                }
                                Intrinsics.checkNotNull(num);
                                int intValue = num.intValue();
                                Intrinsics.checkNotNull(maximumFps);
                                Range range = new Range(Integer.valueOf(Math.min(intValue, maximumFps.intValue())), maximumFps);
                                for (Size size2 : supportedResolutions) {
                                    Intrinsics.checkNotNull(size2);
                                    createArray.pushMap(cameraDeviceDetails.buildFormatMap(size2, size, range));
                                    it = it2;
                                    list = supportedResolutions;
                                    cameraDeviceDetails = this;
                                    it2 = it;
                                    supportedResolutions = list;
                                }
                                it = it2;
                                list = supportedResolutions;
                            } catch (Throwable th) {
                                th = th;
                            }
                            cameraDeviceDetails = this;
                            it2 = it;
                            supportedResolutions = list;
                        }
                        it = it2;
                        cameraDeviceDetails = this;
                        it2 = it;
                    } else {
                        Iterator it5 = it2;
                        throw new NoSuchElementException();
                    }
                } else {
                    Iterator it6 = it2;
                    throw new NoSuchElementException();
                }
            } catch (Throwable th2) {
                th = th2;
                it = it2;
                SentryLogcatAdapter.w(TAG, "Dynamic Range Profile " + dynamicRange + " cannot be used as a format!", th);
                cameraDeviceDetails = this;
                it2 = it;
            }
        }
        Intrinsics.checkNotNull(createArray);
        return createArray;
        try {
            int width = size.getWidth();
            SentryLogcatAdapter.w(TAG, "Video size " + width + ViewHierarchyNode.JsonKeys.X + size.getHeight() + " cannot be used as a format!", th);
            cameraDeviceDetails = this;
            it2 = it;
            supportedResolutions = list;
        } catch (Throwable th3) {
            th = th3;
            SentryLogcatAdapter.w(TAG, "Dynamic Range Profile " + dynamicRange + " cannot be used as a format!", th);
            cameraDeviceDetails = this;
            it2 = it;
        }
    }

    private final ReadableMap buildFormatMap(Size size, Size size2, Range<Integer> range) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("photoHeight", size.getHeight());
        createMap.putInt("photoWidth", size.getWidth());
        createMap.putInt("videoHeight", size2.getHeight());
        createMap.putInt("videoWidth", size2.getWidth());
        Integer lower = range.getLower();
        Intrinsics.checkNotNullExpressionValue(lower, "getLower(...)");
        createMap.putInt("minFps", lower.intValue());
        Integer upper = range.getUpper();
        Intrinsics.checkNotNullExpressionValue(upper, "getUpper(...)");
        createMap.putInt("maxFps", upper.intValue());
        Integer lower2 = this.isoRange.getLower();
        Intrinsics.checkNotNullExpressionValue(lower2, "getLower(...)");
        createMap.putInt("minISO", lower2.intValue());
        Integer upper2 = this.isoRange.getUpper();
        Intrinsics.checkNotNullExpressionValue(upper2, "getUpper(...)");
        createMap.putInt("maxISO", upper2.intValue());
        createMap.putDouble("fieldOfView", this.maxFieldOfView);
        createMap.putBoolean("supportsVideoHdr", this.supports10BitHdr);
        createMap.putBoolean("supportsPhotoHdr", this.supportsHdrExtension);
        createMap.putBoolean("supportsDepthCapture", this.supportsDepthCapture);
        createMap.putString("autoFocusSystem", this.autoFocusSystem.getUnionValue());
        createMap.putArray("videoStabilizationModes", createStabilizationModes());
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }

    private final boolean getSupports10BitHDR() {
        boolean z;
        Set<DynamicRange> supportedDynamicRanges = this.videoCapabilities.getSupportedDynamicRanges();
        Intrinsics.checkNotNullExpressionValue(supportedDynamicRanges, "getSupportedDynamicRanges(...)");
        Iterable<DynamicRange> iterable = supportedDynamicRanges;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (DynamicRange dynamicRange : iterable) {
            if (dynamicRange.is10BitHdr() || Intrinsics.areEqual((Object) dynamicRange, (Object) DynamicRange.HDR_UNSPECIFIED_10_BIT)) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    private final boolean getSupportsFocus() {
        MeteringPoint createPoint = new SurfaceOrientedMeteringPointFactory(1.0f, 1.0f).createPoint(0.5f, 0.5f);
        Intrinsics.checkNotNullExpressionValue(createPoint, "createPoint(...)");
        return this.cameraInfo.isFocusMeteringSupported(new FocusMeteringAction.Builder(createPoint).build());
    }

    private final double getMinFocusDistanceCm() {
        Float f;
        CameraInfo cameraInfo2 = this.cameraInfo;
        Camera2CameraInfoImpl camera2CameraInfoImpl = cameraInfo2 instanceof Camera2CameraInfoImpl ? (Camera2CameraInfoImpl) cameraInfo2 : null;
        if (camera2CameraInfoImpl != null && (f = (Float) camera2CameraInfoImpl.getCameraCharacteristicsCompat().get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE)) != null && !Intrinsics.areEqual(f, 0.0f) && !Float.isNaN(f.floatValue()) && !Float.isInfinite(f.floatValue())) {
            return (1.0d / ((double) f.floatValue())) * 100.0d;
        }
        return 0.0d;
    }

    private final Range<Integer> getIsoRange() {
        CameraInfo cameraInfo2 = this.cameraInfo;
        Camera2CameraInfoImpl camera2CameraInfoImpl = cameraInfo2 instanceof Camera2CameraInfoImpl ? (Camera2CameraInfoImpl) cameraInfo2 : null;
        if (camera2CameraInfoImpl == null) {
            return new Range<>((Comparable) 0, (Comparable) 0);
        }
        Range<Integer> range = (Range) camera2CameraInfoImpl.getCameraCharacteristicsCompat().get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE);
        return range == null ? new Range<>((Comparable) 0, (Comparable) 0) : range;
    }

    private final ReadableArray createStabilizationModes() {
        Set<VideoStabilizationMode> mutableSetOf = SetsKt.mutableSetOf(VideoStabilizationMode.OFF);
        if (this.videoCapabilities.isStabilizationSupported()) {
            mutableSetOf.add(VideoStabilizationMode.CINEMATIC);
        }
        if (this.previewCapabilities.isStabilizationSupported()) {
            mutableSetOf.add(VideoStabilizationMode.CINEMATIC_EXTENDED);
        }
        WritableArray createArray = Arguments.createArray();
        for (VideoStabilizationMode unionValue : mutableSetOf) {
            createArray.pushString(unionValue.getUnionValue());
        }
        Intrinsics.checkNotNull(createArray);
        return createArray;
    }

    private final List<DeviceType> getDeviceTypes() {
        DeviceType deviceType;
        List<DeviceType> listOf = CollectionsKt.listOf(DeviceType.WIDE_ANGLE);
        Camera2CameraInfoImpl camera2CameraInfoImpl = this.camera2Details;
        if (camera2CameraInfoImpl == null) {
            return listOf;
        }
        Map<String, CameraCharacteristics> cameraCharacteristicsMap = camera2CameraInfoImpl.getCameraCharacteristicsMap();
        Intrinsics.checkNotNullExpressionValue(cameraCharacteristicsMap, "getCameraCharacteristicsMap(...)");
        Collection arrayList = new ArrayList(cameraCharacteristicsMap.size());
        for (Map.Entry<String, CameraCharacteristics> value : cameraCharacteristicsMap.entrySet()) {
            CameraCharacteristics cameraCharacteristics = (CameraCharacteristics) value.getValue();
            SizeF sizeF = (SizeF) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
            if (sizeF == null) {
                deviceType = DeviceType.WIDE_ANGLE;
            } else {
                Intrinsics.checkNotNull(sizeF);
                float[] fArr = (float[]) cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
                if (fArr == null) {
                    deviceType = DeviceType.WIDE_ANGLE;
                } else {
                    Intrinsics.checkNotNull(fArr);
                    double maxFieldOfView2 = getMaxFieldOfView(fArr, sizeF);
                    if (maxFieldOfView2 > 94.0d) {
                        deviceType = DeviceType.ULTRA_WIDE_ANGLE;
                    } else {
                        boolean z = false;
                        if (60.0d <= maxFieldOfView2 && maxFieldOfView2 <= 94.0d) {
                            z = true;
                        }
                        if (z) {
                            deviceType = DeviceType.WIDE_ANGLE;
                        } else if (maxFieldOfView2 < 60.0d) {
                            deviceType = DeviceType.TELEPHOTO;
                        } else {
                            throw new Error("Invalid Field Of View! (" + maxFieldOfView2 + ")");
                        }
                    }
                }
            }
            arrayList.add(deviceType);
        }
        return (List) arrayList;
    }

    private final double getFieldOfView(float f, SizeF sizeF) {
        boolean z = true;
        if (sizeF.getWidth() == 0.0f) {
            return 0.0d;
        }
        if (sizeF.getHeight() != 0.0f) {
            z = false;
        }
        if (z) {
            return 0.0d;
        }
        return Math.toDegrees(Math.atan2(Math.sqrt((double) ((sizeF.getWidth() * sizeF.getWidth()) + (sizeF.getHeight() * sizeF.getHeight()))), ((double) f) * 2.0d) * 2.0d);
    }

    private final double getMaxFieldOfView(float[] fArr, SizeF sizeF) {
        Float minOrNull = ArraysKt.minOrNull(fArr);
        if (minOrNull != null) {
            return getFieldOfView(minOrNull.floatValue(), sizeF);
        }
        return 0.0d;
    }

    private final double getMaxFieldOfView() {
        CameraCharacteristicsCompat cameraCharacteristicsCompat;
        SizeF sizeF;
        float[] fArr;
        Camera2CameraInfoImpl camera2CameraInfoImpl = this.camera2Details;
        if (camera2CameraInfoImpl == null || (cameraCharacteristicsCompat = camera2CameraInfoImpl.getCameraCharacteristicsCompat()) == null || (sizeF = (SizeF) cameraCharacteristicsCompat.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE)) == null || (fArr = (float[]) cameraCharacteristicsCompat.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS)) == null) {
            return 0.0d;
        }
        return getMaxFieldOfView(fArr, sizeF);
    }
}
