package androidx.camera.core.imagecapture;

import androidx.camera.core.impl.ImageReaderProxy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NoMetadataImageReader$$ExternalSyntheticLambda0 implements ImageReaderProxy.OnImageAvailableListener {
    public final /* synthetic */ NoMetadataImageReader f$0;
    public final /* synthetic */ ImageReaderProxy.OnImageAvailableListener f$1;

    public /* synthetic */ NoMetadataImageReader$$ExternalSyntheticLambda0(NoMetadataImageReader noMetadataImageReader, ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
        this.f$0 = noMetadataImageReader;
        this.f$1 = onImageAvailableListener;
    }

    public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
        this.f$0.m161lambda$setOnImageAvailableListener$0$androidxcameracoreimagecaptureNoMetadataImageReader(this.f$1, imageReaderProxy);
    }
}
