package androidx.camera.core;

import android.os.Handler;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraFactory;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.QuirkSettings;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.TargetConfig;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;

public final class CameraXConfig implements TargetConfig<CameraX> {
    static final long DEFAULT_OPTION_CAMERA_OPEN_RETRY_MAX_TIMEOUT_IN_MILLIS_WHILE_RESUMING = -1;
    static final Config.Option<CameraSelector> OPTION_AVAILABLE_CAMERAS_LIMITER = Config.Option.create("camerax.core.appConfig.availableCamerasLimiter", CameraSelector.class);
    static final Config.Option<Executor> OPTION_CAMERA_EXECUTOR = Config.Option.create("camerax.core.appConfig.cameraExecutor", Executor.class);
    static final Config.Option<CameraFactory.Provider> OPTION_CAMERA_FACTORY_PROVIDER = Config.Option.create("camerax.core.appConfig.cameraFactoryProvider", CameraFactory.Provider.class);
    static final Config.Option<Long> OPTION_CAMERA_OPEN_RETRY_MAX_TIMEOUT_IN_MILLIS_WHILE_RESUMING = Config.Option.create("camerax.core.appConfig.cameraOpenRetryMaxTimeoutInMillisWhileResuming", Long.TYPE);
    static final Config.Option<RetryPolicy> OPTION_CAMERA_PROVIDER_INIT_RETRY_POLICY = Config.Option.create("camerax.core.appConfig.cameraProviderInitRetryPolicy", RetryPolicy.class);
    static final Config.Option<CameraDeviceSurfaceManager.Provider> OPTION_DEVICE_SURFACE_MANAGER_PROVIDER = Config.Option.create("camerax.core.appConfig.deviceSurfaceManagerProvider", CameraDeviceSurfaceManager.Provider.class);
    static final Config.Option<Integer> OPTION_MIN_LOGGING_LEVEL = Config.Option.create("camerax.core.appConfig.minimumLoggingLevel", Integer.TYPE);
    static final Config.Option<QuirkSettings> OPTION_QUIRK_SETTINGS = Config.Option.create("camerax.core.appConfig.quirksSettings", QuirkSettings.class);
    static final Config.Option<Handler> OPTION_SCHEDULER_HANDLER = Config.Option.create("camerax.core.appConfig.schedulerHandler", Handler.class);
    static final Config.Option<UseCaseConfigFactory.Provider> OPTION_USECASE_CONFIG_FACTORY_PROVIDER = Config.Option.create("camerax.core.appConfig.useCaseConfigFactoryProvider", UseCaseConfigFactory.Provider.class);
    private final OptionsBundle mConfig;

    public interface Provider {
        CameraXConfig getCameraXConfig();
    }

    CameraXConfig(OptionsBundle optionsBundle) {
        this.mConfig = optionsBundle;
    }

    public CameraFactory.Provider getCameraFactoryProvider(CameraFactory.Provider provider) {
        return (CameraFactory.Provider) this.mConfig.retrieveOption(OPTION_CAMERA_FACTORY_PROVIDER, provider);
    }

    public CameraDeviceSurfaceManager.Provider getDeviceSurfaceManagerProvider(CameraDeviceSurfaceManager.Provider provider) {
        return (CameraDeviceSurfaceManager.Provider) this.mConfig.retrieveOption(OPTION_DEVICE_SURFACE_MANAGER_PROVIDER, provider);
    }

    public UseCaseConfigFactory.Provider getUseCaseConfigFactoryProvider(UseCaseConfigFactory.Provider provider) {
        return (UseCaseConfigFactory.Provider) this.mConfig.retrieveOption(OPTION_USECASE_CONFIG_FACTORY_PROVIDER, provider);
    }

    public Executor getCameraExecutor(Executor executor) {
        return (Executor) this.mConfig.retrieveOption(OPTION_CAMERA_EXECUTOR, executor);
    }

    public Handler getSchedulerHandler(Handler handler) {
        return (Handler) this.mConfig.retrieveOption(OPTION_SCHEDULER_HANDLER, handler);
    }

