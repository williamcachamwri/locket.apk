package com.google.android.recaptcha.internal;

import java.security.MessageDigest;
import java.util.Arrays;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzkc extends zzjt {
    private final MessageDigest zza;
    private final int zzb;
    private boolean zzc;

    /* synthetic */ zzkc(MessageDigest messageDigest, int i, zzkd zzkd) {
        this.zza = messageDigest;
        this.zzb = i;
    }

    private final void zzc() {
        zzjh.zze(!this.zzc, "Cannot re-use a Hasher after calling hash() on it");
    }

    /* access modifiers changed from: protected */
    public final void zza(byte[] bArr, int i, int i2) {
        zzc();
        this.zza.update(bArr, 0, i2);
    }

    public final zzjx zzb() {
        zzc();
        this.zzc = true;
        int i = this.zzb;
        if (i == this.zza.getDigestLength()) {
            byte[] digest = this.zza.digest();
            int i2 = zzjx.zzb;
            return new zzjw(digest);
        }
        byte[] copyOf = Arrays.copyOf(this.zza.digest(), i);
        int i3 = zzjx.zzb;
        return new zzjw(copyOf);
    }
}
