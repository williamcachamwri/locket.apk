package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzagn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzagn {
    public static long zza(String str) {
        zzagq zzb = zzb(str);
        return zzb.zza().longValue() - zzb.zzb().longValue();
    }

    private static zzagq zzb(String str) {
        Preconditions.checkNotEmpty(str);
        List<String> zza = zzx.zza((char) FilenameUtils.EXTENSION_SEPARATOR).zza((CharSequence) str);
        if (zza.size() >= 2) {
            try {
                return zzagq.zza(new String(Base64Utils.decodeUrlSafeNoPadding(zza.get(1)), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Unable to decode token", e);
            }
        } else {
            throw new RuntimeException("Invalid idToken " + str);
        }
    }
}
