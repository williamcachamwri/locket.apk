package com.facebook.fresco.ui.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/facebook/fresco/ui/common/ImagePerfDataNotifier;", "Lcom/facebook/fresco/ui/common/ImagePerfNotifier;", "perfDataListener", "Lcom/facebook/fresco/ui/common/ImagePerfDataListener;", "(Lcom/facebook/fresco/ui/common/ImagePerfDataListener;)V", "notifyListenersOfVisibilityStateUpdate", "", "state", "Lcom/facebook/fresco/ui/common/ImagePerfState;", "visibilityState", "Lcom/facebook/fresco/ui/common/VisibilityState;", "notifyStatusUpdated", "imageLoadStatus", "Lcom/facebook/fresco/ui/common/ImageLoadStatus;", "ui-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImagePerfDataNotifier.kt */
public class ImagePerfDataNotifier implements ImagePerfNotifier {
    private final ImagePerfDataListener perfDataListener;

    public ImagePerfDataNotifier(ImagePerfDataListener imagePerfDataListener) {
        Intrinsics.checkNotNullParameter(imagePerfDataListener, "perfDataListener");
        this.perfDataListener = imagePerfDataListener;
    }

    public void notifyListenersOfVisibilityStateUpdate(ImagePerfState imagePerfState, VisibilityState visibilityState) {
        Intrinsics.checkNotNullParameter(imagePerfState, "state");
        Intrinsics.checkNotNullParameter(visibilityState, "visibilityState");
        this.perfDataListener.onImageVisibilityUpdated(imagePerfState.snapshot(), visibilityState);
    }

    public void notifyStatusUpdated(ImagePerfState imagePerfState, ImageLoadStatus imageLoadStatus) {
        Intrinsics.checkNotNullParameter(imagePerfState, "state");
        Intrinsics.checkNotNullParameter(imageLoadStatus, "imageLoadStatus");
        this.perfDataListener.onImageLoadStatusUpdated(imagePerfState.snapshot(), imageLoadStatus);
    }
}
