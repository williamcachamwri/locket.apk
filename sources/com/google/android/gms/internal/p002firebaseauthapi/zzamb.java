package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzamb extends zzamc {
    private final /* synthetic */ zzalw zza;

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzaly(this.zza);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzamb(zzalw zzalw) {
        super(zzalw);
        this.zza = zzalw;
    }
}
