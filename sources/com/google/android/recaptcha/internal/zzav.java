package com.google.android.recaptcha.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzav extends Lambda implements Function0 {
    public static final zzav zza = new zzav();

    zzav() {
        super(0);
    }

    public static final Map zza() {
        Map zzc = zzax.zzc;
        Map linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(zzc.size()));
        for (Map.Entry entry : zzc.entrySet()) {
            linkedHashMap.put(entry.getKey(), ((zzay) entry.getValue()).zzb());
        }
        return linkedHashMap;
    }

    public final /* bridge */ /* synthetic */ Object invoke() {
        return zza();
    }
}
