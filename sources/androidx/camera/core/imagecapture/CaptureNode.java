package androidx.camera.core.imagecapture;

import android.util.Size;
import android.view.Surface;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.ImageReaderProxyProvider;
import androidx.camera.core.ImageReaderProxys;
import androidx.camera.core.Logger;
import androidx.camera.core.SafeCloseImageReaderProxy;
import androidx.camera.core.imagecapture.ProcessingNode;
import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.processing.Edge;
import androidx.camera.core.processing.Node;
import androidx.core.util.Preconditions;
import java.util.List;
import java.util.Objects;

class CaptureNode implements Node<In, ProcessingNode.In> {
    static final int MAX_IMAGES = 4;
    private static final String TAG = "CaptureNode";
    ProcessingRequest mCurrentRequest = null;
    private In mInputEdge;
    /* access modifiers changed from: private */
    public NoMetadataImageReader mNoMetadataImageReader = null;
    private ProcessingNode.In mOutputEdge;
    SafeCloseImageReaderProxy mSafeCloseImageReaderForPostview;
    SafeCloseImageReaderProxy mSafeCloseImageReaderProxy;
    SafeCloseImageReaderProxy mSecondarySafeCloseImageReaderProxy;

    CaptureNode() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: androidx.camera.core.MetadataImageReader} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.camera.core.imagecapture.ProcessingNode.In transform(androidx.camera.core.imagecapture.CaptureNode.In r13) {
        /*
            r12 = this;
            androidx.camera.core.imagecapture.CaptureNode$In r0 = r12.mInputEdge
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x000c
            androidx.camera.core.SafeCloseImageReaderProxy r0 = r12.mSafeCloseImageReaderProxy
            if (r0 != 0) goto L_0x000c
            r0 = r2
            goto L_0x000d
        L_0x000c:
            r0 = r1
        L_0x000d:
            java.lang.String r3 = "CaptureNode does not support recreation yet."
            androidx.core.util.Preconditions.checkState(r0, r3)
            r12.mInputEdge = r13
            android.util.Size r0 = r13.getSize()
            int r3 = r13.getInputFormat()
            boolean r4 = r13.isVirtualCamera()
            r4 = r4 ^ r2
            androidx.camera.core.imagecapture.CaptureNode$1 r5 = new androidx.camera.core.imagecapture.CaptureNode$1
            r5.<init>()
            java.util.List r6 = r13.getOutputFormats()
            int r6 = r6.size()
            if (r6 <= r2) goto L_0x0032
            r6 = r2
            goto L_0x0033
        L_0x0032:
            r6 = r1
        L_0x0033:
            r7 = 0
            if (r4 == 0) goto L_0x00a1
            androidx.camera.core.ImageReaderProxyProvider r4 = r13.getImageReaderProxyProvider()
            if (r4 != 0) goto L_0x00a1
            r4 = 2
            r8 = 4
            if (r6 == 0) goto L_0x007d
            androidx.camera.core.MetadataImageReader r3 = new androidx.camera.core.MetadataImageReader
            int r7 = r0.getWidth()
            int r9 = r0.getHeight()
            r10 = 256(0x100, float:3.59E-43)
            r3.<init>(r7, r9, r10, r8)
            androidx.camera.core.impl.CameraCaptureCallback[] r7 = new androidx.camera.core.impl.CameraCaptureCallback[r4]
            r7[r1] = r5
            androidx.camera.core.impl.CameraCaptureCallback r9 = r3.getCameraCaptureCallback()
            r7[r2] = r9
            androidx.camera.core.impl.CameraCaptureCallback r7 = androidx.camera.core.impl.CameraCaptureCallbacks.createComboCallback((androidx.camera.core.impl.CameraCaptureCallback[]) r7)
            androidx.camera.core.MetadataImageReader r9 = new androidx.camera.core.MetadataImageReader
            int r10 = r0.getWidth()
            int r0 = r0.getHeight()
            r11 = 32
            r9.<init>(r10, r0, r11, r8)
            androidx.camera.core.impl.CameraCaptureCallback[] r0 = new androidx.camera.core.impl.CameraCaptureCallback[r4]
            r0[r1] = r5
            androidx.camera.core.impl.CameraCaptureCallback r1 = r9.getCameraCaptureCallback()
            r0[r2] = r1
            androidx.camera.core.impl.CameraCaptureCallback r0 = androidx.camera.core.impl.CameraCaptureCallbacks.createComboCallback((androidx.camera.core.impl.CameraCaptureCallback[]) r0)
            r5 = r7
            r7 = r0
            goto L_0x009b
        L_0x007d:
            androidx.camera.core.MetadataImageReader r9 = new androidx.camera.core.MetadataImageReader
            int r10 = r0.getWidth()
            int r0 = r0.getHeight()
            r9.<init>(r10, r0, r3, r8)
            androidx.camera.core.impl.CameraCaptureCallback[] r0 = new androidx.camera.core.impl.CameraCaptureCallback[r4]
            r0[r1] = r5
            androidx.camera.core.impl.CameraCaptureCallback r1 = r9.getCameraCaptureCallback()
            r0[r2] = r1
            androidx.camera.core.impl.CameraCaptureCallback r0 = androidx.camera.core.impl.CameraCaptureCallbacks.createComboCallback((androidx.camera.core.impl.CameraCaptureCallback[]) r0)
            r5 = r0
            r3 = r9
            r9 = r7
        L_0x009b:
            androidx.camera.core.imagecapture.CaptureNode$$ExternalSyntheticLambda0 r0 = new androidx.camera.core.imagecapture.CaptureNode$$ExternalSyntheticLambda0
            r0.<init>(r12)
            goto L_0x00bf
        L_0x00a1:
            androidx.camera.core.imagecapture.NoMetadataImageReader r1 = new androidx.camera.core.imagecapture.NoMetadataImageReader
            androidx.camera.core.ImageReaderProxyProvider r2 = r13.getImageReaderProxyProvider()
            int r4 = r0.getWidth()
            int r0 = r0.getHeight()
            androidx.camera.core.impl.ImageReaderProxy r0 = createImageReaderProxy(r2, r4, r0, r3)
            r1.<init>(r0)
            r12.mNoMetadataImageReader = r1
            androidx.camera.core.imagecapture.CaptureNode$$ExternalSyntheticLambda1 r0 = new androidx.camera.core.imagecapture.CaptureNode$$ExternalSyntheticLambda1
            r0.<init>(r12)
            r3 = r1
            r9 = r7
        L_0x00bf:
            r13.setCameraCaptureCallback(r5)
            if (r6 == 0) goto L_0x00c9
            if (r7 == 0) goto L_0x00c9
            r13.setSecondaryCameraCaptureCallback(r7)
        L_0x00c9:
            android.view.Surface r1 = r3.getSurface()
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)
            android.view.Surface r1 = (android.view.Surface) r1
            r13.setSurface(r1)
            androidx.camera.core.SafeCloseImageReaderProxy r1 = new androidx.camera.core.SafeCloseImageReaderProxy
            r1.<init>(r3)
            r12.mSafeCloseImageReaderProxy = r1
            r12.setOnImageAvailableListener(r3)
            android.util.Size r1 = r13.getPostviewSize()
            if (r1 == 0) goto L_0x0124
            androidx.camera.core.ImageReaderProxyProvider r1 = r13.getImageReaderProxyProvider()
            android.util.Size r2 = r13.getPostviewSize()
            int r2 = r2.getWidth()
            android.util.Size r3 = r13.getPostviewSize()
            int r3 = r3.getHeight()
            int r4 = r13.getPostviewImageFormat()
            androidx.camera.core.impl.ImageReaderProxy r1 = createImageReaderProxy(r1, r2, r3, r4)
            androidx.camera.core.imagecapture.CaptureNode$$ExternalSyntheticLambda2 r2 = new androidx.camera.core.imagecapture.CaptureNode$$ExternalSyntheticLambda2
            r2.<init>(r12)
            java.util.concurrent.ScheduledExecutorService r3 = androidx.camera.core.impl.utils.executor.CameraXExecutors.mainThreadExecutor()
            r1.setOnImageAvailableListener(r2, r3)
            androidx.camera.core.SafeCloseImageReaderProxy r2 = new androidx.camera.core.SafeCloseImageReaderProxy
            r2.<init>(r1)
            r12.mSafeCloseImageReaderForPostview = r2
            android.view.Surface r1 = r1.getSurface()
            android.util.Size r2 = r13.getPostviewSize()
            int r3 = r13.getPostviewImageFormat()
            r13.setPostviewSurface(r1, r2, r3)
        L_0x0124:
            if (r6 == 0) goto L_0x0139
            if (r9 == 0) goto L_0x0139
            android.view.Surface r1 = r9.getSurface()
            r13.setSecondarySurface(r1)
            androidx.camera.core.SafeCloseImageReaderProxy r1 = new androidx.camera.core.SafeCloseImageReaderProxy
            r1.<init>(r9)
            r12.mSecondarySafeCloseImageReaderProxy = r1
            r12.setOnImageAvailableListener(r9)
        L_0x0139:
            androidx.camera.core.processing.Edge r1 = r13.getRequestEdge()
            r1.setListener(r0)
            androidx.camera.core.processing.Edge r0 = r13.getErrorEdge()
            androidx.camera.core.imagecapture.CaptureNode$$ExternalSyntheticLambda3 r1 = new androidx.camera.core.imagecapture.CaptureNode$$ExternalSyntheticLambda3
            r1.<init>(r12)
            r0.setListener(r1)
            int r0 = r13.getInputFormat()
            java.util.List r13 = r13.getOutputFormats()
            androidx.camera.core.imagecapture.ProcessingNode$In r13 = androidx.camera.core.imagecapture.ProcessingNode.In.of(r0, r13)
            r12.mOutputEdge = r13
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.imagecapture.CaptureNode.transform(androidx.camera.core.imagecapture.CaptureNode$In):androidx.camera.core.imagecapture.ProcessingNode$In");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$transform$0$androidx-camera-core-imagecapture-CaptureNode  reason: not valid java name */
    public /* synthetic */ void m157lambda$transform$0$androidxcameracoreimagecaptureCaptureNode(ProcessingRequest processingRequest) {
        onRequestAvailable(processingRequest);
        this.mNoMetadataImageReader.acceptProcessingRequest(processingRequest);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$transform$1$androidx-camera-core-imagecapture-CaptureNode  reason: not valid java name */
    public /* synthetic */ void m158lambda$transform$1$androidxcameracoreimagecaptureCaptureNode(ImageReaderProxy imageReaderProxy) {
        try {
            ImageProxy acquireLatestImage = imageReaderProxy.acquireLatestImage();
            if (acquireLatestImage != null) {
                propagatePostviewImage(acquireLatestImage);
            }
        } catch (IllegalStateException e) {
            Logger.e(TAG, "Failed to acquire latest image of postview", e);
        }
    }

    private static ImageReaderProxy createImageReaderProxy(ImageReaderProxyProvider imageReaderProxyProvider, int i, int i2, int i3) {
        if (imageReaderProxyProvider != null) {
            return imageReaderProxyProvider.newInstance(i, i2, i3, 4, 0);
        }
        return ImageReaderProxys.createIsolatedReader(i, i2, i3, 4);
    }

    private void setOnImageAvailableListener(ImageReaderProxy imageReaderProxy) {
        imageReaderProxy.setOnImageAvailableListener(new CaptureNode$$ExternalSyntheticLambda4(this), CameraXExecutors.mainThreadExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setOnImageAvailableListener$2$androidx-camera-core-imagecapture-CaptureNode  reason: not valid java name */
    public /* synthetic */ void m156lambda$setOnImageAvailableListener$2$androidxcameracoreimagecaptureCaptureNode(ImageReaderProxy imageReaderProxy) {
        try {
            ImageProxy acquireLatestImage = imageReaderProxy.acquireLatestImage();
            if (acquireLatestImage != null) {
                onImageProxyAvailable(acquireLatestImage);
                return;
            }
            ProcessingRequest processingRequest = this.mCurrentRequest;
            if (processingRequest != null) {
                sendCaptureError(TakePictureManager.CaptureError.of(processingRequest.getRequestId(), new ImageCaptureException(2, "Failed to acquire latest image", (Throwable) null)));
            }
        } catch (IllegalStateException e) {
            ProcessingRequest processingRequest2 = this.mCurrentRequest;
            if (processingRequest2 != null) {
                sendCaptureError(TakePictureManager.CaptureError.of(processingRequest2.getRequestId(), new ImageCaptureException(2, "Failed to acquire latest image", e)));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onImageProxyAvailable(ImageProxy imageProxy) {
        Threads.checkMainThread();
        if (this.mCurrentRequest == null) {
            Logger.w(TAG, "Discarding ImageProxy which was inadvertently acquired: " + imageProxy);
            imageProxy.close();
        } else if (((Integer) imageProxy.getImageInfo().getTagBundle().getTag(this.mCurrentRequest.getTagBundleKey())) == null) {
            Logger.w(TAG, "Discarding ImageProxy which was acquired for aborted request");
            imageProxy.close();
        } else {
            matchAndPropagateImage(imageProxy);
        }
    }

    private void matchAndPropagateImage(ImageProxy imageProxy) {
        ProcessingRequest processingRequest;
        ProcessingRequest processingRequest2;
        Threads.checkMainThread();
        ((ProcessingNode.In) Objects.requireNonNull(this.mOutputEdge)).getEdge().accept(ProcessingNode.InputPacket.of(this.mCurrentRequest, imageProxy));
        ProcessingRequest processingRequest3 = this.mCurrentRequest;
        In in = this.mInputEdge;
        boolean z = false;
        boolean z2 = in != null && in.getOutputFormats().size() > 1;
        if (z2 && (processingRequest2 = this.mCurrentRequest) != null) {
            processingRequest2.getTakePictureRequest().markFormatProcessStatusInSimultaneousCapture(imageProxy.getFormat(), true);
        }
        if (!z2 || ((processingRequest = this.mCurrentRequest) != null && processingRequest.getTakePictureRequest().isFormatProcessedInSimultaneousCapture())) {
            z = true;
        }
        if (z) {
            this.mCurrentRequest = null;
        }
        processingRequest3.onImageCaptured();
    }

    private void propagatePostviewImage(ImageProxy imageProxy) {
        if (this.mCurrentRequest == null) {
            Logger.w(TAG, "Postview image is closed due to request completed or aborted");
            imageProxy.close();
            return;
        }
        ((ProcessingNode.In) Objects.requireNonNull(this.mOutputEdge)).getPostviewEdge().accept(ProcessingNode.InputPacket.of(this.mCurrentRequest, imageProxy));
    }

    /* access modifiers changed from: package-private */
    public void onRequestAvailable(final ProcessingRequest processingRequest) {
        Threads.checkMainThread();
        boolean z = false;
        Preconditions.checkState(processingRequest.getStageIds().size() == 1, "only one capture stage is supported.");
        if (getCapacity() > 0) {
            z = true;
        }
        Preconditions.checkState(z, "Too many acquire images. Close image to be able to process next.");
        this.mCurrentRequest = processingRequest;
        Futures.addCallback(processingRequest.getCaptureFuture(), new FutureCallback<Void>() {
            public void onSuccess(Void voidR) {
            }

            public void onFailure(Throwable th) {
                Threads.checkMainThread();
                if (processingRequest == CaptureNode.this.mCurrentRequest) {
                    Logger.w(CaptureNode.TAG, "request aborted, id=" + CaptureNode.this.mCurrentRequest.getRequestId());
                    if (CaptureNode.this.mNoMetadataImageReader != null) {
                        CaptureNode.this.mNoMetadataImageReader.clearProcessingRequest();
                    }
                    CaptureNode.this.mCurrentRequest = null;
                }
            }
        }, CameraXExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    public void sendCaptureError(TakePictureManager.CaptureError captureError) {
        Threads.checkMainThread();
        ProcessingRequest processingRequest = this.mCurrentRequest;
        if (processingRequest != null && processingRequest.getRequestId() == captureError.getRequestId()) {
            this.mCurrentRequest.onCaptureFailure(captureError.getImageCaptureException());
        }
    }

    public void release() {
        Threads.checkMainThread();
        releaseInputResources((In) Objects.requireNonNull(this.mInputEdge), (SafeCloseImageReaderProxy) Objects.requireNonNull(this.mSafeCloseImageReaderProxy), this.mSecondarySafeCloseImageReaderProxy, this.mSafeCloseImageReaderForPostview);
    }

    private void releaseInputResources(In in, SafeCloseImageReaderProxy safeCloseImageReaderProxy, SafeCloseImageReaderProxy safeCloseImageReaderProxy2, SafeCloseImageReaderProxy safeCloseImageReaderProxy3) {
        in.getSurface().close();
        in.getSurface().getTerminationFuture().addListener(new CaptureNode$$ExternalSyntheticLambda5(safeCloseImageReaderProxy), CameraXExecutors.mainThreadExecutor());
        if (in.getPostviewSurface() != null) {
            in.getPostviewSurface().close();
            in.getPostviewSurface().getTerminationFuture().addListener(new CaptureNode$$ExternalSyntheticLambda6(safeCloseImageReaderProxy3), CameraXExecutors.mainThreadExecutor());
        }
        if (in.getOutputFormats().size() > 1 && in.getSecondarySurface() != null) {
            in.getSecondarySurface().close();
            in.getSecondarySurface().getTerminationFuture().addListener(new CaptureNode$$ExternalSyntheticLambda7(safeCloseImageReaderProxy2), CameraXExecutors.mainThreadExecutor());
        }
    }

    static /* synthetic */ void lambda$releaseInputResources$4(SafeCloseImageReaderProxy safeCloseImageReaderProxy) {
        if (safeCloseImageReaderProxy != null) {
            safeCloseImageReaderProxy.safeClose();
        }
    }

    static /* synthetic */ void lambda$releaseInputResources$5(SafeCloseImageReaderProxy safeCloseImageReaderProxy) {
        if (safeCloseImageReaderProxy != null) {
            safeCloseImageReaderProxy.safeClose();
        }
    }

    /* access modifiers changed from: package-private */
    public In getInputEdge() {
        return (In) Objects.requireNonNull(this.mInputEdge);
    }

    public SafeCloseImageReaderProxy getSafeCloseImageReaderProxy() {
        return (SafeCloseImageReaderProxy) Objects.requireNonNull(this.mSafeCloseImageReaderProxy);
    }

    public int getCapacity() {
        Threads.checkMainThread();
        Preconditions.checkState(this.mSafeCloseImageReaderProxy != null, "The ImageReader is not initialized.");
        return this.mSafeCloseImageReaderProxy.getCapacity();
    }

    public void setOnImageCloseListener(ForwardingImageProxy.OnImageCloseListener onImageCloseListener) {
        Threads.checkMainThread();
        Preconditions.checkState(this.mSafeCloseImageReaderProxy != null, "The ImageReader is not initialized.");
        this.mSafeCloseImageReaderProxy.setOnImageCloseListener(onImageCloseListener);
    }

    static abstract class In {
        private CameraCaptureCallback mCameraCaptureCallback = new CameraCaptureCallback() {
        };
        private DeferrableSurface mPostviewSurface = null;
        private CameraCaptureCallback mSecondaryCameraCaptureCallback;
        private DeferrableSurface mSecondarySurface;
        private DeferrableSurface mSurface;

        /* access modifiers changed from: package-private */
        public abstract Edge<TakePictureManager.CaptureError> getErrorEdge();

        /* access modifiers changed from: package-private */
        public abstract ImageReaderProxyProvider getImageReaderProxyProvider();

        /* access modifiers changed from: package-private */
        public abstract int getInputFormat();

        /* access modifiers changed from: package-private */
        public abstract List<Integer> getOutputFormats();

        /* access modifiers changed from: package-private */
        public abstract int getPostviewImageFormat();

        /* access modifiers changed from: package-private */
        public abstract Size getPostviewSize();

        /* access modifiers changed from: package-private */
        public abstract Edge<ProcessingRequest> getRequestEdge();

        /* access modifiers changed from: package-private */
        public abstract Size getSize();

        /* access modifiers changed from: package-private */
        public abstract boolean isVirtualCamera();

        In() {
        }

        /* access modifiers changed from: package-private */
        public DeferrableSurface getSurface() {
            return (DeferrableSurface) Objects.requireNonNull(this.mSurface);
        }

        /* access modifiers changed from: package-private */
        public DeferrableSurface getPostviewSurface() {
            return this.mPostviewSurface;
        }

        /* access modifiers changed from: package-private */
        public DeferrableSurface getSecondarySurface() {
            return this.mSecondarySurface;
        }

        /* access modifiers changed from: package-private */
        public void setSurface(Surface surface) {
            Preconditions.checkState(this.mSurface == null, "The surface is already set.");
            this.mSurface = new ImmediateSurface(surface, getSize(), getInputFormat());
        }

        /* access modifiers changed from: package-private */
        public void setPostviewSurface(Surface surface, Size size, int i) {
            this.mPostviewSurface = new ImmediateSurface(surface, size, i);
        }

        /* access modifiers changed from: package-private */
        public void setSecondarySurface(Surface surface) {
            Preconditions.checkState(this.mSecondarySurface == null, "The secondary surface is already set.");
            this.mSecondarySurface = new ImmediateSurface(surface, getSize(), getInputFormat());
        }

        /* access modifiers changed from: package-private */
        public CameraCaptureCallback getCameraCaptureCallback() {
            return this.mCameraCaptureCallback;
        }

        /* access modifiers changed from: package-private */
        public void setCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            this.mCameraCaptureCallback = cameraCaptureCallback;
        }

        /* access modifiers changed from: package-private */
        public CameraCaptureCallback getSecondaryCameraCaptureCallback() {
            return this.mSecondaryCameraCaptureCallback;
        }

        /* access modifiers changed from: package-private */
        public void setSecondaryCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            this.mSecondaryCameraCaptureCallback = cameraCaptureCallback;
        }

        static In of(Size size, int i, List<Integer> list, boolean z, ImageReaderProxyProvider imageReaderProxyProvider) {
            return new AutoValue_CaptureNode_In(size, i, list, z, imageReaderProxyProvider, (Size) null, 35, new Edge(), new Edge());
        }

        static In of(Size size, int i, List<Integer> list, boolean z, ImageReaderProxyProvider imageReaderProxyProvider, Size size2, int i2) {
            return new AutoValue_CaptureNode_In(size, i, list, z, imageReaderProxyProvider, size2, i2, new Edge(), new Edge());
        }
    }
}
