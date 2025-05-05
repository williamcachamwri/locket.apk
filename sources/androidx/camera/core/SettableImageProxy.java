package androidx.camera.core;

import android.graphics.Rect;
import android.util.Size;

public final class SettableImageProxy extends ForwardingImageProxy {
    private Rect mCropRect;
    private final int mHeight;
    private final ImageInfo mImageInfo;
    private final Object mLock;
    private final int mWidth;

    SettableImageProxy(ImageProxy imageProxy, ImageInfo imageInfo) {
        this(imageProxy, (Size) null, imageInfo);
    }

    public SettableImageProxy(ImageProxy imageProxy, Size size, ImageInfo imageInfo) {
        super(imageProxy);
        this.mLock = new Object();
        if (size == null) {
            this.mWidth = super.getWidth();
            this.mHeight = super.getHeight();
        } else {
            this.mWidth = size.getWidth();
            this.mHeight = size.getHeight();
        }
        this.mImageInfo = imageInfo;
    }

    public Rect getCropRect() {
        synchronized (this.mLock) {
            if (this.mCropRect == null) {
                Rect rect = new Rect(0, 0, getWidth(), getHeight());
                return rect;
            }
            Rect rect2 = new Rect(this.mCropRect);
            return rect2;
        }
    }

    public void setCropRect(Rect rect) {
        if (rect != null) {
            Rect rect2 = new Rect(rect);
            if (!rect2.intersect(0, 0, getWidth(), getHeight())) {
                rect2.setEmpty();
            }
            rect = rect2;
        }
        synchronized (this.mLock) {
            this.mCropRect = rect;
        }
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public ImageInfo getImageInfo() {
        return this.mImageInfo;
    }
}
