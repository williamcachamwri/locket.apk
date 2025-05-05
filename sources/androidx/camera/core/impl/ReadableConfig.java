package androidx.camera.core.impl;

import androidx.camera.core.impl.Config;
import java.util.Set;

public interface ReadableConfig extends Config {
    Config getConfig();

    boolean containsOption(Config.Option<?> option) {
        return getConfig().containsOption(option);
    }

    <ValueT> ValueT retrieveOption(Config.Option<ValueT> option) {
        return getConfig().retrieveOption(option);
    }

    <ValueT> ValueT retrieveOption(Config.Option<ValueT> option, ValueT valuet) {
        return getConfig().retrieveOption(option, valuet);
    }

    void findOptions(String str, Config.OptionMatcher optionMatcher) {
        getConfig().findOptions(str, optionMatcher);
    }

    Set<Config.Option<?>> listOptions() {
        return getConfig().listOptions();
    }

    <ValueT> ValueT retrieveOptionWithPriority(Config.Option<ValueT> option, Config.OptionPriority optionPriority) {
        return getConfig().retrieveOptionWithPriority(option, optionPriority);
    }

    Config.OptionPriority getOptionPriority(Config.Option<?> option) {
        return getConfig().getOptionPriority(option);
    }

    Set<Config.OptionPriority> getPriorities(Config.Option<?> option) {
        return getConfig().getPriorities(option);
    }
}
