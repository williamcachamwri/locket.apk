package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import androidx.autofill.HintConstants;
import com.google.android.gms.common.internal.Preconditions;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaia  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzaia implements zzadq {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private boolean zzf;

    public static zzaia zza(String str, String str2, boolean z) {
        zzaia zzaia = new zzaia();
        zzaia.zzb = Preconditions.checkNotEmpty(str);
        zzaia.zzc = Preconditions.checkNotEmpty(str2);
        zzaia.zzf = z;
        return zzaia;
    }

    public static zzaia zzb(String str, String str2, boolean z) {
        zzaia zzaia = new zzaia();
        zzaia.zza = Preconditions.checkNotEmpty(str);
        zzaia.zzd = Preconditions.checkNotEmpty(str2);
        zzaia.zzf = z;
        return zzaia;
    }

    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.zzd)) {
            jSONObject.put(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, this.zza);
            jSONObject.put("temporaryProof", this.zzd);
        } else {
            jSONObject.put("sessionInfo", this.zzb);
            jSONObject.put(UniversalFirebaseFunctionsModule.CODE_KEY, this.zzc);
        }
        String str = this.zze;
        if (str != null) {
            jSONObject.put("idToken", str);
        }
        if (!this.zzf) {
            jSONObject.put("operation", 2);
        }
        return jSONObject.toString();
    }

    private zzaia() {
    }

    public final void zza(String str) {
        this.zze = str;
    }
}
