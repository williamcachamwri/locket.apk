package com.mrousavy.camera.frameprocessors;

import android.hardware.HardwareBuffer;
import android.media.Image;
import android.os.Build;
import androidx.camera.core.ImageProxy;
import com.mrousavy.camera.core.FrameInvalidError;
import com.mrousavy.camera.core.HardwareBuffersNotAvailableError;
import com.mrousavy.camera.core.types.Orientation;
import com.mrousavy.camera.core.types.PixelFormat;

public class Frame {
    private final ImageProxy imageProxy;
    private int refCount = 0;

    public Frame(ImageProxy imageProxy2) {
        this.imageProxy = imageProxy2;
    }

    private void assertIsValid() throws FrameInvalidError {
        if (!getIsImageValid(this.imageProxy)) {
            throw new FrameInvalidError();
        }
    }

    public ImageProxy getImageProxy() throws FrameInvalidError {
        assertIsValid();
        return this.imageProxy;
    }

    public Image getImage() throws FrameInvalidError {
        assertIsValid();
        Image image = this.imageProxy.getImage();
        if (image != null) {
            return image;
        }
        throw new FrameInvalidError();
    }

    public int getWidth() throws FrameInvalidError {
        assertIsValid();
        return this.imageProxy.getWidth();
    }

    public int getHeight() throws FrameInvalidError {
        assertIsValid();
        return this.imageProxy.getHeight();
    }

    public boolean getIsValid() {
        return getIsImageValid(this.imageProxy);
    }

    public boolean getIsMirrored() throws FrameInvalidError {
        assertIsValid();
        float[] fArr = new float[9];
        this.imageProxy.getImageInfo().getSensorToBufferTransformMatrix().getValues(fArr);
        return fArr[0] < 0.0f;
    }

    public long getTimestamp() throws FrameInvalidError {
        assertIsValid();
        return this.imageProxy.getImageInfo().getTimestamp();
    }

    public Orientation getOrientation() throws FrameInvalidError {
        assertIsValid();
        return Orientation.Companion.fromRotationDegrees(this.imageProxy.getImageInfo().getRotationDegrees()).reversed();
    }

    public PixelFormat getPixelFormat() throws FrameInvalidError {
        assertIsValid();
        return PixelFormat.Companion.fromImageFormat(this.imageProxy.getFormat());
    }

    public int getPlanesCount() throws FrameInvalidError {
        assertIsValid();
        return this.imageProxy.getPlanes().length;
    }

    public int getBytesPerRow() throws FrameInvalidError {
        assertIsValid();
        return this.imageProxy.getPlanes()[0].getRowStride();
    }

    private Object getHardwareBufferBoxed() throws HardwareBuffersNotAvailableError, FrameInvalidError {
        return getHardwareBuffer();
    }

    public HardwareBuffer getHardwareBuffer() throws HardwareBuffersNotAvailableError, FrameInvalidError {
        if (Build.VERSION.SDK_INT >= 28) {
            assertIsValid();
            return getImage().getHardwareBuffer();
        }
        throw new HardwareBuffersNotAvailableError();
    }

    private synchronized boolean getIsImageValid(ImageProxy imageProxy2) {
        if (this.refCount <= 0) {
            return false;
        }
        try {
            imageProxy2.getFormat();
            return true;
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    public synchronized void incrementRefCount() {
        this.refCount++;
    }

    public synchronized void decrementRefCount() {
        int i = this.refCount - 1;
        this.refCount = i;
        if (i <= 0) {
            close();
        }
    }

    private void close() {
        this.imageProxy.close();
    }
}
