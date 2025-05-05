package androidx.camera.camera2.internal;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class VideoUsageControl$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ VideoUsageControl f$0;

    public /* synthetic */ VideoUsageControl$$ExternalSyntheticLambda2(VideoUsageControl videoUsageControl) {
        this.f$0 = videoUsageControl;
    }

    public final void run() {
        VideoUsageControl.decrementUsage$lambda$1(this.f$0);
    }
}
