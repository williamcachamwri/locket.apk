package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.internal.zzqf;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzrp;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzad extends zzx {
    private final zzqf zza;
    private final String zzb;
    private final zzrm zzc;
    private final zzrp zzd;

    zzad(zzqf zzqf, String str, zzrm zzrm, zzrp zzrp) {
        this.zza = zzqf;
        if (str != null) {
            this.zzb = str;
            if (zzrm != null) {
                this.zzc = zzrm;
                this.zzd = zzrp;
                return;
            }
            throw new NullPointerException("Null secureSignals");
        }
        throw new NullPointerException("Null spamMsParameter");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzx) {
            zzx zzx = (zzx) obj;
            return this.zza.equals(zzx.zzb()) && this.zzb.equals(zzx.zze()) && this.zzc.equals(zzx.zzc()) && this.zzd.equals(zzx.zzd());
        }
    }

    public final int hashCode() {
        return ((((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode()) * 1000003) ^ this.zzd.hashCode();
    }

    public final String toString() {
        zzrp zzrp = this.zzd;
        zzrm zzrm = this.zzc;
        String obj = this.zza.toString();
        String obj2 = zzrm.toString();
        String obj3 = zzrp.toString();
        return "RequestSignals{identifierInfo=" + obj + ", spamMsParameter=" + this.zzb + ", secureSignals=" + obj2 + ", platformSignals=" + obj3 + "}";
    }

    /* access modifiers changed from: package-private */
    public final zzqf zzb() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final zzrm zzc() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final zzrp zzd() {
        return this.zzd;
    }

    /* access modifiers changed from: package-private */
    public final String zze() {
        return this.zzb;
    }
}
