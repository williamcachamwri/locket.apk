package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzakq implements zzalu {
    private static final zzald zza = new zzakt();
    private final zzald zzb;

    public final <T> zzalv<T> zza(Class<T> cls) {
        zzalx.zza((Class<?>) cls);
        zzala zza2 = this.zzb.zza(cls);
        if (zza2.zzc()) {
            return zzali.zza(zzalx.zza(), zzajo.zza(), zza2.zza());
        }
        zzalk zza3 = zzalm.zza();
        zzakm zza4 = zzako.zza();
        zzamo<?, ?> zza5 = zzalx.zza();
        boolean z = true;
        if (zzaks.zza[zza2.zzb().ordinal()] == 1) {
            z = false;
        }
        return zzalg.zza(cls, zza2, zza3, zza4, zza5, z ? zzajo.zza() : null, zzalb.zza());
    }

    public zzakq() {
        this(new zzakv(zzajw.zza(), zza));
    }

    private zzakq(zzald zzald) {
        this.zzb = (zzald) zzakb.zza(zzald, "messageInfoFactory");
    }
}
