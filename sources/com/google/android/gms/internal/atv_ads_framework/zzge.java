package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public enum zzge {
    DOUBLE(zzgf.DOUBLE, 1),
    FLOAT(zzgf.FLOAT, 5),
    INT64(zzgf.LONG, 0),
    UINT64(zzgf.LONG, 0),
    INT32(zzgf.INT, 0),
    FIXED64(zzgf.LONG, 1),
    FIXED32(zzgf.INT, 5),
    BOOL(zzgf.BOOLEAN, 0),
    STRING(zzgf.STRING, 2),
    GROUP(zzgf.MESSAGE, 3),
    MESSAGE(zzgf.MESSAGE, 2),
    BYTES(zzgf.BYTE_STRING, 2),
    UINT32(zzgf.INT, 0),
    ENUM(zzgf.ENUM, 0),
    SFIXED32(zzgf.INT, 5),
    SFIXED64(zzgf.LONG, 1),
    SINT32(zzgf.INT, 0),
    SINT64(zzgf.LONG, 0);
    
    private final zzgf zzt;

    private zzge(zzgf zzgf, int i) {
        this.zzt = zzgf;
    }

    public final zzgf zza() {
        return this.zzt;
    }
}
