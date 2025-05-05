package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.common.base.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzcu {
    private static final ThreadLocal<Boolean> zza = new zzct();

    public static SharedPreferences zza(Context context, String str, int i, zzcm zzcm) {
        zzcp zzcp = zzcf.zza().zza(str, zzcm, zzcj.SHARED_PREFS_TYPE).equals("") ? new zzcp() : null;
        if (zzcp != null) {
            return zzcp;
        }
        ThreadLocal<Boolean> threadLocal = zza;
        Preconditions.checkArgument(threadLocal.get().booleanValue());
        threadLocal.set(Boolean.FALSE);
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
            threadLocal.set(Boolean.TRUE);
            return sharedPreferences;
        } catch (Throwable th) {
            zza.set(Boolean.TRUE);
            throw th;
        }
    }
}
