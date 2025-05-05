package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zznd implements zzmu {
    final int zza;
    final zzpy zzb;

    zznd(zzni zzni, int i, zzpy zzpy, boolean z, boolean z2) {
        this.zza = i;
        this.zzb = zzpy;
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return this.zza - ((zznd) obj).zza;
    }

    public final int zza() {
        return this.zza;
    }

    public final zzoj zzb(zzoj zzoj, zzok zzok) {
        zzmz zzmz = (zzmz) zzoj;
        zzmz.zzh((zznf) zzok);
        return zzmz;
    }

    public final zzop zzc(zzop zzop, zzop zzop2) {
        throw new UnsupportedOperationException();
    }

    public final zzpy zzd() {
        return this.zzb;
    }

    public final zzpz zze() {
        return this.zzb.zza();
    }

    public final boolean zzf() {
        return false;
    }

    public final boolean zzg() {
        return false;
    }
}
