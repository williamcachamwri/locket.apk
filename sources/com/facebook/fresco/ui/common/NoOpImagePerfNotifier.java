package com.facebook.fresco.ui.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/facebook/fresco/ui/common/NoOpImagePerfNotifier;", "Lcom/facebook/fresco/ui/common/ImagePerfNotifier;", "()V", "notifyListenersOfVisibilityStateUpdate", "", "state", "Lcom/facebook/fresco/ui/common/ImagePerfState;", "visibilityState", "Lcom/facebook/fresco/ui/common/VisibilityState;", "notifyStatusUpdated", "imageLoadStatus", "Lcom/facebook/fresco/ui/common/ImageLoadStatus;", "ui-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NoOpImagePerfNotifier.kt */
public final class NoOpImagePerfNotifier implements ImagePerfNotifier {
    public static final NoOpImagePerfNotifier INSTANCE = new NoOpImagePerfNotifier();

    public void notifyListenersOfVisibilityStateUpdate(ImagePerfState imagePerfState, VisibilityState visibilityState) {
        Intrinsics.checkNotNullParameter(imagePerfState, "state");
        Intrinsics.checkNotNullParameter(visibilityState, "visibilityState");
    }

    public void notifyStatusUpdated(ImagePerfState imagePerfState, ImageLoadStatus imageLoadStatus) {
        Intrinsics.checkNotNullParameter(imagePerfState, "state");
        Intrinsics.checkNotNullParameter(imageLoadStatus, "imageLoadStatus");
    }

    private NoOpImagePerfNotifier() {
    }
}
