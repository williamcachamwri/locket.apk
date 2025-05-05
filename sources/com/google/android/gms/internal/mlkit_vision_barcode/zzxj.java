package com.google.android.gms.internal.mlkit_vision_barcode;

import com.google.android.gms.common.internal.GmsLogger;

/* compiled from: com.google.android.gms:play-services-mlkit-barcode-scanning@@18.3.1 */
final class zzxj implements zzeh {
    final /* synthetic */ zzrc zza;
    final /* synthetic */ float zzb;
    final /* synthetic */ zzxn zzc;
    final /* synthetic */ float zzd;
    final /* synthetic */ zzxk zze;

    zzxj(zzxk zzxk, zzrc zzrc, float f, zzxn zzxn, float f2) {
        this.zza = zzrc;
        this.zzb = f;
        this.zzc = zzxn;
        this.zzd = f2;
        this.zze = zzxk;
    }

    public final void zza(Throwable th) {
        GmsLogger zzb2 = zzxk.zzf;
        zzb2.w("AutoZoom", "Unable to set zoom to " + this.zzd, th);
        this.zze.zzg.set(false);
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        Float f = (Float) obj;
        if (f.floatValue() >= 1.0f) {
            zzxk.zzg(this.zze, f.floatValue());
            this.zze.zzq(this.zza, this.zzb, f.floatValue(), this.zzc);
        }
        this.zze.zzg.set(false);
    }
}
