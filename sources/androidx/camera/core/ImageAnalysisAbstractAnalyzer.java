package androidx.camera.core;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ImageWriter;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.camera.core.internal.compat.ImageWriterCompat;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.os.OperationCanceledException;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;

abstract class ImageAnalysisAbstractAnalyzer implements ImageReaderProxy.OnImageAvailableListener {
    private static final String TAG = "ImageAnalysisAnalyzer";
    private final Object mAnalyzerLock = new Object();
    protected boolean mIsAttached = true;
    private volatile boolean mOnePixelShiftEnabled;
    private Matrix mOriginalSensorToBufferTransformMatrix = new Matrix();
    private Rect mOriginalViewPortCropRect = new Rect();
    private volatile int mOutputImageFormat = 1;
    private volatile boolean mOutputImageRotationEnabled;
    private volatile int mPrevBufferRotationDegrees;
    private SafeCloseImageReaderProxy mProcessedImageReaderProxy;
    private ImageWriter mProcessedImageWriter;
    ByteBuffer mRGBConvertedBuffer;
    private volatile int mRelativeRotation;
    private ImageAnalysis.Analyzer mSubscribedAnalyzer;
    ByteBuffer mURotatedBuffer;
    private Matrix mUpdatedSensorToBufferTransformMatrix = new Matrix();
    private Rect mUpdatedViewPortCropRect = new Rect();
    private Executor mUserExecutor;
    ByteBuffer mVRotatedBuffer;
    ByteBuffer mYRotatedBuffer;

    /* access modifiers changed from: package-private */
    public abstract ImageProxy acquireImage(ImageReaderProxy imageReaderProxy);

    /* access modifiers changed from: package-private */
    public abstract void clearCache();

    /* access modifiers changed from: package-private */
    public abstract void onValidImageAvailable(ImageProxy imageProxy);

    ImageAnalysisAbstractAnalyzer() {
    }

