package com.mrousavy.camera.core;

import android.util.Range;
import androidx.camera.core.Preview;
import androidx.media3.common.MimeTypes;
import com.mrousavy.camera.core.types.CameraDeviceFormat;
import com.mrousavy.camera.core.types.CodeType;
import com.mrousavy.camera.core.types.OutputOrientation;
import com.mrousavy.camera.core.types.PixelFormat;
import com.mrousavy.camera.core.types.QualityBalance;
import com.mrousavy.camera.core.types.Torch;
import com.mrousavy.camera.core.types.VideoStabilizationMode;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b6\b\b\u0018\u0000 \u00012\u00020\u0001:\u0011}~\u0001\u0001\u0001\u0001\u0001\u0001\u0001Bç\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010\u001f\u001a\u00020 \u0012\b\b\u0002\u0010!\u001a\u00020\u0013\u0012\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0005¢\u0006\u0002\u0010$J\u000b\u0010e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010f\u001a\u00020\u0015HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\t\u0010h\u001a\u00020\u0013HÆ\u0003J\t\u0010i\u001a\u00020\u001aHÆ\u0003J\t\u0010j\u001a\u00020\u001cHÆ\u0003J\u0010\u0010k\u001a\u0004\u0018\u00010\u001eHÆ\u0003¢\u0006\u0002\u00106J\t\u0010l\u001a\u00020 HÆ\u0003J\t\u0010m\u001a\u00020\u0013HÆ\u0003J\u000f\u0010n\u001a\b\u0012\u0004\u0012\u00020#0\u0005HÆ\u0003J\u000f\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010p\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J\u000f\u0010q\u001a\b\u0012\u0004\u0012\u00020\n0\u0005HÆ\u0003J\u000f\u0010r\u001a\b\u0012\u0004\u0012\u00020\f0\u0005HÆ\u0003J\u000f\u0010s\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005HÆ\u0003J\u0010\u0010t\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010BJ\u0010\u0010u\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010BJ\t\u0010v\u001a\u00020\u0013HÆ\u0003Jð\u0001\u0010w\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00132\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\u00132\u000e\b\u0002\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0005HÆ\u0001¢\u0006\u0002\u0010xJ\u0013\u0010y\u001a\u00020\u00132\b\u0010z\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010{\u001a\u00020\u0010HÖ\u0001J\t\u0010|\u001a\u00020\u0003HÖ\u0001R \u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010&\"\u0004\b.\u0010(R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u0010\u0018\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00100\"\u0004\b4\u00102R\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010&\"\u0004\b?\u0010(R\u001a\u0010!\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u00100\"\u0004\b@\u00102R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0010\n\u0002\u0010E\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0010\n\u0002\u0010E\u001a\u0004\bF\u0010B\"\u0004\bG\u0010DR\u001a\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010&\"\u0004\bM\u0010(R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010&\"\u0004\bO\u0010(R\u0019\u0010P\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010Q8F¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0013\u0010T\u001a\u0004\u0018\u00010 8F¢\u0006\u0006\u001a\u0004\bU\u0010VR\u001a\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010&\"\u0004\b\\\u0010(R\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010b\"\u0004\bc\u0010d¨\u0006\u0001"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration;", "", "cameraId", "", "preview", "Lcom/mrousavy/camera/core/CameraConfiguration$Output;", "Lcom/mrousavy/camera/core/CameraConfiguration$Preview;", "photo", "Lcom/mrousavy/camera/core/CameraConfiguration$Photo;", "video", "Lcom/mrousavy/camera/core/CameraConfiguration$Video;", "frameProcessor", "Lcom/mrousavy/camera/core/CameraConfiguration$FrameProcessor;", "codeScanner", "Lcom/mrousavy/camera/core/CameraConfiguration$CodeScanner;", "minFps", "", "maxFps", "enableLocation", "", "outputOrientation", "Lcom/mrousavy/camera/core/types/OutputOrientation;", "format", "Lcom/mrousavy/camera/core/types/CameraDeviceFormat;", "enableLowLightBoost", "torch", "Lcom/mrousavy/camera/core/types/Torch;", "videoStabilizationMode", "Lcom/mrousavy/camera/core/types/VideoStabilizationMode;", "exposure", "", "zoom", "", "isActive", "audio", "Lcom/mrousavy/camera/core/CameraConfiguration$Audio;", "(Ljava/lang/String;Lcom/mrousavy/camera/core/CameraConfiguration$Output;Lcom/mrousavy/camera/core/CameraConfiguration$Output;Lcom/mrousavy/camera/core/CameraConfiguration$Output;Lcom/mrousavy/camera/core/CameraConfiguration$Output;Lcom/mrousavy/camera/core/CameraConfiguration$Output;Ljava/lang/Integer;Ljava/lang/Integer;ZLcom/mrousavy/camera/core/types/OutputOrientation;Lcom/mrousavy/camera/core/types/CameraDeviceFormat;ZLcom/mrousavy/camera/core/types/Torch;Lcom/mrousavy/camera/core/types/VideoStabilizationMode;Ljava/lang/Double;FZLcom/mrousavy/camera/core/CameraConfiguration$Output;)V", "getAudio", "()Lcom/mrousavy/camera/core/CameraConfiguration$Output;", "setAudio", "(Lcom/mrousavy/camera/core/CameraConfiguration$Output;)V", "getCameraId", "()Ljava/lang/String;", "setCameraId", "(Ljava/lang/String;)V", "getCodeScanner", "setCodeScanner", "getEnableLocation", "()Z", "setEnableLocation", "(Z)V", "getEnableLowLightBoost", "setEnableLowLightBoost", "getExposure", "()Ljava/lang/Double;", "setExposure", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getFormat", "()Lcom/mrousavy/camera/core/types/CameraDeviceFormat;", "setFormat", "(Lcom/mrousavy/camera/core/types/CameraDeviceFormat;)V", "getFrameProcessor", "setFrameProcessor", "setActive", "getMaxFps", "()Ljava/lang/Integer;", "setMaxFps", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getMinFps", "setMinFps", "getOutputOrientation", "()Lcom/mrousavy/camera/core/types/OutputOrientation;", "setOutputOrientation", "(Lcom/mrousavy/camera/core/types/OutputOrientation;)V", "getPhoto", "setPhoto", "getPreview", "setPreview", "targetFpsRange", "Landroid/util/Range;", "getTargetFpsRange", "()Landroid/util/Range;", "targetPreviewAspectRatio", "getTargetPreviewAspectRatio", "()Ljava/lang/Float;", "getTorch", "()Lcom/mrousavy/camera/core/types/Torch;", "setTorch", "(Lcom/mrousavy/camera/core/types/Torch;)V", "getVideo", "setVideo", "getVideoStabilizationMode", "()Lcom/mrousavy/camera/core/types/VideoStabilizationMode;", "setVideoStabilizationMode", "(Lcom/mrousavy/camera/core/types/VideoStabilizationMode;)V", "getZoom", "()F", "setZoom", "(F)V", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Lcom/mrousavy/camera/core/CameraConfiguration$Output;Lcom/mrousavy/camera/core/CameraConfiguration$Output;Lcom/mrousavy/camera/core/CameraConfiguration$Output;Lcom/mrousavy/camera/core/CameraConfiguration$Output;Lcom/mrousavy/camera/core/CameraConfiguration$Output;Ljava/lang/Integer;Ljava/lang/Integer;ZLcom/mrousavy/camera/core/types/OutputOrientation;Lcom/mrousavy/camera/core/types/CameraDeviceFormat;ZLcom/mrousavy/camera/core/types/Torch;Lcom/mrousavy/camera/core/types/VideoStabilizationMode;Ljava/lang/Double;FZLcom/mrousavy/camera/core/CameraConfiguration$Output;)Lcom/mrousavy/camera/core/CameraConfiguration;", "equals", "other", "hashCode", "toString", "AbortThrow", "Audio", "CodeScanner", "Companion", "Difference", "FrameProcessor", "Output", "Photo", "Preview", "Video", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraConfiguration.kt */
public final class CameraConfiguration {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private Output<Audio> audio;
    private String cameraId;
    private Output<CodeScanner> codeScanner;
    private boolean enableLocation;
    private boolean enableLowLightBoost;
    private Double exposure;
    private CameraDeviceFormat format;
    private Output<FrameProcessor> frameProcessor;
    private boolean isActive;
    private Integer maxFps;
    private Integer minFps;
    private OutputOrientation outputOrientation;
    private Output<Photo> photo;
    private Output<Preview> preview;
    private Torch torch;
    private Output<Video> video;
    private VideoStabilizationMode videoStabilizationMode;
    private float zoom;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$AbortThrow;", "", "()V", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraConfiguration.kt */
    public static final class AbortThrow extends Throwable {
    }

