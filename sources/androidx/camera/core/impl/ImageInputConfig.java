package androidx.camera.core.impl;

import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.Config;
import androidx.core.util.Preconditions;

public interface ImageInputConfig extends ReadableConfig {
    public static final Config.Option<DynamicRange> OPTION_INPUT_DYNAMIC_RANGE = Config.Option.create("camerax.core.imageInput.inputDynamicRange", DynamicRange.class);
    public static final Config.Option<Integer> OPTION_INPUT_FORMAT = Config.Option.create("camerax.core.imageInput.inputFormat", Integer.TYPE);
    public static final Config.Option<Integer> OPTION_SECONDARY_INPUT_FORMAT = Config.Option.create("camerax.core.imageInput.secondaryInputFormat", Integer.TYPE);

    public interface Builder<B> {
        B setDynamicRange(DynamicRange dynamicRange);
    }

    int getInputFormat() {
        return ((Integer) retrieveOption(OPTION_INPUT_FORMAT)).intValue();
    }

    int getSecondaryInputFormat() {
        return ((Integer) retrieveOption(OPTION_SECONDARY_INPUT_FORMAT, 0)).intValue();
    }

    DynamicRange getDynamicRange() {
        return (DynamicRange) Preconditions.checkNotNull((DynamicRange) retrieveOption(OPTION_INPUT_DYNAMIC_RANGE, DynamicRange.UNSPECIFIED));
    }

    boolean hasDynamicRange() {
        return containsOption(OPTION_INPUT_DYNAMIC_RANGE);
    }
}
