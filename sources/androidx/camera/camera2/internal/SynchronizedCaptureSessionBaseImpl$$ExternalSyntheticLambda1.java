package androidx.camera.camera2.internal;

import androidx.camera.core.impl.utils.futures.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda1 implements AsyncFunction {
    public final /* synthetic */ SynchronizedCaptureSessionBaseImpl f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ SynchronizedCaptureSessionBaseImpl$$ExternalSyntheticLambda1(SynchronizedCaptureSessionBaseImpl synchronizedCaptureSessionBaseImpl, List list) {
        this.f$0 = synchronizedCaptureSessionBaseImpl;
        this.f$1 = list;
    }

    public final ListenableFuture apply(Object obj) {
        return this.f$0.m90lambda$startWithDeferrableSurface$1$androidxcameracamera2internalSynchronizedCaptureSessionBaseImpl(this.f$1, (List) obj);
    }
}
