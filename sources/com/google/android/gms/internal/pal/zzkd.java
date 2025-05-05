package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
class zzkd implements zzkb {
    private final zzpa zza;
    private final Class zzb;

    public zzkd(zzpa zzpa, Class cls) {
        if (zzpa.zzl().contains(cls) || Void.class.equals(cls)) {
            this.zza = zzpa;
            this.zzb = cls;
            return;
        }
        throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", new Object[]{zzpa.toString(), cls.getName()}));
    }

    private final zzkc zzg() {
        return new zzkc(this.zza.zza());
    }

    private final Object zzh(zzaef zzaef) throws GeneralSecurityException {
        if (!Void.class.equals(this.zzb)) {
            this.zza.zze(zzaef);
            return this.zza.zzk(zzaef, this.zzb);
        }
        throw new GeneralSecurityException("Cannot create a primitive for Void");
    }

    public final zzvo zza(zzaby zzaby) throws GeneralSecurityException {
        try {
            zzaef zza2 = zzg().zza(zzaby);
            zzvl zza3 = zzvo.zza();
            zza3.zzb(this.zza.zzd());
            zza3.zzc(zza2.zzaI());
            zza3.zza(this.zza.zzb());
            return (zzvo) zza3.zzan();
        } catch (zzadi e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }

    public final zzaef zzb(zzaby zzaby) throws GeneralSecurityException {
        try {
            return zzg().zza(zzaby);
        } catch (zzadi e) {
            throw new GeneralSecurityException("Failures parsing proto of type ".concat(String.valueOf(this.zza.zza().zzg().getName())), e);
        }
    }

    public final Class zzc() {
        return this.zzb;
    }

    public final Object zzd(zzaby zzaby) throws GeneralSecurityException {
        try {
            return zzh(this.zza.zzc(zzaby));
        } catch (zzadi e) {
            throw new GeneralSecurityException("Failures parsing proto of type ".concat(String.valueOf(this.zza.zzj().getName())), e);
        }
    }

    public final Object zze(zzaef zzaef) throws GeneralSecurityException {
        String concat = "Expected proto of type ".concat(String.valueOf(this.zza.zzj().getName()));
        if (this.zza.zzj().isInstance(zzaef)) {
            return zzh(zzaef);
        }
        throw new GeneralSecurityException(concat);
    }

    public final String zzf() {
        return this.zza.zzd();
    }
}