    public void onImageAvailable(ImageReaderProxy imageReaderProxy) {
        try {
            ImageProxy acquireImage = acquireImage(imageReaderProxy);
            if (acquireImage != null) {
                onValidImageAvailable(acquireImage);
            }
        } catch (IllegalStateException e) {
            Logger.e(TAG, "Failed to acquire image.", e);
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0070  */
    com.google.common.util.concurrent.ListenableFuture<java.lang.Void> analyzeImage(androidx.camera.core.ImageProxy r15) {
        /*
            r14 = this;
            boolean r0 = r14.mOutputImageRotationEnabled
            r1 = 0
            if (r0 == 0) goto L_0x0008
            int r0 = r14.mRelativeRotation
            goto L_0x0009
        L_0x0008:
            r0 = r1
        L_0x0009:
            java.lang.Object r2 = r14.mAnalyzerLock
            monitor-enter(r2)
            java.util.concurrent.Executor r9 = r14.mUserExecutor     // Catch:{ all -> 0x00c0 }
            androidx.camera.core.ImageAnalysis$Analyzer r10 = r14.mSubscribedAnalyzer     // Catch:{ all -> 0x00c0 }
            boolean r3 = r14.mOutputImageRotationEnabled     // Catch:{ all -> 0x00c0 }
            r11 = 1
            if (r3 == 0) goto L_0x001b
            int r3 = r14.mPrevBufferRotationDegrees     // Catch:{ all -> 0x00c0 }
            if (r0 == r3) goto L_0x001b
            r12 = r11
            goto L_0x001c
        L_0x001b:
            r12 = r1
        L_0x001c:
            if (r12 == 0) goto L_0x0021
            r14.recreateImageReaderProxy(r15, r0)     // Catch:{ all -> 0x00c0 }
        L_0x0021:
            boolean r3 = r14.mOutputImageRotationEnabled     // Catch:{ all -> 0x00c0 }
            if (r3 == 0) goto L_0x0028
            r14.createHelperBuffer(r15)     // Catch:{ all -> 0x00c0 }
        L_0x0028:
            androidx.camera.core.SafeCloseImageReaderProxy r3 = r14.mProcessedImageReaderProxy     // Catch:{ all -> 0x00c0 }
            android.media.ImageWriter r4 = r14.mProcessedImageWriter     // Catch:{ all -> 0x00c0 }
            java.nio.ByteBuffer r5 = r14.mRGBConvertedBuffer     // Catch:{ all -> 0x00c0 }
            java.nio.ByteBuffer r6 = r14.mYRotatedBuffer     // Catch:{ all -> 0x00c0 }
            java.nio.ByteBuffer r7 = r14.mURotatedBuffer     // Catch:{ all -> 0x00c0 }
            java.nio.ByteBuffer r8 = r14.mVRotatedBuffer     // Catch:{ all -> 0x00c0 }
            monitor-exit(r2)     // Catch:{ all -> 0x00c0 }
            if (r10 == 0) goto L_0x00b4
            if (r9 == 0) goto L_0x00b4
            boolean r2 = r14.mIsAttached
            if (r2 == 0) goto L_0x00b4
            if (r3 == 0) goto L_0x0068
            int r2 = r14.mOutputImageFormat
            r13 = 2
            if (r2 != r13) goto L_0x004b
            boolean r2 = r14.mOnePixelShiftEnabled
            androidx.camera.core.ImageProxy r2 = androidx.camera.core.ImageProcessingUtil.convertYUVToRGB(r15, r3, r5, r0, r2)
            goto L_0x0069
        L_0x004b:
            int r2 = r14.mOutputImageFormat
            if (r2 != r11) goto L_0x0068
            boolean r2 = r14.mOnePixelShiftEnabled
            if (r2 == 0) goto L_0x0056
            androidx.camera.core.ImageProcessingUtil.applyPixelShiftForYUV(r15)
        L_0x0056:
            if (r4 == 0) goto L_0x0068
            if (r6 == 0) goto L_0x0068
            if (r7 == 0) goto L_0x0068
            if (r8 == 0) goto L_0x0068
            r2 = r15
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r0
            androidx.camera.core.ImageProxy r2 = androidx.camera.core.ImageProcessingUtil.rotateYUV(r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0069
        L_0x0068:
            r2 = 0
        L_0x0069:
            if (r2 != 0) goto L_0x006c
            r1 = r11
        L_0x006c:
            if (r1 == 0) goto L_0x0070
            r8 = r15
            goto L_0x0071
        L_0x0070:
            r8 = r2
        L_0x0071:
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            java.lang.Object r3 = r14.mAnalyzerLock
            monitor-enter(r3)
            if (r12 == 0) goto L_0x0095
            if (r1 != 0) goto L_0x0095
            int r1 = r15.getWidth()     // Catch:{ all -> 0x00b1 }
            int r4 = r15.getHeight()     // Catch:{ all -> 0x00b1 }
            int r5 = r8.getWidth()     // Catch:{ all -> 0x00b1 }
            int r6 = r8.getHeight()     // Catch:{ all -> 0x00b1 }
            r14.recalculateTransformMatrixAndCropRect(r1, r4, r5, r6)     // Catch:{ all -> 0x00b1 }
        L_0x0095:
            r14.mPrevBufferRotationDegrees = r0     // Catch:{ all -> 0x00b1 }
            android.graphics.Rect r0 = r14.mUpdatedViewPortCropRect     // Catch:{ all -> 0x00b1 }
            r2.set(r0)     // Catch:{ all -> 0x00b1 }
            android.graphics.Matrix r0 = r14.mUpdatedSensorToBufferTransformMatrix     // Catch:{ all -> 0x00b1 }
            r7.set(r0)     // Catch:{ all -> 0x00b1 }
            monitor-exit(r3)     // Catch:{ all -> 0x00b1 }
            androidx.camera.core.ImageAnalysisAbstractAnalyzer$$ExternalSyntheticLambda0 r0 = new androidx.camera.core.ImageAnalysisAbstractAnalyzer$$ExternalSyntheticLambda0
            r3 = r0
            r4 = r14
            r5 = r9
            r6 = r15
            r9 = r2
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            com.google.common.util.concurrent.ListenableFuture r15 = androidx.concurrent.futures.CallbackToFutureAdapter.getFuture(r0)
            goto L_0x00bf
        L_0x00b1:
            r15 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00b1 }
            throw r15
        L_0x00b4:
            androidx.core.os.OperationCanceledException r15 = new androidx.core.os.OperationCanceledException
            java.lang.String r0 = "No analyzer or executor currently set."
            r15.<init>(r0)
            com.google.common.util.concurrent.ListenableFuture r15 = androidx.camera.core.impl.utils.futures.Futures.immediateFailedFuture(r15)
        L_0x00bf:
            return r15
        L_0x00c0:
            r15 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00c0 }
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.ImageAnalysisAbstractAnalyzer.analyzeImage(androidx.camera.core.ImageProxy):com.google.common.util.concurrent.ListenableFuture");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$analyzeImage$1$androidx-camera-core-ImageAnalysisAbstractAnalyzer  reason: not valid java name */
    public /* synthetic */ Object m142lambda$analyzeImage$1$androidxcameracoreImageAnalysisAbstractAnalyzer(Executor executor, ImageProxy imageProxy, Matrix matrix, ImageProxy imageProxy2, Rect rect, ImageAnalysis.Analyzer analyzer, CallbackToFutureAdapter.Completer completer) throws Exception {
        Executor executor2 = executor;
        executor.execute(new ImageAnalysisAbstractAnalyzer$$ExternalSyntheticLambda1(this, imageProxy, matrix, imageProxy2, rect, analyzer, completer));
        return "analyzeImage";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$analyzeImage$0$androidx-camera-core-ImageAnalysisAbstractAnalyzer  reason: not valid java name */
    public /* synthetic */ void m141lambda$analyzeImage$0$androidxcameracoreImageAnalysisAbstractAnalyzer(ImageProxy imageProxy, Matrix matrix, ImageProxy imageProxy2, Rect rect, ImageAnalysis.Analyzer analyzer, CallbackToFutureAdapter.Completer completer) {
        int i;
        if (this.mIsAttached) {
            TagBundle tagBundle = imageProxy.getImageInfo().getTagBundle();
            long timestamp = imageProxy.getImageInfo().getTimestamp();
            if (this.mOutputImageRotationEnabled) {
                i = 0;
            } else {
                i = this.mRelativeRotation;
            }
            SettableImageProxy settableImageProxy = new SettableImageProxy(imageProxy2, ImmutableImageInfo.create(tagBundle, timestamp, i, matrix));
            if (!rect.isEmpty()) {
                settableImageProxy.setCropRect(rect);
            }
            analyzer.analyze(settableImageProxy);
            completer.set(null);
            return;
        }
        completer.setException(new OperationCanceledException("ImageAnalysis is detached"));
    }

    private static SafeCloseImageReaderProxy createImageReaderProxy(int i, int i2, int i3, int i4, int i5) {
        boolean z = i3 == 90 || i3 == 270;
        int i6 = z ? i2 : i;
        if (!z) {
            i = i2;
        }
        return new SafeCloseImageReaderProxy(ImageReaderProxys.createIsolatedReader(i6, i, i4, i5));
    }

    /* access modifiers changed from: package-private */
    public void setRelativeRotation(int i) {
        this.mRelativeRotation = i;
    }

    /* access modifiers changed from: package-private */
    public void setOutputImageRotationEnabled(boolean z) {
        this.mOutputImageRotationEnabled = z;
    }

    /* access modifiers changed from: package-private */
    public void setOutputImageFormat(int i) {
        this.mOutputImageFormat = i;
    }

    /* access modifiers changed from: package-private */
    public void setOnePixelShiftEnabled(boolean z) {
        this.mOnePixelShiftEnabled = z;
    }

    /* access modifiers changed from: package-private */
    public void setViewPortCropRect(Rect rect) {
        synchronized (this.mAnalyzerLock) {
            this.mOriginalViewPortCropRect = rect;
            this.mUpdatedViewPortCropRect = new Rect(this.mOriginalViewPortCropRect);
        }
    }

    /* access modifiers changed from: package-private */
    public void setSensorToBufferTransformMatrix(Matrix matrix) {
        synchronized (this.mAnalyzerLock) {
            this.mOriginalSensorToBufferTransformMatrix = matrix;
            this.mUpdatedSensorToBufferTransformMatrix = new Matrix(this.mOriginalSensorToBufferTransformMatrix);
        }
    }

    /* access modifiers changed from: package-private */
    public void setProcessedImageReaderProxy(SafeCloseImageReaderProxy safeCloseImageReaderProxy) {
        synchronized (this.mAnalyzerLock) {
            this.mProcessedImageReaderProxy = safeCloseImageReaderProxy;
        }
    }

    /* access modifiers changed from: package-private */
    public void setAnalyzer(Executor executor, ImageAnalysis.Analyzer analyzer) {
        if (analyzer == null) {
            clearCache();
        }
        synchronized (this.mAnalyzerLock) {
            this.mSubscribedAnalyzer = analyzer;
            this.mUserExecutor = executor;
        }
    }

    /* access modifiers changed from: package-private */
    public void attach() {
        this.mIsAttached = true;
    }

    /* access modifiers changed from: package-private */
    public void detach() {
        this.mIsAttached = false;
        clearCache();
    }

    private void createHelperBuffer(ImageProxy imageProxy) {
        if (this.mOutputImageFormat == 1) {
            if (this.mYRotatedBuffer == null) {
                this.mYRotatedBuffer = ByteBuffer.allocateDirect(imageProxy.getWidth() * imageProxy.getHeight());
            }
            this.mYRotatedBuffer.position(0);
            if (this.mURotatedBuffer == null) {
                this.mURotatedBuffer = ByteBuffer.allocateDirect((imageProxy.getWidth() * imageProxy.getHeight()) / 4);
            }
            this.mURotatedBuffer.position(0);
            if (this.mVRotatedBuffer == null) {
                this.mVRotatedBuffer = ByteBuffer.allocateDirect((imageProxy.getWidth() * imageProxy.getHeight()) / 4);
            }
            this.mVRotatedBuffer.position(0);
        } else if (this.mOutputImageFormat == 2 && this.mRGBConvertedBuffer == null) {
            this.mRGBConvertedBuffer = ByteBuffer.allocateDirect(imageProxy.getWidth() * imageProxy.getHeight() * 4);
        }
    }

    private void recreateImageReaderProxy(ImageProxy imageProxy, int i) {
        SafeCloseImageReaderProxy safeCloseImageReaderProxy = this.mProcessedImageReaderProxy;
        if (safeCloseImageReaderProxy != null) {
            safeCloseImageReaderProxy.safeClose();
            this.mProcessedImageReaderProxy = createImageReaderProxy(imageProxy.getWidth(), imageProxy.getHeight(), i, this.mProcessedImageReaderProxy.getImageFormat(), this.mProcessedImageReaderProxy.getMaxImages());
            if (this.mOutputImageFormat == 1) {
                ImageWriter imageWriter = this.mProcessedImageWriter;
                if (imageWriter != null) {
                    ImageWriterCompat.close(imageWriter);
                }
                this.mProcessedImageWriter = ImageWriterCompat.newInstance(this.mProcessedImageReaderProxy.getSurface(), this.mProcessedImageReaderProxy.getMaxImages());
            }
        }
    }

    private void recalculateTransformMatrixAndCropRect(int i, int i2, int i3, int i4) {
        Matrix additionalTransformMatrixAppliedByProcessor = getAdditionalTransformMatrixAppliedByProcessor(i, i2, i3, i4, this.mRelativeRotation);
        this.mUpdatedViewPortCropRect = getUpdatedCropRect(this.mOriginalViewPortCropRect, additionalTransformMatrixAppliedByProcessor);
        this.mUpdatedSensorToBufferTransformMatrix.setConcat(this.mOriginalSensorToBufferTransformMatrix, additionalTransformMatrixAppliedByProcessor);
    }

    static Rect getUpdatedCropRect(Rect rect, Matrix matrix) {
        RectF rectF = new RectF(rect);
        matrix.mapRect(rectF);
        Rect rect2 = new Rect();
        rectF.round(rect2);
        return rect2;
    }

    static Matrix getAdditionalTransformMatrixAppliedByProcessor(int i, int i2, int i3, int i4, int i5) {
        Matrix matrix = new Matrix();
        if (i5 > 0) {
            matrix.setRectToRect(new RectF(0.0f, 0.0f, (float) i, (float) i2), TransformUtils.NORMALIZED_RECT, Matrix.ScaleToFit.FILL);
            matrix.postRotate((float) i5);
            matrix.postConcat(TransformUtils.getNormalizedToBuffer(new RectF(0.0f, 0.0f, (float) i3, (float) i4)));
        }
        return matrix;
    }
}
