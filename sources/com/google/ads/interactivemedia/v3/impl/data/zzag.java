package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzag extends zzbz {
    private final String content;
    private final String contentType;
    private final int errorCode;
    private final String id;

    zzag(String str, String str2, String str3, int i) {
        if (str != null) {
            this.id = str;
            if (str2 != null) {
                this.content = str2;
                if (str3 != null) {
                    this.contentType = str3;
                    this.errorCode = i;
                    return;
                }
                throw new NullPointerException("Null contentType");
            }
            throw new NullPointerException("Null content");
        }
        throw new NullPointerException("Null id");
    }

    public String content() {
        return this.content;
    }

    public String contentType() {
        return this.contentType;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbz) {
            zzbz zzbz = (zzbz) obj;
            return this.id.equals(zzbz.id()) && this.content.equals(zzbz.content()) && this.contentType.equals(zzbz.contentType()) && this.errorCode == zzbz.errorCode();
        }
    }

    public int errorCode() {
        return this.errorCode;
    }

    public int hashCode() {
        return ((((((this.id.hashCode() ^ 1000003) * 1000003) ^ this.content.hashCode()) * 1000003) ^ this.contentType.hashCode()) * 1000003) ^ this.errorCode;
    }

    public String id() {
        return this.id;
    }

    public String toString() {
        return "NetworkResponseData{id=" + this.id + ", content=" + this.content + ", contentType=" + this.contentType + ", errorCode=" + this.errorCode + "}";
    }
}
