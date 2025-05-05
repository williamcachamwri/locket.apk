package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzkk {
    private final zzvt zza;

    private zzkk(zzvt zzvt) {
        this.zza = zzvt;
    }

    public static zzkk zzd(String str, byte[] bArr, int i) {
        zzvs zza2 = zzvt.zza();
        zza2.zza(str);
        zza2.zzb(zzaby.zzn(bArr));
        int i2 = i - 1;
        zza2.zzc(i2 != 0 ? i2 != 1 ? 5 : 4 : 3);
        return new zzkk((zzvt) zza2.zzan());
    }

    public final String zza() {
        return this.zza.zzf();
    }

    public final byte[] zzb() {
        return this.zza.zze().zzt();
    }

    public final int zzc() {
        int zzi = this.zza.zzi() - 2;
        int i = 1;
        if (zzi != 1) {
            i = 2;
            if (zzi != 2) {
                i = 3;
                if (zzi != 3) {
                    if (zzi == 4) {
                        return 4;
                    }
                    throw new IllegalArgumentException("Unknown output prefix type");
                }
            }
        }
        return i;
    }
}
