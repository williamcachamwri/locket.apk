package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzagh implements zzadt<zzagh> {
    private static final String zza = "zzagh";
    private String zzb;

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzagh zza(String str) throws zzabg {
        try {
            this.zzb = Strings.emptyToNull(new JSONObject(str).optString("producerProjectNumber"));
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str);
        }
    }

    public final String zza() {
        return this.zzb;
    }

    public zzagh() {
    }

    public zzagh(String str) {
        this.zzb = str;
    }
}
