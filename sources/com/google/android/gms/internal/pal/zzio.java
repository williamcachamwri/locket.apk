package com.google.android.gms.internal.pal;

import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzio {
    private static final Logger zza = Logger.getLogger(zzio.class.getName());
    private static final zzin zzb = new zzin((zzim) null);

    private zzio() {
    }

    @CheckForNull
    static String zza(@CheckForNull String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return str;
    }
}
