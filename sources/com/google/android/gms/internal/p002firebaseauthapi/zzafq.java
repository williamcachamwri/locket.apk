package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzafq implements zzadt<zzafq> {
    private static final String zza = "zzafq";
    private String zzb;
    private String zzc;
    private boolean zzd;
    private long zze;
    private List<zzags> zzf;
    private String zzg;

    public final long zza() {
        return this.zze;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzafq zza(String str) throws zzabg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.optString("localId", (String) null);
            jSONObject.optString("email", (String) null);
            this.zzb = jSONObject.optString("idToken", (String) null);
            this.zzc = jSONObject.optString("refreshToken", (String) null);
            this.zzd = jSONObject.optBoolean("isNewUser", false);
            this.zze = jSONObject.optLong("expiresIn", 0);
            this.zzf = zzags.zza(jSONObject.optJSONArray("mfaInfo"));
            this.zzg = jSONObject.optString("mfaPendingCredential", (String) null);
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str);
        }
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzg;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final List<zzags> zze() {
        return this.zzf;
    }

    public final boolean zzf() {
        return !TextUtils.isEmpty(this.zzg);
    }

    public final boolean zzg() {
        return this.zzd;
    }
}
