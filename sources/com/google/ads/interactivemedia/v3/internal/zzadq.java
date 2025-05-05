package com.google.ads.interactivemedia.v3.internal;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public enum zzadq {
    DOUBLE(0, 1, zzaeh.DOUBLE),
    FLOAT(1, 1, zzaeh.FLOAT),
    INT64(2, 1, zzaeh.LONG),
    UINT64(3, 1, zzaeh.LONG),
    INT32(4, 1, zzaeh.INT),
    FIXED64(5, 1, zzaeh.LONG),
    FIXED32(6, 1, zzaeh.INT),
    BOOL(7, 1, zzaeh.BOOLEAN),
    STRING(8, 1, zzaeh.STRING),
    MESSAGE(9, 1, zzaeh.MESSAGE),
    BYTES(10, 1, zzaeh.BYTE_STRING),
    UINT32(11, 1, zzaeh.INT),
    ENUM(12, 1, zzaeh.ENUM),
    SFIXED32(13, 1, zzaeh.INT),
    SFIXED64(14, 1, zzaeh.LONG),
    SINT32(15, 1, zzaeh.INT),
    SINT64(16, 1, zzaeh.LONG),
    GROUP(17, 1, zzaeh.MESSAGE),
    DOUBLE_LIST(18, 2, zzaeh.DOUBLE),
    FLOAT_LIST(19, 2, zzaeh.FLOAT),
    INT64_LIST(20, 2, zzaeh.LONG),
    UINT64_LIST(21, 2, zzaeh.LONG),
    INT32_LIST(22, 2, zzaeh.INT),
    FIXED64_LIST(23, 2, zzaeh.LONG),
    FIXED32_LIST(24, 2, zzaeh.INT),
    BOOL_LIST(25, 2, zzaeh.BOOLEAN),
    STRING_LIST(26, 2, zzaeh.STRING),
    MESSAGE_LIST(27, 2, zzaeh.MESSAGE),
    BYTES_LIST(28, 2, zzaeh.BYTE_STRING),
    UINT32_LIST(29, 2, zzaeh.INT),
    ENUM_LIST(30, 2, zzaeh.ENUM),
    SFIXED32_LIST(31, 2, zzaeh.INT),
    SFIXED64_LIST(32, 2, zzaeh.LONG),
    SINT32_LIST(33, 2, zzaeh.INT),
    SINT64_LIST(34, 2, zzaeh.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzaeh.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzaeh.FLOAT),
    INT64_LIST_PACKED(37, 3, zzaeh.LONG),
    UINT64_LIST_PACKED(38, 3, zzaeh.LONG),
    INT32_LIST_PACKED(39, 3, zzaeh.INT),
    FIXED64_LIST_PACKED(40, 3, zzaeh.LONG),
    FIXED32_LIST_PACKED(41, 3, zzaeh.INT),
    BOOL_LIST_PACKED(42, 3, zzaeh.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzaeh.INT),
    ENUM_LIST_PACKED(44, 3, zzaeh.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzaeh.INT),
    SFIXED64_LIST_PACKED(46, 3, zzaeh.LONG),
    SINT32_LIST_PACKED(47, 3, zzaeh.INT),
    SINT64_LIST_PACKED(48, 3, zzaeh.LONG),
    GROUP_LIST(49, 2, zzaeh.MESSAGE),
    MAP(50, 4, zzaeh.VOID);
    
    private static final zzadq[] zzZ = null;
    private final int zzab;

    static {
        zzZ = new zzadq[r1];
        for (zzadq zzadq : values()) {
            zzZ[zzadq.zzab] = zzadq;
        }
    }

    private zzadq(int i, int i2, zzaeh zzaeh) {
        this.zzab = i;
        int i3 = i2 - 1;
        if (i3 == 1) {
            zzaeh.zza();
        } else if (i3 == 3) {
            zzaeh.zza();
        }
        if (i2 == 1) {
            zzaeh zzaeh2 = zzaeh.VOID;
            zzaeh.ordinal();
        }
    }

    public final int zza() {
        return this.zzab;
    }
}
