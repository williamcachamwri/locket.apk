package androidx.camera.video.internal.compat.quirk;

import androidx.camera.core.Logger;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.QuirkSettings;
import androidx.camera.core.impl.QuirkSettingsHolder;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import java.util.List;

public class DeviceQuirks {
    private static final String TAG = "DeviceQuirks";
    private static volatile Quirks sQuirks;

    static {
        QuirkSettingsHolder.instance().observe(CameraXExecutors.directExecutor(), new DeviceQuirks$$ExternalSyntheticLambda0());
    }

    static /* synthetic */ void lambda$static$0(QuirkSettings quirkSettings) {
        sQuirks = new Quirks(DeviceQuirksLoader.loadQuirks(quirkSettings));
        Logger.d(TAG, "video DeviceQuirks = " + Quirks.toString(sQuirks));
    }

    private DeviceQuirks() {
    }

    public static Quirks getAll() {
        return sQuirks;
    }

    public static <T extends Quirk> T get(Class<T> cls) {
        return sQuirks.get(cls);
    }

    public static <T extends Quirk> List<T> getAll(Class<T> cls) {
        return sQuirks.getAll(cls);
    }
}
