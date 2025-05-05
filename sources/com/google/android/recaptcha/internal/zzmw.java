package com.google.android.recaptcha.internal;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public enum zzmw {
    DOUBLE(0, 1, zznq.DOUBLE),
    FLOAT(1, 1, zznq.FLOAT),
    INT64(2, 1, zznq.LONG),
    UINT64(3, 1, zznq.LONG),
    INT32(4, 1, zznq.INT),
    FIXED64(5, 1, zznq.LONG),
    FIXED32(6, 1, zznq.INT),
    BOOL(7, 1, zznq.BOOLEAN),
    STRING(8, 1, zznq.STRING),
    MESSAGE(9, 1, zznq.MESSAGE),
    BYTES(10, 1, zznq.BYTE_STRING),
    UINT32(11, 1, zznq.INT),
    ENUM(12, 1, zznq.ENUM),
    SFIXED32(13, 1, zznq.INT),
    SFIXED64(14, 1, zznq.LONG),
    SINT32(15, 1, zznq.INT),
    SINT64(16, 1, zznq.LONG),
    GROUP(17, 1, zznq.MESSAGE),
    DOUBLE_LIST(18, 2, zznq.DOUBLE),
    FLOAT_LIST(19, 2, zznq.FLOAT),
    INT64_LIST(20, 2, zznq.LONG),
    UINT64_LIST(21, 2, zznq.LONG),
    INT32_LIST(22, 2, zznq.INT),
    FIXED64_LIST(23, 2, zznq.LONG),
    FIXED32_LIST(24, 2, zznq.INT),
    BOOL_LIST(25, 2, zznq.BOOLEAN),
    STRING_LIST(26, 2, zznq.STRING),
    MESSAGE_LIST(27, 2, zznq.MESSAGE),
    BYTES_LIST(28, 2, zznq.BYTE_STRING),
    UINT32_LIST(29, 2, zznq.INT),
    ENUM_LIST(30, 2, zznq.ENUM),
    SFIXED32_LIST(31, 2, zznq.INT),
    SFIXED64_LIST(32, 2, zznq.LONG),
    SINT32_LIST(33, 2, zznq.INT),
    SINT64_LIST(34, 2, zznq.LONG),
    DOUBLE_LIST_PACKED(35, 3, zznq.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zznq.FLOAT),
    INT64_LIST_PACKED(37, 3, zznq.LONG),
    UINT64_LIST_PACKED(38, 3, zznq.LONG),
    INT32_LIST_PACKED(39, 3, zznq.INT),
    FIXED64_LIST_PACKED(40, 3, zznq.LONG),
    FIXED32_LIST_PACKED(41, 3, zznq.INT),
    BOOL_LIST_PACKED(42, 3, zznq.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zznq.INT),
    ENUM_LIST_PACKED(44, 3, zznq.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zznq.INT),
    SFIXED64_LIST_PACKED(46, 3, zznq.LONG),
    SINT32_LIST_PACKED(47, 3, zznq.INT),
    SINT64_LIST_PACKED(48, 3, zznq.LONG),
    GROUP_LIST(49, 2, zznq.MESSAGE),
    MAP(50, 4, zznq.VOID);
    
    private static final zzmw[] zzZ = null;
    private final int zzab;

    static {
        zzZ = new zzmw[r1];
        for (zzmw zzmw : values()) {
            zzZ[zzmw.zzab] = zzmw;
        }
    }

    private zzmw(int i, int i2, zznq zznq) {
        this.zzab = i;
        int i3 = i2 - 1;
        if (i3 == 1) {
            zznq.zza();
        } else if (i3 == 3) {
            zznq.zza();
        }
        if (i2 == 1) {
            zznq zznq2 = zznq.VOID;
            zznq.ordinal();
        }
    }

    public final int zza() {
        return this.zzab;
    }
}
