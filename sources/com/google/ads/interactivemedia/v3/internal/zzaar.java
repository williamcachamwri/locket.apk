package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaar implements zzwk {
    zzaar() {
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        Class<? super Enum> zzc = zzaca.zzc();
        if (!Enum.class.isAssignableFrom(zzc) || zzc == Enum.class) {
            return null;
        }
        if (!zzc.isEnum()) {
            zzc = zzc.getSuperclass();
        }
        return new zzabg(zzc);
    }
}
