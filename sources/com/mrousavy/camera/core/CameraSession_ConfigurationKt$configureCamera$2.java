package com.mrousavy.camera.core;

import android.util.Log;
import androidx.camera.core.CameraState;
import com.mrousavy.camera.core.extensions.StateError_toCameraErrorKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "state", "Landroidx/camera/core/CameraState;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraSession+Configuration.kt */
final class CameraSession_ConfigurationKt$configureCamera$2 extends Lambda implements Function1<CameraState, Unit> {
    final /* synthetic */ Ref.BooleanRef $lastIsStreaming;
    final /* synthetic */ CameraSession $this_configureCamera;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CameraSession_ConfigurationKt$configureCamera$2(Ref.BooleanRef booleanRef, CameraSession cameraSession) {
        super(1);
        this.$lastIsStreaming = booleanRef;
        this.$this_configureCamera = cameraSession;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CameraState) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(CameraState cameraState) {
        boolean z = true;
        Log.i(CameraSession.TAG, "Camera State: " + cameraState.getType() + " (has error: " + (cameraState.getError() != null) + ")");
        if (cameraState.getType() != CameraState.Type.OPEN) {
            z = false;
        }
        if (z != this.$lastIsStreaming.element) {
            if (z) {
                this.$this_configureCamera.getCallback$react_native_vision_camera_release().onStarted();
            } else {
                this.$this_configureCamera.getCallback$react_native_vision_camera_release().onStopped();
            }
            this.$lastIsStreaming.element = z;
        }
        CameraState.StateError error = cameraState.getError();
        if (error != null) {
            this.$this_configureCamera.getCallback$react_native_vision_camera_release().onError(StateError_toCameraErrorKt.toCameraError(error));
        }
    }
}
