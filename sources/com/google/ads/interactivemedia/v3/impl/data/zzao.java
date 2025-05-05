package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzao extends zzcf {
    private final zzcg adapterVersion;
    private final Boolean isPublisherCreated;
    private final String name;
    private final zzcg sdkVersion;
    private final String signals;

    zzao(zzcg zzcg, zzcg zzcg2, String str, String str2, Boolean bool) {
        this.adapterVersion = zzcg;
        this.sdkVersion = zzcg2;
        if (str != null) {
            this.name = str;
            if (str2 != null) {
                this.signals = str2;
                if (bool != null) {
                    this.isPublisherCreated = bool;
                    return;
                }
                throw new NullPointerException("Null isPublisherCreated");
            }
            throw new NullPointerException("Null signals");
        }
        throw new NullPointerException("Null name");
    }

    public zzcg adapterVersion() {
        return this.adapterVersion;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcf) {
            zzcf zzcf = (zzcf) obj;
            zzcg zzcg = this.adapterVersion;
            if (zzcg != null ? zzcg.equals(zzcf.adapterVersion()) : zzcf.adapterVersion() == null) {
                zzcg zzcg2 = this.sdkVersion;
                if (zzcg2 != null ? zzcg2.equals(zzcf.sdkVersion()) : zzcf.sdkVersion() == null) {
                    return this.name.equals(zzcf.name()) && this.signals.equals(zzcf.signals()) && this.isPublisherCreated.equals(zzcf.isPublisherCreated());
                }
            }
        }
    }

    public int hashCode() {
        int i;
        zzcg zzcg = this.adapterVersion;
        int i2 = 0;
        if (zzcg == null) {
            i = 0;
        } else {
            i = zzcg.hashCode();
        }
        zzcg zzcg2 = this.sdkVersion;
        if (zzcg2 != null) {
            i2 = zzcg2.hashCode();
        }
        return ((((((((i ^ 1000003) * 1000003) ^ i2) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.signals.hashCode()) * 1000003) ^ this.isPublisherCreated.hashCode();
    }

    public Boolean isPublisherCreated() {
        return this.isPublisherCreated;
    }

    public String name() {
        return this.name;
    }

    public zzcg sdkVersion() {
        return this.sdkVersion;
    }

    public String signals() {
        return this.signals;
    }

    public String toString() {
        zzcg zzcg = this.sdkVersion;
        String valueOf = String.valueOf(this.adapterVersion);
        String valueOf2 = String.valueOf(zzcg);
        return "SecureSignalsData{adapterVersion=" + valueOf + ", sdkVersion=" + valueOf2 + ", name=" + this.name + ", signals=" + this.signals + ", isPublisherCreated=" + this.isPublisherCreated + "}";
    }
}
