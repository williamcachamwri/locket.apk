package androidx.camera.camera2.internal;

import java.util.LinkedHashSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CaptureSessionRepository$1$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ LinkedHashSet f$0;

    public /* synthetic */ CaptureSessionRepository$1$$ExternalSyntheticLambda1(LinkedHashSet linkedHashSet) {
        this.f$0 = linkedHashSet;
    }

    public final void run() {
        CaptureSessionRepository.forceOnClosed(this.f$0);
    }
}
