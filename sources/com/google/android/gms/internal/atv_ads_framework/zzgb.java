package com.google.android.gms.internal.atv_ads_framework;

import com.google.common.base.Ascii;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzgb extends zzga {
    zzgb() {
    }

    /* access modifiers changed from: package-private */
    public final int zza(int i, byte[] bArr, int i2, int i3) {
        int i4;
        int i5 = 0;
        while (i4 < i3 && bArr[i4] >= 0) {
            i5 = i4 + 1;
        }
        if (i4 >= i3) {
            return 0;
        }
        while (i4 < i3) {
            int i6 = i4 + 1;
            byte b = bArr[i4];
            if (b < 0) {
                if (b < -32) {
                    if (i6 >= i3) {
                        return b;
                    }
                    if (b >= -62) {
                        i4 = i6 + 1;
                        if (bArr[i6] > -65) {
                        }
                    }
                } else if (b < -16) {
                    if (i6 >= i3 - 1) {
                        return zzgd.zza(bArr, i6, i3);
                    }
                    int i7 = i6 + 1;
                    byte b2 = bArr[i6];
                    if (b2 <= -65 && ((b != -32 || b2 >= -96) && (b != -19 || b2 < -96))) {
                        i4 = i7 + 1;
                        if (bArr[i7] > -65) {
                        }
                    }
                } else if (i6 >= i3 - 2) {
                    return zzgd.zza(bArr, i6, i3);
                } else {
                    int i8 = i6 + 1;
                    byte b3 = bArr[i6];
                    if (b3 <= -65 && (((b << Ascii.FS) + (b3 + 112)) >> 30) == 0) {
                        int i9 = i8 + 1;
                        if (bArr[i8] <= -65) {
                            i6 = i9 + 1;
                            if (bArr[i9] > -65) {
                            }
                        }
                    }
                }
                return -1;
            }
            i4 = i6;
        }
        return 0;
    }
}
