package com.google.android.gms.internal.pal;

import java.nio.charset.Charset;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzxn {
    private static final Charset zza = Charset.forName("UTF-8");

    public static byte[] zza(String str, int i) {
        byte[] bytes = str.getBytes(zza);
        int length = bytes.length;
        zzxm zzxm = new zzxm(2, new byte[((length * 3) / 4)]);
        if (zzxm.zza(bytes, 0, length, true)) {
            int i2 = zzxm.zzb;
            byte[] bArr = zzxm.zza;
            if (i2 == bArr.length) {
                return bArr;
            }
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            return bArr2;
        }
        throw new IllegalArgumentException("bad base-64");
    }
}
