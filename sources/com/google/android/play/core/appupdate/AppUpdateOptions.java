package com.google.android.play.core.appupdate;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
public abstract class AppUpdateOptions {

    /* compiled from: com.google.android.play:app-update@@2.1.0 */
    public static abstract class Builder {
        public abstract AppUpdateOptions build();

        public abstract Builder setAllowAssetPackDeletion(boolean z);

        public abstract Builder setAppUpdateType(int i);
    }

    public static AppUpdateOptions defaultOptions(int i) {
        return newBuilder(i).build();
    }

    public static Builder newBuilder(int i) {
        zzv zzv = new zzv();
        zzv.setAppUpdateType(i);
        zzv.setAllowAssetPackDeletion(false);
        return zzv;
    }

    public abstract boolean allowAssetPackDeletion();

    public abstract int appUpdateType();
}
