package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import androidx.autofill.HintConstants;
import com.google.android.gms.common.internal.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzahm implements zzahi {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private zzafg zzg;
    private final String zzh = null;

    public static zzahm zza(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Preconditions.checkNotEmpty(str2);
        return new zzahm(str, "phone", str2, str3, str4, str5, str6, (String) null);
    }

    public final String zzb() {
        return this.zzc;
    }

    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("idToken", this.zza);
        jSONObject.put("mfaProvider", 1);
        if (this.zzb != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, this.zzb);
            if (!TextUtils.isEmpty(this.zzd)) {
                jSONObject2.put("recaptchaToken", this.zzd);
            }
            if (!TextUtils.isEmpty(this.zze)) {
                jSONObject2.put("playIntegrityToken", this.zze);
            }
            zzafg zzafg = this.zzg;
            if (zzafg != null) {
                jSONObject2.put("autoRetrievalInfo", zzafg.zza());
            }
            String str = this.zzf;
            if (str != null) {
                zzaid.zza(jSONObject2, "captchaResponse", str);
            } else {
                zzaid.zza(jSONObject2);
            }
            jSONObject.put("phoneEnrollmentInfo", jSONObject2);
        }
        if (!TextUtils.isEmpty((CharSequence) null)) {
            jSONObject.put("tenantId", (Object) null);
        }
        return jSONObject.toString();
    }

    private zzahm(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.zza = Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        this.zzb = str3;
        this.zzc = str4;
        this.zzd = str5;
        this.zze = str6;
        this.zzf = str7;
    }

    public final void zza(zzafg zzafg) {
        this.zzg = zzafg;
    }
}
