package com.google.firebase.auth.internal;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p002firebaseauthapi.zzzh;
import com.google.firebase.auth.GetTokenResult;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzbg {
    private static final Logger zza = new Logger("GetTokenResultFactory", new String[0]);

    public static GetTokenResult zza(String str) {
        Map map;
        try {
            map = zzbj.zza(str);
        } catch (zzzh e) {
            zza.e("Error parsing token claims", e, new Object[0]);
            map = new HashMap();
        }
        return new GetTokenResult(str, map);
    }
}
