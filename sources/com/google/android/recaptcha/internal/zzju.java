package com.google.android.recaptcha.internal;

import java.nio.charset.Charset;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
abstract class zzju implements zzjy {
    zzju() {
    }

    public final zzjx zza(CharSequence charSequence, Charset charset) {
        zzjz zzb = zzb();
        byte[] bytes = charSequence.toString().getBytes(charset);
        bytes.getClass();
        ((zzjt) zzb).zza(bytes, 0, bytes.length);
        return zzb.zzb();
    }
}
