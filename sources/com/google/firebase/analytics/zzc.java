package com.google.firebase.analytics;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement-api@@22.1.2 */
final class zzc implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zza;

    public final /* synthetic */ Object call() throws Exception {
        return this.zza.zzb.zze();
    }

    zzc(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }
}
