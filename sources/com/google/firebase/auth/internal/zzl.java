package com.google.firebase.auth.internal;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzl extends zzh {
    private final String zza;
    private final String zzb;
    private final String zzc;

    public final int hashCode() {
        String str = this.zza;
        int i = 0;
        int hashCode = ((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003;
        String str2 = this.zzb;
        int hashCode2 = (hashCode ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.zzc;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 ^ i;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zza;
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        return "AttestationResult{recaptchaV2Token=" + str + ", playIntegrityToken=" + str2 + ", recaptchaEnterpriseToken=" + this.zzc + "}";
    }

    private zzl(String str, String str2, String str3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzh) {
            zzh zzh = (zzh) obj;
            String str = this.zza;
            if (str != null ? str.equals(zzh.zzd()) : zzh.zzd() == null) {
                String str2 = this.zzb;
                if (str2 != null ? str2.equals(zzh.zzb()) : zzh.zzb() == null) {
                    String str3 = this.zzc;
                    return str3 != null ? str3.equals(zzh.zzc()) : zzh.zzc() == null;
                }
            }
        }
    }
}
