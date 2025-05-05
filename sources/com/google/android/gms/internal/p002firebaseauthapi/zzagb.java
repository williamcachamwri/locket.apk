package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.autofill.HintConstants;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.auth.zzan;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzagb implements zzadt<zzagb> {
    private static final String zza = "zzagb";
    private zzagd zzb;

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzagb zza(String str) throws zzabg {
        zzagd zzagd;
        int i;
        zzage zzage;
        String str2 = str;
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (!jSONObject.has("users")) {
                zzagd = new zzagd();
            } else {
                JSONArray optJSONArray = jSONObject.optJSONArray("users");
                if (optJSONArray != null) {
                    if (optJSONArray.length() != 0) {
                        ArrayList arrayList = new ArrayList(optJSONArray.length());
                        boolean z = false;
                        int i2 = 0;
                        while (i2 < optJSONArray.length()) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                            if (jSONObject2 == null) {
                                zzage = new zzage();
                                i = i2;
                            } else {
                                i = i2;
                                zzage = new zzage(Strings.emptyToNull(jSONObject2.optString("localId", (String) null)), Strings.emptyToNull(jSONObject2.optString("email", (String) null)), jSONObject2.optBoolean("emailVerified", z), Strings.emptyToNull(jSONObject2.optString("displayName", (String) null)), Strings.emptyToNull(jSONObject2.optString("photoUrl", (String) null)), zzagu.zza(jSONObject2.optJSONArray("providerUserInfo")), Strings.emptyToNull(jSONObject2.optString("rawPassword", (String) null)), Strings.emptyToNull(jSONObject2.optString(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, (String) null)), jSONObject2.optLong("createdAt", 0), jSONObject2.optLong("lastLoginAt", 0), false, (zze) null, zzags.zza(jSONObject2.optJSONArray("mfaInfo")), zzan.zza(jSONObject2.optJSONArray("passkeyInfo")));
                            }
                            arrayList.add(zzage);
                            i2 = i + 1;
                            z = false;
                        }
                        zzagd = new zzagd(arrayList);
                    }
                }
                zzagd = new zzagd(new ArrayList());
            }
            this.zzb = zzagd;
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str2);
        }
    }

    public final List<zzage> zza() {
        return this.zzb.zza();
    }
}
