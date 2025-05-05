package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzeg implements zzez {
    private static final zzem zza = new zzee();
    private final zzem zzb;

    public zzeg() {
        zzem zzem;
        zzem[] zzemArr = new zzem[2];
        zzemArr[0] = zzde.zza();
        try {
            zzem = (zzem) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            zzem = zza;
        }
        zzemArr[1] = zzem;
        zzef zzef = new zzef(zzemArr);
        byte[] bArr = zzdp.zzd;
        this.zzb = zzef;
    }

    private static boolean zzb(zzel zzel) {
        return zzel.zzc() == 1;
    }

    public final zzey zza(Class cls) {
        zzfa.zzB(cls);
        zzel zzb2 = this.zzb.zzb(cls);
        if (zzb2.zzb()) {
            if (zzdh.class.isAssignableFrom(cls)) {
                return zzes.zzi(zzfa.zzz(), zzda.zzb(), zzb2.zza());
            }
            return zzes.zzi(zzfa.zzy(), zzda.zza(), zzb2.zza());
        } else if (zzdh.class.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzer.zzi(cls, zzb2, zzeu.zzb(), zzec.zzd(), zzfa.zzz(), zzda.zzb(), zzek.zzb());
            }
            return zzer.zzi(cls, zzb2, zzeu.zzb(), zzec.zzd(), zzfa.zzz(), (zzcy) null, zzek.zzb());
        } else if (zzb(zzb2)) {
            return zzer.zzi(cls, zzb2, zzeu.zza(), zzec.zzc(), zzfa.zzy(), zzda.zza(), zzek.zza());
        } else {
            return zzer.zzi(cls, zzb2, zzeu.zza(), zzec.zzc(), zzfa.zzy(), (zzcy) null, zzek.zza());
        }
    }
}
