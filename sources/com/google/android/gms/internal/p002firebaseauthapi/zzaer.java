package com.google.android.gms.internal.p002firebaseauthapi;

import android.app.Activity;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaer  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzaer {
    private static final Map<String, zzaet> zza = new ArrayMap();

    public static PhoneAuthProvider.OnVerificationStateChangedCallbacks zza(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, zzady zzady) {
        zza(str, zzady);
        return new zzaeu(onVerificationStateChangedCallbacks, str);
    }

    public static void zza() {
        zza.clear();
    }

    private static void zza(String str, zzady zzady) {
        zza.put(str, new zzaet(zzady, DefaultClock.getInstance().currentTimeMillis()));
    }

    public static boolean zza(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        Map<String, zzaet> map = zza;
        if (map.containsKey(str)) {
            zzaet zzaet = map.get(str);
            if (DefaultClock.getInstance().currentTimeMillis() - zzaet.zzb >= 120000) {
                zza(str, (zzady) null);
                return false;
            } else if (zzaet.zza == null) {
                return true;
            } else {
                zzaet.zza.zza(onVerificationStateChangedCallbacks, activity, executor, str);
                return true;
            }
        } else {
            zza(str, (zzady) null);
            return false;
        }
    }
}
