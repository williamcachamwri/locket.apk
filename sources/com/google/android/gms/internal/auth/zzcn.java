package com.google.android.gms.internal.auth;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzcn extends ContentObserver {
    zzcn(zzco zzco, Handler handler) {
        super((Handler) null);
    }

    public final void onChange(boolean z) {
        zzdc.zzc();
    }
}
