package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
final class zzrs extends zzsa {
    private String zza;
    private boolean zzb;
    private int zzc;
    private byte zzd;

    zzrs() {
    }

    public final zzsa zza(boolean z) {
        this.zzb = true;
        this.zzd = (byte) (1 | this.zzd);
        return this;
    }

    public final zzsa zzb(int i) {
        this.zzc = 1;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    public final zzsa zzc(String str) {
        this.zza = "common";
        return this;
    }

    public final zzsb zzd() {
        String str;
        if (this.zzd == 3 && (str = this.zza) != null) {
            return new zzru(str, this.zzb, this.zzc, (zzrt) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" libraryName");
        }
        if ((this.zzd & 1) == 0) {
            sb.append(" enableFirelog");
        }
        if ((this.zzd & 2) == 0) {
            sb.append(" firelogEventType");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
