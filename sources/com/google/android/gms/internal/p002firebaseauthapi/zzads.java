package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.LibraryVersion;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzads  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzads {
    private final int zza;

    private static int zza(String str) {
        try {
            List<String> zza2 = zzx.zza("[.-]").zza((CharSequence) str);
            if (zza2.size() == 1) {
                return Integer.parseInt(str);
            }
            if (zza2.size() >= 3) {
                return (Integer.parseInt(zza2.get(0)) * 1000000) + (Integer.parseInt(zza2.get(1)) * 1000) + Integer.parseInt(zza2.get(2));
            }
            return -1;
        } catch (IllegalArgumentException e) {
            if (!Log.isLoggable("LibraryVersionContainer", 3)) {
                return -1;
            }
            Log.d("LibraryVersionContainer", String.format("Version code parsing failed for: %s with exception %s.", new Object[]{str, e}));
            return -1;
        }
    }

    public static zzads zza() {
        String version = LibraryVersion.getInstance().getVersion("firebase-auth");
        if (TextUtils.isEmpty(version) || version.equals("UNKNOWN")) {
            version = "-1";
        }
        return new zzads(version);
    }

    public final String zzb() {
        return String.format("X%s", new Object[]{Integer.toString(this.zza)});
    }

    private zzads(String str) {
        this.zza = zza(str);
    }
}
