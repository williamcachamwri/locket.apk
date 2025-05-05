package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagm  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzagm implements zzadt<zzagm> {
    private static final String zza = "zzagm";
    private String zzb;
    private zzal<zzagt> zzc;

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzagm zza(String str) throws zzabg {
        zzal<zzagt> zzal;
        zzagt zzagt;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("recaptchaKey"));
            if (jSONObject.has("recaptchaEnforcementState")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("recaptchaEnforcementState");
                if (optJSONArray != null) {
                    if (optJSONArray.length() != 0) {
                        zzao zzg = zzal.zzg();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            if (jSONObject2 == null) {
                                zzagt = zzagt.zza((String) null, (String) null);
                            } else {
                                zzagt = zzagt.zza(Strings.emptyToNull(jSONObject2.optString("provider")), Strings.emptyToNull(jSONObject2.optString("enforcementState")));
                            }
                            zzg.zza(zzagt);
                        }
                        zzal = zzg.zza();
                        this.zzc = zzal;
                    }
                }
                zzal = zzal.zza(new ArrayList());
                this.zzc = zzal;
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzaid.zza(e, zza, str);
        }
    }

    public final String zzb(String str) {
        Preconditions.checkNotEmpty(str);
        zzal<zzagt> zzal = this.zzc;
        if (zzal != null && !zzal.isEmpty()) {
            zzal<zzagt> zzal2 = this.zzc;
            zzal zzal3 = zzal2;
            int size = zzal2.size();
            int i = 0;
            while (i < size) {
                Object obj = zzal2.get(i);
                i++;
                zzagt zzagt = (zzagt) obj;
                String zza2 = zzagt.zza();
                String zzb2 = zzagt.zzb();
                if (zza2 != null && zzb2 != null && zzb2.equals(str)) {
                    return zzagt.zza();
                }
            }
        }
        return null;
    }

    public final String zza() {
        return this.zzb;
    }

    public final boolean zzc(String str) {
        String zzb2 = zzb(str);
        if (zzb2 == null) {
            return false;
        }
        if (zzb2.equals("ENFORCE") || zzb2.equals("AUDIT")) {
            return true;
        }
        return false;
    }
}