    public CameraConfiguration() {
        this((String) null, (Output) null, (Output) null, (Output) null, (Output) null, (Output) null, (Integer) null, (Integer) null, false, (OutputOrientation) null, (CameraDeviceFormat) null, false, (Torch) null, (VideoStabilizationMode) null, (Double) null, 0.0f, false, (Output) null, 262143, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ CameraConfiguration copy$default(CameraConfiguration cameraConfiguration, String str, Output output, Output output2, Output output3, Output output4, Output output5, Integer num, Integer num2, boolean z, OutputOrientation outputOrientation2, CameraDeviceFormat cameraDeviceFormat, boolean z2, Torch torch2, VideoStabilizationMode videoStabilizationMode2, Double d, float f, boolean z3, Output output6, int i, Object obj) {
        CameraConfiguration cameraConfiguration2 = cameraConfiguration;
        int i2 = i;
        return cameraConfiguration.copy((i2 & 1) != 0 ? cameraConfiguration2.cameraId : str, (i2 & 2) != 0 ? cameraConfiguration2.preview : output, (i2 & 4) != 0 ? cameraConfiguration2.photo : output2, (i2 & 8) != 0 ? cameraConfiguration2.video : output3, (i2 & 16) != 0 ? cameraConfiguration2.frameProcessor : output4, (i2 & 32) != 0 ? cameraConfiguration2.codeScanner : output5, (i2 & 64) != 0 ? cameraConfiguration2.minFps : num, (i2 & 128) != 0 ? cameraConfiguration2.maxFps : num2, (i2 & 256) != 0 ? cameraConfiguration2.enableLocation : z, (i2 & 512) != 0 ? cameraConfiguration2.outputOrientation : outputOrientation2, (i2 & 1024) != 0 ? cameraConfiguration2.format : cameraDeviceFormat, (i2 & 2048) != 0 ? cameraConfiguration2.enableLowLightBoost : z2, (i2 & 4096) != 0 ? cameraConfiguration2.torch : torch2, (i2 & 8192) != 0 ? cameraConfiguration2.videoStabilizationMode : videoStabilizationMode2, (i2 & 16384) != 0 ? cameraConfiguration2.exposure : d, (i2 & 32768) != 0 ? cameraConfiguration2.zoom : f, (i2 & 65536) != 0 ? cameraConfiguration2.isActive : z3, (i2 & 131072) != 0 ? cameraConfiguration2.audio : output6);
    }

    public final String component1() {
        return this.cameraId;
    }

    public final OutputOrientation component10() {
        return this.outputOrientation;
    }

    public final CameraDeviceFormat component11() {
        return this.format;
    }

    public final boolean component12() {
        return this.enableLowLightBoost;
    }

    public final Torch component13() {
        return this.torch;
    }

    public final VideoStabilizationMode component14() {
        return this.videoStabilizationMode;
    }

    public final Double component15() {
        return this.exposure;
    }

    public final float component16() {
        return this.zoom;
    }

    public final boolean component17() {
        return this.isActive;
    }

    public final Output<Audio> component18() {
        return this.audio;
    }

    public final Output<Preview> component2() {
        return this.preview;
    }

    public final Output<Photo> component3() {
        return this.photo;
    }

    public final Output<Video> component4() {
        return this.video;
    }

    public final Output<FrameProcessor> component5() {
        return this.frameProcessor;
    }

    public final Output<CodeScanner> component6() {
        return this.codeScanner;
    }

    public final Integer component7() {
        return this.minFps;
    }

    public final Integer component8() {
        return this.maxFps;
    }

    public final boolean component9() {
        return this.enableLocation;
    }

    public final CameraConfiguration copy(String str, Output<Preview> output, Output<Photo> output2, Output<Video> output3, Output<FrameProcessor> output4, Output<CodeScanner> output5, Integer num, Integer num2, boolean z, OutputOrientation outputOrientation2, CameraDeviceFormat cameraDeviceFormat, boolean z2, Torch torch2, VideoStabilizationMode videoStabilizationMode2, Double d, float f, boolean z3, Output<Audio> output6) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(output, "preview");
        Intrinsics.checkNotNullParameter(output2, "photo");
        Intrinsics.checkNotNullParameter(output3, MimeTypes.BASE_TYPE_VIDEO);
        Intrinsics.checkNotNullParameter(output4, "frameProcessor");
        Intrinsics.checkNotNullParameter(output5, "codeScanner");
        Intrinsics.checkNotNullParameter(outputOrientation2, "outputOrientation");
        Intrinsics.checkNotNullParameter(torch2, "torch");
        Intrinsics.checkNotNullParameter(videoStabilizationMode2, "videoStabilizationMode");
        Intrinsics.checkNotNullParameter(output6, MimeTypes.BASE_TYPE_AUDIO);
        return new CameraConfiguration(str, output, output2, output3, output4, output5, num, num2, z, outputOrientation2, cameraDeviceFormat, z2, torch2, videoStabilizationMode2, d, f, z3, output6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraConfiguration)) {
            return false;
        }
        CameraConfiguration cameraConfiguration = (CameraConfiguration) obj;
        return Intrinsics.areEqual((Object) this.cameraId, (Object) cameraConfiguration.cameraId) && Intrinsics.areEqual((Object) this.preview, (Object) cameraConfiguration.preview) && Intrinsics.areEqual((Object) this.photo, (Object) cameraConfiguration.photo) && Intrinsics.areEqual((Object) this.video, (Object) cameraConfiguration.video) && Intrinsics.areEqual((Object) this.frameProcessor, (Object) cameraConfiguration.frameProcessor) && Intrinsics.areEqual((Object) this.codeScanner, (Object) cameraConfiguration.codeScanner) && Intrinsics.areEqual((Object) this.minFps, (Object) cameraConfiguration.minFps) && Intrinsics.areEqual((Object) this.maxFps, (Object) cameraConfiguration.maxFps) && this.enableLocation == cameraConfiguration.enableLocation && this.outputOrientation == cameraConfiguration.outputOrientation && Intrinsics.areEqual((Object) this.format, (Object) cameraConfiguration.format) && this.enableLowLightBoost == cameraConfiguration.enableLowLightBoost && this.torch == cameraConfiguration.torch && this.videoStabilizationMode == cameraConfiguration.videoStabilizationMode && Intrinsics.areEqual((Object) this.exposure, (Object) cameraConfiguration.exposure) && Float.compare(this.zoom, cameraConfiguration.zoom) == 0 && this.isActive == cameraConfiguration.isActive && Intrinsics.areEqual((Object) this.audio, (Object) cameraConfiguration.audio);
    }

