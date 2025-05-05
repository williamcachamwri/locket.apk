package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzkr implements zzor {
    private final /* synthetic */ zzjq zza;

    zzkr(zzjq zzjq) {
        this.zza = zzjq;
    }

    public final void zza(String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.zza.zza("auto", str2, bundle, str);
        } else {
            this.zza.zzb("auto", str2, bundle);
        }
    }
}
