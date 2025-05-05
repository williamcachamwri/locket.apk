package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.impl.data.zzbs;
import com.google.ads.interactivemedia.v3.impl.data.zzbu;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzes {
    static final zzrp zza = zzrp.zzf("IABTCF_AddtlConsent", "String", "IABTCF_gdprApplies", "Number", "IABTCF_TCString", "String", "IABUSPrivacy_String", "String", "IABGPP_HDR_GppString", "String", "IABGPP_GppSID", "String");
    /* access modifiers changed from: private */
    public final boolean zzb;
    /* access modifiers changed from: private */
    public final zzrp zzc;

    private zzes(zzrp zzrp, boolean z) {
        this.zzc = zzrp;
        this.zzb = z;
    }

    public static zzes zza(zzbu zzbu) {
        Map<String, String> map;
        zzrp zzrp = zza;
        zzbs zzbs = zzbu.consentSettingsConfig;
        if (!(zzbs == null || (map = zzbs.consentKeyTypes) == null)) {
            zzrp = zzrp.zzc(map);
        }
        return new zzes(zzrp, !zzbu.disableJsIdLessEvaluation);
    }
}
