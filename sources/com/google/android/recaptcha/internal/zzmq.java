package com.google.android.recaptcha.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzmq {
    static final zzmq zza = new zzmq(true);
    public static final /* synthetic */ int zzb = 0;
    private static volatile boolean zzc = false;
    private final Map zzd;

    zzmq() {
        this.zzd = new HashMap();
    }

    public final zzne zza(zzok zzok, int i) {
        return (zzne) this.zzd.get(new zzmp(zzok, i));
    }

    zzmq(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
