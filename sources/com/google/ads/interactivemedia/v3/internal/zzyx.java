package com.google.ads.interactivemedia.v3.internal;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzyx implements zzwk {
    private static final zzwk zza = new zzyw((zzyv) null);
    private static final zzwk zzb = new zzyw((zzyv) null);
    private final zzxl zzc;
    private final ConcurrentMap zzd = new ConcurrentHashMap();

    public zzyx(zzxl zzxl) {
        this.zzc = zzxl;
    }

    private final zzwk zzd(Class cls, zzwk zzwk) {
        zzwk zzwk2 = (zzwk) this.zzd.putIfAbsent(cls, zzwk);
        return zzwk2 != null ? zzwk2 : zzwk;
    }

    private static zzwl zze(Class cls) {
        return (zzwl) cls.getAnnotation(zzwl.class);
    }

    private static Object zzf(zzxl zzxl, Class cls) {
        return zzxl.zza(zzaca.zza(cls)).zza();
    }

    public final zzwj zza(zzvr zzvr, zzaca zzaca) {
        zzwl zze = zze(zzaca.zzc());
        if (zze == null) {
            return null;
        }
        return zzb(this.zzc, zzvr, zzaca, zze, true);
    }

    /* JADX WARNING: type inference failed for: r9v16, types: [com.google.ads.interactivemedia.v3.internal.zzwj] */
    /* JADX WARNING: type inference failed for: r9v18, types: [com.google.ads.interactivemedia.v3.internal.zzwj] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.ads.interactivemedia.v3.internal.zzwj zzb(com.google.ads.interactivemedia.v3.internal.zzxl r9, com.google.ads.interactivemedia.v3.internal.zzvr r10, com.google.ads.interactivemedia.v3.internal.zzaca r11, com.google.ads.interactivemedia.v3.internal.zzwl r12, boolean r13) {
        /*
            r8 = this;
            java.lang.Class r0 = r12.zza()
            java.lang.Object r9 = zzf(r9, r0)
            boolean r0 = r9 instanceof com.google.ads.interactivemedia.v3.internal.zzwj
            boolean r7 = r12.zzb()
            if (r0 == 0) goto L_0x0014
            com.google.ads.interactivemedia.v3.internal.zzwj r9 = (com.google.ads.interactivemedia.v3.internal.zzwj) r9
            goto L_0x007f
        L_0x0014:
            boolean r12 = r9 instanceof com.google.ads.interactivemedia.v3.internal.zzwk
            if (r12 == 0) goto L_0x0029
            com.google.ads.interactivemedia.v3.internal.zzwk r9 = (com.google.ads.interactivemedia.v3.internal.zzwk) r9
            if (r13 == 0) goto L_0x0024
            java.lang.Class r12 = r11.zzc()
            com.google.ads.interactivemedia.v3.internal.zzwk r9 = r8.zzd(r12, r9)
        L_0x0024:
            com.google.ads.interactivemedia.v3.internal.zzwj r9 = r9.zza(r10, r11)
            goto L_0x007f
        L_0x0029:
            boolean r12 = r9 instanceof com.google.ads.interactivemedia.v3.internal.zzwd
            r0 = 0
            if (r12 != 0) goto L_0x0061
            boolean r12 = r9 instanceof com.google.ads.interactivemedia.v3.internal.zzvv
            if (r12 == 0) goto L_0x0034
            r2 = r0
            goto L_0x0065
        L_0x0034:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.Class r9 = r9.getClass()
            java.lang.String r9 = r9.getName()
            java.lang.String r11 = r11.toString()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "Invalid attempt to bind an instance of "
            r12.<init>(r13)
            r12.append(r9)
            java.lang.String r9 = " as a @JsonAdapter for "
            r12.append(r9)
            r12.append(r11)
            java.lang.String r9 = ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer."
            r12.append(r9)
            java.lang.String r9 = r12.toString()
            r10.<init>(r9)
            throw r10
        L_0x0061:
            r12 = r9
            com.google.ads.interactivemedia.v3.internal.zzwd r12 = (com.google.ads.interactivemedia.v3.internal.zzwd) r12
            r2 = r12
        L_0x0065:
            boolean r12 = r9 instanceof com.google.ads.interactivemedia.v3.internal.zzvv
            if (r12 == 0) goto L_0x006d
            com.google.ads.interactivemedia.v3.internal.zzvv r9 = (com.google.ads.interactivemedia.v3.internal.zzvv) r9
            r3 = r9
            goto L_0x006e
        L_0x006d:
            r3 = r0
        L_0x006e:
            if (r13 == 0) goto L_0x0073
            com.google.ads.interactivemedia.v3.internal.zzwk r9 = zza
            goto L_0x0075
        L_0x0073:
            com.google.ads.interactivemedia.v3.internal.zzwk r9 = zzb
        L_0x0075:
            r6 = r9
            com.google.ads.interactivemedia.v3.internal.zzzv r9 = new com.google.ads.interactivemedia.v3.internal.zzzv
            r1 = r9
            r4 = r10
            r5 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r7 = 0
        L_0x007f:
            if (r9 == 0) goto L_0x0087
            if (r7 == 0) goto L_0x0087
            com.google.ads.interactivemedia.v3.internal.zzwj r9 = r9.nullSafe()
        L_0x0087:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzyx.zzb(com.google.ads.interactivemedia.v3.internal.zzxl, com.google.ads.interactivemedia.v3.internal.zzvr, com.google.ads.interactivemedia.v3.internal.zzaca, com.google.ads.interactivemedia.v3.internal.zzwl, boolean):com.google.ads.interactivemedia.v3.internal.zzwj");
    }

    public final boolean zzc(zzaca zzaca, zzwk zzwk) {
        Objects.requireNonNull(zzaca);
        Objects.requireNonNull(zzwk);
        if (zzwk == zza) {
            return true;
        }
        Class zzc2 = zzaca.zzc();
        zzwk zzwk2 = (zzwk) this.zzd.get(zzc2);
        if (zzwk2 == null) {
            zzwl zze = zze(zzc2);
            if (zze == null) {
                return false;
            }
            Class zza2 = zze.zza();
            if (zzwk.class.isAssignableFrom(zza2) && zzd(zzc2, (zzwk) zzf(this.zzc, zza2)) == zzwk) {
                return true;
            }
            return false;
        } else if (zzwk2 == zzwk) {
            return true;
        } else {
            return false;
        }
    }
}
