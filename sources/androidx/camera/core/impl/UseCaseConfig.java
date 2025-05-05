package androidx.camera.core.impl;

import android.util.Range;
import androidx.camera.core.ExtendableBuilder;
import androidx.camera.core.UseCase;
import androidx.camera.core.imagecapture.ImageCaptureControl;
import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.camera.core.imagecapture.TakePictureManagerImpl;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.TargetConfig;
import java.util.Objects;

public interface UseCaseConfig<T extends UseCase> extends TargetConfig<T>, ImageInputConfig {
    public static final Config.Option<CaptureConfig.OptionUnpacker> OPTION_CAPTURE_CONFIG_UNPACKER = Config.Option.create("camerax.core.useCase.captureConfigUnpacker", CaptureConfig.OptionUnpacker.class);
    public static final Config.Option<UseCaseConfigFactory.CaptureType> OPTION_CAPTURE_TYPE = Config.Option.create("camerax.core.useCase.captureType", UseCaseConfigFactory.CaptureType.class);
    public static final Config.Option<CaptureConfig> OPTION_DEFAULT_CAPTURE_CONFIG = Config.Option.create("camerax.core.useCase.defaultCaptureConfig", CaptureConfig.class);
    public static final Config.Option<SessionConfig> OPTION_DEFAULT_SESSION_CONFIG = Config.Option.create("camerax.core.useCase.defaultSessionConfig", SessionConfig.class);
    public static final Config.Option<Boolean> OPTION_HIGH_RESOLUTION_DISABLED = Config.Option.create("camerax.core.useCase.highResolutionDisabled", Boolean.TYPE);
    public static final Config.Option<Integer> OPTION_PREVIEW_STABILIZATION_MODE = Config.Option.create("camerax.core.useCase.previewStabilizationMode", Integer.TYPE);
    public static final Config.Option<SessionConfig.OptionUnpacker> OPTION_SESSION_CONFIG_UNPACKER = Config.Option.create("camerax.core.useCase.sessionConfigUnpacker", SessionConfig.OptionUnpacker.class);
    public static final Config.Option<Integer> OPTION_SURFACE_OCCUPANCY_PRIORITY = Config.Option.create("camerax.core.useCase.surfaceOccupancyPriority", Integer.TYPE);
    public static final Config.Option<TakePictureManager.Provider> OPTION_TAKE_PICTURE_MANAGER_PROVIDER = Config.Option.create("camerax.core.useCase.takePictureManagerProvider", TakePictureManager.Provider.class);
    public static final Config.Option<Range<Integer>> OPTION_TARGET_FRAME_RATE = Config.Option.create("camerax.core.useCase.targetFrameRate", Range.class);
    public static final Config.Option<Integer> OPTION_VIDEO_STABILIZATION_MODE = Config.Option.create("camerax.core.useCase.videoStabilizationMode", Integer.TYPE);
    public static final Config.Option<Boolean> OPTION_ZSL_DISABLED = Config.Option.create("camerax.core.useCase.zslDisabled", Boolean.TYPE);

    public interface Builder<T extends UseCase, C extends UseCaseConfig<T>, B> extends TargetConfig.Builder<T, B>, ExtendableBuilder<T> {
        C getUseCaseConfig();

        B setCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker);

        B setCaptureType(UseCaseConfigFactory.CaptureType captureType);

        B setDefaultCaptureConfig(CaptureConfig captureConfig);

        B setDefaultSessionConfig(SessionConfig sessionConfig);

        B setHighResolutionDisabled(boolean z);

        B setSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker);

        B setSurfaceOccupancyPriority(int i);

        B setZslDisabled(boolean z);
    }

    SessionConfig getDefaultSessionConfig(SessionConfig sessionConfig) {
        return (SessionConfig) retrieveOption(OPTION_DEFAULT_SESSION_CONFIG, sessionConfig);
    }

    SessionConfig getDefaultSessionConfig() {
        return (SessionConfig) retrieveOption(OPTION_DEFAULT_SESSION_CONFIG);
    }

    CaptureConfig getDefaultCaptureConfig(CaptureConfig captureConfig) {
        return (CaptureConfig) retrieveOption(OPTION_DEFAULT_CAPTURE_CONFIG, captureConfig);
    }

    CaptureConfig getDefaultCaptureConfig() {
        return (CaptureConfig) retrieveOption(OPTION_DEFAULT_CAPTURE_CONFIG);
    }

    SessionConfig.OptionUnpacker getSessionOptionUnpacker(SessionConfig.OptionUnpacker optionUnpacker) {
        return (SessionConfig.OptionUnpacker) retrieveOption(OPTION_SESSION_CONFIG_UNPACKER, optionUnpacker);
    }

    SessionConfig.OptionUnpacker getSessionOptionUnpacker() {
        return (SessionConfig.OptionUnpacker) retrieveOption(OPTION_SESSION_CONFIG_UNPACKER);
    }

    CaptureConfig.OptionUnpacker getCaptureOptionUnpacker(CaptureConfig.OptionUnpacker optionUnpacker) {
        return (CaptureConfig.OptionUnpacker) retrieveOption(OPTION_CAPTURE_CONFIG_UNPACKER, optionUnpacker);
    }

    CaptureConfig.OptionUnpacker getCaptureOptionUnpacker() {
        return (CaptureConfig.OptionUnpacker) retrieveOption(OPTION_CAPTURE_CONFIG_UNPACKER);
    }

    int getSurfaceOccupancyPriority(int i) {
        return ((Integer) retrieveOption(OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(i))).intValue();
    }

    int getSurfaceOccupancyPriority() {
        return ((Integer) retrieveOption(OPTION_SURFACE_OCCUPANCY_PRIORITY)).intValue();
    }

    Range<Integer> getTargetFrameRate(Range<Integer> range) {
        return (Range) retrieveOption(OPTION_TARGET_FRAME_RATE, range);
    }

    Range<Integer> getTargetFrameRate() {
        return (Range) retrieveOption(OPTION_TARGET_FRAME_RATE);
    }

    boolean isZslDisabled(boolean z) {
        return ((Boolean) retrieveOption(OPTION_ZSL_DISABLED, Boolean.valueOf(z))).booleanValue();
    }

    boolean isHighResolutionDisabled(boolean z) {
        return ((Boolean) retrieveOption(OPTION_HIGH_RESOLUTION_DISABLED, Boolean.valueOf(z))).booleanValue();
    }

    UseCaseConfigFactory.CaptureType getCaptureType() {
        return (UseCaseConfigFactory.CaptureType) retrieveOption(OPTION_CAPTURE_TYPE);
    }

    int getPreviewStabilizationMode() {
        return ((Integer) retrieveOption(OPTION_PREVIEW_STABILIZATION_MODE, 0)).intValue();
    }

    int getVideoStabilizationMode() {
        return ((Integer) retrieveOption(OPTION_VIDEO_STABILIZATION_MODE, 0)).intValue();
    }

    TakePictureManager.Provider getTakePictureManagerProvider() {
        return (TakePictureManager.Provider) Objects.requireNonNull((TakePictureManager.Provider) retrieveOption(OPTION_TAKE_PICTURE_MANAGER_PROVIDER, new TakePictureManager.Provider() {
            public TakePictureManager newInstance(ImageCaptureControl imageCaptureControl) {
                return new TakePictureManagerImpl(imageCaptureControl);
            }
        }));
    }
}
