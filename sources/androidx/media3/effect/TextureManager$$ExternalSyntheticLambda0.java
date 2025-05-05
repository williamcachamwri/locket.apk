package androidx.media3.effect;

import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TextureManager$$ExternalSyntheticLambda0 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ TextureManager f$0;

    public /* synthetic */ TextureManager$$ExternalSyntheticLambda0(TextureManager textureManager) {
        this.f$0 = textureManager;
    }

    public final void run() {
        this.f$0.flush();
    }
}
