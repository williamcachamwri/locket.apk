package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzvq;
import java.security.GeneralSecurityException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zznd<P> extends zzna<P> implements zzcj<P> {
    public final zzvq zzc(zzaip zzaip) throws GeneralSecurityException {
        zzbp zza = zzof.zza().zza(zzpc.zza(this.zza, zzaip, this.zzb, zzws.RAW, (Integer) null), zzbq.zza());
        if (zza instanceof zzck) {
            zzpc zzpc = (zzpc) zzof.zza().zza(((zzck) zza).zzb(), zzpc.class, zzbq.zza());
            return (zzvq) ((zzajy) zzvq.zza().zza(zzpc.zzf()).zza(zzpc.zzd()).zza(zzpc.zza()).zze());
        }
        throw new GeneralSecurityException("Key not private key");
    }

    protected zznd(String str, Class<P> cls, zzalp<? extends zzalc> zzalp) {
        super(str, cls, zzvq.zzb.ASYMMETRIC_PRIVATE, zzalp);
    }
}
