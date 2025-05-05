package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzmh implements zzce {
    private final SharedPreferences.Editor zza;
    private final String zzb;

    public zzmh(Context context, String str, String str2) {
        if (str != null) {
            this.zzb = str;
            Context applicationContext = context.getApplicationContext();
            if (str2 == null) {
                this.zza = PreferenceManager.getDefaultSharedPreferences(applicationContext).edit();
            } else {
                this.zza = applicationContext.getSharedPreferences(str2, 0).edit();
            }
        } else {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
    }

    public final void zza(zzuo zzuo) throws IOException {
        if (!this.zza.putString(this.zzb, zzyt.zza(zzuo.zzk())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    public final void zza(zzwa zzwa) throws IOException {
        if (!this.zza.putString(this.zzb, zzyt.zza(zzwa.zzk())).commit()) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}
