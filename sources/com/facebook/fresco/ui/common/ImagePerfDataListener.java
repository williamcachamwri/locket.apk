package com.facebook.fresco.ui.common;

public interface ImagePerfDataListener {
    void onImageLoadStatusUpdated(ImagePerfData imagePerfData, ImageLoadStatus imageLoadStatus);

    void onImageVisibilityUpdated(ImagePerfData imagePerfData, VisibilityState visibilityState);
}
