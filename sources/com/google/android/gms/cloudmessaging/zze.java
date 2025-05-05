package com.google.android.gms.cloudmessaging;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
public final /* synthetic */ class zze implements Executor {
    public static final /* synthetic */ zze zza = new zze();

    private /* synthetic */ zze() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
