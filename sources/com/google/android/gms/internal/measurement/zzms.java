package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
public enum zzms {
    DOUBLE(zzmz.DOUBLE, 1),
    FLOAT(zzmz.FLOAT, 5),
    INT64(zzmz.LONG, 0),
    UINT64(zzmz.LONG, 0),
    INT32(zzmz.INT, 0),
    FIXED64(zzmz.LONG, 1),
    FIXED32(zzmz.INT, 5),
    BOOL(zzmz.BOOLEAN, 0),
    STRING(zzmz.STRING, (zzmz) null),
    GROUP(zzmz.MESSAGE, (zzmz) null),
    MESSAGE(zzmz.MESSAGE, (zzmz) null),
    BYTES(zzmz.BYTE_STRING, (zzmz) null),
    UINT32(zzmz.INT, 0),
    ENUM(zzmz.ENUM, 0),
    SFIXED32(zzmz.INT, 5),
    SFIXED64(zzmz.LONG, 1),
    SINT32(zzmz.INT, 0),
    SINT64(zzmz.LONG, 0);
    
    private final zzmz zzt;
    private final int zzu;

    public final int zza() {
        return this.zzu;
    }

    public final zzmz zzb() {
        return this.zzt;
    }

    private zzms(zzmz zzmz, int i) {
        this.zzt = zzmz;
        this.zzu = i;
    }
}
