package androidx.camera.core;

import android.graphics.Rect;
import android.media.Image;
import androidx.camera.core.ImageProxy;
import java.util.HashSet;
import java.util.Set;

public abstract class ForwardingImageProxy implements ImageProxy {
    protected final ImageProxy mImage;
    private final Object mLock = new Object();
    private final Set<OnImageCloseListener> mOnImageCloseListeners = new HashSet();

    public interface OnImageCloseListener {
        void onImageClose(ImageProxy imageProxy);
    }

    protected ForwardingImageProxy(ImageProxy imageProxy) {
        this.mImage = imageProxy;
    }

    public void close() {
        this.mImage.close();
        notifyOnImageCloseListeners();
    }

    public Rect getCropRect() {
        return this.mImage.getCropRect();
    }

    public void setCropRect(Rect rect) {
        this.mImage.setCropRect(rect);
    }

    public int getFormat() {
        return this.mImage.getFormat();
    }

    public int getHeight() {
        return this.mImage.getHeight();
    }

    public int getWidth() {
        return this.mImage.getWidth();
    }

    public ImageProxy.PlaneProxy[] getPlanes() {
        return this.mImage.getPlanes();
    }

    public ImageInfo getImageInfo() {
        return this.mImage.getImageInfo();
    }

    public Image getImage() {
        return this.mImage.getImage();
    }

    public void addOnImageCloseListener(OnImageCloseListener onImageCloseListener) {
        synchronized (this.mLock) {
            this.mOnImageCloseListeners.add(onImageCloseListener);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyOnImageCloseListeners() {
        HashSet<OnImageCloseListener> hashSet;
        synchronized (this.mLock) {
            hashSet = new HashSet<>(this.mOnImageCloseListeners);
        }
        for (OnImageCloseListener onImageClose : hashSet) {
            onImageClose.onImageClose(this);
        }
    }
}
