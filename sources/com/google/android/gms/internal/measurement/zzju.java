package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
final class zzju implements zzkz {
    private static final zzju zza = new zzju();

    public static zzju zza() {
        return zza;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzla zza(java.lang.Class<?> r5) {
        /*
            r4 = this;
            java.lang.Class<com.google.android.gms.internal.measurement.zzjt> r0 = com.google.android.gms.internal.measurement.zzjt.class
            boolean r0 = r0.isAssignableFrom(r5)
            if (r0 == 0) goto L_0x0036
            java.lang.Class<com.google.android.gms.internal.measurement.zzjt> r0 = com.google.android.gms.internal.measurement.zzjt.class
            java.lang.Class r0 = r5.asSubclass(r0)     // Catch:{ Exception -> 0x001c }
            com.google.android.gms.internal.measurement.zzjt r0 = com.google.android.gms.internal.measurement.zzjt.zza(r0)     // Catch:{ Exception -> 0x001c }
            int r1 = com.google.android.gms.internal.measurement.zzjt.zze.zzc     // Catch:{ Exception -> 0x001c }
            r2 = 0
            java.lang.Object r0 = r0.zza((int) r1, (java.lang.Object) r2, (java.lang.Object) r2)     // Catch:{ Exception -> 0x001c }
            com.google.android.gms.internal.measurement.zzla r0 = (com.google.android.gms.internal.measurement.zzla) r0     // Catch:{ Exception -> 0x001c }
            return r0
        L_0x001c:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r5 = r5.getName()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unable to get message info for "
            r2.<init>(r3)
            java.lang.StringBuilder r5 = r2.append(r5)
            java.lang.String r5 = r5.toString()
            r1.<init>(r5, r0)
            throw r1
        L_0x0036:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r5 = r5.getName()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unsupported message type: "
            r1.<init>(r2)
            java.lang.StringBuilder r5 = r1.append(r5)
            java.lang.String r5 = r5.toString()
            r0.<init>(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzju.zza(java.lang.Class):com.google.android.gms.internal.measurement.zzla");
    }

    private zzju() {
    }

    public final boolean zzb(Class<?> cls) {
        return zzjt.class.isAssignableFrom(cls);
    }
}
