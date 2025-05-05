package com.google.ads.interactivemedia.pal;

import com.google.android.gms.internal.pal.zzjb;
import com.google.android.gms.internal.pal.zzjc;
import java.util.Random;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzx {
    static final boolean zza = (new Random().nextInt(100) <= 0);
    private final zzs zzb;
    private final boolean zzc;

    zzx(zzs zzs, boolean z) {
        this.zzb = zzs;
        this.zzc = z;
    }

    public final void zza(int i) {
        if (this.zzc) {
            this.zzb.zza("pal_native", zzt.ERROR_EVENT.zza(), zzjc.zzd(zzu.ERROR_CODE.zza(), String.valueOf(i)));
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzw zzw) {
        if (this.zzc) {
            zzjb zzjb = new zzjb();
            zzjb.zza(zzu.NONCE_LOADER_INIT_TIME.zza(), String.valueOf(zzw.zzc().zzd()));
            zzjb.zza(zzu.NONCE_REQUESTED_TIME.zza(), String.valueOf(zzw.zzd().zzd()));
            zzjb.zza(zzu.NONCE_LOADED_TIME.zza(), String.valueOf(zzw.zzb().zzd()));
            zzjb.zza(zzu.SERVICE_START_TIME.zza(), String.valueOf(zzw.zzf().zzd()));
            zzjb.zza(zzu.SERVICE_END_TIME.zza(), String.valueOf(zzw.zze().zzd()));
            zzjb.zza(zzu.NONCE_LENGTH.zza(), String.valueOf(zzw.zza()));
            this.zzb.zza("pal_native", zzt.NONCE_LOADED.zza(), zzjb.zzc());
        }
    }
}
