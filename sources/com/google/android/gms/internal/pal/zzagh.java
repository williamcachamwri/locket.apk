package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzagh {
    static {
        Math.log(10.0d);
    }

    public static void zza(StringBuffer stringBuffer, long j) {
        int i = (int) j;
        if (((long) i) == j) {
            if (i < 0) {
                stringBuffer.append('-');
                if (i != Integer.MIN_VALUE) {
                    i = -i;
                } else {
                    stringBuffer.append("2147483648");
                    return;
                }
            }
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
            } else if (i < 100) {
                int i2 = ((i + 1) * 13421772) >> 27;
                stringBuffer.append((char) (i2 + 48));
                stringBuffer.append((char) (((i - (i2 << 3)) - (i2 + i2)) + 48));
            } else {
                stringBuffer.append(Integer.toString(i));
            }
        } else {
            stringBuffer.append(Long.toString(j));
        }
    }
}
