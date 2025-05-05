package com.google.android.play.core.install;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class NativeInstallStateUpdateListener implements InstallStateUpdatedListener {
    NativeInstallStateUpdateListener() {
    }

    public native void onStateUpdate(InstallState installState);
}
