package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.settings.SettingsProvider;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CrashlyticsCore$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ CrashlyticsCore f$0;
    public final /* synthetic */ SettingsProvider f$1;

    public /* synthetic */ CrashlyticsCore$$ExternalSyntheticLambda1(CrashlyticsCore crashlyticsCore, SettingsProvider settingsProvider) {
        this.f$0 = crashlyticsCore;
        this.f$1 = settingsProvider;
    }

    public final void run() {
        this.f$0.m600lambda$doBackgroundInitializationAsync$0$comgooglefirebasecrashlyticsinternalcommonCrashlyticsCore(this.f$1);
    }
}
