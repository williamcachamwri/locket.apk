package com.google.ads.interactivemedia.v3.internal;

import java.util.Date;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzyq implements zzwk {
    zzyq() {
    }

    public final String toString() {
        return "DefaultDateTypeAdapter#DEFAULT_STYLE_FACTORY";
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        if (zzaca.zzc() == Date.class) {
            return new zzyu(zzys.zza, 2, 2, (zzyt) null);
        }
        return null;
    }
}
