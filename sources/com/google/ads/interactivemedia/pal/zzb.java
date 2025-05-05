package com.google.ads.interactivemedia.pal;

import com.google.ads.interactivemedia.pal.ConsentSettings;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzb extends ConsentSettings.Builder {
    private Boolean zza;
    private Boolean zzb;
    private Boolean zzc;

    zzb() {
    }

    /* synthetic */ zzb(ConsentSettings consentSettings, zza zza2) {
        this.zza = consentSettings.zzc();
        this.zzb = consentSettings.zza();
        this.zzc = consentSettings.zzb();
    }

    public final ConsentSettings.Builder allowStorage(Boolean bool) {
        if (bool != null) {
            this.zzb = bool;
            return this;
        }
        throw new NullPointerException("Null allowStorage");
    }

    public final ConsentSettings build() {
        Boolean bool;
        Boolean bool2 = this.zzb;
        if (bool2 != null && (bool = this.zzc) != null) {
            return new zzd(this.zza, bool2, bool, (zzc) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zzb == null) {
            sb.append(" allowStorage");
        }
        if (this.zzc == null) {
            sb.append(" directedForChildOrUnknownAge");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }

    public final ConsentSettings.Builder directedForChildOrUnknownAge(Boolean bool) {
        if (bool != null) {
            this.zzc = bool;
            return this;
        }
        throw new NullPointerException("Null directedForChildOrUnknownAge");
    }

    public final ConsentSettings.Builder enableCookiesFor3pServerSideAdInsertion(Boolean bool) {
        this.zza = bool;
        return this;
    }
}
