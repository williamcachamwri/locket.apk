package androidx.camera.core;

import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.ImageAnalysisNonBlockingAnalyzer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ImageAnalysisNonBlockingAnalyzer$CacheAnalyzingImageProxy$$ExternalSyntheticLambda1 implements ForwardingImageProxy.OnImageCloseListener {
    public final /* synthetic */ ImageAnalysisNonBlockingAnalyzer.CacheAnalyzingImageProxy f$0;

    public /* synthetic */ ImageAnalysisNonBlockingAnalyzer$CacheAnalyzingImageProxy$$ExternalSyntheticLambda1(ImageAnalysisNonBlockingAnalyzer.CacheAnalyzingImageProxy cacheAnalyzingImageProxy) {
        this.f$0 = cacheAnalyzingImageProxy;
    }

    public final void onImageClose(ImageProxy imageProxy) {
        this.f$0.m143lambda$new$1$androidxcameracoreImageAnalysisNonBlockingAnalyzer$CacheAnalyzingImageProxy(imageProxy);
    }
}
