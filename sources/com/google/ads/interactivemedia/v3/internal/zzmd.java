package com.google.ads.interactivemedia.v3.internal;

import android.content.SharedPreferences;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zzmd implements zzqn {
    public final /* synthetic */ SharedPreferences zza;

    public /* synthetic */ zzmd(SharedPreferences sharedPreferences) {
        this.zza = sharedPreferences;
    }

    public final Object zza() {
        return this.zza.getString("app_settings_json", "{}");
    }
}
