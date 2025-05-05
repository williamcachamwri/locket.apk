package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.internal.mlkit_common.zzsh;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final /* synthetic */ class zze implements Runnable {
    public final /* synthetic */ CloseGuard zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ zzsh zzc;
    public final /* synthetic */ Runnable zzd;

    public /* synthetic */ zze(CloseGuard closeGuard, int i, zzsh zzsh, Runnable runnable) {
        this.zza = closeGuard;
        this.zzb = i;
        this.zzc = zzsh;
        this.zzd = runnable;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd);
    }
}
