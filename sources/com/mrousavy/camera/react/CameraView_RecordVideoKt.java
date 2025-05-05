package com.mrousavy.camera.react;

import androidx.core.content.ContextCompat;
import com.facebook.react.bridge.Callback;
import com.mrousavy.camera.core.CameraSession_VideoKt;
import com.mrousavy.camera.core.MicrophonePermissionError;
import com.mrousavy.camera.core.types.RecordVideoOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0002¨\u0006\u000b"}, d2 = {"cancelRecording", "", "Lcom/mrousavy/camera/react/CameraView;", "pauseRecording", "resumeRecording", "startRecording", "options", "Lcom/mrousavy/camera/core/types/RecordVideoOptions;", "onRecordCallback", "Lcom/facebook/react/bridge/Callback;", "stopRecording", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraView+RecordVideo.kt */
public final class CameraView_RecordVideoKt {
    public static final void startRecording(CameraView cameraView, RecordVideoOptions recordVideoOptions, Callback callback) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        Intrinsics.checkNotNullParameter(recordVideoOptions, "options");
        Intrinsics.checkNotNullParameter(callback, "onRecordCallback");
        if (!cameraView.getAudio() || ContextCompat.checkSelfPermission(cameraView.getContext(), "android.permission.RECORD_AUDIO") == 0) {
            CameraSession_VideoKt.startRecording(cameraView.getCameraSession$react_native_vision_camera_release(), cameraView.getAudio(), recordVideoOptions, new CameraView_RecordVideoKt$startRecording$callback$1(callback), new CameraView_RecordVideoKt$startRecording$onError$1(callback));
            return;
        }
        throw new MicrophonePermissionError();
    }

    public static final void pauseRecording(CameraView cameraView) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        CameraSession_VideoKt.pauseRecording(cameraView.getCameraSession$react_native_vision_camera_release());
    }

    public static final void resumeRecording(CameraView cameraView) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        CameraSession_VideoKt.resumeRecording(cameraView.getCameraSession$react_native_vision_camera_release());
    }

    public static final void stopRecording(CameraView cameraView) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        CameraSession_VideoKt.stopRecording(cameraView.getCameraSession$react_native_vision_camera_release());
    }

    public static final void cancelRecording(CameraView cameraView) {
        Intrinsics.checkNotNullParameter(cameraView, "<this>");
        CameraSession_VideoKt.cancelRecording(cameraView.getCameraSession$react_native_vision_camera_release());
    }
}
