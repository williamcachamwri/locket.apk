package com.google.android.gms.internal.pal;

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

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaei<T> implements zzaer<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzafs.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzaef zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzadt zzn;
    private final zzafi zzo;
    private final zzacn zzp;
    private final zzaek zzq;
    private final zzaea zzr;

    private zzaei(int[] iArr, Object[] objArr, int i, int i2, zzaef zzaef, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzaek zzaek, zzadt zzadt, zzafi zzafi, zzacn zzacn, zzaea zzaea, byte[] bArr) {
        zzaef zzaef2 = zzaef;
        zzacn zzacn2 = zzacn;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        this.zzi = zzaef2 instanceof zzacz;
        this.zzj = z;
        boolean z3 = false;
        if (zzacn2 != null && zzacn2.zzh(zzaef)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzk = iArr2;
        this.zzl = i3;
        this.zzm = i4;
        this.zzq = zzaek;
        this.zzn = zzadt;
        this.zzo = zzafi;
        this.zzp = zzacn2;
        this.zzg = zzaef2;
        this.zzr = zzaea;
    }

    private final int zzA(int i, int i2) {
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

    private static int zzB(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzC(int i) {
        return this.zzc[i + 1];
    }

    private static long zzD(Object obj, long j) {
        return ((Long) zzafs.zzf(obj, j)).longValue();
    }

    private final zzadd zzE(int i) {
        int i2 = i / 3;
        return (zzadd) this.zzd[i2 + i2 + 1];
    }

    private final zzaer zzF(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzaer zzaer = (zzaer) this.zzd[i3];
        if (zzaer != null) {
            return zzaer;
        }
        zzaer zzb2 = zzaen.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzG(Object obj, int i, Object obj2, zzafi zzafi) {
        int i2 = this.zzc[i];
        Object zzf2 = zzafs.zzf(obj, (long) (zzC(i) & 1048575));
        if (zzf2 == null || zzE(i) == null) {
            return obj2;
        }
        zzadz zzadz = (zzadz) zzf2;
        zzady zzady = (zzady) zzH(i);
        throw null;
    }

    private final Object zzH(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzI(Class cls, String str) {
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

    private final void zzJ(Object obj, Object obj2, int i) {
        long zzC = (long) (zzC(i) & 1048575);
        if (zzS(obj2, i)) {
            Object zzf2 = zzafs.zzf(obj, zzC);
            Object zzf3 = zzafs.zzf(obj2, zzC);
            if (zzf2 != null && zzf3 != null) {
                zzafs.zzs(obj, zzC, zzadg.zzg(zzf2, zzf3));
                zzM(obj, i);
            } else if (zzf3 != null) {
                zzafs.zzs(obj, zzC, zzf3);
                zzM(obj, i);
            }
        }
    }

    private final void zzK(Object obj, Object obj2, int i) {
        int zzC = zzC(i);
        int i2 = this.zzc[i];
        long j = (long) (zzC & 1048575);
        if (zzV(obj2, i2, i)) {
            Object zzf2 = zzV(obj, i2, i) ? zzafs.zzf(obj, j) : null;
            Object zzf3 = zzafs.zzf(obj2, j);
            if (zzf2 != null && zzf3 != null) {
                zzafs.zzs(obj, j, zzadg.zzg(zzf2, zzf3));
                zzN(obj, i2, i);
            } else if (zzf3 != null) {
                zzafs.zzs(obj, j, zzf3);
                zzN(obj, i2, i);
            }
        }
    }

    private final void zzL(Object obj, int i, zzaeq zzaeq) throws IOException {
        if (zzR(i)) {
            zzafs.zzs(obj, (long) (i & 1048575), zzaeq.zzu());
        } else if (this.zzi) {
            zzafs.zzs(obj, (long) (i & 1048575), zzaeq.zzt());
        } else {
            zzafs.zzs(obj, (long) (i & 1048575), zzaeq.zzp());
        }
    }

    private final void zzM(Object obj, int i) {
        int zzz = zzz(i);
        long j = (long) (1048575 & zzz);
        if (j != 1048575) {
            zzafs.zzq(obj, j, (1 << (zzz >>> 20)) | zzafs.zzc(obj, j));
        }
    }

    private final void zzN(Object obj, int i, int i2) {
        zzafs.zzq(obj, (long) (zzz(i2) & 1048575), i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:156:0x044c, code lost:
        r7 = r7 + 3;
        r5 = 1048575;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x029b, code lost:
        r13 = r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzO(java.lang.Object r17, com.google.android.gms.internal.pal.zzaga r18) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            boolean r3 = r0.zzh
            if (r3 != 0) goto L_0x045d
            int[] r3 = r0.zzc
            int r3 = r3.length
            sun.misc.Unsafe r4 = zzb
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r5
            r7 = 0
            r8 = 0
        L_0x0015:
            if (r7 >= r3) goto L_0x0453
            int r10 = r0.zzC(r7)
            int[] r11 = r0.zzc
            r12 = r11[r7]
            int r13 = zzB(r10)
            r14 = 17
            r15 = 1
            if (r13 > r14) goto L_0x003b
            int r14 = r7 + 2
            r11 = r11[r14]
            r14 = r11 & r5
            if (r14 == r9) goto L_0x0036
            long r8 = (long) r14
            int r8 = r4.getInt(r1, r8)
            r9 = r14
        L_0x0036:
            int r11 = r11 >>> 20
            int r11 = r15 << r11
            goto L_0x003c
        L_0x003b:
            r11 = 0
        L_0x003c:
            r10 = r10 & r5
            long r5 = (long) r10
            switch(r13) {
                case 0: goto L_0x0440;
                case 1: goto L_0x0433;
                case 2: goto L_0x0426;
                case 3: goto L_0x0419;
                case 4: goto L_0x040c;
                case 5: goto L_0x03ff;
                case 6: goto L_0x03f2;
                case 7: goto L_0x03e5;
                case 8: goto L_0x03d7;
                case 9: goto L_0x03c5;
                case 10: goto L_0x03b5;
                case 11: goto L_0x03a7;
                case 12: goto L_0x0399;
                case 13: goto L_0x038b;
                case 14: goto L_0x037d;
                case 15: goto L_0x036f;
                case 16: goto L_0x0361;
                case 17: goto L_0x034f;
                case 18: goto L_0x033f;
                case 19: goto L_0x032f;
                case 20: goto L_0x031f;
                case 21: goto L_0x030f;
                case 22: goto L_0x02ff;
                case 23: goto L_0x02ef;
                case 24: goto L_0x02df;
                case 25: goto L_0x02cf;
                case 26: goto L_0x02c0;
                case 27: goto L_0x02ad;
                case 28: goto L_0x029e;
                case 29: goto L_0x028d;
                case 30: goto L_0x027e;
                case 31: goto L_0x026f;
                case 32: goto L_0x0260;
                case 33: goto L_0x0251;
                case 34: goto L_0x0242;
                case 35: goto L_0x0233;
                case 36: goto L_0x0224;
                case 37: goto L_0x0215;
                case 38: goto L_0x0206;
                case 39: goto L_0x01f7;
                case 40: goto L_0x01e8;
                case 41: goto L_0x01d9;
                case 42: goto L_0x01ca;
                case 43: goto L_0x01bb;
                case 44: goto L_0x01ac;
                case 45: goto L_0x019d;
                case 46: goto L_0x018e;
                case 47: goto L_0x017f;
                case 48: goto L_0x0170;
                case 49: goto L_0x015d;
                case 50: goto L_0x0154;
                case 51: goto L_0x0145;
                case 52: goto L_0x0136;
                case 53: goto L_0x0127;
                case 54: goto L_0x0118;
                case 55: goto L_0x0109;
                case 56: goto L_0x00fa;
                case 57: goto L_0x00eb;
                case 58: goto L_0x00dc;
                case 59: goto L_0x00cd;
                case 60: goto L_0x00ba;
                case 61: goto L_0x00aa;
                case 62: goto L_0x009c;
                case 63: goto L_0x008e;
                case 64: goto L_0x0080;
                case 65: goto L_0x0072;
                case 66: goto L_0x0064;
                case 67: goto L_0x0056;
                case 68: goto L_0x0044;
                default: goto L_0x0041;
            }
        L_0x0041:
            r13 = 0
            goto L_0x044c
        L_0x0044:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.pal.zzaer r6 = r0.zzF(r7)
            r2.zzq(r12, r5, r6)
            goto L_0x0041
        L_0x0056:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            long r5 = zzD(r1, r5)
            r2.zzC(r12, r5)
            goto L_0x0041
        L_0x0064:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzs(r1, r5)
            r2.zzA(r12, r5)
            goto L_0x0041
        L_0x0072:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            long r5 = zzD(r1, r5)
            r2.zzy(r12, r5)
            goto L_0x0041
        L_0x0080:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzs(r1, r5)
            r2.zzw(r12, r5)
            goto L_0x0041
        L_0x008e:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzs(r1, r5)
            r2.zzi(r12, r5)
            goto L_0x0041
        L_0x009c:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzs(r1, r5)
            r2.zzH(r12, r5)
            goto L_0x0041
        L_0x00aa:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.pal.zzaby r5 = (com.google.android.gms.internal.pal.zzaby) r5
            r2.zzd(r12, r5)
            goto L_0x0041
        L_0x00ba:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.pal.zzaer r6 = r0.zzF(r7)
            r2.zzv(r12, r5, r6)
            goto L_0x0041
        L_0x00cd:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            java.lang.Object r5 = r4.getObject(r1, r5)
            zzX(r12, r5, r2)
            goto L_0x0041
        L_0x00dc:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            boolean r5 = zzW(r1, r5)
            r2.zzb(r12, r5)
            goto L_0x0041
        L_0x00eb:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzs(r1, r5)
            r2.zzk(r12, r5)
            goto L_0x0041
        L_0x00fa:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            long r5 = zzD(r1, r5)
            r2.zzm(r12, r5)
            goto L_0x0041
        L_0x0109:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            int r5 = zzs(r1, r5)
            r2.zzr(r12, r5)
            goto L_0x0041
        L_0x0118:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            long r5 = zzD(r1, r5)
            r2.zzJ(r12, r5)
            goto L_0x0041
        L_0x0127:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            long r5 = zzD(r1, r5)
            r2.zzt(r12, r5)
            goto L_0x0041
        L_0x0136:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            float r5 = zzp(r1, r5)
            r2.zzo(r12, r5)
            goto L_0x0041
        L_0x0145:
            boolean r10 = r0.zzV(r1, r12, r7)
            if (r10 == 0) goto L_0x0041
            double r5 = zzo(r1, r5)
            r2.zzf(r12, r5)
            goto L_0x0041
        L_0x0154:
            java.lang.Object r5 = r4.getObject(r1, r5)
            r0.zzP(r2, r12, r5, r7)
            goto L_0x0041
        L_0x015d:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaer r6 = r0.zzF(r7)
            com.google.android.gms.internal.pal.zzaet.zzO(r10, r5, r2, r6)
            goto L_0x0041
        L_0x0170:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzV(r10, r5, r2, r15)
            goto L_0x0041
        L_0x017f:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzU(r10, r5, r2, r15)
            goto L_0x0041
        L_0x018e:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzT(r10, r5, r2, r15)
            goto L_0x0041
        L_0x019d:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzS(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01ac:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzK(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01bb:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzX(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01ca:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzH(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01d9:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzL(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01e8:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzM(r10, r5, r2, r15)
            goto L_0x0041
        L_0x01f7:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzP(r10, r5, r2, r15)
            goto L_0x0041
        L_0x0206:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzY(r10, r5, r2, r15)
            goto L_0x0041
        L_0x0215:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzQ(r10, r5, r2, r15)
            goto L_0x0041
        L_0x0224:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzN(r10, r5, r2, r15)
            goto L_0x0041
        L_0x0233:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzJ(r10, r5, r2, r15)
            goto L_0x0041
        L_0x0242:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            r11 = 0
            com.google.android.gms.internal.pal.zzaet.zzV(r10, r5, r2, r11)
            goto L_0x029b
        L_0x0251:
            r11 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzU(r10, r5, r2, r11)
            goto L_0x029b
        L_0x0260:
            r11 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzT(r10, r5, r2, r11)
            goto L_0x029b
        L_0x026f:
            r11 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzS(r10, r5, r2, r11)
            goto L_0x029b
        L_0x027e:
            r11 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzK(r10, r5, r2, r11)
            goto L_0x029b
        L_0x028d:
            r11 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzX(r10, r5, r2, r11)
        L_0x029b:
            r13 = r11
            goto L_0x044c
        L_0x029e:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzI(r10, r5, r2)
            goto L_0x0041
        L_0x02ad:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaer r6 = r0.zzF(r7)
            com.google.android.gms.internal.pal.zzaet.zzR(r10, r5, r2, r6)
            goto L_0x0041
        L_0x02c0:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzW(r10, r5, r2)
            goto L_0x0041
        L_0x02cf:
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            r13 = 0
            com.google.android.gms.internal.pal.zzaet.zzH(r10, r5, r2, r13)
            goto L_0x044c
        L_0x02df:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzL(r10, r5, r2, r13)
            goto L_0x044c
        L_0x02ef:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzM(r10, r5, r2, r13)
            goto L_0x044c
        L_0x02ff:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzP(r10, r5, r2, r13)
            goto L_0x044c
        L_0x030f:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzY(r10, r5, r2, r13)
            goto L_0x044c
        L_0x031f:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzQ(r10, r5, r2, r13)
            goto L_0x044c
        L_0x032f:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzN(r10, r5, r2, r13)
            goto L_0x044c
        L_0x033f:
            r13 = 0
            int[] r10 = r0.zzc
            r10 = r10[r7]
            java.lang.Object r5 = r4.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            com.google.android.gms.internal.pal.zzaet.zzJ(r10, r5, r2, r13)
            goto L_0x044c
        L_0x034f:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.pal.zzaer r6 = r0.zzF(r7)
            r2.zzq(r12, r5, r6)
            goto L_0x044c
        L_0x0361:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            long r5 = r4.getLong(r1, r5)
            r2.zzC(r12, r5)
            goto L_0x044c
        L_0x036f:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzA(r12, r5)
            goto L_0x044c
        L_0x037d:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            long r5 = r4.getLong(r1, r5)
            r2.zzy(r12, r5)
            goto L_0x044c
        L_0x038b:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzw(r12, r5)
            goto L_0x044c
        L_0x0399:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzi(r12, r5)
            goto L_0x044c
        L_0x03a7:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzH(r12, r5)
            goto L_0x044c
        L_0x03b5:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.pal.zzaby r5 = (com.google.android.gms.internal.pal.zzaby) r5
            r2.zzd(r12, r5)
            goto L_0x044c
        L_0x03c5:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            java.lang.Object r5 = r4.getObject(r1, r5)
            com.google.android.gms.internal.pal.zzaer r6 = r0.zzF(r7)
            r2.zzv(r12, r5, r6)
            goto L_0x044c
        L_0x03d7:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            java.lang.Object r5 = r4.getObject(r1, r5)
            zzX(r12, r5, r2)
            goto L_0x044c
        L_0x03e5:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            boolean r5 = com.google.android.gms.internal.pal.zzafs.zzw(r1, r5)
            r2.zzb(r12, r5)
            goto L_0x044c
        L_0x03f2:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzk(r12, r5)
            goto L_0x044c
        L_0x03ff:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            long r5 = r4.getLong(r1, r5)
            r2.zzm(r12, r5)
            goto L_0x044c
        L_0x040c:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            int r5 = r4.getInt(r1, r5)
            r2.zzr(r12, r5)
            goto L_0x044c
        L_0x0419:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            long r5 = r4.getLong(r1, r5)
            r2.zzJ(r12, r5)
            goto L_0x044c
        L_0x0426:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            long r5 = r4.getLong(r1, r5)
            r2.zzt(r12, r5)
            goto L_0x044c
        L_0x0433:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            float r5 = com.google.android.gms.internal.pal.zzafs.zzb(r1, r5)
            r2.zzo(r12, r5)
            goto L_0x044c
        L_0x0440:
            r13 = 0
            r10 = r8 & r11
            if (r10 == 0) goto L_0x044c
            double r5 = com.google.android.gms.internal.pal.zzafs.zza(r1, r5)
            r2.zzf(r12, r5)
        L_0x044c:
            int r7 = r7 + 3
            r5 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0015
        L_0x0453:
            com.google.android.gms.internal.pal.zzafi r3 = r0.zzo
            java.lang.Object r1 = r3.zzd(r1)
            r3.zzp(r1, r2)
            return
        L_0x045d:
            com.google.android.gms.internal.pal.zzacn r2 = r0.zzp
            r2.zza(r1)
            r1 = 0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzaei.zzO(java.lang.Object, com.google.android.gms.internal.pal.zzaga):void");
    }

    private final void zzP(zzaga zzaga, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzady zzady = (zzady) zzH(i2);
            throw null;
        }
    }

    private final boolean zzQ(Object obj, Object obj2, int i) {
        return zzS(obj, i) == zzS(obj2, i);
    }

    private static boolean zzR(int i) {
        return (i & C.BUFFER_FLAG_LAST_SAMPLE) != 0;
    }

    private final boolean zzS(Object obj, int i) {
        int zzz = zzz(i);
        long j = (long) (zzz & 1048575);
        if (j != 1048575) {
            return (zzafs.zzc(obj, j) & (1 << (zzz >>> 20))) != 0;
        }
        int zzC = zzC(i);
        long j2 = (long) (zzC & 1048575);
        switch (zzB(zzC)) {
            case 0:
                return Double.doubleToRawLongBits(zzafs.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzafs.zzb(obj, j2)) != 0;
            case 2:
                return zzafs.zzd(obj, j2) != 0;
            case 3:
                return zzafs.zzd(obj, j2) != 0;
            case 4:
                return zzafs.zzc(obj, j2) != 0;
            case 5:
                return zzafs.zzd(obj, j2) != 0;
            case 6:
                return zzafs.zzc(obj, j2) != 0;
            case 7:
                return zzafs.zzw(obj, j2);
            case 8:
                Object zzf2 = zzafs.zzf(obj, j2);
                if (zzf2 instanceof String) {
                    return !((String) zzf2).isEmpty();
                }
                if (zzf2 instanceof zzaby) {
                    return !zzaby.zzb.equals(zzf2);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzafs.zzf(obj, j2) != null;
            case 10:
                return !zzaby.zzb.equals(zzafs.zzf(obj, j2));
            case 11:
                return zzafs.zzc(obj, j2) != 0;
            case 12:
                return zzafs.zzc(obj, j2) != 0;
            case 13:
                return zzafs.zzc(obj, j2) != 0;
            case 14:
                return zzafs.zzd(obj, j2) != 0;
            case 15:
                return zzafs.zzc(obj, j2) != 0;
            case 16:
                return zzafs.zzd(obj, j2) != 0;
            case 17:
                return zzafs.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzT(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzS(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzU(Object obj, int i, zzaer zzaer) {
        return zzaer.zzl(zzafs.zzf(obj, (long) (i & 1048575)));
    }

    private final boolean zzV(Object obj, int i, int i2) {
        return zzafs.zzc(obj, (long) (zzz(i2) & 1048575)) == i;
    }

    private static boolean zzW(Object obj, long j) {
        return ((Boolean) zzafs.zzf(obj, j)).booleanValue();
    }

    private static final void zzX(int i, Object obj, zzaga zzaga) throws IOException {
        if (obj instanceof String) {
            zzaga.zzF(i, (String) obj);
        } else {
            zzaga.zzd(i, (zzaby) obj);
        }
    }

    static zzafj zzd(Object obj) {
        zzacz zzacz = (zzacz) obj;
        zzafj zzafj = zzacz.zzc;
        if (zzafj != zzafj.zzc()) {
            return zzafj;
        }
        zzafj zze2 = zzafj.zze();
        zzacz.zzc = zze2;
        return zze2;
    }

    static zzaei zzm(Class cls, zzaec zzaec, zzaek zzaek, zzadt zzadt, zzafi zzafi, zzacn zzacn, zzaea zzaea) {
        if (zzaec instanceof zzaep) {
            return zzn((zzaep) zzaec, zzaek, zzadt, zzafi, zzacn, zzaea);
        }
        zzaff zzaff = (zzaff) zzaec;
        throw null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0279  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x027c  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x032f  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0378  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0391  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.gms.internal.pal.zzaei zzn(com.google.android.gms.internal.pal.zzaep r34, com.google.android.gms.internal.pal.zzaek r35, com.google.android.gms.internal.pal.zzadt r36, com.google.android.gms.internal.pal.zzafi r37, com.google.android.gms.internal.pal.zzacn r38, com.google.android.gms.internal.pal.zzaea r39) {
        /*
            int r0 = r34.zzc()
            r1 = 2
            r2 = 0
            if (r0 != r1) goto L_0x000a
            r10 = 1
            goto L_0x000b
        L_0x000a:
            r10 = r2
        L_0x000b:
            java.lang.String r0 = r34.zzd()
            int r1 = r0.length()
            char r4 = r0.charAt(r2)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r5) goto L_0x0027
            r4 = 1
        L_0x001d:
            int r6 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0028
            r4 = r6
            goto L_0x001d
        L_0x0027:
            r6 = 1
        L_0x0028:
            int r4 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x0047
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x0034:
            int r9 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0044
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r8
            r6 = r6 | r4
            int r8 = r8 + 13
            r4 = r9
            goto L_0x0034
        L_0x0044:
            int r4 = r4 << r8
            r6 = r6 | r4
            r4 = r9
        L_0x0047:
            if (r6 != 0) goto L_0x0057
            int[] r6 = zza
            r8 = r2
            r9 = r8
            r11 = r9
            r12 = r11
            r14 = r12
            r16 = r14
            r13 = r6
            r6 = r16
            goto L_0x0166
        L_0x0057:
            int r6 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x0076
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x0063:
            int r9 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x0073
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r6 = r6 << r8
            r4 = r4 | r6
            int r8 = r8 + 13
            r6 = r9
            goto L_0x0063
        L_0x0073:
            int r6 = r6 << r8
            r4 = r4 | r6
            r6 = r9
        L_0x0076:
            int r8 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x0095
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0082:
            int r11 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x0092
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r9
            r6 = r6 | r8
            int r9 = r9 + 13
            r8 = r11
            goto L_0x0082
        L_0x0092:
            int r8 = r8 << r9
            r6 = r6 | r8
            r8 = r11
        L_0x0095:
            int r9 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x00b4
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00a1:
            int r12 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00b1
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r11
            r8 = r8 | r9
            int r11 = r11 + 13
            r9 = r12
            goto L_0x00a1
        L_0x00b1:
            int r9 = r9 << r11
            r8 = r8 | r9
            r9 = r12
        L_0x00b4:
            int r11 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00d3
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00c0:
            int r13 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x00d0
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r9 = r9 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00c0
        L_0x00d0:
            int r11 = r11 << r12
            r9 = r9 | r11
            r11 = r13
        L_0x00d3:
            int r12 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x00f2
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00df:
            int r14 = r12 + 1
            char r12 = r0.charAt(r12)
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
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x0111
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00fe:
            int r15 = r13 + 1
            char r13 = r0.charAt(r13)
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
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x0132
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x011d:
            int r16 = r14 + 1
            char r14 = r0.charAt(r14)
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
            char r14 = r0.charAt(r14)
            if (r14 < r5) goto L_0x0155
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x013e:
            int r17 = r15 + 1
            char r15 = r0.charAt(r15)
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
            int[] r13 = new int[r13]
            int r16 = r4 + r4
            int r16 = r16 + r6
            r6 = r4
            r4 = r15
            r33 = r12
            r12 = r9
            r9 = r33
        L_0x0166:
            sun.misc.Unsafe r15 = zzb
            java.lang.Object[] r17 = r34.zze()
            com.google.android.gms.internal.pal.zzaef r18 = r34.zza()
            java.lang.Class r2 = r18.getClass()
            int r7 = r11 * 3
            int[] r7 = new int[r7]
            int r11 = r11 + r11
            java.lang.Object[] r11 = new java.lang.Object[r11]
            int r21 = r14 + r9
            r22 = r14
            r23 = r21
            r9 = 0
            r20 = 0
        L_0x0184:
            if (r4 >= r1) goto L_0x03cb
            int r24 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x01ac
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r3 = r24
            r24 = 13
        L_0x0194:
            int r26 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r5) goto L_0x01a6
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r24
            r4 = r4 | r3
            int r24 = r24 + 13
            r3 = r26
            goto L_0x0194
        L_0x01a6:
            int r3 = r3 << r24
            r4 = r4 | r3
            r3 = r26
            goto L_0x01ae
        L_0x01ac:
            r3 = r24
        L_0x01ae:
            int r24 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r5) goto L_0x01db
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r5 = r24
            r24 = 13
        L_0x01bc:
            int r27 = r5 + 1
            char r5 = r0.charAt(r5)
            r28 = r1
            r1 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r1) goto L_0x01d5
            r1 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r1 = r1 << r24
            r3 = r3 | r1
            int r24 = r24 + 13
            r5 = r27
            r1 = r28
            goto L_0x01bc
        L_0x01d5:
            int r1 = r5 << r24
            r3 = r3 | r1
            r1 = r27
            goto L_0x01df
        L_0x01db:
            r28 = r1
            r1 = r24
        L_0x01df:
            r5 = r3 & 255(0xff, float:3.57E-43)
            r24 = r14
            r14 = r3 & 1024(0x400, float:1.435E-42)
            if (r14 == 0) goto L_0x01ed
            int r14 = r20 + 1
            r13[r20] = r9
            r20 = r14
        L_0x01ed:
            r14 = 51
            if (r5 < r14) goto L_0x0295
            int r14 = r1 + 1
            char r1 = r0.charAt(r1)
            r27 = r14
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r1 < r14) goto L_0x0223
            r1 = r1 & 8191(0x1fff, float:1.1478E-41)
            r14 = r27
            r27 = 13
        L_0x0204:
            int r31 = r14 + 1
            char r14 = r0.charAt(r14)
            r32 = r12
            r12 = 55296(0xd800, float:7.7486E-41)
            if (r14 < r12) goto L_0x021d
            r12 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r27
            r1 = r1 | r12
            int r27 = r27 + 13
            r14 = r31
            r12 = r32
            goto L_0x0204
        L_0x021d:
            int r12 = r14 << r27
            r1 = r1 | r12
            r14 = r31
            goto L_0x0227
        L_0x0223:
            r32 = r12
            r14 = r27
        L_0x0227:
            int r12 = r5 + -51
            r27 = r14
            r14 = 9
            if (r12 == r14) goto L_0x0248
            r14 = 17
            if (r12 != r14) goto L_0x0234
            goto L_0x0248
        L_0x0234:
            r14 = 12
            if (r12 != r14) goto L_0x0257
            if (r10 != 0) goto L_0x0257
            int r12 = r9 / 3
            int r14 = r16 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r16 = r17[r16]
            r11[r12] = r16
            goto L_0x0255
        L_0x0248:
            int r12 = r9 / 3
            int r14 = r16 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r16 = r17[r16]
            r11[r12] = r16
        L_0x0255:
            r16 = r14
        L_0x0257:
            int r1 = r1 + r1
            r12 = r17[r1]
            boolean r14 = r12 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x0261
            java.lang.reflect.Field r12 = (java.lang.reflect.Field) r12
            goto L_0x0269
        L_0x0261:
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = zzI(r2, r12)
            r17[r1] = r12
        L_0x0269:
            r31 = r7
            r14 = r8
            long r7 = r15.objectFieldOffset(r12)
            int r7 = (int) r7
            int r1 = r1 + 1
            r8 = r17[r1]
            boolean r12 = r8 instanceof java.lang.reflect.Field
            if (r12 == 0) goto L_0x027c
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x0284
        L_0x027c:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zzI(r2, r8)
            r17[r1] = r8
        L_0x0284:
            r1 = r7
            long r7 = r15.objectFieldOffset(r8)
            int r7 = (int) r7
            r30 = r0
            r8 = r7
            r29 = r11
            r25 = 1
            r7 = r1
            r1 = 0
            goto L_0x0393
        L_0x0295:
            r31 = r7
            r14 = r8
            r32 = r12
            int r7 = r16 + 1
            r8 = r17[r16]
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zzI(r2, r8)
            r12 = 9
            if (r5 == r12) goto L_0x030d
            r12 = 17
            if (r5 != r12) goto L_0x02ad
            goto L_0x030d
        L_0x02ad:
            r12 = 27
            if (r5 == r12) goto L_0x02fd
            r12 = 49
            if (r5 != r12) goto L_0x02b6
            goto L_0x02fd
        L_0x02b6:
            r12 = 12
            if (r5 == r12) goto L_0x02ed
            r12 = 30
            if (r5 == r12) goto L_0x02ed
            r12 = 44
            if (r5 != r12) goto L_0x02c3
            goto L_0x02ed
        L_0x02c3:
            r12 = 50
            if (r5 != r12) goto L_0x02e3
            int r12 = r22 + 1
            r13[r22] = r9
            int r22 = r9 / 3
            int r22 = r22 + r22
            int r27 = r7 + 1
            r7 = r17[r7]
            r11[r22] = r7
            r7 = r3 & 2048(0x800, float:2.87E-42)
            if (r7 == 0) goto L_0x02e6
            int r7 = r27 + 1
            int r22 = r22 + 1
            r27 = r17[r27]
            r11[r22] = r27
            r22 = r12
        L_0x02e3:
            r25 = 1
            goto L_0x031a
        L_0x02e6:
            r22 = r12
            r12 = r27
            r25 = 1
            goto L_0x031b
        L_0x02ed:
            if (r10 != 0) goto L_0x02e3
            int r12 = r9 / 3
            int r27 = r7 + 1
            int r12 = r12 + r12
            r25 = 1
            int r12 = r12 + 1
            r7 = r17[r7]
            r11[r12] = r7
            goto L_0x030a
        L_0x02fd:
            r25 = 1
            int r12 = r9 / 3
            int r27 = r7 + 1
            int r12 = r12 + r12
            int r12 = r12 + 1
            r7 = r17[r7]
            r11[r12] = r7
        L_0x030a:
            r12 = r27
            goto L_0x031b
        L_0x030d:
            r25 = 1
            int r12 = r9 / 3
            int r12 = r12 + r12
            int r12 = r12 + 1
            java.lang.Class r27 = r8.getType()
            r11[r12] = r27
        L_0x031a:
            r12 = r7
        L_0x031b:
            long r7 = r15.objectFieldOffset(r8)
            int r7 = (int) r7
            r8 = r3 & 4096(0x1000, float:5.74E-42)
            r27 = 1048575(0xfffff, float:1.469367E-39)
            r29 = r11
            r11 = 4096(0x1000, float:5.74E-42)
            if (r8 != r11) goto L_0x0378
            r8 = 17
            if (r5 > r8) goto L_0x0378
            int r8 = r1 + 1
            char r1 = r0.charAt(r1)
            r11 = 55296(0xd800, float:7.7486E-41)
            if (r1 < r11) goto L_0x0354
            r1 = r1 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
        L_0x033e:
            int r27 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r11) goto L_0x0350
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r26
            r1 = r1 | r8
            int r26 = r26 + 13
            r8 = r27
            goto L_0x033e
        L_0x0350:
            int r8 = r8 << r26
            r1 = r1 | r8
            goto L_0x0356
        L_0x0354:
            r27 = r8
        L_0x0356:
            int r8 = r6 + r6
            int r26 = r1 / 32
            int r8 = r8 + r26
            r11 = r17[r8]
            r30 = r0
            boolean r0 = r11 instanceof java.lang.reflect.Field
            if (r0 == 0) goto L_0x0367
            java.lang.reflect.Field r11 = (java.lang.reflect.Field) r11
            goto L_0x036f
        L_0x0367:
            java.lang.String r11 = (java.lang.String) r11
            java.lang.reflect.Field r11 = zzI(r2, r11)
            r17[r8] = r11
        L_0x036f:
            r0 = r12
            long r11 = r15.objectFieldOffset(r11)
            int r8 = (int) r11
            int r1 = r1 % 32
            goto L_0x0380
        L_0x0378:
            r30 = r0
            r0 = r12
            r8 = r27
            r27 = r1
            r1 = 0
        L_0x0380:
            r11 = 18
            if (r5 < r11) goto L_0x0391
            r11 = 49
            if (r5 > r11) goto L_0x0391
            int r11 = r23 + 1
            r13[r23] = r7
            r16 = r0
            r23 = r11
            goto L_0x0393
        L_0x0391:
            r16 = r0
        L_0x0393:
            int r0 = r9 + 1
            r31[r9] = r4
            int r4 = r0 + 1
            r9 = r3 & 512(0x200, float:7.175E-43)
            if (r9 == 0) goto L_0x03a0
            r9 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03a1
        L_0x03a0:
            r9 = 0
        L_0x03a1:
            r3 = r3 & 256(0x100, float:3.59E-43)
            if (r3 == 0) goto L_0x03a8
            r3 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03a9
        L_0x03a8:
            r3 = 0
        L_0x03a9:
            r3 = r3 | r9
            int r5 = r5 << 20
            r3 = r3 | r5
            r3 = r3 | r7
            r31[r0] = r3
            int r9 = r4 + 1
            int r0 = r1 << 20
            r0 = r0 | r8
            r31[r4] = r0
            r8 = r14
            r14 = r24
            r4 = r27
            r1 = r28
            r11 = r29
            r0 = r30
            r7 = r31
            r12 = r32
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0184
        L_0x03cb:
            r31 = r7
            r29 = r11
            r32 = r12
            r24 = r14
            r14 = r8
            com.google.android.gms.internal.pal.zzaei r0 = new com.google.android.gms.internal.pal.zzaei
            r4 = r0
            com.google.android.gms.internal.pal.zzaef r9 = r34.zza()
            r11 = 0
            r1 = r29
            r20 = 0
            r5 = r31
            r6 = r1
            r7 = r14
            r8 = r32
            r12 = r13
            r13 = r24
            r14 = r21
            r15 = r35
            r16 = r36
            r17 = r37
            r18 = r38
            r19 = r39
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzaei.zzn(com.google.android.gms.internal.pal.zzaep, com.google.android.gms.internal.pal.zzaek, com.google.android.gms.internal.pal.zzadt, com.google.android.gms.internal.pal.zzafi, com.google.android.gms.internal.pal.zzacn, com.google.android.gms.internal.pal.zzaea):com.google.android.gms.internal.pal.zzaei");
    }

    private static double zzo(Object obj, long j) {
        return ((Double) zzafs.zzf(obj, j)).doubleValue();
    }

    private static float zzp(Object obj, long j) {
        return ((Float) zzafs.zzf(obj, j)).floatValue();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0309, code lost:
        r4 = r4 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x030a, code lost:
        r4 = r4 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x030b, code lost:
        r6 = r6 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0349, code lost:
        r6 = r6 + r3;
        r12 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x037d, code lost:
        r6 = r6 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0488, code lost:
        r4 = r4 + (r9 + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0503, code lost:
        r4 = r4 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0504, code lost:
        r6 = r6 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x052d, code lost:
        r6 = r6 + (r9 + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x053b, code lost:
        r3 = r3 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x054a, code lost:
        r3 = r3 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x054e, code lost:
        r5 = r5 + 3;
        r4 = 1048575;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ee, code lost:
        r4 = r4 + (r9 + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x019b, code lost:
        r6 = r6 + (r9 + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01ab, code lost:
        r3 = r3 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01bb, code lost:
        r3 = r3 + 8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzq(java.lang.Object r16) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            sun.misc.Unsafe r2 = zzb
            r4 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r4
            r5 = 0
            r6 = 0
            r7 = 0
        L_0x000c:
            int[] r9 = r0.zzc
            int r9 = r9.length
            if (r5 >= r9) goto L_0x0555
            int r9 = r15.zzC(r5)
            int[] r10 = r0.zzc
            r11 = r10[r5]
            int r12 = zzB(r9)
            r13 = 17
            r14 = 1
            if (r12 > r13) goto L_0x0035
            int r13 = r5 + 2
            r10 = r10[r13]
            r13 = r10 & r4
            int r10 = r10 >>> 20
            int r10 = r14 << r10
            if (r13 == r8) goto L_0x0036
            long r7 = (long) r13
            int r7 = r2.getInt(r1, r7)
            r8 = r13
            goto L_0x0036
        L_0x0035:
            r10 = 0
        L_0x0036:
            r9 = r9 & r4
            long r3 = (long) r9
            r9 = 63
            switch(r12) {
                case 0: goto L_0x053f;
                case 1: goto L_0x0530;
                case 2: goto L_0x051a;
                case 3: goto L_0x0506;
                case 4: goto L_0x04f0;
                case 5: goto L_0x04e4;
                case 6: goto L_0x04d8;
                case 7: goto L_0x04ca;
                case 8: goto L_0x049f;
                case 9: goto L_0x048c;
                case 10: goto L_0x046f;
                case 11: goto L_0x045a;
                case 12: goto L_0x0445;
                case 13: goto L_0x0438;
                case 14: goto L_0x042b;
                case 15: goto L_0x0411;
                case 16: goto L_0x03f7;
                case 17: goto L_0x03e3;
                case 18: goto L_0x03d5;
                case 19: goto L_0x03c9;
                case 20: goto L_0x03bd;
                case 21: goto L_0x03b1;
                case 22: goto L_0x03a5;
                case 23: goto L_0x0399;
                case 24: goto L_0x038d;
                case 25: goto L_0x0381;
                case 26: goto L_0x0373;
                case 27: goto L_0x0364;
                case 28: goto L_0x0359;
                case 29: goto L_0x034d;
                case 30: goto L_0x033e;
                case 31: goto L_0x0332;
                case 32: goto L_0x0326;
                case 33: goto L_0x031a;
                case 34: goto L_0x030e;
                case 35: goto L_0x02f5;
                case 36: goto L_0x02e0;
                case 37: goto L_0x02cb;
                case 38: goto L_0x02b6;
                case 39: goto L_0x02a1;
                case 40: goto L_0x028c;
                case 41: goto L_0x0276;
                case 42: goto L_0x0260;
                case 43: goto L_0x024a;
                case 44: goto L_0x0234;
                case 45: goto L_0x021e;
                case 46: goto L_0x0208;
                case 47: goto L_0x01f2;
                case 48: goto L_0x01dc;
                case 49: goto L_0x01cc;
                case 50: goto L_0x01bf;
                case 51: goto L_0x01af;
                case 52: goto L_0x019f;
                case 53: goto L_0x0187;
                case 54: goto L_0x0172;
                case 55: goto L_0x015c;
                case 56: goto L_0x014f;
                case 57: goto L_0x0142;
                case 58: goto L_0x0133;
                case 59: goto L_0x0106;
                case 60: goto L_0x00f2;
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
            goto L_0x037e
        L_0x003f:
            boolean r9 = r15.zzV(r1, r11, r5)
            if (r9 == 0) goto L_0x037e
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.pal.zzaef r3 = (com.google.android.gms.internal.pal.zzaef) r3
            com.google.android.gms.internal.pal.zzaer r4 = r15.zzF(r5)
            int r3 = com.google.android.gms.internal.pal.zzach.zzu(r11, r3, r4)
            goto L_0x037d
        L_0x0055:
            boolean r10 = r15.zzV(r1, r11, r5)
            if (r10 == 0) goto L_0x037e
            long r3 = zzD(r1, r3)
            int r10 = r11 << 3
            int r10 = com.google.android.gms.internal.pal.zzach.zzA(r10)
            long r11 = r3 + r3
            long r3 = r3 >> r9
            long r3 = r3 ^ r11
            int r3 = com.google.android.gms.internal.pal.zzach.zzB(r3)
            int r10 = r10 + r3
            int r6 = r6 + r10
            goto L_0x037e
        L_0x0071:
            boolean r9 = r15.zzV(r1, r11, r5)
            if (r9 == 0) goto L_0x037e
            int r3 = zzs(r1, r3)
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r9 = r3 + r3
            int r3 = r3 >> 31
            r3 = r3 ^ r9
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x030a
        L_0x008c:
            boolean r3 = r15.zzV(r1, r11, r5)
            if (r3 == 0) goto L_0x037e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x01bb
        L_0x009a:
            boolean r3 = r15.zzV(r1, r11, r5)
            if (r3 == 0) goto L_0x037e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x01ab
        L_0x00a8:
            boolean r9 = r15.zzV(r1, r11, r5)
            if (r9 == 0) goto L_0x037e
            int r3 = zzs(r1, r3)
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = com.google.android.gms.internal.pal.zzach.zzv(r3)
            goto L_0x030a
        L_0x00be:
            boolean r9 = r15.zzV(r1, r11, r5)
            if (r9 == 0) goto L_0x037e
            int r3 = zzs(r1, r3)
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x030a
        L_0x00d4:
            boolean r9 = r15.zzV(r1, r11, r5)
            if (r9 == 0) goto L_0x037e
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.pal.zzaby r3 = (com.google.android.gms.internal.pal.zzaby) r3
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
        L_0x00ee:
            int r9 = r9 + r3
            int r4 = r4 + r9
            goto L_0x030b
        L_0x00f2:
            boolean r9 = r15.zzV(r1, r11, r5)
            if (r9 == 0) goto L_0x037e
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.pal.zzaer r4 = r15.zzF(r5)
            int r3 = com.google.android.gms.internal.pal.zzaet.zzo(r11, r3, r4)
            goto L_0x037d
        L_0x0106:
            boolean r9 = r15.zzV(r1, r11, r5)
            if (r9 == 0) goto L_0x037e
            java.lang.Object r3 = r2.getObject(r1, r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.pal.zzaby
            if (r4 == 0) goto L_0x0125
            com.google.android.gms.internal.pal.zzaby r3 = (com.google.android.gms.internal.pal.zzaby) r3
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x00ee
        L_0x0125:
            java.lang.String r3 = (java.lang.String) r3
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = com.google.android.gms.internal.pal.zzach.zzy(r3)
            goto L_0x030a
        L_0x0133:
            boolean r3 = r15.zzV(r1, r11, r5)
            if (r3 == 0) goto L_0x037e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            int r3 = r3 + r14
            goto L_0x037d
        L_0x0142:
            boolean r3 = r15.zzV(r1, r11, r5)
            if (r3 == 0) goto L_0x037e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x01ab
        L_0x014f:
            boolean r3 = r15.zzV(r1, r11, r5)
            if (r3 == 0) goto L_0x037e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x01bb
        L_0x015c:
            boolean r9 = r15.zzV(r1, r11, r5)
            if (r9 == 0) goto L_0x037e
            int r3 = zzs(r1, r3)
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = com.google.android.gms.internal.pal.zzach.zzv(r3)
            goto L_0x030a
        L_0x0172:
            boolean r9 = r15.zzV(r1, r11, r5)
            if (r9 == 0) goto L_0x037e
            long r3 = zzD(r1, r3)
            int r9 = r11 << 3
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r9)
            int r3 = com.google.android.gms.internal.pal.zzach.zzB(r3)
            goto L_0x019b
        L_0x0187:
            boolean r9 = r15.zzV(r1, r11, r5)
            if (r9 == 0) goto L_0x037e
            long r3 = zzD(r1, r3)
            int r9 = r11 << 3
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r9)
            int r3 = com.google.android.gms.internal.pal.zzach.zzB(r3)
        L_0x019b:
            int r9 = r9 + r3
            int r6 = r6 + r9
            goto L_0x037e
        L_0x019f:
            boolean r3 = r15.zzV(r1, r11, r5)
            if (r3 == 0) goto L_0x037e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
        L_0x01ab:
            int r3 = r3 + 4
            goto L_0x037d
        L_0x01af:
            boolean r3 = r15.zzV(r1, r11, r5)
            if (r3 == 0) goto L_0x037e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
        L_0x01bb:
            int r3 = r3 + 8
            goto L_0x037d
        L_0x01bf:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.lang.Object r4 = r15.zzH(r5)
            com.google.android.gms.internal.pal.zzaea.zza(r11, r3, r4)
            goto L_0x037e
        L_0x01cc:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.pal.zzaer r4 = r15.zzF(r5)
            int r3 = com.google.android.gms.internal.pal.zzaet.zzj(r11, r3, r4)
            goto L_0x037d
        L_0x01dc:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzt(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x01f2:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzr(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x0208:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzi(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x021e:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzg(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x0234:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zze(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x024a:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzw(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x0260:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzb(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x0276:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzg(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x028c:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzi(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x02a1:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzl(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x02b6:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzy(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x02cb:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzn(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x02e0:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzg(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0309
        L_0x02f5:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzi(r3)
            if (r3 <= 0) goto L_0x037e
            int r4 = com.google.android.gms.internal.pal.zzach.zzz(r11)
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
        L_0x0309:
            int r4 = r4 + r9
        L_0x030a:
            int r4 = r4 + r3
        L_0x030b:
            int r6 = r6 + r4
            goto L_0x037e
        L_0x030e:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            r9 = 0
            int r3 = com.google.android.gms.internal.pal.zzaet.zzs(r11, r3, r9)
            goto L_0x0349
        L_0x031a:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzq(r11, r3, r9)
            goto L_0x0349
        L_0x0326:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzh(r11, r3, r9)
            goto L_0x0349
        L_0x0332:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzf(r11, r3, r9)
            goto L_0x0349
        L_0x033e:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzd(r11, r3, r9)
        L_0x0349:
            int r6 = r6 + r3
            r12 = r9
            goto L_0x054e
        L_0x034d:
            r9 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzv(r11, r3, r9)
            goto L_0x037d
        L_0x0359:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzc(r11, r3)
            goto L_0x037d
        L_0x0364:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.pal.zzaer r4 = r15.zzF(r5)
            int r3 = com.google.android.gms.internal.pal.zzaet.zzp(r11, r3, r4)
            goto L_0x037d
        L_0x0373:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzu(r11, r3)
        L_0x037d:
            int r6 = r6 + r3
        L_0x037e:
            r12 = 0
            goto L_0x054e
        L_0x0381:
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            r12 = 0
            int r3 = com.google.android.gms.internal.pal.zzaet.zza(r11, r3, r12)
            goto L_0x03e0
        L_0x038d:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzf(r11, r3, r12)
            goto L_0x03e0
        L_0x0399:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzh(r11, r3, r12)
            goto L_0x03e0
        L_0x03a5:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzk(r11, r3, r12)
            goto L_0x03e0
        L_0x03b1:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzx(r11, r3, r12)
            goto L_0x03e0
        L_0x03bd:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzm(r11, r3, r12)
            goto L_0x03e0
        L_0x03c9:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzf(r11, r3, r12)
            goto L_0x03e0
        L_0x03d5:
            r12 = 0
            java.lang.Object r3 = r2.getObject(r1, r3)
            java.util.List r3 = (java.util.List) r3
            int r3 = com.google.android.gms.internal.pal.zzaet.zzh(r11, r3, r12)
        L_0x03e0:
            int r6 = r6 + r3
            goto L_0x054e
        L_0x03e3:
            r12 = 0
            r9 = r7 & r10
            if (r9 == 0) goto L_0x054e
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.pal.zzaef r3 = (com.google.android.gms.internal.pal.zzaef) r3
            com.google.android.gms.internal.pal.zzaer r4 = r15.zzF(r5)
            int r3 = com.google.android.gms.internal.pal.zzach.zzu(r11, r3, r4)
            goto L_0x03e0
        L_0x03f7:
            r12 = 0
            r10 = r10 & r7
            if (r10 == 0) goto L_0x054e
            long r3 = r2.getLong(r1, r3)
            int r10 = r11 << 3
            int r10 = com.google.android.gms.internal.pal.zzach.zzA(r10)
            long r13 = r3 + r3
            long r3 = r3 >> r9
            long r3 = r3 ^ r13
            int r3 = com.google.android.gms.internal.pal.zzach.zzB(r3)
            int r10 = r10 + r3
            int r6 = r6 + r10
            goto L_0x054e
        L_0x0411:
            r12 = 0
            r9 = r7 & r10
            if (r9 == 0) goto L_0x054e
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r9 = r3 + r3
            int r3 = r3 >> 31
            r3 = r3 ^ r9
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0503
        L_0x042b:
            r12 = 0
            r3 = r7 & r10
            if (r3 == 0) goto L_0x054e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x054a
        L_0x0438:
            r12 = 0
            r3 = r7 & r10
            if (r3 == 0) goto L_0x054e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x053b
        L_0x0445:
            r12 = 0
            r9 = r7 & r10
            if (r9 == 0) goto L_0x054e
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = com.google.android.gms.internal.pal.zzach.zzv(r3)
            goto L_0x0503
        L_0x045a:
            r12 = 0
            r9 = r7 & r10
            if (r9 == 0) goto L_0x054e
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0503
        L_0x046f:
            r12 = 0
            r9 = r7 & r10
            if (r9 == 0) goto L_0x054e
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.pal.zzaby r3 = (com.google.android.gms.internal.pal.zzaby) r3
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
        L_0x0488:
            int r9 = r9 + r3
            int r4 = r4 + r9
            goto L_0x0504
        L_0x048c:
            r12 = 0
            r9 = r7 & r10
            if (r9 == 0) goto L_0x054e
            java.lang.Object r3 = r2.getObject(r1, r3)
            com.google.android.gms.internal.pal.zzaer r4 = r15.zzF(r5)
            int r3 = com.google.android.gms.internal.pal.zzaet.zzo(r11, r3, r4)
            goto L_0x03e0
        L_0x049f:
            r12 = 0
            r9 = r7 & r10
            if (r9 == 0) goto L_0x054e
            java.lang.Object r3 = r2.getObject(r1, r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.pal.zzaby
            if (r4 == 0) goto L_0x04bd
            com.google.android.gms.internal.pal.zzaby r3 = (com.google.android.gms.internal.pal.zzaby) r3
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = r3.zzd()
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x0488
        L_0x04bd:
            java.lang.String r3 = (java.lang.String) r3
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = com.google.android.gms.internal.pal.zzach.zzy(r3)
            goto L_0x0503
        L_0x04ca:
            r12 = 0
            r3 = r7 & r10
            if (r3 == 0) goto L_0x054e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            int r3 = r3 + r14
            goto L_0x03e0
        L_0x04d8:
            r12 = 0
            r3 = r7 & r10
            if (r3 == 0) goto L_0x054e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x053b
        L_0x04e4:
            r12 = 0
            r3 = r7 & r10
            if (r3 == 0) goto L_0x054e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
            goto L_0x054a
        L_0x04f0:
            r12 = 0
            r9 = r7 & r10
            if (r9 == 0) goto L_0x054e
            int r3 = r2.getInt(r1, r3)
            int r4 = r11 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            int r3 = com.google.android.gms.internal.pal.zzach.zzv(r3)
        L_0x0503:
            int r4 = r4 + r3
        L_0x0504:
            int r6 = r6 + r4
            goto L_0x054e
        L_0x0506:
            r12 = 0
            r9 = r7 & r10
            if (r9 == 0) goto L_0x054e
            long r3 = r2.getLong(r1, r3)
            int r9 = r11 << 3
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r9)
            int r3 = com.google.android.gms.internal.pal.zzach.zzB(r3)
            goto L_0x052d
        L_0x051a:
            r12 = 0
            r9 = r7 & r10
            if (r9 == 0) goto L_0x054e
            long r3 = r2.getLong(r1, r3)
            int r9 = r11 << 3
            int r9 = com.google.android.gms.internal.pal.zzach.zzA(r9)
            int r3 = com.google.android.gms.internal.pal.zzach.zzB(r3)
        L_0x052d:
            int r9 = r9 + r3
            int r6 = r6 + r9
            goto L_0x054e
        L_0x0530:
            r12 = 0
            r3 = r7 & r10
            if (r3 == 0) goto L_0x054e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
        L_0x053b:
            int r3 = r3 + 4
            goto L_0x03e0
        L_0x053f:
            r12 = 0
            r3 = r7 & r10
            if (r3 == 0) goto L_0x054e
            int r3 = r11 << 3
            int r3 = com.google.android.gms.internal.pal.zzach.zzA(r3)
        L_0x054a:
            int r3 = r3 + 8
            goto L_0x03e0
        L_0x054e:
            int r5 = r5 + 3
            r4 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x000c
        L_0x0555:
            com.google.android.gms.internal.pal.zzafi r2 = r0.zzo
            java.lang.Object r3 = r2.zzd(r1)
            int r2 = r2.zza(r3)
            int r6 = r6 + r2
            boolean r2 = r0.zzh
            if (r2 != 0) goto L_0x0565
            return r6
        L_0x0565:
            com.google.android.gms.internal.pal.zzacn r2 = r0.zzp
            r2.zza(r1)
            r1 = 0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzaei.zzq(java.lang.Object):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02fc, code lost:
        r5 = r5 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0474, code lost:
        r5 = r5 + (r6 + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x04c4, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x04f6, code lost:
        r5 = r5 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x04f7, code lost:
        r3 = r3 + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0522, code lost:
        r3 = r3 + (r6 + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x0531, code lost:
        r4 = r4 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0541, code lost:
        r4 = r4 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0545, code lost:
        r2 = r2 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzr(java.lang.Object r12) {
        /*
            r11 = this;
            sun.misc.Unsafe r0 = zzb
            r1 = 0
            r2 = r1
            r3 = r2
        L_0x0005:
            int[] r4 = r11.zzc
            int r4 = r4.length
            if (r2 >= r4) goto L_0x0549
            int r4 = r11.zzC(r2)
            int r5 = zzB(r4)
            int[] r6 = r11.zzc
            r6 = r6[r2]
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r7
            long r7 = (long) r4
            com.google.android.gms.internal.pal.zzacs r4 = com.google.android.gms.internal.pal.zzacs.DOUBLE_LIST_PACKED
            int r4 = r4.zza()
            if (r5 < r4) goto L_0x0031
            com.google.android.gms.internal.pal.zzacs r4 = com.google.android.gms.internal.pal.zzacs.SINT64_LIST_PACKED
            int r4 = r4.zza()
            if (r5 > r4) goto L_0x0031
            int[] r4 = r11.zzc
            int r9 = r2 + 2
            r4 = r4[r9]
        L_0x0031:
            r4 = 63
            switch(r5) {
                case 0: goto L_0x0535;
                case 1: goto L_0x0525;
                case 2: goto L_0x050e;
                case 3: goto L_0x04f9;
                case 4: goto L_0x04e2;
                case 5: goto L_0x04d5;
                case 6: goto L_0x04c8;
                case 7: goto L_0x04b8;
                case 8: goto L_0x048c;
                case 9: goto L_0x0478;
                case 10: goto L_0x045a;
                case 11: goto L_0x0444;
                case 12: goto L_0x042e;
                case 13: goto L_0x0420;
                case 14: goto L_0x0412;
                case 15: goto L_0x03f7;
                case 16: goto L_0x03dc;
                case 17: goto L_0x03c7;
                case 18: goto L_0x03ba;
                case 19: goto L_0x03af;
                case 20: goto L_0x03a4;
                case 21: goto L_0x0399;
                case 22: goto L_0x038e;
                case 23: goto L_0x0383;
                case 24: goto L_0x0378;
                case 25: goto L_0x036d;
                case 26: goto L_0x0362;
                case 27: goto L_0x0353;
                case 28: goto L_0x0347;
                case 29: goto L_0x033b;
                case 30: goto L_0x032f;
                case 31: goto L_0x0323;
                case 32: goto L_0x0317;
                case 33: goto L_0x030b;
                case 34: goto L_0x02ff;
                case 35: goto L_0x02e8;
                case 36: goto L_0x02d3;
                case 37: goto L_0x02be;
                case 38: goto L_0x02a9;
                case 39: goto L_0x0294;
                case 40: goto L_0x027f;
                case 41: goto L_0x0269;
                case 42: goto L_0x0253;
                case 43: goto L_0x023d;
                case 44: goto L_0x0227;
                case 45: goto L_0x0211;
                case 46: goto L_0x01fb;
                case 47: goto L_0x01e5;
                case 48: goto L_0x01cf;
                case 49: goto L_0x01bf;
                case 50: goto L_0x01b2;
                case 51: goto L_0x01a4;
                case 52: goto L_0x0196;
                case 53: goto L_0x0180;
                case 54: goto L_0x016a;
                case 55: goto L_0x0154;
                case 56: goto L_0x0146;
                case 57: goto L_0x0138;
                case 58: goto L_0x012a;
                case 59: goto L_0x00fc;
                case 60: goto L_0x00e8;
                case 61: goto L_0x00cc;
                case 62: goto L_0x00b6;
                case 63: goto L_0x00a0;
                case 64: goto L_0x0092;
                case 65: goto L_0x0084;
                case 66: goto L_0x0069;
                case 67: goto L_0x004e;
                case 68: goto L_0x0038;
                default: goto L_0x0036;
            }
        L_0x0036:
            goto L_0x0545
        L_0x0038:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            com.google.android.gms.internal.pal.zzaef r4 = (com.google.android.gms.internal.pal.zzaef) r4
            com.google.android.gms.internal.pal.zzaer r5 = r11.zzF(r2)
            int r4 = com.google.android.gms.internal.pal.zzach.zzu(r6, r4, r5)
            goto L_0x03c4
        L_0x004e:
            boolean r5 = r11.zzV(r12, r6, r2)
            if (r5 == 0) goto L_0x0545
            long r7 = zzD(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            long r9 = r7 + r7
            long r6 = r7 >> r4
            long r6 = r6 ^ r9
            int r4 = com.google.android.gms.internal.pal.zzach.zzB(r6)
            goto L_0x04f6
        L_0x0069:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = zzs(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r6 = r4 + r4
            int r4 = r4 >> 31
            r4 = r4 ^ r6
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x04f6
        L_0x0084:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0541
        L_0x0092:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0531
        L_0x00a0:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = zzs(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = com.google.android.gms.internal.pal.zzach.zzv(r4)
            goto L_0x04f6
        L_0x00b6:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = zzs(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x04f6
        L_0x00cc:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            com.google.android.gms.internal.pal.zzaby r4 = (com.google.android.gms.internal.pal.zzaby) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0474
        L_0x00e8:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            com.google.android.gms.internal.pal.zzaer r5 = r11.zzF(r2)
            int r4 = com.google.android.gms.internal.pal.zzaet.zzo(r6, r4, r5)
            goto L_0x03c4
        L_0x00fc:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            boolean r5 = r4 instanceof com.google.android.gms.internal.pal.zzaby
            if (r5 == 0) goto L_0x011c
            com.google.android.gms.internal.pal.zzaby r4 = (com.google.android.gms.internal.pal.zzaby) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0474
        L_0x011c:
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = com.google.android.gms.internal.pal.zzach.zzy(r4)
            goto L_0x04f6
        L_0x012a:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x04c4
        L_0x0138:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0531
        L_0x0146:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0541
        L_0x0154:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = zzs(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = com.google.android.gms.internal.pal.zzach.zzv(r4)
            goto L_0x04f6
        L_0x016a:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            long r4 = zzD(r12, r7)
            int r6 = r6 << 3
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r6)
            int r4 = com.google.android.gms.internal.pal.zzach.zzB(r4)
            goto L_0x0522
        L_0x0180:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            long r4 = zzD(r12, r7)
            int r6 = r6 << 3
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r6)
            int r4 = com.google.android.gms.internal.pal.zzach.zzB(r4)
            goto L_0x0522
        L_0x0196:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0531
        L_0x01a4:
            boolean r4 = r11.zzV(r12, r6, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0541
        L_0x01b2:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.lang.Object r5 = r11.zzH(r2)
            com.google.android.gms.internal.pal.zzaea.zza(r6, r4, r5)
            goto L_0x0545
        L_0x01bf:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.pal.zzaer r5 = r11.zzF(r2)
            int r4 = com.google.android.gms.internal.pal.zzaet.zzj(r6, r4, r5)
            goto L_0x03c4
        L_0x01cf:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzt(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x01e5:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzr(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x01fb:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzi(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x0211:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzg(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x0227:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zze(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x023d:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzw(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x0253:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzb(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x0269:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzg(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x027f:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzi(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x0294:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzl(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x02a9:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzy(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x02be:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzn(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x02d3:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzg(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x02fc
        L_0x02e8:
            java.lang.Object r4 = r0.getObject(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzi(r4)
            if (r4 <= 0) goto L_0x0545
            int r5 = com.google.android.gms.internal.pal.zzach.zzz(r6)
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
        L_0x02fc:
            int r5 = r5 + r6
            goto L_0x04f6
        L_0x02ff:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzs(r6, r4, r1)
            goto L_0x03c4
        L_0x030b:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzq(r6, r4, r1)
            goto L_0x03c4
        L_0x0317:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzh(r6, r4, r1)
            goto L_0x03c4
        L_0x0323:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzf(r6, r4, r1)
            goto L_0x03c4
        L_0x032f:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzd(r6, r4, r1)
            goto L_0x03c4
        L_0x033b:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzv(r6, r4, r1)
            goto L_0x03c4
        L_0x0347:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzc(r6, r4)
            goto L_0x03c4
        L_0x0353:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.pal.zzaer r5 = r11.zzF(r2)
            int r4 = com.google.android.gms.internal.pal.zzaet.zzp(r6, r4, r5)
            goto L_0x03c4
        L_0x0362:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzu(r6, r4)
            goto L_0x03c4
        L_0x036d:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zza(r6, r4, r1)
            goto L_0x03c4
        L_0x0378:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzf(r6, r4, r1)
            goto L_0x03c4
        L_0x0383:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzh(r6, r4, r1)
            goto L_0x03c4
        L_0x038e:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzk(r6, r4, r1)
            goto L_0x03c4
        L_0x0399:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzx(r6, r4, r1)
            goto L_0x03c4
        L_0x03a4:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzm(r6, r4, r1)
            goto L_0x03c4
        L_0x03af:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzf(r6, r4, r1)
            goto L_0x03c4
        L_0x03ba:
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.pal.zzaet.zzh(r6, r4, r1)
        L_0x03c4:
            int r3 = r3 + r4
            goto L_0x0545
        L_0x03c7:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            com.google.android.gms.internal.pal.zzaef r4 = (com.google.android.gms.internal.pal.zzaef) r4
            com.google.android.gms.internal.pal.zzaer r5 = r11.zzF(r2)
            int r4 = com.google.android.gms.internal.pal.zzach.zzu(r6, r4, r5)
            goto L_0x03c4
        L_0x03dc:
            boolean r5 = r11.zzS(r12, r2)
            if (r5 == 0) goto L_0x0545
            long r7 = com.google.android.gms.internal.pal.zzafs.zzd(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            long r9 = r7 + r7
            long r6 = r7 >> r4
            long r6 = r6 ^ r9
            int r4 = com.google.android.gms.internal.pal.zzach.zzB(r6)
            goto L_0x04f6
        L_0x03f7:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = com.google.android.gms.internal.pal.zzafs.zzc(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r6 = r4 + r4
            int r4 = r4 >> 31
            r4 = r4 ^ r6
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x04f6
        L_0x0412:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0541
        L_0x0420:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0531
        L_0x042e:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = com.google.android.gms.internal.pal.zzafs.zzc(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = com.google.android.gms.internal.pal.zzach.zzv(r4)
            goto L_0x04f6
        L_0x0444:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = com.google.android.gms.internal.pal.zzafs.zzc(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x04f6
        L_0x045a:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            com.google.android.gms.internal.pal.zzaby r4 = (com.google.android.gms.internal.pal.zzaby) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
        L_0x0474:
            int r6 = r6 + r4
            int r5 = r5 + r6
            goto L_0x04f7
        L_0x0478:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            com.google.android.gms.internal.pal.zzaer r5 = r11.zzF(r2)
            int r4 = com.google.android.gms.internal.pal.zzaet.zzo(r6, r4, r5)
            goto L_0x03c4
        L_0x048c:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r12, r7)
            boolean r5 = r4 instanceof com.google.android.gms.internal.pal.zzaby
            if (r5 == 0) goto L_0x04ab
            com.google.android.gms.internal.pal.zzaby r4 = (com.google.android.gms.internal.pal.zzaby) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = r4.zzd()
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0474
        L_0x04ab:
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = com.google.android.gms.internal.pal.zzach.zzy(r4)
            goto L_0x04f6
        L_0x04b8:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
        L_0x04c4:
            int r4 = r4 + 1
            goto L_0x03c4
        L_0x04c8:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0531
        L_0x04d5:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
            goto L_0x0541
        L_0x04e2:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = com.google.android.gms.internal.pal.zzafs.zzc(r12, r7)
            int r5 = r6 << 3
            int r5 = com.google.android.gms.internal.pal.zzach.zzA(r5)
            int r4 = com.google.android.gms.internal.pal.zzach.zzv(r4)
        L_0x04f6:
            int r5 = r5 + r4
        L_0x04f7:
            int r3 = r3 + r5
            goto L_0x0545
        L_0x04f9:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            long r4 = com.google.android.gms.internal.pal.zzafs.zzd(r12, r7)
            int r6 = r6 << 3
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r6)
            int r4 = com.google.android.gms.internal.pal.zzach.zzB(r4)
            goto L_0x0522
        L_0x050e:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            long r4 = com.google.android.gms.internal.pal.zzafs.zzd(r12, r7)
            int r6 = r6 << 3
            int r6 = com.google.android.gms.internal.pal.zzach.zzA(r6)
            int r4 = com.google.android.gms.internal.pal.zzach.zzB(r4)
        L_0x0522:
            int r6 = r6 + r4
            int r3 = r3 + r6
            goto L_0x0545
        L_0x0525:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
        L_0x0531:
            int r4 = r4 + 4
            goto L_0x03c4
        L_0x0535:
            boolean r4 = r11.zzS(r12, r2)
            if (r4 == 0) goto L_0x0545
            int r4 = r6 << 3
            int r4 = com.google.android.gms.internal.pal.zzach.zzA(r4)
        L_0x0541:
            int r4 = r4 + 8
            goto L_0x03c4
        L_0x0545:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x0549:
            com.google.android.gms.internal.pal.zzafi r0 = r11.zzo
            java.lang.Object r12 = r0.zzd(r12)
            int r12 = r0.zza(r12)
            int r3 = r3 + r12
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzaei.zzr(java.lang.Object):int");
    }

    private static int zzs(Object obj, long j) {
        return ((Integer) zzafs.zzf(obj, j)).intValue();
    }

    private final int zzt(Object obj, byte[] bArr, int i, int i2, int i3, long j, zzabl zzabl) throws IOException {
        Unsafe unsafe = zzb;
        Object zzH = zzH(i3);
        Object object = unsafe.getObject(obj, j);
        if (zzaea.zzb(object)) {
            zzadz zzb2 = zzadz.zza().zzb();
            zzaea.zzc(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        zzady zzady = (zzady) zzH;
        throw null;
    }

    private final int zzu(Object obj, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzabl zzabl) throws IOException {
        Object obj2 = obj;
        byte[] bArr2 = bArr;
        int i9 = i;
        int i10 = i3;
        int i11 = i4;
        int i12 = i5;
        long j2 = j;
        int i13 = i8;
        zzabl zzabl2 = zzabl;
        Unsafe unsafe = zzb;
        long j3 = (long) (this.zzc[i13 + 2] & 1048575);
        switch (i7) {
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
                if (i12 == 1) {
                    unsafe.putObject(obj2, j2, Double.valueOf(Double.longBitsToDouble(zzabm.zzn(bArr, i))));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 52:
                if (i12 == 5) {
                    unsafe.putObject(obj2, j2, Float.valueOf(Float.intBitsToFloat(zzabm.zzb(bArr, i))));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 4;
                }
                break;
            case 53:
            case 54:
                if (i12 == 0) {
                    int zzm2 = zzabm.zzm(bArr2, i9, zzabl2);
                    unsafe.putObject(obj2, j2, Long.valueOf(zzabl2.zzb));
                    unsafe.putInt(obj2, j3, i11);
                    return zzm2;
                }
                break;
            case 55:
            case Elf64.Ehdr.E_SHSTRNDX:
                if (i12 == 0) {
                    int zzj2 = zzabm.zzj(bArr2, i9, zzabl2);
                    unsafe.putObject(obj2, j2, Integer.valueOf(zzabl2.zza));
                    unsafe.putInt(obj2, j3, i11);
                    return zzj2;
                }
                break;
            case 56:
            case 65:
                if (i12 == 1) {
                    unsafe.putObject(obj2, j2, Long.valueOf(zzabm.zzn(bArr, i)));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 8;
                }
                break;
            case 57:
            case 64:
                if (i12 == 5) {
                    unsafe.putObject(obj2, j2, Integer.valueOf(zzabm.zzb(bArr, i)));
                    unsafe.putInt(obj2, j3, i11);
                    return i9 + 4;
                }
                break;
            case Elf64.Ehdr.E_SHENTSIZE:
                if (i12 == 0) {
                    int zzm3 = zzabm.zzm(bArr2, i9, zzabl2);
                    unsafe.putObject(obj2, j2, Boolean.valueOf(zzabl2.zzb != 0));
                    unsafe.putInt(obj2, j3, i11);
                    return zzm3;
                }
                break;
            case 59:
                if (i12 == 2) {
                    int zzj3 = zzabm.zzj(bArr2, i9, zzabl2);
                    int i14 = zzabl2.zza;
                    if (i14 == 0) {
                        unsafe.putObject(obj2, j2, "");
                    } else if ((i6 & C.BUFFER_FLAG_LAST_SAMPLE) == 0 || zzafx.zzf(bArr2, zzj3, zzj3 + i14)) {
                        unsafe.putObject(obj2, j2, new String(bArr2, zzj3, i14, zzadg.zzb));
                        zzj3 += i14;
                    } else {
                        throw zzadi.zzd();
                    }
                    unsafe.putInt(obj2, j3, i11);
                    return zzj3;
                }
                break;
            case 60:
                if (i12 == 2) {
                    int zzd2 = zzabm.zzd(zzF(i13), bArr2, i9, i2, zzabl2);
                    Object object = unsafe.getInt(obj2, j3) == i11 ? unsafe.getObject(obj2, j2) : null;
                    if (object == null) {
                        unsafe.putObject(obj2, j2, zzabl2.zzc);
                    } else {
                        unsafe.putObject(obj2, j2, zzadg.zzg(object, zzabl2.zzc));
                    }
                    unsafe.putInt(obj2, j3, i11);
                    return zzd2;
                }
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT:
                if (i12 == 2) {
                    int zza2 = zzabm.zza(bArr2, i9, zzabl2);
                    unsafe.putObject(obj2, j2, zzabl2.zzc);
                    unsafe.putInt(obj2, j3, i11);
                    return zza2;
                }
                break;
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
                if (i12 == 0) {
                    int zzj4 = zzabm.zzj(bArr2, i9, zzabl2);
                    int i15 = zzabl2.zza;
                    zzadd zzE = zzE(i13);
                    if (zzE == null || zzE.zza(i15)) {
                        unsafe.putObject(obj2, j2, Integer.valueOf(i15));
                        unsafe.putInt(obj2, j3, i11);
                    } else {
                        zzd(obj).zzh(i10, Long.valueOf((long) i15));
                    }
                    return zzj4;
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                if (i12 == 0) {
                    int zzj5 = zzabm.zzj(bArr2, i9, zzabl2);
                    unsafe.putObject(obj2, j2, Integer.valueOf(zzacc.zzs(zzabl2.zza)));
                    unsafe.putInt(obj2, j3, i11);
                    return zzj5;
                }
                break;
            case 67:
                if (i12 == 0) {
                    int zzm4 = zzabm.zzm(bArr2, i9, zzabl2);
                    unsafe.putObject(obj2, j2, Long.valueOf(zzacc.zzt(zzabl2.zzb)));
                    unsafe.putInt(obj2, j3, i11);
                    return zzm4;
                }
                break;
            case 68:
                if (i12 == 3) {
                    int zzc2 = zzabm.zzc(zzF(i13), bArr, i, i2, (i10 & -8) | 4, zzabl);
                    Object object2 = unsafe.getInt(obj2, j3) == i11 ? unsafe.getObject(obj2, j2) : null;
                    if (object2 == null) {
                        unsafe.putObject(obj2, j2, zzabl2.zzc);
                    } else {
                        unsafe.putObject(obj2, j2, zzadg.zzg(object2, zzabl2.zzc));
                    }
                    unsafe.putInt(obj2, j3, i11);
                    return zzc2;
                }
                break;
        }
        return i9;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02c5, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02f5, code lost:
        if (r0 != r14) goto L_0x02ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0317, code lost:
        if (r0 != r14) goto L_0x02ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00fa, code lost:
        r6 = r6 | r22;
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x019e, code lost:
        r8 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01d5, code lost:
        r6 = r6 | r22;
        r0 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x020c, code lost:
        r0 = r8 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x020e, code lost:
        r6 = r6 | r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0211, code lost:
        r2 = r8;
        r28 = r9;
        r24 = r10;
        r18 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02a9, code lost:
        if (r0 != r32) goto L_0x02ab;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzv(java.lang.Object r30, byte[] r31, int r32, int r33, com.google.android.gms.internal.pal.zzabl r34) throws java.io.IOException {
        /*
            r29 = this;
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            sun.misc.Unsafe r9 = zzb
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r16 = 0
            r8 = -1
            r0 = r32
            r1 = r8
            r7 = r10
            r2 = r16
            r6 = r2
        L_0x0019:
            if (r0 >= r13) goto L_0x0341
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002b
            int r0 = com.google.android.gms.internal.pal.zzabm.zzk(r0, r12, r3, r11)
            int r3 = r11.zza
            r4 = r0
            r17 = r3
            goto L_0x002e
        L_0x002b:
            r17 = r0
            r4 = r3
        L_0x002e:
            int r5 = r17 >>> 3
            r3 = r17 & 7
            if (r5 <= r1) goto L_0x003b
            int r2 = r2 / 3
            int r0 = r15.zzy(r5, r2)
            goto L_0x003f
        L_0x003b:
            int r0 = r15.zzx(r5)
        L_0x003f:
            r2 = r0
            if (r2 != r8) goto L_0x004d
            r2 = r4
            r21 = r5
            r18 = r8
            r28 = r9
            r24 = r16
            goto L_0x031a
        L_0x004d:
            int[] r0 = r15.zzc
            int r1 = r2 + 1
            r1 = r0[r1]
            int r8 = zzB(r1)
            r32 = r5
            r5 = r1 & r10
            long r10 = (long) r5
            r5 = 17
            r20 = r10
            if (r8 > r5) goto L_0x021a
            int r5 = r2 + 2
            r0 = r0[r5]
            int r5 = r0 >>> 20
            r11 = 1
            int r22 = r11 << r5
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            if (r0 == r7) goto L_0x007f
            if (r7 == r5) goto L_0x0077
            long r10 = (long) r7
            r9.putInt(r14, r10, r6)
        L_0x0077:
            if (r0 == r5) goto L_0x007e
            long r6 = (long) r0
            int r6 = r9.getInt(r14, r6)
        L_0x007e:
            r7 = r0
        L_0x007f:
            r0 = 5
            switch(r8) {
                case 0: goto L_0x01f4;
                case 1: goto L_0x01da;
                case 2: goto L_0x01b8;
                case 3: goto L_0x01b8;
                case 4: goto L_0x01a1;
                case 5: goto L_0x0182;
                case 6: goto L_0x016c;
                case 7: goto L_0x014c;
                case 8: goto L_0x0129;
                case 9: goto L_0x00ff;
                case 10: goto L_0x00e5;
                case 11: goto L_0x01a1;
                case 12: goto L_0x00d0;
                case 13: goto L_0x016c;
                case 14: goto L_0x0182;
                case 15: goto L_0x00b7;
                case 16: goto L_0x008d;
                default: goto L_0x0083;
            }
        L_0x0083:
            r21 = r32
            r11 = r34
            r10 = r2
            r8 = r4
            r24 = r5
            goto L_0x0211
        L_0x008d:
            if (r3 != 0) goto L_0x00ae
            r11 = r34
            r0 = r20
            int r8 = com.google.android.gms.internal.pal.zzabm.zzm(r12, r4, r11)
            long r3 = r11.zzb
            long r19 = com.google.android.gms.internal.pal.zzacc.zzt(r3)
            r3 = r0
            r0 = r9
            r1 = r30
            r10 = r2
            r2 = r3
            r21 = r32
            r24 = r5
            r4 = r19
            r0.putLong(r1, r2, r4)
            goto L_0x01d5
        L_0x00ae:
            r21 = r32
            r11 = r34
            r10 = r2
            r24 = r5
            goto L_0x019e
        L_0x00b7:
            r11 = r34
            r10 = r2
            r24 = r5
            r0 = r20
            r21 = r32
            if (r3 != 0) goto L_0x019e
            int r2 = com.google.android.gms.internal.pal.zzabm.zzj(r12, r4, r11)
            int r3 = r11.zza
            int r3 = com.google.android.gms.internal.pal.zzacc.zzs(r3)
            r9.putInt(r14, r0, r3)
            goto L_0x00fa
        L_0x00d0:
            r11 = r34
            r10 = r2
            r24 = r5
            r0 = r20
            r21 = r32
            if (r3 != 0) goto L_0x019e
            int r2 = com.google.android.gms.internal.pal.zzabm.zzj(r12, r4, r11)
            int r3 = r11.zza
            r9.putInt(r14, r0, r3)
            goto L_0x00fa
        L_0x00e5:
            r11 = r34
            r10 = r2
            r24 = r5
            r0 = r20
            r2 = 2
            r21 = r32
            if (r3 != r2) goto L_0x019e
            int r2 = com.google.android.gms.internal.pal.zzabm.zza(r12, r4, r11)
            java.lang.Object r3 = r11.zzc
            r9.putObject(r14, r0, r3)
        L_0x00fa:
            r6 = r6 | r22
            r0 = r2
            goto L_0x025d
        L_0x00ff:
            r11 = r34
            r10 = r2
            r24 = r5
            r0 = r20
            r2 = 2
            r21 = r32
            if (r3 != r2) goto L_0x019e
            com.google.android.gms.internal.pal.zzaer r2 = r15.zzF(r10)
            int r2 = com.google.android.gms.internal.pal.zzabm.zzd(r2, r12, r4, r13, r11)
            java.lang.Object r3 = r9.getObject(r14, r0)
            if (r3 != 0) goto L_0x011f
            java.lang.Object r3 = r11.zzc
            r9.putObject(r14, r0, r3)
            goto L_0x00fa
        L_0x011f:
            java.lang.Object r4 = r11.zzc
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzadg.zzg(r3, r4)
            r9.putObject(r14, r0, r3)
            goto L_0x00fa
        L_0x0129:
            r11 = r34
            r10 = r2
            r24 = r5
            r25 = r20
            r0 = 2
            r21 = r32
            if (r3 != r0) goto L_0x019e
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r1
            if (r0 != 0) goto L_0x013f
            int r0 = com.google.android.gms.internal.pal.zzabm.zzg(r12, r4, r11)
            goto L_0x0143
        L_0x013f:
            int r0 = com.google.android.gms.internal.pal.zzabm.zzh(r12, r4, r11)
        L_0x0143:
            java.lang.Object r1 = r11.zzc
            r2 = r25
            r9.putObject(r14, r2, r1)
            goto L_0x020e
        L_0x014c:
            r11 = r34
            r10 = r2
            r24 = r5
            r1 = r20
            r21 = r32
            if (r3 != 0) goto L_0x019e
            int r0 = com.google.android.gms.internal.pal.zzabm.zzm(r12, r4, r11)
            long r3 = r11.zzb
            r19 = 0
            int r3 = (r3 > r19 ? 1 : (r3 == r19 ? 0 : -1))
            if (r3 == 0) goto L_0x0165
            r3 = 1
            goto L_0x0167
        L_0x0165:
            r3 = r16
        L_0x0167:
            com.google.android.gms.internal.pal.zzafs.zzm(r14, r1, r3)
            goto L_0x020e
        L_0x016c:
            r11 = r34
            r10 = r2
            r24 = r5
            r1 = r20
            r21 = r32
            if (r3 != r0) goto L_0x019e
            int r0 = com.google.android.gms.internal.pal.zzabm.zzb(r12, r4)
            r9.putInt(r14, r1, r0)
            int r0 = r4 + 4
            goto L_0x020e
        L_0x0182:
            r11 = r34
            r10 = r2
            r24 = r5
            r1 = r20
            r0 = 1
            r21 = r32
            if (r3 != r0) goto L_0x019e
            long r19 = com.google.android.gms.internal.pal.zzabm.zzn(r12, r4)
            r0 = r9
            r2 = r1
            r1 = r30
            r8 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
            goto L_0x020c
        L_0x019e:
            r8 = r4
            goto L_0x0211
        L_0x01a1:
            r11 = r34
            r10 = r2
            r8 = r4
            r24 = r5
            r4 = r20
            r21 = r32
            if (r3 != 0) goto L_0x0211
            int r0 = com.google.android.gms.internal.pal.zzabm.zzj(r12, r8, r11)
            int r1 = r11.zza
            r9.putInt(r14, r4, r1)
            goto L_0x020e
        L_0x01b8:
            r11 = r34
            r10 = r2
            r8 = r4
            r24 = r5
            r4 = r20
            r21 = r32
            if (r3 != 0) goto L_0x0211
            int r8 = com.google.android.gms.internal.pal.zzabm.zzm(r12, r8, r11)
            long r2 = r11.zzb
            r0 = r9
            r1 = r30
            r19 = r2
            r2 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
        L_0x01d5:
            r6 = r6 | r22
            r0 = r8
            goto L_0x025d
        L_0x01da:
            r11 = r34
            r10 = r2
            r8 = r4
            r24 = r5
            r4 = r20
            r21 = r32
            if (r3 != r0) goto L_0x0211
            int r0 = com.google.android.gms.internal.pal.zzabm.zzb(r12, r8)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.pal.zzafs.zzp(r14, r4, r0)
            int r0 = r8 + 4
            goto L_0x020e
        L_0x01f4:
            r11 = r34
            r10 = r2
            r8 = r4
            r24 = r5
            r4 = r20
            r0 = 1
            r21 = r32
            if (r3 != r0) goto L_0x0211
            long r0 = com.google.android.gms.internal.pal.zzabm.zzn(r12, r8)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.pal.zzafs.zzo(r14, r4, r0)
        L_0x020c:
            int r0 = r8 + 8
        L_0x020e:
            r6 = r6 | r22
            goto L_0x025d
        L_0x0211:
            r2 = r8
            r28 = r9
            r24 = r10
            r18 = -1
            goto L_0x031a
        L_0x021a:
            r11 = r34
            r10 = r2
            r2 = r4
            r4 = r20
            r24 = 1048575(0xfffff, float:1.469367E-39)
            r21 = r32
            r0 = 27
            if (r8 != r0) goto L_0x0271
            r0 = 2
            if (r3 != r0) goto L_0x0265
            java.lang.Object r0 = r9.getObject(r14, r4)
            com.google.android.gms.internal.pal.zzadf r0 = (com.google.android.gms.internal.pal.zzadf) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x0249
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0241
            r1 = 10
            goto L_0x0242
        L_0x0241:
            int r1 = r1 + r1
        L_0x0242:
            com.google.android.gms.internal.pal.zzadf r0 = r0.zzd(r1)
            r9.putObject(r14, r4, r0)
        L_0x0249:
            r5 = r0
            com.google.android.gms.internal.pal.zzaer r0 = r15.zzF(r10)
            r1 = r17
            r3 = r2
            r2 = r31
            r4 = r33
            r8 = r6
            r6 = r34
            int r0 = com.google.android.gms.internal.pal.zzabm.zze(r0, r1, r2, r3, r4, r5, r6)
            r6 = r8
        L_0x025d:
            r2 = r10
            r1 = r21
            r10 = r24
            r8 = -1
            goto L_0x0019
        L_0x0265:
            r14 = r2
            r23 = r6
            r15 = r7
            r28 = r9
            r24 = r10
            r18 = -1
            goto L_0x02f8
        L_0x0271:
            r0 = 49
            if (r8 > r0) goto L_0x02c7
            long r0 = (long) r1
            r19 = r0
            r0 = r29
            r1 = r30
            r32 = r2
            r2 = r31
            r22 = r3
            r3 = r32
            r25 = r4
            r4 = r33
            r5 = r17
            r15 = r6
            r6 = r21
            r23 = r15
            r15 = r7
            r7 = r22
            r27 = r8
            r18 = -1
            r8 = r10
            r28 = r9
            r24 = r10
            r9 = r19
            r11 = r27
            r12 = r25
            r14 = r34
            int r0 = r0.zzw(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            r14 = r32
            if (r0 == r14) goto L_0x02c5
        L_0x02ab:
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r7 = r15
            r8 = r18
            r1 = r21
            r6 = r23
            r2 = r24
            r9 = r28
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r15 = r29
            goto L_0x0019
        L_0x02c5:
            r2 = r0
            goto L_0x02f9
        L_0x02c7:
            r14 = r2
            r22 = r3
            r25 = r4
            r23 = r6
            r15 = r7
            r27 = r8
            r28 = r9
            r24 = r10
            r18 = -1
            r0 = 50
            r9 = r27
            if (r9 != r0) goto L_0x02fd
            r7 = r22
            r0 = 2
            if (r7 != r0) goto L_0x02f8
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r14
            r4 = r33
            r5 = r24
            r6 = r25
            r8 = r34
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r14) goto L_0x02c5
            goto L_0x02ab
        L_0x02f8:
            r2 = r14
        L_0x02f9:
            r7 = r15
            r6 = r23
            goto L_0x031a
        L_0x02fd:
            r7 = r22
            r0 = r29
            r8 = r1
            r1 = r30
            r2 = r31
            r3 = r14
            r4 = r33
            r5 = r17
            r6 = r21
            r10 = r25
            r12 = r24
            r13 = r34
            int r0 = r0.zzu(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r14) goto L_0x02c5
            goto L_0x02ab
        L_0x031a:
            com.google.android.gms.internal.pal.zzafj r4 = zzd(r30)
            r0 = r17
            r1 = r31
            r3 = r33
            r5 = r34
            int r0 = com.google.android.gms.internal.pal.zzabm.zzi(r0, r1, r2, r3, r4, r5)
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r8 = r18
            r1 = r21
            r2 = r24
            r9 = r28
            r10 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0019
        L_0x0341:
            r23 = r6
            r15 = r7
            r28 = r9
            r1 = r10
            if (r15 == r1) goto L_0x0353
            long r1 = (long) r15
            r3 = r30
            r6 = r23
            r4 = r28
            r4.putInt(r3, r1, r6)
        L_0x0353:
            r1 = r33
            if (r0 != r1) goto L_0x0358
            return r0
        L_0x0358:
            com.google.android.gms.internal.pal.zzadi r0 = com.google.android.gms.internal.pal.zzadi.zzg()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzaei.zzv(java.lang.Object, byte[], int, int, com.google.android.gms.internal.pal.zzabl):int");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0450 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01cf  */
    private final int zzw(java.lang.Object r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.pal.zzabl r29) throws java.io.IOException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r2 = r20
            r6 = r22
            r8 = r23
            r9 = r27
            r7 = r29
            sun.misc.Unsafe r11 = zzb
            java.lang.Object r12 = r11.getObject(r1, r9)
            com.google.android.gms.internal.pal.zzadf r12 = (com.google.android.gms.internal.pal.zzadf) r12
            boolean r13 = r12.zzc()
            if (r13 != 0) goto L_0x0032
            int r13 = r12.size()
            if (r13 != 0) goto L_0x002a
            r13 = 10
            goto L_0x002b
        L_0x002a:
            int r13 = r13 + r13
        L_0x002b:
            com.google.android.gms.internal.pal.zzadf r12 = r12.zzd(r13)
            r11.putObject(r1, r9, r12)
        L_0x0032:
            r9 = 5
            r10 = 0
            r13 = 1
            r14 = 2
            switch(r26) {
                case 18: goto L_0x03e1;
                case 19: goto L_0x0394;
                case 20: goto L_0x0351;
                case 21: goto L_0x0351;
                case 22: goto L_0x0336;
                case 23: goto L_0x02f5;
                case 24: goto L_0x02b4;
                case 25: goto L_0x025a;
                case 26: goto L_0x01a7;
                case 27: goto L_0x018c;
                case 28: goto L_0x0132;
                case 29: goto L_0x0336;
                case 30: goto L_0x00fa;
                case 31: goto L_0x02b4;
                case 32: goto L_0x02f5;
                case 33: goto L_0x00ab;
                case 34: goto L_0x005c;
                case 35: goto L_0x03e1;
                case 36: goto L_0x0394;
                case 37: goto L_0x0351;
                case 38: goto L_0x0351;
                case 39: goto L_0x0336;
                case 40: goto L_0x02f5;
                case 41: goto L_0x02b4;
                case 42: goto L_0x025a;
                case 43: goto L_0x0336;
                case 44: goto L_0x00fa;
                case 45: goto L_0x02b4;
                case 46: goto L_0x02f5;
                case 47: goto L_0x00ab;
                case 48: goto L_0x005c;
                default: goto L_0x003a;
            }
        L_0x003a:
            r1 = 3
            if (r6 != r1) goto L_0x044f
            com.google.android.gms.internal.pal.zzaer r1 = r15.zzF(r8)
            r6 = r2 & -8
            r6 = r6 | 4
            r21 = r1
            r22 = r17
            r23 = r18
            r24 = r19
            r25 = r6
            r26 = r29
            int r4 = com.google.android.gms.internal.pal.zzabm.zzc(r21, r22, r23, r24, r25, r26)
            java.lang.Object r8 = r7.zzc
            r12.add(r8)
            goto L_0x042d
        L_0x005c:
            if (r6 != r14) goto L_0x0080
            com.google.android.gms.internal.pal.zzadu r12 = (com.google.android.gms.internal.pal.zzadu) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x0067:
            if (r1 >= r2) goto L_0x0077
            int r1 = com.google.android.gms.internal.pal.zzabm.zzm(r3, r1, r7)
            long r4 = r7.zzb
            long r4 = com.google.android.gms.internal.pal.zzacc.zzt(r4)
            r12.zzf(r4)
            goto L_0x0067
        L_0x0077:
            if (r1 != r2) goto L_0x007b
            goto L_0x0450
        L_0x007b:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzi()
            throw r1
        L_0x0080:
            if (r6 != 0) goto L_0x044f
            com.google.android.gms.internal.pal.zzadu r12 = (com.google.android.gms.internal.pal.zzadu) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzm(r3, r4, r7)
            long r8 = r7.zzb
            long r8 = com.google.android.gms.internal.pal.zzacc.zzt(r8)
            r12.zzf(r8)
        L_0x0091:
            if (r1 >= r5) goto L_0x00aa
            int r4 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x009c
            goto L_0x00aa
        L_0x009c:
            int r1 = com.google.android.gms.internal.pal.zzabm.zzm(r3, r4, r7)
            long r8 = r7.zzb
            long r8 = com.google.android.gms.internal.pal.zzacc.zzt(r8)
            r12.zzf(r8)
            goto L_0x0091
        L_0x00aa:
            return r1
        L_0x00ab:
            if (r6 != r14) goto L_0x00cf
            com.google.android.gms.internal.pal.zzada r12 = (com.google.android.gms.internal.pal.zzada) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x00b6:
            if (r1 >= r2) goto L_0x00c6
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.pal.zzacc.zzs(r4)
            r12.zzg(r4)
            goto L_0x00b6
        L_0x00c6:
            if (r1 != r2) goto L_0x00ca
            goto L_0x0450
        L_0x00ca:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzi()
            throw r1
        L_0x00cf:
            if (r6 != 0) goto L_0x044f
            com.google.android.gms.internal.pal.zzada r12 = (com.google.android.gms.internal.pal.zzada) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.pal.zzacc.zzs(r4)
            r12.zzg(r4)
        L_0x00e0:
            if (r1 >= r5) goto L_0x00f9
            int r4 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x00eb
            goto L_0x00f9
        L_0x00eb:
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r4 = r7.zza
            int r4 = com.google.android.gms.internal.pal.zzacc.zzs(r4)
            r12.zzg(r4)
            goto L_0x00e0
        L_0x00f9:
            return r1
        L_0x00fa:
            if (r6 != r14) goto L_0x0101
            int r2 = com.google.android.gms.internal.pal.zzabm.zzf(r3, r4, r12, r7)
            goto L_0x0112
        L_0x0101:
            if (r6 != 0) goto L_0x044f
            r2 = r20
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r12
            r7 = r29
            int r2 = com.google.android.gms.internal.pal.zzabm.zzl(r2, r3, r4, r5, r6, r7)
        L_0x0112:
            com.google.android.gms.internal.pal.zzacz r1 = (com.google.android.gms.internal.pal.zzacz) r1
            com.google.android.gms.internal.pal.zzafj r3 = r1.zzc
            com.google.android.gms.internal.pal.zzafj r4 = com.google.android.gms.internal.pal.zzafj.zzc()
            if (r3 != r4) goto L_0x011d
            r3 = 0
        L_0x011d:
            com.google.android.gms.internal.pal.zzadd r4 = r15.zzE(r8)
            com.google.android.gms.internal.pal.zzafi r5 = r0.zzo
            r6 = r21
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzaet.zzC(r6, r12, r4, r3, r5)
            if (r3 != 0) goto L_0x012d
            goto L_0x027b
        L_0x012d:
            com.google.android.gms.internal.pal.zzafj r3 = (com.google.android.gms.internal.pal.zzafj) r3
            r1.zzc = r3
            return r2
        L_0x0132:
            if (r6 != r14) goto L_0x044f
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x0187
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x0182
            if (r4 != 0) goto L_0x0148
            com.google.android.gms.internal.pal.zzaby r4 = com.google.android.gms.internal.pal.zzaby.zzb
            r12.add(r4)
            goto L_0x0150
        L_0x0148:
            com.google.android.gms.internal.pal.zzaby r6 = com.google.android.gms.internal.pal.zzaby.zzo(r3, r1, r4)
            r12.add(r6)
        L_0x014f:
            int r1 = r1 + r4
        L_0x0150:
            if (r1 >= r5) goto L_0x0181
            int r4 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x015b
            goto L_0x0181
        L_0x015b:
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x017c
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x0177
            if (r4 != 0) goto L_0x016f
            com.google.android.gms.internal.pal.zzaby r4 = com.google.android.gms.internal.pal.zzaby.zzb
            r12.add(r4)
            goto L_0x0150
        L_0x016f:
            com.google.android.gms.internal.pal.zzaby r6 = com.google.android.gms.internal.pal.zzaby.zzo(r3, r1, r4)
            r12.add(r6)
            goto L_0x014f
        L_0x0177:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzi()
            throw r1
        L_0x017c:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzf()
            throw r1
        L_0x0181:
            return r1
        L_0x0182:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzi()
            throw r1
        L_0x0187:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzf()
            throw r1
        L_0x018c:
            if (r6 == r14) goto L_0x0190
            goto L_0x044f
        L_0x0190:
            com.google.android.gms.internal.pal.zzaer r1 = r15.zzF(r8)
            r21 = r1
            r22 = r20
            r23 = r17
            r24 = r18
            r25 = r19
            r26 = r12
            r27 = r29
            int r1 = com.google.android.gms.internal.pal.zzabm.zze(r21, r22, r23, r24, r25, r26, r27)
            return r1
        L_0x01a7:
            if (r6 != r14) goto L_0x044f
            r8 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r24 & r8
            int r1 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            java.lang.String r6 = ""
            if (r1 != 0) goto L_0x01fa
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x01f5
            if (r4 != 0) goto L_0x01c2
            r12.add(r6)
            goto L_0x01cd
        L_0x01c2:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.pal.zzadg.zzb
            r8.<init>(r3, r1, r4, r9)
            r12.add(r8)
        L_0x01cc:
            int r1 = r1 + r4
        L_0x01cd:
            if (r1 >= r5) goto L_0x0450
            int r4 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r8 = r7.zza
            if (r2 != r8) goto L_0x0450
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x01f0
            if (r4 != 0) goto L_0x01e5
            r12.add(r6)
            goto L_0x01cd
        L_0x01e5:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.pal.zzadg.zzb
            r8.<init>(r3, r1, r4, r9)
            r12.add(r8)
            goto L_0x01cc
        L_0x01f0:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzf()
            throw r1
        L_0x01f5:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzf()
            throw r1
        L_0x01fa:
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x0255
            if (r4 != 0) goto L_0x0208
            r12.add(r6)
            goto L_0x021b
        L_0x0208:
            int r8 = r1 + r4
            boolean r9 = com.google.android.gms.internal.pal.zzafx.zzf(r3, r1, r8)
            if (r9 == 0) goto L_0x0250
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.pal.zzadg.zzb
            r9.<init>(r3, r1, r4, r10)
            r12.add(r9)
        L_0x021a:
            r1 = r8
        L_0x021b:
            if (r1 >= r5) goto L_0x0450
            int r4 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r8 = r7.zza
            if (r2 != r8) goto L_0x0450
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r4 = r7.zza
            if (r4 < 0) goto L_0x024b
            if (r4 != 0) goto L_0x0233
            r12.add(r6)
            goto L_0x021b
        L_0x0233:
            int r8 = r1 + r4
            boolean r9 = com.google.android.gms.internal.pal.zzafx.zzf(r3, r1, r8)
            if (r9 == 0) goto L_0x0246
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.pal.zzadg.zzb
            r9.<init>(r3, r1, r4, r10)
            r12.add(r9)
            goto L_0x021a
        L_0x0246:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzd()
            throw r1
        L_0x024b:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzf()
            throw r1
        L_0x0250:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzd()
            throw r1
        L_0x0255:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzf()
            throw r1
        L_0x025a:
            r1 = 0
            if (r6 != r14) goto L_0x0283
            com.google.android.gms.internal.pal.zzabn r12 = (com.google.android.gms.internal.pal.zzabn) r12
            int r2 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r4 = r7.zza
            int r4 = r4 + r2
        L_0x0266:
            if (r2 >= r4) goto L_0x0279
            int r2 = com.google.android.gms.internal.pal.zzabm.zzm(r3, r2, r7)
            long r5 = r7.zzb
            int r5 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x0274
            r5 = r13
            goto L_0x0275
        L_0x0274:
            r5 = r1
        L_0x0275:
            r12.zze(r5)
            goto L_0x0266
        L_0x0279:
            if (r2 != r4) goto L_0x027e
        L_0x027b:
            r1 = r2
            goto L_0x0450
        L_0x027e:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzi()
            throw r1
        L_0x0283:
            if (r6 != 0) goto L_0x044f
            com.google.android.gms.internal.pal.zzabn r12 = (com.google.android.gms.internal.pal.zzabn) r12
            int r4 = com.google.android.gms.internal.pal.zzabm.zzm(r3, r4, r7)
            long r8 = r7.zzb
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x0293
            r6 = r13
            goto L_0x0294
        L_0x0293:
            r6 = r1
        L_0x0294:
            r12.zze(r6)
        L_0x0297:
            if (r4 >= r5) goto L_0x02b3
            int r6 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r8 = r7.zza
            if (r2 == r8) goto L_0x02a2
            goto L_0x02b3
        L_0x02a2:
            int r4 = com.google.android.gms.internal.pal.zzabm.zzm(r3, r6, r7)
            long r8 = r7.zzb
            int r6 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x02ae
            r6 = r13
            goto L_0x02af
        L_0x02ae:
            r6 = r1
        L_0x02af:
            r12.zze(r6)
            goto L_0x0297
        L_0x02b3:
            return r4
        L_0x02b4:
            if (r6 != r14) goto L_0x02d4
            com.google.android.gms.internal.pal.zzada r12 = (com.google.android.gms.internal.pal.zzada) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x02bf:
            if (r1 >= r2) goto L_0x02cb
            int r4 = com.google.android.gms.internal.pal.zzabm.zzb(r3, r1)
            r12.zzg(r4)
            int r1 = r1 + 4
            goto L_0x02bf
        L_0x02cb:
            if (r1 != r2) goto L_0x02cf
            goto L_0x0450
        L_0x02cf:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzi()
            throw r1
        L_0x02d4:
            if (r6 != r9) goto L_0x044f
            com.google.android.gms.internal.pal.zzada r12 = (com.google.android.gms.internal.pal.zzada) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzb(r17, r18)
            r12.zzg(r1)
        L_0x02df:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x02f4
            int r4 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x02ec
            goto L_0x02f4
        L_0x02ec:
            int r1 = com.google.android.gms.internal.pal.zzabm.zzb(r3, r4)
            r12.zzg(r1)
            goto L_0x02df
        L_0x02f4:
            return r1
        L_0x02f5:
            if (r6 != r14) goto L_0x0315
            com.google.android.gms.internal.pal.zzadu r12 = (com.google.android.gms.internal.pal.zzadu) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x0300:
            if (r1 >= r2) goto L_0x030c
            long r4 = com.google.android.gms.internal.pal.zzabm.zzn(r3, r1)
            r12.zzf(r4)
            int r1 = r1 + 8
            goto L_0x0300
        L_0x030c:
            if (r1 != r2) goto L_0x0310
            goto L_0x0450
        L_0x0310:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzi()
            throw r1
        L_0x0315:
            if (r6 != r13) goto L_0x044f
            com.google.android.gms.internal.pal.zzadu r12 = (com.google.android.gms.internal.pal.zzadu) r12
            long r8 = com.google.android.gms.internal.pal.zzabm.zzn(r17, r18)
            r12.zzf(r8)
        L_0x0320:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x0335
            int r4 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x032d
            goto L_0x0335
        L_0x032d:
            long r8 = com.google.android.gms.internal.pal.zzabm.zzn(r3, r4)
            r12.zzf(r8)
            goto L_0x0320
        L_0x0335:
            return r1
        L_0x0336:
            if (r6 != r14) goto L_0x033e
            int r1 = com.google.android.gms.internal.pal.zzabm.zzf(r3, r4, r12, r7)
            goto L_0x0450
        L_0x033e:
            if (r6 == 0) goto L_0x0342
            goto L_0x044f
        L_0x0342:
            r21 = r17
            r22 = r18
            r23 = r19
            r24 = r12
            r25 = r29
            int r1 = com.google.android.gms.internal.pal.zzabm.zzl(r20, r21, r22, r23, r24, r25)
            return r1
        L_0x0351:
            if (r6 != r14) goto L_0x0371
            com.google.android.gms.internal.pal.zzadu r12 = (com.google.android.gms.internal.pal.zzadu) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x035c:
            if (r1 >= r2) goto L_0x0368
            int r1 = com.google.android.gms.internal.pal.zzabm.zzm(r3, r1, r7)
            long r4 = r7.zzb
            r12.zzf(r4)
            goto L_0x035c
        L_0x0368:
            if (r1 != r2) goto L_0x036c
            goto L_0x0450
        L_0x036c:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzi()
            throw r1
        L_0x0371:
            if (r6 != 0) goto L_0x044f
            com.google.android.gms.internal.pal.zzadu r12 = (com.google.android.gms.internal.pal.zzadu) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzm(r3, r4, r7)
            long r8 = r7.zzb
            r12.zzf(r8)
        L_0x037e:
            if (r1 >= r5) goto L_0x0393
            int r4 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x0389
            goto L_0x0393
        L_0x0389:
            int r1 = com.google.android.gms.internal.pal.zzabm.zzm(r3, r4, r7)
            long r8 = r7.zzb
            r12.zzf(r8)
            goto L_0x037e
        L_0x0393:
            return r1
        L_0x0394:
            if (r6 != r14) goto L_0x03b8
            com.google.android.gms.internal.pal.zzact r12 = (com.google.android.gms.internal.pal.zzact) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x039f:
            if (r1 >= r2) goto L_0x03af
            int r4 = com.google.android.gms.internal.pal.zzabm.zzb(r3, r1)
            float r4 = java.lang.Float.intBitsToFloat(r4)
            r12.zze(r4)
            int r1 = r1 + 4
            goto L_0x039f
        L_0x03af:
            if (r1 != r2) goto L_0x03b3
            goto L_0x0450
        L_0x03b3:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzi()
            throw r1
        L_0x03b8:
            if (r6 != r9) goto L_0x044f
            com.google.android.gms.internal.pal.zzact r12 = (com.google.android.gms.internal.pal.zzact) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzb(r17, r18)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r12.zze(r1)
        L_0x03c7:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x03e0
            int r4 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x03d4
            goto L_0x03e0
        L_0x03d4:
            int r1 = com.google.android.gms.internal.pal.zzabm.zzb(r3, r4)
            float r1 = java.lang.Float.intBitsToFloat(r1)
            r12.zze(r1)
            goto L_0x03c7
        L_0x03e0:
            return r1
        L_0x03e1:
            if (r6 != r14) goto L_0x0404
            com.google.android.gms.internal.pal.zzacj r12 = (com.google.android.gms.internal.pal.zzacj) r12
            int r1 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r2 = r7.zza
            int r2 = r2 + r1
        L_0x03ec:
            if (r1 >= r2) goto L_0x03fc
            long r4 = com.google.android.gms.internal.pal.zzabm.zzn(r3, r1)
            double r4 = java.lang.Double.longBitsToDouble(r4)
            r12.zze(r4)
            int r1 = r1 + 8
            goto L_0x03ec
        L_0x03fc:
            if (r1 != r2) goto L_0x03ff
            goto L_0x0450
        L_0x03ff:
            com.google.android.gms.internal.pal.zzadi r1 = com.google.android.gms.internal.pal.zzadi.zzi()
            throw r1
        L_0x0404:
            if (r6 != r13) goto L_0x044f
            com.google.android.gms.internal.pal.zzacj r12 = (com.google.android.gms.internal.pal.zzacj) r12
            long r8 = com.google.android.gms.internal.pal.zzabm.zzn(r17, r18)
            double r8 = java.lang.Double.longBitsToDouble(r8)
            r12.zze(r8)
        L_0x0413:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x042c
            int r4 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r1, r7)
            int r6 = r7.zza
            if (r2 == r6) goto L_0x0420
            goto L_0x042c
        L_0x0420:
            long r8 = com.google.android.gms.internal.pal.zzabm.zzn(r3, r4)
            double r8 = java.lang.Double.longBitsToDouble(r8)
            r12.zze(r8)
            goto L_0x0413
        L_0x042c:
            return r1
        L_0x042d:
            if (r4 >= r5) goto L_0x044e
            int r8 = com.google.android.gms.internal.pal.zzabm.zzj(r3, r4, r7)
            int r9 = r7.zza
            if (r2 == r9) goto L_0x0438
            goto L_0x044e
        L_0x0438:
            r21 = r1
            r22 = r17
            r23 = r8
            r24 = r19
            r25 = r6
            r26 = r29
            int r4 = com.google.android.gms.internal.pal.zzabm.zzc(r21, r22, r23, r24, r25, r26)
            java.lang.Object r8 = r7.zzc
            r12.add(r8)
            goto L_0x042d
        L_0x044e:
            return r4
        L_0x044f:
            r1 = r4
        L_0x0450:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzaei.zzw(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.pal.zzabl):int");
    }

    private final int zzx(int i) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzA(i, 0);
    }

    private final int zzy(int i, int i2) {
        if (i < this.zze || i > this.zzf) {
            return -1;
        }
        return zzA(i, i2);
    }

    private final int zzz(int i) {
        return this.zzc[i + 2];
    }

    public final int zza(Object obj) {
        return this.zzj ? zzr(obj) : zzq(obj);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c2, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0226, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0227, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzb(java.lang.Object r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzc
            int r0 = r0.length
            r1 = 0
            r2 = r1
        L_0x0005:
            if (r1 >= r0) goto L_0x022b
            int r3 = r8.zzC(r1)
            int[] r4 = r8.zzc
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            long r5 = (long) r5
            int r3 = zzB(r3)
            r7 = 37
            switch(r3) {
                case 0: goto L_0x0218;
                case 1: goto L_0x020d;
                case 2: goto L_0x0202;
                case 3: goto L_0x01f7;
                case 4: goto L_0x01f0;
                case 5: goto L_0x01e5;
                case 6: goto L_0x01de;
                case 7: goto L_0x01d3;
                case 8: goto L_0x01c6;
                case 9: goto L_0x01b8;
                case 10: goto L_0x01ac;
                case 11: goto L_0x01a4;
                case 12: goto L_0x019c;
                case 13: goto L_0x0194;
                case 14: goto L_0x0188;
                case 15: goto L_0x0180;
                case 16: goto L_0x0174;
                case 17: goto L_0x0169;
                case 18: goto L_0x015d;
                case 19: goto L_0x015d;
                case 20: goto L_0x015d;
                case 21: goto L_0x015d;
                case 22: goto L_0x015d;
                case 23: goto L_0x015d;
                case 24: goto L_0x015d;
                case 25: goto L_0x015d;
                case 26: goto L_0x015d;
                case 27: goto L_0x015d;
                case 28: goto L_0x015d;
                case 29: goto L_0x015d;
                case 30: goto L_0x015d;
                case 31: goto L_0x015d;
                case 32: goto L_0x015d;
                case 33: goto L_0x015d;
                case 34: goto L_0x015d;
                case 35: goto L_0x015d;
                case 36: goto L_0x015d;
                case 37: goto L_0x015d;
                case 38: goto L_0x015d;
                case 39: goto L_0x015d;
                case 40: goto L_0x015d;
                case 41: goto L_0x015d;
                case 42: goto L_0x015d;
                case 43: goto L_0x015d;
                case 44: goto L_0x015d;
                case 45: goto L_0x015d;
                case 46: goto L_0x015d;
                case 47: goto L_0x015d;
                case 48: goto L_0x015d;
                case 49: goto L_0x015d;
                case 50: goto L_0x0151;
                case 51: goto L_0x013b;
                case 52: goto L_0x0129;
                case 53: goto L_0x0117;
                case 54: goto L_0x0105;
                case 55: goto L_0x00f7;
                case 56: goto L_0x00e5;
                case 57: goto L_0x00d7;
                case 58: goto L_0x00c5;
                case 59: goto L_0x00b1;
                case 60: goto L_0x009f;
                case 61: goto L_0x008d;
                case 62: goto L_0x007f;
                case 63: goto L_0x0071;
                case 64: goto L_0x0063;
                case 65: goto L_0x0051;
                case 66: goto L_0x0043;
                case 67: goto L_0x0031;
                case 68: goto L_0x001f;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x0227
        L_0x001f:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzafs.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x0031:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzD(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x0043:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzs(r9, r5)
            goto L_0x0226
        L_0x0051:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzD(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x0063:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzs(r9, r5)
            goto L_0x0226
        L_0x0071:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzs(r9, r5)
            goto L_0x0226
        L_0x007f:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzs(r9, r5)
            goto L_0x0226
        L_0x008d:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzafs.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x009f:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzafs.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x00b1:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzafs.zzf(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x00c5:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            boolean r3 = zzW(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zza(r3)
            goto L_0x0226
        L_0x00d7:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzs(r9, r5)
            goto L_0x0226
        L_0x00e5:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzD(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x00f7:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            int r3 = zzs(r9, r5)
            goto L_0x0226
        L_0x0105:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzD(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x0117:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            long r3 = zzD(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x0129:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            float r3 = zzp(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0226
        L_0x013b:
            boolean r3 = r8.zzV(r9, r4, r1)
            if (r3 == 0) goto L_0x0227
            int r2 = r2 * 53
            double r3 = zzo(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x0151:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzafs.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x015d:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzafs.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x0169:
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzafs.zzf(r9, r5)
            if (r3 == 0) goto L_0x01c2
            int r7 = r3.hashCode()
            goto L_0x01c2
        L_0x0174:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.pal.zzafs.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x0180:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.pal.zzafs.zzc(r9, r5)
            goto L_0x0226
        L_0x0188:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.pal.zzafs.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x0194:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.pal.zzafs.zzc(r9, r5)
            goto L_0x0226
        L_0x019c:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.pal.zzafs.zzc(r9, r5)
            goto L_0x0226
        L_0x01a4:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.pal.zzafs.zzc(r9, r5)
            goto L_0x0226
        L_0x01ac:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzafs.zzf(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x01b8:
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzafs.zzf(r9, r5)
            if (r3 == 0) goto L_0x01c2
            int r7 = r3.hashCode()
        L_0x01c2:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0227
        L_0x01c6:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.pal.zzafs.zzf(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0226
        L_0x01d3:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.pal.zzafs.zzw(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zza(r3)
            goto L_0x0226
        L_0x01de:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.pal.zzafs.zzc(r9, r5)
            goto L_0x0226
        L_0x01e5:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.pal.zzafs.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x01f0:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.pal.zzafs.zzc(r9, r5)
            goto L_0x0226
        L_0x01f7:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.pal.zzafs.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x0202:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.pal.zzafs.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
            goto L_0x0226
        L_0x020d:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.pal.zzafs.zzb(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0226
        L_0x0218:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.pal.zzafs.zza(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.pal.zzadg.zzc(r3)
        L_0x0226:
            int r2 = r2 + r3
        L_0x0227:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022b:
            int r2 = r2 * 53
            com.google.android.gms.internal.pal.zzafi r0 = r8.zzo
            java.lang.Object r0 = r0.zzd(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzh
            if (r0 != 0) goto L_0x023d
            return r2
        L_0x023d:
            com.google.android.gms.internal.pal.zzacn r0 = r8.zzp
            r0.zza(r9)
            r9 = 0
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzaei.zzb(java.lang.Object):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0357, code lost:
        if (r0 != r15) goto L_0x0359;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0373, code lost:
        r8 = r31;
        r7 = r33;
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x039b, code lost:
        if (r0 != r15) goto L_0x0359;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x03be, code lost:
        if (r0 != r15) goto L_0x0359;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0221, code lost:
        r4 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x025a, code lost:
        r5 = r6 | r24;
        r0 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x029a, code lost:
        r5 = r6 | r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x029c, code lost:
        r3 = r8;
        r1 = r11;
        r2 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x02b6, code lost:
        r7 = r33;
        r2 = r4;
        r20 = r6;
        r17 = r8;
        r26 = r10;
        r8 = r11;
        r23 = r19;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzc(java.lang.Object r29, byte[] r30, int r31, int r32, int r33, com.google.android.gms.internal.pal.zzabl r34) throws java.io.IOException {
        /*
            r28 = this;
            r15 = r28
            r14 = r29
            r12 = r30
            r13 = r32
            r11 = r33
            r9 = r34
            sun.misc.Unsafe r10 = zzb
            r16 = 0
            r0 = r31
            r1 = r16
            r3 = r1
            r5 = r3
            r2 = -1
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001a:
            if (r0 >= r13) goto L_0x0432
            int r1 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0029
            int r0 = com.google.android.gms.internal.pal.zzabm.zzk(r0, r12, r1, r9)
            int r1 = r9.zza
            goto L_0x002e
        L_0x0029:
            r27 = r1
            r1 = r0
            r0 = r27
        L_0x002e:
            int r8 = r1 >>> 3
            r7 = r1 & 7
            r4 = 3
            if (r8 <= r2) goto L_0x003b
            int r3 = r3 / r4
            int r2 = r15.zzy(r8, r3)
            goto L_0x003f
        L_0x003b:
            int r2 = r15.zzx(r8)
        L_0x003f:
            r3 = -1
            if (r2 != r3) goto L_0x0051
            r2 = r0
            r18 = r3
            r20 = r5
            r23 = r8
            r26 = r10
            r7 = r11
            r17 = r16
            r8 = r1
            goto L_0x03c1
        L_0x0051:
            int[] r3 = r15.zzc
            int r19 = r2 + 1
            r4 = r3[r19]
            int r11 = zzB(r4)
            r19 = r1
            r17 = 1048575(0xfffff, float:1.469367E-39)
            r1 = r4 & r17
            r21 = r0
            long r0 = (long) r1
            r22 = r0
            r0 = 17
            if (r11 > r0) goto L_0x02c4
            int r0 = r2 + 2
            r0 = r3[r0]
            int r3 = r0 >>> 20
            r1 = 1
            int r24 = r1 << r3
            r3 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r3
            if (r0 == r6) goto L_0x008a
            r17 = r2
            if (r6 == r3) goto L_0x0082
            long r1 = (long) r6
            r10.putInt(r14, r1, r5)
        L_0x0082:
            long r1 = (long) r0
            int r5 = r10.getInt(r14, r1)
            r25 = r0
            goto L_0x008e
        L_0x008a:
            r17 = r2
            r25 = r6
        L_0x008e:
            r6 = r5
            r0 = 5
            switch(r11) {
                case 0: goto L_0x027c;
                case 1: goto L_0x025e;
                case 2: goto L_0x023e;
                case 3: goto L_0x023e;
                case 4: goto L_0x0224;
                case 5: goto L_0x0201;
                case 6: goto L_0x01e6;
                case 7: goto L_0x01c1;
                case 8: goto L_0x019b;
                case 9: goto L_0x0168;
                case 10: goto L_0x014c;
                case 11: goto L_0x0224;
                case 12: goto L_0x0115;
                case 13: goto L_0x01e6;
                case 14: goto L_0x0201;
                case 15: goto L_0x00f6;
                case 16: goto L_0x00c4;
                default: goto L_0x0093;
            }
        L_0x0093:
            r11 = r19
            r4 = r21
            r0 = 3
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != r0) goto L_0x02b6
            com.google.android.gms.internal.pal.zzaer r0 = r15.zzF(r8)
            int r1 = r19 << 3
            r5 = r1 | 4
            r1 = r30
            r12 = r2
            r2 = r4
            r3 = r32
            r4 = r5
            r5 = r34
            int r0 = com.google.android.gms.internal.pal.zzabm.zzc(r0, r1, r2, r3, r4, r5)
            r1 = r6 & r24
            if (r1 != 0) goto L_0x02a2
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r12, r1)
            goto L_0x02af
        L_0x00c4:
            if (r7 != 0) goto L_0x00e8
            r1 = r21
            int r7 = com.google.android.gms.internal.pal.zzabm.zzm(r12, r1, r9)
            long r0 = r9.zzb
            long r4 = com.google.android.gms.internal.pal.zzacc.zzt(r0)
            r1 = r22
            r0 = r10
            r11 = r19
            r1 = r29
            r19 = r8
            r8 = r17
            r18 = -1
            r17 = r3
            r2 = r22
            r0.putLong(r1, r2, r4)
            goto L_0x025a
        L_0x00e8:
            r11 = r19
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r4 = r21
            goto L_0x02b6
        L_0x00f6:
            r11 = r19
            r1 = r21
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            if (r7 != 0) goto L_0x0221
            int r0 = com.google.android.gms.internal.pal.zzabm.zzj(r12, r1, r9)
            int r1 = r9.zza
            int r1 = com.google.android.gms.internal.pal.zzacc.zzs(r1)
            r2 = r22
            r10.putInt(r14, r2, r1)
            goto L_0x029a
        L_0x0115:
            r11 = r19
            r1 = r21
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != 0) goto L_0x0221
            int r0 = com.google.android.gms.internal.pal.zzabm.zzj(r12, r1, r9)
            int r1 = r9.zza
            com.google.android.gms.internal.pal.zzadd r4 = r15.zzE(r8)
            if (r4 == 0) goto L_0x0147
            boolean r4 = r4.zza(r1)
            if (r4 == 0) goto L_0x0138
            goto L_0x0147
        L_0x0138:
            com.google.android.gms.internal.pal.zzafj r2 = zzd(r29)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zzh(r11, r1)
            r5 = r6
            goto L_0x029c
        L_0x0147:
            r10.putInt(r14, r2, r1)
            goto L_0x029a
        L_0x014c:
            r11 = r19
            r1 = r21
            r0 = 2
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != r0) goto L_0x0221
            int r0 = com.google.android.gms.internal.pal.zzabm.zza(r12, r1, r9)
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r2, r1)
            goto L_0x029a
        L_0x0168:
            r11 = r19
            r1 = r21
            r0 = 2
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != r0) goto L_0x0221
            com.google.android.gms.internal.pal.zzaer r0 = r15.zzF(r8)
            int r0 = com.google.android.gms.internal.pal.zzabm.zzd(r0, r12, r1, r13, r9)
            r1 = r6 & r24
            if (r1 != 0) goto L_0x018c
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r2, r1)
            goto L_0x029a
        L_0x018c:
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r4 = r9.zzc
            java.lang.Object r1 = com.google.android.gms.internal.pal.zzadg.zzg(r1, r4)
            r10.putObject(r14, r2, r1)
            goto L_0x029a
        L_0x019b:
            r11 = r19
            r1 = r21
            r0 = 2
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != r0) goto L_0x0221
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r4
            if (r0 != 0) goto L_0x01b6
            int r0 = com.google.android.gms.internal.pal.zzabm.zzg(r12, r1, r9)
            goto L_0x01ba
        L_0x01b6:
            int r0 = com.google.android.gms.internal.pal.zzabm.zzh(r12, r1, r9)
        L_0x01ba:
            java.lang.Object r1 = r9.zzc
            r10.putObject(r14, r2, r1)
            goto L_0x029a
        L_0x01c1:
            r11 = r19
            r1 = r21
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != 0) goto L_0x0221
            int r0 = com.google.android.gms.internal.pal.zzabm.zzm(r12, r1, r9)
            long r4 = r9.zzb
            r20 = 0
            int r1 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r1 == 0) goto L_0x01df
            r1 = 1
            goto L_0x01e1
        L_0x01df:
            r1 = r16
        L_0x01e1:
            com.google.android.gms.internal.pal.zzafs.zzm(r14, r2, r1)
            goto L_0x029a
        L_0x01e6:
            r11 = r19
            r1 = r21
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != r0) goto L_0x0221
            int r0 = com.google.android.gms.internal.pal.zzabm.zzb(r12, r1)
            r10.putInt(r14, r2, r0)
            int r0 = r1 + 4
            goto L_0x029a
        L_0x0201:
            r11 = r19
            r1 = r21
            r0 = 1
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != r0) goto L_0x0221
            long r4 = com.google.android.gms.internal.pal.zzabm.zzn(r12, r1)
            r7 = r1
            r0 = r10
            r1 = r29
            r0.putLong(r1, r2, r4)
            int r0 = r7 + 8
            goto L_0x029a
        L_0x0221:
            r4 = r1
            goto L_0x02b6
        L_0x0224:
            r11 = r19
            r4 = r21
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != 0) goto L_0x02b6
            int r0 = com.google.android.gms.internal.pal.zzabm.zzj(r12, r4, r9)
            int r1 = r9.zza
            r10.putInt(r14, r2, r1)
            goto L_0x029a
        L_0x023e:
            r11 = r19
            r4 = r21
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != 0) goto L_0x02b6
            int r7 = com.google.android.gms.internal.pal.zzabm.zzm(r12, r4, r9)
            long r4 = r9.zzb
            r0 = r10
            r1 = r29
            r0.putLong(r1, r2, r4)
        L_0x025a:
            r5 = r6 | r24
            r0 = r7
            goto L_0x029c
        L_0x025e:
            r11 = r19
            r4 = r21
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != r0) goto L_0x02b6
            int r0 = com.google.android.gms.internal.pal.zzabm.zzb(r12, r4)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            com.google.android.gms.internal.pal.zzafs.zzp(r14, r2, r0)
            int r0 = r4 + 4
            goto L_0x029a
        L_0x027c:
            r11 = r19
            r4 = r21
            r0 = 1
            r18 = -1
            r19 = r8
            r8 = r17
            r17 = r3
            r2 = r22
            if (r7 != r0) goto L_0x02b6
            long r0 = com.google.android.gms.internal.pal.zzabm.zzn(r12, r4)
            double r0 = java.lang.Double.longBitsToDouble(r0)
            com.google.android.gms.internal.pal.zzafs.zzo(r14, r2, r0)
            int r0 = r4 + 8
        L_0x029a:
            r5 = r6 | r24
        L_0x029c:
            r3 = r8
            r1 = r11
            r2 = r19
            goto L_0x0318
        L_0x02a2:
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r9.zzc
            java.lang.Object r1 = com.google.android.gms.internal.pal.zzadg.zzg(r1, r2)
            r10.putObject(r14, r12, r1)
        L_0x02af:
            r5 = r6 | r24
            r12 = r30
            r13 = r32
            goto L_0x029c
        L_0x02b6:
            r7 = r33
            r2 = r4
            r20 = r6
            r17 = r8
            r26 = r10
            r8 = r11
            r23 = r19
            goto L_0x03a3
        L_0x02c4:
            r3 = r19
            r12 = r22
            r17 = 1048575(0xfffff, float:1.469367E-39)
            r18 = -1
            r19 = r8
            r8 = r2
            r2 = r21
            r0 = 27
            if (r11 != r0) goto L_0x032d
            r0 = 2
            if (r7 != r0) goto L_0x031e
            java.lang.Object r0 = r10.getObject(r14, r12)
            com.google.android.gms.internal.pal.zzadf r0 = (com.google.android.gms.internal.pal.zzadf) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x02f6
            int r1 = r0.size()
            if (r1 != 0) goto L_0x02ee
            r1 = 10
            goto L_0x02ef
        L_0x02ee:
            int r1 = r1 + r1
        L_0x02ef:
            com.google.android.gms.internal.pal.zzadf r0 = r0.zzd(r1)
            r10.putObject(r14, r12, r0)
        L_0x02f6:
            r7 = r0
            com.google.android.gms.internal.pal.zzaer r0 = r15.zzF(r8)
            r1 = r3
            r4 = r2
            r2 = r30
            r11 = r3
            r3 = r4
            r4 = r32
            r20 = r5
            r5 = r7
            r25 = r6
            r6 = r34
            int r0 = com.google.android.gms.internal.pal.zzabm.zze(r0, r1, r2, r3, r4, r5, r6)
            r12 = r30
            r13 = r32
            r3 = r8
            r1 = r11
            r2 = r19
            r5 = r20
        L_0x0318:
            r6 = r25
            r11 = r33
            goto L_0x001a
        L_0x031e:
            r20 = r5
            r25 = r6
            r15 = r2
            r31 = r3
            r17 = r8
            r26 = r10
            r23 = r19
            goto L_0x039e
        L_0x032d:
            r20 = r5
            r25 = r6
            r5 = r2
            r6 = r3
            r0 = 49
            if (r11 > r0) goto L_0x0379
            long r3 = (long) r4
            r0 = r28
            r1 = r29
            r2 = r30
            r21 = r3
            r3 = r5
            r4 = r32
            r15 = r5
            r5 = r6
            r31 = r6
            r6 = r19
            r17 = r8
            r23 = r19
            r26 = r10
            r9 = r21
            r14 = r34
            int r0 = r0.zzw(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r14)
            if (r0 == r15) goto L_0x0373
        L_0x0359:
            r15 = r28
            r14 = r29
            r12 = r30
            r1 = r31
            r13 = r32
            r11 = r33
            r9 = r34
            r3 = r17
            r5 = r20
            r2 = r23
            r6 = r25
            r10 = r26
            goto L_0x001a
        L_0x0373:
            r8 = r31
            r7 = r33
            r2 = r0
            goto L_0x03a3
        L_0x0379:
            r15 = r5
            r31 = r6
            r17 = r8
            r26 = r10
            r23 = r19
            r0 = 50
            if (r11 != r0) goto L_0x03a6
            r0 = 2
            if (r7 != r0) goto L_0x039e
            r0 = r28
            r1 = r29
            r2 = r30
            r3 = r15
            r4 = r32
            r5 = r17
            r6 = r12
            r8 = r34
            int r0 = r0.zzt(r1, r2, r3, r4, r5, r6, r8)
            if (r0 == r15) goto L_0x0373
            goto L_0x0359
        L_0x039e:
            r8 = r31
            r7 = r33
            r2 = r15
        L_0x03a3:
            r6 = r25
            goto L_0x03c1
        L_0x03a6:
            r0 = r28
            r1 = r29
            r2 = r30
            r3 = r15
            r8 = r4
            r4 = r32
            r5 = r31
            r6 = r23
            r9 = r11
            r10 = r12
            r12 = r17
            r13 = r34
            int r0 = r0.zzu(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r12, r13)
            if (r0 == r15) goto L_0x0373
            goto L_0x0359
        L_0x03c1:
            if (r8 != r7) goto L_0x03d3
            if (r7 == 0) goto L_0x03d3
            r3 = 1048575(0xfffff, float:1.469367E-39)
            r9 = r28
            r12 = r29
            r0 = r2
            r1 = r8
            r5 = r20
            r2 = 0
            goto L_0x043f
        L_0x03d3:
            r9 = r28
            boolean r0 = r9.zzh
            if (r0 == 0) goto L_0x040b
            r10 = r34
            com.google.android.gms.internal.pal.zzacm r0 = r10.zzd
            com.google.android.gms.internal.pal.zzacm r1 = com.google.android.gms.internal.pal.zzacm.zza()
            if (r0 == r1) goto L_0x0408
            com.google.android.gms.internal.pal.zzaef r0 = r9.zzg
            com.google.android.gms.internal.pal.zzacm r1 = r10.zzd
            r11 = r23
            com.google.android.gms.internal.pal.zzacx r0 = r1.zzb(r0, r11)
            if (r0 != 0) goto L_0x0401
            com.google.android.gms.internal.pal.zzafj r4 = zzd(r29)
            r0 = r8
            r1 = r30
            r3 = r32
            r5 = r34
            int r0 = com.google.android.gms.internal.pal.zzabm.zzi(r0, r1, r2, r3, r4, r5)
            r12 = r29
            goto L_0x0420
        L_0x0401:
            r12 = r29
            r0 = r12
            com.google.android.gms.internal.pal.zzacw r0 = (com.google.android.gms.internal.pal.zzacw) r0
            r2 = 0
            throw r2
        L_0x0408:
            r12 = r29
            goto L_0x040f
        L_0x040b:
            r12 = r29
            r10 = r34
        L_0x040f:
            r11 = r23
            com.google.android.gms.internal.pal.zzafj r4 = zzd(r29)
            r0 = r8
            r1 = r30
            r3 = r32
            r5 = r34
            int r0 = com.google.android.gms.internal.pal.zzabm.zzi(r0, r1, r2, r3, r4, r5)
        L_0x0420:
            r13 = r32
            r1 = r8
            r15 = r9
            r9 = r10
            r2 = r11
            r14 = r12
            r3 = r17
            r5 = r20
            r10 = r26
            r12 = r30
            r11 = r7
            goto L_0x001a
        L_0x0432:
            r20 = r5
            r25 = r6
            r26 = r10
            r7 = r11
            r12 = r14
            r9 = r15
            r2 = 0
            r3 = 1048575(0xfffff, float:1.469367E-39)
        L_0x043f:
            if (r6 == r3) goto L_0x0447
            long r3 = (long) r6
            r6 = r26
            r6.putInt(r12, r3, r5)
        L_0x0447:
            int r3 = r9.zzl
        L_0x0449:
            int r4 = r9.zzm
            if (r3 >= r4) goto L_0x0459
            int[] r4 = r9.zzk
            r4 = r4[r3]
            com.google.android.gms.internal.pal.zzafi r5 = r9.zzo
            r9.zzG(r12, r4, r2, r5)
            int r3 = r3 + 1
            goto L_0x0449
        L_0x0459:
            if (r7 != 0) goto L_0x0465
            r2 = r32
            if (r0 != r2) goto L_0x0460
            goto L_0x046b
        L_0x0460:
            com.google.android.gms.internal.pal.zzadi r0 = com.google.android.gms.internal.pal.zzadi.zzg()
            throw r0
        L_0x0465:
            r2 = r32
            if (r0 > r2) goto L_0x046c
            if (r1 != r7) goto L_0x046c
        L_0x046b:
            return r0
        L_0x046c:
            com.google.android.gms.internal.pal.zzadi r0 = com.google.android.gms.internal.pal.zzadi.zzg()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzaei.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.pal.zzabl):int");
    }

    public final Object zze() {
        return ((zzacz) this.zzg).zzb(4, (Object) null, (Object) null);
    }

    public final void zzf(Object obj) {
        int i;
        int i2 = this.zzl;
        while (true) {
            i = this.zzm;
            if (i2 >= i) {
                break;
            }
            long zzC = (long) (zzC(this.zzk[i2]) & 1048575);
            Object zzf2 = zzafs.zzf(obj, zzC);
            if (zzf2 != null) {
                ((zzadz) zzf2).zzc();
                zzafs.zzs(obj, zzC, zzf2);
            }
            i2++;
        }
        int length = this.zzk.length;
        while (i < length) {
            this.zzn.zzb(obj, (long) this.zzk[i]);
            i++;
        }
        this.zzo.zzm(obj);
        if (this.zzh) {
            this.zzp.zze(obj);
        }
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final void zzh(java.lang.Object r13, com.google.android.gms.internal.pal.zzaeq r14, com.google.android.gms.internal.pal.zzacm r15) throws java.io.IOException {
        /*
            r12 = this;
            r15.getClass()
            com.google.android.gms.internal.pal.zzafi r7 = r12.zzo
            com.google.android.gms.internal.pal.zzacn r8 = r12.zzp
            r9 = 0
            r0 = r9
            r10 = r0
        L_0x000a:
            int r1 = r14.zzc()     // Catch:{ all -> 0x0078 }
            int r2 = r12.zzx(r1)     // Catch:{ all -> 0x0078 }
            if (r2 >= 0) goto L_0x007b
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r2) goto L_0x0030
            int r14 = r12.zzl
        L_0x001b:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x002a
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzG(r13, r15, r10, r7)
            int r14 = r14 + 1
            goto L_0x001b
        L_0x002a:
            if (r10 == 0) goto L_0x05c2
            r7.zzn(r13, r10)
            return
        L_0x0030:
            boolean r2 = r12.zzh     // Catch:{ all -> 0x0078 }
            if (r2 != 0) goto L_0x0036
            r2 = r9
            goto L_0x003d
        L_0x0036:
            com.google.android.gms.internal.pal.zzaef r2 = r12.zzg     // Catch:{ all -> 0x0078 }
            java.lang.Object r1 = r8.zzc(r15, r2, r1)     // Catch:{ all -> 0x0078 }
            r2 = r1
        L_0x003d:
            if (r2 == 0) goto L_0x0052
            if (r0 != 0) goto L_0x0045
            com.google.android.gms.internal.pal.zzacr r0 = r8.zzb(r13)     // Catch:{ all -> 0x0078 }
        L_0x0045:
            r11 = r0
            r0 = r8
            r1 = r14
            r3 = r15
            r4 = r11
            r5 = r10
            r6 = r7
            java.lang.Object r10 = r0.zzd(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0078 }
            r0 = r11
            goto L_0x000a
        L_0x0052:
            r7.zzr(r14)     // Catch:{ all -> 0x0078 }
            if (r10 != 0) goto L_0x005b
            java.lang.Object r10 = r7.zzc(r13)     // Catch:{ all -> 0x0078 }
        L_0x005b:
            boolean r1 = r7.zzq(r10, r14)     // Catch:{ all -> 0x0078 }
            if (r1 != 0) goto L_0x000a
            int r14 = r12.zzl
        L_0x0063:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x0072
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzG(r13, r15, r10, r7)
            int r14 = r14 + 1
            goto L_0x0063
        L_0x0072:
            if (r10 == 0) goto L_0x05c2
            r7.zzn(r13, r10)
            return
        L_0x0078:
            r14 = move-exception
            goto L_0x05c3
        L_0x007b:
            int r3 = r12.zzC(r2)     // Catch:{ all -> 0x0078 }
            int r4 = zzB(r3)     // Catch:{ zzadh -> 0x059c }
            r5 = 1048575(0xfffff, float:1.469367E-39)
            switch(r4) {
                case 0: goto L_0x0570;
                case 1: goto L_0x0561;
                case 2: goto L_0x0552;
                case 3: goto L_0x0543;
                case 4: goto L_0x0534;
                case 5: goto L_0x0525;
                case 6: goto L_0x0516;
                case 7: goto L_0x0507;
                case 8: goto L_0x04ff;
                case 9: goto L_0x04ce;
                case 10: goto L_0x04bf;
                case 11: goto L_0x04b0;
                case 12: goto L_0x048e;
                case 13: goto L_0x047f;
                case 14: goto L_0x0470;
                case 15: goto L_0x0461;
                case 16: goto L_0x0452;
                case 17: goto L_0x0421;
                case 18: goto L_0x0413;
                case 19: goto L_0x0405;
                case 20: goto L_0x03f7;
                case 21: goto L_0x03e9;
                case 22: goto L_0x03db;
                case 23: goto L_0x03cd;
                case 24: goto L_0x03bf;
                case 25: goto L_0x03b1;
                case 26: goto L_0x0387;
                case 27: goto L_0x0375;
                case 28: goto L_0x0367;
                case 29: goto L_0x0359;
                case 30: goto L_0x0344;
                case 31: goto L_0x0336;
                case 32: goto L_0x0328;
                case 33: goto L_0x031a;
                case 34: goto L_0x030c;
                case 35: goto L_0x02fe;
                case 36: goto L_0x02f0;
                case 37: goto L_0x02e2;
                case 38: goto L_0x02d4;
                case 39: goto L_0x02c6;
                case 40: goto L_0x02b8;
                case 41: goto L_0x02aa;
                case 42: goto L_0x029c;
                case 43: goto L_0x028e;
                case 44: goto L_0x0279;
                case 45: goto L_0x026b;
                case 46: goto L_0x025d;
                case 47: goto L_0x024f;
                case 48: goto L_0x0241;
                case 49: goto L_0x022f;
                case 50: goto L_0x01f9;
                case 51: goto L_0x01e7;
                case 52: goto L_0x01d5;
                case 53: goto L_0x01c3;
                case 54: goto L_0x01b1;
                case 55: goto L_0x019f;
                case 56: goto L_0x018d;
                case 57: goto L_0x017b;
                case 58: goto L_0x0169;
                case 59: goto L_0x0161;
                case 60: goto L_0x0130;
                case 61: goto L_0x0122;
                case 62: goto L_0x0110;
                case 63: goto L_0x00eb;
                case 64: goto L_0x00d9;
                case 65: goto L_0x00c7;
                case 66: goto L_0x00b5;
                case 67: goto L_0x00a3;
                case 68: goto L_0x0091;
                default: goto L_0x0089;
            }     // Catch:{ zzadh -> 0x059c }
        L_0x0089:
            if (r10 != 0) goto L_0x057f
            java.lang.Object r10 = r7.zzf()     // Catch:{ zzadh -> 0x059c }
            goto L_0x057f
        L_0x0091:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaer r5 = r12.zzF(r2)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r5 = r14.zzr(r5, r15)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x00a3:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            long r5 = r14.zzn()     // Catch:{ zzadh -> 0x059c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x00b5:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            int r5 = r14.zzi()     // Catch:{ zzadh -> 0x059c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x00c7:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            long r5 = r14.zzm()     // Catch:{ zzadh -> 0x059c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x00d9:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            int r5 = r14.zzh()     // Catch:{ zzadh -> 0x059c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x00eb:
            int r4 = r14.zze()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzadd r6 = r12.zzE(r2)     // Catch:{ zzadh -> 0x059c }
            if (r6 == 0) goto L_0x0102
            boolean r6 = r6.zza(r4)     // Catch:{ zzadh -> 0x059c }
            if (r6 == 0) goto L_0x00fc
            goto L_0x0102
        L_0x00fc:
            java.lang.Object r10 = com.google.android.gms.internal.pal.zzaet.zzD(r1, r4, r10, r7)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0102:
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzadh -> 0x059c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r5, r3)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0110:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            int r5 = r14.zzj()     // Catch:{ zzadh -> 0x059c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0122:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaby r5 = r14.zzp()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0130:
            boolean r4 = r12.zzV(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            if (r4 == 0) goto L_0x014c
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r5 = com.google.android.gms.internal.pal.zzafs.zzf(r13, r3)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaer r6 = r12.zzF(r2)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r6 = r14.zzs(r6, r15)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r5 = com.google.android.gms.internal.pal.zzadg.zzg(r5, r6)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            goto L_0x015c
        L_0x014c:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaer r5 = r12.zzF(r2)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r5 = r14.zzs(r5, r15)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
        L_0x015c:
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0161:
            r12.zzL(r13, r3, r14)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0169:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            boolean r5 = r14.zzN()     // Catch:{ zzadh -> 0x059c }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x017b:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            int r5 = r14.zzf()     // Catch:{ zzadh -> 0x059c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x018d:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            long r5 = r14.zzk()     // Catch:{ zzadh -> 0x059c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x019f:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            int r5 = r14.zzg()     // Catch:{ zzadh -> 0x059c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x01b1:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            long r5 = r14.zzo()     // Catch:{ zzadh -> 0x059c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x01c3:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            long r5 = r14.zzl()     // Catch:{ zzadh -> 0x059c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x01d5:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            float r5 = r14.zzb()     // Catch:{ zzadh -> 0x059c }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x01e7:
            r3 = r3 & r5
            long r3 = (long) r3     // Catch:{ zzadh -> 0x059c }
            double r5 = r14.zza()     // Catch:{ zzadh -> 0x059c }
            java.lang.Double r5 = java.lang.Double.valueOf(r5)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzN(r13, r1, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x01f9:
            java.lang.Object r1 = r12.zzH(r2)     // Catch:{ zzadh -> 0x059c }
            int r2 = r12.zzC(r2)     // Catch:{ zzadh -> 0x059c }
            r2 = r2 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r4 = com.google.android.gms.internal.pal.zzafs.zzf(r13, r2)     // Catch:{ zzadh -> 0x059c }
            if (r4 == 0) goto L_0x021f
            boolean r5 = com.google.android.gms.internal.pal.zzaea.zzb(r4)     // Catch:{ zzadh -> 0x059c }
            if (r5 == 0) goto L_0x022a
            com.google.android.gms.internal.pal.zzadz r5 = com.google.android.gms.internal.pal.zzadz.zza()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzadz r5 = r5.zzb()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaea.zzc(r5, r4)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r2, r5)     // Catch:{ zzadh -> 0x059c }
            r4 = r5
            goto L_0x022a
        L_0x021f:
            com.google.android.gms.internal.pal.zzadz r4 = com.google.android.gms.internal.pal.zzadz.zza()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzadz r4 = r4.zzb()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r2, r4)     // Catch:{ zzadh -> 0x059c }
        L_0x022a:
            com.google.android.gms.internal.pal.zzadz r4 = (com.google.android.gms.internal.pal.zzadz) r4     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzady r1 = (com.google.android.gms.internal.pal.zzady) r1     // Catch:{ zzadh -> 0x059c }
            throw r9     // Catch:{ zzadh -> 0x059c }
        L_0x022f:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaer r1 = r12.zzF(r2)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzadt r2 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzadh -> 0x059c }
            r14.zzC(r2, r1, r15)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0241:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzJ(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x024f:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzI(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x025d:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzH(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x026b:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzG(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0279:
            com.google.android.gms.internal.pal.zzadt r4 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzadh -> 0x059c }
            java.util.List r3 = r4.zza(r13, r5)     // Catch:{ zzadh -> 0x059c }
            r14.zzy(r3)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzadd r2 = r12.zzE(r2)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r10 = com.google.android.gms.internal.pal.zzaet.zzC(r1, r3, r2, r10, r7)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x028e:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzL(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x029c:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzv(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x02aa:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzz(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x02b8:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzA(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x02c6:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzD(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x02d4:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzM(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x02e2:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzE(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x02f0:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzB(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x02fe:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzx(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x030c:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzJ(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x031a:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzI(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0328:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzH(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0336:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzG(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0344:
            com.google.android.gms.internal.pal.zzadt r4 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r3 = r3 & r5
            long r5 = (long) r3     // Catch:{ zzadh -> 0x059c }
            java.util.List r3 = r4.zza(r13, r5)     // Catch:{ zzadh -> 0x059c }
            r14.zzy(r3)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzadd r2 = r12.zzE(r2)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r10 = com.google.android.gms.internal.pal.zzaet.zzC(r1, r3, r2, r10, r7)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0359:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzL(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0367:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzw(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0375:
            com.google.android.gms.internal.pal.zzaer r1 = r12.zzF(r2)     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzadt r4 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            java.util.List r2 = r4.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzF(r2, r1, r15)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0387:
            boolean r1 = zzR(r3)     // Catch:{ zzadh -> 0x059c }
            if (r1 == 0) goto L_0x039f
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r2 = r14
            com.google.android.gms.internal.pal.zzacd r2 = (com.google.android.gms.internal.pal.zzacd) r2     // Catch:{ zzadh -> 0x059c }
            r3 = 1
            r2.zzK(r1, r3)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x039f:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r2 = r14
            com.google.android.gms.internal.pal.zzacd r2 = (com.google.android.gms.internal.pal.zzacd) r2     // Catch:{ zzadh -> 0x059c }
            r3 = 0
            r2.zzK(r1, r3)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x03b1:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzv(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x03bf:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzz(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x03cd:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzA(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x03db:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzD(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x03e9:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzM(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x03f7:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzE(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0405:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzB(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0413:
            com.google.android.gms.internal.pal.zzadt r1 = r12.zzn     // Catch:{ zzadh -> 0x059c }
            r2 = r3 & r5
            long r2 = (long) r2     // Catch:{ zzadh -> 0x059c }
            java.util.List r1 = r1.zza(r13, r2)     // Catch:{ zzadh -> 0x059c }
            r14.zzx(r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0421:
            boolean r1 = r12.zzS(r13, r2)     // Catch:{ zzadh -> 0x059c }
            if (r1 == 0) goto L_0x043f
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r1 = com.google.android.gms.internal.pal.zzafs.zzf(r13, r3)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaer r2 = r12.zzF(r2)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r2 = r14.zzr(r2, r15)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r1 = com.google.android.gms.internal.pal.zzadg.zzg(r1, r2)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x043f:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaer r1 = r12.zzF(r2)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r1 = r14.zzr(r1, r15)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0452:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            long r5 = r14.zzn()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzr(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0461:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            int r1 = r14.zzi()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzq(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0470:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            long r5 = r14.zzm()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzr(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x047f:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            int r1 = r14.zzh()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzq(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x048e:
            int r4 = r14.zze()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzadd r6 = r12.zzE(r2)     // Catch:{ zzadh -> 0x059c }
            if (r6 == 0) goto L_0x04a5
            boolean r6 = r6.zza(r4)     // Catch:{ zzadh -> 0x059c }
            if (r6 == 0) goto L_0x049f
            goto L_0x04a5
        L_0x049f:
            java.lang.Object r10 = com.google.android.gms.internal.pal.zzaet.zzD(r1, r4, r10, r7)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x04a5:
            r1 = r3 & r5
            long r5 = (long) r1     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzq(r13, r5, r4)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x04b0:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            int r1 = r14.zzj()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzq(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x04bf:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaby r1 = r14.zzp()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x04ce:
            boolean r1 = r12.zzS(r13, r2)     // Catch:{ zzadh -> 0x059c }
            if (r1 == 0) goto L_0x04ec
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r1 = com.google.android.gms.internal.pal.zzafs.zzf(r13, r3)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaer r2 = r12.zzF(r2)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r2 = r14.zzs(r2, r15)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r1 = com.google.android.gms.internal.pal.zzadg.zzg(r1, r2)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x04ec:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzaer r1 = r12.zzF(r2)     // Catch:{ zzadh -> 0x059c }
            java.lang.Object r1 = r14.zzs(r1, r15)     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzs(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x04ff:
            r12.zzL(r13, r3, r14)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0507:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            boolean r1 = r14.zzN()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzm(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0516:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            int r1 = r14.zzf()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzq(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0525:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            long r5 = r14.zzk()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzr(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0534:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            int r1 = r14.zzg()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzq(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0543:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            long r5 = r14.zzo()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzr(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0552:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            long r5 = r14.zzl()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzr(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0561:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            float r1 = r14.zzb()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzp(r13, r3, r1)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x0570:
            r1 = r3 & r5
            long r3 = (long) r1     // Catch:{ zzadh -> 0x059c }
            double r5 = r14.zza()     // Catch:{ zzadh -> 0x059c }
            com.google.android.gms.internal.pal.zzafs.zzo(r13, r3, r5)     // Catch:{ zzadh -> 0x059c }
            r12.zzM(r13, r2)     // Catch:{ zzadh -> 0x059c }
            goto L_0x000a
        L_0x057f:
            boolean r1 = r7.zzq(r10, r14)     // Catch:{ zzadh -> 0x059c }
            if (r1 != 0) goto L_0x000a
            int r14 = r12.zzl
        L_0x0587:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x0596
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzG(r13, r15, r10, r7)
            int r14 = r14 + 1
            goto L_0x0587
        L_0x0596:
            if (r10 == 0) goto L_0x05c2
            r7.zzn(r13, r10)
            return
        L_0x059c:
            r7.zzr(r14)     // Catch:{ all -> 0x0078 }
            if (r10 != 0) goto L_0x05a6
            java.lang.Object r1 = r7.zzc(r13)     // Catch:{ all -> 0x0078 }
            r10 = r1
        L_0x05a6:
            boolean r1 = r7.zzq(r10, r14)     // Catch:{ all -> 0x0078 }
            if (r1 != 0) goto L_0x000a
            int r14 = r12.zzl
        L_0x05ae:
            int r15 = r12.zzm
            if (r14 >= r15) goto L_0x05bd
            int[] r15 = r12.zzk
            r15 = r15[r14]
            java.lang.Object r10 = r12.zzG(r13, r15, r10, r7)
            int r14 = r14 + 1
            goto L_0x05ae
        L_0x05bd:
            if (r10 == 0) goto L_0x05c2
            r7.zzn(r13, r10)
        L_0x05c2:
            return
        L_0x05c3:
            int r15 = r12.zzl
        L_0x05c5:
            int r0 = r12.zzm
            if (r15 >= r0) goto L_0x05d4
            int[] r0 = r12.zzk
            r0 = r0[r15]
            java.lang.Object r10 = r12.zzG(r13, r0, r10, r7)
            int r15 = r15 + 1
            goto L_0x05c5
        L_0x05d4:
            if (r10 == 0) goto L_0x05d9
            r7.zzn(r13, r10)
        L_0x05d9:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzaei.zzh(java.lang.Object, com.google.android.gms.internal.pal.zzaeq, com.google.android.gms.internal.pal.zzacm):void");
    }

    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzabl zzabl) throws IOException {
        if (this.zzj) {
            zzv(obj, bArr, i, i2, zzabl);
        } else {
            zzc(obj, bArr, i, i2, 0, zzabl);
        }
    }

    public final void zzj(Object obj, zzaga zzaga) throws IOException {
        if (!this.zzj) {
            zzO(obj, zzaga);
        } else if (!this.zzh) {
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzC = zzC(i);
                int i2 = this.zzc[i];
                switch (zzB(zzC)) {
                    case 0:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzf(i2, zzafs.zza(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 1:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzo(i2, zzafs.zzb(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 2:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzt(i2, zzafs.zzd(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 3:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzJ(i2, zzafs.zzd(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 4:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzr(i2, zzafs.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 5:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzm(i2, zzafs.zzd(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 6:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzk(i2, zzafs.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 7:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzb(i2, zzafs.zzw(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 8:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzX(i2, zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga);
                            break;
                        }
                    case 9:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzv(i2, zzafs.zzf(obj, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                    case 10:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzd(i2, (zzaby) zzafs.zzf(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 11:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzH(i2, zzafs.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 12:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzi(i2, zzafs.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 13:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzw(i2, zzafs.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 14:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzy(i2, zzafs.zzd(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 15:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzA(i2, zzafs.zzc(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 16:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzC(i2, zzafs.zzd(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 17:
                        if (!zzS(obj, i)) {
                            break;
                        } else {
                            zzaga.zzq(i2, zzafs.zzf(obj, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                    case 18:
                        zzaet.zzJ(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 19:
                        zzaet.zzN(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 20:
                        zzaet.zzQ(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 21:
                        zzaet.zzY(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 22:
                        zzaet.zzP(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 23:
                        zzaet.zzM(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 24:
                        zzaet.zzL(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 25:
                        zzaet.zzH(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 26:
                        zzaet.zzW(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga);
                        break;
                    case 27:
                        zzaet.zzR(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, zzF(i));
                        break;
                    case 28:
                        zzaet.zzI(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga);
                        break;
                    case 29:
                        zzaet.zzX(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 30:
                        zzaet.zzK(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 31:
                        zzaet.zzS(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 32:
                        zzaet.zzT(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 33:
                        zzaet.zzU(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 34:
                        zzaet.zzV(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, false);
                        break;
                    case 35:
                        zzaet.zzJ(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 36:
                        zzaet.zzN(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 37:
                        zzaet.zzQ(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 38:
                        zzaet.zzY(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 39:
                        zzaet.zzP(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 40:
                        zzaet.zzM(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 41:
                        zzaet.zzL(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 42:
                        zzaet.zzH(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 43:
                        zzaet.zzX(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 44:
                        zzaet.zzK(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 45:
                        zzaet.zzS(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 46:
                        zzaet.zzT(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 47:
                        zzaet.zzU(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case 48:
                        zzaet.zzV(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, true);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
                        zzaet.zzO(i2, (List) zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga, zzF(i));
                        break;
                    case 50:
                        zzP(zzaga, i2, zzafs.zzf(obj, (long) (zzC & 1048575)), i);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzf(i2, zzo(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 52:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzo(i2, zzp(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 53:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzt(i2, zzD(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 54:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzJ(i2, zzD(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 55:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzr(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 56:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzm(i2, zzD(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 57:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzk(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case Elf64.Ehdr.E_SHENTSIZE:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzb(i2, zzW(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 59:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzX(i2, zzafs.zzf(obj, (long) (zzC & 1048575)), zzaga);
                            break;
                        }
                    case 60:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzv(i2, zzafs.zzf(obj, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                    case LockFreeTaskQueueCore.CLOSED_SHIFT:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzd(i2, (zzaby) zzafs.zzf(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case Elf64.Ehdr.E_SHSTRNDX:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzH(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case HtmlCompat.FROM_HTML_MODE_COMPACT:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzi(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 64:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzw(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 65:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzy(i2, zzD(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzA(i2, zzs(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 67:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzC(i2, zzD(obj, (long) (zzC & 1048575)));
                            break;
                        }
                    case 68:
                        if (!zzV(obj, i2, i)) {
                            break;
                        } else {
                            zzaga.zzq(i2, zzafs.zzf(obj, (long) (zzC & 1048575)), zzF(i));
                            break;
                        }
                }
            }
            zzafi zzafi = this.zzo;
            zzafi.zzp(zzafi.zzd(obj), zzaga);
        } else {
            this.zzp.zza(obj);
            throw null;
        }
    }

    public final boolean zzk(Object obj, Object obj2) {
        boolean z;
        int length = this.zzc.length;
        for (int i = 0; i < length; i += 3) {
            int zzC = zzC(i);
            long j = (long) (zzC & 1048575);
            switch (zzB(zzC)) {
                case 0:
                    if (zzQ(obj, obj2, i) && Double.doubleToLongBits(zzafs.zza(obj, j)) == Double.doubleToLongBits(zzafs.zza(obj2, j))) {
                        continue;
                    }
                case 1:
                    if (zzQ(obj, obj2, i) && Float.floatToIntBits(zzafs.zzb(obj, j)) == Float.floatToIntBits(zzafs.zzb(obj2, j))) {
                        continue;
                    }
                case 2:
                    if (zzQ(obj, obj2, i) && zzafs.zzd(obj, j) == zzafs.zzd(obj2, j)) {
                        continue;
                    }
                case 3:
                    if (zzQ(obj, obj2, i) && zzafs.zzd(obj, j) == zzafs.zzd(obj2, j)) {
                        continue;
                    }
                case 4:
                    if (zzQ(obj, obj2, i) && zzafs.zzc(obj, j) == zzafs.zzc(obj2, j)) {
                        continue;
                    }
                case 5:
                    if (zzQ(obj, obj2, i) && zzafs.zzd(obj, j) == zzafs.zzd(obj2, j)) {
                        continue;
                    }
                case 6:
                    if (zzQ(obj, obj2, i) && zzafs.zzc(obj, j) == zzafs.zzc(obj2, j)) {
                        continue;
                    }
                case 7:
                    if (zzQ(obj, obj2, i) && zzafs.zzw(obj, j) == zzafs.zzw(obj2, j)) {
                        continue;
                    }
                case 8:
                    if (zzQ(obj, obj2, i) && zzaet.zzZ(zzafs.zzf(obj, j), zzafs.zzf(obj2, j))) {
                        continue;
                    }
                case 9:
                    if (zzQ(obj, obj2, i) && zzaet.zzZ(zzafs.zzf(obj, j), zzafs.zzf(obj2, j))) {
                        continue;
                    }
                case 10:
                    if (zzQ(obj, obj2, i) && zzaet.zzZ(zzafs.zzf(obj, j), zzafs.zzf(obj2, j))) {
                        continue;
                    }
                case 11:
                    if (zzQ(obj, obj2, i) && zzafs.zzc(obj, j) == zzafs.zzc(obj2, j)) {
                        continue;
                    }
                case 12:
                    if (zzQ(obj, obj2, i) && zzafs.zzc(obj, j) == zzafs.zzc(obj2, j)) {
                        continue;
                    }
                case 13:
                    if (zzQ(obj, obj2, i) && zzafs.zzc(obj, j) == zzafs.zzc(obj2, j)) {
                        continue;
                    }
                case 14:
                    if (zzQ(obj, obj2, i) && zzafs.zzd(obj, j) == zzafs.zzd(obj2, j)) {
                        continue;
                    }
                case 15:
                    if (zzQ(obj, obj2, i) && zzafs.zzc(obj, j) == zzafs.zzc(obj2, j)) {
                        continue;
                    }
                case 16:
                    if (zzQ(obj, obj2, i) && zzafs.zzd(obj, j) == zzafs.zzd(obj2, j)) {
                        continue;
                    }
                case 17:
                    if (zzQ(obj, obj2, i) && zzaet.zzZ(zzafs.zzf(obj, j), zzafs.zzf(obj2, j))) {
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
                    z = zzaet.zzZ(zzafs.zzf(obj, j), zzafs.zzf(obj2, j));
                    break;
                case 50:
                    z = zzaet.zzZ(zzafs.zzf(obj, j), zzafs.zzf(obj2, j));
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
                    long zzz = (long) (zzz(i) & 1048575);
                    if (zzafs.zzc(obj, zzz) == zzafs.zzc(obj2, zzz) && zzaet.zzZ(zzafs.zzf(obj, j), zzafs.zzf(obj2, j))) {
                        continue;
                    }
            }
            if (!z) {
                return false;
            }
        }
        if (!this.zzo.zzd(obj).equals(this.zzo.zzd(obj2))) {
            return false;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzp.zza(obj);
        this.zzp.zza(obj2);
        throw null;
    }

    public final boolean zzl(Object obj) {
        int i;
        int i2;
        Object obj2 = obj;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzl) {
            int i6 = this.zzk[i5];
            int i7 = this.zzc[i6];
            int zzC = zzC(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(obj2, (long) i9);
                }
                i = i4;
                i2 = i9;
            } else {
                i2 = i3;
                i = i4;
            }
            if ((268435456 & zzC) != 0 && !zzT(obj, i6, i2, i, i10)) {
                return false;
            }
            int zzB = zzB(zzC);
            if (zzB != 9 && zzB != 17) {
                if (zzB != 27) {
                    if (zzB == 60 || zzB == 68) {
                        if (zzV(obj2, i7, i6) && !zzU(obj2, zzC, zzF(i6))) {
                            return false;
                        }
                    } else if (zzB != 49) {
                        if (zzB == 50 && !((zzadz) zzafs.zzf(obj2, (long) (zzC & 1048575))).isEmpty()) {
                            zzady zzady = (zzady) zzH(i6);
                            throw null;
                        }
                    }
                }
                List list = (List) zzafs.zzf(obj2, (long) (zzC & 1048575));
                if (!list.isEmpty()) {
                    zzaer zzF = zzF(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzF.zzl(list.get(i11))) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (zzT(obj, i6, i2, i, i10) && !zzU(obj2, zzC, zzF(i6))) {
                return false;
            }
            i5++;
            i3 = i2;
            i4 = i;
        }
        if (!this.zzh) {
            return true;
        }
        this.zzp.zza(obj2);
        throw null;
    }

    public final void zzg(Object obj, Object obj2) {
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int zzC = zzC(i);
            long j = (long) (1048575 & zzC);
            int i2 = this.zzc[i];
            switch (zzB(zzC)) {
                case 0:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzo(obj, j, zzafs.zza(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 1:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzp(obj, j, zzafs.zzb(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 2:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzr(obj, j, zzafs.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 3:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzr(obj, j, zzafs.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 4:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzq(obj, j, zzafs.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 5:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzr(obj, j, zzafs.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 6:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzq(obj, j, zzafs.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 7:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzm(obj, j, zzafs.zzw(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 8:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzs(obj, j, zzafs.zzf(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 9:
                    zzJ(obj, obj2, i);
                    break;
                case 10:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzs(obj, j, zzafs.zzf(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 11:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzq(obj, j, zzafs.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 12:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzq(obj, j, zzafs.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 13:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzq(obj, j, zzafs.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 14:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzr(obj, j, zzafs.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 15:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzq(obj, j, zzafs.zzc(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 16:
                    if (!zzS(obj2, i)) {
                        break;
                    } else {
                        zzafs.zzr(obj, j, zzafs.zzd(obj2, j));
                        zzM(obj, i);
                        break;
                    }
                case 17:
                    zzJ(obj, obj2, i);
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
                    this.zzn.zzc(obj, obj2, j);
                    break;
                case 50:
                    zzaet.zzaa(this.zzr, obj, obj2, j);
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
                    if (!zzV(obj2, i2, i)) {
                        break;
                    } else {
                        zzafs.zzs(obj, j, zzafs.zzf(obj2, j));
                        zzN(obj, i2, i);
                        break;
                    }
                case 60:
                    zzK(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT:
                case Elf64.Ehdr.E_SHSTRNDX:
                case HtmlCompat.FROM_HTML_MODE_COMPACT:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                case 67:
                    if (!zzV(obj2, i2, i)) {
                        break;
                    } else {
                        zzafs.zzs(obj, j, zzafs.zzf(obj2, j));
                        zzN(obj, i2, i);
                        break;
                    }
                case 68:
                    zzK(obj, obj2, i);
                    break;
            }
        }
        zzaet.zzF(this.zzo, obj, obj2);
        if (this.zzh) {
            zzaet.zzE(this.zzp, obj, obj2);
        }
    }
}
