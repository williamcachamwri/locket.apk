package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import io.sentry.android.core.SentryLogcatAdapter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzags  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzags {
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final long zzd;
    private final zzahp zze;

    private static long zza(String str) {
        try {
            return zzane.zza(zzane.zza(str));
        } catch (ParseException e) {
            SentryLogcatAdapter.w("MfaInfo", "Could not parse timestamp as ISOString. Invalid ISOString \"" + str + "\"", e);
            return 0;
        }
    }

    public final long zza() {
        return this.zzd;
    }

    public static zzags zza(JSONObject jSONObject) {
        zzags zzags = new zzags(jSONObject.optString("phoneInfo", (String) null), jSONObject.optString("mfaEnrollmentId", (String) null), jSONObject.optString("displayName", (String) null), zza(jSONObject.optString("enrolledAt", "")), jSONObject.opt("totpInfo") != null ? new zzahp() : null);
        jSONObject.optString("unobfuscatedPhoneInfo");
        return zzags;
    }

    public final zzahp zzb() {
        return this.zze;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final String zze() {
        return this.zza;
    }

    public static List<zzags> zza(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(zza(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    private zzags(String str, String str2, String str3, long j, zzahp zzahp) {
        if (TextUtils.isEmpty(str) || zzahp == null) {
            this.zza = str;
            this.zzb = Preconditions.checkNotEmpty(str2);
            this.zzc = str3;
            this.zzd = j;
            this.zze = zzahp;
            return;
        }
        SentryLogcatAdapter.e("MfaInfo", "Cannot have both MFA phone_info and totp_info");
        throw new IllegalArgumentException("Cannot have both MFA phone_info and totp_info");
    }
}
