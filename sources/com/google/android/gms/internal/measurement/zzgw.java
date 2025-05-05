package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzgw extends ContentObserver {
    private final /* synthetic */ zzgu zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzgw(zzgu zzgu, Handler handler) {
        super((Handler) null);
        this.zza = zzgu;
    }

    public final void onChange(boolean z) {
        this.zza.zzd();
    }
}
