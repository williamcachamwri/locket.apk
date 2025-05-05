package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzoc implements zzoz {
    private static final zzoi zza = new zzoa();
    private final zzoi zzb;

    public zzoc() {
        zzoi zzoi = zza;
        int i = zzou.zza;
        zzob zzob = new zzob(zzmy.zza(), zzoi);
        byte[] bArr = zznn.zzb;
        this.zzb = zzob;
    }

    public final zzoy zza(Class cls) {
        int i = zzpa.zza;
        if (!zznf.class.isAssignableFrom(cls)) {
            int i2 = zzou.zza;
        }
        zzoh zzb2 = this.zzb.zzb(cls);
        if (!zzb2.zzb()) {
            int i3 = zzou.zza;
            return zzon.zzm(cls, zzb2, zzor.zza(), zzny.zza(), zzpa.zzm(), zzb2.zzc() + -1 != 1 ? zzmt.zza() : null, zzog.zza());
        }
        int i4 = zzou.zza;
        return zzoo.zzc(zzpa.zzm(), zzmt.zza(), zzb2.zza());
    }
}
