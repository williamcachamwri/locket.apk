package com.facebook.react.bridge;

import javax.annotation.Nonnull;

public interface NativeModule {

    public interface NativeMethod {
        String getType();

        void invoke(JSInstance jSInstance, ReadableArray readableArray);
    }

    boolean canOverrideExistingModule();

    @Nonnull
    String getName();

    void initialize();

    void invalidate();

    @Deprecated
    void onCatalystInstanceDestroy();
}
