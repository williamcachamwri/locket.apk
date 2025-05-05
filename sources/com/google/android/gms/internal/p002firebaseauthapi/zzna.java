package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzna  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzna<P> implements zzbs<P> {
    final String zza;
    final zzvq.zzb zzb;
    private final Class<P> zzc;

    public static <P> zzbs<P> zza(String str, Class<P> cls, zzvq.zzb zzb2, zzalp<? extends zzalc> zzalp) {
        return new zzna(str, cls, zzb2, zzalp);
    }

    public static <P> zzcj<P> zza(String str, Class<P> cls, zzalp<? extends zzalc> zzalp) {
        return new zznd(str, cls, zzalp);
    }

    public final zzvq zza(zzaip zzaip) throws GeneralSecurityException {
        zzpc zzpc = (zzpc) zzof.zza().zza(zznv.zza().zza(zzof.zza().zza(zzpf.zza((zzvu) ((zzajy) zzvu.zza().zza(this.zza).zza(zzaip).zza(zzws.RAW).zze()))), (Integer) null), zzpc.class, zzbq.zza());
        return (zzvq) ((zzajy) zzvq.zza().zza(zzpc.zzf()).zza(zzpc.zzd()).zza(zzpc.zza()).zze());
    }

    public final Class<P> zza() {
        return this.zzc;
    }

    public final P zzb(zzaip zzaip) throws GeneralSecurityException {
        return zzoc.zza().zza(zzof.zza().zza(zzpc.zza(this.zza, zzaip, this.zzb, zzws.RAW, (Integer) null), zzbq.zza()), this.zzc);
    }

    public final String zzb() {
        return this.zza;
    }

    zzna(String str, Class<P> cls, zzvq.zzb zzb2, zzalp<? extends zzalc> zzalp) {
        this.zza = str;
        this.zzc = cls;
        this.zzb = zzb2;
    }
}
