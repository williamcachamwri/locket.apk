package com.google.android.gms.internal.auth;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import com.facebook.soloader.Elf64;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzga<T> implements zzgi<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzhj.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzfx zzg;
    private final int[] zzh;
    private final int zzi;
    private final int zzj;
    private final zzfl zzk;
    private final zzgz zzl;
    private final zzem zzm;
    private final zzgc zzn;
    private final zzfs zzo;

    private zzga(int[] iArr, Object[] objArr, int i, int i2, zzfx zzfx, int i3, boolean z, int[] iArr2, int i4, int i5, zzgc zzgc, zzfl zzfl, zzgz zzgz, zzem zzem, zzfs zzfs) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzh = iArr2;
        this.zzi = i4;
        this.zzj = i5;
        this.zzn = zzgc;
        this.zzk = zzfl;
        this.zzl = zzgz;
        this.zzm = zzem;
        this.zzg = zzfx;
        this.zzo = zzfs;
    }

    private final void zzA(Object obj, int i, int i2) {
        zzhj.zzn(obj, (long) (zzl(i2) & 1048575), i);
    }

    private final void zzB(Object obj, int i, Object obj2) {
        zzb.putObject(obj, (long) (zzo(i) & 1048575), obj2);
        zzz(obj, i);
    }

    private final void zzC(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, (long) (zzo(i2) & 1048575), obj2);
        zzA(obj, i, i2);
    }

    private final boolean zzD(Object obj, Object obj2, int i) {
        return zzE(obj, i) == zzE(obj2, i);
    }

    private final boolean zzE(Object obj, int i) {
        int zzl2 = zzl(i);
        long j = (long) (zzl2 & 1048575);
        if (j == 1048575) {
            int zzo2 = zzo(i);
            long j2 = (long) (zzo2 & 1048575);
            switch (zzn(zzo2)) {
                case 0:
                    return Double.doubleToRawLongBits(zzhj.zza(obj, j2)) != 0;
                case 1:
                    return Float.floatToRawIntBits(zzhj.zzb(obj, j2)) != 0;
                case 2:
                    return zzhj.zzd(obj, j2) != 0;
                case 3:
                    return zzhj.zzd(obj, j2) != 0;
                case 4:
                    return zzhj.zzc(obj, j2) != 0;
                case 5:
                    return zzhj.zzd(obj, j2) != 0;
                case 6:
                    return zzhj.zzc(obj, j2) != 0;
                case 7:
                    return zzhj.zzt(obj, j2);
                case 8:
                    Object zzf2 = zzhj.zzf(obj, j2);
                    if (zzf2 instanceof String) {
                        return !((String) zzf2).isEmpty();
                    }
                    if (zzf2 instanceof zzef) {
                        return !zzef.zzb.equals(zzf2);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzhj.zzf(obj, j2) != null;
                case 10:
                    return !zzef.zzb.equals(zzhj.zzf(obj, j2));
                case 11:
                    return zzhj.zzc(obj, j2) != 0;
                case 12:
                    return zzhj.zzc(obj, j2) != 0;
                case 13:
                    return zzhj.zzc(obj, j2) != 0;
                case 14:
                    return zzhj.zzd(obj, j2) != 0;
                case 15:
                    return zzhj.zzc(obj, j2) != 0;
                case 16:
                    return zzhj.zzd(obj, j2) != 0;
                case 17:
                    return zzhj.zzf(obj, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzhj.zzc(obj, j) & (1 << (zzl2 >>> 20))) != 0;
        }
    }

    private final boolean zzF(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzE(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzG(Object obj, int i, zzgi zzgi) {
        return zzgi.zzi(zzhj.zzf(obj, (long) (i & 1048575)));
    }

    private static boolean zzH(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzev) {
            return ((zzev) obj).zzm();
        }
        return true;
    }

    private final boolean zzI(Object obj, int i, int i2) {
        return zzhj.zzc(obj, (long) (zzl(i2) & 1048575)) == i;
    }

    static zzha zzc(Object obj) {
        zzev zzev = (zzev) obj;
        zzha zzha = zzev.zzc;
        if (zzha != zzha.zza()) {
            return zzha;
        }
        zzha zzd2 = zzha.zzd();
        zzev.zzc = zzd2;
        return zzd2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x024f  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0252  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x026a  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x026d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.auth.zzga zzj(java.lang.Class r30, com.google.android.gms.internal.auth.zzfu r31, com.google.android.gms.internal.auth.zzgc r32, com.google.android.gms.internal.auth.zzfl r33, com.google.android.gms.internal.auth.zzgz r34, com.google.android.gms.internal.auth.zzem r35, com.google.android.gms.internal.auth.zzfs r36) {
        /*
            r0 = r31
            boolean r1 = r0 instanceof com.google.android.gms.internal.auth.zzgh
            if (r1 == 0) goto L_0x03e9
            com.google.android.gms.internal.auth.zzgh r0 = (com.google.android.gms.internal.auth.zzgh) r0
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
            com.google.android.gms.internal.auth.zzfx r15 = r0.zza()
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
            if (r4 >= r2) goto L_0x03c4
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
            java.lang.reflect.Field r2 = zzv(r15, r2)
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
            java.lang.reflect.Field r13 = zzv(r15, r13)
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
            java.lang.reflect.Field r13 = zzv(r15, r13)
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
            java.lang.reflect.Field r5 = zzv(r15, r5)
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
            r13 = r3 & 256(0x100, float:3.59E-43)
            if (r13 == 0) goto L_0x039b
            r13 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x039c
        L_0x039b:
            r13 = 0
        L_0x039c:
            r3 = r3 & 2048(0x800, float:2.87E-42)
            if (r3 == 0) goto L_0x03a3
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x03a4
        L_0x03a3:
            r3 = 0
        L_0x03a4:
            int r6 = r6 << 20
            r5 = r5 | r13
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
        L_0x03c4:
            r27 = r13
            r29 = r14
            com.google.android.gms.internal.auth.zzga r1 = new com.google.android.gms.internal.auth.zzga
            com.google.android.gms.internal.auth.zzfx r14 = r0.zza()
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
        L_0x03e9:
            com.google.android.gms.internal.auth.zzgw r0 = (com.google.android.gms.internal.auth.zzgw) r0
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzga.zzj(java.lang.Class, com.google.android.gms.internal.auth.zzfu, com.google.android.gms.internal.auth.zzgc, com.google.android.gms.internal.auth.zzfl, com.google.android.gms.internal.auth.zzgz, com.google.android.gms.internal.auth.zzem, com.google.android.gms.internal.auth.zzfs):com.google.android.gms.internal.auth.zzga");
    }

    private static int zzk(Object obj, long j) {
        return ((Integer) zzhj.zzf(obj, j)).intValue();
    }

    private final int zzl(int i) {
        return this.zzc[i + 2];
    }

    private final int zzm(int i, int i2) {
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

    private static int zzn(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzo(int i) {
        return this.zzc[i + 1];
    }

    private static long zzp(Object obj, long j) {
        return ((Long) zzhj.zzf(obj, j)).longValue();
    }

    private final zzey zzq(int i) {
        int i2 = i / 3;
        return (zzey) this.zzd[i2 + i2 + 1];
    }

    private final zzgi zzr(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzgi zzgi = (zzgi) this.zzd[i3];
        if (zzgi != null) {
            return zzgi;
        }
        zzgi zzb2 = zzgf.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzs(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzt(Object obj, int i) {
        zzgi zzr = zzr(i);
        int zzo2 = zzo(i) & 1048575;
        if (!zzE(obj, i)) {
            return zzr.zzd();
        }
        Object object = zzb.getObject(obj, (long) zzo2);
        if (zzH(object)) {
            return object;
        }
        Object zzd2 = zzr.zzd();
        if (object != null) {
            zzr.zzf(zzd2, object);
        }
        return zzd2;
    }

    private final Object zzu(Object obj, int i, int i2) {
        zzgi zzr = zzr(i2);
        if (!zzI(obj, i, i2)) {
            return zzr.zzd();
        }
        Object object = zzb.getObject(obj, (long) (zzo(i2) & 1048575));
        if (zzH(object)) {
            return object;
        }
        Object zzd2 = zzr.zzd();
        if (object != null) {
            zzr.zzf(zzd2, object);
        }
        return zzd2;
    }

    private static Field zzv(Class cls, String str) {
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

    private static void zzw(Object obj) {
        if (!zzH(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzx(Object obj, Object obj2, int i) {
        if (zzE(obj2, i)) {
            Unsafe unsafe = zzb;
            long zzo2 = (long) (zzo(i) & 1048575);
            Object object = unsafe.getObject(obj2, zzo2);
            if (object != null) {
                zzgi zzr = zzr(i);
                if (!zzE(obj, i)) {
                    if (!zzH(object)) {
                        unsafe.putObject(obj, zzo2, object);
                    } else {
                        Object zzd2 = zzr.zzd();
                        zzr.zzf(zzd2, object);
                        unsafe.putObject(obj, zzo2, zzd2);
                    }
                    zzz(obj, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzo2);
                if (!zzH(object2)) {
                    Object zzd3 = zzr.zzd();
                    zzr.zzf(zzd3, object2);
                    unsafe.putObject(obj, zzo2, zzd3);
                    object2 = zzd3;
                }
                zzr.zzf(object2, object);
                return;
            }
            int i2 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i2 + " is present but null: " + obj3);
        }
    }

    private final void zzy(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzI(obj2, i2, i)) {
            Unsafe unsafe = zzb;
            long zzo2 = (long) (zzo(i) & 1048575);
            Object object = unsafe.getObject(obj2, zzo2);
            if (object != null) {
                zzgi zzr = zzr(i);
                if (!zzI(obj, i2, i)) {
                    if (!zzH(object)) {
                        unsafe.putObject(obj, zzo2, object);
                    } else {
                        Object zzd2 = zzr.zzd();
                        zzr.zzf(zzd2, object);
                        unsafe.putObject(obj, zzo2, zzd2);
                    }
                    zzA(obj, i2, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzo2);
                if (!zzH(object2)) {
                    Object zzd3 = zzr.zzd();
                    zzr.zzf(zzd3, object2);
                    unsafe.putObject(obj, zzo2, zzd3);
                    object2 = zzd3;
                }
                zzr.zzf(object2, object);
                return;
            }
            int i3 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i3 + " is present but null: " + obj3);
        }
    }

    private final void zzz(Object obj, int i) {
        int zzl2 = zzl(i);
        long j = (long) (1048575 & zzl2);
        if (j != 1048575) {
            zzhj.zzn(obj, j, (1 << (zzl2 >>> 20)) | zzhj.zzc(obj, j));
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c6, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0222, code lost:
        r3 = (int) (r3 ^ (r3 >>> 32));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0226, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0227, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(java.lang.Object r10) {
        /*
            r9 = this;
            int[] r0 = r9.zzc
            int r0 = r0.length
            r1 = 0
            r2 = r1
        L_0x0005:
            if (r1 >= r0) goto L_0x022b
            int r3 = r9.zzo(r1)
            int[] r4 = r9.zzc
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            int r3 = zzn(r3)
            long r5 = (long) r5
            r7 = 37
            r8 = 32
            switch(r3) {
                case 0: goto L_0x0216;
                case 1: goto L_0x020b;
                case 2: goto L_0x0202;
                case 3: goto L_0x01f9;
                case 4: goto L_0x01f2;
                case 5: goto L_0x01e9;
                case 6: goto L_0x01e2;
                case 7: goto L_0x01d7;
                case 8: goto L_0x01ca;
                case 9: goto L_0x01bc;
                case 10: goto L_0x01b0;
                case 11: goto L_0x01a8;
                case 12: goto L_0x01a0;
                case 13: goto L_0x0198;
                case 14: goto L_0x018e;
                case 15: goto L_0x0186;
                case 16: goto L_0x017c;
                case 17: goto L_0x0171;
                case 18: goto L_0x0165;
                case 19: goto L_0x0165;
                case 20: goto L_0x0165;
                case 21: goto L_0x0165;
                case 22: goto L_0x0165;
                case 23: goto L_0x0165;
                case 24: goto L_0x0165;
                case 25: goto L_0x0165;
                case 26: goto L_0x0165;
                case 27: goto L_0x0165;
                case 28: goto L_0x0165;
                case 29: goto L_0x0165;
                case 30: goto L_0x0165;
                case 31: goto L_0x0165;
                case 32: goto L_0x0165;
                case 33: goto L_0x0165;
                case 34: goto L_0x0165;
                case 35: goto L_0x0165;
                case 36: goto L_0x0165;
                case 37: goto L_0x0165;
                case 38: goto L_0x0165;
                case 39: goto L_0x0165;
                case 40: goto L_0x0165;
                case 41: goto L_0x0165;
                case 42: goto L_0x0165;
                case 43: goto L_0x0165;
                case 44: goto L_0x0165;
                case 45: goto L_0x0165;
                case 46: goto L_0x0165;
                case 47: goto L_0x0165;
                case 48: goto L_0x0165;
                case 49: goto L_0x0165;
                case 50: goto L_0x0159;
                case 51: goto L_0x013f;
                case 52: goto L_0x0127;
                case 53: goto L_0x0117;
                case 54: goto L_0x0107;
                case 55: goto L_0x00f9;
                case 56: goto L_0x00e9;
                case 57: goto L_0x00db;
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
            goto L_0x0227
        L_0x0021:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x0033:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzp(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x0043:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0226
        L_0x0051:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzp(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x0061:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0226
        L_0x006f:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0226
        L_0x007d:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0226
        L_0x008b:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x009d:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x00af:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x00c3:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            int r3 = com.google.android.gms.internal.auth.zzfa.zza(r3)
            goto L_0x0226
        L_0x00db:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0226
        L_0x00e9:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzp(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x00f9:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzk(r10, r5)
            goto L_0x0226
        L_0x0107:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzp(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x0117:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzp(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x0127:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0226
        L_0x013f:
            boolean r3 = r9.zzI(r10, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            java.lang.Double r3 = (java.lang.Double) r3
            double r3 = r3.doubleValue()
            long r3 = java.lang.Double.doubleToLongBits(r3)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x0159:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x0165:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x0171:
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            if (r3 == 0) goto L_0x01c6
            int r7 = r3.hashCode()
            goto L_0x01c6
        L_0x017c:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.auth.zzhj.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x0186:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0226
        L_0x018e:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.auth.zzhj.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x0198:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0226
        L_0x01a0:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0226
        L_0x01a8:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0226
        L_0x01b0:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x01bc:
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            if (r3 == 0) goto L_0x01c6
            int r7 = r3.hashCode()
        L_0x01c6:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0227
        L_0x01ca:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.auth.zzhj.zzf(r10, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x01d7:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.auth.zzhj.zzt(r10, r5)
            int r3 = com.google.android.gms.internal.auth.zzfa.zza(r3)
            goto L_0x0226
        L_0x01e2:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0226
        L_0x01e9:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.auth.zzhj.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x01f2:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.auth.zzhj.zzc(r10, r5)
            goto L_0x0226
        L_0x01f9:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.auth.zzhj.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x0202:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.auth.zzhj.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
            goto L_0x0222
        L_0x020b:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.auth.zzhj.zzb(r10, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0226
        L_0x0216:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.auth.zzhj.zza(r10, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            byte[] r5 = com.google.android.gms.internal.auth.zzfa.zzd
        L_0x0222:
            long r5 = r3 >>> r8
            long r3 = r3 ^ r5
            int r3 = (int) r3
        L_0x0226:
            int r2 = r2 + r3
        L_0x0227:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022b:
            int r2 = r2 * 53
            com.google.android.gms.internal.auth.zzgz r0 = r9.zzl
            java.lang.Object r10 = r0.zzb(r10)
            int r10 = r10.hashCode()
            int r2 = r2 + r10
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzga.zza(java.lang.Object):int");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x061b  */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x06c5  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0715  */
    /* JADX WARNING: Removed duplicated region for block: B:613:0x0649 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:616:0x0863 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:620:0x0863 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01fa  */
    final int zzb(java.lang.Object r40, byte[] r41, int r42, int r43, int r44, com.google.android.gms.internal.auth.zzdt r45) throws java.io.IOException {
        /*
            r39 = this;
            r0 = r39
            r7 = r40
            r15 = r41
            r14 = r43
            r13 = r44
            r12 = r45
            zzw(r40)
            sun.misc.Unsafe r11 = zzb
            r10 = 0
            r1 = r42
            r3 = r10
            r4 = r3
            r5 = r4
            r2 = -1
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001b:
            r16 = 0
            if (r1 >= r14) goto L_0x0ce2
            int r4 = r1 + 1
            byte r1 = r15[r1]
            if (r1 >= 0) goto L_0x002c
            int r1 = com.google.android.gms.internal.auth.zzdu.zzi(r1, r15, r4, r12)
            int r4 = r12.zza
            goto L_0x0031
        L_0x002c:
            r37 = r4
            r4 = r1
            r1 = r37
        L_0x0031:
            int r8 = r4 >>> 3
            r9 = 3
            if (r8 <= r2) goto L_0x0046
            int r3 = r3 / r9
            int r2 = r0.zze
            if (r8 < r2) goto L_0x0044
            int r2 = r0.zzf
            if (r8 > r2) goto L_0x0044
            int r2 = r0.zzm(r8, r3)
            goto L_0x0052
        L_0x0044:
            r2 = -1
            goto L_0x0052
        L_0x0046:
            int r2 = r0.zze
            if (r8 < r2) goto L_0x0055
            int r2 = r0.zzf
            if (r8 > r2) goto L_0x0055
            int r2 = r0.zzm(r8, r10)
        L_0x0052:
            r3 = r2
            r2 = -1
            goto L_0x0057
        L_0x0055:
            r2 = -1
            r3 = -1
        L_0x0057:
            if (r3 != r2) goto L_0x006a
            r3 = r1
            r18 = r2
            r25 = r5
            r28 = r6
            r1 = r8
            r19 = r10
            r26 = r11
            r5 = r12
            r8 = r13
            r6 = r14
            goto L_0x0caf
        L_0x006a:
            r2 = r4 & 7
            int[] r9 = r0.zzc
            int r19 = r3 + 1
            r10 = r9[r19]
            r19 = r4
            int r4 = zzn(r10)
            r17 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r10 & r17
            long r13 = (long) r13
            r21 = r8
            r22 = 536870912(0x20000000, float:1.0842022E-19)
            r23 = 0
            java.lang.String r8 = ""
            r26 = r8
            r8 = 17
            if (r4 > r8) goto L_0x039d
            int r8 = r3 + 2
            r8 = r9[r8]
            int r9 = r8 >>> 20
            r25 = 1
            int r27 = r25 << r9
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r8 & r9
            r17 = r10
            if (r8 == r6) goto L_0x00b5
            if (r6 == r9) goto L_0x00a7
            long r9 = (long) r6
            r11.putInt(r7, r9, r5)
            r9 = 1048575(0xfffff, float:1.469367E-39)
        L_0x00a7:
            if (r8 != r9) goto L_0x00ab
            r5 = 0
            goto L_0x00b0
        L_0x00ab:
            long r5 = (long) r8
            int r5 = r11.getInt(r7, r5)
        L_0x00b0:
            r25 = r5
            r28 = r8
            goto L_0x00b9
        L_0x00b5:
            r25 = r5
            r28 = r6
        L_0x00b9:
            switch(r4) {
                case 0: goto L_0x0362;
                case 1: goto L_0x034d;
                case 2: goto L_0x032f;
                case 3: goto L_0x032f;
                case 4: goto L_0x031f;
                case 5: goto L_0x0300;
                case 6: goto L_0x02ed;
                case 7: goto L_0x02d4;
                case 8: goto L_0x01b9;
                case 9: goto L_0x0188;
                case 10: goto L_0x0178;
                case 11: goto L_0x031f;
                case 12: goto L_0x013f;
                case 13: goto L_0x02ed;
                case 14: goto L_0x0300;
                case 15: goto L_0x012b;
                case 16: goto L_0x0103;
                default: goto L_0x00bc;
            }
        L_0x00bc:
            r10 = r3
            r9 = r19
            r3 = 3
            r8 = 0
            if (r2 != r3) goto L_0x0385
            java.lang.Object r2 = r0.zzt(r7, r10)
            int r3 = r21 << 3
            r13 = r3 | 4
            com.google.android.gms.internal.auth.zzgi r3 = r0.zzr(r10)
            r5 = r8
            r4 = r21
            r6 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r2
            r14 = r9
            r18 = -1
            r9 = r3
            r19 = r5
            r3 = r10
            r10 = r41
            r5 = r11
            r11 = r1
            r1 = r12
            r12 = r43
            r6 = r43
            r20 = r14
            r14 = r45
            int r8 = com.google.android.gms.internal.auth.zzdu.zzl(r8, r9, r10, r11, r12, r13, r14)
            r0.zzB(r7, r3, r2)
            r2 = r25 | r27
            r13 = r44
            r12 = r1
            r11 = r5
            r14 = r6
            r1 = r8
            r10 = r19
            r6 = r28
            r5 = r2
            r2 = r4
            r4 = r20
            goto L_0x001b
        L_0x0103:
            if (r2 != 0) goto L_0x0123
            int r8 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r1, r12)
            long r1 = r12.zzb
            long r5 = com.google.android.gms.internal.auth.zzej.zzc(r1)
            r1 = r11
            r10 = -1
            r2 = r40
            r10 = r3
            r9 = r19
            r3 = r13
            r1.putLong(r2, r3, r5)
            r5 = r25 | r27
            r14 = r43
            r13 = r44
            r1 = r8
            goto L_0x01ac
        L_0x0123:
            r9 = r19
            r6 = r43
            r20 = r9
            goto L_0x02cb
        L_0x012b:
            r10 = r3
            r9 = r19
            if (r2 != 0) goto L_0x01b5
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r2 = r12.zza
            int r2 = com.google.android.gms.internal.auth.zzej.zzb(r2)
            r11.putInt(r7, r13, r2)
            goto L_0x01a6
        L_0x013f:
            r10 = r3
            r9 = r19
            if (r2 != 0) goto L_0x01b5
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r2 = r12.zza
            com.google.android.gms.internal.auth.zzey r3 = r0.zzq(r10)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r17 & r4
            if (r4 == 0) goto L_0x0174
            if (r3 == 0) goto L_0x0174
            boolean r3 = r3.zza()
            if (r3 == 0) goto L_0x015d
            goto L_0x0174
        L_0x015d:
            com.google.android.gms.internal.auth.zzha r3 = zzc(r40)
            long r4 = (long) r2
            java.lang.Long r2 = java.lang.Long.valueOf(r4)
            r3.zzh(r9, r2)
            r14 = r43
            r13 = r44
            r4 = r9
            r3 = r10
            r2 = r21
            r5 = r25
            goto L_0x01b0
        L_0x0174:
            r11.putInt(r7, r13, r2)
            goto L_0x01a6
        L_0x0178:
            r10 = r3
            r9 = r19
            r3 = 2
            if (r2 != r3) goto L_0x01b5
            int r1 = com.google.android.gms.internal.auth.zzdu.zza(r15, r1, r12)
            java.lang.Object r2 = r12.zzc
            r11.putObject(r7, r13, r2)
            goto L_0x01a6
        L_0x0188:
            r10 = r3
            r9 = r19
            r3 = 2
            if (r2 != r3) goto L_0x01b5
            java.lang.Object r8 = r0.zzt(r7, r10)
            com.google.android.gms.internal.auth.zzgi r2 = r0.zzr(r10)
            r5 = r1
            r1 = r8
            r3 = r41
            r4 = r5
            r5 = r43
            r6 = r45
            int r1 = com.google.android.gms.internal.auth.zzdu.zzm(r1, r2, r3, r4, r5, r6)
            r0.zzB(r7, r10, r8)
        L_0x01a6:
            r5 = r25 | r27
            r14 = r43
            r13 = r44
        L_0x01ac:
            r4 = r9
            r3 = r10
            r2 = r21
        L_0x01b0:
            r6 = r28
            r10 = 0
            goto L_0x001b
        L_0x01b5:
            r6 = r43
            goto L_0x02c8
        L_0x01b9:
            r5 = r1
            r10 = r3
            r9 = r19
            r1 = 2
            if (r2 != r1) goto L_0x02c5
            r1 = r17 & r22
            if (r1 == 0) goto L_0x029f
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r5, r12)
            int r2 = r12.zza
            if (r2 < 0) goto L_0x029a
            if (r2 != 0) goto L_0x01d5
            r3 = r26
            r12.zzc = r3
            r8 = 0
            goto L_0x02b9
        L_0x01d5:
            int r3 = com.google.android.gms.internal.auth.zzhn.zza
            int r3 = r15.length
            int r4 = r3 - r1
            r5 = r1 | r2
            int r4 = r4 - r2
            r4 = r4 | r5
            if (r4 < 0) goto L_0x027e
            int r3 = r1 + r2
            char[] r2 = new char[r2]
            r4 = 0
        L_0x01e5:
            if (r1 >= r3) goto L_0x01f8
            byte r5 = r15[r1]
            boolean r6 = com.google.android.gms.internal.auth.zzhk.zzd(r5)
            if (r6 == 0) goto L_0x01f8
            int r1 = r1 + 1
            int r6 = r4 + 1
            char r5 = (char) r5
            r2[r4] = r5
            r4 = r6
            goto L_0x01e5
        L_0x01f8:
            if (r1 >= r3) goto L_0x0274
            int r5 = r1 + 1
            byte r1 = r15[r1]
            boolean r6 = com.google.android.gms.internal.auth.zzhk.zzd(r1)
            if (r6 == 0) goto L_0x021d
            int r6 = r4 + 1
            char r1 = (char) r1
            r2[r4] = r1
            r1 = r5
        L_0x020a:
            r4 = r6
            if (r1 >= r3) goto L_0x01f8
            byte r5 = r15[r1]
            boolean r6 = com.google.android.gms.internal.auth.zzhk.zzd(r5)
            if (r6 == 0) goto L_0x01f8
            int r1 = r1 + 1
            int r6 = r4 + 1
            char r5 = (char) r5
            r2[r4] = r5
            goto L_0x020a
        L_0x021d:
            r6 = -32
            if (r1 >= r6) goto L_0x0234
            if (r5 >= r3) goto L_0x022f
            int r6 = r5 + 1
            byte r5 = r15[r5]
            int r8 = r4 + 1
            com.google.android.gms.internal.auth.zzhk.zzc(r1, r5, r2, r4)
            r1 = r6
            r4 = r8
            goto L_0x01f8
        L_0x022f:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x0234:
            r6 = -16
            if (r1 >= r6) goto L_0x0252
            int r6 = r3 + -1
            if (r5 >= r6) goto L_0x024d
            int r6 = r5 + 1
            byte r5 = r15[r5]
            int r8 = r6 + 1
            byte r6 = r15[r6]
            int r16 = r4 + 1
            com.google.android.gms.internal.auth.zzhk.zzb(r1, r5, r6, r2, r4)
            r1 = r8
            r4 = r16
            goto L_0x01f8
        L_0x024d:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x0252:
            int r6 = r3 + -2
            if (r5 >= r6) goto L_0x026f
            int r6 = r5 + 1
            byte r30 = r15[r5]
            int r5 = r6 + 1
            byte r31 = r15[r6]
            int r6 = r5 + 1
            byte r32 = r15[r5]
            r29 = r1
            r33 = r2
            r34 = r4
            com.google.android.gms.internal.auth.zzhk.zza(r29, r30, r31, r32, r33, r34)
            int r4 = r4 + 2
            r1 = r6
            goto L_0x01f8
        L_0x026f:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x0274:
            java.lang.String r1 = new java.lang.String
            r8 = 0
            r1.<init>(r2, r8, r4)
            r12.zzc = r1
            r1 = r3
            goto L_0x02b9
        L_0x027e:
            java.lang.ArrayIndexOutOfBoundsException r4 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object[] r1 = new java.lang.Object[]{r3, r1, r2}
            java.lang.String r2 = "buffer length=%d, index=%d, size=%d"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r4.<init>(r1)
            throw r4
        L_0x029a:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x029f:
            r3 = r26
            r8 = 0
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r5, r12)
            int r2 = r12.zza
            if (r2 < 0) goto L_0x02c0
            if (r2 != 0) goto L_0x02af
            r12.zzc = r3
            goto L_0x02b9
        L_0x02af:
            java.lang.String r3 = new java.lang.String
            java.nio.charset.Charset r4 = com.google.android.gms.internal.auth.zzfa.zzb
            r3.<init>(r15, r1, r2, r4)
            r12.zzc = r3
            int r1 = r1 + r2
        L_0x02b9:
            java.lang.Object r2 = r12.zzc
            r11.putObject(r7, r13, r2)
            goto L_0x0376
        L_0x02c0:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x02c5:
            r6 = r43
            r1 = r5
        L_0x02c8:
            r20 = r9
            r3 = r10
        L_0x02cb:
            r5 = r11
            r4 = r21
            r18 = -1
            r19 = 0
            goto L_0x0391
        L_0x02d4:
            r5 = r1
            r10 = r3
            r9 = r19
            r8 = 0
            if (r2 != 0) goto L_0x031a
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r5, r12)
            long r2 = r12.zzb
            int r2 = (r2 > r23 ? 1 : (r2 == r23 ? 0 : -1))
            if (r2 == 0) goto L_0x02e7
            r2 = 1
            goto L_0x02e8
        L_0x02e7:
            r2 = r8
        L_0x02e8:
            com.google.android.gms.internal.auth.zzhj.zzk(r7, r13, r2)
            goto L_0x0376
        L_0x02ed:
            r5 = r1
            r10 = r3
            r9 = r19
            r1 = 5
            r8 = 0
            if (r2 != r1) goto L_0x031a
            int r1 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r5)
            r11.putInt(r7, r13, r1)
            int r1 = r5 + 4
            goto L_0x0376
        L_0x0300:
            r5 = r1
            r10 = r3
            r9 = r19
            r1 = 1
            r8 = 0
            if (r2 != r1) goto L_0x031a
            long r16 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r5)
            r1 = r11
            r2 = r40
            r3 = r13
            r13 = r5
            r5 = r16
            r1.putLong(r2, r3, r5)
            int r1 = r13 + 8
            goto L_0x0376
        L_0x031a:
            r6 = r43
            r1 = r5
            goto L_0x0387
        L_0x031f:
            r10 = r3
            r9 = r19
            r8 = 0
            if (r2 != 0) goto L_0x0385
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r2 = r12.zza
            r11.putInt(r7, r13, r2)
            goto L_0x0376
        L_0x032f:
            r10 = r3
            r9 = r19
            r8 = 0
            if (r2 != 0) goto L_0x0385
            int r16 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r1, r12)
            long r5 = r12.zzb
            r1 = r11
            r2 = r40
            r3 = r13
            r1.putLong(r2, r3, r5)
            r5 = r25 | r27
            r14 = r43
            r13 = r44
            r4 = r9
            r3 = r10
            r1 = r16
            goto L_0x037e
        L_0x034d:
            r10 = r3
            r9 = r19
            r3 = 5
            r8 = 0
            if (r2 != r3) goto L_0x0385
            int r2 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r1)
            float r2 = java.lang.Float.intBitsToFloat(r2)
            com.google.android.gms.internal.auth.zzhj.zzm(r7, r13, r2)
            int r1 = r1 + 4
            goto L_0x0376
        L_0x0362:
            r10 = r3
            r9 = r19
            r3 = 1
            r8 = 0
            if (r2 != r3) goto L_0x0385
            long r2 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r1)
            double r2 = java.lang.Double.longBitsToDouble(r2)
            com.google.android.gms.internal.auth.zzhj.zzl(r7, r13, r2)
            int r1 = r1 + 8
        L_0x0376:
            r5 = r25 | r27
            r14 = r43
            r13 = r44
            r4 = r9
            r3 = r10
        L_0x037e:
            r2 = r21
            r6 = r28
            r10 = r8
            goto L_0x001b
        L_0x0385:
            r6 = r43
        L_0x0387:
            r19 = r8
            r20 = r9
            r3 = r10
            r5 = r11
            r4 = r21
            r18 = -1
        L_0x0391:
            r8 = r44
            r10 = r3
            r26 = r5
            r5 = r12
            r3 = r1
            r1 = r4
        L_0x0399:
            r4 = r20
            goto L_0x0caf
        L_0x039d:
            r8 = r3
            r28 = r6
            r17 = r10
            r20 = r19
            r10 = r21
            r3 = r26
            r18 = -1
            r19 = 0
            r6 = r43
            r21 = r5
            r5 = 27
            r25 = 10
            if (r4 != r5) goto L_0x0409
            r5 = 2
            if (r2 != r5) goto L_0x03fe
            java.lang.Object r2 = r11.getObject(r7, r13)
            com.google.android.gms.internal.auth.zzez r2 = (com.google.android.gms.internal.auth.zzez) r2
            boolean r3 = r2.zzc()
            if (r3 != 0) goto L_0x03d7
            int r3 = r2.size()
            if (r3 != 0) goto L_0x03cc
            goto L_0x03ce
        L_0x03cc:
            int r25 = r3 + r3
        L_0x03ce:
            r3 = r25
            com.google.android.gms.internal.auth.zzez r2 = r2.zzd(r3)
            r11.putObject(r7, r13, r2)
        L_0x03d7:
            r13 = r2
            com.google.android.gms.internal.auth.zzgi r2 = r0.zzr(r8)
            r5 = r8
            r8 = r2
            r9 = r20
            r2 = r10
            r10 = r41
            r3 = r11
            r11 = r1
            r4 = r12
            r12 = r43
            r14 = r45
            int r1 = com.google.android.gms.internal.auth.zzdu.zze(r8, r9, r10, r11, r12, r13, r14)
            r13 = r44
            r11 = r3
            r12 = r4
            r3 = r5
            r14 = r6
            r10 = r19
            r4 = r20
            r5 = r21
            r6 = r28
            goto L_0x001b
        L_0x03fe:
            r3 = r10
            r26 = r11
            r5 = r20
            r25 = r21
            r11 = r1
            r1 = r8
            goto L_0x09fe
        L_0x0409:
            r5 = r8
            r8 = 49
            if (r4 > r8) goto L_0x09bf
            r8 = r17
            long r8 = (long) r8
            r17 = r5
            sun.misc.Unsafe r5 = zzb
            java.lang.Object r22 = r5.getObject(r7, r13)
            r26 = r11
            r11 = r22
            com.google.android.gms.internal.auth.zzez r11 = (com.google.android.gms.internal.auth.zzez) r11
            boolean r22 = r11.zzc()
            if (r22 != 0) goto L_0x043b
            int r22 = r11.size()
            if (r22 != 0) goto L_0x042c
            goto L_0x042e
        L_0x042c:
            int r25 = r22 + r22
        L_0x042e:
            r27 = r3
            r3 = r25
            com.google.android.gms.internal.auth.zzez r3 = r11.zzd(r3)
            r5.putObject(r7, r13, r3)
            r13 = r3
            goto L_0x043e
        L_0x043b:
            r27 = r3
            r13 = r11
        L_0x043e:
            switch(r4) {
                case 18: goto L_0x0928;
                case 19: goto L_0x08ce;
                case 20: goto L_0x0881;
                case 21: goto L_0x0881;
                case 22: goto L_0x0853;
                case 23: goto L_0x0808;
                case 24: goto L_0x07bc;
                case 25: goto L_0x0759;
                case 26: goto L_0x0692;
                case 27: goto L_0x0658;
                case 28: goto L_0x05f2;
                case 29: goto L_0x0853;
                case 30: goto L_0x053f;
                case 31: goto L_0x07bc;
                case 32: goto L_0x0808;
                case 33: goto L_0x04db;
                case 34: goto L_0x046d;
                case 35: goto L_0x0928;
                case 36: goto L_0x08ce;
                case 37: goto L_0x0881;
                case 38: goto L_0x0881;
                case 39: goto L_0x0853;
                case 40: goto L_0x0808;
                case 41: goto L_0x07bc;
                case 42: goto L_0x0759;
                case 43: goto L_0x0853;
                case 44: goto L_0x053f;
                case 45: goto L_0x07bc;
                case 46: goto L_0x0808;
                case 47: goto L_0x04db;
                case 48: goto L_0x046d;
                default: goto L_0x0441;
            }
        L_0x0441:
            r11 = r1
            r14 = r6
            r9 = r10
            r10 = r17
            r7 = r20
            r25 = r21
            r8 = r26
            r1 = 3
            if (r2 != r1) goto L_0x099f
            com.google.android.gms.internal.auth.zzgi r17 = r0.zzr(r10)
            r1 = r7 & -8
            r20 = r1 | 4
            r1 = r17
            r2 = r41
            r3 = r11
            r4 = r43
            r5 = r20
            r6 = r45
            int r1 = com.google.android.gms.internal.auth.zzdu.zzc(r1, r2, r3, r4, r5, r6)
            java.lang.Object r2 = r12.zzc
            r13.add(r2)
            goto L_0x0981
        L_0x046d:
            r3 = 2
            if (r2 != r3) goto L_0x049e
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r3 = r12.zza
            int r3 = r3 + r2
        L_0x0479:
            if (r2 >= r3) goto L_0x0489
            int r2 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r2, r12)
            long r4 = r12.zzb
            long r4 = com.google.android.gms.internal.auth.zzej.zzc(r4)
            r13.zze(r4)
            goto L_0x0479
        L_0x0489:
            if (r2 != r3) goto L_0x0499
            r11 = r1
            r1 = r2
            r14 = r6
            r9 = r10
            r10 = r17
            r7 = r20
            r25 = r21
            r8 = r26
            goto L_0x09a0
        L_0x0499:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x049e:
            if (r2 != 0) goto L_0x04ce
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            int r2 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r1, r12)
            long r3 = r12.zzb
            long r3 = com.google.android.gms.internal.auth.zzej.zzc(r3)
            r13.zze(r3)
        L_0x04af:
            if (r2 >= r6) goto L_0x04cb
            int r3 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r12)
            int r4 = r12.zza
            r14 = r20
            if (r14 != r4) goto L_0x052a
            int r2 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r3, r12)
            long r3 = r12.zzb
            long r3 = com.google.android.gms.internal.auth.zzej.zzc(r3)
            r13.zze(r3)
            r20 = r14
            goto L_0x04af
        L_0x04cb:
            r14 = r20
            goto L_0x052a
        L_0x04ce:
            r11 = r1
            r14 = r6
            r9 = r10
            r10 = r17
            r7 = r20
            r25 = r21
            r8 = r26
            goto L_0x099f
        L_0x04db:
            r14 = r20
            r3 = 2
            if (r2 != r3) goto L_0x0501
            com.google.android.gms.internal.auth.zzew r13 = (com.google.android.gms.internal.auth.zzew) r13
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r3 = r12.zza
            int r3 = r3 + r2
        L_0x04e9:
            if (r2 >= r3) goto L_0x04f9
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r12)
            int r4 = r12.zza
            int r4 = com.google.android.gms.internal.auth.zzej.zzb(r4)
            r13.zze(r4)
            goto L_0x04e9
        L_0x04f9:
            if (r2 != r3) goto L_0x04fc
            goto L_0x052a
        L_0x04fc:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x0501:
            if (r2 != 0) goto L_0x0536
            com.google.android.gms.internal.auth.zzew r13 = (com.google.android.gms.internal.auth.zzew) r13
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r3 = r12.zza
            int r3 = com.google.android.gms.internal.auth.zzej.zzb(r3)
            r13.zze(r3)
        L_0x0512:
            if (r2 >= r6) goto L_0x052a
            int r3 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r12)
            int r4 = r12.zza
            if (r14 != r4) goto L_0x052a
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r3, r12)
            int r3 = r12.zza
            int r3 = com.google.android.gms.internal.auth.zzej.zzb(r3)
            r13.zze(r3)
            goto L_0x0512
        L_0x052a:
            r11 = r1
            r1 = r2
            r9 = r10
            r7 = r14
            r10 = r17
            r25 = r21
            r8 = r26
            goto L_0x0868
        L_0x0536:
            r11 = r1
            r9 = r10
            r7 = r14
            r10 = r17
            r25 = r21
            goto L_0x05ee
        L_0x053f:
            r14 = r20
            r3 = 2
            if (r2 != r3) goto L_0x054f
            int r2 = com.google.android.gms.internal.auth.zzdu.zzf(r15, r1, r13, r12)
            r11 = r1
            r8 = r6
            r9 = r17
            r25 = r21
            goto L_0x0564
        L_0x054f:
            if (r2 != 0) goto L_0x05e5
            r11 = r1
            r1 = r14
            r2 = r41
            r3 = r11
            r4 = r43
            r9 = r17
            r25 = r21
            r5 = r13
            r8 = r6
            r6 = r45
            int r2 = com.google.android.gms.internal.auth.zzdu.zzj(r1, r2, r3, r4, r5, r6)
        L_0x0564:
            com.google.android.gms.internal.auth.zzey r1 = r0.zzq(r9)
            com.google.android.gms.internal.auth.zzgz r3 = r0.zzl
            int r4 = com.google.android.gms.internal.auth.zzgk.zza
            if (r1 == 0) goto L_0x05d4
            boolean r4 = r13 instanceof java.util.RandomAccess
            if (r4 == 0) goto L_0x05ae
            int r4 = r13.size()
            r42 = r2
            r2 = r16
            r5 = r19
            r6 = r5
        L_0x057d:
            if (r5 >= r4) goto L_0x05a4
            java.lang.Object r17 = r13.get(r5)
            java.lang.Integer r17 = (java.lang.Integer) r17
            int r0 = r17.intValue()
            boolean r17 = r1.zza()
            if (r17 == 0) goto L_0x059b
            if (r5 == r6) goto L_0x0598
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r13.set(r6, r0)
        L_0x0598:
            int r6 = r6 + 1
            goto L_0x059f
        L_0x059b:
            java.lang.Object r2 = com.google.android.gms.internal.auth.zzgk.zzc(r7, r10, r0, r2, r3)
        L_0x059f:
            int r5 = r5 + 1
            r0 = r39
            goto L_0x057d
        L_0x05a4:
            if (r6 == r4) goto L_0x05d6
            java.util.List r0 = r13.subList(r6, r4)
            r0.clear()
            goto L_0x05d6
        L_0x05ae:
            r42 = r2
            java.util.Iterator r0 = r13.iterator()
            r2 = r16
        L_0x05b6:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x05d6
            java.lang.Object r4 = r0.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            boolean r5 = r1.zza()
            if (r5 != 0) goto L_0x05b6
            java.lang.Object r2 = com.google.android.gms.internal.auth.zzgk.zzc(r7, r10, r4, r2, r3)
            r0.remove()
            goto L_0x05b6
        L_0x05d4:
            r42 = r2
        L_0x05d6:
            r0 = r39
            r1 = r42
            r7 = r14
        L_0x05db:
            r14 = r8
            r8 = r26
            r37 = r10
            r10 = r9
            r9 = r37
            goto L_0x09a0
        L_0x05e5:
            r25 = r21
            r0 = r39
            r11 = r1
            r9 = r10
            r7 = r14
            r10 = r17
        L_0x05ee:
            r8 = r26
            goto L_0x0756
        L_0x05f2:
            r11 = r1
            r8 = r6
            r9 = r17
            r14 = r20
            r25 = r21
            r0 = 2
            if (r2 != r0) goto L_0x0685
            int r0 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r12)
            int r1 = r12.zza
            if (r1 < 0) goto L_0x0653
            int r2 = r15.length
            int r2 = r2 - r0
            if (r1 > r2) goto L_0x064e
            if (r1 != 0) goto L_0x0611
            com.google.android.gms.internal.auth.zzef r1 = com.google.android.gms.internal.auth.zzef.zzb
            r13.add(r1)
            goto L_0x0619
        L_0x0611:
            com.google.android.gms.internal.auth.zzef r2 = com.google.android.gms.internal.auth.zzef.zzk(r15, r0, r1)
            r13.add(r2)
        L_0x0618:
            int r0 = r0 + r1
        L_0x0619:
            if (r0 >= r8) goto L_0x0649
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r0, r12)
            int r2 = r12.zza
            if (r14 != r2) goto L_0x0649
            int r0 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r1 = r12.zza
            if (r1 < 0) goto L_0x0644
            int r2 = r15.length
            int r2 = r2 - r0
            if (r1 > r2) goto L_0x063f
            if (r1 != 0) goto L_0x0637
            com.google.android.gms.internal.auth.zzef r1 = com.google.android.gms.internal.auth.zzef.zzb
            r13.add(r1)
            goto L_0x0619
        L_0x0637:
            com.google.android.gms.internal.auth.zzef r2 = com.google.android.gms.internal.auth.zzef.zzk(r15, r0, r1)
            r13.add(r2)
            goto L_0x0618
        L_0x063f:
            com.google.android.gms.internal.auth.zzfb r0 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r0
        L_0x0644:
            com.google.android.gms.internal.auth.zzfb r0 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r0
        L_0x0649:
            r1 = r0
            r7 = r14
            r0 = r39
            goto L_0x05db
        L_0x064e:
            com.google.android.gms.internal.auth.zzfb r0 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r0
        L_0x0653:
            com.google.android.gms.internal.auth.zzfb r0 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r0
        L_0x0658:
            r11 = r1
            r8 = r6
            r9 = r17
            r14 = r20
            r25 = r21
            r0 = 2
            if (r2 != r0) goto L_0x0685
            r0 = r39
            com.google.android.gms.internal.auth.zzgi r1 = r0.zzr(r9)
            r6 = r8
            r8 = r1
            r5 = r9
            r9 = r14
            r4 = r10
            r10 = r41
            r1 = r11
            r3 = r26
            r2 = r12
            r12 = r43
            r7 = r14
            r14 = r45
            int r8 = com.google.android.gms.internal.auth.zzdu.zze(r8, r9, r10, r11, r12, r13, r14)
            r12 = r2
            r9 = r4
            r10 = r5
            r14 = r6
            r1 = r8
            r8 = r3
            goto L_0x09a0
        L_0x0685:
            r0 = r39
            r7 = r14
            r14 = r8
            r8 = r26
            r37 = r10
            r10 = r9
            r9 = r37
            goto L_0x099f
        L_0x0692:
            r4 = r10
            r5 = r17
            r7 = r20
            r25 = r21
            r3 = r26
            r10 = 2
            if (r2 != r10) goto L_0x0752
            r10 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r8 & r10
            int r2 = (r8 > r23 ? 1 : (r8 == r23 ? 0 : -1))
            if (r2 != 0) goto L_0x06f0
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r8 = r12.zza
            if (r8 < 0) goto L_0x06eb
            if (r8 != 0) goto L_0x06b6
            r10 = r27
            r13.add(r10)
            goto L_0x06c3
        L_0x06b6:
            r10 = r27
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r11 = com.google.android.gms.internal.auth.zzfa.zzb
            r9.<init>(r15, r2, r8, r11)
            r13.add(r9)
        L_0x06c2:
            int r2 = r2 + r8
        L_0x06c3:
            if (r2 >= r6) goto L_0x0863
            int r8 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r12)
            int r9 = r12.zza
            if (r7 != r9) goto L_0x0863
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r8, r12)
            int r8 = r12.zza
            if (r8 < 0) goto L_0x06e6
            if (r8 != 0) goto L_0x06db
            r13.add(r10)
            goto L_0x06c3
        L_0x06db:
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r11 = com.google.android.gms.internal.auth.zzfa.zzb
            r9.<init>(r15, r2, r8, r11)
            r13.add(r9)
            goto L_0x06c2
        L_0x06e6:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x06eb:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x06f0:
            r10 = r27
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r8 = r12.zza
            if (r8 < 0) goto L_0x074d
            if (r8 != 0) goto L_0x0700
            r13.add(r10)
            goto L_0x0713
        L_0x0700:
            int r9 = r2 + r8
            boolean r11 = com.google.android.gms.internal.auth.zzhn.zzc(r15, r2, r9)
            if (r11 == 0) goto L_0x0748
            java.lang.String r11 = new java.lang.String
            java.nio.charset.Charset r14 = com.google.android.gms.internal.auth.zzfa.zzb
            r11.<init>(r15, r2, r8, r14)
            r13.add(r11)
        L_0x0712:
            r2 = r9
        L_0x0713:
            if (r2 >= r6) goto L_0x0863
            int r8 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r12)
            int r9 = r12.zza
            if (r7 != r9) goto L_0x0863
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r8, r12)
            int r8 = r12.zza
            if (r8 < 0) goto L_0x0743
            if (r8 != 0) goto L_0x072b
            r13.add(r10)
            goto L_0x0713
        L_0x072b:
            int r9 = r2 + r8
            boolean r11 = com.google.android.gms.internal.auth.zzhn.zzc(r15, r2, r9)
            if (r11 == 0) goto L_0x073e
            java.lang.String r11 = new java.lang.String
            java.nio.charset.Charset r14 = com.google.android.gms.internal.auth.zzfa.zzb
            r11.<init>(r15, r2, r8, r14)
            r13.add(r11)
            goto L_0x0712
        L_0x073e:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x0743:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x0748:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x074d:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzc()
            throw r1
        L_0x0752:
            r11 = r1
            r8 = r3
            r9 = r4
            r10 = r5
        L_0x0756:
            r14 = r6
            goto L_0x099f
        L_0x0759:
            r4 = r10
            r5 = r17
            r7 = r20
            r25 = r21
            r3 = r26
            r8 = 2
            if (r2 != r8) goto L_0x078b
            com.google.android.gms.internal.auth.zzdv r13 = (com.google.android.gms.internal.auth.zzdv) r13
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r8 = r12.zza
            int r8 = r8 + r2
        L_0x076e:
            if (r2 >= r8) goto L_0x0782
            int r2 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r2, r12)
            long r9 = r12.zzb
            int r9 = (r9 > r23 ? 1 : (r9 == r23 ? 0 : -1))
            if (r9 == 0) goto L_0x077c
            r10 = 1
            goto L_0x077e
        L_0x077c:
            r10 = r19
        L_0x077e:
            r13.zze(r10)
            goto L_0x076e
        L_0x0782:
            if (r2 != r8) goto L_0x0786
            goto L_0x0863
        L_0x0786:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x078b:
            if (r2 != 0) goto L_0x0752
            com.google.android.gms.internal.auth.zzdv r13 = (com.google.android.gms.internal.auth.zzdv) r13
            int r2 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r1, r12)
            long r8 = r12.zzb
            int r8 = (r8 > r23 ? 1 : (r8 == r23 ? 0 : -1))
            if (r8 == 0) goto L_0x079b
            r10 = 1
            goto L_0x079d
        L_0x079b:
            r10 = r19
        L_0x079d:
            r13.zze(r10)
        L_0x07a0:
            if (r2 >= r6) goto L_0x0863
            int r8 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r12)
            int r9 = r12.zza
            if (r7 != r9) goto L_0x0863
            int r2 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r8, r12)
            long r8 = r12.zzb
            int r8 = (r8 > r23 ? 1 : (r8 == r23 ? 0 : -1))
            if (r8 == 0) goto L_0x07b6
            r10 = 1
            goto L_0x07b8
        L_0x07b6:
            r10 = r19
        L_0x07b8:
            r13.zze(r10)
            goto L_0x07a0
        L_0x07bc:
            r4 = r10
            r5 = r17
            r7 = r20
            r25 = r21
            r3 = r26
            r8 = 2
            if (r2 != r8) goto L_0x07e6
            com.google.android.gms.internal.auth.zzew r13 = (com.google.android.gms.internal.auth.zzew) r13
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r8 = r12.zza
            int r8 = r8 + r2
        L_0x07d1:
            if (r2 >= r8) goto L_0x07dd
            int r9 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r2)
            r13.zze(r9)
            int r2 = r2 + 4
            goto L_0x07d1
        L_0x07dd:
            if (r2 != r8) goto L_0x07e1
            goto L_0x0863
        L_0x07e1:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x07e6:
            r8 = 5
            if (r2 != r8) goto L_0x0752
            com.google.android.gms.internal.auth.zzew r13 = (com.google.android.gms.internal.auth.zzew) r13
            int r2 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r1)
            r13.zze(r2)
            int r2 = r1 + 4
        L_0x07f4:
            if (r2 >= r6) goto L_0x0863
            int r8 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r12)
            int r9 = r12.zza
            if (r7 != r9) goto L_0x0863
            int r2 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r8)
            r13.zze(r2)
            int r2 = r8 + 4
            goto L_0x07f4
        L_0x0808:
            r4 = r10
            r5 = r17
            r7 = r20
            r25 = r21
            r3 = r26
            r8 = 2
            if (r2 != r8) goto L_0x0831
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r8 = r12.zza
            int r8 = r8 + r2
        L_0x081d:
            if (r2 >= r8) goto L_0x0829
            long r9 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r2)
            r13.zze(r9)
            int r2 = r2 + 8
            goto L_0x081d
        L_0x0829:
            if (r2 != r8) goto L_0x082c
            goto L_0x0863
        L_0x082c:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x0831:
            r8 = 1
            if (r2 != r8) goto L_0x0752
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            long r8 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r1)
            r13.zze(r8)
            int r2 = r1 + 8
        L_0x083f:
            if (r2 >= r6) goto L_0x0863
            int r8 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r2, r12)
            int r9 = r12.zza
            if (r7 != r9) goto L_0x0863
            long r9 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r8)
            r13.zze(r9)
            int r2 = r8 + 8
            goto L_0x083f
        L_0x0853:
            r4 = r10
            r5 = r17
            r7 = r20
            r25 = r21
            r3 = r26
            r8 = 2
            if (r2 != r8) goto L_0x086b
            int r2 = com.google.android.gms.internal.auth.zzdu.zzf(r15, r1, r13, r12)
        L_0x0863:
            r11 = r1
            r1 = r2
            r8 = r3
            r9 = r4
            r10 = r5
        L_0x0868:
            r14 = r6
            goto L_0x09a0
        L_0x086b:
            if (r2 != 0) goto L_0x0752
            r11 = r1
            r1 = r7
            r2 = r41
            r8 = r3
            r3 = r11
            r9 = r4
            r4 = r43
            r10 = r5
            r5 = r13
            r14 = r6
            r6 = r45
            int r1 = com.google.android.gms.internal.auth.zzdu.zzj(r1, r2, r3, r4, r5, r6)
            goto L_0x09a0
        L_0x0881:
            r11 = r1
            r14 = r6
            r9 = r10
            r10 = r17
            r7 = r20
            r25 = r21
            r8 = r26
            r1 = 2
            if (r2 != r1) goto L_0x08ad
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r12)
            int r2 = r12.zza
            int r2 = r2 + r1
        L_0x0898:
            if (r1 >= r2) goto L_0x08a4
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r1, r12)
            long r3 = r12.zzb
            r13.zze(r3)
            goto L_0x0898
        L_0x08a4:
            if (r1 != r2) goto L_0x08a8
            goto L_0x09a0
        L_0x08a8:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x08ad:
            if (r2 != 0) goto L_0x099f
            com.google.android.gms.internal.auth.zzfm r13 = (com.google.android.gms.internal.auth.zzfm) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r11, r12)
            long r2 = r12.zzb
            r13.zze(r2)
        L_0x08ba:
            if (r1 >= r14) goto L_0x09a0
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r3 = r12.zza
            if (r7 != r3) goto L_0x09a0
            int r1 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r2, r12)
            long r2 = r12.zzb
            r13.zze(r2)
            goto L_0x08ba
        L_0x08ce:
            r11 = r1
            r14 = r6
            r9 = r10
            r10 = r17
            r7 = r20
            r25 = r21
            r8 = r26
            r1 = 2
            if (r2 != r1) goto L_0x08fe
            com.google.android.gms.internal.auth.zzer r13 = (com.google.android.gms.internal.auth.zzer) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r12)
            int r2 = r12.zza
            int r2 = r2 + r1
        L_0x08e5:
            if (r1 >= r2) goto L_0x08f5
            int r3 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r1)
            float r3 = java.lang.Float.intBitsToFloat(r3)
            r13.zze(r3)
            int r1 = r1 + 4
            goto L_0x08e5
        L_0x08f5:
            if (r1 != r2) goto L_0x08f9
            goto L_0x09a0
        L_0x08f9:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x08fe:
            r1 = 5
            if (r2 != r1) goto L_0x099f
            com.google.android.gms.internal.auth.zzer r13 = (com.google.android.gms.internal.auth.zzer) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r11)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r13.zze(r1)
            int r1 = r11 + 4
        L_0x0910:
            if (r1 >= r14) goto L_0x09a0
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r3 = r12.zza
            if (r7 != r3) goto L_0x09a0
            int r1 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r2)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r13.zze(r1)
            int r1 = r2 + 4
            goto L_0x0910
        L_0x0928:
            r11 = r1
            r14 = r6
            r9 = r10
            r10 = r17
            r7 = r20
            r25 = r21
            r8 = r26
            r1 = 2
            if (r2 != r1) goto L_0x0957
            com.google.android.gms.internal.auth.zzek r13 = (com.google.android.gms.internal.auth.zzek) r13
            int r1 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r12)
            int r2 = r12.zza
            int r2 = r2 + r1
        L_0x093f:
            if (r1 >= r2) goto L_0x094f
            long r3 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r1)
            double r3 = java.lang.Double.longBitsToDouble(r3)
            r13.zze(r3)
            int r1 = r1 + 8
            goto L_0x093f
        L_0x094f:
            if (r1 != r2) goto L_0x0952
            goto L_0x09a0
        L_0x0952:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzf()
            throw r1
        L_0x0957:
            r1 = 1
            if (r2 != r1) goto L_0x099f
            com.google.android.gms.internal.auth.zzek r13 = (com.google.android.gms.internal.auth.zzek) r13
            long r1 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r11)
            double r1 = java.lang.Double.longBitsToDouble(r1)
            r13.zze(r1)
            int r1 = r11 + 8
        L_0x0969:
            if (r1 >= r14) goto L_0x09a0
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r3 = r12.zza
            if (r7 != r3) goto L_0x09a0
            long r3 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r2)
            double r3 = java.lang.Double.longBitsToDouble(r3)
            r13.zze(r3)
            int r1 = r2 + 8
            goto L_0x0969
        L_0x0981:
            if (r1 >= r14) goto L_0x09a0
            int r3 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r1, r12)
            int r2 = r12.zza
            if (r7 != r2) goto L_0x09a0
            r1 = r17
            r2 = r41
            r4 = r43
            r5 = r20
            r6 = r45
            int r1 = com.google.android.gms.internal.auth.zzdu.zzc(r1, r2, r3, r4, r5, r6)
            java.lang.Object r2 = r12.zzc
            r13.add(r2)
            goto L_0x0981
        L_0x099f:
            r1 = r11
        L_0x09a0:
            if (r1 == r11) goto L_0x09b2
            r13 = r44
            r4 = r7
            r11 = r8
            r2 = r9
            r3 = r10
            r10 = r19
            r5 = r25
            r6 = r28
            r7 = r40
            goto L_0x001b
        L_0x09b2:
            r3 = r1
            r4 = r7
            r26 = r8
            r1 = r9
            r5 = r12
            r6 = r14
            r7 = r40
            r8 = r44
            goto L_0x0caf
        L_0x09bf:
            r26 = r11
            r8 = r17
            r7 = r20
            r25 = r21
            r11 = r1
            r1 = r5
            r37 = r10
            r10 = r3
            r3 = r37
            r5 = 50
            if (r4 != r5) goto L_0x0a07
            r5 = 2
            if (r2 != r5) goto L_0x09fb
            sun.misc.Unsafe r2 = zzb
            java.lang.Object r1 = r0.zzs(r1)
            r7 = r40
            java.lang.Object r3 = r2.getObject(r7, r13)
            r4 = r3
            com.google.android.gms.internal.auth.zzfr r4 = (com.google.android.gms.internal.auth.zzfr) r4
            boolean r4 = r4.zze()
            if (r4 != 0) goto L_0x09f8
            com.google.android.gms.internal.auth.zzfr r4 = com.google.android.gms.internal.auth.zzfr.zza()
            com.google.android.gms.internal.auth.zzfr r4 = r4.zzb()
            com.google.android.gms.internal.auth.zzfs.zza(r4, r3)
            r2.putObject(r7, r13, r4)
        L_0x09f8:
            com.google.android.gms.internal.auth.zzfq r1 = (com.google.android.gms.internal.auth.zzfq) r1
            throw r16
        L_0x09fb:
            r5 = r7
            r7 = r40
        L_0x09fe:
            r8 = r44
            r10 = r1
            r1 = r3
            r4 = r5
            r3 = r11
            r5 = r12
            goto L_0x0caf
        L_0x0a07:
            r5 = r7
            r7 = r40
            int r17 = r1 + 2
            sun.misc.Unsafe r6 = zzb
            r9 = r9[r17]
            r17 = r6
            r6 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r9 & r6
            long r6 = (long) r9
            switch(r4) {
                case 51: goto L_0x0c68;
                case 52: goto L_0x0c42;
                case 53: goto L_0x0c21;
                case 54: goto L_0x0c21;
                case 55: goto L_0x0bff;
                case 56: goto L_0x0bdc;
                case 57: goto L_0x0bb9;
                case 58: goto L_0x0b8c;
                case 59: goto L_0x0b3f;
                case 60: goto L_0x0afd;
                case 61: goto L_0x0ace;
                case 62: goto L_0x0bff;
                case 63: goto L_0x0a97;
                case 64: goto L_0x0bb9;
                case 65: goto L_0x0bdc;
                case 66: goto L_0x0a76;
                case 67: goto L_0x0a54;
                case 68: goto L_0x0a27;
                default: goto L_0x0a1a;
            }
        L_0x0a1a:
            r7 = r40
            r6 = r43
            r17 = r1
            r1 = r3
            r20 = r5
            r8 = r11
            r5 = r12
            goto L_0x0c8e
        L_0x0a27:
            r4 = 3
            if (r2 != r4) goto L_0x0a1a
            r7 = r40
            java.lang.Object r2 = r0.zzu(r7, r3, r1)
            r4 = r5 & -8
            r13 = r4 | 4
            com.google.android.gms.internal.auth.zzgi r9 = r0.zzr(r1)
            r8 = r2
            r10 = r41
            r6 = r11
            r4 = r12
            r12 = r43
            r14 = r45
            int r8 = com.google.android.gms.internal.auth.zzdu.zzl(r8, r9, r10, r11, r12, r13, r14)
            r0.zzC(r7, r3, r1, r2)
            r17 = r1
            r1 = r3
            r20 = r5
            r2 = r8
            r5 = r4
            r8 = r6
            r6 = r43
            goto L_0x0c8f
        L_0x0a54:
            r4 = r12
            r37 = r6
            r7 = r40
            r6 = r11
            r11 = r37
            if (r2 != 0) goto L_0x0af2
            int r2 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r6, r4)
            long r8 = r4.zzb
            long r8 = com.google.android.gms.internal.auth.zzej.zzc(r8)
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            r9 = r17
            r9.putObject(r7, r13, r8)
            r9.putInt(r7, r11, r3)
            goto L_0x0ae7
        L_0x0a76:
            r4 = r12
            r9 = r17
            r37 = r6
            r7 = r40
            r6 = r11
            r11 = r37
            if (r2 != 0) goto L_0x0af2
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r6, r4)
            int r8 = r4.zza
            int r8 = com.google.android.gms.internal.auth.zzej.zzb(r8)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r9.putObject(r7, r13, r8)
            r9.putInt(r7, r11, r3)
            goto L_0x0ae7
        L_0x0a97:
            r4 = r12
            r9 = r17
            r37 = r6
            r7 = r40
            r6 = r11
            r11 = r37
            if (r2 != 0) goto L_0x0af2
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r6, r4)
            int r8 = r4.zza
            com.google.android.gms.internal.auth.zzey r10 = r0.zzq(r1)
            if (r10 == 0) goto L_0x0ac3
            boolean r10 = r10.zza()
            if (r10 == 0) goto L_0x0ab6
            goto L_0x0ac3
        L_0x0ab6:
            com.google.android.gms.internal.auth.zzha r9 = zzc(r40)
            long r10 = (long) r8
            java.lang.Long r8 = java.lang.Long.valueOf(r10)
            r9.zzh(r5, r8)
            goto L_0x0ae7
        L_0x0ac3:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r9.putObject(r7, r13, r8)
            r9.putInt(r7, r11, r3)
            goto L_0x0ae7
        L_0x0ace:
            r4 = r12
            r9 = r17
            r8 = 2
            r37 = r6
            r7 = r40
            r6 = r11
            r11 = r37
            if (r2 != r8) goto L_0x0af2
            int r2 = com.google.android.gms.internal.auth.zzdu.zza(r15, r6, r4)
            java.lang.Object r8 = r4.zzc
            r9.putObject(r7, r13, r8)
            r9.putInt(r7, r11, r3)
        L_0x0ae7:
            r17 = r1
            r1 = r3
            r20 = r5
            r8 = r6
            r6 = r43
            r5 = r4
            goto L_0x0c8f
        L_0x0af2:
            r17 = r1
            r1 = r3
            r20 = r5
            r8 = r6
            r6 = r43
            r5 = r4
            goto L_0x0c8e
        L_0x0afd:
            r7 = r40
            r6 = r11
            r4 = r12
            r8 = 2
            if (r2 != r8) goto L_0x0b33
            java.lang.Object r8 = r0.zzu(r7, r3, r1)
            com.google.android.gms.internal.auth.zzgi r2 = r0.zzr(r1)
            r9 = r1
            r1 = r8
            r10 = r3
            r3 = r41
            r11 = r4
            r4 = r6
            r13 = r5
            r12 = r26
            r5 = r43
            r14 = r43
            r11 = r6
            r12 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r45
            int r1 = com.google.android.gms.internal.auth.zzdu.zzm(r1, r2, r3, r4, r5, r6)
            r0.zzC(r7, r10, r9, r8)
            r5 = r45
            r2 = r1
            r17 = r9
            r1 = r10
            r8 = r11
            r20 = r13
            r6 = r14
            goto L_0x0c8f
        L_0x0b33:
            r17 = r1
            r1 = r3
            r20 = r5
            r8 = r6
            r6 = r43
            r5 = r45
            goto L_0x0c8e
        L_0x0b3f:
            r35 = r6
            r9 = r17
            r4 = r26
            r7 = r40
            r6 = r43
            r17 = r1
            r1 = r3
            r3 = r5
            r5 = r12
            r12 = 2
            if (r2 != r12) goto L_0x0b87
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r11, r5)
            int r12 = r5.zza
            if (r12 != 0) goto L_0x0b5f
            r9.putObject(r7, r13, r10)
        L_0x0b5c:
            r12 = r35
            goto L_0x0b7d
        L_0x0b5f:
            r8 = r8 & r22
            if (r8 == 0) goto L_0x0b71
            int r8 = r2 + r12
            boolean r8 = com.google.android.gms.internal.auth.zzhn.zzc(r15, r2, r8)
            if (r8 == 0) goto L_0x0b6c
            goto L_0x0b71
        L_0x0b6c:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzb()
            throw r1
        L_0x0b71:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.auth.zzfa.zzb
            r8.<init>(r15, r2, r12, r10)
            r9.putObject(r7, r13, r8)
            int r2 = r2 + r12
            goto L_0x0b5c
        L_0x0b7d:
            r9.putInt(r7, r12, r1)
            r20 = r3
            r26 = r4
            r8 = r11
            goto L_0x0c8f
        L_0x0b87:
            r20 = r3
            r26 = r4
            goto L_0x0bb6
        L_0x0b8c:
            r20 = r5
            r5 = r12
            r9 = r17
            r17 = r1
            r1 = r3
            r3 = r6
            r7 = r40
            r6 = r43
            if (r2 != 0) goto L_0x0bb6
            int r2 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r11, r5)
            r8 = r11
            long r10 = r5.zzb
            int r10 = (r10 > r23 ? 1 : (r10 == r23 ? 0 : -1))
            if (r10 == 0) goto L_0x0ba8
            r10 = 1
            goto L_0x0baa
        L_0x0ba8:
            r10 = r19
        L_0x0baa:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r9.putObject(r7, r13, r10)
            r9.putInt(r7, r3, r1)
            goto L_0x0c8f
        L_0x0bb6:
            r8 = r11
            goto L_0x0c8e
        L_0x0bb9:
            r20 = r5
            r8 = r11
            r5 = r12
            r9 = r17
            r10 = 5
            r17 = r1
            r1 = r3
            r3 = r6
            r7 = r40
            r6 = r43
            if (r2 != r10) goto L_0x0c8e
            int r2 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r8)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r9.putObject(r7, r13, r2)
            int r2 = r8 + 4
            r9.putInt(r7, r3, r1)
            goto L_0x0c8f
        L_0x0bdc:
            r20 = r5
            r8 = r11
            r5 = r12
            r9 = r17
            r10 = 1
            r17 = r1
            r1 = r3
            r3 = r6
            r7 = r40
            r6 = r43
            if (r2 != r10) goto L_0x0c8e
            long r10 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r8)
            java.lang.Long r2 = java.lang.Long.valueOf(r10)
            r9.putObject(r7, r13, r2)
            int r2 = r8 + 8
            r9.putInt(r7, r3, r1)
            goto L_0x0c8f
        L_0x0bff:
            r20 = r5
            r8 = r11
            r5 = r12
            r9 = r17
            r17 = r1
            r1 = r3
            r3 = r6
            r7 = r40
            r6 = r43
            if (r2 != 0) goto L_0x0c8e
            int r2 = com.google.android.gms.internal.auth.zzdu.zzh(r15, r8, r5)
            int r10 = r5.zza
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r9.putObject(r7, r13, r10)
            r9.putInt(r7, r3, r1)
            goto L_0x0c8f
        L_0x0c21:
            r20 = r5
            r8 = r11
            r5 = r12
            r9 = r17
            r17 = r1
            r1 = r3
            r3 = r6
            r7 = r40
            r6 = r43
            if (r2 != 0) goto L_0x0c8e
            int r2 = com.google.android.gms.internal.auth.zzdu.zzk(r15, r8, r5)
            long r10 = r5.zzb
            java.lang.Long r10 = java.lang.Long.valueOf(r10)
            r9.putObject(r7, r13, r10)
            r9.putInt(r7, r3, r1)
            goto L_0x0c8f
        L_0x0c42:
            r20 = r5
            r8 = r11
            r5 = r12
            r9 = r17
            r10 = 5
            r17 = r1
            r1 = r3
            r3 = r6
            r7 = r40
            r6 = r43
            if (r2 != r10) goto L_0x0c8e
            int r2 = com.google.android.gms.internal.auth.zzdu.zzb(r15, r8)
            float r2 = java.lang.Float.intBitsToFloat(r2)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r9.putObject(r7, r13, r2)
            int r2 = r8 + 4
            r9.putInt(r7, r3, r1)
            goto L_0x0c8f
        L_0x0c68:
            r20 = r5
            r8 = r11
            r5 = r12
            r9 = r17
            r10 = 1
            r17 = r1
            r1 = r3
            r3 = r6
            r7 = r40
            r6 = r43
            if (r2 != r10) goto L_0x0c8e
            long r10 = com.google.android.gms.internal.auth.zzdu.zzn(r15, r8)
            double r10 = java.lang.Double.longBitsToDouble(r10)
            java.lang.Double r2 = java.lang.Double.valueOf(r10)
            r9.putObject(r7, r13, r2)
            int r2 = r8 + 8
            r9.putInt(r7, r3, r1)
            goto L_0x0c8f
        L_0x0c8e:
            r2 = r8
        L_0x0c8f:
            if (r2 == r8) goto L_0x0ca8
            r13 = r44
            r12 = r5
            r14 = r6
            r3 = r17
            r10 = r19
            r4 = r20
            r5 = r25
            r11 = r26
            r6 = r28
            r37 = r2
            r2 = r1
            r1 = r37
            goto L_0x001b
        L_0x0ca8:
            r8 = r44
            r3 = r2
            r10 = r17
            goto L_0x0399
        L_0x0caf:
            if (r4 != r8) goto L_0x0cbd
            if (r8 != 0) goto L_0x0cb4
            goto L_0x0cbd
        L_0x0cb4:
            r1 = r3
            r9 = r6
            r5 = r25
            r13 = r26
            r6 = r28
            goto L_0x0ce9
        L_0x0cbd:
            com.google.android.gms.internal.auth.zzha r9 = zzc(r40)
            r11 = r1
            r1 = r4
            r2 = r41
            r12 = r4
            r13 = r26
            r4 = r43
            r5 = r9
            r9 = r6
            r6 = r45
            int r1 = com.google.android.gms.internal.auth.zzdu.zzg(r1, r2, r3, r4, r5, r6)
            r14 = r9
            r3 = r10
            r2 = r11
            r4 = r12
            r11 = r13
            r10 = r19
            r5 = r25
            r6 = r28
            r12 = r45
            r13 = r8
            goto L_0x001b
        L_0x0ce2:
            r25 = r5
            r28 = r6
            r8 = r13
            r9 = r14
            r13 = r11
        L_0x0ce9:
            r2 = 1048575(0xfffff, float:1.469367E-39)
            if (r6 == r2) goto L_0x0cf2
            long r2 = (long) r6
            r13.putInt(r7, r2, r5)
        L_0x0cf2:
            int r2 = r0.zzi
        L_0x0cf4:
            int r3 = r0.zzj
            if (r2 >= r3) goto L_0x0d22
            int[] r3 = r0.zzh
            r3 = r3[r2]
            int[] r5 = r0.zzc
            r5 = r5[r3]
            int r5 = r0.zzo(r3)
            r6 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r6
            long r10 = (long) r5
            java.lang.Object r5 = com.google.android.gms.internal.auth.zzhj.zzf(r7, r10)
            if (r5 != 0) goto L_0x0d10
            goto L_0x0d16
        L_0x0d10:
            com.google.android.gms.internal.auth.zzey r10 = r0.zzq(r3)
            if (r10 != 0) goto L_0x0d19
        L_0x0d16:
            int r2 = r2 + 1
            goto L_0x0cf4
        L_0x0d19:
            com.google.android.gms.internal.auth.zzfr r5 = (com.google.android.gms.internal.auth.zzfr) r5
            java.lang.Object r1 = r0.zzs(r3)
            com.google.android.gms.internal.auth.zzfq r1 = (com.google.android.gms.internal.auth.zzfq) r1
            throw r16
        L_0x0d22:
            if (r8 != 0) goto L_0x0d2c
            if (r1 != r9) goto L_0x0d27
            goto L_0x0d30
        L_0x0d27:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzd()
            throw r1
        L_0x0d2c:
            if (r1 > r9) goto L_0x0d31
            if (r4 != r8) goto L_0x0d31
        L_0x0d30:
            return r1
        L_0x0d31:
            com.google.android.gms.internal.auth.zzfb r1 = com.google.android.gms.internal.auth.zzfb.zzd()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzga.zzb(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.auth.zzdt):int");
    }

    public final Object zzd() {
        return ((zzev) this.zzg).zzc();
    }

    public final void zze(Object obj) {
        if (zzH(obj)) {
            if (obj instanceof zzev) {
                zzev zzev = (zzev) obj;
                zzev.zzl(Integer.MAX_VALUE);
                zzev.zza = 0;
                zzev.zzj();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzo2 = zzo(i);
                int i2 = 1048575 & zzo2;
                int zzn2 = zzn(zzo2);
                long j = (long) i2;
                if (zzn2 != 9) {
                    if (zzn2 == 60 || zzn2 == 68) {
                        if (zzI(obj, this.zzc[i], i)) {
                            zzr(i).zze(zzb.getObject(obj, j));
                        }
                    } else {
                        switch (zzn2) {
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
                                this.zzk.zza(obj, j);
                                continue;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzfr) object).zzc();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    }
                }
                if (zzE(obj, i)) {
                    zzr(i).zze(zzb.getObject(obj, j));
                }
            }
            this.zzl.zze(obj);
        }
    }

    public final void zzf(Object obj, Object obj2) {
        zzw(obj);
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzo2 = zzo(i);
            int i2 = this.zzc[i];
            long j = (long) (1048575 & zzo2);
            switch (zzn(zzo2)) {
                case 0:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzl(obj, j, zzhj.zza(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 1:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzm(obj, j, zzhj.zzb(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 2:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 3:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 4:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 5:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 6:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 7:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzk(obj, j, zzhj.zzt(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 8:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 9:
                    zzx(obj, obj2, i);
                    break;
                case 10:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 11:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 12:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 13:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 14:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 15:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzn(obj, j, zzhj.zzc(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 16:
                    if (!zzE(obj2, i)) {
                        break;
                    } else {
                        zzhj.zzo(obj, j, zzhj.zzd(obj2, j));
                        zzz(obj, i);
                        break;
                    }
                case 17:
                    zzx(obj, obj2, i);
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
                    this.zzk.zzb(obj, obj2, j);
                    break;
                case 50:
                    int i3 = zzgk.zza;
                    zzhj.zzp(obj, j, zzfs.zza(zzhj.zzf(obj, j), zzhj.zzf(obj2, j)));
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
                    if (!zzI(obj2, i2, i)) {
                        break;
                    } else {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzA(obj, i2, i);
                        break;
                    }
                case 60:
                    zzy(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT:
                case Elf64.Ehdr.E_SHSTRNDX:
                case HtmlCompat.FROM_HTML_MODE_COMPACT:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                case 67:
                    if (!zzI(obj2, i2, i)) {
                        break;
                    } else {
                        zzhj.zzp(obj, j, zzhj.zzf(obj2, j));
                        zzA(obj, i2, i);
                        break;
                    }
                case 68:
                    zzy(obj, obj2, i);
                    break;
            }
        }
        zzgk.zzd(this.zzl, obj, obj2);
    }

    public final void zzg(Object obj, byte[] bArr, int i, int i2, zzdt zzdt) throws IOException {
        zzb(obj, bArr, i, i2, 0, zzdt);
    }

    public final boolean zzh(Object obj, Object obj2) {
        boolean z;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzo2 = zzo(i);
            long j = (long) (zzo2 & 1048575);
            switch (zzn(zzo2)) {
                case 0:
                    if (zzD(obj, obj2, i) && Double.doubleToLongBits(zzhj.zza(obj, j)) == Double.doubleToLongBits(zzhj.zza(obj2, j))) {
                        continue;
                    }
                case 1:
                    if (zzD(obj, obj2, i) && Float.floatToIntBits(zzhj.zzb(obj, j)) == Float.floatToIntBits(zzhj.zzb(obj2, j))) {
                        continue;
                    }
                case 2:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                case 3:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                case 4:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 5:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                case 6:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 7:
                    if (zzD(obj, obj2, i) && zzhj.zzt(obj, j) == zzhj.zzt(obj2, j)) {
                        continue;
                    }
                case 8:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                case 9:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                case 10:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
                case 11:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 12:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 13:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 14:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                case 15:
                    if (zzD(obj, obj2, i) && zzhj.zzc(obj, j) == zzhj.zzc(obj2, j)) {
                        continue;
                    }
                case 16:
                    if (zzD(obj, obj2, i) && zzhj.zzd(obj, j) == zzhj.zzd(obj2, j)) {
                        continue;
                    }
                case 17:
                    if (zzD(obj, obj2, i) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
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
                    z = zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j));
                    break;
                case 50:
                    z = zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j));
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
                    long zzl2 = (long) (zzl(i) & 1048575);
                    if (zzhj.zzc(obj, zzl2) == zzhj.zzc(obj2, zzl2) && zzgk.zzf(zzhj.zzf(obj, j), zzhj.zzf(obj2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzl.zzb(obj).equals(this.zzl.zzb(obj2))) {
            return false;
        }
        return true;
    }

    public final boolean zzi(Object obj) {
        int i;
        int i2;
        Object obj2 = obj;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i4 < this.zzi) {
            int i6 = this.zzh[i4];
            int i7 = this.zzc[i6];
            int zzo2 = zzo(i6);
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
            if ((268435456 & zzo2) != 0 && !zzF(obj, i6, i2, i, i10)) {
                return false;
            }
            int zzn2 = zzn(zzo2);
            if (zzn2 != 9 && zzn2 != 17) {
                if (zzn2 != 27) {
                    if (zzn2 == 60 || zzn2 == 68) {
                        if (zzI(obj2, i7, i6) && !zzG(obj2, zzo2, zzr(i6))) {
                            return false;
                        }
                    } else if (zzn2 != 49) {
                        if (zzn2 == 50 && !((zzfr) zzhj.zzf(obj2, (long) (zzo2 & 1048575))).isEmpty()) {
                            zzfq zzfq = (zzfq) zzs(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzhj.zzf(obj2, (long) (zzo2 & 1048575));
                if (!list.isEmpty()) {
                    zzgi zzr = zzr(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzr.zzi(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (zzF(obj, i6, i2, i, i10) && !zzG(obj2, zzo2, zzr(i6))) {
                return false;
            }
            i4++;
            i5 = i2;
            i3 = i;
        }
        return true;
    }
}
