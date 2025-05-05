package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzwa;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoy  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzoy<P> {
    private final Class<P> zza;
    private Map<zzzc, List<zzpb<P>>> zzb;
    private final List<zzpb<P>> zzc;
    private zzpb<P> zzd;
    private zzng zze;

    private final zzoy<P> zza(P p, zzbp zzbp, zzwa.zzb zzb2, boolean z) throws GeneralSecurityException {
        if (this.zzb == null) {
            throw new IllegalStateException("addEntry cannot be called after build");
        } else if (p == null) {
            throw new NullPointerException("`fullPrimitive` must not be null");
        } else if (zzb2.zzc() == zzvv.ENABLED) {
            zzpb zzpb = new zzpb(p, zzzc.zza(zzbj.zza(zzb2)), zzb2.zzc(), zzb2.zzf(), zzb2.zza(), zzb2.zzb().zzf(), zzbp);
            Map<zzzc, List<zzpb<P>>> map = this.zzb;
            List<zzpb<P>> list = this.zzc;
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzpb);
            List put = map.put(zzpb.zzb, Collections.unmodifiableList(arrayList));
            if (put != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(put);
                arrayList2.add(zzpb);
                map.put(zzpb.zzb, Collections.unmodifiableList(arrayList2));
            }
            list.add(zzpb);
            if (z) {
                if (this.zzd == null) {
                    this.zzd = zzpb;
                } else {
                    throw new IllegalStateException("you cannot set two primary primitives");
                }
            }
            return this;
        } else {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
    }

    public final zzoy<P> zza(P p, zzbp zzbp, zzwa.zzb zzb2) throws GeneralSecurityException {
        return zza(p, zzbp, zzb2, false);
    }

    public final zzoy<P> zzb(P p, zzbp zzbp, zzwa.zzb zzb2) throws GeneralSecurityException {
        return zza(p, zzbp, zzb2, true);
    }

    public final zzoy<P> zza(zzng zzng) {
        if (this.zzb != null) {
            this.zze = zzng;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build");
    }

    public final zzoz<P> zza() throws GeneralSecurityException {
        if (this.zzb != null) {
            zzoz zzoz = new zzoz(this.zzb, this.zzc, this.zzd, this.zze, this.zza);
            this.zzb = null;
            return zzoz;
        }
        throw new IllegalStateException("build cannot be called twice");
    }

    private zzoy(Class<P> cls) {
        this.zzb = new HashMap();
        this.zzc = new ArrayList();
        this.zza = cls;
        this.zze = zzng.zza;
    }
}
