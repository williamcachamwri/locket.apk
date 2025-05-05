package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.AccessibleObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
abstract class zzye {
    public static final zzye zzb;

    static {
        zzye zzye = null;
        if (zzxo.zza()) {
            try {
                zzye = new zzyb(AccessibleObject.class.getDeclaredMethod("canAccess", new Class[]{Object.class}));
            } catch (NoSuchMethodException unused) {
            }
        }
        if (zzye == null) {
            zzye = new zzyc();
        }
        zzb = zzye;
    }

    /* synthetic */ zzye(zzyd zzyd) {
    }

    public abstract boolean zza(AccessibleObject accessibleObject, Object obj);
}
