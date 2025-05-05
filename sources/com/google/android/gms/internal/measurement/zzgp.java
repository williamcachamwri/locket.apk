package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzgp extends ContentObserver {
    private final /* synthetic */ zzgn zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzgp(zzgn zzgn, Handler handler) {
        super((Handler) null);
        this.zza = zzgn;
    }

    public final void onChange(boolean z) {
        this.zza.zza.set(true);
    }
}