    public int hashCode() {
        String str = this.cameraId;
        int i = 0;
        int hashCode = (((((((((((str == null ? 0 : str.hashCode()) * 31) + this.preview.hashCode()) * 31) + this.photo.hashCode()) * 31) + this.video.hashCode()) * 31) + this.frameProcessor.hashCode()) * 31) + this.codeScanner.hashCode()) * 31;
        Integer num = this.minFps;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.maxFps;
        int hashCode3 = (((((hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31) + Boolean.hashCode(this.enableLocation)) * 31) + this.outputOrientation.hashCode()) * 31;
        CameraDeviceFormat cameraDeviceFormat = this.format;
        int hashCode4 = (((((((hashCode3 + (cameraDeviceFormat == null ? 0 : cameraDeviceFormat.hashCode())) * 31) + Boolean.hashCode(this.enableLowLightBoost)) * 31) + this.torch.hashCode()) * 31) + this.videoStabilizationMode.hashCode()) * 31;
        Double d = this.exposure;
        if (d != null) {
            i = d.hashCode();
        }
        return ((((((hashCode4 + i) * 31) + Float.hashCode(this.zoom)) * 31) + Boolean.hashCode(this.isActive)) * 31) + this.audio.hashCode();
    }

    public String toString() {
        String str = this.cameraId;
        Output<Preview> output = this.preview;
        Output<Photo> output2 = this.photo;
        Output<Video> output3 = this.video;
        Output<FrameProcessor> output4 = this.frameProcessor;
        Output<CodeScanner> output5 = this.codeScanner;
        Integer num = this.minFps;
        Integer num2 = this.maxFps;
        boolean z = this.enableLocation;
        OutputOrientation outputOrientation2 = this.outputOrientation;
        CameraDeviceFormat cameraDeviceFormat = this.format;
        boolean z2 = this.enableLowLightBoost;
        Torch torch2 = this.torch;
        VideoStabilizationMode videoStabilizationMode2 = this.videoStabilizationMode;
        Double d = this.exposure;
        float f = this.zoom;
        return "CameraConfiguration(cameraId=" + str + ", preview=" + output + ", photo=" + output2 + ", video=" + output3 + ", frameProcessor=" + output4 + ", codeScanner=" + output5 + ", minFps=" + num + ", maxFps=" + num2 + ", enableLocation=" + z + ", outputOrientation=" + outputOrientation2 + ", format=" + cameraDeviceFormat + ", enableLowLightBoost=" + z2 + ", torch=" + torch2 + ", videoStabilizationMode=" + videoStabilizationMode2 + ", exposure=" + d + ", zoom=" + f + ", isActive=" + this.isActive + ", audio=" + this.audio + ")";
    }

    public CameraConfiguration(String str, Output<Preview> output, Output<Photo> output2, Output<Video> output3, Output<FrameProcessor> output4, Output<CodeScanner> output5, Integer num, Integer num2, boolean z, OutputOrientation outputOrientation2, CameraDeviceFormat cameraDeviceFormat, boolean z2, Torch torch2, VideoStabilizationMode videoStabilizationMode2, Double d, float f, boolean z3, Output<Audio> output6) {
        Output<FrameProcessor> output7 = output4;
        Output<CodeScanner> output8 = output5;
        OutputOrientation outputOrientation3 = outputOrientation2;
        Torch torch3 = torch2;
        VideoStabilizationMode videoStabilizationMode3 = videoStabilizationMode2;
        Output<Audio> output9 = output6;
        Intrinsics.checkNotNullParameter(output, "preview");
        Intrinsics.checkNotNullParameter(output2, "photo");
        Intrinsics.checkNotNullParameter(output3, MimeTypes.BASE_TYPE_VIDEO);
        Intrinsics.checkNotNullParameter(output7, "frameProcessor");
        Intrinsics.checkNotNullParameter(output8, "codeScanner");
        Intrinsics.checkNotNullParameter(outputOrientation3, "outputOrientation");
        Intrinsics.checkNotNullParameter(torch3, "torch");
        Intrinsics.checkNotNullParameter(videoStabilizationMode3, "videoStabilizationMode");
        Intrinsics.checkNotNullParameter(output9, MimeTypes.BASE_TYPE_AUDIO);
        this.cameraId = str;
        this.preview = output;
        this.photo = output2;
        this.video = output3;
        this.frameProcessor = output7;
        this.codeScanner = output8;
        this.minFps = num;
        this.maxFps = num2;
        this.enableLocation = z;
        this.outputOrientation = outputOrientation3;
        this.format = cameraDeviceFormat;
        this.enableLowLightBoost = z2;
        this.torch = torch3;
        this.videoStabilizationMode = videoStabilizationMode3;
        this.exposure = d;
        this.zoom = f;
        this.isActive = z3;
        this.audio = output9;
    }

    public final String getCameraId() {
        return this.cameraId;
    }

    public final void setCameraId(String str) {
        this.cameraId = str;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ CameraConfiguration(java.lang.String r20, com.mrousavy.camera.core.CameraConfiguration.Output r21, com.mrousavy.camera.core.CameraConfiguration.Output r22, com.mrousavy.camera.core.CameraConfiguration.Output r23, com.mrousavy.camera.core.CameraConfiguration.Output r24, com.mrousavy.camera.core.CameraConfiguration.Output r25, java.lang.Integer r26, java.lang.Integer r27, boolean r28, com.mrousavy.camera.core.types.OutputOrientation r29, com.mrousavy.camera.core.types.CameraDeviceFormat r30, boolean r31, com.mrousavy.camera.core.types.Torch r32, com.mrousavy.camera.core.types.VideoStabilizationMode r33, java.lang.Double r34, float r35, boolean r36, com.mrousavy.camera.core.CameraConfiguration.Output r37, int r38, kotlin.jvm.internal.DefaultConstructorMarker r39) {
        /*
            r19 = this;
            r0 = r38
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0008
            r1 = 0
            goto L_0x000a
        L_0x0008:
            r1 = r20
        L_0x000a:
            r3 = r0 & 2
            if (r3 == 0) goto L_0x0017
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled$Companion r3 = com.mrousavy.camera.core.CameraConfiguration.Output.Disabled.Companion
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled r3 = r3.create()
            com.mrousavy.camera.core.CameraConfiguration$Output r3 = (com.mrousavy.camera.core.CameraConfiguration.Output) r3
            goto L_0x0019
        L_0x0017:
            r3 = r21
        L_0x0019:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x0026
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled$Companion r4 = com.mrousavy.camera.core.CameraConfiguration.Output.Disabled.Companion
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled r4 = r4.create()
            com.mrousavy.camera.core.CameraConfiguration$Output r4 = (com.mrousavy.camera.core.CameraConfiguration.Output) r4
            goto L_0x0028
        L_0x0026:
            r4 = r22
        L_0x0028:
            r5 = r0 & 8
            if (r5 == 0) goto L_0x0035
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled$Companion r5 = com.mrousavy.camera.core.CameraConfiguration.Output.Disabled.Companion
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled r5 = r5.create()
            com.mrousavy.camera.core.CameraConfiguration$Output r5 = (com.mrousavy.camera.core.CameraConfiguration.Output) r5
            goto L_0x0037
        L_0x0035:
            r5 = r23
        L_0x0037:
            r6 = r0 & 16
            if (r6 == 0) goto L_0x0044
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled$Companion r6 = com.mrousavy.camera.core.CameraConfiguration.Output.Disabled.Companion
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled r6 = r6.create()
            com.mrousavy.camera.core.CameraConfiguration$Output r6 = (com.mrousavy.camera.core.CameraConfiguration.Output) r6
            goto L_0x0046
        L_0x0044:
            r6 = r24
        L_0x0046:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x0053
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled$Companion r7 = com.mrousavy.camera.core.CameraConfiguration.Output.Disabled.Companion
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled r7 = r7.create()
            com.mrousavy.camera.core.CameraConfiguration$Output r7 = (com.mrousavy.camera.core.CameraConfiguration.Output) r7
            goto L_0x0055
        L_0x0053:
            r7 = r25
        L_0x0055:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x005b
            r8 = 0
            goto L_0x005d
        L_0x005b:
            r8 = r26
        L_0x005d:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0063
            r9 = 0
            goto L_0x0065
        L_0x0063:
            r9 = r27
        L_0x0065:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x006b
            r10 = 0
            goto L_0x006d
        L_0x006b:
            r10 = r28
        L_0x006d:
            r12 = r0 & 512(0x200, float:7.175E-43)
            if (r12 == 0) goto L_0x0074
            com.mrousavy.camera.core.types.OutputOrientation r12 = com.mrousavy.camera.core.types.OutputOrientation.DEVICE
            goto L_0x0076
        L_0x0074:
            r12 = r29
        L_0x0076:
            r13 = r0 & 1024(0x400, float:1.435E-42)
            if (r13 == 0) goto L_0x007c
            r13 = 0
            goto L_0x007e
        L_0x007c:
            r13 = r30
        L_0x007e:
            r14 = r0 & 2048(0x800, float:2.87E-42)
            if (r14 == 0) goto L_0x0084
            r14 = 0
            goto L_0x0086
        L_0x0084:
            r14 = r31
        L_0x0086:
            r15 = r0 & 4096(0x1000, float:5.74E-42)
            if (r15 == 0) goto L_0x008d
            com.mrousavy.camera.core.types.Torch r15 = com.mrousavy.camera.core.types.Torch.OFF
            goto L_0x008f
        L_0x008d:
            r15 = r32
        L_0x008f:
            r2 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r2 == 0) goto L_0x0096
            com.mrousavy.camera.core.types.VideoStabilizationMode r2 = com.mrousavy.camera.core.types.VideoStabilizationMode.OFF
            goto L_0x0098
        L_0x0096:
            r2 = r33
        L_0x0098:
            r11 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r11 == 0) goto L_0x009e
            r11 = 0
            goto L_0x00a0
        L_0x009e:
            r11 = r34
        L_0x00a0:
            r16 = 32768(0x8000, float:4.5918E-41)
            r16 = r0 & r16
            if (r16 == 0) goto L_0x00aa
            r16 = 1065353216(0x3f800000, float:1.0)
            goto L_0x00ac
        L_0x00aa:
            r16 = r35
        L_0x00ac:
            r17 = 65536(0x10000, float:9.18355E-41)
            r17 = r0 & r17
            if (r17 == 0) goto L_0x00b5
            r17 = 0
            goto L_0x00b7
        L_0x00b5:
            r17 = r36
        L_0x00b7:
            r18 = 131072(0x20000, float:1.83671E-40)
            r0 = r0 & r18
            if (r0 == 0) goto L_0x00c6
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled$Companion r0 = com.mrousavy.camera.core.CameraConfiguration.Output.Disabled.Companion
            com.mrousavy.camera.core.CameraConfiguration$Output$Disabled r0 = r0.create()
            com.mrousavy.camera.core.CameraConfiguration$Output r0 = (com.mrousavy.camera.core.CameraConfiguration.Output) r0
            goto L_0x00c8
        L_0x00c6:
            r0 = r37
        L_0x00c8:
            r20 = r19
            r21 = r1
            r22 = r3
            r23 = r4
            r24 = r5
            r25 = r6
            r26 = r7
            r27 = r8
            r28 = r9
            r29 = r10
            r30 = r12
            r31 = r13
            r32 = r14
            r33 = r15
            r34 = r2
            r35 = r11
            r36 = r16
            r37 = r17
            r38 = r0
            r20.<init>(r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.CameraConfiguration.<init>(java.lang.String, com.mrousavy.camera.core.CameraConfiguration$Output, com.mrousavy.camera.core.CameraConfiguration$Output, com.mrousavy.camera.core.CameraConfiguration$Output, com.mrousavy.camera.core.CameraConfiguration$Output, com.mrousavy.camera.core.CameraConfiguration$Output, java.lang.Integer, java.lang.Integer, boolean, com.mrousavy.camera.core.types.OutputOrientation, com.mrousavy.camera.core.types.CameraDeviceFormat, boolean, com.mrousavy.camera.core.types.Torch, com.mrousavy.camera.core.types.VideoStabilizationMode, java.lang.Double, float, boolean, com.mrousavy.camera.core.CameraConfiguration$Output, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Output<Preview> getPreview() {
        return this.preview;
    }

    public final void setPreview(Output<Preview> output) {
        Intrinsics.checkNotNullParameter(output, "<set-?>");
        this.preview = output;
    }

    public final Output<Photo> getPhoto() {
        return this.photo;
    }

    public final void setPhoto(Output<Photo> output) {
        Intrinsics.checkNotNullParameter(output, "<set-?>");
        this.photo = output;
    }

    public final Output<Video> getVideo() {
        return this.video;
    }

    public final void setVideo(Output<Video> output) {
        Intrinsics.checkNotNullParameter(output, "<set-?>");
        this.video = output;
    }

    public final Output<FrameProcessor> getFrameProcessor() {
        return this.frameProcessor;
    }

    public final void setFrameProcessor(Output<FrameProcessor> output) {
        Intrinsics.checkNotNullParameter(output, "<set-?>");
        this.frameProcessor = output;
    }

    public final Output<CodeScanner> getCodeScanner() {
        return this.codeScanner;
    }

    public final void setCodeScanner(Output<CodeScanner> output) {
        Intrinsics.checkNotNullParameter(output, "<set-?>");
        this.codeScanner = output;
    }

    public final Integer getMinFps() {
        return this.minFps;
    }

    public final void setMinFps(Integer num) {
        this.minFps = num;
    }

    public final Integer getMaxFps() {
        return this.maxFps;
    }

    public final void setMaxFps(Integer num) {
        this.maxFps = num;
    }

    public final boolean getEnableLocation() {
        return this.enableLocation;
    }

    public final void setEnableLocation(boolean z) {
        this.enableLocation = z;
    }

    public final OutputOrientation getOutputOrientation() {
        return this.outputOrientation;
    }

    public final void setOutputOrientation(OutputOrientation outputOrientation2) {
        Intrinsics.checkNotNullParameter(outputOrientation2, "<set-?>");
        this.outputOrientation = outputOrientation2;
    }

    public final CameraDeviceFormat getFormat() {
        return this.format;
    }

    public final void setFormat(CameraDeviceFormat cameraDeviceFormat) {
        this.format = cameraDeviceFormat;
    }

    public final boolean getEnableLowLightBoost() {
        return this.enableLowLightBoost;
    }

    public final void setEnableLowLightBoost(boolean z) {
        this.enableLowLightBoost = z;
    }

    public final Torch getTorch() {
        return this.torch;
    }

    public final void setTorch(Torch torch2) {
        Intrinsics.checkNotNullParameter(torch2, "<set-?>");
        this.torch = torch2;
    }

    public final VideoStabilizationMode getVideoStabilizationMode() {
        return this.videoStabilizationMode;
    }

    public final void setVideoStabilizationMode(VideoStabilizationMode videoStabilizationMode2) {
        Intrinsics.checkNotNullParameter(videoStabilizationMode2, "<set-?>");
        this.videoStabilizationMode = videoStabilizationMode2;
    }

    public final Double getExposure() {
        return this.exposure;
    }

    public final void setExposure(Double d) {
        this.exposure = d;
    }

    public final float getZoom() {
        return this.zoom;
    }

    public final void setZoom(float f) {
        this.zoom = f;
    }

    public final boolean isActive() {
        return this.isActive;
    }

    public final void setActive(boolean z) {
        this.isActive = z;
    }

    public final Output<Audio> getAudio() {
        return this.audio;
    }

    public final void setAudio(Output<Audio> output) {
        Intrinsics.checkNotNullParameter(output, "<set-?>");
        this.audio = output;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$CodeScanner;", "", "codeTypes", "", "Lcom/mrousavy/camera/core/types/CodeType;", "(Ljava/util/List;)V", "getCodeTypes", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraConfiguration.kt */
    public static final class CodeScanner {
        private final List<CodeType> codeTypes;

        public static /* synthetic */ CodeScanner copy$default(CodeScanner codeScanner, List<CodeType> list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = codeScanner.codeTypes;
            }
            return codeScanner.copy(list);
        }

        public final List<CodeType> component1() {
            return this.codeTypes;
        }

        public final CodeScanner copy(List<? extends CodeType> list) {
            Intrinsics.checkNotNullParameter(list, "codeTypes");
            return new CodeScanner(list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof CodeScanner) && Intrinsics.areEqual((Object) this.codeTypes, (Object) ((CodeScanner) obj).codeTypes);
        }

        public int hashCode() {
            return this.codeTypes.hashCode();
        }

        public String toString() {
            return "CodeScanner(codeTypes=" + this.codeTypes + ")";
        }

        public CodeScanner(List<? extends CodeType> list) {
            Intrinsics.checkNotNullParameter(list, "codeTypes");
            this.codeTypes = list;
        }

        public final List<CodeType> getCodeTypes() {
            return this.codeTypes;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Photo;", "", "isMirrored", "", "enableHdr", "photoQualityBalance", "Lcom/mrousavy/camera/core/types/QualityBalance;", "(ZZLcom/mrousavy/camera/core/types/QualityBalance;)V", "getEnableHdr", "()Z", "getPhotoQualityBalance", "()Lcom/mrousavy/camera/core/types/QualityBalance;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraConfiguration.kt */
    public static final class Photo {
        private final boolean enableHdr;
        private final boolean isMirrored;
        private final QualityBalance photoQualityBalance;

        public static /* synthetic */ Photo copy$default(Photo photo, boolean z, boolean z2, QualityBalance qualityBalance, int i, Object obj) {
            if ((i & 1) != 0) {
                z = photo.isMirrored;
            }
            if ((i & 2) != 0) {
                z2 = photo.enableHdr;
            }
            if ((i & 4) != 0) {
                qualityBalance = photo.photoQualityBalance;
            }
            return photo.copy(z, z2, qualityBalance);
        }

        public final boolean component1() {
            return this.isMirrored;
        }

        public final boolean component2() {
            return this.enableHdr;
        }

        public final QualityBalance component3() {
            return this.photoQualityBalance;
        }

        public final Photo copy(boolean z, boolean z2, QualityBalance qualityBalance) {
            Intrinsics.checkNotNullParameter(qualityBalance, "photoQualityBalance");
            return new Photo(z, z2, qualityBalance);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Photo)) {
                return false;
            }
            Photo photo = (Photo) obj;
            return this.isMirrored == photo.isMirrored && this.enableHdr == photo.enableHdr && this.photoQualityBalance == photo.photoQualityBalance;
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.isMirrored) * 31) + Boolean.hashCode(this.enableHdr)) * 31) + this.photoQualityBalance.hashCode();
        }

        public String toString() {
            boolean z = this.isMirrored;
            boolean z2 = this.enableHdr;
            return "Photo(isMirrored=" + z + ", enableHdr=" + z2 + ", photoQualityBalance=" + this.photoQualityBalance + ")";
        }

        public Photo(boolean z, boolean z2, QualityBalance qualityBalance) {
            Intrinsics.checkNotNullParameter(qualityBalance, "photoQualityBalance");
            this.isMirrored = z;
            this.enableHdr = z2;
            this.photoQualityBalance = qualityBalance;
        }

        public final boolean getEnableHdr() {
            return this.enableHdr;
        }

        public final QualityBalance getPhotoQualityBalance() {
            return this.photoQualityBalance;
        }

        public final boolean isMirrored() {
            return this.isMirrored;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\nJ:\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Video;", "", "isMirrored", "", "enableHdr", "bitRateOverride", "", "bitRateMultiplier", "(ZZLjava/lang/Double;Ljava/lang/Double;)V", "getBitRateMultiplier", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getBitRateOverride", "getEnableHdr", "()Z", "component1", "component2", "component3", "component4", "copy", "(ZZLjava/lang/Double;Ljava/lang/Double;)Lcom/mrousavy/camera/core/CameraConfiguration$Video;", "equals", "other", "hashCode", "", "toString", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraConfiguration.kt */
    public static final class Video {
        private final Double bitRateMultiplier;
        private final Double bitRateOverride;
        private final boolean enableHdr;
        private final boolean isMirrored;

        public static /* synthetic */ Video copy$default(Video video, boolean z, boolean z2, Double d, Double d2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = video.isMirrored;
            }
            if ((i & 2) != 0) {
                z2 = video.enableHdr;
            }
            if ((i & 4) != 0) {
                d = video.bitRateOverride;
            }
            if ((i & 8) != 0) {
                d2 = video.bitRateMultiplier;
            }
            return video.copy(z, z2, d, d2);
        }

        public final boolean component1() {
            return this.isMirrored;
        }

        public final boolean component2() {
            return this.enableHdr;
        }

        public final Double component3() {
            return this.bitRateOverride;
        }

        public final Double component4() {
            return this.bitRateMultiplier;
        }

        public final Video copy(boolean z, boolean z2, Double d, Double d2) {
            return new Video(z, z2, d, d2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Video)) {
                return false;
            }
            Video video = (Video) obj;
            return this.isMirrored == video.isMirrored && this.enableHdr == video.enableHdr && Intrinsics.areEqual((Object) this.bitRateOverride, (Object) video.bitRateOverride) && Intrinsics.areEqual((Object) this.bitRateMultiplier, (Object) video.bitRateMultiplier);
        }

        public int hashCode() {
            int hashCode = ((Boolean.hashCode(this.isMirrored) * 31) + Boolean.hashCode(this.enableHdr)) * 31;
            Double d = this.bitRateOverride;
            int i = 0;
            int hashCode2 = (hashCode + (d == null ? 0 : d.hashCode())) * 31;
            Double d2 = this.bitRateMultiplier;
            if (d2 != null) {
                i = d2.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            boolean z = this.isMirrored;
            boolean z2 = this.enableHdr;
            Double d = this.bitRateOverride;
            return "Video(isMirrored=" + z + ", enableHdr=" + z2 + ", bitRateOverride=" + d + ", bitRateMultiplier=" + this.bitRateMultiplier + ")";
        }

        public Video(boolean z, boolean z2, Double d, Double d2) {
            this.isMirrored = z;
            this.enableHdr = z2;
            this.bitRateOverride = d;
            this.bitRateMultiplier = d2;
        }

        public final Double getBitRateMultiplier() {
            return this.bitRateMultiplier;
        }

        public final Double getBitRateOverride() {
            return this.bitRateOverride;
        }

        public final boolean getEnableHdr() {
            return this.enableHdr;
        }

        public final boolean isMirrored() {
            return this.isMirrored;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$FrameProcessor;", "", "isMirrored", "", "pixelFormat", "Lcom/mrousavy/camera/core/types/PixelFormat;", "(ZLcom/mrousavy/camera/core/types/PixelFormat;)V", "()Z", "getPixelFormat", "()Lcom/mrousavy/camera/core/types/PixelFormat;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraConfiguration.kt */
    public static final class FrameProcessor {
        private final boolean isMirrored;
        private final PixelFormat pixelFormat;

        public static /* synthetic */ FrameProcessor copy$default(FrameProcessor frameProcessor, boolean z, PixelFormat pixelFormat2, int i, Object obj) {
            if ((i & 1) != 0) {
                z = frameProcessor.isMirrored;
            }
            if ((i & 2) != 0) {
                pixelFormat2 = frameProcessor.pixelFormat;
            }
            return frameProcessor.copy(z, pixelFormat2);
        }

        public final boolean component1() {
            return this.isMirrored;
        }

        public final PixelFormat component2() {
            return this.pixelFormat;
        }

        public final FrameProcessor copy(boolean z, PixelFormat pixelFormat2) {
            Intrinsics.checkNotNullParameter(pixelFormat2, "pixelFormat");
            return new FrameProcessor(z, pixelFormat2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FrameProcessor)) {
                return false;
            }
            FrameProcessor frameProcessor = (FrameProcessor) obj;
            return this.isMirrored == frameProcessor.isMirrored && this.pixelFormat == frameProcessor.pixelFormat;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.isMirrored) * 31) + this.pixelFormat.hashCode();
        }

        public String toString() {
            boolean z = this.isMirrored;
            return "FrameProcessor(isMirrored=" + z + ", pixelFormat=" + this.pixelFormat + ")";
        }

        public FrameProcessor(boolean z, PixelFormat pixelFormat2) {
            Intrinsics.checkNotNullParameter(pixelFormat2, "pixelFormat");
            this.isMirrored = z;
            this.pixelFormat = pixelFormat2;
        }

        public final PixelFormat getPixelFormat() {
            return this.pixelFormat;
        }

        public final boolean isMirrored() {
            return this.isMirrored;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0018\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Audio;", "", "nothing", "", "(Lkotlin/Unit;)V", "getNothing", "()Lkotlin/Unit;", "Lkotlin/Unit;", "component1", "copy", "(Lkotlin/Unit;)Lcom/mrousavy/camera/core/CameraConfiguration$Audio;", "equals", "", "other", "hashCode", "", "toString", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraConfiguration.kt */
    public static final class Audio {
        private final Unit nothing;

        public static /* synthetic */ Audio copy$default(Audio audio, Unit unit, int i, Object obj) {
            if ((i & 1) != 0) {
                unit = audio.nothing;
            }
            return audio.copy(unit);
        }

        public final void component1() {
        }

        public final Audio copy(Unit unit) {
            Intrinsics.checkNotNullParameter(unit, "nothing");
            return new Audio(unit);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Audio) && Intrinsics.areEqual((Object) this.nothing, (Object) ((Audio) obj).nothing);
        }

        public int hashCode() {
            return this.nothing.hashCode();
        }

        public String toString() {
            return "Audio(nothing=" + this.nothing + ")";
        }

        public Audio(Unit unit) {
            Intrinsics.checkNotNullParameter(unit, "nothing");
            this.nothing = unit;
        }

        public final Unit getNothing() {
            return this.nothing;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Preview;", "", "surfaceProvider", "Landroidx/camera/core/Preview$SurfaceProvider;", "(Landroidx/camera/core/Preview$SurfaceProvider;)V", "getSurfaceProvider", "()Landroidx/camera/core/Preview$SurfaceProvider;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraConfiguration.kt */
    public static final class Preview {
        private final Preview.SurfaceProvider surfaceProvider;

        public static /* synthetic */ Preview copy$default(Preview preview, Preview.SurfaceProvider surfaceProvider2, int i, Object obj) {
            if ((i & 1) != 0) {
                surfaceProvider2 = preview.surfaceProvider;
            }
            return preview.copy(surfaceProvider2);
        }

        public final Preview.SurfaceProvider component1() {
            return this.surfaceProvider;
        }

        public final Preview copy(Preview.SurfaceProvider surfaceProvider2) {
            Intrinsics.checkNotNullParameter(surfaceProvider2, "surfaceProvider");
            return new Preview(surfaceProvider2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Preview) && Intrinsics.areEqual((Object) this.surfaceProvider, (Object) ((Preview) obj).surfaceProvider);
        }

        public int hashCode() {
            return this.surfaceProvider.hashCode();
        }

        public String toString() {
            return "Preview(surfaceProvider=" + this.surfaceProvider + ")";
        }

        public Preview(Preview.SurfaceProvider surfaceProvider2) {
            Intrinsics.checkNotNullParameter(surfaceProvider2, "surfaceProvider");
            this.surfaceProvider = surfaceProvider2;
        }

        public final Preview.SurfaceProvider getSurfaceProvider() {
            return this.surfaceProvider;
        }
    }

    public final Range<Integer> getTargetFpsRange() {
        Integer num = this.minFps;
        if (num != null) {
            int intValue = num.intValue();
            Integer num2 = this.maxFps;
            if (num2 != null) {
                return new Range<>(Integer.valueOf(intValue), Integer.valueOf(num2.intValue()));
            }
        }
        return null;
    }

    public final Float getTargetPreviewAspectRatio() {
        CameraDeviceFormat cameraDeviceFormat = this.format;
        if (cameraDeviceFormat == null) {
            return null;
        }
        Output<Video> output = this.video;
        Output.Enabled enabled = output instanceof Output.Enabled ? (Output.Enabled) output : null;
        Output<Photo> output2 = this.photo;
        Output.Enabled enabled2 = output2 instanceof Output.Enabled ? (Output.Enabled) output2 : null;
        if (enabled != null) {
            return Float.valueOf(((float) cameraDeviceFormat.getVideoWidth()) / ((float) cameraDeviceFormat.getVideoHeight()));
        }
        if (enabled2 != null) {
            return Float.valueOf(((float) cameraDeviceFormat.getPhotoWidth()) / ((float) cameraDeviceFormat.getPhotoHeight()));
        }
        return null;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u0007\bB\u0007\b\u0004¢\u0006\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Output;", "T", "", "()V", "isEnabled", "", "()Z", "Disabled", "Enabled", "Lcom/mrousavy/camera/core/CameraConfiguration$Output$Disabled;", "Lcom/mrousavy/camera/core/CameraConfiguration$Output$Enabled;", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraConfiguration.kt */
    public static abstract class Output<T> {
        public /* synthetic */ Output(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Output() {
        }

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \b*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002¨\u0006\t"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Output$Disabled;", "T", "Lcom/mrousavy/camera/core/CameraConfiguration$Output;", "()V", "equals", "", "other", "", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: CameraConfiguration.kt */
        public static final class Disabled<T> extends Output<T> {
            public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

            public /* synthetic */ Disabled(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Disabled() {
                super((DefaultConstructorMarker) null);
            }

            @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Output$Disabled$Companion;", "", "()V", "create", "Lcom/mrousavy/camera/core/CameraConfiguration$Output$Disabled;", "T", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            /* compiled from: CameraConfiguration.kt */
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final <T> Disabled<T> create() {
                    return new Disabled<>((DefaultConstructorMarker) null);
                }
            }

            public boolean equals(Object obj) {
                return obj instanceof Disabled;
            }
        }

        public final boolean isEnabled() {
            return this instanceof Enabled;
        }

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \f*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0002\u0010\u0004J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002R\u0013\u0010\u0003\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Output$Enabled;", "T", "Lcom/mrousavy/camera/core/CameraConfiguration$Output;", "config", "(Ljava/lang/Object;)V", "getConfig", "()Ljava/lang/Object;", "Ljava/lang/Object;", "equals", "", "other", "", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: CameraConfiguration.kt */
        public static final class Enabled<T> extends Output<T> {
            public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
            private final T config;

            public /* synthetic */ Enabled(Object obj, DefaultConstructorMarker defaultConstructorMarker) {
                this(obj);
            }

            private Enabled(T t) {
                super((DefaultConstructorMarker) null);
                this.config = t;
            }

            public final T getConfig() {
                return this.config;
            }

            @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0002\u0010\u00052\u0006\u0010\u0006\u001a\u0002H\u0005¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Output$Enabled$Companion;", "", "()V", "create", "Lcom/mrousavy/camera/core/CameraConfiguration$Output$Enabled;", "T", "config", "(Ljava/lang/Object;)Lcom/mrousavy/camera/core/CameraConfiguration$Output$Enabled;", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            /* compiled from: CameraConfiguration.kt */
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                private Companion() {
                }

                public final <T> Enabled<T> create(T t) {
                    return new Enabled<>(t, (DefaultConstructorMarker) null);
                }
            }

            public boolean equals(Object obj) {
                return (obj instanceof Enabled) && Intrinsics.areEqual((Object) this.config, (Object) ((Enabled) obj).config);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u001f"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Difference;", "", "deviceChanged", "", "outputsChanged", "sidePropsChanged", "isActiveChanged", "orientationChanged", "locationChanged", "(ZZZZZZ)V", "getDeviceChanged", "()Z", "hasChanges", "getHasChanges", "getLocationChanged", "getOrientationChanged", "getOutputsChanged", "getSidePropsChanged", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraConfiguration.kt */
    public static final class Difference {
        private final boolean deviceChanged;
        private final boolean isActiveChanged;
        private final boolean locationChanged;
        private final boolean orientationChanged;
        private final boolean outputsChanged;
        private final boolean sidePropsChanged;

        public static /* synthetic */ Difference copy$default(Difference difference, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i, Object obj) {
            if ((i & 1) != 0) {
                z = difference.deviceChanged;
            }
            if ((i & 2) != 0) {
                z2 = difference.outputsChanged;
            }
            boolean z7 = z2;
            if ((i & 4) != 0) {
                z3 = difference.sidePropsChanged;
            }
            boolean z8 = z3;
            if ((i & 8) != 0) {
                z4 = difference.isActiveChanged;
            }
            boolean z9 = z4;
            if ((i & 16) != 0) {
                z5 = difference.orientationChanged;
            }
            boolean z10 = z5;
            if ((i & 32) != 0) {
                z6 = difference.locationChanged;
            }
            return difference.copy(z, z7, z8, z9, z10, z6);
        }

        public final boolean component1() {
            return this.deviceChanged;
        }

        public final boolean component2() {
            return this.outputsChanged;
        }

        public final boolean component3() {
            return this.sidePropsChanged;
        }

        public final boolean component4() {
            return this.isActiveChanged;
        }

        public final boolean component5() {
            return this.orientationChanged;
        }

        public final boolean component6() {
            return this.locationChanged;
        }

        public final Difference copy(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
            return new Difference(z, z2, z3, z4, z5, z6);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Difference)) {
                return false;
            }
            Difference difference = (Difference) obj;
            return this.deviceChanged == difference.deviceChanged && this.outputsChanged == difference.outputsChanged && this.sidePropsChanged == difference.sidePropsChanged && this.isActiveChanged == difference.isActiveChanged && this.orientationChanged == difference.orientationChanged && this.locationChanged == difference.locationChanged;
        }

        public int hashCode() {
            return (((((((((Boolean.hashCode(this.deviceChanged) * 31) + Boolean.hashCode(this.outputsChanged)) * 31) + Boolean.hashCode(this.sidePropsChanged)) * 31) + Boolean.hashCode(this.isActiveChanged)) * 31) + Boolean.hashCode(this.orientationChanged)) * 31) + Boolean.hashCode(this.locationChanged);
        }

        public String toString() {
            boolean z = this.deviceChanged;
            boolean z2 = this.outputsChanged;
            boolean z3 = this.sidePropsChanged;
            boolean z4 = this.isActiveChanged;
            boolean z5 = this.orientationChanged;
            return "Difference(deviceChanged=" + z + ", outputsChanged=" + z2 + ", sidePropsChanged=" + z3 + ", isActiveChanged=" + z4 + ", orientationChanged=" + z5 + ", locationChanged=" + this.locationChanged + ")";
        }

        public Difference(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
            this.deviceChanged = z;
            this.outputsChanged = z2;
            this.sidePropsChanged = z3;
            this.isActiveChanged = z4;
            this.orientationChanged = z5;
            this.locationChanged = z6;
        }

        public final boolean getDeviceChanged() {
            return this.deviceChanged;
        }

        public final boolean getOutputsChanged() {
            return this.outputsChanged;
        }

        public final boolean getSidePropsChanged() {
            return this.sidePropsChanged;
        }

        public final boolean isActiveChanged() {
            return this.isActiveChanged;
        }

        public final boolean getOrientationChanged() {
            return this.orientationChanged;
        }

        public final boolean getLocationChanged() {
            return this.locationChanged;
        }

        public final boolean getHasChanges() {
            return this.deviceChanged || this.outputsChanged || this.sidePropsChanged || this.isActiveChanged || this.orientationChanged || this.locationChanged;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0018\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\u0004¨\u0006\n"}, d2 = {"Lcom/mrousavy/camera/core/CameraConfiguration$Companion;", "", "()V", "copyOf", "Lcom/mrousavy/camera/core/CameraConfiguration;", "other", "difference", "Lcom/mrousavy/camera/core/CameraConfiguration$Difference;", "left", "right", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraConfiguration.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
            r0 = com.mrousavy.camera.core.CameraConfiguration.copy$default(r23, (java.lang.String) null, (com.mrousavy.camera.core.CameraConfiguration.Output) null, (com.mrousavy.camera.core.CameraConfiguration.Output) null, (com.mrousavy.camera.core.CameraConfiguration.Output) null, (com.mrousavy.camera.core.CameraConfiguration.Output) null, (com.mrousavy.camera.core.CameraConfiguration.Output) null, (java.lang.Integer) null, (java.lang.Integer) null, false, (com.mrousavy.camera.core.types.OutputOrientation) null, (com.mrousavy.camera.core.types.CameraDeviceFormat) null, false, (com.mrousavy.camera.core.types.Torch) null, (com.mrousavy.camera.core.types.VideoStabilizationMode) null, (java.lang.Double) null, 0.0f, false, (com.mrousavy.camera.core.CameraConfiguration.Output) null, 262143, (java.lang.Object) null);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.mrousavy.camera.core.CameraConfiguration copyOf(com.mrousavy.camera.core.CameraConfiguration r23) {
            /*
                r22 = this;
                if (r23 == 0) goto L_0x0024
                r1 = 0
                r2 = 0
                r3 = 0
                r4 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                r13 = 0
                r14 = 0
                r15 = 0
                r16 = 0
                r17 = 0
                r18 = 0
                r19 = 262143(0x3ffff, float:3.6734E-40)
                r20 = 0
                r0 = r23
                com.mrousavy.camera.core.CameraConfiguration r0 = com.mrousavy.camera.core.CameraConfiguration.copy$default(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
                if (r0 != 0) goto L_0x0045
            L_0x0024:
                com.mrousavy.camera.core.CameraConfiguration r0 = new com.mrousavy.camera.core.CameraConfiguration
                r1 = r0
                r2 = 0
                r3 = 0
                r4 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                r13 = 0
                r14 = 0
                r15 = 0
                r16 = 0
                r17 = 0
                r18 = 0
                r19 = 0
                r20 = 262143(0x3ffff, float:3.6734E-40)
                r21 = 0
                r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            L_0x0045:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.CameraConfiguration.Companion.copyOf(com.mrousavy.camera.core.CameraConfiguration):com.mrousavy.camera.core.CameraConfiguration");
        }

        /* JADX WARNING: Removed duplicated region for block: B:35:0x00ae  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x00eb  */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x00ed  */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x00f2  */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x00fc  */
        /* JADX WARNING: Removed duplicated region for block: B:60:0x00fe  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.mrousavy.camera.core.CameraConfiguration.Difference difference(com.mrousavy.camera.core.CameraConfiguration r12, com.mrousavy.camera.core.CameraConfiguration r13) {
            /*
                r11 = this;
                java.lang.String r0 = "right"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
                r0 = 0
                if (r12 == 0) goto L_0x000d
                com.mrousavy.camera.core.CameraConfiguration$Output r1 = r12.getPhoto()
                goto L_0x000e
            L_0x000d:
                r1 = r0
            L_0x000e:
                com.mrousavy.camera.core.CameraConfiguration$Output r2 = r13.getPhoto()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L_0x0093
                com.mrousavy.camera.core.CameraConfiguration$Output r1 = r12.getVideo()
                com.mrousavy.camera.core.CameraConfiguration$Output r4 = r13.getVideo()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)
                if (r1 == 0) goto L_0x0093
                boolean r1 = r12.getEnableLowLightBoost()
                boolean r4 = r13.getEnableLowLightBoost()
                if (r1 != r4) goto L_0x0093
                com.mrousavy.camera.core.types.VideoStabilizationMode r1 = r12.getVideoStabilizationMode()
                com.mrousavy.camera.core.types.VideoStabilizationMode r4 = r13.getVideoStabilizationMode()
                if (r1 != r4) goto L_0x0093
                com.mrousavy.camera.core.CameraConfiguration$Output r1 = r12.getFrameProcessor()
                com.mrousavy.camera.core.CameraConfiguration$Output r4 = r13.getFrameProcessor()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)
                if (r1 == 0) goto L_0x0093
                com.mrousavy.camera.core.CameraConfiguration$Output r1 = r12.getCodeScanner()
                com.mrousavy.camera.core.CameraConfiguration$Output r4 = r13.getCodeScanner()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)
                if (r1 == 0) goto L_0x0093
                com.mrousavy.camera.core.CameraConfiguration$Output r1 = r12.getPreview()
                com.mrousavy.camera.core.CameraConfiguration$Output r4 = r13.getPreview()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)
                if (r1 == 0) goto L_0x0093
                com.mrousavy.camera.core.types.CameraDeviceFormat r1 = r12.getFormat()
                com.mrousavy.camera.core.types.CameraDeviceFormat r4 = r13.getFormat()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)
                if (r1 == 0) goto L_0x0093
                java.lang.Integer r1 = r12.getMinFps()
                java.lang.Integer r4 = r13.getMinFps()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)
                if (r1 == 0) goto L_0x0093
                java.lang.Integer r1 = r12.getMaxFps()
                java.lang.Integer r4 = r13.getMaxFps()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)
                if (r1 != 0) goto L_0x0091
                goto L_0x0093
            L_0x0091:
                r6 = r2
                goto L_0x0094
            L_0x0093:
                r6 = r3
            L_0x0094:
                if (r6 != 0) goto L_0x00ab
                if (r12 == 0) goto L_0x009d
                java.lang.String r1 = r12.getCameraId()
                goto L_0x009e
            L_0x009d:
                r1 = r0
            L_0x009e:
                java.lang.String r4 = r13.getCameraId()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)
                if (r1 != 0) goto L_0x00a9
                goto L_0x00ab
            L_0x00a9:
                r5 = r2
                goto L_0x00ac
            L_0x00ab:
                r5 = r3
            L_0x00ac:
                if (r5 != 0) goto L_0x00de
                if (r12 == 0) goto L_0x00b5
                com.mrousavy.camera.core.types.Torch r1 = r12.getTorch()
                goto L_0x00b6
            L_0x00b5:
                r1 = r0
            L_0x00b6:
                com.mrousavy.camera.core.types.Torch r4 = r13.getTorch()
                if (r1 != r4) goto L_0x00de
                float r1 = r12.getZoom()
                float r4 = r13.getZoom()
                int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
                if (r1 != 0) goto L_0x00ca
                r1 = r3
                goto L_0x00cb
            L_0x00ca:
                r1 = r2
            L_0x00cb:
                if (r1 == 0) goto L_0x00de
                java.lang.Double r1 = r12.getExposure()
                java.lang.Double r4 = r13.getExposure()
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Double) r1, (java.lang.Double) r4)
                if (r1 != 0) goto L_0x00dc
                goto L_0x00de
            L_0x00dc:
                r7 = r2
                goto L_0x00df
            L_0x00de:
                r7 = r3
            L_0x00df:
                if (r12 == 0) goto L_0x00ed
                boolean r1 = r12.isActive()
                boolean r4 = r13.isActive()
                if (r1 != r4) goto L_0x00ed
                r1 = r3
                goto L_0x00ee
            L_0x00ed:
                r1 = r2
            L_0x00ee:
                r8 = r1 ^ 1
                if (r12 == 0) goto L_0x00f6
                com.mrousavy.camera.core.types.OutputOrientation r0 = r12.getOutputOrientation()
            L_0x00f6:
                com.mrousavy.camera.core.types.OutputOrientation r1 = r13.getOutputOrientation()
                if (r0 == r1) goto L_0x00fe
                r9 = r3
                goto L_0x00ff
            L_0x00fe:
                r9 = r2
            L_0x00ff:
                if (r12 == 0) goto L_0x010c
                boolean r12 = r12.getEnableLocation()
                boolean r13 = r13.getEnableLocation()
                if (r12 != r13) goto L_0x010c
                r2 = r3
            L_0x010c:
                r10 = r2 ^ 1
                com.mrousavy.camera.core.CameraConfiguration$Difference r12 = new com.mrousavy.camera.core.CameraConfiguration$Difference
                r4 = r12
                r4.<init>(r5, r6, r7, r8, r9, r10)
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.CameraConfiguration.Companion.difference(com.mrousavy.camera.core.CameraConfiguration, com.mrousavy.camera.core.CameraConfiguration):com.mrousavy.camera.core.CameraConfiguration$Difference");
        }
    }
}
