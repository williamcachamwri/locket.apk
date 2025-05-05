package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzcw {
    private static final zzcw zza = new zzcw();
    private Context zzb;

    private zzcw() {
    }

    public static zzcw zzb() {
        return zza;
    }

    public final Context zza() {
        return this.zzb;
    }

    public final void zzc(Context context) {
        this.zzb = context != null ? context.getApplicationContext() : null;
    }
}
