package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzadx implements zzaes {
    private static final zzaed zza = new zzadv();
    private final zzaed zzb;

    public zzadx() {
        zzaed zzaed;
        zzaed[] zzaedArr = new zzaed[2];
        zzaedArr[0] = zzacu.zza();
        try {
            zzaed = (zzaed) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            zzaed = zza;
        }
        zzaedArr[1] = zzaed;
        zzadw zzadw = new zzadw(zzaedArr);
        zzadg.zzf(zzadw, "messageInfoFactory");
        this.zzb = zzadw;
    }

    private static boolean zzb(zzaec zzaec) {
        return zzaec.zzc() == 1;
    }

    public final zzaer zza(Class cls) {
        zzaet.zzG(cls);
        zzaec zzb2 = this.zzb.zzb(cls);
        if (zzb2.zzb()) {
            if (zzacz.class.isAssignableFrom(cls)) {
                return zzaej.zzc(zzaet.zzB(), zzacp.zzb(), zzb2.zza());
            }
            return zzaej.zzc(zzaet.zzz(), zzacp.zza(), zzb2.zza());
        } else if (zzacz.class.isAssignableFrom(cls)) {
            if (zzb(zzb2)) {
                return zzaei.zzm(cls, zzb2, zzael.zzb(), zzadt.zze(), zzaet.zzB(), zzacp.zzb(), zzaeb.zzb());
            }
            return zzaei.zzm(cls, zzb2, zzael.zzb(), zzadt.zze(), zzaet.zzB(), (zzacn) null, zzaeb.zzb());
        } else if (zzb(zzb2)) {
            return zzaei.zzm(cls, zzb2, zzael.zza(), zzadt.zzd(), zzaet.zzz(), zzacp.zza(), zzaeb.zza());
        } else {
            return zzaei.zzm(cls, zzb2, zzael.zza(), zzadt.zzd(), zzaet.zzA(), (zzacn) null, zzaeb.zza());
        }
    }
}
