package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Uploader$$ExternalSyntheticLambda8 implements SynchronizationGuard.CriticalSection {
    public final /* synthetic */ Uploader f$0;
    public final /* synthetic */ Iterable f$1;

    public /* synthetic */ Uploader$$ExternalSyntheticLambda8(Uploader uploader, Iterable iterable) {
        this.f$0 = uploader;
        this.f$1 = iterable;
    }

    public final Object execute() {
        return this.f$0.m2103lambda$logAndUpdateState$5$comgoogleandroiddatatransportruntimeschedulingjobschedulingUploader(this.f$1);
    }
}
