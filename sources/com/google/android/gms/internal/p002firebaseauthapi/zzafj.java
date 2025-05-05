package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import io.sentry.android.core.SentryLogcatAdapter;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzafj implements zzadt<zzafj> {
    private static final String zza = "com.google.android.gms.internal.firebase-auth-api.zzafj";
    private String zzb;

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzafj zza(String str) throws zzabg {
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(str).getString("error"));
            jSONObject.getInt(UniversalFirebaseFunctionsModule.CODE_KEY);
            this.zzb = jSONObject.getString("message");
            return this;
        } catch (NullPointerException | JSONException e) {
            SentryLogcatAdapter.e(zza, "Failed to parse error for string [" + str + "] with exception: " + e.getMessage());
            throw new zzabg("Failed to parse error for string [" + str + "]", e);
        }
    }

    public final String zza() {
        return this.zzb;
    }

    public final boolean zzb() {
        return !TextUtils.isEmpty(this.zzb);
    }
}
