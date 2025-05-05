package com.google.android.gms.internal.pal;

import android.util.Base64;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzbj {
    public static String zza(byte[] bArr, boolean z) {
        return Base64.encodeToString(bArr, true != z ? 2 : 11);
    }

    public static byte[] zzb(String str, boolean z) throws IllegalArgumentException {
        byte[] decode = Base64.decode(str, true != z ? 2 : 11);
        if (decode.length != 0 || str.length() <= 0) {
            return decode;
        }
        throw new IllegalArgumentException("Unable to decode ".concat(String.valueOf(str)));
    }
}
