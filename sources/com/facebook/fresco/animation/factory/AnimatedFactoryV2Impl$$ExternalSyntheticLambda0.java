package com.facebook.fresco.animation.factory;

import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AnimatedFactoryV2Impl$$ExternalSyntheticLambda0 implements ImageDecoder {
    public final /* synthetic */ AnimatedFactoryV2Impl f$0;

    public /* synthetic */ AnimatedFactoryV2Impl$$ExternalSyntheticLambda0(AnimatedFactoryV2Impl animatedFactoryV2Impl) {
        this.f$0 = animatedFactoryV2Impl;
    }

    public final CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        return this.f$0.m1210lambda$getWebPDecoder$0$comfacebookfrescoanimationfactoryAnimatedFactoryV2Impl(encodedImage, i, qualityInfo, imageDecodeOptions);
    }
}
