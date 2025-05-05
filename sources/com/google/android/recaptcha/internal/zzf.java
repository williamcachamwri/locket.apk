package com.google.android.recaptcha.internal;

import kotlinx.coroutines.TimeoutCancellationException;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzf {
    public static final zzbf zza(Exception exc, zzbf zzbf) {
        if (exc instanceof TimeoutCancellationException) {
            return new zzbf(zzbd.zzb, zzbc.zzb, exc.getMessage());
        }
        return exc instanceof zzbf ? (zzbf) exc : zzbf;
    }
}
