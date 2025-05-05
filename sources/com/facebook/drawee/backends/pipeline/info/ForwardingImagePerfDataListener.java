package com.facebook.drawee.backends.pipeline.info;

import com.facebook.fresco.ui.common.ImageLoadStatus;
import com.facebook.fresco.ui.common.ImagePerfData;
import com.facebook.fresco.ui.common.ImagePerfDataListener;
import com.facebook.fresco.ui.common.VisibilityState;
import java.util.Collection;

public class ForwardingImagePerfDataListener implements ImagePerfDataListener {
    private final Collection<ImagePerfDataListener> mListeners;

    public ForwardingImagePerfDataListener(Collection<ImagePerfDataListener> collection) {
        this.mListeners = collection;
    }

    public void onImageLoadStatusUpdated(ImagePerfData imagePerfData, ImageLoadStatus imageLoadStatus) {
        for (ImagePerfDataListener onImageLoadStatusUpdated : this.mListeners) {
            onImageLoadStatusUpdated.onImageLoadStatusUpdated(imagePerfData, imageLoadStatus);
        }
    }

    public void onImageVisibilityUpdated(ImagePerfData imagePerfData, VisibilityState visibilityState) {
        for (ImagePerfDataListener onImageVisibilityUpdated : this.mListeners) {
            onImageVisibilityUpdated.onImageVisibilityUpdated(imagePerfData, visibilityState);
        }
    }
}
