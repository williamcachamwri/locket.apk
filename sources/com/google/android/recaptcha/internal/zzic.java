package com.google.android.recaptcha.internal;

import android.content.Context;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzic implements zzij {
    private final Context zza;

    public zzic(Context context) {
        this.zza = context;
    }

    public final /* synthetic */ Object cs(Object[] objArr) {
        return zzig.zza(this, objArr);
    }

    public final Object zza(Object... objArr) {
        return zzar.zza(this.zza.getContentResolver());
    }
}
