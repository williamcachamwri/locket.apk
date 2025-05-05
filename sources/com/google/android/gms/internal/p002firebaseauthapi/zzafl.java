package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzafl implements zzadt<zzafl> {
    private static final String zza = "zzafl";
    private List<String> zzb;

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzafl zza(String str) throws zzabg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.optString("authUri", (String) null);
            jSONObject.optBoolean("registered", false);
            jSONObject.optString("providerId", (String) null);
            jSONObject.optBoolean("forExistingProvider", false);
            if (!jSONObject.has("allProviders")) {
                zzahq.zza();
            } else {
                new zzahq(1, zzaid.zza(jSONObject.optJSONArray("allProviders")));
            }
            this.zzb = zzaid.zza(jSONObject.optJSONArray("signinMethods"));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str);
        }
    }

    public final List<String> zza() {
        return this.zzb;
    }

    public zzafl() {
        zzahq.zza();
    }
}
