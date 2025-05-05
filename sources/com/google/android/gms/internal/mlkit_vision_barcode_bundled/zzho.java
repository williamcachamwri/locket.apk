package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public enum zzho {
    DOUBLE(zzhp.DOUBLE, 1),
    FLOAT(zzhp.FLOAT, 5),
    INT64(zzhp.LONG, 0),
    UINT64(zzhp.LONG, 0),
    INT32(zzhp.INT, 0),
    FIXED64(zzhp.LONG, 1),
    FIXED32(zzhp.INT, 5),
    BOOL(zzhp.BOOLEAN, 0),
    STRING(zzhp.STRING, 2),
    GROUP(zzhp.MESSAGE, 3),
    MESSAGE(zzhp.MESSAGE, 2),
    BYTES(zzhp.BYTE_STRING, 2),
    UINT32(zzhp.INT, 0),
    ENUM(zzhp.ENUM, 0),
    SFIXED32(zzhp.INT, 5),
    SFIXED64(zzhp.LONG, 1),
    SINT32(zzhp.INT, 0),
    SINT64(zzhp.LONG, 0);
    
    private final zzhp zzt;

    private zzho(zzhp zzhp, int i) {
        this.zzt = zzhp;
    }

    public final zzhp zza() {
        return this.zzt;
    }
}
