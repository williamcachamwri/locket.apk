package com.google.ads.interactivemedia.v3.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzlw extends zzma {
    zzlw(int i, String str, Long l, Long l2) {
        super(1, str, l, l2, (zzlz) null);
    }

    public final /* bridge */ /* synthetic */ Object zza(JSONObject jSONObject) {
        return Long.valueOf(jSONObject.optLong(zzj(), ((Long) zzi()).longValue()));
    }

    public final /* bridge */ /* synthetic */ Object zzb(Bundle bundle) {
        if (bundle.containsKey("com.google.android.gms.ads.flag.".concat(zzj()))) {
            return Long.valueOf(bundle.getLong("com.google.android.gms.ads.flag.".concat(zzj())));
        }
        return (Long) zzi();
    }

    public final /* bridge */ /* synthetic */ Object zzc(SharedPreferences sharedPreferences) {
        return Long.valueOf(sharedPreferences.getLong(zzj(), ((Long) zzi()).longValue()));
    }
}
