package androidx.camera.extensions.internal.sessionprocessor;

import android.media.ImageReader;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SessionProcessorBase$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageReader f$0;

    public /* synthetic */ SessionProcessorBase$$ExternalSyntheticLambda0(ImageReader imageReader) {
        this.f$0 = imageReader;
    }

    public final void run() {
        this.f$0.close();
    }
}
