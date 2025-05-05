package androidx.camera.core.impl;

import android.hardware.camera2.CaptureResult;
import androidx.camera.core.impl.CameraCaptureMetaData;
import androidx.camera.core.impl.utils.ExifData;

public interface CameraCaptureResult {
    CameraCaptureMetaData.AeMode getAeMode();

    CameraCaptureMetaData.AeState getAeState();

    CameraCaptureMetaData.AfMode getAfMode();

    CameraCaptureMetaData.AfState getAfState();

    CameraCaptureMetaData.AwbMode getAwbMode();

    CameraCaptureMetaData.AwbState getAwbState();

    CaptureResult getCaptureResult() {
        return null;
    }

    CameraCaptureMetaData.FlashState getFlashState();

    TagBundle getTagBundle();

    long getTimestamp();

    void populateExifData(ExifData.Builder builder) {
        builder.setFlashState(getFlashState());
    }

    public static final class EmptyCameraCaptureResult implements CameraCaptureResult {
        public CaptureResult getCaptureResult() {
            return null;
        }

        public long getTimestamp() {
            return -1;
        }

        public static CameraCaptureResult create() {
            return new EmptyCameraCaptureResult();
        }

        public CameraCaptureMetaData.AfMode getAfMode() {
            return CameraCaptureMetaData.AfMode.UNKNOWN;
        }

        public CameraCaptureMetaData.AfState getAfState() {
            return CameraCaptureMetaData.AfState.UNKNOWN;
        }

        public CameraCaptureMetaData.AeState getAeState() {
            return CameraCaptureMetaData.AeState.UNKNOWN;
        }

        public CameraCaptureMetaData.AwbState getAwbState() {
            return CameraCaptureMetaData.AwbState.UNKNOWN;
        }

        public CameraCaptureMetaData.FlashState getFlashState() {
            return CameraCaptureMetaData.FlashState.UNKNOWN;
        }

        public CameraCaptureMetaData.AeMode getAeMode() {
            return CameraCaptureMetaData.AeMode.UNKNOWN;
        }

        public CameraCaptureMetaData.AwbMode getAwbMode() {
            return CameraCaptureMetaData.AwbMode.UNKNOWN;
        }

        public TagBundle getTagBundle() {
            return TagBundle.emptyBundle();
        }
    }
}
