package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public enum zzacs {
    DOUBLE(0, 1, zzadj.DOUBLE),
    FLOAT(1, 1, zzadj.FLOAT),
    INT64(2, 1, zzadj.LONG),
    UINT64(3, 1, zzadj.LONG),
    INT32(4, 1, zzadj.INT),
    FIXED64(5, 1, zzadj.LONG),
    FIXED32(6, 1, zzadj.INT),
    BOOL(7, 1, zzadj.BOOLEAN),
    STRING(8, 1, zzadj.STRING),
    MESSAGE(9, 1, zzadj.MESSAGE),
    BYTES(10, 1, zzadj.BYTE_STRING),
    UINT32(11, 1, zzadj.INT),
    ENUM(12, 1, zzadj.ENUM),
    SFIXED32(13, 1, zzadj.INT),
    SFIXED64(14, 1, zzadj.LONG),
    SINT32(15, 1, zzadj.INT),
    SINT64(16, 1, zzadj.LONG),
    GROUP(17, 1, zzadj.MESSAGE),
    DOUBLE_LIST(18, 2, zzadj.DOUBLE),
    FLOAT_LIST(19, 2, zzadj.FLOAT),
    INT64_LIST(20, 2, zzadj.LONG),
    UINT64_LIST(21, 2, zzadj.LONG),
    INT32_LIST(22, 2, zzadj.INT),
    FIXED64_LIST(23, 2, zzadj.LONG),
    FIXED32_LIST(24, 2, zzadj.INT),
    BOOL_LIST(25, 2, zzadj.BOOLEAN),
    STRING_LIST(26, 2, zzadj.STRING),
    MESSAGE_LIST(27, 2, zzadj.MESSAGE),
    BYTES_LIST(28, 2, zzadj.BYTE_STRING),
    UINT32_LIST(29, 2, zzadj.INT),
    ENUM_LIST(30, 2, zzadj.ENUM),
    SFIXED32_LIST(31, 2, zzadj.INT),
    SFIXED64_LIST(32, 2, zzadj.LONG),
    SINT32_LIST(33, 2, zzadj.INT),
    SINT64_LIST(34, 2, zzadj.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzadj.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzadj.FLOAT),
    INT64_LIST_PACKED(37, 3, zzadj.LONG),
    UINT64_LIST_PACKED(38, 3, zzadj.LONG),
    INT32_LIST_PACKED(39, 3, zzadj.INT),
    FIXED64_LIST_PACKED(40, 3, zzadj.LONG),
    FIXED32_LIST_PACKED(41, 3, zzadj.INT),
    BOOL_LIST_PACKED(42, 3, zzadj.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzadj.INT),
    ENUM_LIST_PACKED(44, 3, zzadj.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzadj.INT),
    SFIXED64_LIST_PACKED(46, 3, zzadj.LONG),
    SINT32_LIST_PACKED(47, 3, zzadj.INT),
    SINT64_LIST_PACKED(48, 3, zzadj.LONG),
    GROUP_LIST(49, 2, zzadj.MESSAGE),
    MAP(50, 4, zzadj.VOID);
    
    private static final zzacs[] zzZ = null;
    private final zzadj zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzZ = new zzacs[r1];
        for (zzacs zzacs : values()) {
            zzZ[zzacs.zzac] = zzacs;
        }
    }

    private zzacs(int i, int i2, zzadj zzadj) {
        this.zzac = i;
        this.zzab = zzadj;
        zzadj zzadj2 = zzadj.VOID;
        int i3 = i2 - 1;
        if (i3 == 1) {
            this.zzad = zzadj.zza();
        } else if (i3 != 3) {
            this.zzad = null;
        } else {
            this.zzad = zzadj.zza();
        }
        if (i2 == 1) {
            zzadj.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}
