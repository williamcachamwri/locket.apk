package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DebugViewProvider;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.effect.DefaultVideoFrameProcessor;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultVideoFrameProcessor$Factory$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ DefaultVideoFrameProcessor.Factory f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ DebugViewProvider f$2;
    public final /* synthetic */ ColorInfo f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ VideoFrameProcessingTaskExecutor f$5;
    public final /* synthetic */ Executor f$6;
    public final /* synthetic */ VideoFrameProcessor.Listener f$7;

    public /* synthetic */ DefaultVideoFrameProcessor$Factory$$ExternalSyntheticLambda1(DefaultVideoFrameProcessor.Factory factory, Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, Executor executor, VideoFrameProcessor.Listener listener) {
        this.f$0 = factory;
        this.f$1 = context;
        this.f$2 = debugViewProvider;
        this.f$3 = colorInfo;
        this.f$4 = z;
        this.f$5 = videoFrameProcessingTaskExecutor;
        this.f$6 = executor;
        this.f$7 = listener;
    }

    public final Object call() {
        return this.f$0.m423lambda$create$0$androidxmedia3effectDefaultVideoFrameProcessor$Factory(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
