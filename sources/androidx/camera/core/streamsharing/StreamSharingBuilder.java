package androidx.camera.core.streamsharing;

import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.TargetConfig;
import java.util.UUID;

class StreamSharingBuilder implements UseCaseConfig.Builder<StreamSharing, StreamSharingConfig, StreamSharingBuilder> {
    private static final String UNSUPPORTED_MESSAGE = "Operation not supported by StreamSharingBuilder.";
    private final MutableOptionsBundle mMutableConfig;

    StreamSharingBuilder() {
        this(MutableOptionsBundle.create());
    }

    StreamSharingBuilder(MutableOptionsBundle mutableOptionsBundle) {
        this.mMutableConfig = mutableOptionsBundle;
        Class cls = (Class) mutableOptionsBundle.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
        if (cls == null || cls.equals(StreamSharing.class)) {
            setCaptureType(UseCaseConfigFactory.CaptureType.STREAM_SHARING);
            setTargetClass((Class<StreamSharing>) StreamSharing.class);
            return;
        }
        throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
    }

    public MutableConfig getMutableConfig() {
        return this.mMutableConfig;
    }

    public StreamSharing build() {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    public StreamSharingBuilder setDefaultSessionConfig(SessionConfig sessionConfig) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    public StreamSharingBuilder setDefaultCaptureConfig(CaptureConfig captureConfig) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    public StreamSharingBuilder setSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    public StreamSharingBuilder setCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    public StreamSharingBuilder setSurfaceOccupancyPriority(int i) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    public StreamSharingBuilder setZslDisabled(boolean z) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    public StreamSharingBuilder setHighResolutionDisabled(boolean z) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    public StreamSharingConfig getUseCaseConfig() {
        return new StreamSharingConfig(OptionsBundle.from(this.mMutableConfig));
    }

    public StreamSharingBuilder setTargetClass(Class<StreamSharing> cls) {
        getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_CLASS, cls);
        if (getMutableConfig().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) == null) {
            setTargetName(cls.getCanonicalName() + "-" + UUID.randomUUID());
        }
        return this;
    }

    public StreamSharingBuilder setTargetName(String str) {
        getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_NAME, str);
        return this;
    }

    public StreamSharingBuilder setCaptureType(UseCaseConfigFactory.CaptureType captureType) {
        getMutableConfig().insertOption(UseCaseConfig.OPTION_CAPTURE_TYPE, captureType);
        return this;
    }
}
