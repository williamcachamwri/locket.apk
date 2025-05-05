package androidx.camera.core.streamsharing;

import androidx.camera.core.impl.CameraCaptureMetaData;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.TagBundle;

public class VirtualCameraCaptureResult implements CameraCaptureResult {
    private static final long INVALID_TIMESTAMP = -1;
    private final CameraCaptureResult mBaseCameraCaptureResult;
    private final TagBundle mTagBundle;
    private final long mTimestamp;

    public VirtualCameraCaptureResult(TagBundle tagBundle, CameraCaptureResult cameraCaptureResult) {
        this(cameraCaptureResult, tagBundle, -1);
    }

    public VirtualCameraCaptureResult(TagBundle tagBundle, long j) {
        this((CameraCaptureResult) null, tagBundle, j);
    }

    private VirtualCameraCaptureResult(CameraCaptureResult cameraCaptureResult, TagBundle tagBundle, long j) {
        this.mBaseCameraCaptureResult = cameraCaptureResult;
        this.mTagBundle = tagBundle;
        this.mTimestamp = j;
    }

    public TagBundle getTagBundle() {
        return this.mTagBundle;
    }

    public CameraCaptureMetaData.AfMode getAfMode() {
        CameraCaptureResult cameraCaptureResult = this.mBaseCameraCaptureResult;
        if (cameraCaptureResult != null) {
            return cameraCaptureResult.getAfMode();
        }
        return CameraCaptureMetaData.AfMode.UNKNOWN;
    }

    public CameraCaptureMetaData.AfState getAfState() {
        CameraCaptureResult cameraCaptureResult = this.mBaseCameraCaptureResult;
        if (cameraCaptureResult != null) {
            return cameraCaptureResult.getAfState();
        }
        return CameraCaptureMetaData.AfState.UNKNOWN;
    }

    public CameraCaptureMetaData.AeState getAeState() {
        CameraCaptureResult cameraCaptureResult = this.mBaseCameraCaptureResult;
        if (cameraCaptureResult != null) {
            return cameraCaptureResult.getAeState();
        }
        return CameraCaptureMetaData.AeState.UNKNOWN;
    }

    public CameraCaptureMetaData.AwbState getAwbState() {
        CameraCaptureResult cameraCaptureResult = this.mBaseCameraCaptureResult;
        if (cameraCaptureResult != null) {
            return cameraCaptureResult.getAwbState();
        }
        return CameraCaptureMetaData.AwbState.UNKNOWN;
    }

    public CameraCaptureMetaData.FlashState getFlashState() {
        CameraCaptureResult cameraCaptureResult = this.mBaseCameraCaptureResult;
        if (cameraCaptureResult != null) {
            return cameraCaptureResult.getFlashState();
        }
        return CameraCaptureMetaData.FlashState.UNKNOWN;
    }

    public CameraCaptureMetaData.AeMode getAeMode() {
        CameraCaptureResult cameraCaptureResult = this.mBaseCameraCaptureResult;
        if (cameraCaptureResult != null) {
            return cameraCaptureResult.getAeMode();
        }
        return CameraCaptureMetaData.AeMode.UNKNOWN;
    }

    public CameraCaptureMetaData.AwbMode getAwbMode() {
        CameraCaptureResult cameraCaptureResult = this.mBaseCameraCaptureResult;
        if (cameraCaptureResult != null) {
            return cameraCaptureResult.getAwbMode();
        }
        return CameraCaptureMetaData.AwbMode.UNKNOWN;
    }

    public long getTimestamp() {
        CameraCaptureResult cameraCaptureResult = this.mBaseCameraCaptureResult;
        if (cameraCaptureResult != null) {
            return cameraCaptureResult.getTimestamp();
        }
        long j = this.mTimestamp;
        if (j != -1) {
            return j;
        }
        throw new IllegalStateException("No timestamp is available.");
    }
}
