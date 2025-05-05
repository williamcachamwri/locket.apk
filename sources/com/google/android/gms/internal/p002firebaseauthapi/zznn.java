package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zznn {
    private final zzng zza;
    private final List<zznp> zzb;
    @Nullable
    private final Integer zzc;

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", new Object[]{this.zza, this.zzb, this.zzc});
    }

    private zznn(zzng zzng, List<zznp> list, Integer num) {
        this.zza = zzng;
        this.zzb = list;
        this.zzc = num;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zznn)) {
            return false;
        }
        zznn zznn = (zznn) obj;
        if (!this.zza.equals(zznn.zza) || !this.zzb.equals(zznn.zzb) || !Objects.equals(this.zzc, zznn.zzc)) {
            return false;
        }
        return true;
    }
}
