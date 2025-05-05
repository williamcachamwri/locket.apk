package androidx.camera.core.processing;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.SurfaceOutput;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SurfaceEdge {
    private final Rect mCropRect;
    private final int mFormat;
    private final boolean mHasCameraTransform;
    private boolean mHasConsumer = false;
    private boolean mIsClosed = false;
    private final boolean mMirroring;
    private final Set<Runnable> mOnInvalidatedListeners = new HashSet();
    private SurfaceRequest mProviderSurfaceRequest;
    private int mRotationDegrees;
    private final Matrix mSensorToBufferTransform;
    private SettableSurface mSettableSurface;
    private final StreamSpec mStreamSpec;
    private int mTargetRotation;
    private final int mTargets;
    private final List<Consumer<SurfaceRequest.TransformationInfo>> mTransformationUpdatesListeners = new ArrayList();

    public SurfaceEdge(int i, int i2, StreamSpec streamSpec, Matrix matrix, boolean z, Rect rect, int i3, int i4, boolean z2) {
        this.mTargets = i;
        this.mFormat = i2;
        this.mStreamSpec = streamSpec;
        this.mSensorToBufferTransform = matrix;
        this.mHasCameraTransform = z;
        this.mCropRect = rect;
        this.mRotationDegrees = i3;
        this.mTargetRotation = i4;
        this.mMirroring = z2;
        this.mSettableSurface = new SettableSurface(streamSpec.getResolution(), i2);
    }

    public void addOnInvalidatedListener(Runnable runnable) {
        Threads.checkMainThread();
        checkNotClosed();
        this.mOnInvalidatedListeners.add(runnable);
    }

    public DeferrableSurface getDeferrableSurface() {
        Threads.checkMainThread();
        checkNotClosed();
        checkAndSetHasConsumer();
        return this.mSettableSurface;
    }

    public void setProvider(DeferrableSurface deferrableSurface) throws DeferrableSurface.SurfaceClosedException {
        Threads.checkMainThread();
        checkNotClosed();
        SettableSurface settableSurface = this.mSettableSurface;
        Objects.requireNonNull(settableSurface);
        settableSurface.setProvider(deferrableSurface, new SurfaceEdge$$ExternalSyntheticLambda1(settableSurface));
    }

    public SurfaceRequest createSurfaceRequest(CameraInternal cameraInternal) {
        return createSurfaceRequest(cameraInternal, true);
    }

    public SurfaceRequest createSurfaceRequest(CameraInternal cameraInternal, boolean z) {
        Threads.checkMainThread();
        checkNotClosed();
        SurfaceRequest surfaceRequest = new SurfaceRequest(this.mStreamSpec.getResolution(), cameraInternal, z, this.mStreamSpec.getDynamicRange(), this.mStreamSpec.getExpectedFrameRateRange(), new SurfaceEdge$$ExternalSyntheticLambda2(this));
        try {
            DeferrableSurface deferrableSurface = surfaceRequest.getDeferrableSurface();
            SettableSurface settableSurface = this.mSettableSurface;
            Objects.requireNonNull(settableSurface);
            if (settableSurface.setProvider(deferrableSurface, new SurfaceEdge$$ExternalSyntheticLambda1(settableSurface))) {
                ListenableFuture<Void> terminationFuture = settableSurface.getTerminationFuture();
                Objects.requireNonNull(deferrableSurface);
                terminationFuture.addListener(new SurfaceEdge$$ExternalSyntheticLambda3(deferrableSurface), CameraXExecutors.directExecutor());
            }
            this.mProviderSurfaceRequest = surfaceRequest;
            notifyTransformationInfoUpdate();
            return surfaceRequest;
        } catch (DeferrableSurface.SurfaceClosedException e) {
            throw new AssertionError("Surface is somehow already closed", e);
        } catch (RuntimeException e2) {
            surfaceRequest.willNotProvideSurface();
            throw e2;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createSurfaceRequest$1$androidx-camera-core-processing-SurfaceEdge  reason: not valid java name */
    public /* synthetic */ void m204lambda$createSurfaceRequest$1$androidxcameracoreprocessingSurfaceEdge() {
        CameraXExecutors.mainThreadExecutor().execute(new SurfaceEdge$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createSurfaceRequest$0$androidx-camera-core-processing-SurfaceEdge  reason: not valid java name */
    public /* synthetic */ void m203lambda$createSurfaceRequest$0$androidxcameracoreprocessingSurfaceEdge() {
        if (!this.mIsClosed) {
            invalidate();
        }
    }

    public ListenableFuture<SurfaceOutput> createSurfaceOutputFuture(int i, SurfaceOutput.CameraInputInfo cameraInputInfo, SurfaceOutput.CameraInputInfo cameraInputInfo2) {
        Threads.checkMainThread();
        checkNotClosed();
        checkAndSetHasConsumer();
        SettableSurface settableSurface = this.mSettableSurface;
        return Futures.transformAsync(settableSurface.getSurface(), new SurfaceEdge$$ExternalSyntheticLambda4(this, settableSurface, i, cameraInputInfo, cameraInputInfo2), CameraXExecutors.mainThreadExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createSurfaceOutputFuture$2$androidx-camera-core-processing-SurfaceEdge  reason: not valid java name */
    public /* synthetic */ ListenableFuture m202lambda$createSurfaceOutputFuture$2$androidxcameracoreprocessingSurfaceEdge(SettableSurface settableSurface, int i, SurfaceOutput.CameraInputInfo cameraInputInfo, SurfaceOutput.CameraInputInfo cameraInputInfo2, Surface surface) throws Exception {
        Preconditions.checkNotNull(surface);
        try {
            settableSurface.incrementUseCount();
            SurfaceOutputImpl surfaceOutputImpl = new SurfaceOutputImpl(surface, getTargets(), i, this.mStreamSpec.getResolution(), cameraInputInfo, cameraInputInfo2, this.mSensorToBufferTransform);
            ListenableFuture<Void> closeFuture = surfaceOutputImpl.getCloseFuture();
            Objects.requireNonNull(settableSurface);
            closeFuture.addListener(new SurfaceEdge$$ExternalSyntheticLambda5(settableSurface), CameraXExecutors.directExecutor());
            settableSurface.setConsumer(surfaceOutputImpl);
            return Futures.immediateFuture(surfaceOutputImpl);
        } catch (DeferrableSurface.SurfaceClosedException e) {
            return Futures.immediateFailedFuture(e);
        }
    }

    public void invalidate() {
        Threads.checkMainThread();
        checkNotClosed();
        if (!this.mSettableSurface.canSetProvider()) {
            this.mHasConsumer = false;
            this.mSettableSurface.close();
            this.mSettableSurface = new SettableSurface(this.mStreamSpec.getResolution(), this.mFormat);
            for (Runnable run : this.mOnInvalidatedListeners) {
                run.run();
            }
        }
    }

    public final void close() {
        Threads.checkMainThread();
        this.mSettableSurface.close();
        this.mIsClosed = true;
    }

    public final void disconnect() {
        Threads.checkMainThread();
        checkNotClosed();
        this.mSettableSurface.close();
    }

    public int getTargets() {
        return this.mTargets;
    }

    public int getFormat() {
        return this.mFormat;
    }

    public Matrix getSensorToBufferTransform() {
        return this.mSensorToBufferTransform;
    }

    public boolean hasCameraTransform() {
        return this.mHasCameraTransform;
    }

    public Rect getCropRect() {
        return this.mCropRect;
    }

    public int getRotationDegrees() {
        return this.mRotationDegrees;
    }

    public void updateTransformation(int i) {
        updateTransformation(i, -1);
    }

    public void updateTransformation(int i, int i2) {
        Threads.runOnMain(new SurfaceEdge$$ExternalSyntheticLambda6(this, i, i2));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateTransformation$3$androidx-camera-core-processing-SurfaceEdge  reason: not valid java name */
    public /* synthetic */ void m205lambda$updateTransformation$3$androidxcameracoreprocessingSurfaceEdge(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (this.mRotationDegrees != i) {
            this.mRotationDegrees = i;
            z = true;
        } else {
            z = false;
        }
        if (this.mTargetRotation != i2) {
            this.mTargetRotation = i2;
        } else {
            z2 = z;
        }
        if (z2) {
            notifyTransformationInfoUpdate();
        }
    }

    public void addTransformationUpdateListener(Consumer<SurfaceRequest.TransformationInfo> consumer) {
        Preconditions.checkNotNull(consumer);
        this.mTransformationUpdatesListeners.add(consumer);
    }

    public void removeTransformationUpdateListener(Consumer<SurfaceRequest.TransformationInfo> consumer) {
        Preconditions.checkNotNull(consumer);
        this.mTransformationUpdatesListeners.remove(consumer);
    }

    private void notifyTransformationInfoUpdate() {
        Threads.checkMainThread();
        SurfaceRequest.TransformationInfo of = SurfaceRequest.TransformationInfo.of(this.mCropRect, this.mRotationDegrees, this.mTargetRotation, hasCameraTransform(), this.mSensorToBufferTransform, this.mMirroring);
        SurfaceRequest surfaceRequest = this.mProviderSurfaceRequest;
        if (surfaceRequest != null) {
            surfaceRequest.updateTransformationInfo(of);
        }
        for (Consumer<SurfaceRequest.TransformationInfo> accept : this.mTransformationUpdatesListeners) {
            accept.accept(of);
        }
    }

    private void checkAndSetHasConsumer() {
        Preconditions.checkState(!this.mHasConsumer, "Consumer can only be linked once.");
        this.mHasConsumer = true;
    }

    public boolean isMirroring() {
        return this.mMirroring;
    }

    public StreamSpec getStreamSpec() {
        return this.mStreamSpec;
    }

    private void checkNotClosed() {
        Preconditions.checkState(!this.mIsClosed, "Edge is already closed.");
    }

    public DeferrableSurface getDeferrableSurfaceForTesting() {
        return this.mSettableSurface;
    }

    public boolean isClosed() {
        return this.mIsClosed;
    }

    public boolean hasProvider() {
        return this.mSettableSurface.hasProvider();
    }

    static class SettableSurface extends DeferrableSurface {
        CallbackToFutureAdapter.Completer<Surface> mCompleter;
        private SurfaceOutputImpl mConsumer;
        private DeferrableSurface mProvider;
        final ListenableFuture<Surface> mSurfaceFuture = CallbackToFutureAdapter.getFuture(new SurfaceEdge$SettableSurface$$ExternalSyntheticLambda0(this));

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$androidx-camera-core-processing-SurfaceEdge$SettableSurface  reason: not valid java name */
        public /* synthetic */ Object m207lambda$new$0$androidxcameracoreprocessingSurfaceEdge$SettableSurface(CallbackToFutureAdapter.Completer completer) throws Exception {
            this.mCompleter = completer;
            return "SettableFuture hashCode: " + hashCode();
        }

        SettableSurface(Size size, int i) {
            super(size, i);
        }

        /* access modifiers changed from: protected */
        public ListenableFuture<Surface> provideSurface() {
            return this.mSurfaceFuture;
        }

        /* access modifiers changed from: package-private */
        public boolean canSetProvider() {
            Threads.checkMainThread();
            return this.mProvider == null && !isClosed();
        }

        public void setConsumer(SurfaceOutputImpl surfaceOutputImpl) {
            Preconditions.checkState(this.mConsumer == null, "Consumer can only be linked once.");
            this.mConsumer = surfaceOutputImpl;
        }

        /* access modifiers changed from: package-private */
        public boolean hasProvider() {
            return this.mProvider != null;
        }

        public boolean setProvider(DeferrableSurface deferrableSurface, Runnable runnable) throws DeferrableSurface.SurfaceClosedException {
            Threads.checkMainThread();
            Preconditions.checkNotNull(deferrableSurface);
            DeferrableSurface deferrableSurface2 = this.mProvider;
            boolean z = false;
            if (deferrableSurface2 == deferrableSurface) {
                return false;
            }
            Preconditions.checkState(deferrableSurface2 == null, "A different provider has been set. To change the provider, call SurfaceEdge#invalidate before calling SurfaceEdge#setProvider");
            Preconditions.checkArgument(getPrescribedSize().equals(deferrableSurface.getPrescribedSize()), String.format("The provider's size(%s) must match the parent(%s)", new Object[]{getPrescribedSize(), deferrableSurface.getPrescribedSize()}));
            if (getPrescribedStreamFormat() == deferrableSurface.getPrescribedStreamFormat()) {
                z = true;
            }
            Preconditions.checkArgument(z, String.format("The provider's format(%s) must match the parent(%s)", new Object[]{Integer.valueOf(getPrescribedStreamFormat()), Integer.valueOf(deferrableSurface.getPrescribedStreamFormat())}));
            Preconditions.checkState(!isClosed(), "The parent is closed. Call SurfaceEdge#invalidate() before setting a new provider.");
            this.mProvider = deferrableSurface;
            Futures.propagate(deferrableSurface.getSurface(), this.mCompleter);
            deferrableSurface.incrementUseCount();
            ListenableFuture<Void> terminationFuture = getTerminationFuture();
            Objects.requireNonNull(deferrableSurface);
            terminationFuture.addListener(new SurfaceEdge$SettableSurface$$ExternalSyntheticLambda1(deferrableSurface), CameraXExecutors.directExecutor());
            deferrableSurface.getCloseFuture().addListener(runnable, CameraXExecutors.mainThreadExecutor());
            return true;
        }

        public void close() {
            super.close();
            Threads.runOnMain(new SurfaceEdge$SettableSurface$$ExternalSyntheticLambda2(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$close$1$androidx-camera-core-processing-SurfaceEdge$SettableSurface  reason: not valid java name */
        public /* synthetic */ void m206lambda$close$1$androidxcameracoreprocessingSurfaceEdge$SettableSurface() {
            SurfaceOutputImpl surfaceOutputImpl = this.mConsumer;
            if (surfaceOutputImpl != null) {
                surfaceOutputImpl.requestClose();
            }
            if (this.mProvider == null) {
                this.mCompleter.setCancelled();
            }
        }
    }
}
