package androidx.media3.effect;

import androidx.media3.common.FrameInfo;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TexIdTextureManager$$ExternalSyntheticLambda1 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ TexIdTextureManager f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ FrameInfo f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ TexIdTextureManager$$ExternalSyntheticLambda1(TexIdTextureManager texIdTextureManager, int i, FrameInfo frameInfo, long j) {
        this.f$0 = texIdTextureManager;
        this.f$1 = i;
        this.f$2 = frameInfo;
        this.f$3 = j;
    }

    public final void run() {
        this.f$0.m458lambda$queueInputTexture$1$androidxmedia3effectTexIdTextureManager(this.f$1, this.f$2, this.f$3);
    }
}
