package com.google.android.recaptcha.internal;

import android.app.Application;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zziy extends Lambda implements Function0 {
    public static final zziy zza = new zziy();

    public zziy() {
        super(0);
    }

    public final Object invoke() {
        int i = zzax.zza;
        Object zzb = zzaw.zza().zzb(Application.class.getName().hashCode());
        if (zzb != null) {
            return (Application) zzb;
        }
        throw new zzbf(zzbd.zzb, zzbc.zzaA, (String) null);
    }
}
