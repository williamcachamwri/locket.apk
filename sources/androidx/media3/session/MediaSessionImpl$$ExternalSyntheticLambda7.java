package androidx.media3.session;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;
    public final /* synthetic */ PlayerWrapper f$1;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda7(MediaSessionImpl mediaSessionImpl, PlayerWrapper playerWrapper) {
        this.f$0 = mediaSessionImpl;
        this.f$1 = playerWrapper;
    }

    public final void run() {
        this.f$0.setPlayerInternal((PlayerWrapper) null, this.f$1);
    }
}
