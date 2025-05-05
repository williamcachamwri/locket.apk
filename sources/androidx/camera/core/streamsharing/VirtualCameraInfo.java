package androidx.camera.core.streamsharing;

import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.ForwardingCameraInfo;
import androidx.camera.core.impl.utils.TransformUtils;
import java.util.UUID;

public class VirtualCameraInfo extends ForwardingCameraInfo {
    private final String mVirtualCameraId;
    private int mVirtualCameraRotationDegrees;

    VirtualCameraInfo(CameraInfoInternal cameraInfoInternal) {
        super(cameraInfoInternal);
        this.mVirtualCameraId = "virtual-" + cameraInfoInternal.getCameraId() + "-" + UUID.randomUUID().toString();
    }

    public String getCameraId() {
        return this.mVirtualCameraId;
    }

    /* access modifiers changed from: package-private */
    public void setVirtualCameraRotationDegrees(int i) {
        this.mVirtualCameraRotationDegrees = i;
    }

    public int getSensorRotationDegrees() {
        return getSensorRotationDegrees(0);
    }

    public int getSensorRotationDegrees(int i) {
        return TransformUtils.within360(super.getSensorRotationDegrees(i) - this.mVirtualCameraRotationDegrees);
    }
}
