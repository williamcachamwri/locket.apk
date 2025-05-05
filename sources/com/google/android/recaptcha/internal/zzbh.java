package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzbh {
    private final SharedPreferences zza;

    public zzbh(Context context) {
        this.zza = context.getSharedPreferences("_GRECAPTCHA", 0);
    }

    public final String zza() {
        String string = this.zza.getString("_GRECAPTCHA_KC", (String) null);
        return string == null ? "" : string;
    }

    public final void zzb(Map map) {
        SharedPreferences.Editor edit = this.zza.edit();
        for (Map.Entry entry : map.entrySet()) {
            edit.putString((String) entry.getKey(), (String) entry.getValue());
        }
        edit.commit();
    }
}
