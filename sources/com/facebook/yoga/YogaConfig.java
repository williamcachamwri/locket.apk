package com.facebook.yoga;

public abstract class YogaConfig {
    public static int SPACING_TYPE = 1;

    public abstract YogaErrata getErrata();

    public abstract YogaLogger getLogger();

    /* access modifiers changed from: package-private */
    public abstract long getNativePointer();

    public abstract void setErrata(YogaErrata yogaErrata);

    public abstract void setExperimentalFeatureEnabled(YogaExperimentalFeature yogaExperimentalFeature, boolean z);

    public abstract void setLogger(YogaLogger yogaLogger);

    public abstract void setPointScaleFactor(float f);

    public abstract void setPrintTreeFlag(boolean z);

    public abstract void setUseWebDefaults(boolean z);
}
