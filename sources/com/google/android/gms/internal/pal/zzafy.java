package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public enum zzafy {
    DOUBLE(zzafz.DOUBLE, 1),
    FLOAT(zzafz.FLOAT, 5),
    INT64(zzafz.LONG, 0),
    UINT64(zzafz.LONG, 0),
    INT32(zzafz.INT, 0),
    FIXED64(zzafz.LONG, 1),
    FIXED32(zzafz.INT, 5),
    BOOL(zzafz.BOOLEAN, 0),
    STRING(zzafz.STRING, 2),
    GROUP(zzafz.MESSAGE, 3),
    MESSAGE(zzafz.MESSAGE, 2),
    BYTES(zzafz.BYTE_STRING, 2),
    UINT32(zzafz.INT, 0),
    ENUM(zzafz.ENUM, 0),
    SFIXED32(zzafz.INT, 5),
    SFIXED64(zzafz.LONG, 1),
    SINT32(zzafz.INT, 0),
    SINT64(zzafz.LONG, 0);
    
    private final zzafz zzt;

    private zzafy(zzafz zzafz, int i) {
        this.zzt = zzafz;
    }

    public final zzafz zza() {
        return this.zzt;
    }
}
