package com.mrousavy.camera.core;

import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import com.mrousavy.camera.core.CameraSession;
import com.mrousavy.camera.frameprocessors.Frame;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/mrousavy/camera/core/FrameProcessorPipeline;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "callback", "Lcom/mrousavy/camera/core/CameraSession$Callback;", "(Lcom/mrousavy/camera/core/CameraSession$Callback;)V", "analyze", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FrameProcessorPipeline.kt */
public final class FrameProcessorPipeline implements ImageAnalysis.Analyzer {
    private final CameraSession.Callback callback;

    public FrameProcessorPipeline(CameraSession.Callback callback2) {
        Intrinsics.checkNotNullParameter(callback2, "callback");
        this.callback = callback2;
    }

    public void analyze(ImageProxy imageProxy) {
        Intrinsics.checkNotNullParameter(imageProxy, "imageProxy");
        Frame frame = new Frame(imageProxy);
        try {
            frame.incrementRefCount();
            this.callback.onFrame(frame);
        } finally {
            frame.decrementRefCount();
        }
    }
}
