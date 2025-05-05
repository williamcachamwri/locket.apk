package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public enum zzags {
    DOUBLE(zzagt.DOUBLE, 1),
    FLOAT(zzagt.FLOAT, 5),
    INT64(zzagt.LONG, 0),
    UINT64(zzagt.LONG, 0),
    INT32(zzagt.INT, 0),
    FIXED64(zzagt.LONG, 1),
    FIXED32(zzagt.INT, 5),
    BOOL(zzagt.BOOLEAN, 0),
    STRING(zzagt.STRING, 2),
    GROUP(zzagt.MESSAGE, 3),
    MESSAGE(zzagt.MESSAGE, 2),
    BYTES(zzagt.BYTE_STRING, 2),
    UINT32(zzagt.INT, 0),
    ENUM(zzagt.ENUM, 0),
    SFIXED32(zzagt.INT, 5),
    SFIXED64(zzagt.LONG, 1),
    SINT32(zzagt.INT, 0),
    SINT64(zzagt.LONG, 0);
    
    private final zzagt zzt;

    private zzags(zzagt zzagt, int i) {
        this.zzt = zzagt;
    }

    public final zzagt zza() {
        return this.zzt;
    }
}
