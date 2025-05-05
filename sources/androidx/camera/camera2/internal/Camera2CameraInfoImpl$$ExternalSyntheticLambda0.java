package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.camera2.internal.compat.workaround.CameraCharacteristicsProvider;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Camera2CameraInfoImpl$$ExternalSyntheticLambda0 implements CameraCharacteristicsProvider {
    public final /* synthetic */ CameraCharacteristicsCompat f$0;

    public /* synthetic */ Camera2CameraInfoImpl$$ExternalSyntheticLambda0(CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        this.f$0 = cameraCharacteristicsCompat;
    }

    public final Object get(CameraCharacteristics.Key key) {
        return this.f$0.get(key);
    }
}
