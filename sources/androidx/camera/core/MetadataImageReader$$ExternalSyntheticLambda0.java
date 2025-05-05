package androidx.camera.core;

import androidx.camera.core.impl.ImageReaderProxy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MetadataImageReader$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ MetadataImageReader f$0;
    public final /* synthetic */ ImageReaderProxy.OnImageAvailableListener f$1;

    public /* synthetic */ MetadataImageReader$$ExternalSyntheticLambda0(MetadataImageReader metadataImageReader, ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
        this.f$0 = metadataImageReader;
        this.f$1 = onImageAvailableListener;
    }

    public final void run() {
        this.f$0.m148lambda$enqueueImageProxy$1$androidxcameracoreMetadataImageReader(this.f$1);
    }
}
