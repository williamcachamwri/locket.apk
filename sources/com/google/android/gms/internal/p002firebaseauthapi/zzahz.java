package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.autofill.HintConstants;
import com.google.android.gms.common.util.Strings;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahz  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzahz implements zzadt<zzahz> {
    private static final String zza = "zzahz";
    private String zzb;
    private String zzc;
    private long zzd;
    private boolean zze;
    private String zzf;
    private String zzg;

    public final long zza() {
        return this.zzd;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzahz zza(String str) throws zzabg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("idToken", (String) null));
            this.zzc = Strings.emptyToNull(jSONObject.optString("refreshToken", (String) null));
            this.zzd = jSONObject.optLong("expiresIn", 0);
            Strings.emptyToNull(jSONObject.optString("localId", (String) null));
            this.zze = jSONObject.optBoolean("isNewUser", false);
            this.zzf = Strings.emptyToNull(jSONObject.optString("temporaryProof", (String) null));
            this.zzg = Strings.emptyToNull(jSONObject.optString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, (String) null));
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

    public final String zze() {
        return this.zzf;
    }

    public final boolean zzf() {
        return this.zze;
    }
}
