package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzfp implements zzgj {
    private static final zzfv zza = new zzfn();
    private final zzfv zzb;

    public zzfp() {
        zzfv zzfv;
        zzfv[] zzfvArr = new zzfv[2];
        zzfvArr[0] = zzes.zza();
        try {
            zzfv = (zzfv) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            zzfv = zza;
        }
        zzfvArr[1] = zzfv;
        zzfo zzfo = new zzfo(zzfvArr);
        byte[] bArr = zzfa.zzd;
        this.zzb = zzfo;
    }

    private static boolean zzb(zzfu zzfu) {
        return zzfu.zzc() + -1 != 1;
    }

    public final zzgi zza(Class cls) {
        zzgk.zze(cls);
        zzfu zzb2 = this.zzb.zzb(cls);
        if (zzb2.zzb()) {
            if (zzev.class.isAssignableFrom(cls)) {
                return zzgb.zzb(zzgk.zzb(), zzeo.zzb(), zzb2.zza());
            }
            return zzgb.zzb(zzgk.zza(), zzeo.zza(), zzb2.zza());
        } else if (zzev.class.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzga.zzj(cls, zzb2, zzgd.zzb(), zzfl.zzd(), zzgk.zzb(), zzeo.zzb(), zzft.zzb());
            }
            return zzga.zzj(cls, zzb2, zzgd.zzb(), zzfl.zzd(), zzgk.zzb(), (zzem) null, zzft.zzb());
        } else if (zzb(zzb2)) {
            return zzga.zzj(cls, zzb2, zzgd.zza(), zzfl.zzc(), zzgk.zza(), zzeo.zza(), zzft.zza());
        } else {
            return zzga.zzj(cls, zzb2, zzgd.zza(), zzfl.zzc(), zzgk.zza(), (zzem) null, zzft.zza());
        }
    }
}
