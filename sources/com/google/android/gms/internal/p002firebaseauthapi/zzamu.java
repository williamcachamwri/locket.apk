package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.media3.extractor.ts.PsExtractor;
import com.google.common.base.Ascii;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzamu extends zzamv {
    /* access modifiers changed from: package-private */
    public final int zza(String str, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        char charAt;
        int length = str.length();
        int i6 = i2 + i;
        int i7 = 0;
        while (i7 < length && (i5 = i7 + i) < i6 && (charAt = str.charAt(i7)) < 128) {
            bArr[i5] = (byte) charAt;
            i7++;
        }
        if (i7 == length) {
            return i + length;
        }
        int i8 = i + i7;
        while (i7 < length) {
            char charAt2 = str.charAt(i7);
            if (charAt2 < 128 && i8 < i6) {
                i4 = i8 + 1;
                bArr[i8] = (byte) charAt2;
            } else if (charAt2 < 2048 && i8 <= i6 - 2) {
                int i9 = i8 + 1;
                bArr[i8] = (byte) ((charAt2 >>> 6) | 960);
                i8 = i9 + 1;
                bArr[i9] = (byte) ((charAt2 & '?') | 128);
                i7++;
            } else if ((charAt2 < 55296 || 57343 < charAt2) && i8 <= i6 - 3) {
                int i10 = i8 + 1;
                bArr[i8] = (byte) ((charAt2 >>> 12) | 480);
                int i11 = i10 + 1;
                bArr[i10] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i4 = i11 + 1;
                bArr[i11] = (byte) ((charAt2 & '?') | 128);
            } else if (i8 <= i6 - 4) {
                int i12 = i7 + 1;
                if (i12 != str.length()) {
                    char charAt3 = str.charAt(i12);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i13 = i8 + 1;
                        bArr[i8] = (byte) ((codePoint >>> 18) | PsExtractor.VIDEO_STREAM_MASK);
                        int i14 = i13 + 1;
                        bArr[i13] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i15 = i14 + 1;
                        bArr[i14] = (byte) (((codePoint >>> 6) & 63) | 128);
                        i8 = i15 + 1;
                        bArr[i15] = (byte) ((codePoint & 63) | 128);
                        i7 = i12;
                        i7++;
                    } else {
                        i7 = i12;
                    }
                }
                throw new zzamx(i7 - 1, length);
            } else if (55296 > charAt2 || charAt2 > 57343 || ((i3 = i7 + 1) != str.length() && Character.isSurrogatePair(charAt2, str.charAt(i3)))) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i8);
            } else {
                throw new zzamx(i7, length);
            }
            i8 = i4;
            i7++;
        }
        return i8;
    }

    /* access modifiers changed from: package-private */
    public final int zza(int i, byte[] bArr, int i2, int i3) {
        while (r9 < i3 && bArr[r9] >= 0) {
            i2 = r9 + 1;
        }
        if (r9 >= i3) {
            return 0;
        }
        while (r9 < i3) {
            int i4 = r9 + 1;
            byte b = bArr[r9];
            if (b < 0) {
                if (b < -32) {
                    if (i4 >= i3) {
                        return b;
                    }
                    if (b >= -62) {
                        r9 = i4 + 1;
                        if (bArr[i4] > -65) {
                        }
                    }
                    return -1;
                } else if (b < -16) {
                    if (i4 >= i3 - 1) {
                        return zzamt.zza(bArr, i4, i3);
                    }
                    int i5 = i4 + 1;
                    byte b2 = bArr[i4];
                    if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                        r9 = i5 + 1;
                        if (bArr[i5] > -65) {
                        }
                    }
                    return -1;
                } else if (i4 >= i3 - 2) {
                    return zzamt.zza(bArr, i4, i3);
                } else {
                    int i6 = i4 + 1;
                    byte b3 = bArr[i4];
                    if (b3 <= -65 && (((b << Ascii.FS) + (b3 + 112)) >> 30) == 0) {
                        int i7 = i6 + 1;
                        if (bArr[i6] <= -65) {
                            i4 = i7 + 1;
                            if (bArr[i7] > -65) {
                            }
                        }
                    }
                    return -1;
                }
            }
            r9 = i4;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final String zza(byte[] bArr, int i, int i2) throws zzakf {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (r13 < i3) {
                byte b = bArr[r13];
                if (!(b >= 0)) {
                    break;
                }
                i = r13 + 1;
                zzams.zza(b, cArr, i4);
                i4++;
            }
            int i5 = i4;
            while (r13 < i3) {
                int i6 = r13 + 1;
                byte b2 = bArr[r13];
                if (b2 >= 0) {
                    int i7 = i5 + 1;
                    zzams.zza(b2, cArr, i5);
                    while (i6 < i3) {
                        byte b3 = bArr[i6];
                        if (!(b3 >= 0)) {
                            break;
                        }
                        i6++;
                        zzams.zza(b3, cArr, i7);
                        i7++;
                    }
                    r13 = i6;
                    i5 = i7;
                } else if (b2 < -32) {
                    if (i6 < i3) {
                        zzams.zza(b2, bArr[i6], cArr, i5);
                        r13 = i6 + 1;
                        i5++;
                    } else {
                        throw zzakf.zzd();
                    }
                } else if (b2 < -16) {
                    if (i6 < i3 - 1) {
                        int i8 = i6 + 1;
                        zzams.zza(b2, bArr[i6], bArr[i8], cArr, i5);
                        r13 = i8 + 1;
                        i5++;
                    } else {
                        throw zzakf.zzd();
                    }
                } else if (i6 < i3 - 2) {
                    int i9 = i6 + 1;
                    byte b4 = bArr[i6];
                    int i10 = i9 + 1;
                    zzams.zza(b2, b4, bArr[i9], bArr[i10], cArr, i5);
                    r13 = i10 + 1;
                    i5 = i5 + 1 + 1;
                } else {
                    throw zzakf.zzd();
                }
            }
            return new String(cArr, 0, i5);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    zzamu() {
    }
}
