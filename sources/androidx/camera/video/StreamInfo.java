package androidx.camera.video;

import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.ConstantObservable;
import androidx.camera.core.impl.Observable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class StreamInfo {
    static final Observable<StreamInfo> ALWAYS_ACTIVE_OBSERVABLE = ConstantObservable.withValue(of(0, StreamState.ACTIVE));
    static final Set<Integer> NON_SURFACE_STREAM_ID = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Integer[]{0, -1})));
    static final int STREAM_ID_ANY = 0;
    static final int STREAM_ID_ERROR = -1;
    static final StreamInfo STREAM_INFO_ANY_INACTIVE = of(0, StreamState.INACTIVE);

    enum StreamState {
        ACTIVE,
        INACTIVE
    }

    public abstract int getId();

    public abstract SurfaceRequest.TransformationInfo getInProgressTransformationInfo();

    public abstract StreamState getStreamState();

    StreamInfo() {
    }

    static StreamInfo of(int i, StreamState streamState) {
        return new AutoValue_StreamInfo(i, streamState, (SurfaceRequest.TransformationInfo) null);
    }

    static StreamInfo of(int i, StreamState streamState, SurfaceRequest.TransformationInfo transformationInfo) {
        return new AutoValue_StreamInfo(i, streamState, transformationInfo);
    }
}
