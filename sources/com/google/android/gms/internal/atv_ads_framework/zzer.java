package com.google.android.gms.internal.atv_ads_framework;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import com.facebook.soloader.Elf64;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzer<T> implements zzey<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzfz.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final zzeo zze;
    private final boolean zzf;
    private final boolean zzg;
    private final int[] zzh;
    private final int zzi;
    private final zzec zzj;
    private final zzfp zzk;
    private final zzcy zzl;
    private final zzet zzm;
    private final zzej zzn;

    private zzer(int[] iArr, Object[] objArr, int i, int i2, zzeo zzeo, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzet zzet, zzec zzec, zzfp zzfp, zzcy zzcy, zzej zzej) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzg = z;
        boolean z3 = false;
        if (zzcy != null && zzcy.zzc(zzeo)) {
            z3 = true;
        }
        this.zzf = z3;
        this.zzh = iArr2;
        this.zzi = i3;
        this.zzm = zzet;
        this.zzj = zzec;
        this.zzk = zzfp;
        this.zzl = zzcy;
        this.zze = zzeo;
        this.zzn = zzej;
    }

    private final boolean zzA(Object obj, int i) {
        int zzn2 = zzn(i);
        long j = (long) (zzn2 & 1048575);
        if (j == 1048575) {
            int zzp = zzp(i);
            long j2 = (long) (zzp & 1048575);
            switch (zzo(zzp)) {
                case 0:
                    return Double.doubleToRawLongBits(zzfz.zza(obj, j2)) != 0;
                case 1:
                    return Float.floatToRawIntBits(zzfz.zzb(obj, j2)) != 0;
                case 2:
                    return zzfz.zzd(obj, j2) != 0;
                case 3:
                    return zzfz.zzd(obj, j2) != 0;
                case 4:
                    return zzfz.zzc(obj, j2) != 0;
                case 5:
                    return zzfz.zzd(obj, j2) != 0;
                case 6:
                    return zzfz.zzc(obj, j2) != 0;
                case 7:
                    return zzfz.zzw(obj, j2);
                case 8:
                    Object zzf2 = zzfz.zzf(obj, j2);
                    if (zzf2 instanceof String) {
                        return !((String) zzf2).isEmpty();
                    }
                    if (zzf2 instanceof zzcn) {
                        return !zzcn.zzb.equals(zzf2);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzfz.zzf(obj, j2) != null;
                case 10:
                    return !zzcn.zzb.equals(zzfz.zzf(obj, j2));
                case 11:
                    return zzfz.zzc(obj, j2) != 0;
                case 12:
                    return zzfz.zzc(obj, j2) != 0;
                case 13:
                    return zzfz.zzc(obj, j2) != 0;
                case 14:
                    return zzfz.zzd(obj, j2) != 0;
                case 15:
                    return zzfz.zzc(obj, j2) != 0;
                case 16:
                    return zzfz.zzd(obj, j2) != 0;
                case 17:
                    return zzfz.zzf(obj, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzfz.zzc(obj, j) & (1 << (zzn2 >>> 20))) != 0;
        }
    }

    private final boolean zzB(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzA(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzC(Object obj, int i, zzey zzey) {
        return zzey.zzh(zzfz.zzf(obj, (long) (i & 1048575)));
    }

    private static boolean zzD(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzdh) {
            return ((zzdh) obj).zzA();
        }
        return true;
    }

    private final boolean zzE(Object obj, int i, int i2) {
        return zzfz.zzc(obj, (long) (zzn(i2) & 1048575)) == i;
    }

    private static boolean zzF(Object obj, long j) {
        return ((Boolean) zzfz.zzf(obj, j)).booleanValue();
    }

    private static final void zzG(int i, Object obj, zzgg zzgg) throws IOException {
        if (obj instanceof String) {
            zzgg.zzD(i, (String) obj);
        } else {
            zzgg.zzd(i, (zzcn) obj);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:121:0x026d  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0270  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0285  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x032e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0372  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.atv_ads_framework.zzer zzi(java.lang.Class r31, com.google.android.gms.internal.atv_ads_framework.zzel r32, com.google.android.gms.internal.atv_ads_framework.zzet r33, com.google.android.gms.internal.atv_ads_framework.zzec r34, com.google.android.gms.internal.atv_ads_framework.zzfp r35, com.google.android.gms.internal.atv_ads_framework.zzcy r36, com.google.android.gms.internal.atv_ads_framework.zzej r37) {
        /*
            r0 = r32
            boolean r1 = r0 instanceof com.google.android.gms.internal.atv_ads_framework.zzex
            if (r1 == 0) goto L_0x03da
            com.google.android.gms.internal.atv_ads_framework.zzex r0 = (com.google.android.gms.internal.atv_ads_framework.zzex) r0
            int r1 = r0.zzc()
            java.lang.String r2 = r0.zzd()
            int r3 = r2.length()
            r4 = 0
            char r5 = r2.charAt(r4)
            r6 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r6) goto L_0x0029
            r5 = 1
        L_0x001f:
            int r8 = r5 + 1
            char r5 = r2.charAt(r5)
            if (r5 < r6) goto L_0x002a
            r5 = r8
            goto L_0x001f
        L_0x0029:
            r8 = 1
        L_0x002a:
            int r5 = r8 + 1
            char r8 = r2.charAt(r8)
            if (r8 < r6) goto L_0x0049
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0036:
            int r11 = r5 + 1
            char r5 = r2.charAt(r5)
            if (r5 < r6) goto L_0x0046
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r10
            r8 = r8 | r5
            int r10 = r10 + 13
            r5 = r11
            goto L_0x0036
        L_0x0046:
            int r5 = r5 << r10
            r8 = r8 | r5
            r5 = r11
        L_0x0049:
            if (r8 != 0) goto L_0x0059
            int[] r8 = zza
            r10 = r4
            r12 = r10
            r13 = r12
            r14 = r13
            r19 = r14
            r18 = r8
            r8 = r19
            goto L_0x016f
        L_0x0059:
            int r8 = r5 + 1
            char r5 = r2.charAt(r5)
            if (r5 < r6) goto L_0x0078
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0065:
            int r11 = r8 + 1
            char r8 = r2.charAt(r8)
            if (r8 < r6) goto L_0x0075
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r10
            r5 = r5 | r8
            int r10 = r10 + 13
            r8 = r11
            goto L_0x0065
        L_0x0075:
            int r8 = r8 << r10
            r5 = r5 | r8
            r8 = r11
        L_0x0078:
            int r10 = r8 + 1
            char r8 = r2.charAt(r8)
            if (r8 < r6) goto L_0x0097
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x0084:
            int r12 = r10 + 1
            char r10 = r2.charAt(r10)
            if (r10 < r6) goto L_0x0094
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r11
            r8 = r8 | r10
            int r11 = r11 + 13
            r10 = r12
            goto L_0x0084
        L_0x0094:
            int r10 = r10 << r11
            r8 = r8 | r10
            r10 = r12
        L_0x0097:
            int r11 = r10 + 1
            char r10 = r2.charAt(r10)
            if (r10 < r6) goto L_0x00b6
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00a3:
            int r13 = r11 + 1
            char r11 = r2.charAt(r11)
            if (r11 < r6) goto L_0x00b3
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r10 = r10 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00a3
        L_0x00b3:
            int r11 = r11 << r12
            r10 = r10 | r11
            r11 = r13
        L_0x00b6:
            int r12 = r11 + 1
            char r11 = r2.charAt(r11)
            if (r11 < r6) goto L_0x00d5
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00c2:
            int r14 = r12 + 1
            char r12 = r2.charAt(r12)
            if (r12 < r6) goto L_0x00d2
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00c2
        L_0x00d2:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
        L_0x00d5:
            int r13 = r12 + 1
            char r12 = r2.charAt(r12)
            if (r12 < r6) goto L_0x00f4
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00e1:
            int r15 = r13 + 1
            char r13 = r2.charAt(r13)
            if (r13 < r6) goto L_0x00f1
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00e1
        L_0x00f1:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x00f4:
            int r14 = r13 + 1
            char r13 = r2.charAt(r13)
            if (r13 < r6) goto L_0x0115
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x0100:
            int r16 = r14 + 1
            char r14 = r2.charAt(r14)
            if (r14 < r6) goto L_0x0111
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x0100
        L_0x0111:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x0115:
            int r15 = r14 + 1
            char r14 = r2.charAt(r14)
            if (r14 < r6) goto L_0x0138
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0121:
            int r17 = r15 + 1
            char r15 = r2.charAt(r15)
            if (r15 < r6) goto L_0x0133
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x0121
        L_0x0133:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
        L_0x0138:
            int r16 = r15 + 1
            char r15 = r2.charAt(r15)
            if (r15 < r6) goto L_0x015d
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r4 = r16
            r16 = 13
        L_0x0146:
            int r17 = r4 + 1
            char r4 = r2.charAt(r4)
            if (r4 < r6) goto L_0x0158
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r16
            r15 = r15 | r4
            int r16 = r16 + 13
            r4 = r17
            goto L_0x0146
        L_0x0158:
            int r4 = r4 << r16
            r15 = r15 | r4
            r16 = r17
        L_0x015d:
            int r4 = r15 + r13
            int r4 = r4 + r14
            int r14 = r5 + r5
            int r14 = r14 + r8
            int[] r8 = new int[r4]
            r4 = r5
            r18 = r8
            r8 = r10
            r10 = r14
            r19 = r15
            r5 = r16
            r14 = r11
        L_0x016f:
            sun.misc.Unsafe r11 = zzb
            java.lang.Object[] r15 = r0.zze()
            com.google.android.gms.internal.atv_ads_framework.zzeo r16 = r0.zza()
            java.lang.Class r9 = r16.getClass()
            int r20 = r19 + r13
            int r13 = r12 + r12
            int r12 = r12 * 3
            int[] r12 = new int[r12]
            java.lang.Object[] r13 = new java.lang.Object[r13]
            r21 = r19
            r22 = r20
            r16 = 0
            r17 = 0
        L_0x018f:
            r7 = 2
            if (r1 != r7) goto L_0x0194
            r7 = 1
            goto L_0x0195
        L_0x0194:
            r7 = 0
        L_0x0195:
            if (r5 >= r3) goto L_0x03b9
            int r24 = r5 + 1
            char r5 = r2.charAt(r5)
            if (r5 < r6) goto L_0x01c4
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            r6 = r24
            r24 = 13
        L_0x01a5:
            int r26 = r6 + 1
            char r6 = r2.charAt(r6)
            r27 = r1
            r1 = 55296(0xd800, float:7.7486E-41)
            if (r6 < r1) goto L_0x01be
            r1 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r1 = r1 << r24
            r5 = r5 | r1
            int r24 = r24 + 13
            r6 = r26
            r1 = r27
            goto L_0x01a5
        L_0x01be:
            int r1 = r6 << r24
            r5 = r5 | r1
            r1 = r26
            goto L_0x01c8
        L_0x01c4:
            r27 = r1
            r1 = r24
        L_0x01c8:
            int r6 = r1 + 1
            char r1 = r2.charAt(r1)
            r24 = r3
            r3 = 55296(0xd800, float:7.7486E-41)
            if (r1 < r3) goto L_0x01f3
            r1 = r1 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
        L_0x01d9:
            int r28 = r6 + 1
            char r6 = r2.charAt(r6)
            if (r6 < r3) goto L_0x01ee
            r3 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r26
            r1 = r1 | r3
            int r26 = r26 + 13
            r6 = r28
            r3 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01d9
        L_0x01ee:
            int r3 = r6 << r26
            r1 = r1 | r3
            r6 = r28
        L_0x01f3:
            r3 = r1 & 1024(0x400, float:1.435E-42)
            if (r3 == 0) goto L_0x01fd
            int r3 = r17 + 1
            r18[r17] = r16
            r17 = r3
        L_0x01fd:
            r3 = r1 & 255(0xff, float:3.57E-43)
            r26 = r14
            r14 = 51
            if (r3 < r14) goto L_0x029f
            int r14 = r6 + 1
            char r6 = r2.charAt(r6)
            r28 = r14
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r6 < r14) goto L_0x0237
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r14 = r28
            r28 = 13
        L_0x0218:
            int r29 = r14 + 1
            char r14 = r2.charAt(r14)
            r30 = r8
            r8 = 55296(0xd800, float:7.7486E-41)
            if (r14 < r8) goto L_0x0231
            r8 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r28
            r6 = r6 | r8
            int r28 = r28 + 13
            r14 = r29
            r8 = r30
            goto L_0x0218
        L_0x0231:
            int r8 = r14 << r28
            r6 = r6 | r8
            r14 = r29
            goto L_0x023b
        L_0x0237:
            r30 = r8
            r14 = r28
        L_0x023b:
            int r8 = r3 + -51
            r28 = r14
            r14 = 9
            if (r8 == r14) goto L_0x025a
            r14 = 17
            if (r8 != r14) goto L_0x0248
            goto L_0x025a
        L_0x0248:
            r14 = 12
            if (r8 != r14) goto L_0x0266
            if (r7 != 0) goto L_0x0266
            int r7 = r16 / 3
            int r7 = r7 + r7
            r8 = 1
            int r7 = r7 + r8
            int r8 = r10 + 1
            r10 = r15[r10]
            r13[r7] = r10
            goto L_0x0265
        L_0x025a:
            int r7 = r16 / 3
            int r7 = r7 + r7
            r8 = 1
            int r7 = r7 + r8
            int r8 = r10 + 1
            r10 = r15[r10]
            r13[r7] = r10
        L_0x0265:
            r10 = r8
        L_0x0266:
            int r6 = r6 + r6
            r7 = r15[r6]
            boolean r8 = r7 instanceof java.lang.reflect.Field
            if (r8 == 0) goto L_0x0270
            java.lang.reflect.Field r7 = (java.lang.reflect.Field) r7
            goto L_0x0278
        L_0x0270:
            java.lang.String r7 = (java.lang.String) r7
            java.lang.reflect.Field r7 = zzt(r9, r7)
            r15[r6] = r7
        L_0x0278:
            long r7 = r11.objectFieldOffset(r7)
            int r7 = (int) r7
            int r6 = r6 + 1
            r8 = r15[r6]
            boolean r14 = r8 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x0288
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x0290
        L_0x0288:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zzt(r9, r8)
            r15[r6] = r8
        L_0x0290:
            r14 = r7
            long r6 = r11.objectFieldOffset(r8)
            int r6 = (int) r6
            r7 = r14
            r23 = r15
            r25 = r28
            r14 = r6
            r6 = 0
            goto L_0x0384
        L_0x029f:
            r30 = r8
            int r8 = r10 + 1
            r10 = r15[r10]
            java.lang.String r10 = (java.lang.String) r10
            java.lang.reflect.Field r10 = zzt(r9, r10)
            r14 = 9
            if (r3 == r14) goto L_0x030f
            r14 = 17
            if (r3 != r14) goto L_0x02b4
            goto L_0x030f
        L_0x02b4:
            r14 = 27
            if (r3 == r14) goto L_0x0301
            r14 = 49
            if (r3 != r14) goto L_0x02bd
            goto L_0x0301
        L_0x02bd:
            r14 = 12
            if (r3 == r14) goto L_0x02f1
            r14 = 30
            if (r3 == r14) goto L_0x02f1
            r14 = 44
            if (r3 != r14) goto L_0x02ca
            goto L_0x02f1
        L_0x02ca:
            r7 = 50
            if (r3 != r7) goto L_0x02ff
            int r7 = r21 + 1
            r18[r21] = r16
            int r14 = r16 / 3
            int r21 = r8 + 1
            r8 = r15[r8]
            int r14 = r14 + r14
            r13[r14] = r8
            r8 = r1 & 2048(0x800, float:2.87E-42)
            if (r8 == 0) goto L_0x02ea
            int r14 = r14 + 1
            int r8 = r21 + 1
            r21 = r15[r21]
            r13[r14] = r21
            r21 = r7
            goto L_0x031a
        L_0x02ea:
            r23 = r15
            r8 = r21
            r21 = r7
            goto L_0x031c
        L_0x02f1:
            if (r7 != 0) goto L_0x02ff
            int r7 = r16 / 3
            int r7 = r7 + r7
            r14 = 1
            int r7 = r7 + r14
            int r23 = r8 + 1
            r8 = r15[r8]
            r13[r7] = r8
            goto L_0x030c
        L_0x02ff:
            r14 = 1
            goto L_0x031a
        L_0x0301:
            r14 = 1
            int r7 = r16 / 3
            int r7 = r7 + r7
            int r7 = r7 + r14
            int r23 = r8 + 1
            r8 = r15[r8]
            r13[r7] = r8
        L_0x030c:
            r8 = r23
            goto L_0x031a
        L_0x030f:
            r14 = 1
            int r7 = r16 / 3
            int r7 = r7 + r7
            int r7 = r7 + r14
            java.lang.Class r23 = r10.getType()
            r13[r7] = r23
        L_0x031a:
            r23 = r15
        L_0x031c:
            long r14 = r11.objectFieldOffset(r10)
            int r7 = (int) r14
            r10 = r1 & 4096(0x1000, float:5.74E-42)
            r14 = 1048575(0xfffff, float:1.469367E-39)
            r15 = 4096(0x1000, float:5.74E-42)
            if (r10 != r15) goto L_0x0372
            r10 = 17
            if (r3 > r10) goto L_0x0372
            int r10 = r6 + 1
            char r6 = r2.charAt(r6)
            r15 = 55296(0xd800, float:7.7486E-41)
            if (r6 < r15) goto L_0x0351
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x033d:
            int r25 = r10 + 1
            char r10 = r2.charAt(r10)
            if (r10 < r15) goto L_0x034e
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r14
            r6 = r6 | r10
            int r14 = r14 + 13
            r10 = r25
            goto L_0x033d
        L_0x034e:
            int r10 = r10 << r14
            r6 = r6 | r10
            goto L_0x0353
        L_0x0351:
            r25 = r10
        L_0x0353:
            int r10 = r4 + r4
            int r14 = r6 / 32
            int r10 = r10 + r14
            r14 = r23[r10]
            boolean r15 = r14 instanceof java.lang.reflect.Field
            if (r15 == 0) goto L_0x0361
            java.lang.reflect.Field r14 = (java.lang.reflect.Field) r14
            goto L_0x0369
        L_0x0361:
            java.lang.String r14 = (java.lang.String) r14
            java.lang.reflect.Field r14 = zzt(r9, r14)
            r23[r10] = r14
        L_0x0369:
            long r14 = r11.objectFieldOffset(r14)
            int r10 = (int) r14
            int r6 = r6 % 32
            r14 = r10
            goto L_0x0375
        L_0x0372:
            r25 = r6
            r6 = 0
        L_0x0375:
            r10 = 18
            if (r3 < r10) goto L_0x0383
            r10 = 49
            if (r3 > r10) goto L_0x0383
            int r10 = r22 + 1
            r18[r22] = r7
            r22 = r10
        L_0x0383:
            r10 = r8
        L_0x0384:
            int r8 = r16 + 1
            r12[r16] = r5
            int r5 = r8 + 1
            r15 = r1 & 512(0x200, float:7.175E-43)
            if (r15 == 0) goto L_0x0391
            r15 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x0392
        L_0x0391:
            r15 = 0
        L_0x0392:
            r1 = r1 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0399
            r1 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x039a
        L_0x0399:
            r1 = 0
        L_0x039a:
            int r3 = r3 << 20
            r1 = r1 | r15
            r1 = r1 | r3
            r1 = r1 | r7
            r12[r8] = r1
            int r16 = r5 + 1
            int r1 = r6 << 20
            r1 = r1 | r14
            r12[r5] = r1
            r15 = r23
            r3 = r24
            r5 = r25
            r14 = r26
            r1 = r27
            r8 = r30
            r6 = 55296(0xd800, float:7.7486E-41)
            goto L_0x018f
        L_0x03b9:
            r30 = r8
            r26 = r14
            com.google.android.gms.internal.atv_ads_framework.zzer r1 = new com.google.android.gms.internal.atv_ads_framework.zzer
            com.google.android.gms.internal.atv_ads_framework.zzeo r15 = r0.zza()
            r17 = 0
            r10 = r1
            r11 = r12
            r12 = r13
            r13 = r30
            r16 = r7
            r21 = r33
            r22 = r34
            r23 = r35
            r24 = r36
            r25 = r37
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
            return r1
        L_0x03da:
            com.google.android.gms.internal.atv_ads_framework.zzfm r0 = (com.google.android.gms.internal.atv_ads_framework.zzfm) r0
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzer.zzi(java.lang.Class, com.google.android.gms.internal.atv_ads_framework.zzel, com.google.android.gms.internal.atv_ads_framework.zzet, com.google.android.gms.internal.atv_ads_framework.zzec, com.google.android.gms.internal.atv_ads_framework.zzfp, com.google.android.gms.internal.atv_ads_framework.zzcy, com.google.android.gms.internal.atv_ads_framework.zzej):com.google.android.gms.internal.atv_ads_framework.zzer");
    }

    private static double zzj(Object obj, long j) {
        return ((Double) zzfz.zzf(obj, j)).doubleValue();
    }

    private static float zzk(Object obj, long j) {
        return ((Float) zzfz.zzf(obj, j)).floatValue();
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
    private final int zzl(java.lang.Object r16) {
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
            int r9 = r15.zzp(r5)
            int[] r10 = r0.zzc
            r11 = r10[r5]
            int r12 = zzo(r9)
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
            boolean r9 = r15.zzE(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.atv_ads_framework.zzeo r3 = (com.google.android.gms.internal.atv_ads_framework.zzeo) r3
            com.google.android.gms.internal.atv_ads_framework.zzey r4 = r15.zzr(r5)
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzt(r11, r3, r4)
            goto L_0x03a0
        L_0x0055:
            boolean r10 = r15.zzE(r1, r11, r5)
            if (r10 == 0) goto L_0x03a1
            long r3 = zzq(r1, r3)
            int r10 = r11 << 3
            long r11 = r3 + r3
            long r3 = r3 >> r9
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r10)
            long r3 = r3 ^ r11
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r3)
            int r9 = r9 + r3
            int r6 = r6 + r9
            goto L_0x03a1
        L_0x0071:
            boolean r9 = r15.zzE(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            int r3 = zzm(r1, r3)
            int r4 = r11 << 3
            int r9 = r3 + r3
            int r3 = r3 >> 31
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            r3 = r3 ^ r9
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            goto L_0x032d
        L_0x008c:
            boolean r3 = r15.zzE(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            goto L_0x01bf
        L_0x009a:
            boolean r3 = r15.zzE(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            goto L_0x01af
        L_0x00a8:
            boolean r9 = r15.zzE(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            int r3 = zzm(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzu(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032d
        L_0x00be:
            boolean r9 = r15.zzE(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            int r3 = zzm(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032d
        L_0x00d4:
            boolean r9 = r15.zzE(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.atv_ads_framework.zzcn r3 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r3
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzb
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r9 = r9 + r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
        L_0x00f1:
            int r3 = r3 + r9
            goto L_0x03a0
        L_0x00f4:
            boolean r9 = r15.zzE(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.atv_ads_framework.zzey r4 = r15.zzr(r5)
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzn(r11, r3, r4)
            goto L_0x03a0
        L_0x0108:
            boolean r9 = r15.zzE(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            java.lang.Object r3 = r2.getObject(r1, r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.atv_ads_framework.zzcn
            if (r4 == 0) goto L_0x012a
            com.google.android.gms.internal.atv_ads_framework.zzcn r3 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r3
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzb
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r9 = r9 + r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x00f1
        L_0x012a:
            java.lang.String r3 = (java.lang.String) r3
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzw(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032d
        L_0x0138:
            boolean r3 = r15.zzE(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r3 = r3 + r14
            goto L_0x03a0
        L_0x0147:
            boolean r3 = r15.zzE(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            goto L_0x01af
        L_0x0154:
            boolean r3 = r15.zzE(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            goto L_0x01bf
        L_0x0161:
            boolean r9 = r15.zzE(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            int r3 = zzm(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzu(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032d
        L_0x0177:
            boolean r9 = r15.zzE(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            long r3 = zzq(r1, r3)
            int r9 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r9)
            goto L_0x032d
        L_0x018d:
            boolean r9 = r15.zzE(r1, r11, r5)
            if (r9 == 0) goto L_0x03a1
            long r3 = zzq(r1, r3)
            int r9 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r9)
            goto L_0x032d
        L_0x01a3:
            boolean r3 = r15.zzE(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
        L_0x01af:
            int r3 = r3 + 4
            goto L_0x03a0
        L_0x01b3:
            boolean r3 = r15.zzE(r1, r11, r5)
            if (r3 == 0) goto L_0x03a1
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
        L_0x01bf:
            int r3 = r3 + 8
            goto L_0x03a0
        L_0x01c3:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.lang.Object r4 = r15.zzs(r5)
            com.google.android.gms.internal.atv_ads_framework.zzej.zza(r11, r3, r4)
            goto L_0x03a1
        L_0x01d0:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.atv_ads_framework.zzey r4 = r15.zzr(r5)
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzi(r11, r3, r4)
            goto L_0x03a0
        L_0x01e0:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzs(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x01f8:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzq(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x0210:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzh(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x0228:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzf(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x0240:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzd(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x0258:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzv(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x0270:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zza
            int r3 = r3.size()
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x028a:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzf(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x02a2:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzh(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x02ba:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzk(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x02d1:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzx(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x02e8:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzm(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x02ff:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzf(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x032c
        L_0x0316:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzh(r3)
            if (r3 <= 0) goto L_0x03a1
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
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
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzr(r11, r3, r9)
            goto L_0x036c
        L_0x033d:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzp(r11, r3, r9)
            goto L_0x036c
        L_0x0349:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzg(r11, r3, r9)
            goto L_0x036c
        L_0x0355:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zze(r11, r3, r9)
            goto L_0x036c
        L_0x0361:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzc(r11, r3, r9)
        L_0x036c:
            int r6 = r6 + r3
            r12 = r9
            goto L_0x0574
        L_0x0370:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzu(r11, r3, r9)
            goto L_0x03a0
        L_0x037c:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzb(r11, r3)
            goto L_0x03a0
        L_0x0387:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.atv_ads_framework.zzey r4 = r15.zzr(r5)
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzo(r11, r3, r4)
            goto L_0x03a0
        L_0x0396:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzt(r11, r3)
        L_0x03a0:
            int r6 = r6 + r3
        L_0x03a1:
            r12 = 0
            goto L_0x0574
        L_0x03a4:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            r12 = 0
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zza(r11, r3, r12)
            goto L_0x0403
        L_0x03b0:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zze(r11, r3, r12)
            goto L_0x0403
        L_0x03bc:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzg(r11, r3, r12)
            goto L_0x0403
        L_0x03c8:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzj(r11, r3, r12)
            goto L_0x0403
        L_0x03d4:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzw(r11, r3, r12)
            goto L_0x0403
        L_0x03e0:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzl(r11, r3, r12)
            goto L_0x0403
        L_0x03ec:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zze(r11, r3, r12)
            goto L_0x0403
        L_0x03f8:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzg(r11, r3, r12)
        L_0x0403:
            int r6 = r6 + r3
            goto L_0x0574
        L_0x0406:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.atv_ads_framework.zzeo r3 = (com.google.android.gms.internal.atv_ads_framework.zzeo) r3
            com.google.android.gms.internal.atv_ads_framework.zzey r4 = r15.zzr(r5)
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzt(r11, r3, r4)
            goto L_0x0403
        L_0x041a:
            r12 = 0
            r10 = r10 & r8
            if (r10 == 0) goto L_0x0574
            long r3 = r2.getLong(r1, r3)
            int r10 = r11 << 3
            long r13 = r3 + r3
            long r3 = r3 >> r9
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r10)
            long r3 = r3 ^ r13
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r3)
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
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            r3 = r3 ^ r9
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            goto L_0x0553
        L_0x044e:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            goto L_0x0570
        L_0x045b:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            goto L_0x0561
        L_0x0468:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzu(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x0553
        L_0x047d:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x0553
        L_0x0492:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.atv_ads_framework.zzcn r3 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r3
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzb
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r9 = r9 + r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
        L_0x04ae:
            int r3 = r3 + r9
            goto L_0x0403
        L_0x04b1:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.atv_ads_framework.zzey r4 = r15.zzr(r5)
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzn(r11, r3, r4)
            goto L_0x0403
        L_0x04c4:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            java.lang.Object r3 = r2.getObject(r1, r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.atv_ads_framework.zzcn
            if (r4 == 0) goto L_0x04e5
            com.google.android.gms.internal.atv_ads_framework.zzcn r3 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r3
            int r4 = r11 << 3
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzb
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r9 = r9 + r3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x04ae
        L_0x04e5:
            java.lang.String r3 = (java.lang.String) r3
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzw(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x0553
        L_0x04f2:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            int r3 = r3 + r14
            goto L_0x0403
        L_0x0500:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            goto L_0x0561
        L_0x050c:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
            goto L_0x0570
        L_0x0518:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzu(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x0553
        L_0x052c:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            long r3 = r2.getLong(r1, r3)
            int r9 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r9)
            goto L_0x0553
        L_0x0540:
            r12 = 0
            r9 = r8 & r10
            if (r9 == 0) goto L_0x0574
            long r3 = r2.getLong(r1, r3)
            int r9 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r3)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r9)
        L_0x0553:
            int r4 = r4 + r3
            int r6 = r6 + r4
            goto L_0x0574
        L_0x0556:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
        L_0x0561:
            int r3 = r3 + 4
            goto L_0x0403
        L_0x0565:
            r12 = 0
            r3 = r8 & r10
            if (r3 == 0) goto L_0x0574
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r3)
        L_0x0570:
            int r3 = r3 + 8
            goto L_0x0403
        L_0x0574:
            int r5 = r5 + 3
            r4 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x000c
        L_0x057b:
            com.google.android.gms.internal.atv_ads_framework.zzfp r2 = r0.zzk
            java.lang.Object r3 = r2.zzc(r1)
            int r2 = r2.zza(r3)
            int r6 = r6 + r2
            boolean r2 = r0.zzf
            if (r2 != 0) goto L_0x058b
            return r6
        L_0x058b:
            com.google.android.gms.internal.atv_ads_framework.zzcy r2 = r0.zzl
            r2.zza(r1)
            r1 = 0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzer.zzl(java.lang.Object):int");
    }

    private static int zzm(Object obj, long j) {
        return ((Integer) zzfz.zzf(obj, j)).intValue();
    }

    private final int zzn(int i) {
        return this.zzc[i + 2];
    }

    private static int zzo(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzp(int i) {
        return this.zzc[i + 1];
    }

    private static long zzq(Object obj, long j) {
        return ((Long) zzfz.zzf(obj, j)).longValue();
    }

    private final zzey zzr(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzey zzey = (zzey) this.zzd[i3];
        if (zzey != null) {
            return zzey;
        }
        zzey zzb2 = zzew.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzs(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzt(Class cls, String str) {
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

    private final void zzu(Object obj, Object obj2, int i) {
        if (zzA(obj2, i)) {
            Unsafe unsafe = zzb;
            long zzp = (long) (zzp(i) & 1048575);
            Object object = unsafe.getObject(obj2, zzp);
            if (object != null) {
                zzey zzr = zzr(i);
                if (!zzA(obj, i)) {
                    if (!zzD(object)) {
                        unsafe.putObject(obj, zzp, object);
                    } else {
                        Object zzc2 = zzr.zzc();
                        zzr.zze(zzc2, object);
                        unsafe.putObject(obj, zzp, zzc2);
                    }
                    zzw(obj, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzp);
                if (!zzD(object2)) {
                    Object zzc3 = zzr.zzc();
                    zzr.zze(zzc3, object2);
                    unsafe.putObject(obj, zzp, zzc3);
                    object2 = zzc3;
                }
                zzr.zze(object2, object);
                return;
            }
            int i2 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i2 + " is present but null: " + obj3);
        }
    }

    private final void zzv(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzE(obj2, i2, i)) {
            Unsafe unsafe = zzb;
            long zzp = (long) (zzp(i) & 1048575);
            Object object = unsafe.getObject(obj2, zzp);
            if (object != null) {
                zzey zzr = zzr(i);
                if (!zzE(obj, i2, i)) {
                    if (!zzD(object)) {
                        unsafe.putObject(obj, zzp, object);
                    } else {
                        Object zzc2 = zzr.zzc();
                        zzr.zze(zzc2, object);
                        unsafe.putObject(obj, zzp, zzc2);
                    }
                    zzx(obj, i2, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, zzp);
                if (!zzD(object2)) {
                    Object zzc3 = zzr.zzc();
                    zzr.zze(zzc3, object2);
                    unsafe.putObject(obj, zzp, zzc3);
                    object2 = zzc3;
                }
                zzr.zze(object2, object);
                return;
            }
            int i3 = this.zzc[i];
            String obj3 = obj2.toString();
            throw new IllegalStateException("Source subfield " + i3 + " is present but null: " + obj3);
        }
    }

    private final void zzw(Object obj, int i) {
        int zzn2 = zzn(i);
        long j = (long) (1048575 & zzn2);
        if (j != 1048575) {
            zzfz.zzq(obj, j, (1 << (zzn2 >>> 20)) | zzfz.zzc(obj, j));
        }
    }

    private final void zzx(Object obj, int i, int i2) {
        zzfz.zzq(obj, (long) (zzn(i2) & 1048575), i);
    }

    private final void zzy(zzgg zzgg, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzeh zzeh = (zzeh) zzs(i2);
            throw null;
        }
    }

    private final boolean zzz(Object obj, Object obj2, int i) {
        return zzA(obj, i) == zzA(obj2, i);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0326, code lost:
        r5 = r5 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0420, code lost:
        r4 = r4 + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x04a2, code lost:
        r4 = r4 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x04f4, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x055f, code lost:
        r4 = r4 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x056f, code lost:
        r4 = r4 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x0573, code lost:
        r2 = r2 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(java.lang.Object r12) {
        /*
            r11 = this;
            boolean r0 = r11.zzg
            if (r0 == 0) goto L_0x0583
            sun.misc.Unsafe r0 = zzb
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x0009:
            int[] r4 = r11.zzc
            int r4 = r4.length
            if (r2 >= r4) goto L_0x0577
            int r4 = r11.zzp(r2)
            int r5 = zzo(r4)
            int[] r6 = r11.zzc
            r6 = r6[r2]
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r7
            com.google.android.gms.internal.atv_ads_framework.zzdd r7 = com.google.android.gms.internal.atv_ads_framework.zzdd.DOUBLE_LIST_PACKED
            int r7 = r7.zza()
            if (r5 < r7) goto L_0x0034
            com.google.android.gms.internal.atv_ads_framework.zzdd r7 = com.google.android.gms.internal.atv_ads_framework.zzdd.SINT64_LIST_PACKED
            int r7 = r7.zza()
            if (r5 > r7) goto L_0x0034
            int[] r7 = r11.zzc
            int r8 = r2 + 2
            r7 = r7[r8]
        L_0x0034:
            long r7 = (long) r4
            r4 = 63
            switch(r5) {
                case 0: goto L_0x0563;
                case 1: goto L_0x0553;
                case 2: goto L_0x053c;
                case 3: goto L_0x0527;
                case 4: goto L_0x0512;
                case 5: goto L_0x0505;
                case 6: goto L_0x04f8;
                case 7: goto L_0x04e8;
                case 8: goto L_0x04b9;
                case 9: goto L_0x04a5;
                case 10: goto L_0x0485;
                case 11: goto L_0x046f;
                case 12: goto L_0x0459;
                case 13: goto L_0x044b;
                case 14: goto L_0x043d;
                case 15: goto L_0x0422;
                case 16: goto L_0x0406;
                case 17: goto L_0x03f1;
                case 18: goto L_0x03e4;
                case 19: goto L_0x03d9;
                case 20: goto L_0x03ce;
                case 21: goto L_0x03c3;
                case 22: goto L_0x03b8;
                case 23: goto L_0x03ad;
                case 24: goto L_0x03a2;
                case 25: goto L_0x0397;
                case 26: goto L_0x038c;
                case 27: goto L_0x037d;
                case 28: goto L_0x0371;
                case 29: goto L_0x0365;
                case 30: goto L_0x0359;
                case 31: goto L_0x034d;
                case 32: goto L_0x0341;
                case 33: goto L_0x0335;
                case 34: goto L_0x0329;
                case 35: goto L_0x0310;
                case 36: goto L_0x02f9;
                case 37: goto L_0x02e2;
                case 38: goto L_0x02cb;
                case 39: goto L_0x02b4;
                case 40: goto L_0x029c;
                case 41: goto L_0x0284;
                case 42: goto L_0x026a;
                case 43: goto L_0x0252;
                case 44: goto L_0x023a;
                case 45: goto L_0x0222;
                case 46: goto L_0x020a;
                case 47: goto L_0x01f2;
                case 48: goto L_0x01da;
                case 49: goto L_0x01ca;
                case 50: goto L_0x01bd;
                case 51: goto L_0x01af;
                case 52: goto L_0x01a1;
                case 53: goto L_0x018b;
                case 54: goto L_0x0175;
                case 55: goto L_0x015f;
                case 56: goto L_0x0151;
                case 57: goto L_0x0143;
                case 58: goto L_0x0135;
                case 59: goto L_0x0104;
                case 60: goto L_0x00f0;
                case 61: goto L_0x00d1;
                case 62: goto L_0x00bb;
                case 63: goto L_0x00a5;
                case 64: goto L_0x0097;
                case 65: goto L_0x0089;
                case 66: goto L_0x006e;
                case 67: goto L_0x0052;
                case 68: goto L_0x003c;
                default: goto L_0x003a;
            }
        L_0x003a:
            goto L_0x0573
        L_0x003c:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            com.google.android.gms.internal.atv_ads_framework.zzeo r4 = (com.google.android.gms.internal.atv_ads_framework.zzeo) r4
            com.google.android.gms.internal.atv_ads_framework.zzey r5 = r11.zzr(r2)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzt(r6, r4, r5)
            goto L_0x03ee
        L_0x0052:
            boolean r5 = r11.zzE(r12, r6, r2)
            if (r5 == 0) goto L_0x0573
            long r7 = zzq(r12, r7)
            int r5 = r6 << 3
            long r9 = r7 + r7
            long r6 = r7 >> r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            long r5 = r9 ^ r6
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r5)
            goto L_0x0420
        L_0x006e:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = zzm(r12, r7)
            int r5 = r6 << 3
            int r6 = r4 + r4
            int r4 = r4 >> 31
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            r4 = r4 ^ r6
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x0550
        L_0x0089:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x056f
        L_0x0097:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x055f
        L_0x00a5:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = zzm(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzu(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0550
        L_0x00bb:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = zzm(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0550
        L_0x00d1:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            com.google.android.gms.internal.atv_ads_framework.zzcn r4 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r4
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzb
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r6 = r6 + r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x04a2
        L_0x00f0:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            com.google.android.gms.internal.atv_ads_framework.zzey r5 = r11.zzr(r2)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzn(r6, r4, r5)
            goto L_0x03ee
        L_0x0104:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            boolean r5 = r4 instanceof com.google.android.gms.internal.atv_ads_framework.zzcn
            if (r5 == 0) goto L_0x0127
            com.google.android.gms.internal.atv_ads_framework.zzcn r4 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r4
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzb
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r6 = r6 + r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x04a2
        L_0x0127:
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzw(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0550
        L_0x0135:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x04f4
        L_0x0143:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x055f
        L_0x0151:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x056f
        L_0x015f:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = zzm(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzu(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0550
        L_0x0175:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            long r4 = zzq(r12, r7)
            int r6 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r6)
            goto L_0x0550
        L_0x018b:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            long r4 = zzq(r12, r7)
            int r6 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r6)
            goto L_0x0550
        L_0x01a1:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x055f
        L_0x01af:
            boolean r4 = r11.zzE(r12, r6, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x056f
        L_0x01bd:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.lang.Object r5 = r11.zzs(r2)
            com.google.android.gms.internal.atv_ads_framework.zzej.zza(r6, r4, r5)
            goto L_0x0573
        L_0x01ca:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.atv_ads_framework.zzey r5 = r11.zzr(r2)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzi(r6, r4, r5)
            goto L_0x03ee
        L_0x01da:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzs(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x01f2:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzq(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x020a:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzh(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x0222:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzf(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x023a:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzd(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x0252:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzv(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x026a:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzfa.zza
            int r4 = r4.size()
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x0284:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzf(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x029c:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzh(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x02b4:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzk(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x02cb:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzx(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x02e2:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzm(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x02f9:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzf(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0326
        L_0x0310:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzh(r4)
            if (r4 <= 0) goto L_0x0573
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
        L_0x0326:
            int r5 = r5 + r6
            goto L_0x0550
        L_0x0329:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzr(r6, r4, r1)
            goto L_0x03ee
        L_0x0335:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzp(r6, r4, r1)
            goto L_0x03ee
        L_0x0341:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzg(r6, r4, r1)
            goto L_0x03ee
        L_0x034d:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zze(r6, r4, r1)
            goto L_0x03ee
        L_0x0359:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzc(r6, r4, r1)
            goto L_0x03ee
        L_0x0365:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzu(r6, r4, r1)
            goto L_0x03ee
        L_0x0371:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzb(r6, r4)
            goto L_0x03ee
        L_0x037d:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.atv_ads_framework.zzey r5 = r11.zzr(r2)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzo(r6, r4, r5)
            goto L_0x03ee
        L_0x038c:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzt(r6, r4)
            goto L_0x03ee
        L_0x0397:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zza(r6, r4, r1)
            goto L_0x03ee
        L_0x03a2:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zze(r6, r4, r1)
            goto L_0x03ee
        L_0x03ad:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzg(r6, r4, r1)
            goto L_0x03ee
        L_0x03b8:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzj(r6, r4, r1)
            goto L_0x03ee
        L_0x03c3:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzw(r6, r4, r1)
            goto L_0x03ee
        L_0x03ce:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzl(r6, r4, r1)
            goto L_0x03ee
        L_0x03d9:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zze(r6, r4, r1)
            goto L_0x03ee
        L_0x03e4:
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzg(r6, r4, r1)
        L_0x03ee:
            int r3 = r3 + r4
            goto L_0x0573
        L_0x03f1:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            com.google.android.gms.internal.atv_ads_framework.zzeo r4 = (com.google.android.gms.internal.atv_ads_framework.zzeo) r4
            com.google.android.gms.internal.atv_ads_framework.zzey r5 = r11.zzr(r2)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzt(r6, r4, r5)
            goto L_0x03ee
        L_0x0406:
            boolean r5 = r11.zzA(r12, r2)
            if (r5 == 0) goto L_0x0573
            long r7 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r12, r7)
            int r5 = r6 << 3
            long r9 = r7 + r7
            long r6 = r7 >> r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            long r5 = r9 ^ r6
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r5)
        L_0x0420:
            int r4 = r4 + r5
            goto L_0x03ee
        L_0x0422:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r12, r7)
            int r5 = r6 << 3
            int r6 = r4 + r4
            int r4 = r4 >> 31
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            r4 = r4 ^ r6
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x0550
        L_0x043d:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x056f
        L_0x044b:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x055f
        L_0x0459:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzu(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0550
        L_0x046f:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0550
        L_0x0485:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            com.google.android.gms.internal.atv_ads_framework.zzcn r4 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r4
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzb
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r6 = r6 + r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
        L_0x04a2:
            int r4 = r4 + r6
            goto L_0x03ee
        L_0x04a5:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            com.google.android.gms.internal.atv_ads_framework.zzey r5 = r11.zzr(r2)
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfa.zzn(r6, r4, r5)
            goto L_0x03ee
        L_0x04b9:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            java.lang.Object r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r12, r7)
            boolean r5 = r4 instanceof com.google.android.gms.internal.atv_ads_framework.zzcn
            if (r5 == 0) goto L_0x04db
            com.google.android.gms.internal.atv_ads_framework.zzcn r4 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r4
            int r5 = r6 << 3
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzb
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            int r6 = r6 + r4
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x04a2
        L_0x04db:
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzw(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0550
        L_0x04e8:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
        L_0x04f4:
            int r4 = r4 + 1
            goto L_0x03ee
        L_0x04f8:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x055f
        L_0x0505:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
            goto L_0x056f
        L_0x0512:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r12, r7)
            int r5 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzu(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r5)
            goto L_0x0550
        L_0x0527:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            long r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r12, r7)
            int r6 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r6)
            goto L_0x0550
        L_0x053c:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            long r4 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r12, r7)
            int r6 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzy(r4)
            int r5 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r6)
        L_0x0550:
            int r5 = r5 + r4
            int r3 = r3 + r5
            goto L_0x0573
        L_0x0553:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
        L_0x055f:
            int r4 = r4 + 4
            goto L_0x03ee
        L_0x0563:
            boolean r4 = r11.zzA(r12, r2)
            if (r4 == 0) goto L_0x0573
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.atv_ads_framework.zzcv.zzx(r4)
        L_0x056f:
            int r4 = r4 + 8
            goto L_0x03ee
        L_0x0573:
            int r2 = r2 + 3
            goto L_0x0009
        L_0x0577:
            com.google.android.gms.internal.atv_ads_framework.zzfp r0 = r11.zzk
            java.lang.Object r12 = r0.zzc(r12)
            int r12 = r0.zza(r12)
            int r3 = r3 + r12
            goto L_0x0587
        L_0x0583:
            int r3 = r11.zzl(r12)
        L_0x0587:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzer.zza(java.lang.Object):int");
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
            int r3 = r9.zzp(r1)
            int[] r4 = r9.zzc
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            int r3 = zzo(r3)
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
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            java.lang.Object r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r10, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x0033:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            long r3 = zzq(r10, r5)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x0043:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzm(r10, r5)
            goto L_0x0214
        L_0x0051:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            long r3 = zzq(r10, r5)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x0061:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzm(r10, r5)
            goto L_0x0214
        L_0x006f:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzm(r10, r5)
            goto L_0x0214
        L_0x007d:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzm(r10, r5)
            goto L_0x0214
        L_0x008b:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x009d:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            java.lang.Object r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r10, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x00af:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r10, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x00c3:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            boolean r3 = zzF(r10, r5)
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzdp.zza(r3)
            goto L_0x0214
        L_0x00d5:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzm(r10, r5)
            goto L_0x0214
        L_0x00e3:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            long r3 = zzq(r10, r5)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x00f3:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            int r3 = zzm(r10, r5)
            goto L_0x0214
        L_0x0101:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            long r3 = zzq(r10, r5)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x0111:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            long r3 = zzq(r10, r5)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x0121:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            float r3 = zzk(r10, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0214
        L_0x0133:
            boolean r3 = r9.zzE(r10, r4, r1)
            if (r3 == 0) goto L_0x0215
            int r2 = r2 * 53
            double r3 = zzj(r10, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x0147:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x0153:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x015f:
            java.lang.Object r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r10, r5)
            if (r3 == 0) goto L_0x01b4
            int r7 = r3.hashCode()
            goto L_0x01b4
        L_0x016a:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x0174:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r10, r5)
            goto L_0x0214
        L_0x017c:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x0186:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r10, r5)
            goto L_0x0214
        L_0x018e:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r10, r5)
            goto L_0x0214
        L_0x0196:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r10, r5)
            goto L_0x0214
        L_0x019e:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r10, r5)
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x01aa:
            java.lang.Object r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r10, r5)
            if (r3 == 0) goto L_0x01b4
            int r7 = r3.hashCode()
        L_0x01b4:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0215
        L_0x01b8:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r10, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0214
        L_0x01c5:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzw(r10, r5)
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzdp.zza(r3)
            goto L_0x0214
        L_0x01d0:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r10, r5)
            goto L_0x0214
        L_0x01d7:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x01e0:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r10, r5)
            goto L_0x0214
        L_0x01e7:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x01f0:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r10, r5)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
            goto L_0x0210
        L_0x01f9:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzb(r10, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0214
        L_0x0204:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.atv_ads_framework.zzfz.zza(r10, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            byte[] r5 = com.google.android.gms.internal.atv_ads_framework.zzdp.zzd
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
            com.google.android.gms.internal.atv_ads_framework.zzfp r0 = r9.zzk
            java.lang.Object r0 = r0.zzc(r10)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r9.zzf
            if (r0 != 0) goto L_0x022b
            return r2
        L_0x022b:
            com.google.android.gms.internal.atv_ads_framework.zzcy r0 = r9.zzl
            r0.zza(r10)
            r10 = 0
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzer.zzb(java.lang.Object):int");
    }

    public final Object zzc() {
        return ((zzdh) this.zze).zzq();
    }

    public final void zzd(Object obj) {
        if (zzD(obj)) {
            if (obj instanceof zzdh) {
                zzdh zzdh = (zzdh) obj;
                zzdh.zzy(Integer.MAX_VALUE);
                zzdh.zza = 0;
                zzdh.zzw();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzp = zzp(i);
                int i2 = 1048575 & zzp;
                int zzo = zzo(zzp);
                long j = (long) i2;
                if (zzo != 9) {
                    if (zzo == 60 || zzo == 68) {
                        if (zzE(obj, this.zzc[i], i)) {
                            zzr(i).zzd(zzb.getObject(obj, j));
                        }
                    } else {
                        switch (zzo) {
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
                                this.zzj.zza(obj, j);
                                continue;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzei) object).zzb();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    }
                }
                if (zzA(obj, i)) {
                    zzr(i).zzd(zzb.getObject(obj, j));
                }
            }
            this.zzk.zze(obj);
            if (this.zzf) {
                this.zzl.zzb(obj);
            }
        }
    }

    public final void zze(Object obj, Object obj2) {
        if (zzD(obj)) {
            obj2.getClass();
            for (int i = 0; i < this.zzc.length; i += 3) {
                int zzp = zzp(i);
                int i2 = this.zzc[i];
                long j = (long) (1048575 & zzp);
                switch (zzo(zzp)) {
                    case 0:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzo(obj, j, zzfz.zza(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 1:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzp(obj, j, zzfz.zzb(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 2:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzr(obj, j, zzfz.zzd(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 3:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzr(obj, j, zzfz.zzd(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 4:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzq(obj, j, zzfz.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 5:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzr(obj, j, zzfz.zzd(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 6:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzq(obj, j, zzfz.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 7:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzm(obj, j, zzfz.zzw(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 8:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzs(obj, j, zzfz.zzf(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 9:
                        zzu(obj, obj2, i);
                        break;
                    case 10:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzs(obj, j, zzfz.zzf(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 11:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzq(obj, j, zzfz.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 12:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzq(obj, j, zzfz.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 13:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzq(obj, j, zzfz.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 14:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzr(obj, j, zzfz.zzd(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 15:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzq(obj, j, zzfz.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 16:
                        if (!zzA(obj2, i)) {
                            break;
                        } else {
                            zzfz.zzr(obj, j, zzfz.zzd(obj2, j));
                            zzw(obj, i);
                            break;
                        }
                    case 17:
                        zzu(obj, obj2, i);
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
                        this.zzj.zzb(obj, obj2, j);
                        break;
                    case 50:
                        int i3 = zzfa.zza;
                        zzei zzei = (zzei) zzfz.zzf(obj, j);
                        zzei zzei2 = (zzei) zzfz.zzf(obj2, j);
                        if (!zzei2.isEmpty()) {
                            if (!zzei.zzd()) {
                                zzei = zzei.zza();
                            }
                            zzei.zzc(zzei2);
                        }
                        zzfz.zzs(obj, j, zzei);
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
                        if (!zzE(obj2, i2, i)) {
                            break;
                        } else {
                            zzfz.zzs(obj, j, zzfz.zzf(obj2, j));
                            zzx(obj, i2, i);
                            break;
                        }
                    case 60:
                        zzv(obj, obj2, i);
                        break;
                    case LockFreeTaskQueueCore.CLOSED_SHIFT:
                    case Elf64.Ehdr.E_SHSTRNDX:
                    case HtmlCompat.FROM_HTML_MODE_COMPACT:
                    case 64:
                    case 65:
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                    case 67:
                        if (!zzE(obj2, i2, i)) {
                            break;
                        } else {
                            zzfz.zzs(obj, j, zzfz.zzf(obj2, j));
                            zzx(obj, i2, i);
                            break;
                        }
                    case 68:
                        zzv(obj, obj2, i);
                        break;
                }
            }
            zzfa.zzA(this.zzk, obj, obj2);
            if (this.zzf) {
                this.zzl.zza(obj2);
                throw null;
            }
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
    }

    public final boolean zzg(Object obj, Object obj2) {
        boolean z;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzp = zzp(i);
            long j = (long) (zzp & 1048575);
            switch (zzo(zzp)) {
                case 0:
                    if (zzz(obj, obj2, i) && Double.doubleToLongBits(zzfz.zza(obj, j)) == Double.doubleToLongBits(zzfz.zza(obj2, j))) {
                        continue;
                    }
                case 1:
                    if (zzz(obj, obj2, i) && Float.floatToIntBits(zzfz.zzb(obj, j)) == Float.floatToIntBits(zzfz.zzb(obj2, j))) {
                        continue;
                    }
                case 2:
                    if (zzz(obj, obj2, i) && zzfz.zzd(obj, j) == zzfz.zzd(obj2, j)) {
                        continue;
                    }
                case 3:
                    if (zzz(obj, obj2, i) && zzfz.zzd(obj, j) == zzfz.zzd(obj2, j)) {
                        continue;
                    }
                case 4:
                    if (zzz(obj, obj2, i) && zzfz.zzc(obj, j) == zzfz.zzc(obj2, j)) {
                        continue;
                    }
                case 5:
                    if (zzz(obj, obj2, i) && zzfz.zzd(obj, j) == zzfz.zzd(obj2, j)) {
                        continue;
                    }
                case 6:
                    if (zzz(obj, obj2, i) && zzfz.zzc(obj, j) == zzfz.zzc(obj2, j)) {
                        continue;
                    }
                case 7:
                    if (zzz(obj, obj2, i) && zzfz.zzw(obj, j) == zzfz.zzw(obj2, j)) {
                        continue;
                    }
                case 8:
                    if (zzz(obj, obj2, i) && zzfa.zzU(zzfz.zzf(obj, j), zzfz.zzf(obj2, j))) {
                        continue;
                    }
                case 9:
                    if (zzz(obj, obj2, i) && zzfa.zzU(zzfz.zzf(obj, j), zzfz.zzf(obj2, j))) {
                        continue;
                    }
                case 10:
                    if (zzz(obj, obj2, i) && zzfa.zzU(zzfz.zzf(obj, j), zzfz.zzf(obj2, j))) {
                        continue;
                    }
                case 11:
                    if (zzz(obj, obj2, i) && zzfz.zzc(obj, j) == zzfz.zzc(obj2, j)) {
                        continue;
                    }
                case 12:
                    if (zzz(obj, obj2, i) && zzfz.zzc(obj, j) == zzfz.zzc(obj2, j)) {
                        continue;
                    }
                case 13:
                    if (zzz(obj, obj2, i) && zzfz.zzc(obj, j) == zzfz.zzc(obj2, j)) {
                        continue;
                    }
                case 14:
                    if (zzz(obj, obj2, i) && zzfz.zzd(obj, j) == zzfz.zzd(obj2, j)) {
                        continue;
                    }
                case 15:
                    if (zzz(obj, obj2, i) && zzfz.zzc(obj, j) == zzfz.zzc(obj2, j)) {
                        continue;
                    }
                case 16:
                    if (zzz(obj, obj2, i) && zzfz.zzd(obj, j) == zzfz.zzd(obj2, j)) {
                        continue;
                    }
                case 17:
                    if (zzz(obj, obj2, i) && zzfa.zzU(zzfz.zzf(obj, j), zzfz.zzf(obj2, j))) {
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
                    z = zzfa.zzU(zzfz.zzf(obj, j), zzfz.zzf(obj2, j));
                    break;
                case 50:
                    z = zzfa.zzU(zzfz.zzf(obj, j), zzfz.zzf(obj2, j));
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
                    long zzn2 = (long) (zzn(i) & 1048575);
                    if (zzfz.zzc(obj, zzn2) == zzfz.zzc(obj2, zzn2) && zzfa.zzU(zzfz.zzf(obj, j), zzfz.zzf(obj2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzk.zzc(obj).equals(this.zzk.zzc(obj2))) {
            return false;
        }
        if (!this.zzf) {
            return true;
        }
        this.zzl.zza(obj);
        this.zzl.zza(obj2);
        throw null;
    }

    public final boolean zzh(Object obj) {
        int i;
        int i2;
        Object obj2 = obj;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i4 < this.zzi) {
            int i6 = this.zzh[i4];
            int i7 = this.zzc[i6];
            int zzp = zzp(i6);
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
            if ((268435456 & zzp) != 0 && !zzB(obj, i6, i2, i, i10)) {
                return false;
            }
            int zzo = zzo(zzp);
            if (zzo != 9 && zzo != 17) {
                if (zzo != 27) {
                    if (zzo == 60 || zzo == 68) {
                        if (zzE(obj2, i7, i6) && !zzC(obj2, zzp, zzr(i6))) {
                            return false;
                        }
                    } else if (zzo != 49) {
                        if (zzo == 50 && !((zzei) zzfz.zzf(obj2, (long) (zzp & 1048575))).isEmpty()) {
                            zzeh zzeh = (zzeh) zzs(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzfz.zzf(obj2, (long) (zzp & 1048575));
                if (!list.isEmpty()) {
                    zzey zzr = zzr(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzr.zzh(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (zzB(obj, i6, i2, i, i10) && !zzC(obj2, zzp, zzr(i6))) {
                return false;
            }
            i4++;
            i5 = i2;
            i3 = i;
        }
        if (!this.zzf) {
            return true;
        }
        this.zzl.zza(obj2);
        throw null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0499, code lost:
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x06f3, code lost:
        r14 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x08a4, code lost:
        r8 = r8 + 3;
        r6 = r14;
        r7 = 1048575;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzf(java.lang.Object r17, com.google.android.gms.internal.atv_ads_framework.zzgg r18) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            boolean r3 = r0.zzg
            r4 = 0
            r5 = 1
            r6 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            if (r3 == 0) goto L_0x0462
            boolean r3 = r0.zzf
            if (r3 != 0) goto L_0x045c
            int[] r3 = r0.zzc
            int r3 = r3.length
            r4 = r6
        L_0x0018:
            if (r4 >= r3) goto L_0x0452
            int r8 = r0.zzp(r4)
            int[] r9 = r0.zzc
            r9 = r9[r4]
            int r10 = zzo(r8)
            switch(r10) {
                case 0: goto L_0x043f;
                case 1: goto L_0x042f;
                case 2: goto L_0x041f;
                case 3: goto L_0x040f;
                case 4: goto L_0x03ff;
                case 5: goto L_0x03ef;
                case 6: goto L_0x03df;
                case 7: goto L_0x03ce;
                case 8: goto L_0x03bd;
                case 9: goto L_0x03a8;
                case 10: goto L_0x0395;
                case 11: goto L_0x0384;
                case 12: goto L_0x0373;
                case 13: goto L_0x0362;
                case 14: goto L_0x0351;
                case 15: goto L_0x0340;
                case 16: goto L_0x032f;
                case 17: goto L_0x031a;
                case 18: goto L_0x030d;
                case 19: goto L_0x0300;
                case 20: goto L_0x02f3;
                case 21: goto L_0x02e6;
                case 22: goto L_0x02d9;
                case 23: goto L_0x02cc;
                case 24: goto L_0x02bf;
                case 25: goto L_0x02b2;
                case 26: goto L_0x02a5;
                case 27: goto L_0x0294;
                case 28: goto L_0x0287;
                case 29: goto L_0x027a;
                case 30: goto L_0x026d;
                case 31: goto L_0x0260;
                case 32: goto L_0x0253;
                case 33: goto L_0x0246;
                case 34: goto L_0x0239;
                case 35: goto L_0x022c;
                case 36: goto L_0x021f;
                case 37: goto L_0x0212;
                case 38: goto L_0x0205;
                case 39: goto L_0x01f8;
                case 40: goto L_0x01eb;
                case 41: goto L_0x01de;
                case 42: goto L_0x01d1;
                case 43: goto L_0x01c4;
                case 44: goto L_0x01b7;
                case 45: goto L_0x01aa;
                case 46: goto L_0x019d;
                case 47: goto L_0x0190;
                case 48: goto L_0x0183;
                case 49: goto L_0x0172;
                case 50: goto L_0x0167;
                case 51: goto L_0x0156;
                case 52: goto L_0x0145;
                case 53: goto L_0x0134;
                case 54: goto L_0x0123;
                case 55: goto L_0x0112;
                case 56: goto L_0x0101;
                case 57: goto L_0x00f0;
                case 58: goto L_0x00df;
                case 59: goto L_0x00ce;
                case 60: goto L_0x00b9;
                case 61: goto L_0x00a6;
                case 62: goto L_0x0095;
                case 63: goto L_0x0084;
                case 64: goto L_0x0073;
                case 65: goto L_0x0062;
                case 66: goto L_0x0051;
                case 67: goto L_0x0040;
                case 68: goto L_0x002b;
                default: goto L_0x0029;
            }
        L_0x0029:
            goto L_0x044e
        L_0x002b:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            com.google.android.gms.internal.atv_ads_framework.zzey r10 = r0.zzr(r4)
            r2.zzp(r9, r8, r10)
            goto L_0x044e
        L_0x0040:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            long r10 = zzq(r1, r10)
            r2.zzB(r9, r10)
            goto L_0x044e
        L_0x0051:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = zzm(r1, r10)
            r2.zzz(r9, r8)
            goto L_0x044e
        L_0x0062:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            long r10 = zzq(r1, r10)
            r2.zzx(r9, r10)
            goto L_0x044e
        L_0x0073:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = zzm(r1, r10)
            r2.zzv(r9, r8)
            goto L_0x044e
        L_0x0084:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = zzm(r1, r10)
            r2.zzh(r9, r8)
            goto L_0x044e
        L_0x0095:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = zzm(r1, r10)
            r2.zzF(r9, r8)
            goto L_0x044e
        L_0x00a6:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            com.google.android.gms.internal.atv_ads_framework.zzcn r8 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r8
            r2.zzd(r9, r8)
            goto L_0x044e
        L_0x00b9:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            com.google.android.gms.internal.atv_ads_framework.zzey r10 = r0.zzr(r4)
            r2.zzu(r9, r8, r10)
            goto L_0x044e
        L_0x00ce:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            zzG(r9, r8, r2)
            goto L_0x044e
        L_0x00df:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            boolean r8 = zzF(r1, r10)
            r2.zzb(r9, r8)
            goto L_0x044e
        L_0x00f0:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = zzm(r1, r10)
            r2.zzj(r9, r8)
            goto L_0x044e
        L_0x0101:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            long r10 = zzq(r1, r10)
            r2.zzl(r9, r10)
            goto L_0x044e
        L_0x0112:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = zzm(r1, r10)
            r2.zzq(r9, r8)
            goto L_0x044e
        L_0x0123:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            long r10 = zzq(r1, r10)
            r2.zzH(r9, r10)
            goto L_0x044e
        L_0x0134:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            long r10 = zzq(r1, r10)
            r2.zzs(r9, r10)
            goto L_0x044e
        L_0x0145:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            float r8 = zzk(r1, r10)
            r2.zzn(r9, r8)
            goto L_0x044e
        L_0x0156:
            boolean r10 = r0.zzE(r1, r9, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            double r10 = zzj(r1, r10)
            r2.zzf(r9, r10)
            goto L_0x044e
        L_0x0167:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            r0.zzy(r2, r9, r8, r4)
            goto L_0x044e
        L_0x0172:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzey r10 = r0.zzr(r4)
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzJ(r9, r8, r2, r10)
            goto L_0x044e
        L_0x0183:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzQ(r9, r8, r2, r5)
            goto L_0x044e
        L_0x0190:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzP(r9, r8, r2, r5)
            goto L_0x044e
        L_0x019d:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzO(r9, r8, r2, r5)
            goto L_0x044e
        L_0x01aa:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzN(r9, r8, r2, r5)
            goto L_0x044e
        L_0x01b7:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzF(r9, r8, r2, r5)
            goto L_0x044e
        L_0x01c4:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzS(r9, r8, r2, r5)
            goto L_0x044e
        L_0x01d1:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzC(r9, r8, r2, r5)
            goto L_0x044e
        L_0x01de:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzG(r9, r8, r2, r5)
            goto L_0x044e
        L_0x01eb:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzH(r9, r8, r2, r5)
            goto L_0x044e
        L_0x01f8:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzK(r9, r8, r2, r5)
            goto L_0x044e
        L_0x0205:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzT(r9, r8, r2, r5)
            goto L_0x044e
        L_0x0212:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzL(r9, r8, r2, r5)
            goto L_0x044e
        L_0x021f:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzI(r9, r8, r2, r5)
            goto L_0x044e
        L_0x022c:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzE(r9, r8, r2, r5)
            goto L_0x044e
        L_0x0239:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzQ(r9, r8, r2, r6)
            goto L_0x044e
        L_0x0246:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzP(r9, r8, r2, r6)
            goto L_0x044e
        L_0x0253:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzO(r9, r8, r2, r6)
            goto L_0x044e
        L_0x0260:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzN(r9, r8, r2, r6)
            goto L_0x044e
        L_0x026d:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzF(r9, r8, r2, r6)
            goto L_0x044e
        L_0x027a:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzS(r9, r8, r2, r6)
            goto L_0x044e
        L_0x0287:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzD(r9, r8, r2)
            goto L_0x044e
        L_0x0294:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzey r10 = r0.zzr(r4)
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzM(r9, r8, r2, r10)
            goto L_0x044e
        L_0x02a5:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzR(r9, r8, r2)
            goto L_0x044e
        L_0x02b2:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzC(r9, r8, r2, r6)
            goto L_0x044e
        L_0x02bf:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzG(r9, r8, r2, r6)
            goto L_0x044e
        L_0x02cc:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzH(r9, r8, r2, r6)
            goto L_0x044e
        L_0x02d9:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzK(r9, r8, r2, r6)
            goto L_0x044e
        L_0x02e6:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzT(r9, r8, r2, r6)
            goto L_0x044e
        L_0x02f3:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzL(r9, r8, r2, r6)
            goto L_0x044e
        L_0x0300:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzI(r9, r8, r2, r6)
            goto L_0x044e
        L_0x030d:
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzE(r9, r8, r2, r6)
            goto L_0x044e
        L_0x031a:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            com.google.android.gms.internal.atv_ads_framework.zzey r10 = r0.zzr(r4)
            r2.zzp(r9, r8, r10)
            goto L_0x044e
        L_0x032f:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r1, r10)
            r2.zzB(r9, r10)
            goto L_0x044e
        L_0x0340:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r1, r10)
            r2.zzz(r9, r8)
            goto L_0x044e
        L_0x0351:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r1, r10)
            r2.zzx(r9, r10)
            goto L_0x044e
        L_0x0362:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r1, r10)
            r2.zzv(r9, r8)
            goto L_0x044e
        L_0x0373:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r1, r10)
            r2.zzh(r9, r8)
            goto L_0x044e
        L_0x0384:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r1, r10)
            r2.zzF(r9, r8)
            goto L_0x044e
        L_0x0395:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            com.google.android.gms.internal.atv_ads_framework.zzcn r8 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r8
            r2.zzd(r9, r8)
            goto L_0x044e
        L_0x03a8:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            com.google.android.gms.internal.atv_ads_framework.zzey r10 = r0.zzr(r4)
            r2.zzu(r9, r8, r10)
            goto L_0x044e
        L_0x03bd:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzf(r1, r10)
            zzG(r9, r8, r2)
            goto L_0x044e
        L_0x03ce:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzw(r1, r10)
            r2.zzb(r9, r8)
            goto L_0x044e
        L_0x03df:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r1, r10)
            r2.zzj(r9, r8)
            goto L_0x044e
        L_0x03ef:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r1, r10)
            r2.zzl(r9, r10)
            goto L_0x044e
        L_0x03ff:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzc(r1, r10)
            r2.zzq(r9, r8)
            goto L_0x044e
        L_0x040f:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r1, r10)
            r2.zzH(r9, r10)
            goto L_0x044e
        L_0x041f:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzd(r1, r10)
            r2.zzs(r9, r10)
            goto L_0x044e
        L_0x042f:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzb(r1, r10)
            r2.zzn(r9, r8)
            goto L_0x044e
        L_0x043f:
            boolean r10 = r0.zzA(r1, r4)
            if (r10 == 0) goto L_0x044e
            r8 = r8 & r7
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.atv_ads_framework.zzfz.zza(r1, r10)
            r2.zzf(r9, r10)
        L_0x044e:
            int r4 = r4 + 3
            goto L_0x0018
        L_0x0452:
            com.google.android.gms.internal.atv_ads_framework.zzfp r3 = r0.zzk
            java.lang.Object r1 = r3.zzc(r1)
            r3.zzg(r1, r2)
            return
        L_0x045c:
            com.google.android.gms.internal.atv_ads_framework.zzcy r2 = r0.zzl
            r2.zza(r1)
            throw r4
        L_0x0462:
            boolean r3 = r0.zzf
            if (r3 != 0) goto L_0x08b6
            int[] r3 = r0.zzc
            int r3 = r3.length
            sun.misc.Unsafe r4 = zzb
            r8 = r6
            r10 = r8
            r9 = r7
        L_0x046e:
            if (r8 >= r3) goto L_0x08ac
            int r11 = r0.zzp(r8)
            int[] r12 = r0.zzc
            r13 = r12[r8]
            int r14 = zzo(r11)
            r15 = 17
            if (r14 > r15) goto L_0x0493
            int r15 = r8 + 2
            r12 = r12[r15]
            r15 = r12 & r7
            if (r15 == r9) goto L_0x048e
            long r9 = (long) r15
            int r10 = r4.getInt(r1, r9)
            r9 = r15
        L_0x048e:
            int r12 = r12 >>> 20
            int r12 = r5 << r12
            goto L_0x0494
        L_0x0493:
            r12 = r6
        L_0x0494:
            r11 = r11 & r7
            long r6 = (long) r11
            switch(r14) {
                case 0: goto L_0x0898;
                case 1: goto L_0x088b;
                case 2: goto L_0x087e;
                case 3: goto L_0x0871;
                case 4: goto L_0x0864;
                case 5: goto L_0x0857;
                case 6: goto L_0x084a;
                case 7: goto L_0x083d;
                case 8: goto L_0x082f;
                case 9: goto L_0x081d;
                case 10: goto L_0x080d;
                case 11: goto L_0x07ff;
                case 12: goto L_0x07f1;
                case 13: goto L_0x07e3;
                case 14: goto L_0x07d5;
                case 15: goto L_0x07c7;
                case 16: goto L_0x07b9;
                case 17: goto L_0x07a7;
                case 18: goto L_0x0797;
                case 19: goto L_0x0787;
                case 20: goto L_0x0777;
                case 21: goto L_0x0767;
                case 22: goto L_0x0757;
                case 23: goto L_0x0747;
                case 24: goto L_0x0737;
                case 25: goto L_0x0727;
                case 26: goto L_0x0718;
                case 27: goto L_0x0705;
                case 28: goto L_0x06f6;
                case 29: goto L_0x06e5;
                case 30: goto L_0x06d6;
                case 31: goto L_0x06c7;
                case 32: goto L_0x06b8;
                case 33: goto L_0x06a9;
                case 34: goto L_0x069a;
                case 35: goto L_0x068b;
                case 36: goto L_0x067c;
                case 37: goto L_0x066d;
                case 38: goto L_0x065e;
                case 39: goto L_0x064f;
                case 40: goto L_0x0640;
                case 41: goto L_0x0631;
                case 42: goto L_0x0622;
                case 43: goto L_0x0613;
                case 44: goto L_0x0604;
                case 45: goto L_0x05f5;
                case 46: goto L_0x05e6;
                case 47: goto L_0x05d7;
                case 48: goto L_0x05c8;
                case 49: goto L_0x05b5;
                case 50: goto L_0x05ac;
                case 51: goto L_0x059d;
                case 52: goto L_0x058e;
                case 53: goto L_0x057f;
                case 54: goto L_0x0570;
                case 55: goto L_0x0561;
                case 56: goto L_0x0552;
                case 57: goto L_0x0543;
                case 58: goto L_0x0534;
                case 59: goto L_0x0525;
                case 60: goto L_0x0512;
                case 61: goto L_0x0502;
                case 62: goto L_0x04f4;
                case 63: goto L_0x04e6;
                case 64: goto L_0x04d8;
                case 65: goto L_0x04ca;
                case 66: goto L_0x04bc;
                case 67: goto L_0x04ae;
                case 68: goto L_0x049c;
                default: goto L_0x0499;
            }
        L_0x0499:
            r14 = 0
            goto L_0x08a4
        L_0x049c:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            java.lang.Object r6 = r4.getObject(r1, r6)
            com.google.android.gms.internal.atv_ads_framework.zzey r7 = r0.zzr(r8)
            r2.zzp(r13, r6, r7)
            goto L_0x0499
        L_0x04ae:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            long r6 = zzq(r1, r6)
            r2.zzB(r13, r6)
            goto L_0x0499
        L_0x04bc:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            int r6 = zzm(r1, r6)
            r2.zzz(r13, r6)
            goto L_0x0499
        L_0x04ca:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            long r6 = zzq(r1, r6)
            r2.zzx(r13, r6)
            goto L_0x0499
        L_0x04d8:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            int r6 = zzm(r1, r6)
            r2.zzv(r13, r6)
            goto L_0x0499
        L_0x04e6:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            int r6 = zzm(r1, r6)
            r2.zzh(r13, r6)
            goto L_0x0499
        L_0x04f4:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            int r6 = zzm(r1, r6)
            r2.zzF(r13, r6)
            goto L_0x0499
        L_0x0502:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            java.lang.Object r6 = r4.getObject(r1, r6)
            com.google.android.gms.internal.atv_ads_framework.zzcn r6 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r6
            r2.zzd(r13, r6)
            goto L_0x0499
        L_0x0512:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            java.lang.Object r6 = r4.getObject(r1, r6)
            com.google.android.gms.internal.atv_ads_framework.zzey r7 = r0.zzr(r8)
            r2.zzu(r13, r6, r7)
            goto L_0x0499
        L_0x0525:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            java.lang.Object r6 = r4.getObject(r1, r6)
            zzG(r13, r6, r2)
            goto L_0x0499
        L_0x0534:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            boolean r6 = zzF(r1, r6)
            r2.zzb(r13, r6)
            goto L_0x0499
        L_0x0543:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            int r6 = zzm(r1, r6)
            r2.zzj(r13, r6)
            goto L_0x0499
        L_0x0552:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            long r6 = zzq(r1, r6)
            r2.zzl(r13, r6)
            goto L_0x0499
        L_0x0561:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            int r6 = zzm(r1, r6)
            r2.zzq(r13, r6)
            goto L_0x0499
        L_0x0570:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            long r6 = zzq(r1, r6)
            r2.zzH(r13, r6)
            goto L_0x0499
        L_0x057f:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            long r6 = zzq(r1, r6)
            r2.zzs(r13, r6)
            goto L_0x0499
        L_0x058e:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            float r6 = zzk(r1, r6)
            r2.zzn(r13, r6)
            goto L_0x0499
        L_0x059d:
            boolean r11 = r0.zzE(r1, r13, r8)
            if (r11 == 0) goto L_0x0499
            double r6 = zzj(r1, r6)
            r2.zzf(r13, r6)
            goto L_0x0499
        L_0x05ac:
            java.lang.Object r6 = r4.getObject(r1, r6)
            r0.zzy(r2, r13, r6, r8)
            goto L_0x0499
        L_0x05b5:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzey r7 = r0.zzr(r8)
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzJ(r11, r6, r2, r7)
            goto L_0x0499
        L_0x05c8:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzQ(r11, r6, r2, r5)
            goto L_0x0499
        L_0x05d7:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzP(r11, r6, r2, r5)
            goto L_0x0499
        L_0x05e6:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzO(r11, r6, r2, r5)
            goto L_0x0499
        L_0x05f5:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzN(r11, r6, r2, r5)
            goto L_0x0499
        L_0x0604:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzF(r11, r6, r2, r5)
            goto L_0x0499
        L_0x0613:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzS(r11, r6, r2, r5)
            goto L_0x0499
        L_0x0622:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzC(r11, r6, r2, r5)
            goto L_0x0499
        L_0x0631:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzG(r11, r6, r2, r5)
            goto L_0x0499
        L_0x0640:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzH(r11, r6, r2, r5)
            goto L_0x0499
        L_0x064f:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzK(r11, r6, r2, r5)
            goto L_0x0499
        L_0x065e:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzT(r11, r6, r2, r5)
            goto L_0x0499
        L_0x066d:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzL(r11, r6, r2, r5)
            goto L_0x0499
        L_0x067c:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzI(r11, r6, r2, r5)
            goto L_0x0499
        L_0x068b:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzE(r11, r6, r2, r5)
            goto L_0x0499
        L_0x069a:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            r12 = 0
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzQ(r11, r6, r2, r12)
            goto L_0x06f3
        L_0x06a9:
            r12 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzP(r11, r6, r2, r12)
            goto L_0x06f3
        L_0x06b8:
            r12 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzO(r11, r6, r2, r12)
            goto L_0x06f3
        L_0x06c7:
            r12 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzN(r11, r6, r2, r12)
            goto L_0x06f3
        L_0x06d6:
            r12 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzF(r11, r6, r2, r12)
            goto L_0x06f3
        L_0x06e5:
            r12 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzS(r11, r6, r2, r12)
        L_0x06f3:
            r14 = r12
            goto L_0x08a4
        L_0x06f6:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzD(r11, r6, r2)
            goto L_0x0499
        L_0x0705:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzey r7 = r0.zzr(r8)
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzM(r11, r6, r2, r7)
            goto L_0x0499
        L_0x0718:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzR(r11, r6, r2)
            goto L_0x0499
        L_0x0727:
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            r14 = 0
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzC(r11, r6, r2, r14)
            goto L_0x08a4
        L_0x0737:
            r14 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzG(r11, r6, r2, r14)
            goto L_0x08a4
        L_0x0747:
            r14 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzH(r11, r6, r2, r14)
            goto L_0x08a4
        L_0x0757:
            r14 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzK(r11, r6, r2, r14)
            goto L_0x08a4
        L_0x0767:
            r14 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzT(r11, r6, r2, r14)
            goto L_0x08a4
        L_0x0777:
            r14 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzL(r11, r6, r2, r14)
            goto L_0x08a4
        L_0x0787:
            r14 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzI(r11, r6, r2, r14)
            goto L_0x08a4
        L_0x0797:
            r14 = 0
            int[] r11 = r0.zzc
            r11 = r11[r8]
            java.lang.Object r6 = r4.getObject(r1, r6)
            java.util.List r6 = (java.util.List) r6
            com.google.android.gms.internal.atv_ads_framework.zzfa.zzE(r11, r6, r2, r14)
            goto L_0x08a4
        L_0x07a7:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            java.lang.Object r6 = r4.getObject(r1, r6)
            com.google.android.gms.internal.atv_ads_framework.zzey r7 = r0.zzr(r8)
            r2.zzp(r13, r6, r7)
            goto L_0x08a4
        L_0x07b9:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            long r6 = r4.getLong(r1, r6)
            r2.zzB(r13, r6)
            goto L_0x08a4
        L_0x07c7:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            int r6 = r4.getInt(r1, r6)
            r2.zzz(r13, r6)
            goto L_0x08a4
        L_0x07d5:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            long r6 = r4.getLong(r1, r6)
            r2.zzx(r13, r6)
            goto L_0x08a4
        L_0x07e3:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            int r6 = r4.getInt(r1, r6)
            r2.zzv(r13, r6)
            goto L_0x08a4
        L_0x07f1:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            int r6 = r4.getInt(r1, r6)
            r2.zzh(r13, r6)
            goto L_0x08a4
        L_0x07ff:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            int r6 = r4.getInt(r1, r6)
            r2.zzF(r13, r6)
            goto L_0x08a4
        L_0x080d:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            java.lang.Object r6 = r4.getObject(r1, r6)
            com.google.android.gms.internal.atv_ads_framework.zzcn r6 = (com.google.android.gms.internal.atv_ads_framework.zzcn) r6
            r2.zzd(r13, r6)
            goto L_0x08a4
        L_0x081d:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            java.lang.Object r6 = r4.getObject(r1, r6)
            com.google.android.gms.internal.atv_ads_framework.zzey r7 = r0.zzr(r8)
            r2.zzu(r13, r6, r7)
            goto L_0x08a4
        L_0x082f:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            java.lang.Object r6 = r4.getObject(r1, r6)
            zzG(r13, r6, r2)
            goto L_0x08a4
        L_0x083d:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            boolean r6 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzw(r1, r6)
            r2.zzb(r13, r6)
            goto L_0x08a4
        L_0x084a:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            int r6 = r4.getInt(r1, r6)
            r2.zzj(r13, r6)
            goto L_0x08a4
        L_0x0857:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            long r6 = r4.getLong(r1, r6)
            r2.zzl(r13, r6)
            goto L_0x08a4
        L_0x0864:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            int r6 = r4.getInt(r1, r6)
            r2.zzq(r13, r6)
            goto L_0x08a4
        L_0x0871:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            long r6 = r4.getLong(r1, r6)
            r2.zzH(r13, r6)
            goto L_0x08a4
        L_0x087e:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            long r6 = r4.getLong(r1, r6)
            r2.zzs(r13, r6)
            goto L_0x08a4
        L_0x088b:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            float r6 = com.google.android.gms.internal.atv_ads_framework.zzfz.zzb(r1, r6)
            r2.zzn(r13, r6)
            goto L_0x08a4
        L_0x0898:
            r14 = 0
            r11 = r10 & r12
            if (r11 == 0) goto L_0x08a4
            double r6 = com.google.android.gms.internal.atv_ads_framework.zzfz.zza(r1, r6)
            r2.zzf(r13, r6)
        L_0x08a4:
            int r8 = r8 + 3
            r6 = r14
            r7 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x046e
        L_0x08ac:
            com.google.android.gms.internal.atv_ads_framework.zzfp r3 = r0.zzk
            java.lang.Object r1 = r3.zzc(r1)
            r3.zzg(r1, r2)
            return
        L_0x08b6:
            com.google.android.gms.internal.atv_ads_framework.zzcy r2 = r0.zzl
            r2.zza(r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzer.zzf(java.lang.Object, com.google.android.gms.internal.atv_ads_framework.zzgg):void");
    }
}
