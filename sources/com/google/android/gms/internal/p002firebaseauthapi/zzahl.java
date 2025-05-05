package com.google.android.gms.internal.p002firebaseauthapi;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzahl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzahl extends zzahh {
    private static final String zza = "zzahl";
    private String zzb;

    public final /* synthetic */ zzadt zza(String str) throws zzabg {
        return (zzahl) zza(str);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final zzahl zzb(String str) throws zzabg {
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("phoneSessionInfo");
            if (optJSONObject != null) {
                this.zzb = zzag.zza(optJSONObject.optString("sessionInfo"));
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str);
        }
    }

    public final String zza() {
        return this.zzb;
    }
}
