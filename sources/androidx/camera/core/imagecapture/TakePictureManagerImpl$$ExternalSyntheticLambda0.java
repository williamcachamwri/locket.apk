package androidx.camera.core.imagecapture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TakePictureManagerImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ TakePictureManagerImpl f$0;

    public /* synthetic */ TakePictureManagerImpl$$ExternalSyntheticLambda0(TakePictureManagerImpl takePictureManagerImpl) {
        this.f$0 = takePictureManagerImpl;
    }

    public final void run() {
        this.f$0.issueNextRequest();
    }
}
