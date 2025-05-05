package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzkz extends zzks {
    private final String zza;
    private final int zzb;

    /* synthetic */ zzkz(String str, int i, zzky zzky) {
        this.zza = str;
        this.zzb = i;
    }

    public final String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.zza;
        int i = this.zzb - 2;
        objArr[1] = i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNKNOWN" : "CRUNCHY" : "RAW" : "LEGACY" : "TINK";
        return String.format("(typeUrl=%s, outputPrefixType=%s)", objArr);
    }
}
