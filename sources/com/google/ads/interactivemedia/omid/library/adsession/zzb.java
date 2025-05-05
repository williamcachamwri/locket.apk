package com.google.ads.interactivemedia.omid.library.adsession;

import com.google.ads.interactivemedia.v3.internal.zzdl;
import com.google.ads.interactivemedia.v3.internal.zzdp;
import org.json.JSONObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzb {
    private final zzi zza;
    private final zzi zzb;
    private final zzf zzc;
    private final zzh zzd;

    private zzb(zzf zzf, zzh zzh, zzi zzi, zzi zzi2, boolean z) {
        this.zzc = zzf;
        this.zzd = zzh;
        this.zza = zzi;
        if (zzi2 == null) {
            this.zzb = zzi.NONE;
        } else {
            this.zzb = zzi2;
        }
    }

    public static zzb zza(zzf zzf, zzh zzh, zzi zzi, zzi zzi2, boolean z) {
        zzdp.zzb(zzf, "CreativeType is null");
        zzdp.zzb(zzh, "ImpressionType is null");
        zzdp.zzb(zzi, "Impression owner is null");
        if (zzi == zzi.NONE) {
            throw new IllegalArgumentException("Impression owner is none");
        } else if (zzf == zzf.DEFINED_BY_JAVASCRIPT && zzi == zzi.NATIVE) {
            throw new IllegalArgumentException("ImpressionType/CreativeType can only be defined as DEFINED_BY_JAVASCRIPT if Impression Owner is JavaScript");
        } else if (zzh != zzh.DEFINED_BY_JAVASCRIPT || zzi != zzi.NATIVE) {
            return new zzb(zzf, zzh, zzi, zzi2, true);
        } else {
            throw new IllegalArgumentException("ImpressionType/CreativeType can only be defined as DEFINED_BY_JAVASCRIPT if Impression Owner is JavaScript");
        }
    }

    public final JSONObject zzb() {
        JSONObject jSONObject = new JSONObject();
        zzdl.zze(jSONObject, "impressionOwner", this.zza);
        zzdl.zze(jSONObject, "mediaEventsOwner", this.zzb);
        zzdl.zze(jSONObject, "creativeType", this.zzc);
        zzdl.zze(jSONObject, "impressionType", this.zzd);
        zzdl.zze(jSONObject, "isolateVerificationScripts", true);
        return jSONObject;
    }
}
