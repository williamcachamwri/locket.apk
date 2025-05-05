package com.google.firebase.crashlytics.internal.metadata;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UserMetadata$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ UserMetadata f$0;

    public /* synthetic */ UserMetadata$$ExternalSyntheticLambda0(UserMetadata userMetadata) {
        this.f$0 = userMetadata;
    }

    public final void run() {
        this.f$0.serializeUserDataIfNeeded();
    }
}
