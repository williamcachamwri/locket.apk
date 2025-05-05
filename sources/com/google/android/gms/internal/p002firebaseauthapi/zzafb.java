package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.collection.ArrayMap;
import com.google.firebase.FirebaseApp;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzafb {
    private static final Map<String, zzafe> zza = new ArrayMap();
    private static final Map<String, List<WeakReference<zzafd>>> zzb = new ArrayMap();

    private static String zza(String str, int i, boolean z) {
        if (z) {
            return "http://[" + str + "]:" + i + "/";
        }
        return "http://" + str + ":" + i + "/";
    }

    public static String zza(String str) {
        zzafe zzafe;
        Map<String, zzafe> map = zza;
        synchronized (map) {
            zzafe = map.get(str);
        }
        if (zzafe != null) {
            return zza(zzafe.zzb(), zzafe.zza(), zzafe.zzb().contains(":")) + "emulator/auth/handler";
        }
        throw new IllegalStateException("Tried to get the emulator widget endpoint, but no emulator endpoint overrides found.");
    }

    public static String zzb(String str) {
        zzafe zzafe;
        String str2;
        Map<String, zzafe> map = zza;
        synchronized (map) {
            zzafe = map.get(str);
        }
        if (zzafe != null) {
            str2 = "" + zza(zzafe.zzb(), zzafe.zza(), zzafe.zzb().contains(":"));
        } else {
            str2 = "https://";
        }
        return str2 + "www.googleapis.com/identitytoolkit/v3/relyingparty";
    }

    public static String zzc(String str) {
        zzafe zzafe;
        String str2;
        Map<String, zzafe> map = zza;
        synchronized (map) {
            zzafe = map.get(str);
        }
        if (zzafe != null) {
            str2 = "" + zza(zzafe.zzb(), zzafe.zza(), zzafe.zzb().contains(":"));
        } else {
            str2 = "https://";
        }
        return str2 + "identitytoolkit.googleapis.com/v2";
    }

    public static String zzd(String str) {
        zzafe zzafe;
        String str2;
        Map<String, zzafe> map = zza;
        synchronized (map) {
            zzafe = map.get(str);
        }
        if (zzafe != null) {
            str2 = "" + zza(zzafe.zzb(), zzafe.zza(), zzafe.zzb().contains(":"));
        } else {
            str2 = "https://";
        }
        return str2 + "securetoken.googleapis.com/v1";
    }

    public static void zza(String str, zzafd zzafd) {
        Map<String, List<WeakReference<zzafd>>> map = zzb;
        synchronized (map) {
            if (map.containsKey(str)) {
                map.get(str).add(new WeakReference(zzafd));
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new WeakReference(zzafd));
                map.put(str, arrayList);
            }
        }
    }

    public static void zza(FirebaseApp firebaseApp, String str, int i) {
        String apiKey = firebaseApp.getOptions().getApiKey();
        Map<String, zzafe> map = zza;
        synchronized (map) {
            map.put(apiKey, new zzafe(str, i));
        }
        Map<String, List<WeakReference<zzafd>>> map2 = zzb;
        synchronized (map2) {
            if (map2.containsKey(apiKey)) {
                boolean z = false;
                for (WeakReference weakReference : map2.get(apiKey)) {
                    zzafd zzafd = (zzafd) weakReference.get();
                    if (zzafd != null) {
                        zzafd.zza();
                        z = true;
                    }
                }
                if (!z) {
                    zza.remove(apiKey);
                }
            }
        }
    }

    public static boolean zza(FirebaseApp firebaseApp) {
        return zza.containsKey(firebaseApp.getOptions().getApiKey());
    }
}
