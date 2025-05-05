package androidx.camera.core;

import android.media.ImageReader;
import android.util.LongSparseArray;
import android.view.Surface;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.internal.CameraCaptureResultImageInfo;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MetadataImageReader implements ImageReaderProxy, ForwardingImageProxy.OnImageCloseListener {
    private static final String TAG = "MetadataImageReader";
    private final List<ImageProxy> mAcquiredImageProxies;
    private CameraCaptureCallback mCameraCaptureCallback;
    private boolean mClosed;
    private Executor mExecutor;
    private int mImageProxiesIndex;
    private final ImageReaderProxy mImageReaderProxy;
    ImageReaderProxy.OnImageAvailableListener mListener;
    private final Object mLock;
    private final List<ImageProxy> mMatchedImageProxies;
    private final LongSparseArray<ImageInfo> mPendingImageInfos;
    private final LongSparseArray<ImageProxy> mPendingImages;
    private ImageReaderProxy.OnImageAvailableListener mTransformedListener;
    private int mUnAcquiredAvailableImageCount;

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$androidx-camera-core-MetadataImageReader  reason: not valid java name */
    public /* synthetic */ void m149lambda$new$0$androidxcameracoreMetadataImageReader(ImageReaderProxy imageReaderProxy) {
        synchronized (this.mLock) {
            this.mUnAcquiredAvailableImageCount++;
        }
        imageIncoming(imageReaderProxy);
    }

    public MetadataImageReader(int i, int i2, int i3, int i4) {
        this(createImageReaderProxy(i, i2, i3, i4));
    }

    private static ImageReaderProxy createImageReaderProxy(int i, int i2, int i3, int i4) {
        return new AndroidImageReaderProxy(ImageReader.newInstance(i, i2, i3, i4));
    }

    MetadataImageReader(ImageReaderProxy imageReaderProxy) {
        this.mLock = new Object();
        this.mCameraCaptureCallback = new CameraCaptureCallback() {
            public void onCaptureCompleted(int i, CameraCaptureResult cameraCaptureResult) {
                super.onCaptureCompleted(i, cameraCaptureResult);
                MetadataImageReader.this.resultIncoming(cameraCaptureResult);
            }
        };
        this.mUnAcquiredAvailableImageCount = 0;
        this.mTransformedListener = new MetadataImageReader$$ExternalSyntheticLambda1(this);
        this.mClosed = false;
        this.mPendingImageInfos = new LongSparseArray<>();
        this.mPendingImages = new LongSparseArray<>();
        this.mAcquiredImageProxies = new ArrayList();
        this.mImageReaderProxy = imageReaderProxy;
        this.mImageProxiesIndex = 0;
        this.mMatchedImageProxies = new ArrayList(getMaxImages());
    }

    public ImageProxy acquireLatestImage() {
        synchronized (this.mLock) {
            if (this.mMatchedImageProxies.isEmpty()) {
                return null;
            }
            if (this.mImageProxiesIndex < this.mMatchedImageProxies.size()) {
                ArrayList<ImageProxy> arrayList = new ArrayList<>();
                for (int i = 0; i < this.mMatchedImageProxies.size() - 1; i++) {
                    if (!this.mAcquiredImageProxies.contains(this.mMatchedImageProxies.get(i))) {
                        arrayList.add(this.mMatchedImageProxies.get(i));
                    }
                }
                for (ImageProxy close : arrayList) {
                    close.close();
                }
                int size = this.mMatchedImageProxies.size() - 1;
                List<ImageProxy> list = this.mMatchedImageProxies;
                this.mImageProxiesIndex = size + 1;
                ImageProxy imageProxy = list.get(size);
                this.mAcquiredImageProxies.add(imageProxy);
                return imageProxy;
            }
            throw new IllegalStateException("Maximum image number reached.");
        }
    }

    public ImageProxy acquireNextImage() {
        synchronized (this.mLock) {
            if (this.mMatchedImageProxies.isEmpty()) {
                return null;
            }
            if (this.mImageProxiesIndex < this.mMatchedImageProxies.size()) {
                List<ImageProxy> list = this.mMatchedImageProxies;
                int i = this.mImageProxiesIndex;
                this.mImageProxiesIndex = i + 1;
                ImageProxy imageProxy = list.get(i);
                this.mAcquiredImageProxies.add(imageProxy);
                return imageProxy;
            }
            throw new IllegalStateException("Maximum image number reached.");
        }
    }

    public void close() {
        synchronized (this.mLock) {
            if (!this.mClosed) {
                for (ImageProxy close : new ArrayList(this.mMatchedImageProxies)) {
                    close.close();
                }
                this.mMatchedImageProxies.clear();
                this.mImageReaderProxy.close();
                this.mClosed = true;
            }
        }
    }

    public int getHeight() {
        int height;
        synchronized (this.mLock) {
            height = this.mImageReaderProxy.getHeight();
        }
        return height;
    }

    public int getWidth() {
        int width;
        synchronized (this.mLock) {
            width = this.mImageReaderProxy.getWidth();
        }
        return width;
    }

    public int getImageFormat() {
        int imageFormat;
        synchronized (this.mLock) {
            imageFormat = this.mImageReaderProxy.getImageFormat();
        }
        return imageFormat;
    }

    public int getMaxImages() {
        int maxImages;
        synchronized (this.mLock) {
            maxImages = this.mImageReaderProxy.getMaxImages();
        }
        return maxImages;
    }

    public Surface getSurface() {
        Surface surface;
        synchronized (this.mLock) {
            surface = this.mImageReaderProxy.getSurface();
        }
        return surface;
    }

    public void setOnImageAvailableListener(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener, Executor executor) {
        synchronized (this.mLock) {
            this.mListener = (ImageReaderProxy.OnImageAvailableListener) Preconditions.checkNotNull(onImageAvailableListener);
            this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
            this.mImageReaderProxy.setOnImageAvailableListener(this.mTransformedListener, executor);
        }
    }

    public void clearOnImageAvailableListener() {
        synchronized (this.mLock) {
            this.mImageReaderProxy.clearOnImageAvailableListener();
            this.mListener = null;
            this.mExecutor = null;
            this.mUnAcquiredAvailableImageCount = 0;
        }
    }

    public void onImageClose(ImageProxy imageProxy) {
        synchronized (this.mLock) {
            dequeImageProxy(imageProxy);
        }
    }

    private void enqueueImageProxy(SettableImageProxy settableImageProxy) {
        ImageReaderProxy.OnImageAvailableListener onImageAvailableListener;
        Executor executor;
        synchronized (this.mLock) {
            if (this.mMatchedImageProxies.size() < getMaxImages()) {
                settableImageProxy.addOnImageCloseListener(this);
                this.mMatchedImageProxies.add(settableImageProxy);
                onImageAvailableListener = this.mListener;
                executor = this.mExecutor;
            } else {
                Logger.d("TAG", "Maximum image number reached.");
                settableImageProxy.close();
                onImageAvailableListener = null;
                executor = null;
            }
        }
        if (onImageAvailableListener == null) {
            return;
        }
        if (executor != null) {
            executor.execute(new MetadataImageReader$$ExternalSyntheticLambda0(this, onImageAvailableListener));
        } else {
            onImageAvailableListener.onImageAvailable(this);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$enqueueImageProxy$1$androidx-camera-core-MetadataImageReader  reason: not valid java name */
    public /* synthetic */ void m148lambda$enqueueImageProxy$1$androidxcameracoreMetadataImageReader(ImageReaderProxy.OnImageAvailableListener onImageAvailableListener) {
        onImageAvailableListener.onImageAvailable(this);
    }

    private void dequeImageProxy(ImageProxy imageProxy) {
        synchronized (this.mLock) {
            int indexOf = this.mMatchedImageProxies.indexOf(imageProxy);
            if (indexOf >= 0) {
                this.mMatchedImageProxies.remove(indexOf);
                int i = this.mImageProxiesIndex;
                if (indexOf <= i) {
                    this.mImageProxiesIndex = i - 1;
                }
            }
            this.mAcquiredImageProxies.remove(imageProxy);
            if (this.mUnAcquiredAvailableImageCount > 0) {
                imageIncoming(this.mImageReaderProxy);
            }
        }
    }

    public CameraCaptureCallback getCameraCaptureCallback() {
        return this.mCameraCaptureCallback;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void imageIncoming(androidx.camera.core.impl.ImageReaderProxy r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            boolean r1 = r6.mClosed     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0009:
            android.util.LongSparseArray<androidx.camera.core.ImageProxy> r1 = r6.mPendingImages     // Catch:{ all -> 0x005e }
            int r1 = r1.size()     // Catch:{ all -> 0x005e }
            java.util.List<androidx.camera.core.ImageProxy> r2 = r6.mMatchedImageProxies     // Catch:{ all -> 0x005e }
            int r2 = r2.size()     // Catch:{ all -> 0x005e }
            int r1 = r1 + r2
            int r2 = r7.getMaxImages()     // Catch:{ all -> 0x005e }
            if (r1 < r2) goto L_0x0025
            java.lang.String r7 = "MetadataImageReader"
            java.lang.String r1 = "Skip to acquire the next image because the acquired image count has reached the max images count."
            androidx.camera.core.Logger.d(r7, r1)     // Catch:{ all -> 0x005e }
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0025:
            androidx.camera.core.ImageProxy r2 = r7.acquireNextImage()     // Catch:{ IllegalStateException -> 0x0046 }
            if (r2 == 0) goto L_0x004f
            int r3 = r6.mUnAcquiredAvailableImageCount     // Catch:{ all -> 0x005e }
            int r3 = r3 + -1
            r6.mUnAcquiredAvailableImageCount = r3     // Catch:{ all -> 0x005e }
            int r1 = r1 + 1
            android.util.LongSparseArray<androidx.camera.core.ImageProxy> r3 = r6.mPendingImages     // Catch:{ all -> 0x005e }
            androidx.camera.core.ImageInfo r4 = r2.getImageInfo()     // Catch:{ all -> 0x005e }
            long r4 = r4.getTimestamp()     // Catch:{ all -> 0x005e }
            r3.put(r4, r2)     // Catch:{ all -> 0x005e }
            r6.matchImages()     // Catch:{ all -> 0x005e }
            goto L_0x004f
        L_0x0044:
            r7 = move-exception
            goto L_0x005d
        L_0x0046:
            r2 = move-exception
            java.lang.String r3 = "MetadataImageReader"
            java.lang.String r4 = "Failed to acquire next image."
            androidx.camera.core.Logger.d(r3, r4, r2)     // Catch:{ all -> 0x0044 }
            r2 = 0
        L_0x004f:
            if (r2 == 0) goto L_0x005b
            int r2 = r6.mUnAcquiredAvailableImageCount     // Catch:{ all -> 0x005e }
            if (r2 <= 0) goto L_0x005b
            int r2 = r7.getMaxImages()     // Catch:{ all -> 0x005e }
            if (r1 < r2) goto L_0x0025
        L_0x005b:
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x005d:
            throw r7     // Catch:{ all -> 0x005e }
        L_0x005e:
            r7 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.MetadataImageReader.imageIncoming(androidx.camera.core.impl.ImageReaderProxy):void");
    }

    /* access modifiers changed from: package-private */
    public void resultIncoming(CameraCaptureResult cameraCaptureResult) {
        synchronized (this.mLock) {
            if (!this.mClosed) {
                this.mPendingImageInfos.put(cameraCaptureResult.getTimestamp(), new CameraCaptureResultImageInfo(cameraCaptureResult));
                matchImages();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void removeStaleData() {
        /*
            r10 = this;
            java.lang.Object r0 = r10.mLock
            monitor-enter(r0)
            android.util.LongSparseArray<androidx.camera.core.ImageProxy> r1 = r10.mPendingImages     // Catch:{ all -> 0x008e }
            int r1 = r1.size()     // Catch:{ all -> 0x008e }
            if (r1 == 0) goto L_0x008c
            android.util.LongSparseArray<androidx.camera.core.ImageInfo> r1 = r10.mPendingImageInfos     // Catch:{ all -> 0x008e }
            int r1 = r1.size()     // Catch:{ all -> 0x008e }
            if (r1 != 0) goto L_0x0015
            goto L_0x008c
        L_0x0015:
            android.util.LongSparseArray<androidx.camera.core.ImageProxy> r1 = r10.mPendingImages     // Catch:{ all -> 0x008e }
            r2 = 0
            long r3 = r1.keyAt(r2)     // Catch:{ all -> 0x008e }
            java.lang.Long r1 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x008e }
            android.util.LongSparseArray<androidx.camera.core.ImageInfo> r3 = r10.mPendingImageInfos     // Catch:{ all -> 0x008e }
            long r3 = r3.keyAt(r2)     // Catch:{ all -> 0x008e }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x008e }
            boolean r4 = r3.equals(r1)     // Catch:{ all -> 0x008e }
            r5 = 1
            if (r4 != 0) goto L_0x0032
            r2 = r5
        L_0x0032:
            androidx.core.util.Preconditions.checkArgument(r2)     // Catch:{ all -> 0x008e }
            long r6 = r3.longValue()     // Catch:{ all -> 0x008e }
            long r8 = r1.longValue()     // Catch:{ all -> 0x008e }
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x006b
            android.util.LongSparseArray<androidx.camera.core.ImageProxy> r1 = r10.mPendingImages     // Catch:{ all -> 0x008e }
            int r1 = r1.size()     // Catch:{ all -> 0x008e }
            int r1 = r1 - r5
        L_0x0048:
            if (r1 < 0) goto L_0x008a
            android.util.LongSparseArray<androidx.camera.core.ImageProxy> r2 = r10.mPendingImages     // Catch:{ all -> 0x008e }
            long r4 = r2.keyAt(r1)     // Catch:{ all -> 0x008e }
            long r6 = r3.longValue()     // Catch:{ all -> 0x008e }
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L_0x0068
            android.util.LongSparseArray<androidx.camera.core.ImageProxy> r2 = r10.mPendingImages     // Catch:{ all -> 0x008e }
            java.lang.Object r2 = r2.valueAt(r1)     // Catch:{ all -> 0x008e }
            androidx.camera.core.ImageProxy r2 = (androidx.camera.core.ImageProxy) r2     // Catch:{ all -> 0x008e }
            r2.close()     // Catch:{ all -> 0x008e }
            android.util.LongSparseArray<androidx.camera.core.ImageProxy> r2 = r10.mPendingImages     // Catch:{ all -> 0x008e }
            r2.removeAt(r1)     // Catch:{ all -> 0x008e }
        L_0x0068:
            int r1 = r1 + -1
            goto L_0x0048
        L_0x006b:
            android.util.LongSparseArray<androidx.camera.core.ImageInfo> r2 = r10.mPendingImageInfos     // Catch:{ all -> 0x008e }
            int r2 = r2.size()     // Catch:{ all -> 0x008e }
            int r2 = r2 - r5
        L_0x0072:
            if (r2 < 0) goto L_0x008a
            android.util.LongSparseArray<androidx.camera.core.ImageInfo> r3 = r10.mPendingImageInfos     // Catch:{ all -> 0x008e }
            long r3 = r3.keyAt(r2)     // Catch:{ all -> 0x008e }
            long r5 = r1.longValue()     // Catch:{ all -> 0x008e }
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 >= 0) goto L_0x0087
            android.util.LongSparseArray<androidx.camera.core.ImageInfo> r3 = r10.mPendingImageInfos     // Catch:{ all -> 0x008e }
            r3.removeAt(r2)     // Catch:{ all -> 0x008e }
        L_0x0087:
            int r2 = r2 + -1
            goto L_0x0072
        L_0x008a:
            monitor-exit(r0)     // Catch:{ all -> 0x008e }
            return
        L_0x008c:
            monitor-exit(r0)     // Catch:{ all -> 0x008e }
            return
        L_0x008e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.MetadataImageReader.removeStaleData():void");
    }

    private void matchImages() {
        synchronized (this.mLock) {
            for (int size = this.mPendingImageInfos.size() - 1; size >= 0; size--) {
                ImageInfo valueAt = this.mPendingImageInfos.valueAt(size);
                long timestamp = valueAt.getTimestamp();
                ImageProxy imageProxy = this.mPendingImages.get(timestamp);
                if (imageProxy != null) {
                    this.mPendingImages.remove(timestamp);
                    this.mPendingImageInfos.removeAt(size);
                    enqueueImageProxy(new SettableImageProxy(imageProxy, valueAt));
                }
            }
            removeStaleData();
        }
    }
}
