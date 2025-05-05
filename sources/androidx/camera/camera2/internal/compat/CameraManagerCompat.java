package androidx.camera.camera2.internal.compat;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.util.ArrayMap;
import androidx.camera.camera2.internal.compat.ApiCompat;
import androidx.camera.core.impl.utils.MainThreadAsyncHandler;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

public final class CameraManagerCompat {
    private final Map<String, CameraCharacteristicsCompat> mCameraCharacteristicsMap = new ArrayMap(4);
    private final CameraManagerCompatImpl mImpl;

    private CameraManagerCompat(CameraManagerCompatImpl cameraManagerCompatImpl) {
        this.mImpl = cameraManagerCompatImpl;
    }

    public static CameraManagerCompat from(Context context) {
        return from(context, MainThreadAsyncHandler.getInstance());
    }

    public static CameraManagerCompat from(Context context, Handler handler) {
        return new CameraManagerCompat(CameraManagerCompatImpl.from(context, handler));
    }

    public static CameraManagerCompat from(CameraManagerCompatImpl cameraManagerCompatImpl) {
        return new CameraManagerCompat(cameraManagerCompatImpl);
    }

    public String[] getCameraIdList() throws CameraAccessExceptionCompat {
        return this.mImpl.getCameraIdList();
    }

    public Set<Set<String>> getConcurrentCameraIds() throws CameraAccessExceptionCompat {
        return this.mImpl.getConcurrentCameraIds();
    }

    public void registerAvailabilityCallback(Executor executor, CameraManager.AvailabilityCallback availabilityCallback) {
        this.mImpl.registerAvailabilityCallback(executor, availabilityCallback);
    }

    public void unregisterAvailabilityCallback(CameraManager.AvailabilityCallback availabilityCallback) {
        this.mImpl.unregisterAvailabilityCallback(availabilityCallback);
    }

    public CameraCharacteristicsCompat getCameraCharacteristicsCompat(String str) throws CameraAccessExceptionCompat {
        CameraCharacteristicsCompat cameraCharacteristicsCompat;
        synchronized (this.mCameraCharacteristicsMap) {
            cameraCharacteristicsCompat = this.mCameraCharacteristicsMap.get(str);
            if (cameraCharacteristicsCompat == null) {
                try {
                    cameraCharacteristicsCompat = CameraCharacteristicsCompat.toCameraCharacteristicsCompat(this.mImpl.getCameraCharacteristics(str), str);
                    this.mCameraCharacteristicsMap.put(str, cameraCharacteristicsCompat);
                } catch (AssertionError e) {
                    throw new CameraAccessExceptionCompat(CameraAccessExceptionCompat.CAMERA_CHARACTERISTICS_CREATION_ERROR, e.getMessage(), e);
                }
            }
        }
        return cameraCharacteristicsCompat;
    }

    public void openCamera(String str, Executor executor, CameraDevice.StateCallback stateCallback) throws CameraAccessExceptionCompat {
        this.mImpl.openCamera(str, executor, stateCallback);
    }

    public CameraManager unwrap() {
        return this.mImpl.getCameraManager();
    }

    public interface CameraManagerCompatImpl {
        CameraCharacteristics getCameraCharacteristics(String str) throws CameraAccessExceptionCompat;

        String[] getCameraIdList() throws CameraAccessExceptionCompat;

        CameraManager getCameraManager();

        Set<Set<String>> getConcurrentCameraIds() throws CameraAccessExceptionCompat;

        void openCamera(String str, Executor executor, CameraDevice.StateCallback stateCallback) throws CameraAccessExceptionCompat;

        void registerAvailabilityCallback(Executor executor, CameraManager.AvailabilityCallback availabilityCallback);

        void unregisterAvailabilityCallback(CameraManager.AvailabilityCallback availabilityCallback);

        static CameraManagerCompatImpl from(Context context, Handler handler) {
            if (Build.VERSION.SDK_INT >= 30) {
                return new CameraManagerCompatApi30Impl(context);
            }
            if (Build.VERSION.SDK_INT >= 29) {
                return new CameraManagerCompatApi29Impl(context);
            }
            if (Build.VERSION.SDK_INT >= 28) {
                return CameraManagerCompatApi28Impl.create(context);
            }
            return CameraManagerCompatBaseImpl.create(context, handler);
        }
    }

    static final class AvailabilityCallbackExecutorWrapper extends CameraManager.AvailabilityCallback {
        private boolean mDisabled = false;
        private final Executor mExecutor;
        private final Object mLock = new Object();
        final CameraManager.AvailabilityCallback mWrappedCallback;

        AvailabilityCallbackExecutorWrapper(Executor executor, CameraManager.AvailabilityCallback availabilityCallback) {
            this.mExecutor = executor;
            this.mWrappedCallback = availabilityCallback;
        }

        /* access modifiers changed from: package-private */
        public void setDisabled() {
            synchronized (this.mLock) {
                this.mDisabled = true;
            }
        }

        public void onCameraAccessPrioritiesChanged() {
            synchronized (this.mLock) {
                if (!this.mDisabled) {
                    this.mExecutor.execute(new CameraManagerCompat$AvailabilityCallbackExecutorWrapper$$ExternalSyntheticLambda0(this));
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCameraAccessPrioritiesChanged$0$androidx-camera-camera2-internal-compat-CameraManagerCompat$AvailabilityCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m120lambda$onCameraAccessPrioritiesChanged$0$androidxcameracamera2internalcompatCameraManagerCompat$AvailabilityCallbackExecutorWrapper() {
            ApiCompat.Api29Impl.onCameraAccessPrioritiesChanged(this.mWrappedCallback);
        }

        public void onCameraAvailable(String str) {
            synchronized (this.mLock) {
                if (!this.mDisabled) {
                    this.mExecutor.execute(new CameraManagerCompat$AvailabilityCallbackExecutorWrapper$$ExternalSyntheticLambda2(this, str));
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCameraAvailable$1$androidx-camera-camera2-internal-compat-CameraManagerCompat$AvailabilityCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m121lambda$onCameraAvailable$1$androidxcameracamera2internalcompatCameraManagerCompat$AvailabilityCallbackExecutorWrapper(String str) {
            this.mWrappedCallback.onCameraAvailable(str);
        }

        public void onCameraUnavailable(String str) {
            synchronized (this.mLock) {
                if (!this.mDisabled) {
                    this.mExecutor.execute(new CameraManagerCompat$AvailabilityCallbackExecutorWrapper$$ExternalSyntheticLambda1(this, str));
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onCameraUnavailable$2$androidx-camera-camera2-internal-compat-CameraManagerCompat$AvailabilityCallbackExecutorWrapper  reason: not valid java name */
        public /* synthetic */ void m122lambda$onCameraUnavailable$2$androidxcameracamera2internalcompatCameraManagerCompat$AvailabilityCallbackExecutorWrapper(String str) {
            this.mWrappedCallback.onCameraUnavailable(str);
        }
    }
}
