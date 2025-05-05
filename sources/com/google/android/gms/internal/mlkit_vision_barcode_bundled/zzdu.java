package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public enum zzdu {
    DOUBLE(0, 1, zzep.DOUBLE),
    FLOAT(1, 1, zzep.FLOAT),
    INT64(2, 1, zzep.LONG),
    UINT64(3, 1, zzep.LONG),
    INT32(4, 1, zzep.INT),
    FIXED64(5, 1, zzep.LONG),
    FIXED32(6, 1, zzep.INT),
    BOOL(7, 1, zzep.BOOLEAN),
    STRING(8, 1, zzep.STRING),
    MESSAGE(9, 1, zzep.MESSAGE),
    BYTES(10, 1, zzep.BYTE_STRING),
    UINT32(11, 1, zzep.INT),
    ENUM(12, 1, zzep.ENUM),
    SFIXED32(13, 1, zzep.INT),
    SFIXED64(14, 1, zzep.LONG),
    SINT32(15, 1, zzep.INT),
    SINT64(16, 1, zzep.LONG),
    GROUP(17, 1, zzep.MESSAGE),
    DOUBLE_LIST(18, 2, zzep.DOUBLE),
    FLOAT_LIST(19, 2, zzep.FLOAT),
    INT64_LIST(20, 2, zzep.LONG),
    UINT64_LIST(21, 2, zzep.LONG),
    INT32_LIST(22, 2, zzep.INT),
    FIXED64_LIST(23, 2, zzep.LONG),
    FIXED32_LIST(24, 2, zzep.INT),
    BOOL_LIST(25, 2, zzep.BOOLEAN),
    STRING_LIST(26, 2, zzep.STRING),
    MESSAGE_LIST(27, 2, zzep.MESSAGE),
    BYTES_LIST(28, 2, zzep.BYTE_STRING),
    UINT32_LIST(29, 2, zzep.INT),
    ENUM_LIST(30, 2, zzep.ENUM),
    SFIXED32_LIST(31, 2, zzep.INT),
    SFIXED64_LIST(32, 2, zzep.LONG),
    SINT32_LIST(33, 2, zzep.INT),
    SINT64_LIST(34, 2, zzep.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzep.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzep.FLOAT),
    INT64_LIST_PACKED(37, 3, zzep.LONG),
    UINT64_LIST_PACKED(38, 3, zzep.LONG),
    INT32_LIST_PACKED(39, 3, zzep.INT),
    FIXED64_LIST_PACKED(40, 3, zzep.LONG),
    FIXED32_LIST_PACKED(41, 3, zzep.INT),
    BOOL_LIST_PACKED(42, 3, zzep.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzep.INT),
    ENUM_LIST_PACKED(44, 3, zzep.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzep.INT),
    SFIXED64_LIST_PACKED(46, 3, zzep.LONG),
    SINT32_LIST_PACKED(47, 3, zzep.INT),
    SINT64_LIST_PACKED(48, 3, zzep.LONG),
    GROUP_LIST(49, 2, zzep.MESSAGE),
    MAP(50, 4, zzep.VOID);
    
    private static final zzdu[] zzZ = null;
    private final zzep zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzZ = new zzdu[r1];
        for (zzdu zzdu : values()) {
            zzZ[zzdu.zzac] = zzdu;
        }
    }

    private zzdu(int i, int i2, zzep zzep) {
        this.zzac = i;
        this.zzab = zzep;
        int i3 = i2 - 1;
        if (i3 == 1) {
            this.zzad = zzep.zza();
        } else if (i3 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzep.zza();
        }
        if (i2 == 1) {
            zzep zzep2 = zzep.VOID;
            zzep.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
