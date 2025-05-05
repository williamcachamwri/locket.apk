package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
final class zzfg implements zzgi {
    private static final zzfm zza = new zzfe();
    private final zzfm zzb;

    public zzfg() {
        zzfm zzfm;
        zzfm[] zzfmArr = new zzfm[2];
        zzfmArr[0] = zzdw.zza();
        try {
            zzfm = (zzfm) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            zzfm = zza;
        }
        zzfmArr[1] = zzfm;
        zzff zzff = new zzff(zzfmArr);
        byte[] bArr = zzem.zzd;
        this.zzb = zzff;
    }

    private static boolean zzb(zzfl zzfl) {
        return zzfl.zzc() + -1 != 1;
    }

    public final zzgh zza(Class cls) {
        zzgj.zzD(cls);
        zzfl zzb2 = this.zzb.zzb(cls);
        if (zzb2.zzb()) {
            if (zzed.class.isAssignableFrom(cls)) {
                return zzfs.zzc(zzgj.zzz(), zzdr.zzb(), zzb2.zza());
            }
            return zzfs.zzc(zzgj.zzy(), zzdr.zza(), zzb2.zza());
        } else if (zzed.class.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzfr.zzl(cls, zzb2, zzfv.zzb(), zzfc.zzd(), zzgj.zzz(), zzdr.zzb(), zzfk.zzb());
            }
            return zzfr.zzl(cls, zzb2, zzfv.zzb(), zzfc.zzd(), zzgj.zzz(), (zzdp) null, zzfk.zzb());
        } else if (zzb(zzb2)) {
            return zzfr.zzl(cls, zzb2, zzfv.zza(), zzfc.zzc(), zzgj.zzy(), zzdr.zza(), zzfk.zza());
        } else {
            return zzfr.zzl(cls, zzb2, zzfv.zza(), zzfc.zzc(), zzgj.zzy(), (zzdp) null, zzfk.zza());
        }
    }
}
