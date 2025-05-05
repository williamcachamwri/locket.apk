package androidx.camera.core;

import android.media.ImageReader;
import androidx.camera.core.impl.ImageReaderProxy;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AndroidImageReaderProxy$$ExternalSyntheticLambda1 implements ImageReader.OnImageAvailableListener {
    public final /* synthetic */ AndroidImageReaderProxy f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ ImageReaderProxy.OnImageAvailableListener f$2;

    public /* synthetic */ AndroidImageReaderProxy$$ExternalSyntheticLambda1(AndroidImageReaderProxy androidImageReaderProxy, Executor executor, ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
        this.f$0 = androidImageReaderProxy;
        this.f$1 = executor;
        this.f$2 = onImageAvailableListener;
    }

    public final void onImageAvailable(ImageReader imageReader) {
        this.f$0.m134lambda$setOnImageAvailableListener$1$androidxcameracoreAndroidImageReaderProxy(this.f$1, this.f$2, imageReader);
    }
}
