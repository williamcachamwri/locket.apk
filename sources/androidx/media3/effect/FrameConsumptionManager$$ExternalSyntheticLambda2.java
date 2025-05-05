package androidx.media3.effect;

import android.util.Pair;
import androidx.media3.effect.VideoFrameProcessingTaskExecutor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FrameConsumptionManager$$ExternalSyntheticLambda2 implements VideoFrameProcessingTaskExecutor.Task {
    public final /* synthetic */ FrameConsumptionManager f$0;
    public final /* synthetic */ Pair f$1;

    public /* synthetic */ FrameConsumptionManager$$ExternalSyntheticLambda2(FrameConsumptionManager frameConsumptionManager, Pair pair) {
        this.f$0 = frameConsumptionManager;
        this.f$1 = pair;
    }

    public final void run() {
        this.f$0.m442lambda$onReadyToAcceptInputFrame$0$androidxmedia3effectFrameConsumptionManager(this.f$1);
    }
}
