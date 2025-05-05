package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import expo.modules.interfaces.permissions.PermissionsResponse;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzje {
    public static final zzje zza = new zzje((Boolean) null, (Boolean) null, 100);
    private final EnumMap<zza, zzjh> zzb;
    private final int zzc;

    static String zza(int i) {
        return i != -30 ? i != -20 ? i != -10 ? i != 0 ? i != 30 ? i != 90 ? i != 100 ? "OTHER" : "UNKNOWN" : "REMOTE_CONFIG" : "1P_INIT" : "1P_API" : "MANIFEST" : "API" : "TCF";
    }

    public static boolean zza(int i, int i2) {
        return ((i == -20 && i2 == -30) || ((i == -30 && i2 == -20) || i == i2)) || i < i2;
    }

    static char zza(zzjh zzjh) {
        if (zzjh == null) {
            return '-';
        }
        int ordinal = zzjh.ordinal();
        if (ordinal == 1) {
            return '+';
        }
        if (ordinal != 2) {
            return ordinal != 3 ? '-' : '1';
        }
        return '0';
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
    public enum zza {
        AD_STORAGE("ad_storage"),
        ANALYTICS_STORAGE("analytics_storage"),
        AD_USER_DATA("ad_user_data"),
        AD_PERSONALIZATION("ad_personalization");
        
        public final String zze;

        private zza(String str) {
            this.zze = str;
        }
    }

    public final int zza() {
        return this.zzc;
    }

    public final int hashCode() {
        int i = this.zzc * 17;
        for (zzjh hashCode : this.zzb.values()) {
            i = (i * 31) + hashCode.hashCode();
        }
        return i;
    }

    public final Bundle zzb() {
        Bundle bundle = new Bundle();
        for (Map.Entry next : this.zzb.entrySet()) {
            String zzb2 = zzb((zzjh) next.getValue());
            if (zzb2 != null) {
                bundle.putString(((zza) next.getKey()).zze, zzb2);
            }
        }
        return bundle;
    }

    static zzjh zza(String str) {
        if (str == null) {
            return zzjh.UNINITIALIZED;
        }
        if (str.equals(PermissionsResponse.GRANTED_KEY)) {
            return zzjh.GRANTED;
        }
        if (str.equals("denied")) {
            return zzjh.DENIED;
        }
        return zzjh.UNINITIALIZED;
    }

    public final zzjh zzc() {
        zzjh zzjh = this.zzb.get(zza.AD_STORAGE);
        return zzjh == null ? zzjh.UNINITIALIZED : zzjh;
    }

    public final zzjh zzd() {
        zzjh zzjh = this.zzb.get(zza.ANALYTICS_STORAGE);
        return zzjh == null ? zzjh.UNINITIALIZED : zzjh;
    }

    static zzjh zza(char c) {
        if (c == '+') {
            return zzjh.POLICY;
        }
        if (c == '0') {
            return zzjh.DENIED;
        }
        if (c != '1') {
            return zzjh.UNINITIALIZED;
        }
        return zzjh.GRANTED;
    }

    static zzjh zza(Boolean bool) {
        if (bool == null) {
            return zzjh.UNINITIALIZED;
        }
        if (bool.booleanValue()) {
            return zzjh.GRANTED;
        }
        return zzjh.DENIED;
    }

    public static zzje zza(Bundle bundle, int i) {
        if (bundle == null) {
            return new zzje((Boolean) null, (Boolean) null, i);
        }
        EnumMap enumMap = new EnumMap(zza.class);
        for (zza zza2 : zzjg.STORAGE.zzd) {
            enumMap.put(zza2, zza(bundle.getString(zza2.zze)));
        }
        return new zzje(enumMap, i);
    }

    public static zzje zza(zzjh zzjh, zzjh zzjh2, int i) {
        EnumMap enumMap = new EnumMap(zza.class);
        enumMap.put(zza.AD_STORAGE, zzjh);
        enumMap.put(zza.ANALYTICS_STORAGE, zzjh2);
        return new zzje(enumMap, -10);
    }

    public static zzje zzb(String str) {
        return zza(str, 100);
    }

    public static zzje zza(String str, int i) {
        EnumMap enumMap = new EnumMap(zza.class);
        if (str == null) {
            str = "";
        }
        zza[] zza2 = zzjg.STORAGE.zza();
        for (int i2 = 0; i2 < zza2.length; i2++) {
            zza zza3 = zza2[i2];
            int i3 = i2 + 2;
            if (i3 < str.length()) {
                enumMap.put(zza3, zza(str.charAt(i3)));
            } else {
                enumMap.put(zza3, zzjh.UNINITIALIZED);
            }
        }
        return new zzje(enumMap, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0051 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzje zza(com.google.android.gms.measurement.internal.zzje r9) {
        /*
            r8 = this;
            java.util.EnumMap r0 = new java.util.EnumMap
            java.lang.Class<com.google.android.gms.measurement.internal.zzje$zza> r1 = com.google.android.gms.measurement.internal.zzje.zza.class
            r0.<init>(r1)
            com.google.android.gms.measurement.internal.zzjg r1 = com.google.android.gms.measurement.internal.zzjg.STORAGE
            com.google.android.gms.measurement.internal.zzje$zza[] r1 = r1.zzd
            int r2 = r1.length
            r3 = 0
        L_0x000f:
            if (r3 >= r2) goto L_0x0054
            r4 = r1[r3]
            java.util.EnumMap<com.google.android.gms.measurement.internal.zzje$zza, com.google.android.gms.measurement.internal.zzjh> r5 = r8.zzb
            java.lang.Object r5 = r5.get(r4)
            com.google.android.gms.measurement.internal.zzjh r5 = (com.google.android.gms.measurement.internal.zzjh) r5
            java.util.EnumMap<com.google.android.gms.measurement.internal.zzje$zza, com.google.android.gms.measurement.internal.zzjh> r6 = r9.zzb
            java.lang.Object r6 = r6.get(r4)
            com.google.android.gms.measurement.internal.zzjh r6 = (com.google.android.gms.measurement.internal.zzjh) r6
            if (r5 != 0) goto L_0x0026
            goto L_0x0037
        L_0x0026:
            if (r6 != 0) goto L_0x0029
            goto L_0x004c
        L_0x0029:
            com.google.android.gms.measurement.internal.zzjh r7 = com.google.android.gms.measurement.internal.zzjh.UNINITIALIZED
            if (r5 != r7) goto L_0x002e
            goto L_0x0037
        L_0x002e:
            com.google.android.gms.measurement.internal.zzjh r7 = com.google.android.gms.measurement.internal.zzjh.UNINITIALIZED
            if (r6 != r7) goto L_0x0033
            goto L_0x004c
        L_0x0033:
            com.google.android.gms.measurement.internal.zzjh r7 = com.google.android.gms.measurement.internal.zzjh.POLICY
            if (r5 != r7) goto L_0x0039
        L_0x0037:
            r5 = r6
            goto L_0x004c
        L_0x0039:
            com.google.android.gms.measurement.internal.zzjh r7 = com.google.android.gms.measurement.internal.zzjh.POLICY
            if (r6 != r7) goto L_0x003e
            goto L_0x004c
        L_0x003e:
            com.google.android.gms.measurement.internal.zzjh r7 = com.google.android.gms.measurement.internal.zzjh.DENIED
            if (r5 == r7) goto L_0x004a
            com.google.android.gms.measurement.internal.zzjh r5 = com.google.android.gms.measurement.internal.zzjh.DENIED
            if (r6 != r5) goto L_0x0047
            goto L_0x004a
        L_0x0047:
            com.google.android.gms.measurement.internal.zzjh r5 = com.google.android.gms.measurement.internal.zzjh.GRANTED
            goto L_0x004c
        L_0x004a:
            com.google.android.gms.measurement.internal.zzjh r5 = com.google.android.gms.measurement.internal.zzjh.DENIED
        L_0x004c:
            if (r5 == 0) goto L_0x0051
            r0.put(r4, r5)
        L_0x0051:
            int r3 = r3 + 1
            goto L_0x000f
        L_0x0054:
            com.google.android.gms.measurement.internal.zzje r9 = new com.google.android.gms.measurement.internal.zzje
            r1 = 100
            r9.<init>(r0, r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzje.zza(com.google.android.gms.measurement.internal.zzje):com.google.android.gms.measurement.internal.zzje");
    }

    public final zzje zzb(zzje zzje) {
        EnumMap enumMap = new EnumMap(zza.class);
        for (zza zza2 : zzjg.STORAGE.zzd) {
            zzjh zzjh = this.zzb.get(zza2);
            if (zzjh == zzjh.UNINITIALIZED) {
                zzjh = zzje.zzb.get(zza2);
            }
            if (zzjh != null) {
                enumMap.put(zza2, zzjh);
            }
        }
        return new zzje(enumMap, this.zzc);
    }

    static String zzb(zzjh zzjh) {
        int ordinal = zzjh.ordinal();
        if (ordinal == 2) {
            return "denied";
        }
        if (ordinal != 3) {
            return null;
        }
        return PermissionsResponse.GRANTED_KEY;
    }

    public static String zza(Bundle bundle) {
        String string;
        zza[] zza2 = zzjg.STORAGE.zzd;
        int length = zza2.length;
        int i = 0;
        while (true) {
            Boolean bool = null;
            if (i >= length) {
                return null;
            }
            zza zza3 = zza2[i];
            if (bundle.containsKey(zza3.zze) && (string = bundle.getString(zza3.zze)) != null) {
                if (string != null) {
                    if (string.equals(PermissionsResponse.GRANTED_KEY)) {
                        bool = Boolean.TRUE;
                    } else if (string.equals("denied")) {
                        bool = Boolean.FALSE;
                    }
                }
                if (bool == null) {
                    return string;
                }
            }
            i++;
        }
    }

    public final String zze() {
        int ordinal;
        StringBuilder sb = new StringBuilder("G1");
        for (zza zza2 : zzjg.STORAGE.zza()) {
            zzjh zzjh = this.zzb.get(zza2);
            char c = '-';
            if (!(zzjh == null || (ordinal = zzjh.ordinal()) == 0)) {
                if (ordinal != 1) {
                    if (ordinal == 2) {
                        c = '0';
                    } else if (ordinal != 3) {
                    }
                }
                c = '1';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public final String zzf() {
        StringBuilder sb = new StringBuilder("G1");
        for (zza zza2 : zzjg.STORAGE.zza()) {
            sb.append(zza(this.zzb.get(zza2)));
        }
        return sb.toString();
    }

    public final String toString() {
        StringBuilder append = new StringBuilder("source=").append(zza(this.zzc));
        for (zza zza2 : zzjg.STORAGE.zzd) {
            append.append(",");
            append.append(zza2.zze);
            append.append("=");
            zzjh zzjh = this.zzb.get(zza2);
            if (zzjh == null) {
                zzjh = zzjh.UNINITIALIZED;
            }
            append.append(zzjh);
        }
        return append.toString();
    }

    private zzje(EnumMap<zza, zzjh> enumMap, int i) {
        EnumMap<zza, zzjh> enumMap2 = new EnumMap<>(zza.class);
        this.zzb = enumMap2;
        enumMap2.putAll(enumMap);
        this.zzc = i;
    }

    public zzje(Boolean bool, Boolean bool2, int i) {
        EnumMap<zza, zzjh> enumMap = new EnumMap<>(zza.class);
        this.zzb = enumMap;
        enumMap.put(zza.AD_STORAGE, zza((Boolean) null));
        enumMap.put(zza.ANALYTICS_STORAGE, zza((Boolean) null));
        this.zzc = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzje)) {
            return false;
        }
        zzje zzje = (zzje) obj;
        for (zza zza2 : zzjg.STORAGE.zzd) {
            if (this.zzb.get(zza2) != zzje.zzb.get(zza2)) {
                return false;
            }
        }
        if (this.zzc == zzje.zzc) {
            return true;
        }
        return false;
    }

    public final boolean zza(zzje zzje, zza... zzaArr) {
        for (zza zza2 : zzaArr) {
            if (!zzje.zza(zza2) && zza(zza2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzg() {
        return zza(zza.AD_STORAGE);
    }

    public final boolean zza(zza zza2) {
        return this.zzb.get(zza2) != zzjh.DENIED;
    }

    public final boolean zzh() {
        return zza(zza.ANALYTICS_STORAGE);
    }

    public final boolean zzi() {
        for (zzjh zzjh : this.zzb.values()) {
            if (zzjh != zzjh.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzc(zzje zzje) {
        return zzb(zzje, (zza[]) this.zzb.keySet().toArray(new zza[0]));
    }

    public final boolean zzb(zzje zzje, zza... zzaArr) {
        for (zza zza2 : zzaArr) {
            zzjh zzjh = this.zzb.get(zza2);
            zzjh zzjh2 = zzje.zzb.get(zza2);
            if (zzjh == zzjh.DENIED && zzjh2 != zzjh.DENIED) {
                return true;
            }
        }
        return false;
    }
}
