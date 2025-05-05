package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzck {
    private boolean zza;

    /* access modifiers changed from: package-private */
    public final void zza(Context context) {
        zzdp.zzb(context, "Application Context cannot be null");
        if (!this.zza) {
            this.zza = true;
            zzcz.zzb().zzd(context);
            zzcq.zza().zzd(context);
            zzdk.zzb(context);
            zzdl.zzd(context);
            zzdo.zza(context);
            zzcw.zzb().zzc(context);
            zzcp.zza().zzd(context);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb() {
        return this.zza;
    }
}
