package expo.modules.camera.next;

import expo.modules.kotlin.exception.CodedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lexpo/modules/camera/next/CameraExceptions;", "", "()V", "ImageCaptureFailed", "VideoRecordingFailed", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraExceptions.kt */
public final class CameraExceptions {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/camera/next/CameraExceptions$ImageCaptureFailed;", "Lexpo/modules/kotlin/exception/CodedException;", "()V", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraExceptions.kt */
    public static final class ImageCaptureFailed extends CodedException {
        public ImageCaptureFailed() {
            super("Failed to capture image", (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/camera/next/CameraExceptions$VideoRecordingFailed;", "Lexpo/modules/kotlin/exception/CodedException;", "cause", "", "(Ljava/lang/String;)V", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraExceptions.kt */
    public static final class VideoRecordingFailed extends CodedException {
        public VideoRecordingFailed(String str) {
            super("Video recording failed: " + str, (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }
}
