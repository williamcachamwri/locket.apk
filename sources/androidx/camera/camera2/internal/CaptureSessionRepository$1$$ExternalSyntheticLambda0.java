package androidx.camera.camera2.internal;

import androidx.camera.camera2.internal.CaptureSessionRepository;
import java.util.LinkedHashSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CaptureSessionRepository$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ LinkedHashSet f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ CaptureSessionRepository$1$$ExternalSyntheticLambda0(LinkedHashSet linkedHashSet, int i) {
        this.f$0 = linkedHashSet;
        this.f$1 = i;
    }

    public final void run() {
        CaptureSessionRepository.AnonymousClass1.lambda$dispatchOnError$1(this.f$0, this.f$1);
    }
}
