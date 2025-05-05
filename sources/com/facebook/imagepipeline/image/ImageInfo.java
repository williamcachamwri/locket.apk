package com.facebook.imagepipeline.image;

public interface ImageInfo extends HasImageMetadata {
    int getHeight();

    QualityInfo getQualityInfo();

    int getSizeInBytes() {
        return 0;
    }

    int getWidth();
}
