package androidx.media3.effect;

import androidx.media3.common.GlTextureInfo;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FrameConsumptionManager$$ExternalSyntheticLambda1 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ FrameConsumptionManager f$0;
    public final /* synthetic */ GlTextureInfo f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ FrameConsumptionManager$$ExternalSyntheticLambda1(FrameConsumptionManager frameConsumptionManager, GlTextureInfo glTextureInfo, long j) {
        this.f$0 = frameConsumptionManager;
        this.f$1 = glTextureInfo;
        this.f$2 = j;
    }

    public final void run() {
        this.f$0.m443lambda$queueInputFrame$1$androidxmedia3effectFrameConsumptionManager(this.f$1, this.f$2);
    }
}
