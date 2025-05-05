package com.google.android.recaptcha.internal;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzbx implements Executor {
    public static final zzbx zza = new zzbx();

    private zzbx() {
    }

    public final void execute(Runnable runnable) {
        runnable.run();
    }
}
