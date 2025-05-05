package com.google.android.gms.measurement.internal;

import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzf {
    private final zzjh zza;

    static zzf zza(String str) {
        zzjh zzjh;
        if (TextUtils.isEmpty(str) || str.length() > 1) {
            zzjh = zzjh.UNINITIALIZED;
        } else {
            zzjh = zzje.zza(str.charAt(0));
        }
        return new zzf(zzjh);
    }

    /* access modifiers changed from: package-private */
    public final zzjh zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final String zzb() {
        return String.valueOf(zzje.zza(this.zza));
    }

    zzf(zzjh zzjh) {
        this.zza = zzjh;
    }
}
