package com.google.android.odml.image;

import android.graphics.Bitmap;

/* compiled from: com.google.android.odml:image@@1.0.0-beta1 */
final class zze implements zzg {
    private final Bitmap zza;
    private final ImageProperties zzb;

    public zze(Bitmap bitmap) {
        this.zza = bitmap;
        zzb zzb2 = new zzb();
        int i = zzd.zza[bitmap.getConfig().ordinal()];
        zzb2.zza(i != 1 ? i != 2 ? 0 : 1 : 8);
        zzb2.zzb(1);
        this.zzb = zzb2.zzc();
    }

    public final Bitmap zza() {
        return this.zza;
    }

    public final ImageProperties zzb() {
        return this.zzb;
    }

    public final void zzc() {
        this.zza.recycle();
    }
}
