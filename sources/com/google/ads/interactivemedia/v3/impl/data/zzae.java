package com.google.ads.interactivemedia.v3.impl.data;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzae extends zzbw {
    private final String message;
    private final String name;
    private final String stackTrace;

    zzae(String str, String str2, String str3) {
        this.name = str;
        this.message = str2;
        this.stackTrace = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbw) {
            zzbw zzbw = (zzbw) obj;
            String str = this.name;
            if (str != null ? str.equals(zzbw.name()) : zzbw.name() == null) {
                String str2 = this.message;
                if (str2 != null ? str2.equals(zzbw.message()) : zzbw.message() == null) {
                    String str3 = this.stackTrace;
                    if (str3 != null ? str3.equals(zzbw.stackTrace()) : zzbw.stackTrace() == null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String message() {
        return this.message;
    }

    public String name() {
        return this.name;
    }

    public String stackTrace() {
        return this.stackTrace;
    }

    public String toString() {
        return "LoggableException{name=" + this.name + ", message=" + this.message + ", stackTrace=" + this.stackTrace + "}";
    }

    public int hashCode() {
        int i;
        int i2;
        String str = this.name;
        int i3 = 0;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        String str2 = this.message;
        if (str2 == null) {
            i2 = 0;
        } else {
            i2 = str2.hashCode();
        }
        int i4 = i ^ 1000003;
        String str3 = this.stackTrace;
        if (str3 != null) {
            i3 = str3.hashCode();
        }
        return (((i4 * 1000003) ^ i2) * 1000003) ^ i3;
    }
}
