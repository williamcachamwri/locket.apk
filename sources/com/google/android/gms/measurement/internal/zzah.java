package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.internal.zzje;
import java.util.EnumMap;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzah {
    private final EnumMap<zzje.zza, zzak> zza;

    public final zzak zza(zzje.zza zza2) {
        zzak zzak = this.zza.get(zza2);
        return zzak == null ? zzak.UNSET : zzak;
    }

    public static zzah zza(String str) {
        EnumMap enumMap = new EnumMap(zzje.zza.class);
        if (str.length() >= zzje.zza.values().length) {
            int i = 0;
            if (str.charAt(0) == '1') {
                zzje.zza[] values = zzje.zza.values();
                int length = values.length;
                int i2 = 1;
                while (i < length) {
                    enumMap.put(values[i], zzak.zza(str.charAt(i2)));
                    i++;
                    i2++;
                }
                return new zzah(enumMap);
            }
        }
        return new zzah();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("1");
        for (zzje.zza zza2 : zzje.zza.values()) {
            zzak zzak = this.zza.get(zza2);
            if (zzak == null) {
                zzak = zzak.UNSET;
            }
            sb.append(zzak.zzl);
        }
        return sb.toString();
    }

    zzah() {
        this.zza = new EnumMap<>(zzje.zza.class);
    }

    private zzah(EnumMap<zzje.zza, zzak> enumMap) {
        EnumMap<zzje.zza, zzak> enumMap2 = new EnumMap<>(zzje.zza.class);
        this.zza = enumMap2;
        enumMap2.putAll(enumMap);
    }

    public final void zza(zzje.zza zza2, int i) {
        zzak zzak = zzak.UNSET;
        if (i != -30) {
            if (i != -20) {
                if (i == -10) {
                    zzak = zzak.MANIFEST;
                } else if (i != 0) {
                    if (i == 30) {
                        zzak = zzak.INITIALIZATION;
                    }
                }
            }
            zzak = zzak.API;
        } else {
            zzak = zzak.TCF;
        }
        this.zza.put(zza2, zzak);
    }

    public final void zza(zzje.zza zza2, zzak zzak) {
        this.zza.put(zza2, zzak);
    }
}