    public int getMinimumLoggingLevel() {
        return ((Integer) this.mConfig.retrieveOption(OPTION_MIN_LOGGING_LEVEL, 3)).intValue();
    }

    public CameraSelector getAvailableCamerasLimiter(CameraSelector cameraSelector) {
        return (CameraSelector) this.mConfig.retrieveOption(OPTION_AVAILABLE_CAMERAS_LIMITER, cameraSelector);
    }

    public long getCameraOpenRetryMaxTimeoutInMillisWhileResuming() {
        return ((Long) this.mConfig.retrieveOption(OPTION_CAMERA_OPEN_RETRY_MAX_TIMEOUT_IN_MILLIS_WHILE_RESUMING, -1L)).longValue();
    }

    public RetryPolicy getCameraProviderInitRetryPolicy() {
        return (RetryPolicy) Objects.requireNonNull((RetryPolicy) this.mConfig.retrieveOption(OPTION_CAMERA_PROVIDER_INIT_RETRY_POLICY, RetryPolicy.DEFAULT));
    }

    public QuirkSettings getQuirkSettings() {
        return (QuirkSettings) this.mConfig.retrieveOption(OPTION_QUIRK_SETTINGS, null);
    }

    public Config getConfig() {
        return this.mConfig;
    }

    public static final class Builder implements TargetConfig.Builder<CameraX, Builder> {
        private final MutableOptionsBundle mMutableConfig;

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        private Builder(MutableOptionsBundle mutableOptionsBundle) {
            this.mMutableConfig = mutableOptionsBundle;
            Class cls = (Class) mutableOptionsBundle.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (cls == null || cls.equals(CameraX.class)) {
                setTargetClass((Class<CameraX>) CameraX.class);
                return;
            }
            throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
        }

        public static Builder fromConfig(CameraXConfig cameraXConfig) {
            return new Builder(MutableOptionsBundle.from(cameraXConfig));
        }

        public Builder setCameraFactoryProvider(CameraFactory.Provider provider) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_CAMERA_FACTORY_PROVIDER, provider);
            return this;
        }

        public Builder setDeviceSurfaceManagerProvider(CameraDeviceSurfaceManager.Provider provider) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_DEVICE_SURFACE_MANAGER_PROVIDER, provider);
            return this;
        }

        public Builder setUseCaseConfigFactoryProvider(UseCaseConfigFactory.Provider provider) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_USECASE_CONFIG_FACTORY_PROVIDER, provider);
            return this;
        }

        public Builder setCameraExecutor(Executor executor) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_CAMERA_EXECUTOR, executor);
            return this;
        }

        public Builder setSchedulerHandler(Handler handler) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_SCHEDULER_HANDLER, handler);
            return this;
        }

        public Builder setMinimumLoggingLevel(int i) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_MIN_LOGGING_LEVEL, Integer.valueOf(i));
            return this;
        }

        public Builder setAvailableCamerasLimiter(CameraSelector cameraSelector) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_AVAILABLE_CAMERAS_LIMITER, cameraSelector);
            return this;
        }

        public Builder setCameraOpenRetryMaxTimeoutInMillisWhileResuming(long j) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_CAMERA_OPEN_RETRY_MAX_TIMEOUT_IN_MILLIS_WHILE_RESUMING, Long.valueOf(j));
            return this;
        }

        public Builder setCameraProviderInitRetryPolicy(RetryPolicy retryPolicy) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_CAMERA_PROVIDER_INIT_RETRY_POLICY, retryPolicy);
            return this;
        }

        public Builder setQuirkSettings(QuirkSettings quirkSettings) {
            getMutableConfig().insertOption(CameraXConfig.OPTION_QUIRK_SETTINGS, quirkSettings);
            return this;
        }

        private MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        public CameraXConfig build() {
            return new CameraXConfig(OptionsBundle.from(this.mMutableConfig));
        }

        public Builder setTargetClass(Class<CameraX> cls) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_CLASS, cls);
            if (getMutableConfig().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) == null) {
                setTargetName(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        public Builder setTargetName(String str) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_NAME, str);
            return this;
        }
    }
}
