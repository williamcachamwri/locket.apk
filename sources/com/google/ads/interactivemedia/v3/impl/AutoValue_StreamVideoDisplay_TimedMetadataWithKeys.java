package com.google.ads.interactivemedia.v3.impl;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class AutoValue_StreamVideoDisplay_TimedMetadataWithKeys extends zzca {
    private final String TXXX;

    AutoValue_StreamVideoDisplay_TimedMetadataWithKeys(String str) {
        if (str != null) {
            this.TXXX = str;
            return;
        }
        throw new NullPointerException("Null TXXX");
    }

    /* access modifiers changed from: package-private */
    public String TXXX() {
        return this.TXXX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzca) {
            return this.TXXX.equals(((zzca) obj).TXXX());
        }
        return false;
    }

    public int hashCode() {
        return this.TXXX.hashCode() ^ 1000003;
    }

    public String toString() {
        return "TimedMetadataWithKeys{TXXX=" + this.TXXX + "}";
    }
}
