package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzvs {
    private zzxn zza = zzxn.zza;
    private final Map zzb = new HashMap();
    private final List zzc = new ArrayList();
    private final List zzd = new ArrayList();
    private boolean zze;
    private final zzvl zzf;
    private final ArrayDeque zzg;
    private final int zzh = 1;
    private final int zzi;
    private final int zzj;

    public zzvs() {
        int i = zzvr.zzg;
        this.zze = false;
        this.zzf = zzvr.zza;
        this.zzi = zzvr.zze;
        this.zzj = zzvr.zzf;
        this.zzg = new ArrayDeque();
    }

    public final zzvr zza() {
        ArrayList arrayList = r3;
        ArrayList arrayList2 = new ArrayList(this.zzc.size() + this.zzd.size() + 3);
        arrayList2.addAll(this.zzc);
        Collections.reverse(arrayList2);
        ArrayList arrayList3 = new ArrayList(this.zzd);
        Collections.reverse(arrayList3);
        arrayList2.addAll(arrayList3);
        boolean z = zzabz.zza;
        zzxn zzxn = this.zza;
        HashMap hashMap = r2;
        HashMap hashMap2 = new HashMap(this.zzb);
        boolean z2 = this.zze;
        ArrayList arrayList4 = r2;
        ArrayList arrayList5 = new ArrayList(this.zzc);
        ArrayList arrayList6 = r2;
        ArrayList arrayList7 = new ArrayList(this.zzd);
        ArrayList arrayList8 = r2;
        ArrayList arrayList9 = new ArrayList(this.zzg);
        return new zzvr(zzxn, 1, hashMap, false, false, false, true, this.zzf, (zzwg) null, z2, true, 1, (String) null, 2, 2, arrayList4, arrayList6, arrayList, this.zzi, this.zzj, arrayList8);
    }

    public final zzvs zzb(Type type, Object obj) {
        Objects.requireNonNull(type);
        boolean z = obj instanceof zzwd;
        boolean z2 = true;
        if (!z && !(obj instanceof zzvv) && !(obj instanceof zzvt) && !(obj instanceof zzwj)) {
            z2 = false;
        }
        zzwn.zza(z2);
        if (type != Object.class) {
            if (!zzvw.class.isAssignableFrom((Class) type)) {
                if (obj instanceof zzvt) {
                    this.zzb.put(type, (zzvt) obj);
                }
                if (z || (obj instanceof zzvv)) {
                    this.zzc.add(zzzv.zzb(zzaca.zzb(type), obj));
                }
                if (obj instanceof zzwj) {
                    this.zzc.add(zzabh.zza(zzaca.zzb(type), (zzwj) obj));
                }
                return this;
            }
        }
        throw new IllegalArgumentException("Cannot override built-in adapter for ".concat(type.toString()));
    }

    public final zzvs zzc(zzwk zzwk) {
        Objects.requireNonNull(zzwk);
        this.zzc.add(zzwk);
        return this;
    }

    public final zzvs zzd() {
        this.zze = true;
        return this;
    }

    public final zzvs zze(zzpt zzpt) {
        Objects.requireNonNull(zzpt);
        this.zza = this.zza.zze(zzpt, true, false);
        return this;
    }
}
