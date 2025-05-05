package androidx.camera.camera2.internal.compat;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Size;

class StreamConfigurationMapCompatApi23Impl extends StreamConfigurationMapCompatBaseImpl {
    StreamConfigurationMapCompatApi23Impl(StreamConfigurationMap streamConfigurationMap) {
        super(streamConfigurationMap);
    }

    public Size[] getOutputSizes(int i) {
        return this.mStreamConfigurationMap.getOutputSizes(i);
    }
}
