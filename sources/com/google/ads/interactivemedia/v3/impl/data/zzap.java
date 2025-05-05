package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzap extends zzcg {
    private final int major;
    private final int micro;
    private final int minor;

    zzap(int i, int i2, int i3) {
        this.major = i;
        this.minor = i2;
        this.micro = i3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzcg) {
            zzcg zzcg = (zzcg) obj;
            return this.major == zzcg.major() && this.minor == zzcg.minor() && this.micro == zzcg.micro();
        }
    }

    public int hashCode() {
        return ((((this.major ^ 1000003) * 1000003) ^ this.minor) * 1000003) ^ this.micro;
    }

    public int major() {
        return this.major;
    }

    public int micro() {
        return this.micro;
    }

    public int minor() {
        return this.minor;
    }

    public String toString() {
        return "SecureSignalsVersionData{major=" + this.major + ", minor=" + this.minor + ", micro=" + this.micro + "}";
    }
}
