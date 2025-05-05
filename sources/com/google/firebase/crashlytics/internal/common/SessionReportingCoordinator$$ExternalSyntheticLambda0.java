package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SessionReportingCoordinator$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SessionReportingCoordinator f$0;
    public final /* synthetic */ CrashlyticsReport.Session.Event f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ SessionReportingCoordinator$$ExternalSyntheticLambda0(SessionReportingCoordinator sessionReportingCoordinator, CrashlyticsReport.Session.Event event, String str, boolean z) {
        this.f$0 = sessionReportingCoordinator;
        this.f$1 = event;
        this.f$2 = str;
        this.f$3 = z;
    }

    public final void run() {
        this.f$0.m611lambda$persistEvent$0$comgooglefirebasecrashlyticsinternalcommonSessionReportingCoordinator(this.f$1, this.f$2, this.f$3);
    }
}
