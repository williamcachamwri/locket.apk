package com.google.android.gms.internal.fido;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzcn {
    static final String zza;
    static final Comparator zzb;

    static {
        Comparator comparator;
        String concat = String.valueOf(zzcn.class.getName()).concat("$UnsafeComparator");
        zza = concat;
        try {
            Object[] enumConstants = Class.forName(concat).getEnumConstants();
            enumConstants.getClass();
            comparator = (Comparator) enumConstants[0];
        } catch (Throwable unused) {
            comparator = zzcm.INSTANCE;
        }
        zzb = comparator;
    }

    zzcn() {
    }
}
