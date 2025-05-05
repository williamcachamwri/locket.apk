package androidx.media3.session;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionImpl$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ MediaSessionImpl f$0;

    public /* synthetic */ MediaSessionImpl$$ExternalSyntheticLambda8(MediaSessionImpl mediaSessionImpl) {
        this.f$0 = mediaSessionImpl;
    }

    public final void run() {
        this.f$0.notifyPeriodicSessionPositionInfoChangesOnHandler();
    }
}
