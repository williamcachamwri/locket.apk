package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public enum zzx implements zzdj {
    SIGNAL_COLLECTION_ERROR_UNKNOWN(0),
    SIGNAL_COLLECTION_ERROR_RUNTIME_EXCEPTION(1),
    SIGNAL_COLLECTION_ERROR_NAME_NOT_FOUND(2),
    SIGNAL_COLLECTION_ERROR_BUILD_FINGERPRINT_PREFIX(3),
    SIGNAL_COLLECTION_ERROR_NULL_CONTENT_PROVIDER_DATA(4);
    
    private static final zzdk zzf = null;
    private final int zzh;

    static {
        zzf = new zzv();
    }

    private zzx(int i) {
        this.zzh = i;
    }

    public final String toString() {
        return Integer.toString(this.zzh);
    }

    public final int zza() {
        return this.zzh;
    }
}
