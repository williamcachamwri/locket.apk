package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahe  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzahe implements zzadt<zzahe> {
    private static final String zza = "zzahe";
    private String zzb;
    private zzagu zzc;
    private String zzd;
    private String zze;
    private long zzf;

    public final long zza() {
        return this.zzf;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzahe zza(String str) throws zzabg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("email", (String) null));
            Strings.emptyToNull(jSONObject.optString("passwordHash", (String) null));
            Boolean.valueOf(jSONObject.optBoolean("emailVerified", false));
            Strings.emptyToNull(jSONObject.optString("displayName", (String) null));
            Strings.emptyToNull(jSONObject.optString("photoUrl", (String) null));
            this.zzc = zzagu.zza(jSONObject.optJSONArray("providerUserInfo"));
            this.zzd = Strings.emptyToNull(jSONObject.optString("idToken", (String) null));
            this.zze = Strings.emptyToNull(jSONObject.optString("refreshToken", (String) null));
            this.zzf = jSONObject.optLong("expiresIn", 0);
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str);
        }
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzd;
    }

    public final String zzd() {
        return this.zze;
    }

    public final List<zzagr> zze() {
        zzagu zzagu = this.zzc;
        if (zzagu != null) {
            return zzagu.zza();
        }
        return null;
    }
}
