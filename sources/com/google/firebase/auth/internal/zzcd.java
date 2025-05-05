package com.google.firebase.auth.internal;

import android.content.Intent;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzcd {
    private static final Map<String, String> zza;

    public static Status zza(Intent intent) {
        Preconditions.checkNotNull(intent);
        Preconditions.checkArgument(zzb(intent));
        return (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.firebase.auth.internal.STATUS", Status.CREATOR);
    }

    public static Status zza(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(UniversalFirebaseFunctionsModule.CODE_KEY);
            String string2 = jSONObject.getString("message");
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                Map<String, String> map = zza;
                if (map.containsKey(string)) {
                    return zzaq.zza(map.get(string) + ":" + string2);
                }
            }
            return zzaq.zza("WEB_INTERNAL_ERROR:" + str);
        } catch (JSONException e) {
            return zzaq.zza("WEB_INTERNAL_ERROR:" + str + "[ " + e.getLocalizedMessage() + " ]");
        }
    }

    static {
        HashMap hashMap = new HashMap();
        zza = hashMap;
        hashMap.put("auth/invalid-provider-id", "INVALID_PROVIDER_ID");
        hashMap.put("auth/invalid-cert-hash", "INVALID_CERT_HASH");
        hashMap.put("auth/network-request-failed", "WEB_NETWORK_REQUEST_FAILED");
        hashMap.put("auth/web-storage-unsupported", "WEB_STORAGE_UNSUPPORTED");
        hashMap.put("auth/operation-not-allowed", "OPERATION_NOT_ALLOWED");
    }

    public static void zza(Intent intent, Status status) {
        SafeParcelableSerializer.serializeToIntentExtra(status, intent, "com.google.firebase.auth.internal.STATUS");
    }

    public static boolean zzb(Intent intent) {
        Preconditions.checkNotNull(intent);
        return intent.hasExtra("com.google.firebase.auth.internal.STATUS");
    }
}
