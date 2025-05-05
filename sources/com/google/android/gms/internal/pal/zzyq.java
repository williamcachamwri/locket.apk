package com.google.android.gms.internal.pal;

import java.security.SecureRandom;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzyq {
    private static final ThreadLocal zza = new zzyp();

    public static byte[] zza(int i) {
        byte[] bArr = new byte[i];
        ((SecureRandom) zza.get()).nextBytes(bArr);
        return bArr;
    }
}
