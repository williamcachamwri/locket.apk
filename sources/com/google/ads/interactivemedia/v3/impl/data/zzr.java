package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzr extends zzbe {
    private final String clickThroughUrl;
    private final String size;
    private final String src;
    private final zzbd type;

    zzr(String str, String str2, String str3, zzbd zzbd) {
        if (str != null) {
            this.size = str;
            if (str2 != null) {
                this.src = str2;
                if (str3 != null) {
                    this.clickThroughUrl = str3;
                    if (zzbd != null) {
                        this.type = zzbd;
                        return;
                    }
                    throw new NullPointerException("Null type");
                }
                throw new NullPointerException("Null clickThroughUrl");
            }
            throw new NullPointerException("Null src");
        }
        throw new NullPointerException("Null size");
    }

    public String clickThroughUrl() {
        return this.clickThroughUrl;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbe) {
            zzbe zzbe = (zzbe) obj;
            return this.size.equals(zzbe.size()) && this.src.equals(zzbe.src()) && this.clickThroughUrl.equals(zzbe.clickThroughUrl()) && this.type.equals(zzbe.type());
        }
    }

    public int hashCode() {
        return ((((((this.size.hashCode() ^ 1000003) * 1000003) ^ this.src.hashCode()) * 1000003) ^ this.clickThroughUrl.hashCode()) * 1000003) ^ this.type.hashCode();
    }

    public String size() {
        return this.size;
    }

    public String src() {
        return this.src;
    }

    public zzbd type() {
        return this.type;
    }
}
