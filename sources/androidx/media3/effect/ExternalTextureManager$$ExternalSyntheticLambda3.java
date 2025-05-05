package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExternalTextureManager$$ExternalSyntheticLambda3 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ ExternalTextureManager f$0;

    public /* synthetic */ ExternalTextureManager$$ExternalSyntheticLambda3(ExternalTextureManager externalTextureManager) {
        this.f$0 = externalTextureManager;
    }

    public final void run() {
        this.f$0.forceSignalEndOfStream();
    }
}
