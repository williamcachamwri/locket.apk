package com.google.android.gms.internal.mlkit_vision_common;

import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zze {
    private static final Logger zza = Logger.getLogger(zze.class.getName());
    private static final zzd zzb = new zzd((zzc) null);

    private zze() {
    }

    static boolean zza(@CheckForNull String str) {
        return str == null || str.isEmpty();
    }
}
