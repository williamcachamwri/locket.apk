package androidx.media3.session;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaController$Builder$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MediaControllerHolder f$0;
    public final /* synthetic */ MediaController f$1;

    public /* synthetic */ MediaController$Builder$$ExternalSyntheticLambda0(MediaControllerHolder mediaControllerHolder, MediaController mediaController) {
        this.f$0 = mediaControllerHolder;
        this.f$1 = mediaController;
    }

    public final void run() {
        this.f$0.setController(this.f$1);
    }
}
