package com.google.ads.interactivemedia.v3.internal;

import java.sql.Timestamp;
import java.util.Date;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzabw implements zzwk {
    zzabw() {
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        if (zzaca.zzc() == Timestamp.class) {
            return new zzaby(zzvr.zza(zzaca.zza(Date.class)), (zzabx) null);
        }
        return null;
    }
}
