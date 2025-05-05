package com.google.android.gms.internal.pal;

import android.util.Base64;
import android.util.Log;
import com.google.ads.interactivemedia.pal.NonceLoaderException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzbb implements zzba {
    private final zzjy zza;

    zzbb(zzjy zzjy) {
        this.zza = zzjy;
    }

    public final String zza(String str) throws NonceLoaderException {
        try {
            return Base64.encodeToString(this.zza.zza(str.getBytes("UTF-8"), new byte[0]), 10);
        } catch (UnsupportedEncodingException | IllegalArgumentException | GeneralSecurityException e) {
            Log.e("NonceGenerator", "Failed to encrypt the string.", e);
            throw new NonceLoaderException(204, e);
        }
    }
}
