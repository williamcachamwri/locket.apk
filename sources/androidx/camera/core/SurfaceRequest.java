package androidx.camera.core;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public final class SurfaceRequest {
    public static final Range<Integer> FRAME_RATE_RANGE_UNSPECIFIED = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
    private final CameraInternal mCamera;
    private final DynamicRange mDynamicRange;
    private final Range<Integer> mExpectedFrameRate;
    private final DeferrableSurface mInternalDeferrableSurface;
    private final boolean mIsPrimary;
    private final Object mLock;
    private final CallbackToFutureAdapter.Completer<Void> mRequestCancellationCompleter;
    private final Size mResolution;
    private final ListenableFuture<Void> mSessionStatusFuture;
    private final CallbackToFutureAdapter.Completer<Surface> mSurfaceCompleter;
    final ListenableFuture<Surface> mSurfaceFuture;
    private final CallbackToFutureAdapter.Completer<Void> mSurfaceRecreationCompleter;
    private TransformationInfo mTransformationInfo;
    private Executor mTransformationInfoExecutor;
    private TransformationInfoListener mTransformationInfoListener;

    public interface TransformationInfoListener {
        void onTransformationInfoUpdate(TransformationInfo transformationInfo);
    }

    public SurfaceRequest(Size size, CameraInternal cameraInternal, Runnable runnable) {
        this(size, cameraInternal, DynamicRange.SDR, FRAME_RATE_RANGE_UNSPECIFIED, runnable);
    }

    public SurfaceRequest(Size size, CameraInternal cameraInternal, DynamicRange dynamicRange, Range<Integer> range, Runnable runnable) {
        this(size, cameraInternal, true, dynamicRange, range, runnable);
    }

    public SurfaceRequest(Size size, CameraInternal cameraInternal, boolean z, DynamicRange dynamicRange, Range<Integer> range, Runnable runnable) {
        this.mLock = new Object();
        this.mResolution = size;
        this.mCamera = cameraInternal;
        this.mIsPrimary = z;
        Preconditions.checkArgument(dynamicRange.isFullySpecified(), "SurfaceRequest's DynamicRange must always be fully specified.");
        this.mDynamicRange = dynamicRange;
        this.mExpectedFrameRate = range;
        final String str = "SurfaceRequest[size: " + size + ", id: " + hashCode() + "]";
        AtomicReference atomicReference = new AtomicReference((Object) null);
        final ListenableFuture future = CallbackToFutureAdapter.getFuture(new SurfaceRequest$$ExternalSyntheticLambda3(atomicReference, str));
        final CallbackToFutureAdapter.Completer<Void> completer = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
        this.mRequestCancellationCompleter = completer;
        AtomicReference atomicReference2 = new AtomicReference((Object) null);
        ListenableFuture<Void> future2 = CallbackToFutureAdapter.getFuture(new SurfaceRequest$$ExternalSyntheticLambda4(atomicReference2, str));
        this.mSessionStatusFuture = future2;
        Futures.addCallback(future2, new FutureCallback<Void>() {
            public void onSuccess(Void voidR) {
                Preconditions.checkState(completer.set(null));
            }

            public void onFailure(Throwable th) {
                if (th instanceof RequestCancelledException) {
                    Preconditions.checkState(future.cancel(false));
                } else {
                    Preconditions.checkState(completer.set(null));
                }
            }
        }, CameraXExecutors.directExecutor());
        final CallbackToFutureAdapter.Completer completer2 = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference2.get());
        AtomicReference atomicReference3 = new AtomicReference((Object) null);
        ListenableFuture<Surface> future3 = CallbackToFutureAdapter.getFuture(new SurfaceRequest$$ExternalSyntheticLambda5(atomicReference3, str));
        this.mSurfaceFuture = future3;
        this.mSurfaceCompleter = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference3.get());
        AnonymousClass2 r8 = new DeferrableSurface(size, 34) {
            /* access modifiers changed from: protected */
            public ListenableFuture<Surface> provideSurface() {
                return SurfaceRequest.this.mSurfaceFuture;
            }
        };
        this.mInternalDeferrableSurface = r8;
        final ListenableFuture<Void> terminationFuture = r8.getTerminationFuture();
        Futures.addCallback(future3, new FutureCallback<Surface>() {
            public void onSuccess(Surface surface) {
                Futures.propagate(terminationFuture, completer2);
            }

            public void onFailure(Throwable th) {
                if (th instanceof CancellationException) {
                    Preconditions.checkState(completer2.setException(new RequestCancelledException(str + " cancelled.", th)));
                } else {
                    completer2.set(null);
                }
            }
        }, CameraXExecutors.directExecutor());
        terminationFuture.addListener(new SurfaceRequest$$ExternalSyntheticLambda6(this), CameraXExecutors.directExecutor());
        this.mSurfaceRecreationCompleter = initialSurfaceRecreationCompleter(CameraXExecutors.directExecutor(), runnable);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$new$3$androidx-camera-core-SurfaceRequest  reason: not valid java name */
    public /* synthetic */ void m155lambda$new$3$androidxcameracoreSurfaceRequest() {
        this.mSurfaceFuture.cancel(true);
    }

    public DeferrableSurface getDeferrableSurface() {
        return this.mInternalDeferrableSurface;
    }

    public boolean isServiced() {
        return this.mSurfaceFuture.isDone();
    }

    public Size getResolution() {
        return this.mResolution;
    }

    public DynamicRange getDynamicRange() {
        return this.mDynamicRange;
    }

    public Range<Integer> getExpectedFrameRate() {
        return this.mExpectedFrameRate;
    }

    public CameraInternal getCamera() {
        return this.mCamera;
    }

    public boolean isPrimary() {
        return this.mIsPrimary;
    }

    public void provideSurface(final Surface surface, Executor executor, final Consumer<Result> consumer) {
        if (this.mSurfaceCompleter.set(surface) || this.mSurfaceFuture.isCancelled()) {
            Futures.addCallback(this.mSessionStatusFuture, new FutureCallback<Void>() {
                public void onSuccess(Void voidR) {
                    consumer.accept(Result.of(0, surface));
                }

                public void onFailure(Throwable th) {
                    Preconditions.checkState(th instanceof RequestCancelledException, "Camera surface session should only fail with request cancellation. Instead failed due to:\n" + th);
                    consumer.accept(Result.of(1, surface));
                }
            }, executor);
            return;
        }
        Preconditions.checkState(this.mSurfaceFuture.isDone());
        try {
            this.mSurfaceFuture.get();
            executor.execute(new SurfaceRequest$$ExternalSyntheticLambda1(consumer, surface));
        } catch (InterruptedException | ExecutionException unused) {
            executor.execute(new SurfaceRequest$$ExternalSyntheticLambda2(consumer, surface));
        }
    }

    public boolean willNotProvideSurface() {
        return this.mSurfaceCompleter.setException(new DeferrableSurface.SurfaceUnavailableException("Surface request will not complete."));
    }

    private CallbackToFutureAdapter.Completer<Void> initialSurfaceRecreationCompleter(Executor executor, final Runnable runnable) {
        AtomicReference atomicReference = new AtomicReference((Object) null);
        Futures.addCallback(CallbackToFutureAdapter.getFuture(new SurfaceRequest$$ExternalSyntheticLambda0(this, atomicReference)), new FutureCallback<Void>() {
            public void onFailure(Throwable th) {
            }

            public void onSuccess(Void voidR) {
                runnable.run();
            }
        }, executor);
        return (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$initialSurfaceRecreationCompleter$6$androidx-camera-core-SurfaceRequest  reason: not valid java name */
    public /* synthetic */ Object m154lambda$initialSurfaceRecreationCompleter$6$androidxcameracoreSurfaceRequest(AtomicReference atomicReference, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return "SurfaceRequest-surface-recreation(" + hashCode() + ")";
    }

    public boolean invalidate() {
        willNotProvideSurface();
        return this.mSurfaceRecreationCompleter.set(null);
    }

    public void addRequestCancellationListener(Executor executor, Runnable runnable) {
        this.mRequestCancellationCompleter.addCancellationListener(runnable, executor);
    }

    public void updateTransformationInfo(TransformationInfo transformationInfo) {
        TransformationInfoListener transformationInfoListener;
        Executor executor;
        synchronized (this.mLock) {
            this.mTransformationInfo = transformationInfo;
            transformationInfoListener = this.mTransformationInfoListener;
            executor = this.mTransformationInfoExecutor;
        }
        if (transformationInfoListener != null && executor != null) {
            executor.execute(new SurfaceRequest$$ExternalSyntheticLambda8(transformationInfoListener, transformationInfo));
        }
    }

    public void setTransformationInfoListener(Executor executor, TransformationInfoListener transformationInfoListener) {
        TransformationInfo transformationInfo;
        synchronized (this.mLock) {
            this.mTransformationInfoListener = transformationInfoListener;
            this.mTransformationInfoExecutor = executor;
            transformationInfo = this.mTransformationInfo;
        }
        if (transformationInfo != null) {
            executor.execute(new SurfaceRequest$$ExternalSyntheticLambda7(transformationInfoListener, transformationInfo));
        }
    }

    public void clearTransformationInfoListener() {
        synchronized (this.mLock) {
            this.mTransformationInfoListener = null;
            this.mTransformationInfoExecutor = null;
        }
    }

    private static final class RequestCancelledException extends RuntimeException {
        RequestCancelledException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static abstract class Result {
        public static final int RESULT_INVALID_SURFACE = 2;
        public static final int RESULT_REQUEST_CANCELLED = 1;
        public static final int RESULT_SURFACE_ALREADY_PROVIDED = 3;
        public static final int RESULT_SURFACE_USED_SUCCESSFULLY = 0;
        public static final int RESULT_WILL_NOT_PROVIDE_SURFACE = 4;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ResultCode {
        }

        public abstract int getResultCode();

        public abstract Surface getSurface();

        static Result of(int i, Surface surface) {
            return new AutoValue_SurfaceRequest_Result(i, surface);
        }

        Result() {
        }
    }

    public static abstract class TransformationInfo {
        public abstract Rect getCropRect();

        public abstract int getRotationDegrees();

        public abstract Matrix getSensorToBufferTransform();

        public abstract int getTargetRotation();

        public abstract boolean hasCameraTransform();

        public abstract boolean isMirroring();

        public static TransformationInfo of(Rect rect, int i, int i2, boolean z, Matrix matrix, boolean z2) {
            return new AutoValue_SurfaceRequest_TransformationInfo(rect, i, i2, z, matrix, z2);
        }

        TransformationInfo() {
        }
    }
}
