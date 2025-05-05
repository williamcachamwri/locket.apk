package com.google.ads.interactivemedia.v3.api.signals;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class SecureSignals {
    private final String zza;

    private SecureSignals(String str) {
        this.zza = str;
    }

    public static SecureSignals create(String str) {
        return new SecureSignals(str);
    }

    public String getSecureSignal() {
        return this.zza;
    }
}
