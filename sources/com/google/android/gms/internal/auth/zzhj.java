package com.google.android.gms.internal.auth;

import io.sentry.SentryLockReason;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import libcore.io.Memory;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzhj {
    static final boolean zza;
    private static final Unsafe zzb;
    private static final Class zzc = Memory.class;
    private static final boolean zzd;
    private static final zzhi zze;
    private static final boolean zzf;
    private static final boolean zzg;

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0150  */
    static {
        /*
            sun.misc.Unsafe r0 = zzg()
            zzb = r0
            int r1 = com.google.android.gms.internal.auth.zzds.zza
            java.lang.Class<libcore.io.Memory> r1 = libcore.io.Memory.class
            zzc = r1
            java.lang.Class r1 = java.lang.Long.TYPE
            boolean r1 = zzs(r1)
            zzd = r1
            java.lang.Class r2 = java.lang.Integer.TYPE
            boolean r2 = zzs(r2)
            r3 = 0
            if (r0 != 0) goto L_0x001e
            goto L_0x002d
        L_0x001e:
            if (r1 == 0) goto L_0x0026
            com.google.android.gms.internal.auth.zzhh r3 = new com.google.android.gms.internal.auth.zzhh
            r3.<init>(r0)
            goto L_0x002d
        L_0x0026:
            if (r2 == 0) goto L_0x002d
            com.google.android.gms.internal.auth.zzhg r3 = new com.google.android.gms.internal.auth.zzhg
            r3.<init>(r0)
        L_0x002d:
            zze = r3
            java.lang.String r0 = "getLong"
            java.lang.String r1 = "objectFieldOffset"
            r2 = 2
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L_0x003a
        L_0x0038:
            r3 = r5
            goto L_0x0064
        L_0x003a:
            sun.misc.Unsafe r3 = r3.zza
            java.lang.Class r3 = r3.getClass()     // Catch:{ all -> 0x005f }
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x005f }
            java.lang.Class<java.lang.reflect.Field> r7 = java.lang.reflect.Field.class
            r6[r5] = r7     // Catch:{ all -> 0x005f }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x005f }
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ all -> 0x005f }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r5] = r7     // Catch:{ all -> 0x005f }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x005f }
            r6[r4] = r7     // Catch:{ all -> 0x005f }
            r3.getMethod(r0, r6)     // Catch:{ all -> 0x005f }
            java.lang.reflect.Field r3 = zzy()     // Catch:{ all -> 0x005f }
            if (r3 != 0) goto L_0x005d
            goto L_0x0038
        L_0x005d:
            r3 = r4
            goto L_0x0064
        L_0x005f:
            r3 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.auth.zzhj.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r3.toString()))
            goto L_0x0038
        L_0x0064:
            zzf = r3
            com.google.android.gms.internal.auth.zzhi r3 = zze
            if (r3 != 0) goto L_0x006d
        L_0x006a:
            r0 = r5
            goto L_0x00ff
        L_0x006d:
            sun.misc.Unsafe r3 = r3.zza
            java.lang.Class r3 = r3.getClass()     // Catch:{ all -> 0x00f9 }
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.reflect.Field> r7 = java.lang.reflect.Field.class
            r6[r5] = r7     // Catch:{ all -> 0x00f9 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f9 }
            java.lang.String r1 = "arrayBaseOffset"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Class> r7 = java.lang.Class.class
            r6[r5] = r7     // Catch:{ all -> 0x00f9 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f9 }
            java.lang.String r1 = "arrayIndexScale"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Class> r7 = java.lang.Class.class
            r6[r5] = r7     // Catch:{ all -> 0x00f9 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f9 }
            java.lang.String r1 = "getInt"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r5] = r7     // Catch:{ all -> 0x00f9 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r6[r4] = r7     // Catch:{ all -> 0x00f9 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f9 }
            java.lang.String r1 = "putInt"
            r6 = 3
            java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r8 = java.lang.Object.class
            r7[r5] = r8     // Catch:{ all -> 0x00f9 }
            java.lang.Class r8 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r7[r4] = r8     // Catch:{ all -> 0x00f9 }
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch:{ all -> 0x00f9 }
            r7[r2] = r8     // Catch:{ all -> 0x00f9 }
            r3.getMethod(r1, r7)     // Catch:{ all -> 0x00f9 }
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r1[r5] = r7     // Catch:{ all -> 0x00f9 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r1[r4] = r7     // Catch:{ all -> 0x00f9 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f9 }
            java.lang.String r0 = "putLong"
            java.lang.Class[] r1 = new java.lang.Class[r6]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r1[r5] = r7     // Catch:{ all -> 0x00f9 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r1[r4] = r7     // Catch:{ all -> 0x00f9 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r1[r2] = r7     // Catch:{ all -> 0x00f9 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f9 }
            java.lang.String r0 = "getObject"
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r1[r5] = r7     // Catch:{ all -> 0x00f9 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r1[r4] = r7     // Catch:{ all -> 0x00f9 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f9 }
            java.lang.String r0 = "putObject"
            java.lang.Class[] r1 = new java.lang.Class[r6]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            r1[r5] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r1[r4] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            r1[r2] = r6     // Catch:{ all -> 0x00f9 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f9 }
            r0 = r4
            goto L_0x00ff
        L_0x00f9:
            r0 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.auth.zzhj.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r0.toString()))
            goto L_0x006a
        L_0x00ff:
            zzg = r0
            java.lang.Class<byte[]> r0 = byte[].class
            zzw(r0)
            java.lang.Class<boolean[]> r0 = boolean[].class
            zzw(r0)
            zzx(r0)
            java.lang.Class<int[]> r0 = int[].class
            zzw(r0)
            zzx(r0)
            java.lang.Class<long[]> r0 = long[].class
            zzw(r0)
            zzx(r0)
            java.lang.Class<float[]> r0 = float[].class
            zzw(r0)
            zzx(r0)
            java.lang.Class<double[]> r0 = double[].class
            zzw(r0)
            zzx(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzw(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzx(r0)
            java.lang.reflect.Field r0 = zzy()
            if (r0 == 0) goto L_0x0147
            com.google.android.gms.internal.auth.zzhi r1 = zze
            if (r1 == 0) goto L_0x0147
            sun.misc.Unsafe r1 = r1.zza
            r1.objectFieldOffset(r0)
        L_0x0147:
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x0150
            goto L_0x0151
        L_0x0150:
            r4 = r5
        L_0x0151:
            zza = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzhj.<clinit>():void");
    }

    private zzhj() {
    }

    static double zza(Object obj, long j) {
        return zze.zza(obj, j);
    }

    static float zzb(Object obj, long j) {
        return zze.zzb(obj, j);
    }

    static int zzc(Object obj, long j) {
        return zze.zza.getInt(obj, j);
    }

    static long zzd(Object obj, long j) {
        return zze.zza.getLong(obj, j);
    }

    static Object zze(Class cls) {
        try {
            return zzb.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static Object zzf(Object obj, long j) {
        return zze.zza.getObject(obj, j);
    }

    static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzhf());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* synthetic */ void zzi(Object obj, long j, boolean z) {
        zzhi zzhi = zze;
        long j2 = -4 & j;
        int i = zzhi.zza.getInt(obj, j2);
        int i2 = ((~((int) j)) & 3) << 3;
        zzhi.zza.putInt(obj, j2, ((z ? 1 : 0) << i2) | ((~(255 << i2)) & i));
    }

    static /* synthetic */ void zzj(Object obj, long j, boolean z) {
        zzhi zzhi = zze;
        long j2 = -4 & j;
        int i = zzhi.zza.getInt(obj, j2);
        int i2 = (((int) j) & 3) << 3;
        zzhi.zza.putInt(obj, j2, ((z ? 1 : 0) << i2) | ((~(255 << i2)) & i));
    }

    static void zzk(Object obj, long j, boolean z) {
        zze.zzc(obj, j, z);
    }

    static void zzl(Object obj, long j, double d) {
        zze.zzd(obj, j, d);
    }

    static void zzm(Object obj, long j, float f) {
        zze.zze(obj, j, f);
    }

    static void zzn(Object obj, long j, int i) {
        zze.zza.putInt(obj, j, i);
    }

    static void zzo(Object obj, long j, long j2) {
        zze.zza.putLong(obj, j, j2);
    }

    static void zzp(Object obj, long j, Object obj2) {
        zze.zza.putObject(obj, j, obj2);
    }

    static /* bridge */ /* synthetic */ boolean zzq(Object obj, long j) {
        return ((byte) ((zze.zza.getInt(obj, -4 & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    static /* bridge */ /* synthetic */ boolean zzr(Object obj, long j) {
        return ((byte) ((zze.zza.getInt(obj, -4 & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    static boolean zzs(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        int i = zzds.zza;
        try {
            Class cls3 = zzc;
            cls3.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls3.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls3.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static boolean zzt(Object obj, long j) {
        return zze.zzf(obj, j);
    }

    static boolean zzu() {
        return zzg;
    }

    static boolean zzv() {
        return zzf;
    }

    private static int zzw(Class cls) {
        if (zzg) {
            return zze.zza.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzx(Class cls) {
        if (zzg) {
            return zze.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    private static Field zzy() {
        int i = zzds.zza;
        Field zzz = zzz(Buffer.class, "effectiveDirectAddress");
        if (zzz != null) {
            return zzz;
        }
        Field zzz2 = zzz(Buffer.class, SentryLockReason.JsonKeys.ADDRESS);
        if (zzz2 == null || zzz2.getType() != Long.TYPE) {
            return null;
        }
        return zzz2;
    }

    private static Field zzz(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
