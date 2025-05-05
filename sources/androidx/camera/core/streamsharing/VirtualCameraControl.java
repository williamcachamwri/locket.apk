package androidx.camera.core.streamsharing;

import androidx.camera.core.imagecapture.CameraCapturePipeline;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.ForwardingCameraControl;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class VirtualCameraControl extends ForwardingCameraControl {
    private static final int DEFAULT_JPEG_QUALITY = 100;
    private static final int DEFAULT_ROTATION_DEGREES = 0;
    private final StreamSharing.Control mStreamSharingControl;

    VirtualCameraControl(CameraControlInternal cameraControlInternal, StreamSharing.Control control) {
        super(cameraControlInternal);
        this.mStreamSharingControl = control;
    }

    public ListenableFuture<List<Void>> submitStillCaptureRequests(List<CaptureConfig> list, int i, int i2) {
        boolean z = true;
        if (list.size() != 1) {
            z = false;
        }
        Preconditions.checkArgument(z, "Only support one capture config.");
        ListenableFuture<CameraCapturePipeline> cameraCapturePipelineAsync = getCameraCapturePipelineAsync(i, i2);
        return Futures.allAsList(Collections.singletonList(FutureChain.from(cameraCapturePipelineAsync).transformAsync(new VirtualCameraControl$$ExternalSyntheticLambda0(cameraCapturePipelineAsync), CameraXExecutors.directExecutor()).transformAsync(new VirtualCameraControl$$ExternalSyntheticLambda1(this, list), CameraXExecutors.directExecutor()).transformAsync(new VirtualCameraControl$$ExternalSyntheticLambda2(cameraCapturePipelineAsync), CameraXExecutors.directExecutor())));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$submitStillCaptureRequests$1$androidx-camera-core-streamsharing-VirtualCameraControl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m226lambda$submitStillCaptureRequests$1$androidxcameracorestreamsharingVirtualCameraControl(List list, Void voidR) throws Exception {
        return this.mStreamSharingControl.jpegSnapshot(getJpegQuality((CaptureConfig) list.get(0)), getRotationDegrees((CaptureConfig) list.get(0)));
    }

    private int getJpegQuality(CaptureConfig captureConfig) {
        return ((Integer) Objects.requireNonNull((Integer) captureConfig.getImplementationOptions().retrieveOption(CaptureConfig.OPTION_JPEG_QUALITY, 100))).intValue();
    }

    private int getRotationDegrees(CaptureConfig captureConfig) {
        return ((Integer) Objects.requireNonNull((Integer) captureConfig.getImplementationOptions().retrieveOption(CaptureConfig.OPTION_ROTATION, 0))).intValue();
    }
}
