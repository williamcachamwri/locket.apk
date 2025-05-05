package com.google.firebase.appcheck.internal;

import android.content.Context;
import com.google.firebase.inject.Provider;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class StorageHelper$$ExternalSyntheticLambda0 implements Provider {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ StorageHelper$$ExternalSyntheticLambda0(Context context, String str) {
        this.f$0 = context;
        this.f$1 = str;
    }

    public final Object get() {
        return this.f$0.getSharedPreferences(this.f$1, 0);
    }
}
