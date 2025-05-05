package com.google.ads.interactivemedia.v3.impl;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class AutoValue_AdsLoaderImpl_MarketAppInfo extends zzw {
    private final int appVersion;
    private final String packageName;

    AutoValue_AdsLoaderImpl_MarketAppInfo(int i, String str) {
        this.appVersion = i;
        if (str != null) {
            this.packageName = str;
            return;
        }
        throw new NullPointerException("Null packageName");
    }

    public int appVersion() {
        return this.appVersion;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzw) {
            zzw zzw = (zzw) obj;
            return this.appVersion == zzw.appVersion() && this.packageName.equals(zzw.packageName());
        }
    }

    public int hashCode() {
        return ((this.appVersion ^ 1000003) * 1000003) ^ this.packageName.hashCode();
    }

    public String packageName() {
        return this.packageName;
    }

    public String toString() {
        return "MarketAppInfo{appVersion=" + this.appVersion + ", packageName=" + this.packageName + "}";
    }
}
