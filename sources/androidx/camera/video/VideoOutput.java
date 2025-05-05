package androidx.camera.video;

import androidx.camera.core.CameraInfo;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.ConstantObservable;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.Timebase;

public interface VideoOutput {

    public enum SourceState {
        ACTIVE_STREAMING,
        ACTIVE_NON_STREAMING,
        INACTIVE
    }

    void onSourceStateChanged(SourceState sourceState) {
    }

    void onSurfaceRequested(SurfaceRequest surfaceRequest);

    void onSurfaceRequested(SurfaceRequest surfaceRequest, Timebase timebase) {
        onSurfaceRequested(surfaceRequest);
    }

    Observable<StreamInfo> getStreamInfo() {
        return StreamInfo.ALWAYS_ACTIVE_OBSERVABLE;
    }

    Observable<MediaSpec> getMediaSpec() {
        return ConstantObservable.withValue(null);
    }

    Observable<Boolean> isSourceStreamRequired() {
        return ConstantObservable.withValue(false);
    }

    VideoCapabilities getMediaCapabilities(CameraInfo cameraInfo) {
        return VideoCapabilities.EMPTY;
    }
}
