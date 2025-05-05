package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.internal.zzje;
import expo.modules.interfaces.permissions.PermissionsResponse;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzax {
    private static final zzax zza = new zzax((Boolean) null, 100);
    private final int zzb;
    private final String zzc;
    private final Boolean zzd;
    private final String zze;
    private final EnumMap<zzje.zza, zzjh> zzf;

    public final int zza() {
        return this.zzb;
    }

    public final int hashCode() {
        int i;
        int i2;
        Boolean bool = this.zzd;
        if (bool == null) {
            i = 3;
        } else {
            i = bool == Boolean.TRUE ? 7 : 13;
        }
        String str = this.zze;
        if (str == null) {
            i2 = 17;
        } else {
            i2 = str.hashCode();
        }
        return this.zzc.hashCode() + (i * 29) + (i2 * 137);
    }

    public final Bundle zzb() {
        Bundle bundle = new Bundle();
        for (Map.Entry next : this.zzf.entrySet()) {
            String zzb2 = zzje.zzb((zzjh) next.getValue());
            if (zzb2 != null) {
                bundle.putString(((zzje.zza) next.getKey()).zze, zzb2);
            }
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            bundle.putString("is_dma_region", bool.toString());
        }
        String str = this.zze;
        if (str != null) {
            bundle.putString("cps_display_str", str);
        }
        return bundle;
    }

    public static zzax zza(Bundle bundle, int i) {
        Boolean bool = null;
        if (bundle == null) {
            return new zzax((Boolean) null, i);
        }
        EnumMap enumMap = new EnumMap(zzje.zza.class);
        for (zzje.zza zza2 : zzjg.DMA.zza()) {
            enumMap.put(zza2, zzje.zza(bundle.getString(zza2.zze)));
        }
        if (bundle.containsKey("is_dma_region")) {
            bool = Boolean.valueOf(bundle.getString("is_dma_region"));
        }
        return new zzax((EnumMap<zzje.zza, zzjh>) enumMap, i, bool, bundle.getString("cps_display_str"));
    }

    static zzax zza(zzjh zzjh, int i) {
        EnumMap enumMap = new EnumMap(zzje.zza.class);
        enumMap.put(zzje.zza.AD_USER_DATA, zzjh);
        return new zzax((EnumMap<zzje.zza, zzjh>) enumMap, -10, (Boolean) null, (String) null);
    }

    public static zzax zza(String str) {
        if (str == null || str.length() <= 0) {
            return zza;
        }
        String[] split = str.split(":");
        int parseInt = Integer.parseInt(split[0]);
        EnumMap enumMap = new EnumMap(zzje.zza.class);
        zzje.zza[] zza2 = zzjg.DMA.zza();
        int length = zza2.length;
        int i = 1;
        int i2 = 0;
        while (i2 < length) {
            enumMap.put(zza2[i2], zzje.zza(split[i].charAt(0)));
            i2++;
            i++;
        }
        return new zzax((EnumMap<zzje.zza, zzjh>) enumMap, parseInt, (Boolean) null, (String) null);
    }

    public final zzjh zzc() {
        zzjh zzjh = this.zzf.get(zzje.zza.AD_USER_DATA);
        return zzjh == null ? zzjh.UNINITIALIZED : zzjh;
    }

    public static Boolean zza(Bundle bundle) {
        zzjh zza2;
        if (bundle == null || (zza2 = zzje.zza(bundle.getString("ad_personalization"))) == null) {
            return null;
        }
        int i = zzba.zza[zza2.ordinal()];
        if (i == 3) {
            return false;
        }
        if (i != 4) {
            return null;
        }
        return true;
    }

    public final Boolean zzd() {
        return this.zzd;
    }

    private final String zzh() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.zzb);
        for (zzje.zza zza2 : zzjg.DMA.zza()) {
            sb.append(":");
            sb.append(zzje.zza(this.zzf.get(zza2)));
        }
        return sb.toString();
    }

    public final String zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzc;
    }

    public final String toString() {
        StringBuilder append = new StringBuilder("source=").append(zzje.zza(this.zzb));
        for (zzje.zza zza2 : zzjg.DMA.zza()) {
            append.append(",");
            append.append(zza2.zze);
            append.append("=");
            zzjh zzjh = this.zzf.get(zza2);
            if (zzjh == null) {
                append.append("uninitialized");
            } else {
                int i = zzba.zza[zzjh.ordinal()];
                if (i == 1) {
                    append.append("uninitialized");
                } else if (i == 2) {
                    append.append("eu_consent_policy");
                } else if (i == 3) {
                    append.append("denied");
                } else if (i == 4) {
                    append.append(PermissionsResponse.GRANTED_KEY);
                }
            }
        }
        if (this.zzd != null) {
            append.append(",isDmaRegion=").append(this.zzd);
        }
        if (this.zze != null) {
            append.append(",cpsDisplayStr=").append(this.zze);
        }
        return append.toString();
    }

    zzax(Boolean bool, int i) {
        this((Boolean) null, i, (Boolean) null, (String) null);
    }

    zzax(Boolean bool, int i, Boolean bool2, String str) {
        EnumMap<zzje.zza, zzjh> enumMap = new EnumMap<>(zzje.zza.class);
        this.zzf = enumMap;
        enumMap.put(zzje.zza.AD_USER_DATA, zzje.zza(bool));
        this.zzb = i;
        this.zzc = zzh();
        this.zzd = bool2;
        this.zze = str;
    }

    private zzax(EnumMap<zzje.zza, zzjh> enumMap, int i, Boolean bool, String str) {
        EnumMap<zzje.zza, zzjh> enumMap2 = new EnumMap<>(zzje.zza.class);
        this.zzf = enumMap2;
        enumMap2.putAll(enumMap);
        this.zzb = i;
        this.zzc = zzh();
        this.zzd = bool;
        this.zze = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzax)) {
            return false;
        }
        zzax zzax = (zzax) obj;
        if (this.zzc.equalsIgnoreCase(zzax.zzc) && Objects.equals(this.zzd, zzax.zzd)) {
            return Objects.equals(this.zze, zzax.zze);
        }
        return false;
    }

    public final boolean zzg() {
        for (zzjh zzjh : this.zzf.values()) {
            if (zzjh != zzjh.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }
}
