package androidx.camera.core.impl;

import android.content.Context;
import androidx.camera.core.InitializationException;

public interface UseCaseConfigFactory {
    public static final UseCaseConfigFactory EMPTY_INSTANCE = new UseCaseConfigFactory() {
        public Config getConfig(CaptureType captureType, int i) {
            return null;
        }
    };

    public enum CaptureType {
        IMAGE_CAPTURE,
        PREVIEW,
        IMAGE_ANALYSIS,
        VIDEO_CAPTURE,
        STREAM_SHARING,
        METERING_REPEATING
    }

    public interface Provider {
        UseCaseConfigFactory newInstance(Context context) throws InitializationException;
    }

    Config getConfig(CaptureType captureType, int i);
}
