package androidx.camera.core.impl;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Logger;
import java.util.LinkedHashSet;

public final class CameraValidator {
    private static final CameraSelector EXTERNAL_LENS_FACING = new CameraSelector.Builder().requireLensFacing(2).build();
    private static final String TAG = "CameraValidator";

    private CameraValidator() {
    }

    public static void validateCameras(Context context, CameraRepository cameraRepository, CameraSelector cameraSelector) throws CameraIdListIncorrectException {
        Integer num;
        int i = 0;
        IllegalArgumentException illegalArgumentException = null;
        if (Build.VERSION.SDK_INT < 34 || Api34Impl.getDeviceId(context) == 0) {
            if (cameraSelector != null) {
                try {
                    num = cameraSelector.getLensFacing();
                    if (num == null) {
                        Logger.w(TAG, "No lens facing info in the availableCamerasSelector, don't verify the camera lens facing.");
                        return;
                    }
                } catch (IllegalStateException e) {
                    Logger.e(TAG, "Cannot get lens facing from the availableCamerasSelector don't verify the camera lens facing.", e);
                    return;
                }
            } else {
                num = null;
            }
            Logger.d(TAG, "Verifying camera lens facing on " + Build.DEVICE + ", lensFacingInteger: " + num);
            PackageManager packageManager = context.getPackageManager();
            try {
                if (packageManager.hasSystemFeature("android.hardware.camera") && (cameraSelector == null || num.intValue() == 1)) {
                    CameraSelector.DEFAULT_BACK_CAMERA.select(cameraRepository.getCameras());
                    i = 1;
                }
            } catch (IllegalArgumentException e2) {
                illegalArgumentException = e2;
                Logger.w(TAG, "Camera LENS_FACING_BACK verification failed", illegalArgumentException);
            }
            try {
                if (packageManager.hasSystemFeature("android.hardware.camera.front") && (cameraSelector == null || num.intValue() == 0)) {
                    CameraSelector.DEFAULT_FRONT_CAMERA.select(cameraRepository.getCameras());
                    i++;
                }
            } catch (IllegalArgumentException e3) {
                illegalArgumentException = e3;
                Logger.w(TAG, "Camera LENS_FACING_FRONT verification failed", illegalArgumentException);
            }
            try {
                EXTERNAL_LENS_FACING.select(cameraRepository.getCameras());
                Logger.d(TAG, "Found a LENS_FACING_EXTERNAL camera");
                i++;
            } catch (IllegalArgumentException unused) {
            }
            if (illegalArgumentException != null) {
                Logger.e(TAG, "Camera LensFacing verification failed, existing cameras: " + cameraRepository.getCameras());
                throw new CameraIdListIncorrectException("Expected camera missing from device.", i, illegalArgumentException);
            }
            return;
        }
        LinkedHashSet<CameraInternal> cameras = cameraRepository.getCameras();
        if (!cameras.isEmpty()) {
            Logger.d(TAG, "Virtual device with ID: " + Api34Impl.getDeviceId(context) + " has " + cameras.size() + " cameras. Skipping validation.");
            return;
        }
        throw new CameraIdListIncorrectException("No cameras available", 0, (Throwable) null);
    }

    public static class CameraIdListIncorrectException extends Exception {
        private int mAvailableCameraCount;

        public CameraIdListIncorrectException(String str, int i, Throwable th) {
            super(str, th);
            this.mAvailableCameraCount = i;
        }

        public int getAvailableCameraCount() {
            return this.mAvailableCameraCount;
        }
    }

    private static class Api34Impl {
        private Api34Impl() {
        }

        static int getDeviceId(Context context) {
            return context.getDeviceId();
        }
    }
}
