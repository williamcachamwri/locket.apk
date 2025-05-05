package com.google.firebase.functions;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.security.ProviderInstaller;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirebaseFunctions$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ Context f$0;

    public /* synthetic */ FirebaseFunctions$$ExternalSyntheticLambda4(Context context) {
        this.f$0 = context;
    }

    public final void run() {
        ProviderInstaller.installIfNeededAsync(this.f$0, new ProviderInstaller.ProviderInstallListener() {
            public void onProviderInstalled() {
                FirebaseFunctions.providerInstalled.setResult(null);
            }

            public void onProviderInstallFailed(int i, Intent intent) {
                Log.d("FirebaseFunctions", "Failed to update ssl context");
                FirebaseFunctions.providerInstalled.setResult(null);
            }
        });
    }
}
