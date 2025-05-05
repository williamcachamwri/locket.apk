package com.google.android.recaptcha.internal;

import java.math.BigInteger;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzhx {
    /* access modifiers changed from: private */
    public static final zzhw zza = new zzhw(11, ((long) Math.pow(2.0d, 32.0d)) ^ 20919936621L, (long) Math.pow(2.0d, 48.0d));
    private final zzhw zzb;
    private long zzc;

    public zzhx(long j, long j2, zzhw zzhw) {
        this.zzb = zzhw;
        this.zzc = Math.abs(j);
    }

    public final long zza() {
        zzhw zzhw = this.zzb;
        long longValue = (BigInteger.valueOf(zzhw.zzb()).multiply(BigInteger.valueOf(this.zzc)).mod(BigInteger.valueOf(zzhw.zza())).longValue() + 11) % this.zzb.zza();
        this.zzc = longValue;
        return longValue % 255;
    }
}
