package com.google.android.gms.internal.common;

import android.os.Handler;
import android.os.Looper;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class zzi extends Handler {
    private final Looper zza = Looper.getMainLooper();

    public zzi() {
    }

    public zzi(Looper looper) {
        super(looper);
    }

    public zzi(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }
}
