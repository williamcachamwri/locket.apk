package expo.modules.camera.next;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\"\u0019\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"cameraEvents", "", "", "getCameraEvents", "()[Ljava/lang/String;", "[Ljava/lang/String;", "expo-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraViewNextModule.kt */
public final class CameraViewNextModuleKt {
    private static final String[] cameraEvents = {"onCameraReady", "onMountError", "onBarcodeScanned", "onFacesDetected", "onFaceDetectionError", "onPictureSaved"};

    public static final String[] getCameraEvents() {
        return cameraEvents;
    }
}
