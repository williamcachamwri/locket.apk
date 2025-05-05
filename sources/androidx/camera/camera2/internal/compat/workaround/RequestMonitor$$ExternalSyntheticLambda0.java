package androidx.camera.camera2.internal.compat.workaround;

import androidx.camera.camera2.internal.compat.workaround.RequestMonitor;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class RequestMonitor$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ RequestMonitor f$0;
    public final /* synthetic */ RequestMonitor.RequestCompleteListener f$1;
    public final /* synthetic */ ListenableFuture f$2;

    public /* synthetic */ RequestMonitor$$ExternalSyntheticLambda0(RequestMonitor requestMonitor, RequestMonitor.RequestCompleteListener requestCompleteListener, ListenableFuture listenableFuture) {
        this.f$0 = requestMonitor;
        this.f$1 = requestCompleteListener;
        this.f$2 = listenableFuture;
    }

    public final void run() {
        this.f$0.m123lambda$createMonitorListener$1$androidxcameracamera2internalcompatworkaroundRequestMonitor(this.f$1, this.f$2);
    }
}
