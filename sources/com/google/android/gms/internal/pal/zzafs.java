package com.google.android.gms.internal.pal;

import io.sentry.SentryLockReason;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzafs {
    static final long zza = ((long) zzz(byte[].class));
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd = zzabk.zza();
    private static final boolean zze;
    private static final zzafr zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0152  */
    static {
        /*
            sun.misc.Unsafe r0 = zzg()
            zzc = r0
            java.lang.Class r1 = com.google.android.gms.internal.pal.zzabk.zza()
            zzd = r1
            java.lang.Class r1 = java.lang.Long.TYPE
            boolean r1 = zzv(r1)
            zze = r1
            java.lang.Class r2 = java.lang.Integer.TYPE
            boolean r2 = zzv(r2)
            r3 = 0
            if (r0 != 0) goto L_0x001e
            goto L_0x002d
        L_0x001e:
            if (r1 == 0) goto L_0x0026
            com.google.android.gms.internal.pal.zzafq r3 = new com.google.android.gms.internal.pal.zzafq
            r3.<init>(r0)
            goto L_0x002d
        L_0x0026:
            if (r2 == 0) goto L_0x002d
            com.google.android.gms.internal.pal.zzafp r3 = new com.google.android.gms.internal.pal.zzafp
            r3.<init>(r0)
        L_0x002d:
            zzf = r3
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
            java.lang.reflect.Field r3 = zzB()     // Catch:{ all -> 0x005f }
            if (r3 != 0) goto L_0x005d
            goto L_0x0038
        L_0x005d:
            r3 = r4
            goto L_0x0064
        L_0x005f:
            r3 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.pal.zzafs.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r3.toString()))
            goto L_0x0038
        L_0x0064:
            zzg = r3
            com.google.android.gms.internal.pal.zzafr r3 = zzf
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
            java.lang.Class[] r1 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Class> r6 = java.lang.Class.class
            r1[r5] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.String r6 = "arrayBaseOffset"
            r3.getMethod(r6, r1)     // Catch:{ all -> 0x00f9 }
            java.lang.Class[] r1 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Class> r6 = java.lang.Class.class
            r1[r5] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.String r6 = "arrayIndexScale"
            r3.getMethod(r6, r1)     // Catch:{ all -> 0x00f9 }
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            r1[r5] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r1[r4] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.String r6 = "getInt"
            r3.getMethod(r6, r1)     // Catch:{ all -> 0x00f9 }
            r1 = 3
            java.lang.Class[] r6 = new java.lang.Class[r1]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r5] = r7     // Catch:{ all -> 0x00f9 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r6[r4] = r7     // Catch:{ all -> 0x00f9 }
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ all -> 0x00f9 }
            r6[r2] = r7     // Catch:{ all -> 0x00f9 }
            java.lang.String r7 = "putInt"
            r3.getMethod(r7, r6)     // Catch:{ all -> 0x00f9 }
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r5] = r7     // Catch:{ all -> 0x00f9 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r6[r4] = r7     // Catch:{ all -> 0x00f9 }
            r3.getMethod(r0, r6)     // Catch:{ all -> 0x00f9 }
            java.lang.Class[] r0 = new java.lang.Class[r1]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            r0[r5] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r0[r4] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r0[r2] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.String r6 = "putLong"
            r3.getMethod(r6, r0)     // Catch:{ all -> 0x00f9 }
            java.lang.Class[] r0 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            r0[r5] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.Class r6 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r0[r4] = r6     // Catch:{ all -> 0x00f9 }
            java.lang.String r6 = "getObject"
            r3.getMethod(r6, r0)     // Catch:{ all -> 0x00f9 }
            java.lang.Class[] r0 = new java.lang.Class[r1]     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r0[r5] = r1     // Catch:{ all -> 0x00f9 }
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f9 }
            r0[r4] = r1     // Catch:{ all -> 0x00f9 }
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r0[r2] = r1     // Catch:{ all -> 0x00f9 }
            java.lang.String r1 = "putObject"
            r3.getMethod(r1, r0)     // Catch:{ all -> 0x00f9 }
            r0 = r4
            goto L_0x00ff
        L_0x00f9:
            r0 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.pal.zzafs.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r0.toString()))
            goto L_0x006a
        L_0x00ff:
            zzh = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzz(r0)
            long r0 = (long) r0
            zza = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<int[]> r0 = int[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<long[]> r0 = long[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<float[]> r0 = float[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<double[]> r0 = double[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzz(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzA(r0)
            java.lang.reflect.Field r0 = zzB()
            if (r0 == 0) goto L_0x0149
            com.google.android.gms.internal.pal.zzafr r1 = zzf
            if (r1 == 0) goto L_0x0149
            r1.zzl(r0)
        L_0x0149:
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x0152
            goto L_0x0153
        L_0x0152:
            r4 = r5
        L_0x0153:
            zzb = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzafs.<clinit>():void");
    }

    private zzafs() {
    }

    private static int zzA(Class cls) {
        if (zzh) {
            return zzf.zzi(cls);
        }
        return -1;
    }

    private static Field zzB() {
        int i = zzabk.zza;
        Field zzC = zzC(Buffer.class, "effectiveDirectAddress");
        if (zzC != null) {
            return zzC;
        }
        Field zzC2 = zzC(Buffer.class, SentryLockReason.JsonKeys.ADDRESS);
        if (zzC2 == null || zzC2.getType() != Long.TYPE) {
            return null;
        }
        return zzC2;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzD(Object obj, long j, byte b) {
        long j2 = -4 & j;
        zzafr zzafr = zzf;
        int zzj = zzafr.zzj(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzafr.zzn(obj, j2, ((255 & b) << i) | (zzj & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static void zzE(Object obj, long j, byte b) {
        long j2 = -4 & j;
        zzafr zzafr = zzf;
        int i = (((int) j) & 3) << 3;
        zzafr.zzn(obj, j2, ((255 & b) << i) | (zzafr.zzj(obj, j2) & (~(255 << i))));
    }

    static double zza(Object obj, long j) {
        return zzf.zza(obj, j);
    }

    static float zzb(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    static int zzc(Object obj, long j) {
        return zzf.zzj(obj, j);
    }

    static long zzd(Object obj, long j) {
        return zzf.zzk(obj, j);
    }

    static Object zze(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static Object zzf(Object obj, long j) {
        return zzf.zzm(obj, j);
    }

    static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzafo());
        } catch (Throwable unused) {
            return null;
        }
    }

    static void zzm(Object obj, long j, boolean z) {
        zzf.zzc(obj, j, z);
    }

    static void zzn(byte[] bArr, long j, byte b) {
        zzf.zzd(bArr, zza + j, b);
    }

    static void zzo(Object obj, long j, double d) {
        zzf.zze(obj, j, d);
    }

    static void zzp(Object obj, long j, float f) {
        zzf.zzf(obj, j, f);
    }

    static void zzq(Object obj, long j, int i) {
        zzf.zzn(obj, j, i);
    }

    static void zzr(Object obj, long j, long j2) {
        zzf.zzo(obj, j, j2);
    }

    static void zzs(Object obj, long j, Object obj2) {
        zzf.zzp(obj, j, obj2);
    }

    static /* bridge */ /* synthetic */ boolean zzt(Object obj, long j) {
        return ((byte) ((zzf.zzj(obj, -4 & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    static /* bridge */ /* synthetic */ boolean zzu(Object obj, long j) {
        return ((byte) ((zzf.zzj(obj, -4 & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    static boolean zzv(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        int i = zzabk.zza;
        try {
            Class cls3 = zzd;
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

    static boolean zzw(Object obj, long j) {
        return zzf.zzg(obj, j);
    }

    static boolean zzx() {
        return zzh;
    }

    static boolean zzy() {
        return zzg;
    }

    private static int zzz(Class cls) {
        if (zzh) {
            return zzf.zzh(cls);
        }
        return -1;
    }
}
