package com.google.android.gms.internal.atv_ads_framework;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public enum zzdd {
    DOUBLE(0, 1, zzds.DOUBLE),
    FLOAT(1, 1, zzds.FLOAT),
    INT64(2, 1, zzds.LONG),
    UINT64(3, 1, zzds.LONG),
    INT32(4, 1, zzds.INT),
    FIXED64(5, 1, zzds.LONG),
    FIXED32(6, 1, zzds.INT),
    BOOL(7, 1, zzds.BOOLEAN),
    STRING(8, 1, zzds.STRING),
    MESSAGE(9, 1, zzds.MESSAGE),
    BYTES(10, 1, zzds.BYTE_STRING),
    UINT32(11, 1, zzds.INT),
    ENUM(12, 1, zzds.ENUM),
    SFIXED32(13, 1, zzds.INT),
    SFIXED64(14, 1, zzds.LONG),
    SINT32(15, 1, zzds.INT),
    SINT64(16, 1, zzds.LONG),
    GROUP(17, 1, zzds.MESSAGE),
    DOUBLE_LIST(18, 2, zzds.DOUBLE),
    FLOAT_LIST(19, 2, zzds.FLOAT),
    INT64_LIST(20, 2, zzds.LONG),
    UINT64_LIST(21, 2, zzds.LONG),
    INT32_LIST(22, 2, zzds.INT),
    FIXED64_LIST(23, 2, zzds.LONG),
    FIXED32_LIST(24, 2, zzds.INT),
    BOOL_LIST(25, 2, zzds.BOOLEAN),
    STRING_LIST(26, 2, zzds.STRING),
    MESSAGE_LIST(27, 2, zzds.MESSAGE),
    BYTES_LIST(28, 2, zzds.BYTE_STRING),
    UINT32_LIST(29, 2, zzds.INT),
    ENUM_LIST(30, 2, zzds.ENUM),
    SFIXED32_LIST(31, 2, zzds.INT),
    SFIXED64_LIST(32, 2, zzds.LONG),
    SINT32_LIST(33, 2, zzds.INT),
    SINT64_LIST(34, 2, zzds.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzds.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzds.FLOAT),
    INT64_LIST_PACKED(37, 3, zzds.LONG),
    UINT64_LIST_PACKED(38, 3, zzds.LONG),
    INT32_LIST_PACKED(39, 3, zzds.INT),
    FIXED64_LIST_PACKED(40, 3, zzds.LONG),
    FIXED32_LIST_PACKED(41, 3, zzds.INT),
    BOOL_LIST_PACKED(42, 3, zzds.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzds.INT),
    ENUM_LIST_PACKED(44, 3, zzds.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzds.INT),
    SFIXED64_LIST_PACKED(46, 3, zzds.LONG),
    SINT32_LIST_PACKED(47, 3, zzds.INT),
    SINT64_LIST_PACKED(48, 3, zzds.LONG),
    GROUP_LIST(49, 2, zzds.MESSAGE),
    MAP(50, 4, zzds.VOID);
    
    private static final zzdd[] zzZ = null;
    private final zzds zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzZ = new zzdd[r1];
        for (zzdd zzdd : values()) {
            zzZ[zzdd.zzac] = zzdd;
        }
    }

    private zzdd(int i, int i2, zzds zzds) {
        this.zzac = i;
        this.zzab = zzds;
        zzds zzds2 = zzds.VOID;
        int i3 = i2 - 1;
        if (i3 == 1) {
            this.zzad = zzds.zza();
        } else if (i3 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzds.zza();
        }
        if (i2 == 1) {
            zzds.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
