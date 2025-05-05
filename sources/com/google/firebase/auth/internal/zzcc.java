package com.google.firebase.auth.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzcc implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ zzbz zza;

    zzcc(zzbz zzbz) {
        this.zza = zzbz;
    }

    public final void onBackgroundStateChanged(boolean z) {
        if (z) {
            this.zza.zzc = true;
            this.zza.zza();
            return;
        }
        this.zza.zzc = false;
        if (this.zza.zzb()) {
            this.zza.zzb.zzc();
        }
    }
}
