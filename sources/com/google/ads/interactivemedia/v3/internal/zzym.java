package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzym implements zzwk {
    zzym() {
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        Type type;
        Type zzd = zzaca.zzd();
        if (zzd instanceof GenericArrayType) {
            type = ((GenericArrayType) zzd).getGenericComponentType();
        } else if (!(zzd instanceof Class)) {
            return null;
        } else {
            Class cls = (Class) zzd;
            if (!cls.isArray()) {
                return null;
            }
            type = cls.getComponentType();
        }
        return new zzyn(zzvr, zzvr.zza(zzaca.zzb(type)), zzwr.zza(type));
    }
}
