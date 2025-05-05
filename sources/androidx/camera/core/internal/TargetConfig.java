package androidx.camera.core.internal;

import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ReadableConfig;

public interface TargetConfig<T> extends ReadableConfig {
    public static final Config.Option<Class<?>> OPTION_TARGET_CLASS = Config.Option.create("camerax.core.target.class", Class.class);
    public static final Config.Option<String> OPTION_TARGET_NAME = Config.Option.create("camerax.core.target.name", String.class);

    public interface Builder<T, B> {
        B setTargetClass(Class<T> cls);

        B setTargetName(String str);
    }

    Class<T> getTargetClass(Class<T> cls) {
        return (Class) retrieveOption(OPTION_TARGET_CLASS, cls);
    }

    Class<T> getTargetClass() {
        return (Class) retrieveOption(OPTION_TARGET_CLASS);
    }

    String getTargetName(String str) {
        return (String) retrieveOption(OPTION_TARGET_NAME, str);
    }

    String getTargetName() {
        return (String) retrieveOption(OPTION_TARGET_NAME);
    }
}
