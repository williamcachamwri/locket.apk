package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public enum zzpy {
    DOUBLE(zzpz.DOUBLE, 1),
    FLOAT(zzpz.FLOAT, 5),
    INT64(zzpz.LONG, 0),
    UINT64(zzpz.LONG, 0),
    INT32(zzpz.INT, 0),
    FIXED64(zzpz.LONG, 1),
    FIXED32(zzpz.INT, 5),
    BOOL(zzpz.BOOLEAN, 0),
    STRING(zzpz.STRING, 2),
    GROUP(zzpz.MESSAGE, 3),
    MESSAGE(zzpz.MESSAGE, 2),
    BYTES(zzpz.BYTE_STRING, 2),
    UINT32(zzpz.INT, 0),
    ENUM(zzpz.ENUM, 0),
    SFIXED32(zzpz.INT, 5),
    SFIXED64(zzpz.LONG, 1),
    SINT32(zzpz.INT, 0),
    SINT64(zzpz.LONG, 0);
    
    private final zzpz zzt;

    private zzpy(zzpz zzpz, int i) {
        this.zzt = zzpz;
    }

    public final zzpz zza() {
        return this.zzt;
    }
}
