package androidx.camera.video.impl;

import androidx.arch.core.util.Function;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.internal.ThreadConfig;
import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoOutput;
import androidx.camera.video.internal.encoder.VideoEncoderConfig;
import androidx.camera.video.internal.encoder.VideoEncoderInfo;
import androidx.core.util.Preconditions;
import java.util.Objects;

public final class VideoCaptureConfig<T extends VideoOutput> implements UseCaseConfig<VideoCapture<T>>, ImageOutputConfig, ThreadConfig {
    public static final Config.Option<Boolean> OPTION_FORCE_ENABLE_SURFACE_PROCESSING = Config.Option.create("camerax.video.VideoCapture.forceEnableSurfaceProcessing", Boolean.class);
    public static final Config.Option<Function<VideoEncoderConfig, VideoEncoderInfo>> OPTION_VIDEO_ENCODER_INFO_FINDER = Config.Option.create("camerax.video.VideoCapture.videoEncoderInfoFinder", Function.class);
    public static final Config.Option<VideoOutput> OPTION_VIDEO_OUTPUT = Config.Option.create("camerax.video.VideoCapture.videoOutput", VideoOutput.class);
    private final OptionsBundle mConfig;

    public int getInputFormat() {
        return 34;
    }

    public VideoCaptureConfig(OptionsBundle optionsBundle) {
        Preconditions.checkArgument(optionsBundle.containsOption(OPTION_VIDEO_OUTPUT));
        this.mConfig = optionsBundle;
    }

    public T getVideoOutput() {
        return (VideoOutput) Objects.requireNonNull((VideoOutput) retrieveOption(OPTION_VIDEO_OUTPUT));
    }

    public Function<VideoEncoderConfig, VideoEncoderInfo> getVideoEncoderInfoFinder() {
        return (Function) Objects.requireNonNull((Function) retrieveOption(OPTION_VIDEO_ENCODER_INFO_FINDER));
    }

    public boolean isSurfaceProcessingForceEnabled() {
        return ((Boolean) Objects.requireNonNull((Boolean) retrieveOption(OPTION_FORCE_ENABLE_SURFACE_PROCESSING, false))).booleanValue();
    }

    public Config getConfig() {
        return this.mConfig;
    }
}
