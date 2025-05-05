package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.media3.muxer.MuxerUtil;
import com.google.common.base.Ascii;
import java.util.Arrays;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzht  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzht {
    private static long zza(byte[] bArr, int i, int i2) {
        return (zza(bArr, i) >> i2) & 67108863;
    }

    private static long zza(byte[] bArr, int i) {
        return ((long) (((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16))) & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
    }

    private static void zza(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) ((int) (255 & j));
            i2++;
            j >>= 8;
        }
    }

    public static byte[] zza(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (bArr3.length == 32) {
            long zza = zza(bArr3, 0, 0) & 67108863;
            int i = 3;
            long zza2 = zza(bArr3, 3, 2) & 67108611;
            long zza3 = zza(bArr3, 6, 4) & 67092735;
            long zza4 = zza(bArr3, 9, 6) & 66076671;
            long zza5 = zza(bArr3, 12, 8) & 1048575;
            long j = zza2 * 5;
            long j2 = zza3 * 5;
            long j3 = zza4 * 5;
            long j4 = zza5 * 5;
            int i2 = 17;
            byte[] bArr5 = new byte[17];
            long j5 = 0;
            int i3 = 0;
            long j6 = 0;
            long j7 = 0;
            long j8 = 0;
            long j9 = 0;
            while (i3 < bArr4.length) {
                int min = Math.min(16, bArr4.length - i3);
                System.arraycopy(bArr4, i3, bArr5, 0, min);
                bArr5[min] = 1;
                if (min != 16) {
                    Arrays.fill(bArr5, min + 1, i2, (byte) 0);
                }
                long zza6 = j9 + zza(bArr5, 0, 0);
                long zza7 = j5 + zza(bArr5, i, 2);
                long zza8 = j6 + zza(bArr5, 6, 4);
                long zza9 = j7 + zza(bArr5, 9, 6);
                long zza10 = j8 + (zza(bArr5, 12, 8) | ((long) (bArr5[16] << Ascii.CAN)));
                long j10 = (zza6 * zza) + (zza7 * j4) + (zza8 * j3) + (zza9 * j2) + (zza10 * j);
                long j11 = (zza6 * zza2) + (zza7 * zza) + (zza8 * j4) + (zza9 * j3) + (zza10 * j2) + (j10 >> 26);
                long j12 = (zza6 * zza3) + (zza7 * zza2) + (zza8 * zza) + (zza9 * j4) + (zza10 * j3) + (j11 >> 26);
                long j13 = (zza6 * zza4) + (zza7 * zza3) + (zza8 * zza2) + (zza9 * zza) + (zza10 * j4) + (j12 >> 26);
                long j14 = (zza6 * zza5) + (zza7 * zza4) + (zza8 * zza3) + (zza9 * zza2) + (zza10 * zza) + (j13 >> 26);
                long j15 = (j10 & 67108863) + ((j14 >> 26) * 5);
                j5 = (j11 & 67108863) + (j15 >> 26);
                i3 += 16;
                j6 = j12 & 67108863;
                j7 = j13 & 67108863;
                j8 = j14 & 67108863;
                i2 = 17;
                i = 3;
                j9 = j15 & 67108863;
            }
            long j16 = j6 + (j5 >> 26);
            long j17 = j16 & 67108863;
            long j18 = j7 + (j16 >> 26);
            long j19 = j18 & 67108863;
            long j20 = j8 + (j18 >> 26);
            long j21 = j20 & 67108863;
            long j22 = j9 + ((j20 >> 26) * 5);
            long j23 = j22 & 67108863;
            long j24 = (j5 & 67108863) + (j22 >> 26);
            long j25 = j23 + 5;
            long j26 = j25 & 67108863;
            long j27 = (j25 >> 26) + j24;
            long j28 = j17 + (j27 >> 26);
            long j29 = j19 + (j28 >> 26);
            long j30 = (j21 + (j29 >> 26)) - 67108864;
            long j31 = j30 >> 63;
            long j32 = ~j31;
            long j33 = (j24 & j31) | (j27 & 67108863 & j32);
            long j34 = (j17 & j31) | (j28 & 67108863 & j32);
            long j35 = (j19 & j31) | (j29 & 67108863 & j32);
            long j36 = ((j33 << 26) | (j23 & j31) | (j26 & j32)) & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
            long j37 = ((j33 >> 6) | (j34 << 20)) & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
            long j38 = ((j34 >> 12) | (j35 << 14)) & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
            long j39 = ((((j30 & j32) | (j21 & j31)) << 8) | (j35 >> 18)) & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
            long zza11 = j36 + zza(bArr3, 16);
            long j40 = zza11 & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
            long zza12 = j37 + zza(bArr3, 20) + (zza11 >> 32);
            long j41 = zza12 & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
            long zza13 = j38 + zza(bArr3, 24) + (zza12 >> 32);
            long j42 = zza13 & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
            long zza14 = (j39 + zza(bArr3, 28) + (zza13 >> 32)) & MuxerUtil.UNSIGNED_INT_MAX_VALUE;
            byte[] bArr6 = new byte[16];
            zza(bArr6, j40, 0);
            zza(bArr6, j41, 4);
            zza(bArr6, j42, 8);
            zza(bArr6, zza14, 12);
            return bArr6;
        }
        throw new IllegalArgumentException("The key length in bytes must be 32.");
    }
}
