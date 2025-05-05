package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface CameraConfig extends ReadableConfig {
    public static final Config.Option<Boolean> OPTION_CAPTURE_PROCESS_PROGRESS_SUPPORTED = Config.Option.create("camerax.core.camera.isCaptureProcessProgressSupported", Boolean.class);
    public static final Config.Option<Identifier> OPTION_COMPATIBILITY_ID = Config.Option.create("camerax.core.camera.compatibilityId", Identifier.class);
    public static final Config.Option<Boolean> OPTION_POSTVIEW_SUPPORTED = Config.Option.create("camerax.core.camera.isPostviewSupported", Boolean.class);
    public static final Config.Option<SessionProcessor> OPTION_SESSION_PROCESSOR = Config.Option.create("camerax.core.camera.SessionProcessor", SessionProcessor.class);
    public static final Config.Option<UseCaseConfigFactory> OPTION_USECASE_CONFIG_FACTORY = Config.Option.create("camerax.core.camera.useCaseConfigFactory", UseCaseConfigFactory.class);
    public static final Config.Option<Integer> OPTION_USE_CASE_COMBINATION_REQUIRED_RULE = Config.Option.create("camerax.core.camera.useCaseCombinationRequiredRule", Integer.class);
    public static final Config.Option<Boolean> OPTION_ZSL_DISABLED = Config.Option.create("camerax.core.camera.isZslDisabled", Boolean.class);
    public static final int REQUIRED_RULE_COEXISTING_PREVIEW_AND_IMAGE_CAPTURE = 1;
    public static final int REQUIRED_RULE_NONE = 0;

    public interface Builder<B> {
        B setCaptureProcessProgressSupported(boolean z);

        B setCompatibilityId(Identifier identifier);

        B setPostviewSupported(boolean z);

        B setSessionProcessor(SessionProcessor sessionProcessor);

        B setUseCaseCombinationRequiredRule(int i);

        B setUseCaseConfigFactory(UseCaseConfigFactory useCaseConfigFactory);

        B setZslDisabled(boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RequiredRule {
    }

    Identifier getCompatibilityId();

    UseCaseConfigFactory getUseCaseConfigFactory() {
        return (UseCaseConfigFactory) retrieveOption(OPTION_USECASE_CONFIG_FACTORY, UseCaseConfigFactory.EMPTY_INSTANCE);
    }

    int getUseCaseCombinationRequiredRule() {
        return ((Integer) retrieveOption(OPTION_USE_CASE_COMBINATION_REQUIRED_RULE, 0)).intValue();
    }

    SessionProcessor getSessionProcessor(SessionProcessor sessionProcessor) {
        return (SessionProcessor) retrieveOption(OPTION_SESSION_PROCESSOR, sessionProcessor);
    }

    boolean isPostviewSupported() {
        return ((Boolean) retrieveOption(OPTION_POSTVIEW_SUPPORTED, false)).booleanValue();
    }

    boolean isCaptureProcessProgressSupported() {
        return ((Boolean) retrieveOption(OPTION_CAPTURE_PROCESS_PROGRESS_SUPPORTED, false)).booleanValue();
    }

    SessionProcessor getSessionProcessor() {
        return (SessionProcessor) retrieveOption(OPTION_SESSION_PROCESSOR);
    }
}
