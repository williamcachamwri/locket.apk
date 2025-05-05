package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzac extends zzbn {
    private final String adsIdentityToken;
    private final String appSetId;
    private final int appSetIdScope;
    private final String deviceId;
    private final String idType;
    private final boolean isLimitedAdTracking;

    zzac(String str, String str2, boolean z, String str3, int i, String str4) {
        this.deviceId = str;
        if (str2 != null) {
            this.idType = str2;
            this.isLimitedAdTracking = z;
            if (str3 != null) {
                this.appSetId = str3;
                this.appSetIdScope = i;
                if (str4 != null) {
                    this.adsIdentityToken = str4;
                    return;
                }
                throw new NullPointerException("Null adsIdentityToken");
            }
            throw new NullPointerException("Null appSetId");
        }
        throw new NullPointerException("Null idType");
    }

    public String adsIdentityToken() {
        return this.adsIdentityToken;
    }

    public String appSetId() {
        return this.appSetId;
    }

    public int appSetIdScope() {
        return this.appSetIdScope;
    }

    public String deviceId() {
        return this.deviceId;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbn) {
            zzbn zzbn = (zzbn) obj;
            String str = this.deviceId;
            if (str != null ? str.equals(zzbn.deviceId()) : zzbn.deviceId() == null) {
                return this.idType.equals(zzbn.idType()) && this.isLimitedAdTracking == zzbn.isLimitedAdTracking() && this.appSetId.equals(zzbn.appSetId()) && this.appSetIdScope == zzbn.appSetIdScope() && this.adsIdentityToken.equals(zzbn.adsIdentityToken());
            }
        }
    }

    public String idType() {
        return this.idType;
    }

    public boolean isLimitedAdTracking() {
        return this.isLimitedAdTracking;
    }

    public String toString() {
        return "IdentifierInfo{deviceId=" + this.deviceId + ", idType=" + this.idType + ", isLimitedAdTracking=" + this.isLimitedAdTracking + ", appSetId=" + this.appSetId + ", appSetIdScope=" + this.appSetIdScope + ", adsIdentityToken=" + this.adsIdentityToken + "}";
    }

    public int hashCode() {
        int i;
        String str = this.deviceId;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        return ((((((((((i ^ 1000003) * 1000003) ^ this.idType.hashCode()) * 1000003) ^ (true != this.isLimitedAdTracking ? 1237 : 1231)) * 1000003) ^ this.appSetId.hashCode()) * 1000003) ^ this.appSetIdScope) * 1000003) ^ this.adsIdentityToken.hashCode();
    }
}
