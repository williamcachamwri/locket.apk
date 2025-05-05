package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import androidx.media3.common.C;
import com.facebook.soloader.Elf64;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzfr<T> implements zzgh<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzhi.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzfo zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzfc zzl;
    private final zzgy zzm;
    private final zzdp zzn;
    private final int zzo;
    private final zzfu zzp;
    private final zzfj zzq;

    private zzfr(int[] iArr, Object[] objArr, int i, int i2, zzfo zzfo, int i3, boolean z, int[] iArr2, int i4, int i5, zzfu zzfu, zzfc zzfc, zzgy zzgy, zzdp zzdp, zzfj zzfj) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzo = i3;
        boolean z2 = false;
        if (zzdp != null && zzdp.zzg(zzfo)) {
            z2 = true;
        }
        this.zzh = z2;
        this.zzi = iArr2;
        this.zzj = i4;
        this.zzk = i5;
        this.zzp = zzfu;
        this.zzl = zzfc;
        this.zzm = zzgy;
        this.zzn = zzdp;
        this.zzg = zzfo;
        this.zzq = zzfj;
    }

    private final zzeh zzA(int i) {
        int i2 = i / 3;
        return (zzeh) this.zzd[i2 + i2 + 1];
    }

    private final zzgh zzB(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzgh zzgh = (zzgh) this.zzd[i3];
        if (zzgh != null) {
            return zzgh;
        }
        zzgh zzb2 = zzfx.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzC(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzD(Object obj, int i) {
        zzgh zzB = zzB(i);
        int zzy = zzy(i) & 1048575;
        if (!zzP(obj, i)) {
            return zzB.zze();
        }
        Object object = zzb.getObject(obj, (long) zzy);
        if (zzS(object)) {
            return object;
        }
        Object zze2 = zzB.zze();
        if (object != null) {
            zzB.zzg(zze2, object);
        }
        return zze2;
    }

    private final Object zzE(Object obj, int i, int i2) {
        zzgh zzB = zzB(i2);
        if (!zzT(obj, i, i2)) {
            return zzB.zze();
        }
        Object object = zzb.getObject(obj, (long) (zzy(i2) & 1048575));
        if (zzS(object)) {
            return object;
        }
        Object zze2 = zzB.zze();
        if (object != null) {
            zzB.zzg(zze2, object);
        }
        return zze2;
    }

    private static Field zzF(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private static void zzG(Object obj) {
        if (!zzS(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzH(Object obj, Object obj2, int i) {
        if (zzP(obj2, i)) {
            Unsafe unsafe = zzb;
            long zzy = (long) (zzy(i) & 1048575);
            Object object = unsafe.getObject(obj2, zzy);
            if (object != null) {
                zzgh zzB = zzB(i);
                if (!zzP(obj, i)) {
                    if (!zzS(object)) {
                        unsafe.putObject(obj, zzy, object);
                    } else {
                        Object zze2 = zzB.zze();
                        zzB.zzg(zze2, object);
                        unsafe.putObject(obj, zzy, zze2);
                    }
                    zzJ(obj, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzy);
                if (!zzS(object2)) {
                    Object zze3 = zzB.zze();
                    zzB.zzg(zze3, object2);
                    unsafe.putObject(obj, zzy, zze3);
                    object2 = zze3;
                }
                zzB.zzg(object2, object);
                return;
            }
            int i2 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i2 + " is present but null: " + obj3);
        }
    }

    private final void zzI(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzT(obj2, i2, i)) {
            Unsafe unsafe = zzb;
            long zzy = (long) (zzy(i) & 1048575);
            Object object = unsafe.getObject(obj2, zzy);
            if (object != null) {
                zzgh zzB = zzB(i);
                if (!zzT(obj, i2, i)) {
                    if (!zzS(object)) {
                        unsafe.putObject(obj, zzy, object);
                    } else {
                        Object zze2 = zzB.zze();
                        zzB.zzg(zze2, object);
                        unsafe.putObject(obj, zzy, zze2);
                    }
                    zzK(obj, i2, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzy);
                if (!zzS(object2)) {
                    Object zze3 = zzB.zze();
                    zzB.zzg(zze3, object2);
                    unsafe.putObject(obj, zzy, zze3);
                    object2 = zze3;
                }
                zzB.zzg(object2, object);
                return;
            }
            int i3 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i3 + " is present but null: " + obj3);
        }
    }

    private final void zzJ(Object obj, int i) {
        int zzv = zzv(i);
        long j = (long) (1048575 & zzv);
        if (j != 1048575) {
            zzhi.zzq(obj, j, (1 << (zzv >>> 20)) | zzhi.zzc(obj, j));
        }
    }

    private final void zzK(Object obj, int i, int i2) {
        zzhi.zzq(obj, (long) (zzv(i2) & 1048575), i);
    }

    private final void zzL(Object obj, int i, Object obj2) {
        zzb.putObject(obj, (long) (zzy(i) & 1048575), obj2);
        zzJ(obj, i);
    }

    private final void zzM(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, (long) (zzy(i2) & 1048575), obj2);
        zzK(obj, i, i2);
    }

    private final void zzN(zzhq zzhq, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzfh zzfh = (zzfh) zzC(i2);
            throw null;
        }
    }

    private final boolean zzO(Object obj, Object obj2, int i) {
        return zzP(obj, i) == zzP(obj2, i);
    }

    private final boolean zzP(Object obj, int i) {
        int zzv = zzv(i);
        long j = (long) (zzv & 1048575);
        if (j == 1048575) {
            int zzy = zzy(i);
            long j2 = (long) (zzy & 1048575);
            switch (zzx(zzy)) {
                case 0:
                    return Double.doubleToRawLongBits(zzhi.zza(obj, j2)) != 0;
                case 1:
                    return Float.floatToRawIntBits(zzhi.zzb(obj, j2)) != 0;
                case 2:
                    return zzhi.zzd(obj, j2) != 0;
                case 3:
                    return zzhi.zzd(obj, j2) != 0;
                case 4:
                    return zzhi.zzc(obj, j2) != 0;
                case 5:
                    return zzhi.zzd(obj, j2) != 0;
                case 6:
                    return zzhi.zzc(obj, j2) != 0;
                case 7:
                    return zzhi.zzw(obj, j2);
                case 8:
                    Object zzf2 = zzhi.zzf(obj, j2);
                    if (zzf2 instanceof String) {
                        return !((String) zzf2).isEmpty();
                    }
                    if (zzf2 instanceof zzdb) {
                        return !zzdb.zzb.equals(zzf2);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzhi.zzf(obj, j2) != null;
                case 10:
                    return !zzdb.zzb.equals(zzhi.zzf(obj, j2));
                case 11:
                    return zzhi.zzc(obj, j2) != 0;
                case 12:
                    return zzhi.zzc(obj, j2) != 0;
                case 13:
                    return zzhi.zzc(obj, j2) != 0;
                case 14:
                    return zzhi.zzd(obj, j2) != 0;
                case 15:
                    return zzhi.zzc(obj, j2) != 0;
                case 16:
                    return zzhi.zzd(obj, j2) != 0;
                case 17:
                    return zzhi.zzf(obj, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzhi.zzc(obj, j) & (1 << (zzv >>> 20))) != 0;
        }
    }

    private final boolean zzQ(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzP(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzR(Object obj, int i, zzgh zzgh) {
        return zzgh.zzk(zzhi.zzf(obj, (long) (i & 1048575)));
    }

    private static boolean zzS(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzed) {
            return ((zzed) obj).zzX();
        }
        return true;
    }

    private final boolean zzT(Object obj, int i, int i2) {
        return zzhi.zzc(obj, (long) (zzv(i2) & 1048575)) == i;
    }

    private static boolean zzU(Object obj, long j) {
        return ((Boolean) zzhi.zzf(obj, j)).booleanValue();
    }

    private static final void zzV(int i, Object obj, zzhq zzhq) throws IOException {
        if (obj instanceof String) {
            zzhq.zzG(i, (String) obj);
        } else {
            zzhq.zzd(i, (zzdb) obj);
        }
    }

    static zzgz zzd(Object obj) {
        zzed zzed = (zzed) obj;
        zzgz zzgz = zzed.zzc;
        if (zzgz != zzgz.zzc()) {
            return zzgz;
        }
        zzgz zzf2 = zzgz.zzf();
        zzed.zzc = zzf2;
        return zzf2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x024f  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0252  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x026a  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x026d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr zzl(java.lang.Class r30, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl r31, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfu r32, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfc r33, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgy r34, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r35, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfj r36) {
        /*
            r0 = r31
            boolean r1 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfz
            if (r1 == 0) goto L_0x03e0
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfz r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfz) r0
            java.lang.String r1 = r0.zzd()
            int r2 = r1.length()
            r3 = 0
            char r4 = r1.charAt(r3)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r5) goto L_0x0025
            r4 = 1
        L_0x001b:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0026
            r4 = r7
            goto L_0x001b
        L_0x0025:
            r7 = 1
        L_0x0026:
            int r4 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0045
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0032:
            int r10 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0042
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r9
            r7 = r7 | r4
            int r9 = r9 + 13
            r4 = r10
            goto L_0x0032
        L_0x0042:
            int r4 = r4 << r9
            r7 = r7 | r4
            r4 = r10
        L_0x0045:
            if (r7 != 0) goto L_0x0057
            int[] r7 = zza
            r11 = r3
            r12 = r11
            r13 = r12
            r14 = r13
            r16 = r14
            r18 = r16
            r17 = r7
            r7 = r18
            goto L_0x0167
        L_0x0057:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0076
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0063:
            int r10 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0073
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r9
            r4 = r4 | r7
            int r9 = r9 + 13
            r7 = r10
            goto L_0x0063
        L_0x0073:
            int r7 = r7 << r9
            r4 = r4 | r7
            r7 = r10
        L_0x0076:
            int r9 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0095
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0082:
            int r11 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x0092
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r7 = r7 | r9
            int r10 = r10 + 13
            r9 = r11
            goto L_0x0082
        L_0x0092:
            int r9 = r9 << r10
            r7 = r7 | r9
            r9 = r11
        L_0x0095:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x00b4
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00a1:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00b1
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r11
            r9 = r9 | r10
            int r11 = r11 + 13
            r10 = r12
            goto L_0x00a1
        L_0x00b1:
            int r10 = r10 << r11
            r9 = r9 | r10
            r10 = r12
        L_0x00b4:
            int r11 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00d3
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00c0:
            int r13 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r5) goto L_0x00d0
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r10 = r10 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00c0
        L_0x00d0:
            int r11 = r11 << r12
            r10 = r10 | r11
            r11 = r13
        L_0x00d3:
            int r12 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r5) goto L_0x00f2
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00df:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x00ef
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00df
        L_0x00ef:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
        L_0x00f2:
            int r13 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x0111
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00fe:
            int r15 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r5) goto L_0x010e
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00fe
        L_0x010e:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x0111:
            int r14 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r5) goto L_0x0132
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x011d:
            int r16 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r5) goto L_0x012e
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x011d
        L_0x012e:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x0132:
            int r15 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r5) goto L_0x0155
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x013e:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r5) goto L_0x0150
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x013e
        L_0x0150:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
        L_0x0155:
            int r16 = r14 + r12
            int r13 = r16 + r13
            int r16 = r4 + r4
            int r16 = r16 + r7
            int[] r7 = new int[r13]
            r17 = r7
            r13 = r9
            r18 = r14
            r7 = r4
            r14 = r10
            r4 = r15
        L_0x0167:
            sun.misc.Unsafe r9 = zzb
            java.lang.Object[] r10 = r0.zze()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r15 = r0.zza()
            java.lang.Class r15 = r15.getClass()
            int r19 = r18 + r12
            int r12 = r11 + r11
            int r11 = r11 * 3
            int[] r11 = new int[r11]
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r20 = r3
            r21 = r20
            r22 = r18
            r23 = r19
        L_0x0187:
            if (r4 >= r2) goto L_0x03bb
            int r24 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x01af
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r3 = r24
            r24 = 13
        L_0x0197:
            int r25 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r5) goto L_0x01a9
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r24
            r4 = r4 | r3
            int r24 = r24 + 13
            r3 = r25
            goto L_0x0197
        L_0x01a9:
            int r3 = r3 << r24
            r4 = r4 | r3
            r3 = r25
            goto L_0x01b1
        L_0x01af:
            r3 = r24
        L_0x01b1:
            int r24 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r5) goto L_0x01d7
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r8 = r24
            r24 = 13
        L_0x01bf:
            int r25 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r5) goto L_0x01d1
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r24
            r3 = r3 | r8
            int r24 = r24 + 13
            r8 = r25
            goto L_0x01bf
        L_0x01d1:
            int r8 = r8 << r24
            r3 = r3 | r8
            r8 = r25
            goto L_0x01d9
        L_0x01d7:
            r8 = r24
        L_0x01d9:
            r6 = r3 & 1024(0x400, float:1.435E-42)
            if (r6 == 0) goto L_0x01e3
            int r6 = r21 + 1
            r17[r21] = r20
            r21 = r6
        L_0x01e3:
            r6 = r3 & 255(0xff, float:3.57E-43)
            r5 = 51
            if (r6 < r5) goto L_0x0285
            int r5 = r8 + 1
            char r8 = r1.charAt(r8)
            r26 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r8 < r2) goto L_0x0214
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r28 = 13
        L_0x01fa:
            int r29 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r2) goto L_0x020f
            r2 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r28
            r8 = r8 | r2
            int r28 = r28 + 13
            r5 = r29
            r2 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01fa
        L_0x020f:
            int r2 = r5 << r28
            r8 = r8 | r2
            r5 = r29
        L_0x0214:
            int r2 = r6 + -51
            r28 = r5
            r5 = 9
            if (r2 == r5) goto L_0x023b
            r5 = 17
            if (r2 != r5) goto L_0x0221
            goto L_0x023b
        L_0x0221:
            r5 = 12
            if (r2 != r5) goto L_0x0248
            int r2 = r0.zzc()
            r5 = 1
            if (r2 == r5) goto L_0x0230
            r2 = r3 & 2048(0x800, float:2.87E-42)
            if (r2 == 0) goto L_0x0248
        L_0x0230:
            int r2 = r20 / 3
            int r2 = r2 + r2
            int r2 = r2 + r5
            int r5 = r16 + 1
            r16 = r10[r16]
            r12[r2] = r16
            goto L_0x0246
        L_0x023b:
            int r2 = r20 / 3
            int r2 = r2 + r2
            r5 = 1
            int r2 = r2 + r5
            int r5 = r16 + 1
            r16 = r10[r16]
            r12[r2] = r16
        L_0x0246:
            r16 = r5
        L_0x0248:
            int r8 = r8 + r8
            r2 = r10[r8]
            boolean r5 = r2 instanceof java.lang.reflect.Field
            if (r5 == 0) goto L_0x0252
            java.lang.reflect.Field r2 = (java.lang.reflect.Field) r2
            goto L_0x025a
        L_0x0252:
            java.lang.String r2 = (java.lang.String) r2
            java.lang.reflect.Field r2 = zzF(r15, r2)
            r10[r8] = r2
        L_0x025a:
            r5 = r13
            r29 = r14
            long r13 = r9.objectFieldOffset(r2)
            int r2 = (int) r13
            int r8 = r8 + 1
            r13 = r10[r8]
            boolean r14 = r13 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x026d
            java.lang.reflect.Field r13 = (java.lang.reflect.Field) r13
            goto L_0x0275
        L_0x026d:
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = zzF(r15, r13)
            r10[r8] = r13
        L_0x0275:
            long r13 = r9.objectFieldOffset(r13)
            int r8 = (int) r13
            r27 = r5
            r24 = r8
            r25 = r28
            r8 = 0
            r28 = r1
            goto L_0x0386
        L_0x0285:
            r26 = r2
            r5 = r13
            r29 = r14
            int r2 = r16 + 1
            r13 = r10[r16]
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = zzF(r15, r13)
            r14 = 9
            if (r6 == r14) goto L_0x0307
            r14 = 17
            if (r6 != r14) goto L_0x029e
            goto L_0x0307
        L_0x029e:
            r14 = 27
            if (r6 == r14) goto L_0x02f7
            r14 = 49
            if (r6 != r14) goto L_0x02a7
            goto L_0x02f7
        L_0x02a7:
            r14 = 12
            if (r6 == r14) goto L_0x02df
            r14 = 30
            if (r6 == r14) goto L_0x02df
            r14 = 44
            if (r6 != r14) goto L_0x02b4
            goto L_0x02df
        L_0x02b4:
            r14 = 50
            if (r6 != r14) goto L_0x02db
            int r14 = r22 + 1
            r17[r22] = r20
            int r22 = r20 / 3
            int r27 = r2 + 1
            r2 = r10[r2]
            int r22 = r22 + r22
            r12[r22] = r2
            r2 = r3 & 2048(0x800, float:2.87E-42)
            if (r2 == 0) goto L_0x02d7
            int r22 = r22 + 1
            int r2 = r27 + 1
            r27 = r10[r27]
            r12[r22] = r27
            r27 = r5
            r22 = r14
            goto L_0x02dd
        L_0x02d7:
            r22 = r14
            r2 = r27
        L_0x02db:
            r27 = r5
        L_0x02dd:
            r5 = 1
            goto L_0x0314
        L_0x02df:
            int r14 = r0.zzc()
            r27 = r5
            r5 = 1
            if (r14 == r5) goto L_0x02ec
            r14 = r3 & 2048(0x800, float:2.87E-42)
            if (r14 == 0) goto L_0x0314
        L_0x02ec:
            int r14 = r20 / 3
            int r14 = r14 + r14
            int r14 = r14 + r5
            int r24 = r2 + 1
            r2 = r10[r2]
            r12[r14] = r2
            goto L_0x0304
        L_0x02f7:
            r27 = r5
            r5 = 1
            int r14 = r20 / 3
            int r14 = r14 + r14
            int r14 = r14 + r5
            int r24 = r2 + 1
            r2 = r10[r2]
            r12[r14] = r2
        L_0x0304:
            r2 = r24
            goto L_0x0314
        L_0x0307:
            r27 = r5
            r5 = 1
            int r14 = r20 / 3
            int r14 = r14 + r14
            int r14 = r14 + r5
            java.lang.Class r24 = r13.getType()
            r12[r14] = r24
        L_0x0314:
            long r13 = r9.objectFieldOffset(r13)
            int r13 = (int) r13
            r14 = r3 & 4096(0x1000, float:5.74E-42)
            r24 = 1048575(0xfffff, float:1.469367E-39)
            if (r14 == 0) goto L_0x036f
            r14 = 17
            if (r6 > r14) goto L_0x036f
            int r14 = r8 + 1
            char r8 = r1.charAt(r8)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r8 < r5) goto L_0x0349
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r24 = 13
        L_0x0333:
            int r25 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r5) goto L_0x0345
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r24
            r8 = r8 | r14
            int r24 = r24 + 13
            r14 = r25
            goto L_0x0333
        L_0x0345:
            int r14 = r14 << r24
            r8 = r8 | r14
            goto L_0x034b
        L_0x0349:
            r25 = r14
        L_0x034b:
            int r14 = r7 + r7
            int r24 = r8 / 32
            int r14 = r14 + r24
            r5 = r10[r14]
            r28 = r1
            boolean r1 = r5 instanceof java.lang.reflect.Field
            if (r1 == 0) goto L_0x035c
            java.lang.reflect.Field r5 = (java.lang.reflect.Field) r5
            goto L_0x0364
        L_0x035c:
            java.lang.String r5 = (java.lang.String) r5
            java.lang.reflect.Field r5 = zzF(r15, r5)
            r10[r14] = r5
        L_0x0364:
            r14 = r2
            long r1 = r9.objectFieldOffset(r5)
            int r1 = (int) r1
            int r8 = r8 % 32
            r24 = r1
            goto L_0x0375
        L_0x036f:
            r28 = r1
            r14 = r2
            r25 = r8
            r8 = 0
        L_0x0375:
            r1 = 18
            if (r6 < r1) goto L_0x0383
            r1 = 49
            if (r6 > r1) goto L_0x0383
            int r1 = r23 + 1
            r17[r23] = r13
            r23 = r1
        L_0x0383:
            r2 = r13
            r16 = r14
        L_0x0386:
            int r1 = r20 + 1
            r11[r20] = r4
            int r4 = r1 + 1
            r5 = r3 & 512(0x200, float:7.175E-43)
            if (r5 == 0) goto L_0x0393
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x0394
        L_0x0393:
            r5 = 0
        L_0x0394:
            r3 = r3 & 256(0x100, float:3.59E-43)
            if (r3 == 0) goto L_0x039b
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x039c
        L_0x039b:
            r3 = 0
        L_0x039c:
            int r6 = r6 << 20
            r3 = r3 | r5
            r3 = r3 | r6
            r2 = r2 | r3
            r11[r1] = r2
            int r20 = r4 + 1
            int r1 = r8 << 20
            r1 = r1 | r24
            r11[r4] = r1
            r4 = r25
            r2 = r26
            r13 = r27
            r1 = r28
            r14 = r29
            r3 = 0
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0187
        L_0x03bb:
            r27 = r13
            r29 = r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr r1 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r14 = r0.zza()
            int r15 = r0.zzc()
            r16 = 0
            r9 = r1
            r10 = r11
            r11 = r12
            r12 = r27
            r13 = r29
            r20 = r32
            r21 = r33
            r22 = r34
            r23 = r35
            r24 = r36
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            return r1
        L_0x03e0:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgv r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgv) r0
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr.zzl(java.lang.Class, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfl, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfu, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfc, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgy, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfj):com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr");
    }

    private static double zzm(Object obj, long j) {
        return ((Double) zzhi.zzf(obj, j)).doubleValue();
    }

    private static float zzn(Object obj, long j) {
        return ((Float) zzhi.zzf(obj, j)).floatValue();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x032c, code lost:
        r4 = r4 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x036c, code lost:
        r6 = r6 + r3;
        r12 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x03a0, code lost:
        r6 = r6 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x04ae, code lost:
        r3 = r3 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0561, code lost:
        r3 = r3 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0570, code lost:
        r3 = r3 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0574, code lost:
        r5 = r5 + 3;
        r4 = 1048575;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00f1, code lost:
        r3 = r3 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01af, code lost:
        r3 = r3 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01bf, code lost:
        r3 = r3 + 8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzo(java.lang.Object r16) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            sun.misc.Unsafe r2 = zzb
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r4
            r5 = 0
            r6 = 0
            r8 = 0
        L_0x000c:
            int[] r9 = r0.zzc
            int r9 = r9.length
            if (r5 >= r9) goto L_0x057b
            int r9 = r15.zzy(r5)
            int[] r10 = r0.zzc
            r11 = r10[r5]
            int r12 = zzx(r9)
            r13 = 17
            r14 = 1
            if (r12 > r13) goto L_0x0035
            int r13 = r5 + 2
            r10 = r10[r13]
            r13 = r10 & r4
            int r10 = r10 >>> 20
            if (r13 == r7) goto L_0x0032
            long r7 = (long) r13
            int r8 = r2.getInt(r1, r7)
            r7 = r13
        L_0x0032:
            int r10 = r14 << r10
            goto L_0x0036
        L_0x0035:
            r10 = 0
        L_0x0036:
            r9 = r9 & r4
            long r3 = (long) r9
            r9 = 63
            switch(r12) {
                case 0: goto L_0x0565;
                case 1: goto L_0x0556;
                case 2: goto L_0x0540;
                case 3: goto L_0x052c;
                case 4: goto L_0x0518;
                case 5: goto L_0x050c;
                case 6: goto L_0x0500;
                case 7: goto L_0x04f2;
                case 8: goto L_0x04c4;
                case 9: goto L_0x04b1;
                case 10: goto L_0x0492;
                case 11: goto L_0x047d;
                case 12: goto L_0x0468;
                case 13: goto L_0x045b;
                case 14: goto L_0x044e;
                case 15: goto L_0x0434;
                case 16: goto L_0x041a;
                case 17: goto L_0x0406;
                case 18: goto L_0x03f8;
                case 19: goto L_0x03ec;
                case 20: goto L_0x03e0;
                case 21: goto L_0x03d4;
                case 22: goto L_0x03c8;
                case 23: goto L_0x03bc;
                case 24: goto L_0x03b0;
                case 25: goto L_0x03a4;
                case 26: goto L_0x0396;
                case 27: goto L_0x0387;
                case 28: goto L_0x037c;
                case 29: goto L_0x0370;
                case 30: goto L_0x0361;
                case 31: goto L_0x0355;
                case 32: goto L_0x0349;
                case 33: goto L_0x033d;
                case 34: goto L_0x0331;
                case 35: goto L_0x0316;
                case 36: goto L_0x02ff;
                case 37: goto L_0x02e8;
                case 38: goto L_0x02d1;
                case 39: goto L_0x02ba;
                case 40: goto L_0x02a2;
                case 41: goto L_0x028a;
                case 42: goto L_0x0270;
                case 43: goto L_0x0258;
                case 44: goto L_0x0240;
                case 45: goto L_0x0228;
                case 46: goto L_0x0210;
                case 47: goto L_0x01f8;
                case 48: goto L_0x01e0;
                case 49: goto L_0x01d0;
                case 50: goto L_0x01c3;
                case 51: goto L_0x01b3;
                case 52: goto L_0x01a3;
                case 53: goto L_0x018d;
                case 54: goto L_0x0177;
                case 55: goto L_0x0161;
                case 56: goto L_0x0154;
                case 57: goto L_0x0147;
                case 58: goto L_0x0138;
                case 59: goto L_0x0108;
                case 60: goto L_0x00f4;
                case 61: goto L_0x00d4;
                case 62: goto L_0x00be;
                case 63: goto L_0x00a8;
                case 64: goto L_0x009a;
                case 65: goto L_0x008c;
                case 66: goto L_0x0071;
                case 67: goto L_0x0055;
                case 68: goto L_0x003f;
                default: goto L_0x003d;
            }
        L_0x003d:
            goto L_0x03a1
        L_0x003f:
            boolean r9 = r15.zzT(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r3 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo) r3
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r4 = r15.zzB(r5)
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzt(r11, r3, r4)
            goto L_0x03a0
        L_0x0055:
            boolean r10 = r15.zzT(r1, r11, r5)
            if (r10 == 0) goto L_0x03a1
            long r3 = zzz(r1, r3)
            int r10 = r11 << 3
            long r11 = r3 + r3
            long r3 = r3 >> r9
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r10)
            long r3 = r3 ^ r11
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r3)
            int r9 = r9 + r3
            int r6 = r6 + r9
            goto L_0x03a1
        L_0x0071:
            boolean r9 = r15.zzT(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            int r3 = zzp(r1, r3)
            int r4 = r11 << 3
            int r9 = r3 + r3
            int r3 = r3 >> 31
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            r3 = r3 ^ r9
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            goto L_0x032d
        L_0x008c:
            boolean r3 = r15.zzT(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            goto L_0x01bf
        L_0x009a:
            boolean r3 = r15.zzT(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            goto L_0x01af
        L_0x00a8:
            boolean r9 = r15.zzT(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            int r3 = zzp(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032d
        L_0x00be:
            boolean r9 = r15.zzT(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            int r3 = zzp(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032d
        L_0x00d4:
            boolean r9 = r15.zzT(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r3 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r3
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzb
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r9 = r9 + r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
        L_0x00f1:
            int r3 = r3 + r9
            goto L_0x03a0
        L_0x00f4:
            boolean r9 = r15.zzT(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r4 = r15.zzB(r5)
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzn(r11, r3, r4)
            goto L_0x03a0
        L_0x0108:
            boolean r9 = r15.zzT(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            java.lang.Object r3 = r2.getObject(r1, r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
            if (r4 == 0) goto L_0x012a
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r3 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r3
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzb
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r9 = r9 + r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x00f1
        L_0x012a:
            java.lang.String r3 = (java.lang.String) r3
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzx(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032d
        L_0x0138:
            boolean r3 = r15.zzT(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r3 = r3 + r14
            goto L_0x03a0
        L_0x0147:
            boolean r3 = r15.zzT(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            goto L_0x01af
        L_0x0154:
            boolean r3 = r15.zzT(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            goto L_0x01bf
        L_0x0161:
            boolean r9 = r15.zzT(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            int r3 = zzp(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032d
        L_0x0177:
            boolean r9 = r15.zzT(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            long r3 = zzz(r1, r3)
            int r9 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r9)
            goto L_0x032d
        L_0x018d:
            boolean r9 = r15.zzT(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            long r3 = zzz(r1, r3)
            int r9 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r9)
            goto L_0x032d
        L_0x01a3:
            boolean r3 = r15.zzT(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
        L_0x01af:
            int r3 = r3 + 4
            goto L_0x03a0
        L_0x01b3:
            boolean r3 = r15.zzT(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
        L_0x01bf:
            int r3 = r3 + 8
            goto L_0x03a0
        L_0x01c3:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.lang.Object r4 = r15.zzC(r5)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfj.zza(r11, r3, r4)
            goto L_0x03a1
        L_0x01d0:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r4 = r15.zzB(r5)
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzi(r11, r3, r4)
            goto L_0x03a0
        L_0x01e0:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzs(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x01f8:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzq(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x0210:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzh(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x0228:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzf(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x0240:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzd(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x0258:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzv(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x0270:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zza
            int r3 = r3.size()
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x028a:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzf(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x02a2:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzh(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x02ba:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzk(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x02d1:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzx(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x02e8:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzm(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x02ff:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzf(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x032c
        L_0x0316:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzh(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
        L_0x032c:
            int r4 = r4 + r9
        L_0x032d:
            int r4 = r4 + r3
            int r6 = r6 + r4
            goto L_0x03a1
        L_0x0331:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            r9 = 0
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzr(r11, r3, r9)
            goto L_0x036c
        L_0x033d:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzp(r11, r3, r9)
            goto L_0x036c
        L_0x0349:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzg(r11, r3, r9)
            goto L_0x036c
        L_0x0355:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zze(r11, r3, r9)
            goto L_0x036c
        L_0x0361:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzc(r11, r3, r9)
        L_0x036c:
            int r6 = r6 + r3
            r12 = r9
            goto L_0x0574
        L_0x0370:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzu(r11, r3, r9)
            goto L_0x03a0
        L_0x037c:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzb(r11, r3)
            goto L_0x03a0
        L_0x0387:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r4 = r15.zzB(r5)
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzo(r11, r3, r4)
            goto L_0x03a0
        L_0x0396:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzt(r11, r3)
        L_0x03a0:
            int r6 = r6 + r3
        L_0x03a1:
            r12 = 0
            goto L_0x0574
        L_0x03a4:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            r12 = 0
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zza(r11, r3, r12)
            goto L_0x0403
        L_0x03b0:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zze(r11, r3, r12)
            goto L_0x0403
        L_0x03bc:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzg(r11, r3, r12)
            goto L_0x0403
        L_0x03c8:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzj(r11, r3, r12)
            goto L_0x0403
        L_0x03d4:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzw(r11, r3, r12)
            goto L_0x0403
        L_0x03e0:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzl(r11, r3, r12)
            goto L_0x0403
        L_0x03ec:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zze(r11, r3, r12)
            goto L_0x0403
        L_0x03f8:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzg(r11, r3, r12)
        L_0x0403:
            int r6 = r6 + r3
            goto L_0x0574
        L_0x0406:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r3 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo) r3
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r4 = r15.zzB(r5)
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzt(r11, r3, r4)
            goto L_0x0403
        L_0x041a:
            r12 = 0
            r10 = r10 & r8
            if (r10 == 0) goto L_0x0574
            long r3 = r2.getLong(r1, r3)
            int r10 = r11 << 3
            long r13 = r3 + r3
            long r3 = r3 >> r9
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r10)
            long r3 = r3 ^ r13
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r3)
            int r9 = r9 + r3
            int r6 = r6 + r9
            goto L_0x0574
        L_0x0434:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r9 = r3 + r3
            int r3 = r3 >> 31
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            r3 = r3 ^ r9
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            goto L_0x0553
        L_0x044e:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            goto L_0x0570
        L_0x045b:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            goto L_0x0561
        L_0x0468:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0553
        L_0x047d:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0553
        L_0x0492:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r3 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r3
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzb
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r9 = r9 + r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
        L_0x04ae:
            int r3 = r3 + r9
            goto L_0x0403
        L_0x04b1:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r4 = r15.zzB(r5)
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzn(r11, r3, r4)
            goto L_0x0403
        L_0x04c4:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            java.lang.Object r3 = r2.getObject(r1, r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
            if (r4 == 0) goto L_0x04e5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r3 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r3
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzb
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r9 = r9 + r3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x04ae
        L_0x04e5:
            java.lang.String r3 = (java.lang.String) r3
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzx(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0553
        L_0x04f2:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            int r3 = r3 + r14
            goto L_0x0403
        L_0x0500:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            goto L_0x0561
        L_0x050c:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
            goto L_0x0570
        L_0x0518:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0553
        L_0x052c:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            long r3 = r2.getLong(r1, r3)
            int r9 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r9)
            goto L_0x0553
        L_0x0540:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            long r3 = r2.getLong(r1, r3)
            int r9 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r3)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r9)
        L_0x0553:
            int r4 = r4 + r3
            int r6 = r6 + r4
            goto L_0x0574
        L_0x0556:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
        L_0x0561:
            int r3 = r3 + 4
            goto L_0x0403
        L_0x0565:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r3)
        L_0x0570:
            int r3 = r3 + 8
            goto L_0x0403
        L_0x0574:
            int r5 = r5 + 3
            r4 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x000c
        L_0x057b:
            r12 = 0
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgy r2 = r0.zzm
            java.lang.Object r3 = r2.zzd(r1)
            int r2 = r2.zza(r3)
            int r6 = r6 + r2
            boolean r2 = r0.zzh
            if (r2 == 0) goto L_0x05d9
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r2 = r0.zzn
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt r1 = r2.zzb(r1)
            r3 = r12
        L_0x0592:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgu r2 = r1.zza
            int r2 = r2.zzb()
            if (r3 >= r2) goto L_0x05b2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgu r2 = r1.zza
            java.util.Map$Entry r2 = r2.zzg(r3)
            java.lang.Object r4 = r2.getKey()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds r4 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds) r4
            java.lang.Object r2 = r2.getValue()
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt.zza(r4, r2)
            int r12 = r12 + r2
            int r3 = r3 + 1
            goto L_0x0592
        L_0x05b2:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgu r1 = r1.zza
            java.lang.Iterable r1 = r1.zzc()
            java.util.Iterator r1 = r1.iterator()
        L_0x05bc:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x05d8
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds r3 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds) r3
            java.lang.Object r2 = r2.getValue()
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt.zza(r3, r2)
            int r12 = r12 + r2
            goto L_0x05bc
        L_0x05d8:
            int r6 = r6 + r12
        L_0x05d9:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr.zzo(java.lang.Object):int");
    }

    private static int zzp(Object obj, long j) {
        return ((Integer) zzhi.zzf(obj, j)).intValue();
    }

    private final int zzq(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzco zzco) throws IOException {
        Unsafe unsafe = zzb;
        Object zzC = zzC(i3);
        Object object = unsafe.getObject(obj, j);
        if (!((zzfi) object).zze()) {
            zzfi zzb2 = zzfi.zza().zzb();
            zzfj.zzb(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        zzfh zzfh = (zzfh) zzC;
        throw null;
    }

    private final int zzr(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzco zzco) throws IOException {
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i9 = i;
        int i10 = i3;
        int i11 = i4;
        int i12 = i5;
        long j2 = j;
        int i13 = i8;
        zzco zzco2 = zzco;
        Unsafe unsafe = zzb;
        long j3 = (long) (this.zzc[i13 + 2] & 1048575);
        switch (i7) {
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
                if (i12 == 1) {
                    unsafe.putObject(obj2, j2, Double.valueOf(Double.longBitsToDouble(zzcp.zzq(bArr, i))));
                    int i14 = i9 + 8;
                    unsafe.putInt(obj2, j3, i11);
                    return i14;
                }
                break;
            case 52:
                if (i12 == 5) {
                    unsafe.putObject(obj2, j2, Float.valueOf(Float.intBitsToFloat(zzcp.zzb(bArr, i))));
                    int i15 = i9 + 4;
                    unsafe.putInt(obj2, j3, i11);
                    return i15;
                }
                break;
            case 53:
            case 54:
                if (i12 == 0) {
                    int zzm2 = zzcp.zzm(bArr2, i9, zzco2);
                    unsafe.putObject(obj2, j2, Long.valueOf(zzco2.zzb));
                    unsafe.putInt(obj2, j3, i11);
                    return zzm2;
                }
                break;
            case 55:
            case Elf64.Ehdr.E_SHSTRNDX:
                if (i12 == 0) {
                    int zzj2 = zzcp.zzj(bArr2, i9, zzco2);
                    unsafe.putObject(obj2, j2, Integer.valueOf(zzco2.zza));
                    unsafe.putInt(obj2, j3, i11);
                    return zzj2;
                }
                break;
            case 56:
            case 65:
                if (i12 == 1) {
                    unsafe.putObject(obj2, j2, Long.valueOf(zzcp.zzq(bArr, i)));
                    int i16 = i9 + 8;
                    unsafe.putInt(obj2, j3, i11);
                    return i16;
                }
                break;
            case 57:
            case 64:
                if (i12 == 5) {
                    unsafe.putObject(obj2, j2, Integer.valueOf(zzcp.zzb(bArr, i)));
                    int i17 = i9 + 4;
                    unsafe.putInt(obj2, j3, i11);
                    return i17;
                }
                break;
            case Elf64.Ehdr.E_SHENTSIZE:
                if (i12 == 0) {
                    int zzm3 = zzcp.zzm(bArr2, i9, zzco2);
                    unsafe.putObject(obj2, j2, Boolean.valueOf(zzco2.zzb != 0));
                    unsafe.putInt(obj2, j3, i11);
                    return zzm3;
                }
                break;
            case 59:
                if (i12 == 2) {
                    int zzj3 = zzcp.zzj(bArr2, i9, zzco2);
                    int i18 = zzco2.zza;
                    if (i18 == 0) {
                        unsafe.putObject(obj2, j2, "");
                    } else if ((i6 & C.BUFFER_FLAG_LAST_SAMPLE) == 0 || zzhn.zzh(bArr2, zzj3, zzj3 + i18)) {
                        unsafe.putObject(obj2, j2, new String(bArr2, zzj3, i18, zzem.zzb));
                        zzj3 += i18;
                    } else {
                        throw zzeo.zzc();
                    }
                    unsafe.putInt(obj2, j3, i11);
                    return zzj3;
                }
                break;
            case 60:
                if (i12 == 2) {
                    Object zzE = zzE(obj2, i11, i13);
                    int zzo2 = zzcp.zzo(zzE, zzB(i13), bArr, i, i2, zzco);
                    zzM(obj2, i11, i13, zzE);
                    return zzo2;
                }
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT:
                if (i12 == 2) {
                    int zza2 = zzcp.zza(bArr2, i9, zzco2);
                    unsafe.putObject(obj2, j2, zzco2.zzc);
                    unsafe.putInt(obj2, j3, i11);
                    return zza2;
                }
                break;
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
                if (i12 == 0) {
                    int zzj4 = zzcp.zzj(bArr2, i9, zzco2);
                    int i19 = zzco2.zza;
                    zzeh zzA = zzA(i13);
                    if (zzA == null || zzA.zza(i19)) {
                        unsafe.putObject(obj2, j2, Integer.valueOf(i19));
                        unsafe.putInt(obj2, j3, i11);
                    } else {
                        zzd(obj).zzj(i10, Long.valueOf((long) i19));
                    }
                    return zzj4;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                if (i12 == 0) {
                    int zzj5 = zzcp.zzj(bArr2, i9, zzco2);
                    unsafe.putObject(obj2, j2, Integer.valueOf(zzdf.zzb(zzco2.zza)));
                    unsafe.putInt(obj2, j3, i11);
                    return zzj5;
                }
                break;
            case 67:
                if (i12 == 0) {
                    int zzm4 = zzcp.zzm(bArr2, i9, zzco2);
                    unsafe.putObject(obj2, j2, Long.valueOf(zzdf.zzc(zzco2.zzb)));
                    unsafe.putInt(obj2, j3, i11);
                    return zzm4;
                }
                break;
            case 68:
                if (i12 == 3) {
                    Object zzE2 = zzE(obj2, i11, i13);
                    int zzn2 = zzcp.zzn(zzE2, zzB(i13), bArr, i, i2, (i10 & -8) | 4, zzco);
                    zzM(obj2, i11, i13, zzE2);
                    return zzn2;
                }
                break;
        }
        return i9;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0219  */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x0496 A[SYNTHETIC] */
    private final int zzs(java.lang.Object r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, long r25, int r27, long r28, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco r30) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r2 = r21
            r8 = r22
            r6 = r23
            r9 = r24
            r10 = r28
            r7 = r30
            sun.misc.Unsafe r12 = zzb
            java.lang.Object r13 = r12.getObject(r1, r10)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel) r13
            boolean r14 = r13.zzc()
            if (r14 != 0) goto L_0x0035
            int r14 = r13.size()
            if (r14 != 0) goto L_0x002d
            r14 = 10
            goto L_0x002e
        L_0x002d:
            int r14 = r14 + r14
        L_0x002e:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel r13 = r13.zzd(r14)
            r12.putObject(r1, r10, r13)
        L_0x0035:
            r10 = 5
            r11 = 0
            r15 = 1
            r14 = 2
            switch(r27) {
                case 18: goto L_0x0427;
                case 19: goto L_0x03da;
                case 20: goto L_0x0397;
                case 21: goto L_0x0397;
                case 22: goto L_0x037e;
                case 23: goto L_0x033d;
                case 24: goto L_0x02fc;
                case 25: goto L_0x02a4;
                case 26: goto L_0x01f1;
                case 27: goto L_0x01d8;
                case 28: goto L_0x017e;
                case 29: goto L_0x037e;
                case 30: goto L_0x00fd;
                case 31: goto L_0x02fc;
                case 32: goto L_0x033d;
                case 33: goto L_0x00ae;
                case 34: goto L_0x005f;
                case 35: goto L_0x0427;
                case 36: goto L_0x03da;
                case 37: goto L_0x0397;
                case 38: goto L_0x0397;
                case 39: goto L_0x037e;
                case 40: goto L_0x033d;
                case 41: goto L_0x02fc;
                case 42: goto L_0x02a4;
                case 43: goto L_0x037e;
                case 44: goto L_0x00fd;
                case 45: goto L_0x02fc;
                case 46: goto L_0x033d;
                case 47: goto L_0x00ae;
                case 48: goto L_0x005f;
                default: goto L_0x003d;
            }
        L_0x003d:
            r1 = 3
            if (r6 != r1) goto L_0x0495
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r1 = r0.zzB(r9)
            r6 = r2 & -8
            r6 = r6 | 4
            r22 = r1
            r23 = r18
            r24 = r19
            r25 = r20
            r26 = r6
            r27 = r30
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzc(r22, r23, r24, r25, r26, r27)
            java.lang.Object r8 = r7.zzc
            r13.add(r8)
            goto L_0x0473
        L_0x005f:
            if (r6 != r14) goto L_0x0083
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x006a:
            if (r1 >= r2) goto L_0x007a
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r3, r1, r7)
            long r4 = r7.zzb
            long r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzc(r4)
            r13.zzf(r4)
            goto L_0x006a
        L_0x007a:
            if (r1 != r2) goto L_0x007e
            goto L_0x0496
        L_0x007e:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r1
        L_0x0083:
            if (r6 != 0) goto L_0x0495
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r3, r4, r7)
            long r8 = r7.zzb
            long r8 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzc(r8)
            r13.zzf(r8)
        L_0x0094:
            if (r1 >= r5) goto L_0x00ad
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x009f
            goto L_0x00ad
        L_0x009f:
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r3, r4, r7)
            long r8 = r7.zzb
            long r8 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzc(r8)
            r13.zzf(r8)
            goto L_0x0094
        L_0x00ad:
            return r1
        L_0x00ae:
            if (r6 != r14) goto L_0x00d2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzee r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzee) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x00b9:
            if (r1 >= r2) goto L_0x00c9
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzb(r4)
            r13.zzg(r4)
            goto L_0x00b9
        L_0x00c9:
            if (r1 != r2) goto L_0x00cd
            goto L_0x0496
        L_0x00cd:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r1
        L_0x00d2:
            if (r6 != 0) goto L_0x0495
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzee r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzee) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzb(r4)
            r13.zzg(r4)
        L_0x00e3:
            if (r1 >= r5) goto L_0x00fc
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x00ee
            goto L_0x00fc
        L_0x00ee:
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzb(r4)
            r13.zzg(r4)
            goto L_0x00e3
        L_0x00fc:
            return r1
        L_0x00fd:
            if (r6 != r14) goto L_0x0104
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzf(r3, r4, r13, r7)
            goto L_0x0115
        L_0x0104:
            if (r6 != 0) goto L_0x0495
            r2 = r21
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r13
            r7 = r30
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzl(r2, r3, r4, r5, r6, r7)
        L_0x0115:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh r3 = r0.zzA(r9)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgy r4 = r0.zzm
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zza
            if (r3 == 0) goto L_0x017b
            boolean r5 = r13 instanceof java.util.RandomAccess
            r6 = 0
            if (r5 == 0) goto L_0x0159
            int r5 = r13.size()
            r7 = 0
            r14 = 0
        L_0x012a:
            if (r14 >= r5) goto L_0x014f
            java.lang.Object r9 = r13.get(r14)
            java.lang.Integer r9 = (java.lang.Integer) r9
            int r9 = r9.intValue()
            boolean r10 = r3.zza(r9)
            if (r10 == 0) goto L_0x0148
            if (r14 == r7) goto L_0x0145
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r13.set(r7, r9)
        L_0x0145:
            int r7 = r7 + 1
            goto L_0x014c
        L_0x0148:
            java.lang.Object r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzA(r1, r8, r9, r6, r4)
        L_0x014c:
            int r14 = r14 + 1
            goto L_0x012a
        L_0x014f:
            if (r7 == r5) goto L_0x017b
            java.util.List r1 = r13.subList(r7, r5)
            r1.clear()
            return r2
        L_0x0159:
            java.util.Iterator r5 = r13.iterator()
        L_0x015d:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x017b
            java.lang.Object r7 = r5.next()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            boolean r9 = r3.zza(r7)
            if (r9 != 0) goto L_0x015d
            java.lang.Object r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzA(r1, r8, r7, r6, r4)
            r5.remove()
            goto L_0x015d
        L_0x017b:
            r1 = r2
            goto L_0x0496
        L_0x017e:
            if (r6 != r14) goto L_0x0495
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x01d3
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x01ce
            if (r4 != 0) goto L_0x0194
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb.zzb
            r13.add(r4)
            goto L_0x019c
        L_0x0194:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb.zzr(r3, r1, r4)
            r13.add(r6)
        L_0x019b:
            int r1 = r1 + r4
        L_0x019c:
            if (r1 >= r5) goto L_0x01cd
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x01a7
            goto L_0x01cd
        L_0x01a7:
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x01c8
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x01c3
            if (r4 != 0) goto L_0x01bb
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb.zzb
            r13.add(r4)
            goto L_0x019c
        L_0x01bb:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb.zzr(r3, r1, r4)
            r13.add(r6)
            goto L_0x019b
        L_0x01c3:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r1
        L_0x01c8:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzd()
            throw r1
        L_0x01cd:
            return r1
        L_0x01ce:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r1
        L_0x01d3:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzd()
            throw r1
        L_0x01d8:
            if (r6 != r14) goto L_0x0495
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r1 = r0.zzB(r9)
            r22 = r1
            r23 = r21
            r24 = r18
            r25 = r19
            r26 = r20
            r27 = r13
            r28 = r30
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zze(r22, r23, r24, r25, r26, r27, r28)
            return r1
        L_0x01f1:
            if (r6 != r14) goto L_0x0495
            r8 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r25 & r8
            int r1 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            java.lang.String r6 = ""
            if (r1 != 0) goto L_0x0244
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x023f
            if (r4 != 0) goto L_0x020c
            r13.add(r6)
            goto L_0x0217
        L_0x020c:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzb
            r8.<init>(r3, r1, r4, r9)
            r13.add(r8)
        L_0x0216:
            int r1 = r1 + r4
        L_0x0217:
            if (r1 >= r5) goto L_0x0496
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r8 = r7.zza
            if (r2 != r8) goto L_0x0496
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x023a
            if (r4 != 0) goto L_0x022f
            r13.add(r6)
            goto L_0x0217
        L_0x022f:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzb
            r8.<init>(r3, r1, r4, r9)
            r13.add(r8)
            goto L_0x0216
        L_0x023a:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzd()
            throw r1
        L_0x023f:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzd()
            throw r1
        L_0x0244:
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x029f
            if (r4 != 0) goto L_0x0252
            r13.add(r6)
            goto L_0x0265
        L_0x0252:
            int r8 = r1 + r4
            boolean r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhn.zzh(r3, r1, r8)
            if (r9 == 0) goto L_0x029a
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzb
            r9.<init>(r3, r1, r4, r10)
            r13.add(r9)
        L_0x0264:
            r1 = r8
        L_0x0265:
            if (r1 >= r5) goto L_0x0496
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r8 = r7.zza
            if (r2 != r8) goto L_0x0496
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x0295
            if (r4 != 0) goto L_0x027d
            r13.add(r6)
            goto L_0x0265
        L_0x027d:
            int r8 = r1 + r4
            boolean r9 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhn.zzh(r3, r1, r8)
            if (r9 == 0) goto L_0x0290
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzb
            r9.<init>(r3, r1, r4, r10)
            r13.add(r9)
            goto L_0x0264
        L_0x0290:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzc()
            throw r1
        L_0x0295:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzd()
            throw r1
        L_0x029a:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzc()
            throw r1
        L_0x029f:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzd()
            throw r1
        L_0x02a4:
            if (r6 != r14) goto L_0x02cb
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcq r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcq) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x02af:
            if (r1 >= r2) goto L_0x02c2
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r3, r1, r7)
            long r4 = r7.zzb
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r4 == 0) goto L_0x02bd
            r4 = r15
            goto L_0x02be
        L_0x02bd:
            r4 = 0
        L_0x02be:
            r13.zze(r4)
            goto L_0x02af
        L_0x02c2:
            if (r1 != r2) goto L_0x02c6
            goto L_0x0496
        L_0x02c6:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r1
        L_0x02cb:
            if (r6 != 0) goto L_0x0495
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcq r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcq) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r3, r4, r7)
            long r8 = r7.zzb
            int r4 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r4 == 0) goto L_0x02db
            r4 = r15
            goto L_0x02dc
        L_0x02db:
            r4 = 0
        L_0x02dc:
            r13.zze(r4)
        L_0x02df:
            if (r1 >= r5) goto L_0x02fb
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x02ea
            goto L_0x02fb
        L_0x02ea:
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r3, r4, r7)
            long r8 = r7.zzb
            int r4 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r4 == 0) goto L_0x02f6
            r4 = r15
            goto L_0x02f7
        L_0x02f6:
            r4 = 0
        L_0x02f7:
            r13.zze(r4)
            goto L_0x02df
        L_0x02fb:
            return r1
        L_0x02fc:
            if (r6 != r14) goto L_0x031c
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzee r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzee) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x0307:
            if (r1 >= r2) goto L_0x0313
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r3, r1)
            r13.zzg(r4)
            int r1 = r1 + 4
            goto L_0x0307
        L_0x0313:
            if (r1 != r2) goto L_0x0317
            goto L_0x0496
        L_0x0317:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r1
        L_0x031c:
            if (r6 != r10) goto L_0x0495
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzee r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzee) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r18, r19)
            r13.zzg(r1)
        L_0x0327:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x033c
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x0334
            goto L_0x033c
        L_0x0334:
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r3, r4)
            r13.zzg(r1)
            goto L_0x0327
        L_0x033c:
            return r1
        L_0x033d:
            if (r6 != r14) goto L_0x035d
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x0348:
            if (r1 >= r2) goto L_0x0354
            long r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r3, r1)
            r13.zzf(r4)
            int r1 = r1 + 8
            goto L_0x0348
        L_0x0354:
            if (r1 != r2) goto L_0x0358
            goto L_0x0496
        L_0x0358:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r1
        L_0x035d:
            if (r6 != r15) goto L_0x0495
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd) r13
            long r8 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r18, r19)
            r13.zzf(r8)
        L_0x0368:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x037d
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x0375
            goto L_0x037d
        L_0x0375:
            long r8 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r3, r4)
            r13.zzf(r8)
            goto L_0x0368
        L_0x037d:
            return r1
        L_0x037e:
            if (r6 != r14) goto L_0x0386
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzf(r3, r4, r13, r7)
            goto L_0x0496
        L_0x0386:
            if (r6 != 0) goto L_0x0495
            r22 = r18
            r23 = r19
            r24 = r20
            r25 = r13
            r26 = r30
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzl(r21, r22, r23, r24, r25, r26)
            return r1
        L_0x0397:
            if (r6 != r14) goto L_0x03b7
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x03a2:
            if (r1 >= r2) goto L_0x03ae
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r3, r1, r7)
            long r4 = r7.zzb
            r13.zzf(r4)
            goto L_0x03a2
        L_0x03ae:
            if (r1 != r2) goto L_0x03b2
            goto L_0x0496
        L_0x03b2:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r1
        L_0x03b7:
            if (r6 != 0) goto L_0x0495
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfd) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r3, r4, r7)
            long r8 = r7.zzb
            r13.zzf(r8)
        L_0x03c4:
            if (r1 >= r5) goto L_0x03d9
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x03cf
            goto L_0x03d9
        L_0x03cf:
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r3, r4, r7)
            long r8 = r7.zzb
            r13.zzf(r8)
            goto L_0x03c4
        L_0x03d9:
            return r1
        L_0x03da:
            if (r6 != r14) goto L_0x03fe
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdv r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdv) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x03e5:
            if (r1 >= r2) goto L_0x03f5
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r3, r1)
            float r4 = java.lang.Float.intBitsToFloat(r4)
            r13.zzg(r4)
            int r1 = r1 + 4
            goto L_0x03e5
        L_0x03f5:
            if (r1 != r2) goto L_0x03f9
            goto L_0x0496
        L_0x03f9:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r1
        L_0x03fe:
            if (r6 != r10) goto L_0x0495
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdv r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdv) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r18, r19)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r13.zzg(r1)
        L_0x040d:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x0426
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x041a
            goto L_0x0426
        L_0x041a:
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r3, r4)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r13.zzg(r1)
            goto L_0x040d
        L_0x0426:
            return r1
        L_0x0427:
            if (r6 != r14) goto L_0x044a
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdl r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdl) r13
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x0432:
            if (r1 >= r2) goto L_0x0442
            long r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r3, r1)
            double r4 = java.lang.Double.longBitsToDouble(r4)
            r13.zze(r4)
            int r1 = r1 + 8
            goto L_0x0432
        L_0x0442:
            if (r1 != r2) goto L_0x0445
            goto L_0x0496
        L_0x0445:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zzg()
            throw r1
        L_0x044a:
            if (r6 != r15) goto L_0x0495
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdl r13 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdl) r13
            long r8 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r18, r19)
            double r8 = java.lang.Double.longBitsToDouble(r8)
            r13.zze(r8)
        L_0x0459:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x0472
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x0466
            goto L_0x0472
        L_0x0466:
            long r8 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r3, r4)
            double r8 = java.lang.Double.longBitsToDouble(r8)
            r13.zze(r8)
            goto L_0x0459
        L_0x0472:
            return r1
        L_0x0473:
            if (r4 >= r5) goto L_0x0494
            int r8 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r3, r4, r7)
            int r9 = r7.zza
            if (r2 == r9) goto L_0x047e
            goto L_0x0494
        L_0x047e:
            r22 = r1
            r23 = r18
            r24 = r8
            r25 = r20
            r26 = r6
            r27 = r30
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzc(r22, r23, r24, r25, r26, r27)
            java.lang.Object r8 = r7.zzc
            r13.add(r8)
            goto L_0x0473
        L_0x0494:
            return r4
        L_0x0495:
            r1 = r4
        L_0x0496:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr.zzs(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco):int");
    }

    private final int zzt(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzw(i, 0);
    }

    private final int zzu(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzw(i, i2);
    }

    private final int zzv(int i) {
        return this.zzc[i + 2];
    }

    private final int zzw(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzx(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzy(int i) {
        return this.zzc[i + 1];
    }

    private static long zzz(Object obj, long j) {
        return ((Long) zzhi.zzf(obj, j)).longValue();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x032a, code lost:
        r5 = r5 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0424, code lost:
        r4 = r4 + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x04a6, code lost:
        r4 = r4 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x04f8, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x0563, code lost:
        r4 = r4 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0573, code lost:
        r4 = r4 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0577, code lost:
        r2 = r2 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(java.lang.Object r12) {
        /*
            r11 = this;
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho.DOUBLE
            int r0 = r11.zzo
            int r0 = r0 + -1
            if (r0 == 0) goto L_0x0587
            sun.misc.Unsafe r0 = zzb
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x000d:
            int[] r4 = r11.zzc
            int r4 = r4.length
            if (r2 >= r4) goto L_0x057b
            int r4 = r11.zzy(r2)
            int r5 = zzx(r4)
            int[] r6 = r11.zzc
            r6 = r6[r2]
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r7
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdu r7 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdu.DOUBLE_LIST_PACKED
            int r7 = r7.zza()
            if (r5 < r7) goto L_0x0038
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdu r7 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdu.SINT64_LIST_PACKED
            int r7 = r7.zza()
            if (r5 > r7) goto L_0x0038
            int[] r7 = r11.zzc
            int r8 = r2 + 2
            r7 = r7[r8]
        L_0x0038:
            long r7 = (long) r4
            r4 = 63
            switch(r5) {
                case 0: goto L_0x0567;
                case 1: goto L_0x0557;
                case 2: goto L_0x0540;
                case 3: goto L_0x052b;
                case 4: goto L_0x0516;
                case 5: goto L_0x0509;
                case 6: goto L_0x04fc;
                case 7: goto L_0x04ec;
                case 8: goto L_0x04bd;
                case 9: goto L_0x04a9;
                case 10: goto L_0x0489;
                case 11: goto L_0x0473;
                case 12: goto L_0x045d;
                case 13: goto L_0x044f;
                case 14: goto L_0x0441;
                case 15: goto L_0x0426;
                case 16: goto L_0x040a;
                case 17: goto L_0x03f5;
                case 18: goto L_0x03e8;
                case 19: goto L_0x03dd;
                case 20: goto L_0x03d2;
                case 21: goto L_0x03c7;
                case 22: goto L_0x03bc;
                case 23: goto L_0x03b1;
                case 24: goto L_0x03a6;
                case 25: goto L_0x039b;
                case 26: goto L_0x0390;
                case 27: goto L_0x0381;
                case 28: goto L_0x0375;
                case 29: goto L_0x0369;
                case 30: goto L_0x035d;
                case 31: goto L_0x0351;
                case 32: goto L_0x0345;
                case 33: goto L_0x0339;
                case 34: goto L_0x032d;
                case 35: goto L_0x0314;
                case 36: goto L_0x02fd;
                case 37: goto L_0x02e6;
                case 38: goto L_0x02cf;
                case 39: goto L_0x02b8;
                case 40: goto L_0x02a0;
                case 41: goto L_0x0288;
                case 42: goto L_0x026e;
                case 43: goto L_0x0256;
                case 44: goto L_0x023e;
                case 45: goto L_0x0226;
                case 46: goto L_0x020e;
                case 47: goto L_0x01f6;
                case 48: goto L_0x01de;
                case 49: goto L_0x01ce;
                case 50: goto L_0x01c1;
                case 51: goto L_0x01b3;
                case 52: goto L_0x01a5;
                case 53: goto L_0x018f;
                case 54: goto L_0x0179;
                case 55: goto L_0x0163;
                case 56: goto L_0x0155;
                case 57: goto L_0x0147;
                case 58: goto L_0x0139;
                case 59: goto L_0x0108;
                case 60: goto L_0x00f4;
                case 61: goto L_0x00d5;
                case 62: goto L_0x00bf;
                case 63: goto L_0x00a9;
                case 64: goto L_0x009b;
                case 65: goto L_0x008d;
                case 66: goto L_0x0072;
                case 67: goto L_0x0056;
                case 68: goto L_0x0040;
                default: goto L_0x003e;
            }
        L_0x003e:
            goto L_0x0577
        L_0x0040:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r4 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo) r4
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r5 = r11.zzB(r2)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzt(r6, r4, r5)
            goto L_0x03f2
        L_0x0056:
            boolean r5 = r11.zzT(r12, r6, r2)
            if (r5 == 0) goto L_0x0577
            long r7 = zzz(r12, r7)
            int r5 = r6 << 3
            long r9 = r7 + r7
            long r6 = r7 >> r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            long r5 = r9 ^ r6
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r5)
            goto L_0x0424
        L_0x0072:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = zzp(r12, r7)
            int r5 = r6 << 3
            int r6 = r4 + r4
            int r4 = r4 >> 31
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            r4 = r4 ^ r6
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0554
        L_0x008d:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0573
        L_0x009b:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0563
        L_0x00a9:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = zzp(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x0554
        L_0x00bf:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = zzp(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x0554
        L_0x00d5:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r4 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r4
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzb
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r6 = r6 + r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x04a6
        L_0x00f4:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r5 = r11.zzB(r2)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzn(r6, r4, r5)
            goto L_0x03f2
        L_0x0108:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            boolean r5 = r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
            if (r5 == 0) goto L_0x012b
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r4 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r4
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzb
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r6 = r6 + r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x04a6
        L_0x012b:
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzx(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x0554
        L_0x0139:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x04f8
        L_0x0147:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0563
        L_0x0155:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0573
        L_0x0163:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = zzp(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x0554
        L_0x0179:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            long r4 = zzz(r12, r7)
            int r6 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r6)
            goto L_0x0554
        L_0x018f:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            long r4 = zzz(r12, r7)
            int r6 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r6)
            goto L_0x0554
        L_0x01a5:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0563
        L_0x01b3:
            boolean r4 = r11.zzT(r12, r6, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0573
        L_0x01c1:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.lang.Object r5 = r11.zzC(r2)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfj.zza(r6, r4, r5)
            goto L_0x0577
        L_0x01ce:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r5 = r11.zzB(r2)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzi(r6, r4, r5)
            goto L_0x03f2
        L_0x01de:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzs(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x01f6:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzq(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x020e:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzh(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x0226:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzf(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x023e:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzd(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x0256:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzv(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x026e:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zza
            int r4 = r4.size()
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x0288:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzf(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x02a0:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzh(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x02b8:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzk(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x02cf:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzx(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x02e6:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzm(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x02fd:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzf(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x032a
        L_0x0314:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzh(r4)
            if (r4 <= 0) goto L_0x0577
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
        L_0x032a:
            int r5 = r5 + r6
            goto L_0x0554
        L_0x032d:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzr(r6, r4, r1)
            goto L_0x03f2
        L_0x0339:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzp(r6, r4, r1)
            goto L_0x03f2
        L_0x0345:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzg(r6, r4, r1)
            goto L_0x03f2
        L_0x0351:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zze(r6, r4, r1)
            goto L_0x03f2
        L_0x035d:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzc(r6, r4, r1)
            goto L_0x03f2
        L_0x0369:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzu(r6, r4, r1)
            goto L_0x03f2
        L_0x0375:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzb(r6, r4)
            goto L_0x03f2
        L_0x0381:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r5 = r11.zzB(r2)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzo(r6, r4, r5)
            goto L_0x03f2
        L_0x0390:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzt(r6, r4)
            goto L_0x03f2
        L_0x039b:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zza(r6, r4, r1)
            goto L_0x03f2
        L_0x03a6:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zze(r6, r4, r1)
            goto L_0x03f2
        L_0x03b1:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzg(r6, r4, r1)
            goto L_0x03f2
        L_0x03bc:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzj(r6, r4, r1)
            goto L_0x03f2
        L_0x03c7:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzw(r6, r4, r1)
            goto L_0x03f2
        L_0x03d2:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzl(r6, r4, r1)
            goto L_0x03f2
        L_0x03dd:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zze(r6, r4, r1)
            goto L_0x03f2
        L_0x03e8:
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzg(r6, r4, r1)
        L_0x03f2:
            int r3 = r3 + r4
            goto L_0x0577
        L_0x03f5:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r4 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo) r4
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r5 = r11.zzB(r2)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzt(r6, r4, r5)
            goto L_0x03f2
        L_0x040a:
            boolean r5 = r11.zzP(r12, r2)
            if (r5 == 0) goto L_0x0577
            long r7 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r12, r7)
            int r5 = r6 << 3
            long r9 = r7 + r7
            long r6 = r7 >> r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            long r5 = r9 ^ r6
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r5)
        L_0x0424:
            int r4 = r4 + r5
            goto L_0x03f2
        L_0x0426:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r12, r7)
            int r5 = r6 << 3
            int r6 = r4 + r4
            int r4 = r4 >> 31
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            r4 = r4 ^ r6
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0554
        L_0x0441:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0573
        L_0x044f:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0563
        L_0x045d:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x0554
        L_0x0473:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x0554
        L_0x0489:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r4 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r4
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzb
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r6 = r6 + r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
        L_0x04a6:
            int r4 = r4 + r6
            goto L_0x03f2
        L_0x04a9:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r5 = r11.zzB(r2)
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzn(r6, r4, r5)
            goto L_0x03f2
        L_0x04bd:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            java.lang.Object r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r7)
            boolean r5 = r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
            if (r5 == 0) goto L_0x04df
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r4 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r4
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzb
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            int r6 = r6 + r4
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x04a6
        L_0x04df:
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzx(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x0554
        L_0x04ec:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
        L_0x04f8:
            int r4 = r4 + 1
            goto L_0x03f2
        L_0x04fc:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0563
        L_0x0509:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
            goto L_0x0573
        L_0x0516:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzu(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r5)
            goto L_0x0554
        L_0x052b:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            long r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r12, r7)
            int r6 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r6)
            goto L_0x0554
        L_0x0540:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            long r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r12, r7)
            int r6 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzz(r4)
            int r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r6)
        L_0x0554:
            int r5 = r5 + r4
            int r3 = r3 + r5
            goto L_0x0577
        L_0x0557:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
        L_0x0563:
            int r4 = r4 + 4
            goto L_0x03f2
        L_0x0567:
            boolean r4 = r11.zzP(r12, r2)
            if (r4 == 0) goto L_0x0577
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdj.zzy(r4)
        L_0x0573:
            int r4 = r4 + 8
            goto L_0x03f2
        L_0x0577:
            int r2 = r2 + 3
            goto L_0x000d
        L_0x057b:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgy r0 = r11.zzm
            java.lang.Object r12 = r0.zzd(r12)
            int r12 = r0.zza(r12)
            int r3 = r3 + r12
            return r3
        L_0x0587:
            int r12 = r11.zzo(r12)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr.zza(java.lang.Object):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01b4, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0210, code lost:
        r3 = (int) (r3 ^ (r3 >>> 32));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0214, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0215, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzb(java.lang.Object r10) {
        /*
            r9 = this;
            int[] r0 = r9.zzc
            int r0 = r0.length
            r1 = 0
            r2 = r1
        L_0x0005:
            if (r1 >= r0) goto L_0x0219
            int r3 = r9.zzy(r1)
            int[] r4 = r9.zzc
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            int r3 = zzx(r3)
            long r5 = (long) r5
            r7 = 37
            r8 = 32
            switch(r3) {
                case 0: goto L_0x0204;
                case 1: goto L_0x01f9;
                case 2: goto L_0x01f0;
                case 3: goto L_0x01e7;
                case 4: goto L_0x01e0;
                case 5: goto L_0x01d7;
                case 6: goto L_0x01d0;
                case 7: goto L_0x01c5;
                case 8: goto L_0x01b8;
                case 9: goto L_0x01aa;
                case 10: goto L_0x019e;
                case 11: goto L_0x0196;
                case 12: goto L_0x018e;
                case 13: goto L_0x0186;
                case 14: goto L_0x017c;
                case 15: goto L_0x0174;
                case 16: goto L_0x016a;
                case 17: goto L_0x015f;
                case 18: goto L_0x0153;
                case 19: goto L_0x0153;
                case 20: goto L_0x0153;
                case 21: goto L_0x0153;
                case 22: goto L_0x0153;
                case 23: goto L_0x0153;
                case 24: goto L_0x0153;
                case 25: goto L_0x0153;
                case 26: goto L_0x0153;
                case 27: goto L_0x0153;
                case 28: goto L_0x0153;
                case 29: goto L_0x0153;
                case 30: goto L_0x0153;
                case 31: goto L_0x0153;
                case 32: goto L_0x0153;
                case 33: goto L_0x0153;
                case 34: goto L_0x0153;
                case 35: goto L_0x0153;
                case 36: goto L_0x0153;
                case 37: goto L_0x0153;
                case 38: goto L_0x0153;
                case 39: goto L_0x0153;
                case 40: goto L_0x0153;
                case 41: goto L_0x0153;
                case 42: goto L_0x0153;
                case 43: goto L_0x0153;
                case 44: goto L_0x0153;
                case 45: goto L_0x0153;
                case 46: goto L_0x0153;
                case 47: goto L_0x0153;
                case 48: goto L_0x0153;
                case 49: goto L_0x0153;
                case 50: goto L_0x0147;
                case 51: goto L_0x0133;
                case 52: goto L_0x0121;
                case 53: goto L_0x0111;
                case 54: goto L_0x0101;
                case 55: goto L_0x00f3;
                case 56: goto L_0x00e3;
                case 57: goto L_0x00d5;
                case 58: goto L_0x00c3;
                case 59: goto L_0x00af;
                case 60: goto L_0x009d;
                case 61: goto L_0x008b;
                case 62: goto L_0x007d;
                case 63: goto L_0x006f;
                case 64: goto L_0x0061;
                case 65: goto L_0x0051;
                case 66: goto L_0x0043;
                case 67: goto L_0x0033;
                case 68: goto L_0x0021;
                default: goto L_0x001f;
            }
        L_0x001f:
            goto L_0x0215
        L_0x0021:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r10, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x0033:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            long r3 = zzz(r10, r5)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x0043:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzp(r10, r5)
            goto L_0x0214
        L_0x0051:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            long r3 = zzz(r10, r5)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x0061:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzp(r10, r5)
            goto L_0x0214
        L_0x006f:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzp(r10, r5)
            goto L_0x0214
        L_0x007d:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzp(r10, r5)
            goto L_0x0214
        L_0x008b:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x009d:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r10, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x00af:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r10, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x00c3:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            boolean r3 = zzU(r10, r5)
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zza(r3)
            goto L_0x0214
        L_0x00d5:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzp(r10, r5)
            goto L_0x0214
        L_0x00e3:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            long r3 = zzz(r10, r5)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x00f3:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzp(r10, r5)
            goto L_0x0214
        L_0x0101:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            long r3 = zzz(r10, r5)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x0111:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            long r3 = zzz(r10, r5)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x0121:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            float r3 = zzn(r10, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0214
        L_0x0133:
            boolean r3 = r9.zzT(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            double r3 = zzm(r10, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x0147:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x0153:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x015f:
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r10, r5)
            if (r3 == 0) goto L_0x01b4
            int r7 = r3.hashCode()
            goto L_0x01b4
        L_0x016a:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x0174:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r10, r5)
            goto L_0x0214
        L_0x017c:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x0186:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r10, r5)
            goto L_0x0214
        L_0x018e:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r10, r5)
            goto L_0x0214
        L_0x0196:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r10, r5)
            goto L_0x0214
        L_0x019e:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x01aa:
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r10, r5)
            if (r3 == 0) goto L_0x01b4
            int r7 = r3.hashCode()
        L_0x01b4:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0215
        L_0x01b8:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r10, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x01c5:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzw(r10, r5)
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zza(r3)
            goto L_0x0214
        L_0x01d0:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r10, r5)
            goto L_0x0214
        L_0x01d7:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x01e0:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r10, r5)
            goto L_0x0214
        L_0x01e7:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x01f0:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
            goto L_0x0210
        L_0x01f9:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzb(r10, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0214
        L_0x0204:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zza(r10, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            byte[] r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzem.zzd
        L_0x0210:
            long r5 = r3 >>> r8
            long r3 = r3 ^ r5
            int r3 = (int) r3
        L_0x0214:
            int r2 = r2 + r3
        L_0x0215:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x0219:
            int r2 = r2 * 53
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgy r0 = r9.zzm
            java.lang.Object r0 = r0.zzd(r10)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r9.zzh
            if (r0 == 0) goto L_0x0239
            int r2 = r2 * 53
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r0 = r9.zzn
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt r10 = r0.zzb(r10)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgu r10 = r10.zza
            int r10 = r10.hashCode()
            int r2 = r2 + r10
        L_0x0239:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr.zzb(java.lang.Object):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v37, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v37, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v53, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v54, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x032c, code lost:
        if (r0 != r15) goto L_0x032e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x034a, code lost:
        r8 = r37;
        r7 = r39;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0374, code lost:
        if (r0 != r15) goto L_0x032e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0398, code lost:
        if (r0 != r15) goto L_0x032e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x04f1, code lost:
        r3 = r3 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0502, code lost:
        r3 = r3 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0504, code lost:
        r2 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0506, code lost:
        r1.zzi(r0.zzd, r2);
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x016e, code lost:
        r0 = r13 | r7;
        r13 = r38;
        r3 = r6;
        r1 = r11;
        r6 = r27;
        r8 = -1;
        r11 = r39;
        r33 = r5;
        r5 = r0;
        r0 = r2;
        r2 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0181, code lost:
        r12 = r5;
        r37 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0254, code lost:
        r0 = r3 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0257, code lost:
        r12 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0258, code lost:
        r37 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x025a, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0271, code lost:
        r5 = r13 | r7;
        r13 = r38;
        r2 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0276, code lost:
        r1 = r11;
        r3 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0279, code lost:
        r6 = r27;
        r8 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x027c, code lost:
        r11 = r39;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0280, code lost:
        r12 = r6;
        r37 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0283, code lost:
        r8 = r37;
        r7 = r39;
        r25 = r2;
        r32 = r10;
        r22 = r11;
        r26 = r12;
        r18 = r13;
        r21 = -1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzc(java.lang.Object r35, byte[] r36, int r37, int r38, int r39, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco r40) throws java.io.IOException {
        /*
            r34 = this;
            r15 = r34
            r14 = r35
            r12 = r36
            r13 = r38
            r11 = r39
            r9 = r40
            zzG(r35)
            sun.misc.Unsafe r10 = zzb
            r16 = 0
            r8 = -1
            r0 = r37
            r1 = r8
            r2 = r16
            r3 = r2
            r5 = r3
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001e:
            r17 = 0
            if (r0 >= r13) goto L_0x0545
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0031
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzk(r0, r12, r3, r9)
            int r3 = r9.zza
            r4 = r3
            r3 = r0
            goto L_0x0032
        L_0x0031:
            r4 = r0
        L_0x0032:
            int r0 = r4 >>> 3
            r7 = 3
            if (r0 <= r1) goto L_0x003d
            int r2 = r2 / r7
            int r1 = r15.zzu(r0, r2)
            goto L_0x0041
        L_0x003d:
            int r1 = r15.zzt(r0)
        L_0x0041:
            r2 = r1
            r19 = 0
            if (r2 != r8) goto L_0x0058
            r22 = r0
            r18 = r5
            r27 = r6
            r21 = r8
            r32 = r10
            r7 = r11
            r26 = r16
            r25 = 1
            r8 = r4
            goto L_0x039b
        L_0x0058:
            r8 = r4 & 7
            int[] r7 = r15.zzc
            int r22 = r2 + 1
            r1 = r7[r22]
            int r11 = zzx(r1)
            r18 = r0
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r1 & r13
            long r13 = (long) r0
            r0 = 17
            r24 = r4
            r4 = 2
            if (r11 > r0) goto L_0x0295
            int r0 = r2 + 2
            r0 = r7[r0]
            int r7 = r0 >>> 20
            r23 = 1
            int r7 = r23 << r7
            r25 = r13
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r13
            if (r0 == r6) goto L_0x009a
            if (r6 == r13) goto L_0x008e
            long r13 = (long) r6
            r6 = r35
            r10.putInt(r6, r13, r5)
            goto L_0x0090
        L_0x008e:
            r6 = r35
        L_0x0090:
            long r13 = (long) r0
            int r5 = r10.getInt(r6, r13)
            r27 = r0
            r13 = r5
            r14 = r6
            goto L_0x009f
        L_0x009a:
            r14 = r35
            r13 = r5
            r27 = r6
        L_0x009f:
            r0 = 5
            switch(r11) {
                case 0: goto L_0x025c;
                case 1: goto L_0x0242;
                case 2: goto L_0x0223;
                case 3: goto L_0x0223;
                case 4: goto L_0x0212;
                case 5: goto L_0x01f5;
                case 6: goto L_0x01e5;
                case 7: goto L_0x01cb;
                case 8: goto L_0x01ad;
                case 9: goto L_0x0186;
                case 10: goto L_0x015c;
                case 11: goto L_0x0212;
                case 12: goto L_0x0124;
                case 13: goto L_0x01e5;
                case 14: goto L_0x01f5;
                case 15: goto L_0x0101;
                case 16: goto L_0x00d6;
                default: goto L_0x00a3;
            }
        L_0x00a3:
            r6 = r2
            r11 = r18
            r0 = 3
            r2 = 1
            if (r8 != r0) goto L_0x0280
            java.lang.Object r8 = r15.zzD(r14, r6)
            int r0 = r11 << 3
            r5 = r0 | 4
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r1 = r15.zzB(r6)
            r0 = r8
            r2 = r36
            r4 = r38
            r12 = r6
            r37 = r24
            r6 = r40
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzn(r0, r1, r2, r3, r4, r5, r6)
            r15.zzL(r14, r12, r8)
            r5 = r13 | r7
            r3 = r37
            r13 = r38
            r1 = r11
            r2 = r12
            r6 = r27
            r8 = -1
            r12 = r36
            goto L_0x027c
        L_0x00d6:
            if (r8 != 0) goto L_0x00fc
            int r6 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r12, r3, r9)
            long r0 = r9.zzb
            long r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzc(r0)
            r11 = r18
            r0 = r10
            r1 = r35
            r8 = r2
            r2 = r25
            r37 = r6
            r6 = r24
            r0.putLong(r1, r2, r4)
            r5 = r13 | r7
            r0 = r37
            r13 = r38
            r3 = r6
            r2 = r8
            r1 = r11
            goto L_0x0279
        L_0x00fc:
            r11 = r18
            r12 = r2
            goto L_0x0258
        L_0x0101:
            r5 = r2
            r11 = r18
            r6 = r24
            if (r8 != 0) goto L_0x0181
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r12, r3, r9)
            int r1 = r9.zza
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzb(r1)
            r2 = r25
            r10.putInt(r14, r2, r1)
            r1 = r13 | r7
            r13 = r38
            r2 = r5
            r3 = r6
            r6 = r27
            r8 = -1
            r5 = r1
            r1 = r11
            goto L_0x027c
        L_0x0124:
            r5 = r2
            r11 = r18
            r6 = r24
            r0 = r25
            if (r8 != 0) goto L_0x0181
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r12, r3, r9)
            int r3 = r9.zza
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh r4 = r15.zzA(r5)
            if (r4 == 0) goto L_0x0158
            boolean r4 = r4.zza(r3)
            if (r4 == 0) goto L_0x0140
            goto L_0x0158
        L_0x0140:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz r0 = zzd(r35)
            long r3 = (long) r3
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r0.zzj(r6, r1)
            r0 = r2
            r2 = r5
            r3 = r6
            r1 = r11
            r5 = r13
            r6 = r27
            r8 = -1
            r13 = r38
            goto L_0x027c
        L_0x0158:
            r10.putInt(r14, r0, r3)
            goto L_0x016e
        L_0x015c:
            r5 = r2
            r11 = r18
            r6 = r24
            r0 = r25
            if (r8 != r4) goto L_0x0181
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zza(r12, r3, r9)
            java.lang.Object r3 = r9.zzc
            r10.putObject(r14, r0, r3)
        L_0x016e:
            r0 = r13 | r7
            r13 = r38
            r3 = r6
            r1 = r11
            r6 = r27
            r8 = -1
            r11 = r39
            r33 = r5
            r5 = r0
            r0 = r2
            r2 = r33
            goto L_0x001e
        L_0x0181:
            r12 = r5
            r37 = r6
            goto L_0x025a
        L_0x0186:
            r5 = r2
            r11 = r18
            r6 = r24
            if (r8 != r4) goto L_0x01a8
            java.lang.Object r8 = r15.zzD(r14, r5)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r1 = r15.zzB(r5)
            r0 = r8
            r2 = r36
            r4 = r38
            r24 = r6
            r6 = r5
            r5 = r40
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzo(r0, r1, r2, r3, r4, r5)
            r15.zzL(r14, r6, r8)
            goto L_0x0271
        L_0x01a8:
            r24 = r6
            r12 = r5
            goto L_0x0258
        L_0x01ad:
            r6 = r2
            r11 = r18
            r28 = r25
            if (r8 != r4) goto L_0x0257
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r1
            if (r0 != 0) goto L_0x01be
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzg(r12, r3, r9)
            goto L_0x01c2
        L_0x01be:
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzh(r12, r3, r9)
        L_0x01c2:
            java.lang.Object r1 = r9.zzc
            r4 = r28
            r10.putObject(r14, r4, r1)
            goto L_0x0271
        L_0x01cb:
            r6 = r2
            r11 = r18
            r4 = r25
            if (r8 != 0) goto L_0x0257
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r12, r3, r9)
            long r1 = r9.zzb
            int r1 = (r1 > r19 ? 1 : (r1 == r19 ? 0 : -1))
            if (r1 == 0) goto L_0x01de
            r1 = 1
            goto L_0x01e0
        L_0x01de:
            r1 = r16
        L_0x01e0:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzm(r14, r4, r1)
            goto L_0x0271
        L_0x01e5:
            r6 = r2
            r11 = r18
            r4 = r25
            if (r8 != r0) goto L_0x0257
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r12, r3)
            r10.putInt(r14, r4, r0)
            goto L_0x0254
        L_0x01f5:
            r6 = r2
            r11 = r18
            r4 = r25
            r0 = 1
            if (r8 != r0) goto L_0x020f
            long r17 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r12, r3)
            r0 = r10
            r1 = r35
            r8 = r3
            r2 = r4
            r4 = r17
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            goto L_0x0271
        L_0x020f:
            r2 = r0
            goto L_0x0280
        L_0x0212:
            r6 = r2
            r11 = r18
            r4 = r25
            if (r8 != 0) goto L_0x0257
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r12, r3, r9)
            int r1 = r9.zza
            r10.putInt(r14, r4, r1)
            goto L_0x0271
        L_0x0223:
            r6 = r2
            r11 = r18
            r4 = r25
            if (r8 != 0) goto L_0x0257
            int r8 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r12, r3, r9)
            long r2 = r9.zzb
            r0 = r10
            r1 = r35
            r17 = r2
            r2 = r4
            r4 = r17
            r0.putLong(r1, r2, r4)
            r5 = r13 | r7
            r13 = r38
            r2 = r6
            r0 = r8
            goto L_0x0276
        L_0x0242:
            r6 = r2
            r11 = r18
            r4 = r25
            if (r8 != r0) goto L_0x0257
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r12, r3)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzp(r14, r4, r0)
        L_0x0254:
            int r0 = r3 + 4
            goto L_0x0271
        L_0x0257:
            r12 = r6
        L_0x0258:
            r37 = r24
        L_0x025a:
            r2 = 1
            goto L_0x0283
        L_0x025c:
            r6 = r2
            r11 = r18
            r4 = r25
            r2 = 1
            if (r8 != r2) goto L_0x0280
            long r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r12, r3)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzo(r14, r4, r0)
            int r0 = r3 + 8
        L_0x0271:
            r5 = r13 | r7
            r13 = r38
            r2 = r6
        L_0x0276:
            r1 = r11
            r3 = r24
        L_0x0279:
            r6 = r27
            r8 = -1
        L_0x027c:
            r11 = r39
            goto L_0x001e
        L_0x0280:
            r12 = r6
            r37 = r24
        L_0x0283:
            r8 = r37
            r7 = r39
            r25 = r2
            r32 = r10
            r22 = r11
            r26 = r12
            r18 = r13
            r21 = -1
            goto L_0x039b
        L_0x0295:
            r12 = r2
            r30 = r13
            r13 = r18
            r37 = r24
            r2 = 1
            r14 = r35
            r0 = 27
            if (r11 != r0) goto L_0x02fc
            if (r8 != r4) goto L_0x02eb
            r0 = r30
            java.lang.Object r2 = r10.getObject(r14, r0)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel r2 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel) r2
            boolean r4 = r2.zzc()
            if (r4 != 0) goto L_0x02c4
            int r4 = r2.size()
            if (r4 != 0) goto L_0x02bc
            r4 = 10
            goto L_0x02bd
        L_0x02bc:
            int r4 = r4 + r4
        L_0x02bd:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel r2 = r2.zzd(r4)
            r10.putObject(r14, r0, r2)
        L_0x02c4:
            r7 = r2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r0 = r15.zzB(r12)
            r1 = r37
            r2 = r36
            r4 = r38
            r18 = r5
            r5 = r7
            r27 = r6
            r6 = r40
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zze(r0, r1, r2, r3, r4, r5, r6)
            r3 = r37
            r11 = r39
            r2 = r12
            r1 = r13
            r5 = r18
            r6 = r27
            r8 = -1
            r12 = r36
            r13 = r38
            goto L_0x001e
        L_0x02eb:
            r18 = r5
            r27 = r6
            r25 = r2
            r15 = r3
            r32 = r10
            r26 = r12
            r22 = r13
            r21 = -1
            goto L_0x0377
        L_0x02fc:
            r18 = r5
            r27 = r6
            r23 = r30
            r0 = 49
            if (r11 > r0) goto L_0x0350
            long r6 = (long) r1
            r0 = r34
            r25 = r2
            r1 = r35
            r2 = r36
            r5 = r3
            r4 = r38
            r15 = r5
            r5 = r37
            r28 = r6
            r6 = r13
            r7 = r8
            r21 = -1
            r8 = r12
            r32 = r10
            r9 = r28
            r26 = r12
            r22 = r13
            r12 = r23
            r14 = r40
            int r0 = r0.zzs(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x034a
        L_0x032e:
            r15 = r34
            r14 = r35
            r12 = r36
            r3 = r37
            r13 = r38
            r11 = r39
            r9 = r40
            r5 = r18
            r8 = r21
            r1 = r22
            r2 = r26
            r6 = r27
            r10 = r32
            goto L_0x001e
        L_0x034a:
            r8 = r37
            r7 = r39
            r3 = r0
            goto L_0x039b
        L_0x0350:
            r25 = r2
            r15 = r3
            r32 = r10
            r26 = r12
            r22 = r13
            r21 = -1
            r0 = 50
            if (r11 != r0) goto L_0x037d
            if (r8 != r4) goto L_0x0377
            r0 = r34
            r1 = r35
            r2 = r36
            r3 = r15
            r4 = r38
            r5 = r26
            r6 = r23
            r8 = r40
            int r0 = r0.zzq(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x034a
            goto L_0x032e
        L_0x0377:
            r8 = r37
            r7 = r39
            r3 = r15
            goto L_0x039b
        L_0x037d:
            r0 = r34
            r9 = r1
            r1 = r35
            r2 = r36
            r3 = r15
            r4 = r38
            r5 = r37
            r6 = r22
            r7 = r8
            r8 = r9
            r9 = r11
            r10 = r23
            r12 = r26
            r13 = r40
            int r0 = r0.zzr(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r15) goto L_0x034a
            goto L_0x032e
        L_0x039b:
            if (r8 != r7) goto L_0x03ae
            if (r7 == 0) goto L_0x03ae
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r34
            r12 = r35
            r0 = r3
            r3 = r8
            r5 = r18
            r6 = r27
            goto L_0x0551
        L_0x03ae:
            r9 = r34
            boolean r0 = r9.zzh
            if (r0 == 0) goto L_0x0518
            r10 = r40
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo r0 = r10.zzd
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo.zza
            if (r0 == r1) goto L_0x0513
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r1 = r9.zzg
            r11 = r22
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeb r0 = r0.zzb(r1, r11)
            if (r0 != 0) goto L_0x03dc
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz r4 = zzd(r35)
            r0 = r8
            r1 = r36
            r2 = r3
            r3 = r38
            r5 = r40
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzi(r0, r1, r2, r3, r4, r5)
            r12 = r35
            r13 = r36
            goto L_0x0530
        L_0x03dc:
            r12 = r35
            r1 = r12
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdz r1 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdz) r1
            r1.zzc()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt r1 = r1.zza
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea r2 = r0.zzd
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r2 = r2.zzb
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho.ENUM
            if (r2 == r4) goto L_0x050d
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea r2 = r0.zzd
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r2 = r2.zzb
            int r2 = r2.ordinal()
            switch(r2) {
                case 0: goto L_0x04f4;
                case 1: goto L_0x04e3;
                case 2: goto L_0x04d6;
                case 3: goto L_0x04d6;
                case 4: goto L_0x04c9;
                case 5: goto L_0x04be;
                case 6: goto L_0x04b3;
                case 7: goto L_0x049f;
                case 8: goto L_0x0495;
                case 9: goto L_0x0462;
                case 10: goto L_0x0433;
                case 11: goto L_0x0429;
                case 12: goto L_0x04c9;
                case 13: goto L_0x0421;
                case 14: goto L_0x04b3;
                case 15: goto L_0x04be;
                case 16: goto L_0x040f;
                case 17: goto L_0x03fd;
                default: goto L_0x03f9;
            }
        L_0x03f9:
            r13 = r36
            goto L_0x0504
        L_0x03fd:
            r13 = r36
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r13, r3, r10)
            long r4 = r10.zzb
            long r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzc(r4)
            java.lang.Long r17 = java.lang.Long.valueOf(r4)
            goto L_0x0504
        L_0x040f:
            r13 = r36
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r13, r3, r10)
            int r2 = r10.zza
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzb(r2)
            java.lang.Integer r17 = java.lang.Integer.valueOf(r2)
            goto L_0x0504
        L_0x0421:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Shouldn't reach here."
            r0.<init>(r1)
            throw r0
        L_0x0429:
            r13 = r36
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zza(r13, r3, r10)
            java.lang.Object r2 = r10.zzc
            goto L_0x0506
        L_0x0433:
            r13 = r36
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfx r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfx.zza()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r4 = r0.zzc
            java.lang.Class r4 = r4.getClass()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r2 = r2.zzb(r4)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea r4 = r0.zzd
            java.lang.Object r4 = r1.zze(r4)
            if (r4 != 0) goto L_0x0454
            java.lang.Object r4 = r2.zze()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea r0 = r0.zzd
            r1.zzi(r0, r4)
        L_0x0454:
            r0 = r4
            r1 = r2
            r2 = r36
            r4 = r38
            r5 = r40
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzo(r0, r1, r2, r3, r4, r5)
            goto L_0x0530
        L_0x0462:
            r13 = r36
            int r2 = r11 << 3
            r5 = r2 | 4
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfx r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfx.zza()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfo r4 = r0.zzc
            java.lang.Class r4 = r4.getClass()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r2 = r2.zzb(r4)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea r4 = r0.zzd
            java.lang.Object r4 = r1.zze(r4)
            if (r4 != 0) goto L_0x0487
            java.lang.Object r4 = r2.zze()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea r0 = r0.zzd
            r1.zzi(r0, r4)
        L_0x0487:
            r0 = r4
            r1 = r2
            r2 = r36
            r4 = r38
            r6 = r40
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzn(r0, r1, r2, r3, r4, r5, r6)
            goto L_0x0530
        L_0x0495:
            r13 = r36
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzg(r13, r3, r10)
            java.lang.Object r2 = r10.zzc
            goto L_0x0506
        L_0x049f:
            r13 = r36
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r13, r3, r10)
            long r4 = r10.zzb
            int r2 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r2 == 0) goto L_0x04ac
            goto L_0x04ae
        L_0x04ac:
            r25 = r16
        L_0x04ae:
            java.lang.Boolean r17 = java.lang.Boolean.valueOf(r25)
            goto L_0x0504
        L_0x04b3:
            r13 = r36
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r13, r3)
            java.lang.Integer r17 = java.lang.Integer.valueOf(r2)
            goto L_0x04f1
        L_0x04be:
            r13 = r36
            long r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r13, r3)
            java.lang.Long r17 = java.lang.Long.valueOf(r4)
            goto L_0x0502
        L_0x04c9:
            r13 = r36
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r13, r3, r10)
            int r2 = r10.zza
            java.lang.Integer r17 = java.lang.Integer.valueOf(r2)
            goto L_0x0504
        L_0x04d6:
            r13 = r36
            int r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r13, r3, r10)
            long r4 = r10.zzb
            java.lang.Long r17 = java.lang.Long.valueOf(r4)
            goto L_0x0504
        L_0x04e3:
            r13 = r36
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r13, r3)
            float r2 = java.lang.Float.intBitsToFloat(r2)
            java.lang.Float r17 = java.lang.Float.valueOf(r2)
        L_0x04f1:
            int r3 = r3 + 4
            goto L_0x0504
        L_0x04f4:
            r13 = r36
            long r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r13, r3)
            double r4 = java.lang.Double.longBitsToDouble(r4)
            java.lang.Double r17 = java.lang.Double.valueOf(r4)
        L_0x0502:
            int r3 = r3 + 8
        L_0x0504:
            r2 = r17
        L_0x0506:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzea r0 = r0.zzd
            r1.zzi(r0, r2)
            r0 = r3
            goto L_0x0530
        L_0x050d:
            r13 = r36
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r13, r3, r10)
            throw r17
        L_0x0513:
            r12 = r35
            r13 = r36
            goto L_0x051e
        L_0x0518:
            r12 = r35
            r13 = r36
            r10 = r40
        L_0x051e:
            r11 = r22
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz r4 = zzd(r35)
            r0 = r8
            r1 = r36
            r2 = r3
            r3 = r38
            r5 = r40
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzi(r0, r1, r2, r3, r4, r5)
        L_0x0530:
            r3 = r8
            r15 = r9
            r9 = r10
            r1 = r11
            r14 = r12
            r12 = r13
            r5 = r18
            r8 = r21
            r2 = r26
            r6 = r27
            r10 = r32
            r13 = r38
            r11 = r7
            goto L_0x001e
        L_0x0545:
            r18 = r5
            r27 = r6
            r32 = r10
            r7 = r11
            r12 = r14
            r9 = r15
            r1 = 1048575(0xfffff, float:1.469367E-39)
        L_0x0551:
            if (r6 == r1) goto L_0x0559
            long r10 = (long) r6
            r2 = r32
            r2.putInt(r12, r10, r5)
        L_0x0559:
            int r2 = r9.zzj
        L_0x055b:
            int r4 = r9.zzk
            if (r2 >= r4) goto L_0x0586
            int[] r4 = r9.zzi
            r4 = r4[r2]
            int[] r5 = r9.zzc
            r5 = r5[r4]
            int r5 = r9.zzy(r4)
            r5 = r5 & r1
            long r5 = (long) r5
            java.lang.Object r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r12, r5)
            if (r5 != 0) goto L_0x0574
            goto L_0x057a
        L_0x0574:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh r6 = r9.zzA(r4)
            if (r6 != 0) goto L_0x057d
        L_0x057a:
            int r2 = r2 + 1
            goto L_0x055b
        L_0x057d:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfi r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfi) r5
            java.lang.Object r0 = r9.zzC(r4)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfh r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfh) r0
            throw r17
        L_0x0586:
            if (r7 != 0) goto L_0x0592
            r1 = r38
            if (r0 != r1) goto L_0x058d
            goto L_0x0598
        L_0x058d:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zze()
            throw r0
        L_0x0592:
            r1 = r38
            if (r0 > r1) goto L_0x0599
            if (r3 != r7) goto L_0x0599
        L_0x0598:
            return r0
        L_0x0599:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zze()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco):int");
    }

    public final Object zze() {
        return ((zzed) this.zzg).zzJ();
    }

    public final void zzf(Object obj) {
        if (zzS(obj)) {
            if (obj instanceof zzed) {
                zzed zzed = (zzed) obj;
                zzed.zzV(Integer.MAX_VALUE);
                zzed.zzb = 0;
                zzed.zzT();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzy = zzy(i);
                int i2 = 1048575 & zzy;
                int zzx = zzx(zzy);
                long j = (long) i2;
                if (zzx != 9) {
                    if (zzx == 60 || zzx == 68) {
                        if (zzT(obj, this.zzc[i], i)) {
                            zzB(i).zzf(zzb.getObject(obj, j));
                        }
                    } else {
                        switch (zzx) {
                            case 17:
                                break;
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
                                this.zzl.zza(obj, j);
                                continue;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzfi) object).zzc();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    }
                }
                if (zzP(obj, i)) {
                    zzB(i).zzf(zzb.getObject(obj, j));
                }
            }
            this.zzm.zzg(obj);
            if (this.zzh) {
                this.zzn.zze(obj);
            }
        }
    }

    public final void zzg(Object obj, Object obj2) {
        zzG(obj);
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzy = zzy(i);
            int i2 = this.zzc[i];
            long j = (long) (1048575 & zzy);
            switch (zzx(zzy)) {
                case 0:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzo(obj, j, zzhi.zza(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 1:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzp(obj, j, zzhi.zzb(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 2:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzr(obj, j, zzhi.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 3:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzr(obj, j, zzhi.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 4:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzq(obj, j, zzhi.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 5:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzr(obj, j, zzhi.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 6:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzq(obj, j, zzhi.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 7:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzm(obj, j, zzhi.zzw(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 8:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzs(obj, j, zzhi.zzf(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 9:
                    zzH(obj, obj2, i);
                    break;
                case 10:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzs(obj, j, zzhi.zzf(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 11:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzq(obj, j, zzhi.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 12:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzq(obj, j, zzhi.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 13:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzq(obj, j, zzhi.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 14:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzr(obj, j, zzhi.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 15:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzq(obj, j, zzhi.zzc(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 16:
                    if (!zzP(obj2, i)) {
                        break;
                    } else {
                        zzhi.zzr(obj, j, zzhi.zzd(obj2, j));
                        zzJ(obj, i);
                        break;
                    }
                case 17:
                    zzH(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
                    this.zzl.zzb(obj, obj2, j);
                    break;
                case 50:
                    int i3 = zzgj.zza;
                    zzhi.zzs(obj, j, zzfj.zzb(zzhi.zzf(obj, j), zzhi.zzf(obj2, j)));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case Elf64.Ehdr.E_SHENTSIZE:
                case 59:
                    if (!zzT(obj2, i2, i)) {
                        break;
                    } else {
                        zzhi.zzs(obj, j, zzhi.zzf(obj2, j));
                        zzK(obj, i2, i);
                        break;
                    }
                case 60:
                    zzI(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT:
                case Elf64.Ehdr.E_SHSTRNDX:
                case HtmlCompat.FROM_HTML_MODE_COMPACT:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                case 67:
                    if (!zzT(obj2, i2, i)) {
                        break;
                    } else {
                        zzhi.zzs(obj, j, zzhi.zzf(obj2, j));
                        zzK(obj, i2, i);
                        break;
                    }
                case 68:
                    zzI(obj, obj2, i);
                    break;
            }
        }
        zzgj.zzC(this.zzm, obj, obj2);
        if (this.zzh) {
            zzgj.zzB(this.zzn, obj, obj2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02d9, code lost:
        if (r0 != r15) goto L_0x0296;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02fd, code lost:
        if (r0 != r15) goto L_0x0296;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0096, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01ec, code lost:
        r6 = r6 | r22;
        r2 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01ef, code lost:
        r1 = r19;
        r8 = r24;
        r9 = -1;
        r13 = r34;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01f8, code lost:
        r2 = r5;
        r27 = r10;
        r22 = r13;
        r18 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0294, code lost:
        if (r0 != r15) goto L_0x0296;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x02ac, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzh(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco r35) throws java.io.IOException {
        /*
            r30 = this;
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho.DOUBLE
            int r0 = r15.zzo
            r9 = -1
            int r0 = r0 + r9
            if (r0 == 0) goto L_0x0342
            zzG(r31)
            sun.misc.Unsafe r10 = zzb
            r16 = 0
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r33
            r7 = r8
            r1 = r9
            r2 = r16
            r6 = r2
        L_0x0023:
            if (r0 >= r13) goto L_0x0327
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0035
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzk(r0, r12, r3, r11)
            int r3 = r11.zza
            r4 = r0
            r17 = r3
            goto L_0x0038
        L_0x0035:
            r17 = r0
            r4 = r3
        L_0x0038:
            int r5 = r17 >>> 3
            if (r5 <= r1) goto L_0x0043
            int r2 = r2 / 3
            int r0 = r15.zzu(r5, r2)
            goto L_0x0047
        L_0x0043:
            int r0 = r15.zzt(r5)
        L_0x0047:
            r2 = r0
            if (r2 != r9) goto L_0x0055
            r2 = r4
            r19 = r5
            r18 = r9
            r27 = r10
            r22 = r16
            goto L_0x0300
        L_0x0055:
            r3 = r17 & 7
            int[] r0 = r15.zzc
            int r1 = r2 + 1
            r1 = r0[r1]
            int r13 = zzx(r1)
            r9 = r1 & r8
            long r8 = (long) r9
            r33 = r5
            r5 = 17
            r20 = r1
            if (r13 > r5) goto L_0x0201
            int r5 = r2 + 2
            r0 = r0[r5]
            int r5 = r0 >>> 20
            r1 = 1
            int r22 = r1 << r5
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            r19 = r2
            if (r0 == r7) goto L_0x008c
            if (r7 == r5) goto L_0x0083
            long r1 = (long) r7
            r10.putInt(r14, r1, r6)
        L_0x0083:
            if (r0 == r5) goto L_0x008b
            long r1 = (long) r0
            int r1 = r10.getInt(r14, r1)
            r6 = r1
        L_0x008b:
            r7 = r0
        L_0x008c:
            r0 = 5
            switch(r13) {
                case 0: goto L_0x01d5;
                case 1: goto L_0x01be;
                case 2: goto L_0x01a2;
                case 3: goto L_0x01a2;
                case 4: goto L_0x018f;
                case 5: goto L_0x0174;
                case 6: goto L_0x0161;
                case 7: goto L_0x0144;
                case 8: goto L_0x0125;
                case 9: goto L_0x0103;
                case 10: goto L_0x00ef;
                case 11: goto L_0x018f;
                case 12: goto L_0x00dc;
                case 13: goto L_0x0161;
                case 14: goto L_0x0174;
                case 15: goto L_0x00c5;
                case 16: goto L_0x0099;
                default: goto L_0x0090;
            }
        L_0x0090:
            r24 = r5
            r13 = r19
            r19 = r33
        L_0x0096:
            r5 = r4
            goto L_0x01f8
        L_0x0099:
            if (r3 != 0) goto L_0x00bb
            int r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r12, r4, r11)
            long r0 = r11.zzb
            long r20 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzc(r0)
            r0 = r10
            r1 = r31
            r4 = r19
            r2 = r8
            r19 = r33
            r8 = r4
            r24 = r5
            r4 = r20
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r2 = r8
            r0 = r13
            goto L_0x01ef
        L_0x00bb:
            r24 = r5
            r8 = r19
            r19 = r33
            r5 = r4
            r13 = r8
            goto L_0x01f8
        L_0x00c5:
            r24 = r5
            r13 = r19
            r19 = r33
            if (r3 != 0) goto L_0x0096
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r12, r4, r11)
            int r1 = r11.zza
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf.zzb(r1)
            r10.putInt(r14, r8, r1)
            goto L_0x01ec
        L_0x00dc:
            r24 = r5
            r13 = r19
            r19 = r33
            if (r3 != 0) goto L_0x0096
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r12, r4, r11)
            int r1 = r11.zza
            r10.putInt(r14, r8, r1)
            goto L_0x01ec
        L_0x00ef:
            r24 = r5
            r13 = r19
            r0 = 2
            r19 = r33
            if (r3 != r0) goto L_0x0096
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zza(r12, r4, r11)
            java.lang.Object r1 = r11.zzc
            r10.putObject(r14, r8, r1)
            goto L_0x01ec
        L_0x0103:
            r24 = r5
            r13 = r19
            r0 = 2
            r19 = r33
            if (r3 != r0) goto L_0x0096
            java.lang.Object r8 = r15.zzD(r14, r13)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r1 = r15.zzB(r13)
            r0 = r8
            r2 = r32
            r3 = r4
            r4 = r34
            r5 = r35
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzo(r0, r1, r2, r3, r4, r5)
            r15.zzL(r14, r13, r8)
            goto L_0x01ec
        L_0x0125:
            r24 = r5
            r13 = r19
            r0 = 2
            r19 = r33
            if (r3 != r0) goto L_0x0096
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r20 & r0
            if (r0 != 0) goto L_0x0139
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzg(r12, r4, r11)
            goto L_0x013d
        L_0x0139:
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzh(r12, r4, r11)
        L_0x013d:
            java.lang.Object r1 = r11.zzc
            r10.putObject(r14, r8, r1)
            goto L_0x01ec
        L_0x0144:
            r24 = r5
            r13 = r19
            r19 = r33
            if (r3 != 0) goto L_0x0096
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r12, r4, r11)
            long r1 = r11.zzb
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x015a
            r1 = 1
            goto L_0x015c
        L_0x015a:
            r1 = r16
        L_0x015c:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzm(r14, r8, r1)
            goto L_0x01ec
        L_0x0161:
            r24 = r5
            r13 = r19
            r19 = r33
            if (r3 != r0) goto L_0x0096
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r12, r4)
            r10.putInt(r14, r8, r0)
            int r0 = r4 + 4
            goto L_0x01ec
        L_0x0174:
            r24 = r5
            r13 = r19
            r0 = 1
            r19 = r33
            if (r3 != r0) goto L_0x0096
            long r20 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r12, r4)
            r0 = r10
            r1 = r31
            r2 = r8
            r8 = r4
            r4 = r20
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            goto L_0x01ec
        L_0x018f:
            r24 = r5
            r13 = r19
            r19 = r33
            r5 = r4
            if (r3 != 0) goto L_0x01f8
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzj(r12, r5, r11)
            int r1 = r11.zza
            r10.putInt(r14, r8, r1)
            goto L_0x01ec
        L_0x01a2:
            r24 = r5
            r13 = r19
            r19 = r33
            r5 = r4
            if (r3 != 0) goto L_0x01f8
            int r17 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzm(r12, r5, r11)
            long r4 = r11.zzb
            r0 = r10
            r1 = r31
            r2 = r8
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r2 = r13
            r0 = r17
            goto L_0x01ef
        L_0x01be:
            r24 = r5
            r13 = r19
            r19 = r33
            r5 = r4
            if (r3 != r0) goto L_0x01f8
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzb(r12, r5)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzp(r14, r8, r0)
            int r0 = r5 + 4
            goto L_0x01ec
        L_0x01d5:
            r24 = r5
            r13 = r19
            r0 = 1
            r19 = r33
            r5 = r4
            if (r3 != r0) goto L_0x01f8
            long r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzq(r12, r5)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzo(r14, r8, r0)
            int r0 = r5 + 8
        L_0x01ec:
            r6 = r6 | r22
            r2 = r13
        L_0x01ef:
            r1 = r19
            r8 = r24
            r9 = -1
            r13 = r34
            goto L_0x0023
        L_0x01f8:
            r2 = r5
            r27 = r10
            r22 = r13
            r18 = -1
            goto L_0x0300
        L_0x0201:
            r19 = r33
            r5 = r4
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r2
            r0 = 27
            if (r13 != r0) goto L_0x025b
            r0 = 2
            if (r3 != r0) goto L_0x024e
            java.lang.Object r0 = r10.getObject(r14, r8)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel r0 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x022c
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0224
            r1 = 10
            goto L_0x0225
        L_0x0224:
            int r1 = r1 + r1
        L_0x0225:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel r0 = r0.zzd(r1)
            r10.putObject(r14, r8, r0)
        L_0x022c:
            r8 = r0
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r0 = r15.zzB(r4)
            r1 = r17
            r2 = r32
            r3 = r5
            r22 = r4
            r4 = r34
            r5 = r8
            r8 = r6
            r6 = r35
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zze(r0, r1, r2, r3, r4, r5, r6)
            r13 = r34
            r6 = r8
            r1 = r19
            r2 = r22
            r8 = r24
            r9 = -1
            goto L_0x0023
        L_0x024e:
            r22 = r4
            r15 = r5
            r25 = r6
            r26 = r7
            r27 = r10
            r18 = -1
            goto L_0x02dc
        L_0x025b:
            r22 = r4
            r0 = 49
            if (r13 > r0) goto L_0x02ae
            r1 = r20
            long r1 = (long) r1
            r0 = r30
            r20 = r1
            r1 = r31
            r2 = r32
            r4 = r3
            r3 = r5
            r33 = r4
            r4 = r34
            r15 = r5
            r5 = r17
            r25 = r6
            r6 = r19
            r26 = r7
            r7 = r33
            r28 = r8
            r9 = r24
            r23 = r28
            r8 = r22
            r27 = r10
            r18 = -1
            r9 = r20
            r11 = r13
            r12 = r23
            r14 = r35
            int r0 = r0.zzs(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x02ac
        L_0x0296:
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r9 = r18
            r1 = r19
            r2 = r22
            r6 = r25
            r7 = r26
            goto L_0x0320
        L_0x02ac:
            r2 = r0
            goto L_0x02dd
        L_0x02ae:
            r33 = r3
            r15 = r5
            r25 = r6
            r26 = r7
            r23 = r8
            r27 = r10
            r1 = r20
            r18 = -1
            r0 = 50
            if (r13 != r0) goto L_0x02e2
            r7 = r33
            r0 = 2
            if (r7 != r0) goto L_0x02dc
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r22
            r6 = r23
            r8 = r35
            int r0 = r0.zzq(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x02ac
            goto L_0x0296
        L_0x02dc:
            r2 = r15
        L_0x02dd:
            r6 = r25
            r7 = r26
            goto L_0x0300
        L_0x02e2:
            r7 = r33
            r0 = r30
            r8 = r1
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r17
            r6 = r19
            r9 = r13
            r10 = r23
            r12 = r22
            r13 = r35
            int r0 = r0.zzr(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r15) goto L_0x02ac
            goto L_0x0296
        L_0x0300:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz r4 = zzd(r31)
            r0 = r17
            r1 = r32
            r3 = r34
            r5 = r35
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp.zzi(r0, r1, r2, r3, r4, r5)
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r9 = r18
            r1 = r19
            r2 = r22
        L_0x0320:
            r10 = r27
            r8 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0023
        L_0x0327:
            r25 = r6
            r1 = r8
            r27 = r10
            if (r7 == r1) goto L_0x0338
            long r1 = (long) r7
            r3 = r31
            r6 = r25
            r4 = r27
            r4.putInt(r3, r1, r6)
        L_0x0338:
            r4 = r34
            if (r0 != r4) goto L_0x033d
            return
        L_0x033d:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo.zze()
            throw r0
        L_0x0342:
            r4 = r13
            r3 = r14
            r5 = 0
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r33
            r4 = r34
            r6 = r35
            r0.zzc(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr.zzh(java.lang.Object, byte[], int, int, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.util.Map$Entry} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v144, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v23, resolved type: java.util.Map$Entry} */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x05a0, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x05a1, code lost:
        r16 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x085a, code lost:
        r16 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x08cd, code lost:
        r16 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x09e0, code lost:
        r11 = r11 + 3;
        r5 = r6;
        r6 = r16;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0510  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0558  */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x09e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzi(java.lang.Object r18, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhq r19) throws java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho r3 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzho.DOUBLE
            int r3 = r0.zzo
            int r3 = r3 + -1
            r5 = 1
            r6 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            if (r3 == 0) goto L_0x052f
            boolean r3 = r0.zzh
            if (r3 == 0) goto L_0x0030
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r3 = r0.zzn
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt r3 = r3.zzb(r1)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgu r8 = r3.zza
            boolean r8 = r8.isEmpty()
            if (r8 != 0) goto L_0x0030
            java.util.Iterator r3 = r3.zzf()
            java.lang.Object r8 = r3.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x0032
        L_0x0030:
            r3 = 0
            r8 = 0
        L_0x0032:
            int[] r9 = r0.zzc
            int r9 = r9.length
            r10 = r6
        L_0x0036:
            if (r10 >= r9) goto L_0x050e
            int r11 = r0.zzy(r10)
            int[] r12 = r0.zzc
            r12 = r12[r10]
        L_0x0040:
            if (r8 == 0) goto L_0x005e
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r13 = r0.zzn
            int r13 = r13.zza(r8)
            if (r13 > r12) goto L_0x005e
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r13 = r0.zzn
            r13.zzf(r2, r8)
            boolean r8 = r3.hasNext()
            if (r8 == 0) goto L_0x005c
            java.lang.Object r8 = r3.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x0040
        L_0x005c:
            r8 = 0
            goto L_0x0040
        L_0x005e:
            int r13 = zzx(r11)
            switch(r13) {
                case 0: goto L_0x04fb;
                case 1: goto L_0x04eb;
                case 2: goto L_0x04db;
                case 3: goto L_0x04cb;
                case 4: goto L_0x04bb;
                case 5: goto L_0x04ab;
                case 6: goto L_0x049b;
                case 7: goto L_0x048a;
                case 8: goto L_0x0479;
                case 9: goto L_0x0464;
                case 10: goto L_0x0451;
                case 11: goto L_0x0440;
                case 12: goto L_0x042f;
                case 13: goto L_0x041e;
                case 14: goto L_0x040d;
                case 15: goto L_0x03fc;
                case 16: goto L_0x03eb;
                case 17: goto L_0x03d6;
                case 18: goto L_0x03c5;
                case 19: goto L_0x03b4;
                case 20: goto L_0x03a3;
                case 21: goto L_0x0392;
                case 22: goto L_0x0381;
                case 23: goto L_0x0370;
                case 24: goto L_0x035f;
                case 25: goto L_0x034e;
                case 26: goto L_0x033d;
                case 27: goto L_0x0328;
                case 28: goto L_0x0317;
                case 29: goto L_0x0306;
                case 30: goto L_0x02f5;
                case 31: goto L_0x02e4;
                case 32: goto L_0x02d3;
                case 33: goto L_0x02c2;
                case 34: goto L_0x02b1;
                case 35: goto L_0x02a0;
                case 36: goto L_0x028f;
                case 37: goto L_0x027e;
                case 38: goto L_0x026d;
                case 39: goto L_0x025c;
                case 40: goto L_0x024b;
                case 41: goto L_0x023a;
                case 42: goto L_0x0229;
                case 43: goto L_0x0218;
                case 44: goto L_0x0207;
                case 45: goto L_0x01f6;
                case 46: goto L_0x01e5;
                case 47: goto L_0x01d4;
                case 48: goto L_0x01c3;
                case 49: goto L_0x01ae;
                case 50: goto L_0x01a3;
                case 51: goto L_0x0192;
                case 52: goto L_0x0181;
                case 53: goto L_0x0170;
                case 54: goto L_0x015f;
                case 55: goto L_0x014e;
                case 56: goto L_0x013d;
                case 57: goto L_0x012c;
                case 58: goto L_0x011b;
                case 59: goto L_0x010a;
                case 60: goto L_0x00f5;
                case 61: goto L_0x00e2;
                case 62: goto L_0x00d1;
                case 63: goto L_0x00c0;
                case 64: goto L_0x00af;
                case 65: goto L_0x009e;
                case 66: goto L_0x008d;
                case 67: goto L_0x007c;
                case 68: goto L_0x0067;
                default: goto L_0x0065;
            }
        L_0x0065:
            goto L_0x050a
        L_0x0067:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r13 = r0.zzB(r10)
            r2.zzq(r12, r11, r13)
            goto L_0x050a
        L_0x007c:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            long r13 = zzz(r1, r13)
            r2.zzD(r12, r13)
            goto L_0x050a
        L_0x008d:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = zzp(r1, r13)
            r2.zzB(r12, r11)
            goto L_0x050a
        L_0x009e:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            long r13 = zzz(r1, r13)
            r2.zzz(r12, r13)
            goto L_0x050a
        L_0x00af:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = zzp(r1, r13)
            r2.zzx(r12, r11)
            goto L_0x050a
        L_0x00c0:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = zzp(r1, r13)
            r2.zzi(r12, r11)
            goto L_0x050a
        L_0x00d1:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = zzp(r1, r13)
            r2.zzI(r12, r11)
            goto L_0x050a
        L_0x00e2:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r11 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r11
            r2.zzd(r12, r11)
            goto L_0x050a
        L_0x00f5:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r13 = r0.zzB(r10)
            r2.zzv(r12, r11, r13)
            goto L_0x050a
        L_0x010a:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            zzV(r12, r11, r2)
            goto L_0x050a
        L_0x011b:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            boolean r11 = zzU(r1, r13)
            r2.zzb(r12, r11)
            goto L_0x050a
        L_0x012c:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = zzp(r1, r13)
            r2.zzk(r12, r11)
            goto L_0x050a
        L_0x013d:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            long r13 = zzz(r1, r13)
            r2.zzm(r12, r13)
            goto L_0x050a
        L_0x014e:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = zzp(r1, r13)
            r2.zzr(r12, r11)
            goto L_0x050a
        L_0x015f:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            long r13 = zzz(r1, r13)
            r2.zzK(r12, r13)
            goto L_0x050a
        L_0x0170:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            long r13 = zzz(r1, r13)
            r2.zzt(r12, r13)
            goto L_0x050a
        L_0x0181:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            float r11 = zzn(r1, r13)
            r2.zzo(r12, r11)
            goto L_0x050a
        L_0x0192:
            boolean r13 = r0.zzT(r1, r12, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            double r13 = zzm(r1, r13)
            r2.zzf(r12, r13)
            goto L_0x050a
        L_0x01a3:
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            r0.zzN(r2, r12, r11, r10)
            goto L_0x050a
        L_0x01ae:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r13 = r0.zzB(r10)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzL(r12, r11, r2, r13)
            goto L_0x050a
        L_0x01c3:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzS(r12, r11, r2, r5)
            goto L_0x050a
        L_0x01d4:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzR(r12, r11, r2, r5)
            goto L_0x050a
        L_0x01e5:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzQ(r12, r11, r2, r5)
            goto L_0x050a
        L_0x01f6:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzP(r12, r11, r2, r5)
            goto L_0x050a
        L_0x0207:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzH(r12, r11, r2, r5)
            goto L_0x050a
        L_0x0218:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzU(r12, r11, r2, r5)
            goto L_0x050a
        L_0x0229:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzE(r12, r11, r2, r5)
            goto L_0x050a
        L_0x023a:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzI(r12, r11, r2, r5)
            goto L_0x050a
        L_0x024b:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzJ(r12, r11, r2, r5)
            goto L_0x050a
        L_0x025c:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzM(r12, r11, r2, r5)
            goto L_0x050a
        L_0x026d:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzV(r12, r11, r2, r5)
            goto L_0x050a
        L_0x027e:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzN(r12, r11, r2, r5)
            goto L_0x050a
        L_0x028f:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzK(r12, r11, r2, r5)
            goto L_0x050a
        L_0x02a0:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzG(r12, r11, r2, r5)
            goto L_0x050a
        L_0x02b1:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzS(r12, r11, r2, r6)
            goto L_0x050a
        L_0x02c2:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzR(r12, r11, r2, r6)
            goto L_0x050a
        L_0x02d3:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzQ(r12, r11, r2, r6)
            goto L_0x050a
        L_0x02e4:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzP(r12, r11, r2, r6)
            goto L_0x050a
        L_0x02f5:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzH(r12, r11, r2, r6)
            goto L_0x050a
        L_0x0306:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzU(r12, r11, r2, r6)
            goto L_0x050a
        L_0x0317:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzF(r12, r11, r2)
            goto L_0x050a
        L_0x0328:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r13 = r0.zzB(r10)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzO(r12, r11, r2, r13)
            goto L_0x050a
        L_0x033d:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzT(r12, r11, r2)
            goto L_0x050a
        L_0x034e:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzE(r12, r11, r2, r6)
            goto L_0x050a
        L_0x035f:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzI(r12, r11, r2, r6)
            goto L_0x050a
        L_0x0370:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzJ(r12, r11, r2, r6)
            goto L_0x050a
        L_0x0381:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzM(r12, r11, r2, r6)
            goto L_0x050a
        L_0x0392:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzV(r12, r11, r2, r6)
            goto L_0x050a
        L_0x03a3:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzN(r12, r11, r2, r6)
            goto L_0x050a
        L_0x03b4:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzK(r12, r11, r2, r6)
            goto L_0x050a
        L_0x03c5:
            int[] r12 = r0.zzc
            r12 = r12[r10]
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            java.util.List r11 = (java.util.List) r11
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzG(r12, r11, r2, r6)
            goto L_0x050a
        L_0x03d6:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r13 = r0.zzB(r10)
            r2.zzq(r12, r11, r13)
            goto L_0x050a
        L_0x03eb:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            long r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r1, r13)
            r2.zzD(r12, r13)
            goto L_0x050a
        L_0x03fc:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r1, r13)
            r2.zzB(r12, r11)
            goto L_0x050a
        L_0x040d:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            long r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r1, r13)
            r2.zzz(r12, r13)
            goto L_0x050a
        L_0x041e:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r1, r13)
            r2.zzx(r12, r11)
            goto L_0x050a
        L_0x042f:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r1, r13)
            r2.zzi(r12, r11)
            goto L_0x050a
        L_0x0440:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r1, r13)
            r2.zzI(r12, r11)
            goto L_0x050a
        L_0x0451:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r11 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r11
            r2.zzd(r12, r11)
            goto L_0x050a
        L_0x0464:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r13 = r0.zzB(r10)
            r2.zzv(r12, r11, r13)
            goto L_0x050a
        L_0x0479:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            java.lang.Object r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzf(r1, r13)
            zzV(r12, r11, r2)
            goto L_0x050a
        L_0x048a:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            boolean r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzw(r1, r13)
            r2.zzb(r12, r11)
            goto L_0x050a
        L_0x049b:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r1, r13)
            r2.zzk(r12, r11)
            goto L_0x050a
        L_0x04ab:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            long r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r1, r13)
            r2.zzm(r12, r13)
            goto L_0x050a
        L_0x04bb:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            int r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzc(r1, r13)
            r2.zzr(r12, r11)
            goto L_0x050a
        L_0x04cb:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            long r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r1, r13)
            r2.zzK(r12, r13)
            goto L_0x050a
        L_0x04db:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            long r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzd(r1, r13)
            r2.zzt(r12, r13)
            goto L_0x050a
        L_0x04eb:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            float r11 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzb(r1, r13)
            r2.zzo(r12, r11)
            goto L_0x050a
        L_0x04fb:
            boolean r13 = r0.zzP(r1, r10)
            if (r13 == 0) goto L_0x050a
            r11 = r11 & r7
            long r13 = (long) r11
            double r13 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zza(r1, r13)
            r2.zzf(r12, r13)
        L_0x050a:
            int r10 = r10 + 3
            goto L_0x0036
        L_0x050e:
            if (r8 == 0) goto L_0x0525
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r5 = r0.zzn
            r5.zzf(r2, r8)
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0523
            java.lang.Object r5 = r3.next()
            r8 = r5
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x050e
        L_0x0523:
            r8 = 0
            goto L_0x050e
        L_0x0525:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgy r3 = r0.zzm
            java.lang.Object r1 = r3.zzd(r1)
            r3.zzj(r1, r2)
            return
        L_0x052f:
            boolean r3 = r0.zzh
            if (r3 == 0) goto L_0x054c
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r3 = r0.zzn
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdt r3 = r3.zzb(r1)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgu r8 = r3.zza
            boolean r8 = r8.isEmpty()
            if (r8 != 0) goto L_0x054c
            java.util.Iterator r3 = r3.zzf()
            java.lang.Object r8 = r3.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x054e
        L_0x054c:
            r3 = 0
            r8 = 0
        L_0x054e:
            int[] r9 = r0.zzc
            int r9 = r9.length
            sun.misc.Unsafe r10 = zzb
            r11 = r6
            r13 = r11
            r12 = r7
        L_0x0556:
            if (r11 >= r9) goto L_0x09e7
            int r14 = r0.zzy(r11)
            int[] r15 = r0.zzc
            r4 = r15[r11]
            int r6 = zzx(r14)
            r5 = 17
            if (r6 > r5) goto L_0x057c
            int r5 = r11 + 2
            r5 = r15[r5]
            r15 = r5 & r7
            if (r15 == r12) goto L_0x0576
            long r12 = (long) r15
            int r13 = r10.getInt(r1, r12)
            r12 = r15
        L_0x0576:
            int r5 = r5 >>> 20
            r15 = 1
            int r5 = r15 << r5
            goto L_0x057d
        L_0x057c:
            r5 = 0
        L_0x057d:
            if (r8 == 0) goto L_0x059b
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r15 = r0.zzn
            int r15 = r15.zza(r8)
            if (r15 > r4) goto L_0x059b
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r15 = r0.zzn
            r15.zzf(r2, r8)
            boolean r8 = r3.hasNext()
            if (r8 == 0) goto L_0x0599
            java.lang.Object r8 = r3.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x057d
        L_0x0599:
            r8 = 0
            goto L_0x057d
        L_0x059b:
            r14 = r14 & r7
            long r14 = (long) r14
            switch(r6) {
                case 0: goto L_0x09d3;
                case 1: goto L_0x09c5;
                case 2: goto L_0x09b7;
                case 3: goto L_0x09a9;
                case 4: goto L_0x099b;
                case 5: goto L_0x098d;
                case 6: goto L_0x097f;
                case 7: goto L_0x0971;
                case 8: goto L_0x0962;
                case 9: goto L_0x094f;
                case 10: goto L_0x093e;
                case 11: goto L_0x092f;
                case 12: goto L_0x0920;
                case 13: goto L_0x0911;
                case 14: goto L_0x0902;
                case 15: goto L_0x08f3;
                case 16: goto L_0x08e4;
                case 17: goto L_0x08d1;
                case 18: goto L_0x08be;
                case 19: goto L_0x08ae;
                case 20: goto L_0x089e;
                case 21: goto L_0x088e;
                case 22: goto L_0x087e;
                case 23: goto L_0x086e;
                case 24: goto L_0x085e;
                case 25: goto L_0x084b;
                case 26: goto L_0x083b;
                case 27: goto L_0x0827;
                case 28: goto L_0x0817;
                case 29: goto L_0x0806;
                case 30: goto L_0x07f5;
                case 31: goto L_0x07e4;
                case 32: goto L_0x07d3;
                case 33: goto L_0x07c2;
                case 34: goto L_0x07b1;
                case 35: goto L_0x07a1;
                case 36: goto L_0x0791;
                case 37: goto L_0x0781;
                case 38: goto L_0x0771;
                case 39: goto L_0x0761;
                case 40: goto L_0x0751;
                case 41: goto L_0x0741;
                case 42: goto L_0x0731;
                case 43: goto L_0x0721;
                case 44: goto L_0x0711;
                case 45: goto L_0x0701;
                case 46: goto L_0x06f1;
                case 47: goto L_0x06e1;
                case 48: goto L_0x06d1;
                case 49: goto L_0x06be;
                case 50: goto L_0x06b5;
                case 51: goto L_0x06a6;
                case 52: goto L_0x0697;
                case 53: goto L_0x0688;
                case 54: goto L_0x0679;
                case 55: goto L_0x066a;
                case 56: goto L_0x065b;
                case 57: goto L_0x064c;
                case 58: goto L_0x063d;
                case 59: goto L_0x062e;
                case 60: goto L_0x061b;
                case 61: goto L_0x060b;
                case 62: goto L_0x05fd;
                case 63: goto L_0x05ef;
                case 64: goto L_0x05e1;
                case 65: goto L_0x05d3;
                case 66: goto L_0x05c5;
                case 67: goto L_0x05b7;
                case 68: goto L_0x05a5;
                default: goto L_0x05a0;
            }
        L_0x05a0:
            r6 = 1
        L_0x05a1:
            r16 = 0
            goto L_0x09e0
        L_0x05a5:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            java.lang.Object r5 = r10.getObject(r1, r14)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r6 = r0.zzB(r11)
            r2.zzq(r4, r5, r6)
            goto L_0x05a0
        L_0x05b7:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            long r5 = zzz(r1, r14)
            r2.zzD(r4, r5)
            goto L_0x05a0
        L_0x05c5:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            int r5 = zzp(r1, r14)
            r2.zzB(r4, r5)
            goto L_0x05a0
        L_0x05d3:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            long r5 = zzz(r1, r14)
            r2.zzz(r4, r5)
            goto L_0x05a0
        L_0x05e1:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            int r5 = zzp(r1, r14)
            r2.zzx(r4, r5)
            goto L_0x05a0
        L_0x05ef:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            int r5 = zzp(r1, r14)
            r2.zzi(r4, r5)
            goto L_0x05a0
        L_0x05fd:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            int r5 = zzp(r1, r14)
            r2.zzI(r4, r5)
            goto L_0x05a0
        L_0x060b:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            java.lang.Object r5 = r10.getObject(r1, r14)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r5
            r2.zzd(r4, r5)
            goto L_0x05a0
        L_0x061b:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            java.lang.Object r5 = r10.getObject(r1, r14)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r6 = r0.zzB(r11)
            r2.zzv(r4, r5, r6)
            goto L_0x05a0
        L_0x062e:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            java.lang.Object r5 = r10.getObject(r1, r14)
            zzV(r4, r5, r2)
            goto L_0x05a0
        L_0x063d:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            boolean r5 = zzU(r1, r14)
            r2.zzb(r4, r5)
            goto L_0x05a0
        L_0x064c:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            int r5 = zzp(r1, r14)
            r2.zzk(r4, r5)
            goto L_0x05a0
        L_0x065b:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            long r5 = zzz(r1, r14)
            r2.zzm(r4, r5)
            goto L_0x05a0
        L_0x066a:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            int r5 = zzp(r1, r14)
            r2.zzr(r4, r5)
            goto L_0x05a0
        L_0x0679:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            long r5 = zzz(r1, r14)
            r2.zzK(r4, r5)
            goto L_0x05a0
        L_0x0688:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            long r5 = zzz(r1, r14)
            r2.zzt(r4, r5)
            goto L_0x05a0
        L_0x0697:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            float r5 = zzn(r1, r14)
            r2.zzo(r4, r5)
            goto L_0x05a0
        L_0x06a6:
            boolean r5 = r0.zzT(r1, r4, r11)
            if (r5 == 0) goto L_0x05a0
            double r5 = zzm(r1, r14)
            r2.zzf(r4, r5)
            goto L_0x05a0
        L_0x06b5:
            java.lang.Object r5 = r10.getObject(r1, r14)
            r0.zzN(r2, r4, r5, r11)
            goto L_0x05a0
        L_0x06be:
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r6 = r0.zzB(r11)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzL(r4, r5, r2, r6)
            goto L_0x05a0
        L_0x06d1:
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            r6 = 1
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzS(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x06e1:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzR(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x06f1:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzQ(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x0701:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzP(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x0711:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzH(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x0721:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzU(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x0731:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzE(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x0741:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzI(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x0751:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzJ(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x0761:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzM(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x0771:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzV(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x0781:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzN(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x0791:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzK(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x07a1:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzG(r4, r5, r2, r6)
            goto L_0x05a1
        L_0x07b1:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            r14 = 0
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzS(r4, r5, r2, r14)
            goto L_0x085a
        L_0x07c2:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzR(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x07d3:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzQ(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x07e4:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzP(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x07f5:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzH(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x0806:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzU(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x0817:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzF(r4, r5, r2)
            goto L_0x05a1
        L_0x0827:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r14 = r0.zzB(r11)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzO(r4, r5, r2, r14)
            goto L_0x05a1
        L_0x083b:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzT(r4, r5, r2)
            goto L_0x05a1
        L_0x084b:
            r6 = 1
            int[] r4 = r0.zzc
            r4 = r4[r11]
            java.lang.Object r5 = r10.getObject(r1, r14)
            java.util.List r5 = (java.util.List) r5
            r14 = 0
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzE(r4, r5, r2, r14)
        L_0x085a:
            r16 = r14
            goto L_0x09e0
        L_0x085e:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzI(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x086e:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzJ(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x087e:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzM(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x088e:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzV(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x089e:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzN(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x08ae:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzK(r5, r14, r2, r4)
            goto L_0x08cd
        L_0x08be:
            r4 = 0
            r6 = 1
            int[] r5 = r0.zzc
            r5 = r5[r11]
            java.lang.Object r14 = r10.getObject(r1, r14)
            java.util.List r14 = (java.util.List) r14
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgj.zzG(r5, r14, r2, r4)
        L_0x08cd:
            r16 = r4
            goto L_0x09e0
        L_0x08d1:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            java.lang.Object r5 = r10.getObject(r1, r14)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r14 = r0.zzB(r11)
            r2.zzq(r4, r5, r14)
            goto L_0x09e0
        L_0x08e4:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            long r14 = r10.getLong(r1, r14)
            r2.zzD(r4, r14)
            goto L_0x09e0
        L_0x08f3:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            int r5 = r10.getInt(r1, r14)
            r2.zzB(r4, r5)
            goto L_0x09e0
        L_0x0902:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            long r14 = r10.getLong(r1, r14)
            r2.zzz(r4, r14)
            goto L_0x09e0
        L_0x0911:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            int r5 = r10.getInt(r1, r14)
            r2.zzx(r4, r5)
            goto L_0x09e0
        L_0x0920:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            int r5 = r10.getInt(r1, r14)
            r2.zzi(r4, r5)
            goto L_0x09e0
        L_0x092f:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            int r5 = r10.getInt(r1, r14)
            r2.zzI(r4, r5)
            goto L_0x09e0
        L_0x093e:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            java.lang.Object r5 = r10.getObject(r1, r14)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb) r5
            r2.zzd(r4, r5)
            goto L_0x09e0
        L_0x094f:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            java.lang.Object r5 = r10.getObject(r1, r14)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgh r14 = r0.zzB(r11)
            r2.zzv(r4, r5, r14)
            goto L_0x09e0
        L_0x0962:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            java.lang.Object r5 = r10.getObject(r1, r14)
            zzV(r4, r5, r2)
            goto L_0x09e0
        L_0x0971:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            boolean r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzw(r1, r14)
            r2.zzb(r4, r5)
            goto L_0x09e0
        L_0x097f:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            int r5 = r10.getInt(r1, r14)
            r2.zzk(r4, r5)
            goto L_0x09e0
        L_0x098d:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            long r14 = r10.getLong(r1, r14)
            r2.zzm(r4, r14)
            goto L_0x09e0
        L_0x099b:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            int r5 = r10.getInt(r1, r14)
            r2.zzr(r4, r5)
            goto L_0x09e0
        L_0x09a9:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            long r14 = r10.getLong(r1, r14)
            r2.zzK(r4, r14)
            goto L_0x09e0
        L_0x09b7:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            long r14 = r10.getLong(r1, r14)
            r2.zzt(r4, r14)
            goto L_0x09e0
        L_0x09c5:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            float r5 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zzb(r1, r14)
            r2.zzo(r4, r5)
            goto L_0x09e0
        L_0x09d3:
            r6 = 1
            r16 = 0
            r5 = r5 & r13
            if (r5 == 0) goto L_0x09e0
            double r14 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhi.zza(r1, r14)
            r2.zzf(r4, r14)
        L_0x09e0:
            int r11 = r11 + 3
            r5 = r6
            r6 = r16
            goto L_0x0556
        L_0x09e7:
            if (r8 == 0) goto L_0x09fe
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdp r4 = r0.zzn
            r4.zzf(r2, r8)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x09fc
            java.lang.Object r4 = r3.next()
            r8 = r4
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x09e7
        L_0x09fc:
            r8 = 0
            goto L_0x09e7
        L_0x09fe:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgy r3 = r0.zzm
            java.lang.Object r1 = r3.zzd(r1)
            r3.zzj(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfr.zzi(java.lang.Object, com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhq):void");
    }

    public final boolean zzj(Object obj, Object obj2) {
        boolean z;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzy = zzy(i);
            long j = (long) (zzy & 1048575);
            switch (zzx(zzy)) {
                case 0:
                    if (zzO(obj, obj2, i) && Double.doubleToLongBits(zzhi.zza(obj, j)) == Double.doubleToLongBits(zzhi.zza(obj2, j))) {
                        continue;
                    }
                case 1:
                    if (zzO(obj, obj2, i) && Float.floatToIntBits(zzhi.zzb(obj, j)) == Float.floatToIntBits(zzhi.zzb(obj2, j))) {
                        continue;
                    }
                case 2:
                    if (zzO(obj, obj2, i) && zzhi.zzd(obj, j) == zzhi.zzd(obj2, j)) {
                        continue;
                    }
                case 3:
                    if (zzO(obj, obj2, i) && zzhi.zzd(obj, j) == zzhi.zzd(obj2, j)) {
                        continue;
                    }
                case 4:
                    if (zzO(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                case 5:
                    if (zzO(obj, obj2, i) && zzhi.zzd(obj, j) == zzhi.zzd(obj2, j)) {
                        continue;
                    }
                case 6:
                    if (zzO(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                case 7:
                    if (zzO(obj, obj2, i) && zzhi.zzw(obj, j) == zzhi.zzw(obj2, j)) {
                        continue;
                    }
                case 8:
                    if (zzO(obj, obj2, i) && zzgj.zzW(zzhi.zzf(obj, j), zzhi.zzf(obj2, j))) {
                        continue;
                    }
                case 9:
                    if (zzO(obj, obj2, i) && zzgj.zzW(zzhi.zzf(obj, j), zzhi.zzf(obj2, j))) {
                        continue;
                    }
                case 10:
                    if (zzO(obj, obj2, i) && zzgj.zzW(zzhi.zzf(obj, j), zzhi.zzf(obj2, j))) {
                        continue;
                    }
                case 11:
                    if (zzO(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                case 12:
                    if (zzO(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                case 13:
                    if (zzO(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                case 14:
                    if (zzO(obj, obj2, i) && zzhi.zzd(obj, j) == zzhi.zzd(obj2, j)) {
                        continue;
                    }
                case 15:
                    if (zzO(obj, obj2, i) && zzhi.zzc(obj, j) == zzhi.zzc(obj2, j)) {
                        continue;
                    }
                case 16:
                    if (zzO(obj, obj2, i) && zzhi.zzd(obj, j) == zzhi.zzd(obj2, j)) {
                        continue;
                    }
                case 17:
                    if (zzO(obj, obj2, i) && zzgj.zzW(zzhi.zzf(obj, j), zzhi.zzf(obj2, j))) {
                        continue;
                    }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
                    z = zzgj.zzW(zzhi.zzf(obj, j), zzhi.zzf(obj2, j));
                    break;
                case 50:
                    z = zzgj.zzW(zzhi.zzf(obj, j), zzhi.zzf(obj2, j));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case Elf64.Ehdr.E_SHENTSIZE:
                case 59:
                case 60:
                case LockFreeTaskQueueCore.CLOSED_SHIFT:
                case Elf64.Ehdr.E_SHSTRNDX:
                case HtmlCompat.FROM_HTML_MODE_COMPACT:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                case 67:
                case 68:
                    long zzv = (long) (zzv(i) & 1048575);
                    if (zzhi.zzc(obj, zzv) == zzhi.zzc(obj2, zzv) && zzgj.zzW(zzhi.zzf(obj, j), zzhi.zzf(obj2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzm.zzd(obj).equals(this.zzm.zzd(obj2))) {
            return false;
        }
        if (this.zzh) {
            return this.zzn.zzb(obj).equals(this.zzn.zzb(obj2));
        }
        return true;
    }

    public final boolean zzk(Object obj) {
        int i;
        int i2;
        Object obj2 = obj;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i4 < this.zzj) {
            int i6 = this.zzi[i4];
            int i7 = this.zzc[i6];
            int zzy = zzy(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i5) {
                if (i9 != 1048575) {
                    i3 = zzb.getInt(obj2, (long) i9);
                }
                i = i3;
                i2 = i9;
            } else {
                i2 = i5;
                i = i3;
            }
            if ((268435456 & zzy) != 0 && !zzQ(obj, i6, i2, i, i10)) {
                return false;
            }
            int zzx = zzx(zzy);
            if (zzx != 9 && zzx != 17) {
                if (zzx != 27) {
                    if (zzx == 60 || zzx == 68) {
                        if (zzT(obj2, i7, i6) && !zzR(obj2, zzy, zzB(i6))) {
                            return false;
                        }
                    } else if (zzx != 49) {
                        if (zzx == 50 && !((zzfi) zzhi.zzf(obj2, (long) (zzy & 1048575))).isEmpty()) {
                            zzfh zzfh = (zzfh) zzC(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzhi.zzf(obj2, (long) (zzy & 1048575));
                if (!list.isEmpty()) {
                    zzgh zzB = zzB(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzB.zzk(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (zzQ(obj, i6, i2, i, i10) && !zzR(obj2, zzy, zzB(i6))) {
                return false;
            }
            i4++;
            i5 = i2;
            i3 = i;
        }
        return !this.zzh || this.zzn.zzb(obj2).zzk();
    }
}
