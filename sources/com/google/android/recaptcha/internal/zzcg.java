package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzcg extends Exception {
    private final Throwable zza;
    private final zztf zzb;
    private final int zzc;
    private final int zzd;

    public zzcg(int i, int i2, Throwable th) {
        this.zzc = i;
        this.zzd = i2;
        this.zza = th;
        zztf zzf = zztg.zzf();
        zzf.zzq(i2);
        zzf.zzr(i);
        this.zzb = zzf;
    }

    public final Throwable getCause() {
        return this.zza;
    }

    public final zztf zza() {
        return this.zzb;
    }

    public final int zzb() {
        return this.zzd;
    }
}
