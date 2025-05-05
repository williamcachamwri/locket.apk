package com.facebook.imagepipeline.decoder;

import com.facebook.imagepipeline.image.QualityInfo;

public interface ProgressiveJpegConfig {
    boolean decodeProgressively();

    int getNextScanNumberToDecode(int i);

    QualityInfo getQualityInfo(int i);
}
