package androidx.media3.session;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SequencedFutureManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SequencedFutureManager f$0;

    public /* synthetic */ SequencedFutureManager$$ExternalSyntheticLambda0(SequencedFutureManager sequencedFutureManager) {
        this.f$0 = sequencedFutureManager;
    }

    public final void run() {
        this.f$0.release();
    }
}
