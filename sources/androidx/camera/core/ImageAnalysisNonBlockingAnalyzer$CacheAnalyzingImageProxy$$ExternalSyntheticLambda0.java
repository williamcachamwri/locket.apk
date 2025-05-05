package androidx.camera.core;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageAnalysisNonBlockingAnalyzer$CacheAnalyzingImageProxy$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ImageAnalysisNonBlockingAnalyzer f$0;

    public /* synthetic */ ImageAnalysisNonBlockingAnalyzer$CacheAnalyzingImageProxy$$ExternalSyntheticLambda0(ImageAnalysisNonBlockingAnalyzer imageAnalysisNonBlockingAnalyzer) {
        this.f$0 = imageAnalysisNonBlockingAnalyzer;
    }

    public final void run() {
        this.f$0.analyzeCachedImage();
    }
}
