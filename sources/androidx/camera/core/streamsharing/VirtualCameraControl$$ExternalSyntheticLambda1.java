package androidx.camera.core.streamsharing;

import androidx.camera.core.impl.utils.futures.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VirtualCameraControl$$ExternalSyntheticLambda1 implements AsyncFunction {
    public final /* synthetic */ VirtualCameraControl f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ VirtualCameraControl$$ExternalSyntheticLambda1(VirtualCameraControl virtualCameraControl, List list) {
        this.f$0 = virtualCameraControl;
        this.f$1 = list;
    }

    public final ListenableFuture apply(Object obj) {
        return this.f$0.m226lambda$submitStillCaptureRequests$1$androidxcameracorestreamsharingVirtualCameraControl(this.f$1, (Void) obj);
    }
}
