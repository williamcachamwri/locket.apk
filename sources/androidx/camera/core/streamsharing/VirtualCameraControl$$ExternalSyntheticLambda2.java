package androidx.camera.core.streamsharing;

import androidx.camera.core.imagecapture.CameraCapturePipeline;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VirtualCameraControl$$ExternalSyntheticLambda2 implements AsyncFunction {
    public final /* synthetic */ ListenableFuture f$0;

    public /* synthetic */ VirtualCameraControl$$ExternalSyntheticLambda2(ListenableFuture listenableFuture) {
        this.f$0 = listenableFuture;
    }

    public final ListenableFuture apply(Object obj) {
        return ((CameraCapturePipeline) this.f$0.get()).invokePostCapture();
    }
}
