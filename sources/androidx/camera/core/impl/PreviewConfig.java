package androidx.camera.core.impl;

import androidx.camera.core.Preview;
import androidx.camera.core.internal.ThreadConfig;

public final class PreviewConfig implements UseCaseConfig<Preview>, ImageOutputConfig, ThreadConfig {
    private final OptionsBundle mConfig;

    public PreviewConfig(OptionsBundle optionsBundle) {
        this.mConfig = optionsBundle;
    }

    public Config getConfig() {
        return this.mConfig;
    }

    public int getInputFormat() {
        return ((Integer) retrieveOption(OPTION_INPUT_FORMAT)).intValue();
    }
}
