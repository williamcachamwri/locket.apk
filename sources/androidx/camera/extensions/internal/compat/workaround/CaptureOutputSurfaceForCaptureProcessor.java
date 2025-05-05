package androidx.camera.extensions.internal.compat.workaround;

import android.media.Image;
import android.media.ImageWriter;
import android.os.Build;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.ImageReaderProxys;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.extensions.internal.compat.quirk.CaptureOutputSurfaceOccupiedQuirk;
import androidx.camera.extensions.internal.compat.quirk.DeviceQuirks;

public class CaptureOutputSurfaceForCaptureProcessor {
    private static final int MAX_IMAGES = 2;
    private static final String TAG = "CaptureOutputSurface";
    private static final long UNSPECIFIED_TIMESTAMP = -1;
    private final ImageWriter mImageWriter;
    private final ImageReaderProxy mIntermediateImageReader;
    private boolean mIsClosed;
    private final Object mLock = new Object();
    private final boolean mNeedIntermediaSurface;
    private final boolean mNeedOverrideTimestamp;
    long mOutputImageTimeStamp;
    private final Surface mOutputSurface;

    public CaptureOutputSurfaceForCaptureProcessor(Surface surface, Size size, boolean z) {
        boolean z2 = false;
        this.mIsClosed = false;
        this.mOutputImageTimeStamp = -1;
        this.mNeedOverrideTimestamp = z;
        z2 = (DeviceQuirks.get(CaptureOutputSurfaceOccupiedQuirk.class) != null || z) ? true : z2;
        this.mNeedIntermediaSurface = z2;
        if (Build.VERSION.SDK_INT < 29 || !z2) {
            this.mOutputSurface = surface;
            this.mIntermediateImageReader = null;
            this.mImageWriter = null;
            return;
        }
        Logger.d(TAG, "Enabling intermediate surface");
        ImageReaderProxy createIsolatedReader = ImageReaderProxys.createIsolatedReader(size.getWidth(), size.getHeight(), 35, 2);
        this.mIntermediateImageReader = createIsolatedReader;
        this.mOutputSurface = createIsolatedReader.getSurface();
        this.mImageWriter = ImageWriterCompat.newInstance(surface, 2, 35);
        createIsolatedReader.setOnImageAvailableListener(new CaptureOutputSurfaceForCaptureProcessor$$ExternalSyntheticLambda0(this), CameraXExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        return;
     */
    /* renamed from: lambda$new$0$androidx-camera-extensions-internal-compat-workaround-CaptureOutputSurfaceForCaptureProcessor  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void m229lambda$new$0$androidxcameraextensionsinternalcompatworkaroundCaptureOutputSurfaceForCaptureProcessor(androidx.camera.core.impl.ImageReaderProxy r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            boolean r1 = r5.mIsClosed     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x0009:
            androidx.camera.core.ImageProxy r6 = r6.acquireNextImage()     // Catch:{ all -> 0x002b }
            if (r6 == 0) goto L_0x0029
            android.media.Image r6 = r6.getImage()     // Catch:{ all -> 0x002b }
            if (r6 == 0) goto L_0x0029
            boolean r1 = r5.mNeedOverrideTimestamp     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x0024
            long r1 = r5.mOutputImageTimeStamp     // Catch:{ all -> 0x002b }
            r3 = -1
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x0024
            androidx.camera.extensions.internal.compat.workaround.CaptureOutputSurfaceForCaptureProcessor.Api23Impl.setImageTimestamp(r6, r1)     // Catch:{ all -> 0x002b }
        L_0x0024:
            android.media.ImageWriter r1 = r5.mImageWriter     // Catch:{ all -> 0x002b }
            androidx.camera.extensions.internal.compat.workaround.CaptureOutputSurfaceForCaptureProcessor.ImageWriterCompat.queueInputImage(r1, r6)     // Catch:{ all -> 0x002b }
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.extensions.internal.compat.workaround.CaptureOutputSurfaceForCaptureProcessor.m229lambda$new$0$androidxcameraextensionsinternalcompatworkaroundCaptureOutputSurfaceForCaptureProcessor(androidx.camera.core.impl.ImageReaderProxy):void");
    }

    public void setOutputImageTimestamp(long j) {
        if (this.mNeedOverrideTimestamp) {
            this.mOutputImageTimeStamp = j;
        }
    }

    public Surface getSurface() {
        return this.mOutputSurface;
    }

    public void close() {
        synchronized (this.mLock) {
            this.mIsClosed = true;
            if (Build.VERSION.SDK_INT >= 29 && this.mNeedIntermediaSurface) {
                this.mIntermediateImageReader.clearOnImageAvailableListener();
                this.mIntermediateImageReader.close();
                ImageWriterCompat.close(this.mImageWriter);
            }
        }
    }

    static final class ImageWriterCompat {
        private ImageWriterCompat() {
        }

        static ImageWriter newInstance(Surface surface, int i, int i2) {
            return ImageWriter.newInstance(surface, i, i2);
        }

        static void queueInputImage(ImageWriter imageWriter, Image image) {
            imageWriter.queueInputImage(image);
        }

        static void close(ImageWriter imageWriter) {
            imageWriter.close();
        }
    }

    static final class Api23Impl {
        private Api23Impl() {
        }

        static void setImageTimestamp(Image image, long j) {
            image.setTimestamp(j);
        }
    }
}
