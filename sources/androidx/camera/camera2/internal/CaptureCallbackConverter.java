package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureCallbacks;
import java.util.ArrayList;
import java.util.List;

final class CaptureCallbackConverter {
    private CaptureCallbackConverter() {
    }

    static CameraCaptureSession.CaptureCallback toCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
        if (cameraCaptureCallback == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        toCaptureCallback(cameraCaptureCallback, arrayList);
        if (arrayList.size() == 1) {
            return (CameraCaptureSession.CaptureCallback) arrayList.get(0);
        }
        return Camera2CaptureCallbacks.createComboCallback((List<CameraCaptureSession.CaptureCallback>) arrayList);
    }

    static void toCaptureCallback(CameraCaptureCallback cameraCaptureCallback, List<CameraCaptureSession.CaptureCallback> list) {
        if (cameraCaptureCallback instanceof CameraCaptureCallbacks.ComboCameraCaptureCallback) {
            for (CameraCaptureCallback captureCallback : ((CameraCaptureCallbacks.ComboCameraCaptureCallback) cameraCaptureCallback).getCallbacks()) {
                toCaptureCallback(captureCallback, list);
            }
        } else if (cameraCaptureCallback instanceof CaptureCallbackContainer) {
            list.add(((CaptureCallbackContainer) cameraCaptureCallback).getCaptureCallback());
        } else {
            list.add(new CaptureCallbackAdapter(cameraCaptureCallback));
        }
    }
}
