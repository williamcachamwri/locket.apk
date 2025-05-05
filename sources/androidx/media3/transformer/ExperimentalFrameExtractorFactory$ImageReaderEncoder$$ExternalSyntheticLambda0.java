package androidx.media3.transformer;

import android.media.ImageReader;
import androidx.media3.transformer.ExperimentalFrameExtractorFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExperimentalFrameExtractorFactory$ImageReaderEncoder$$ExternalSyntheticLambda0 implements ImageReader.OnImageAvailableListener {
    public final /* synthetic */ ExperimentalFrameExtractorFactory.ImageReaderEncoder f$0;
    public final /* synthetic */ ExperimentalFrameExtractorFactory.Listener f$1;

    public /* synthetic */ ExperimentalFrameExtractorFactory$ImageReaderEncoder$$ExternalSyntheticLambda0(ExperimentalFrameExtractorFactory.ImageReaderEncoder imageReaderEncoder, ExperimentalFrameExtractorFactory.Listener listener) {
        this.f$0 = imageReaderEncoder;
        this.f$1 = listener;
    }

    public final void onImageAvailable(ImageReader imageReader) {
        this.f$0.m1122lambda$new$0$androidxmedia3transformerExperimentalFrameExtractorFactory$ImageReaderEncoder(this.f$1, imageReader);
    }
}
