package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzhe extends ContentObserver {
    zzhe(zzhc zzhc, Handler handler) {
        super((Handler) null);
    }

    public final void onChange(boolean z) {
        zzhj.zzc();
    }
}
