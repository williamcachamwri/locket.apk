package androidx.camera.core;

import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.view.Surface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.concurrent.Executor;

class AndroidImageReaderProxy implements ImageReaderProxy {
    private final ImageReader mImageReader;
    private boolean mIsImageAvailableListenerCleared = true;
    private final Object mLock = new Object();

    AndroidImageReaderProxy(ImageReader imageReader) {
        this.mImageReader = imageReader;
    }

    public ImageProxy acquireLatestImage() {
        Image image;
        synchronized (this.mLock) {
            try {
                image = this.mImageReader.acquireLatestImage();
            } catch (RuntimeException e) {
                if (isImageReaderContextNotInitializedException(e)) {
                    image = null;
                } else {
                    throw e;
                }
            }
            if (image == null) {
                return null;
            }
            AndroidImageProxy androidImageProxy = new AndroidImageProxy(image);
            return androidImageProxy;
        }
    }

    public ImageProxy acquireNextImage() {
        Image image;
        synchronized (this.mLock) {
            try {
                image = this.mImageReader.acquireNextImage();
            } catch (RuntimeException e) {
                if (isImageReaderContextNotInitializedException(e)) {
                    image = null;
                } else {
                    throw e;
                }
            }
            if (image == null) {
                return null;
            }
            AndroidImageProxy androidImageProxy = new AndroidImageProxy(image);
            return androidImageProxy;
        }
    }

    private boolean isImageReaderContextNotInitializedException(RuntimeException runtimeException) {
        return "ImageReaderContext is not initialized".equals(runtimeException.getMessage());
    }

    public void close() {
        synchronized (this.mLock) {
            this.mImageReader.close();
        }
    }

    public int getHeight() {
        int height;
        synchronized (this.mLock) {
            height = this.mImageReader.getHeight();
        }
        return height;
    }

    public int getWidth() {
        int width;
        synchronized (this.mLock) {
            width = this.mImageReader.getWidth();
        }
        return width;
    }

    public int getImageFormat() {
        int imageFormat;
        synchronized (this.mLock) {
            imageFormat = this.mImageReader.getImageFormat();
        }
        return imageFormat;
    }

    public int getMaxImages() {
        int maxImages;
        synchronized (this.mLock) {
            maxImages = this.mImageReader.getMaxImages();
        }
        return maxImages;
    }

    public Surface getSurface() {
        Surface surface;
        synchronized (this.mLock) {
            surface = this.mImageReader.getSurface();
        }
        return surface;
    }

    public void setOnImageAvailableListener(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, Executor executor) {
        synchronized (this.mLock) {
            this.mIsImageAvailableListenerCleared = false;
            this.mImageReader.setOnImageAvailableListener(new AndroidImageReaderProxy$$ExternalSyntheticLambda1(this, executor, onImageAvailableListener), MainThreadAsyncHandler.getInstance());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOnImageAvailableListener$1$androidx-camera-core-AndroidImageReaderProxy  reason: not valid java name */
    public /* synthetic */ void m134lambda$setOnImageAvailableListener$1$androidxcameracoreAndroidImageReaderProxy(Executor executor, ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, ImageReader imageReader) {
        synchronized (this.mLock) {
            if (!this.mIsImageAvailableListenerCleared) {
                executor.execute(new AndroidImageReaderProxy$$ExternalSyntheticLambda0(this, onImageAvailableListener));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOnImageAvailableListener$0$androidx-camera-core-AndroidImageReaderProxy  reason: not valid java name */
    public /* synthetic */ void m133lambda$setOnImageAvailableListener$0$androidxcameracoreAndroidImageReaderProxy(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
        onImageAvailableListener.onImageAvailable(this);
    }

    public void clearOnImageAvailableListener() {
        synchronized (this.mLock) {
            this.mIsImageAvailableListenerCleared = true;
            this.mImageReader.setOnImageAvailableListener((ImageReader.OnImageAvailableListener) null, (Handler) null);
        }
    }
}
