package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.util.Strings;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzahx implements zzadt<zzahx> {
    private static final String zza = "zzahx";
    private String zzb;
    private String zzc;
    private long zzd;
    private List<zzags> zze;
    private String zzf;

    public final long zza() {
        return this.zzd;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzahx zza(String str) throws zzabg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Strings.emptyToNull(jSONObject.optString("localId", (String) null));
            Strings.emptyToNull(jSONObject.optString("email", (String) null));
            Strings.emptyToNull(jSONObject.optString("displayName", (String) null));
            this.zzb = Strings.emptyToNull(jSONObject.optString("idToken", (String) null));
            Strings.emptyToNull(jSONObject.optString("photoUrl", (String) null));
            this.zzc = Strings.emptyToNull(jSONObject.optString("refreshToken", (String) null));
            this.zzd = jSONObject.optLong("expiresIn", 0);
            this.zze = zzags.zza(jSONObject.optJSONArray("mfaInfo"));
            this.zzf = jSONObject.optString("mfaPendingCredential", (String) null);
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str);
        }
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final List<zzags> zze() {
        return this.zze;
    }

    public final boolean zzf() {
        return !TextUtils.isEmpty(this.zzf);
    }
}
