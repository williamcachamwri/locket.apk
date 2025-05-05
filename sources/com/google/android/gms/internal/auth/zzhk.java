package com.google.android.gms.internal.auth;

import com.google.common.base.Ascii;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzhk {
    static /* bridge */ /* synthetic */ void zza(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) {
        if (zze(b2) || (((b << Ascii.FS) + (b2 + 112)) >> 30) != 0 || zze(b3) || zze(b4)) {
            throw zzfb.zzb();
        }
        byte b5 = b2 & Utf8.REPLACEMENT_BYTE;
        byte b6 = b3 & Utf8.REPLACEMENT_BYTE;
        byte b7 = ((b & 7) << Ascii.DC2) | (b5 << 12) | (b6 << 6) | (b4 & Utf8.REPLACEMENT_BYTE);
        cArr[i] = (char) ((b7 >>> 10) + Utf8.HIGH_SURROGATE_HEADER);
        cArr[i + 1] = (char) ((b7 & 1023) + Utf8.LOG_SURROGATE_HEADER);
    }

    static /* bridge */ /* synthetic */ void zzc(byte b, byte b2, char[] cArr, int i) {
        if (b < -62 || zze(b2)) {
            throw zzfb.zzb();
        }
        cArr[i] = (char) (((b & Ascii.US) << 6) | (b2 & Utf8.REPLACEMENT_BYTE));
    }

    static /* bridge */ /* synthetic */ boolean zzd(byte b) {
        return b >= 0;
    }

    private static boolean zze(byte b) {
        return b > -65;
    }

    static /* bridge */ /* synthetic */ void zzb(byte b, byte b2, byte b3, char[] cArr, int i) {
        if (!zze(b2)) {
            if (b == -32) {
                if (b2 >= -96) {
                    b = -32;
                }
            }
            if (b == -19) {
                if (b2 < -96) {
                    b = -19;
                }
            }
            if (!zze(b3)) {
                cArr[i] = (char) (((b & 15) << 12) | ((b2 & Utf8.REPLACEMENT_BYTE) << 6) | (b3 & Utf8.REPLACEMENT_BYTE));
                return;
            }
        }
        throw zzfb.zzb();
    }
}
