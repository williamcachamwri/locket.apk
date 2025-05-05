package androidx.camera.core.internal.utils;

import androidx.camera.core.ImageInfo;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.CameraCaptureMetaData;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraCaptureResults;
import androidx.camera.core.internal.utils.RingBuffer;

public final class ZslRingBuffer extends ArrayRingBuffer<ImageProxy> {
    public ZslRingBuffer(int i, RingBuffer.OnRemoveCallback<ImageProxy> onRemoveCallback) {
        super(i, onRemoveCallback);
    }

    public void enqueue(ImageProxy imageProxy) {
        if (isValidZslFrame(imageProxy.getImageInfo())) {
            super.enqueue(imageProxy);
        } else {
            this.mOnRemoveCallback.onRemove(imageProxy);
        }
    }

    private boolean isValidZslFrame(ImageInfo imageInfo) {
        CameraCaptureResult retrieveCameraCaptureResult = CameraCaptureResults.retrieveCameraCaptureResult(imageInfo);
        if ((retrieveCameraCaptureResult.getAfState() == CameraCaptureMetaData.AfState.LOCKED_FOCUSED || retrieveCameraCaptureResult.getAfState() == CameraCaptureMetaData.AfState.PASSIVE_FOCUSED) && retrieveCameraCaptureResult.getAeState() == CameraCaptureMetaData.AeState.CONVERGED && retrieveCameraCaptureResult.getAwbState() == CameraCaptureMetaData.AwbState.CONVERGED) {
            return true;
        }
        return false;
    }
}
