package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Objects;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzne  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzne extends zzch {
    private final zzpf zza;

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza.zza(), this.zza.zzc()});
    }

    public final zzpf zzb() {
        return this.zza;
    }

    public final String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.zza.zza().zzf();
        int i = zznh.zza[this.zza.zza().zzd().ordinal()];
        objArr[1] = i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNKNOWN" : "CRUNCHY" : "RAW" : "LEGACY" : "TINK";
        return String.format("(typeUrl=%s, outputPrefixType=%s)", objArr);
    }

    public zzne(zzpf zzpf) {
        this.zza = zzpf;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzne)) {
            return false;
        }
        zzpf zzpf = ((zzne) obj).zza;
        if (!this.zza.zza().zzd().equals(zzpf.zza().zzd()) || !this.zza.zza().zzf().equals(zzpf.zza().zzf()) || !this.zza.zza().zze().equals(zzpf.zza().zze())) {
            return false;
        }
        return true;
    }

    public final boolean zza() {
        return this.zza.zza().zzd() != zzws.RAW;
    }
}
