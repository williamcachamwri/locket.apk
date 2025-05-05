package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzx {
    /* access modifiers changed from: private */
    public final zzh zza;
    private final zzad zzb;
    /* access modifiers changed from: private */
    public final int zzc;

    public static zzx zza(char c) {
        zzj zzj = new zzj(c);
        zzy.zza(zzj);
        return new zzx(new zzaa(zzj));
    }

    public static zzx zza(String str) {
        zzn zza2 = zzt.zza(str);
        if (!zza2.zza("").zzc()) {
            return new zzx(new zzac(zza2));
        }
        throw new IllegalArgumentException(zzag.zza("The pattern may not match the empty string: %s", zza2));
    }

    public final List<String> zza(CharSequence charSequence) {
        zzy.zza(charSequence);
        Iterator<String> zza2 = this.zzb.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zza2.hasNext()) {
            arrayList.add(zza2.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    private zzx(zzad zzad) {
        this(zzad, false, zzl.zza, Integer.MAX_VALUE);
    }

    private zzx(zzad zzad, boolean z, zzh zzh, int i) {
        this.zzb = zzad;
        this.zza = zzh;
        this.zzc = Integer.MAX_VALUE;
    }
}
