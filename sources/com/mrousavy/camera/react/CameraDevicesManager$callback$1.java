package com.mrousavy.camera.react;

import android.hardware.camera2.CameraManager;
import android.util.Log;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0004H\u0016R\u001c\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"com/mrousavy/camera/react/CameraDevicesManager$callback$1", "Landroid/hardware/camera2/CameraManager$AvailabilityCallback;", "deviceIds", "", "", "kotlin.jvm.PlatformType", "isDeviceConnected", "", "cameraId", "onCameraAvailable", "", "onCameraUnavailable", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraDevicesManager.kt */
public final class CameraDevicesManager$callback$1 extends CameraManager.AvailabilityCallback {
    private List<String> deviceIds;
    final /* synthetic */ CameraDevicesManager this$0;

    CameraDevicesManager$callback$1(CameraDevicesManager cameraDevicesManager) {
        this.this$0 = cameraDevicesManager;
        String[] cameraIdList = cameraDevicesManager.cameraManager.getCameraIdList();
        Intrinsics.checkNotNullExpressionValue(cameraIdList, "getCameraIdList(...)");
        this.deviceIds = ArraysKt.toMutableList((T[]) (Object[]) cameraIdList);
    }

    private final boolean isDeviceConnected(String str) {
        try {
            this.this$0.cameraManager.getCameraCharacteristics(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public void onCameraAvailable(String str) {
        Intrinsics.checkNotNullParameter(str, "cameraId");
        Log.i("CameraDevices", "Camera #" + str + " is now available.");
        if (!this.deviceIds.contains(str)) {
            this.deviceIds.add(str);
            this.this$0.sendAvailableDevicesChangedEvent();
        }
    }

    public void onCameraUnavailable(String str) {
        Intrinsics.checkNotNullParameter(str, "cameraId");
        Log.i("CameraDevices", "Camera #" + str + " is now unavailable.");
        if (this.deviceIds.contains(str) && !isDeviceConnected(str)) {
            this.deviceIds.remove(str);
            this.this$0.sendAvailableDevicesChangedEvent();
        }
    }
}
