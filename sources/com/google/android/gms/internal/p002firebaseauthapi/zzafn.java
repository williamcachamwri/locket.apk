package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.auth.ActionCodeUrl;
import com.google.firebase.auth.EmailAuthCredential;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzafn implements zzadq {
    private static final String zza = "zzafn";
    private final String zzb;
    private final String zzc;
    private final EmailAuthCredential zzd;
    private final String zze;
    private final String zzf;

    public final EmailAuthCredential zzb() {
        return this.zzd;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final String zza() throws JSONException {
        ActionCodeUrl parseLink = ActionCodeUrl.parseLink(this.zzc);
        String str = null;
        String code = parseLink != null ? parseLink.getCode() : null;
        if (parseLink != null) {
            str = parseLink.zza();
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("email", this.zzb);
        if (code != null) {
            jSONObject.put("oobCode", code);
        }
        if (str != null) {
            jSONObject.put("tenantId", str);
        }
        String str2 = this.zze;
        if (str2 != null) {
            jSONObject.put("idToken", str2);
        }
        String str3 = this.zzf;
        if (str3 != null) {
            zzaid.zza(jSONObject, "captchaResp", str3);
        } else {
            zzaid.zza(jSONObject);
        }
        return jSONObject.toString();
    }

    static {
        new Logger(zza, new String[0]);
    }

    public zzafn(EmailAuthCredential emailAuthCredential, String str, String str2) {
        this.zzd = (EmailAuthCredential) Preconditions.checkNotNull(emailAuthCredential);
        this.zzb = Preconditions.checkNotEmpty(emailAuthCredential.zzc());
        this.zzc = Preconditions.checkNotEmpty(emailAuthCredential.zze());
        this.zze = str;
        this.zzf = str2;
    }
}
