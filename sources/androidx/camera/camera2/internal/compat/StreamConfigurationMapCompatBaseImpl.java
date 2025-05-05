package androidx.camera.camera2.internal.compat;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Size;
import androidx.camera.camera2.internal.compat.StreamConfigurationMapCompat;
import androidx.camera.core.Logger;

class StreamConfigurationMapCompatBaseImpl implements StreamConfigurationMapCompat.StreamConfigurationMapCompatImpl {
    private static final String TAG = "StreamConfigurationMapCompatBaseImpl";
    final StreamConfigurationMap mStreamConfigurationMap;

    StreamConfigurationMapCompatBaseImpl(StreamConfigurationMap streamConfigurationMap) {
        this.mStreamConfigurationMap = streamConfigurationMap;
    }

    public int[] getOutputFormats() {
        try {
            return this.mStreamConfigurationMap.getOutputFormats();
        } catch (IllegalArgumentException | NullPointerException e) {
            Logger.w(TAG, "Failed to get output formats from StreamConfigurationMap", e);
            return null;
        }
    }

    public Size[] getOutputSizes(int i) {
        if (i == 34) {
            return this.mStreamConfigurationMap.getOutputSizes(SurfaceTexture.class);
        }
        return this.mStreamConfigurationMap.getOutputSizes(i);
    }

    public <T> Size[] getOutputSizes(Class<T> cls) {
        return this.mStreamConfigurationMap.getOutputSizes(cls);
    }

    public Size[] getHighResolutionOutputSizes(int i) {
        return Api23Impl.getHighResolutionOutputSizes(this.mStreamConfigurationMap, i);
    }

    public StreamConfigurationMap unwrap() {
        return this.mStreamConfigurationMap;
    }

    static class Api23Impl {
        private Api23Impl() {
        }

        static Size[] getHighResolutionOutputSizes(StreamConfigurationMap streamConfigurationMap, int i) {
            return streamConfigurationMap.getHighResolutionOutputSizes(i);
        }
    }
}
