package com.google.android.gms.internal.pal;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzkx implements Comparable {
    private final byte[] zza;

    /* synthetic */ zzkx(byte[] bArr, zzkw zzkw) {
        this.zza = Arrays.copyOf(bArr, bArr.length);
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzkx zzkx = (zzkx) obj;
        int length = this.zza.length;
        int length2 = zzkx.zza.length;
        if (length != length2) {
            return length - length2;
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.zza;
            if (i >= bArr.length) {
                return 0;
            }
            byte b = bArr[i];
            byte b2 = zzkx.zza[i];
            if (b != b2) {
                return b - b2;
            }
            i++;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzkx)) {
            return false;
        }
        return Arrays.equals(this.zza, ((zzkx) obj).zza);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        return zzyj.zza(this.zza);
    }
}
