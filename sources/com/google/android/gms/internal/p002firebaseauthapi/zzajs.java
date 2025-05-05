package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public enum zzajs {
    DOUBLE(0, zzaju.SCALAR, zzakh.DOUBLE),
    FLOAT(1, zzaju.SCALAR, zzakh.FLOAT),
    INT64(2, zzaju.SCALAR, zzakh.LONG),
    UINT64(3, zzaju.SCALAR, zzakh.LONG),
    INT32(4, zzaju.SCALAR, zzakh.INT),
    FIXED64(5, zzaju.SCALAR, zzakh.LONG),
    FIXED32(6, zzaju.SCALAR, zzakh.INT),
    BOOL(7, zzaju.SCALAR, zzakh.BOOLEAN),
    STRING(8, zzaju.SCALAR, zzakh.STRING),
    MESSAGE(9, zzaju.SCALAR, zzakh.MESSAGE),
    BYTES(10, zzaju.SCALAR, zzakh.BYTE_STRING),
    UINT32(11, zzaju.SCALAR, zzakh.INT),
    ENUM(12, zzaju.SCALAR, zzakh.ENUM),
    SFIXED32(13, zzaju.SCALAR, zzakh.INT),
    SFIXED64(14, zzaju.SCALAR, zzakh.LONG),
    SINT32(15, zzaju.SCALAR, zzakh.INT),
    SINT64(16, zzaju.SCALAR, zzakh.LONG),
    GROUP(17, zzaju.SCALAR, zzakh.MESSAGE),
    DOUBLE_LIST(18, zzaju.VECTOR, zzakh.DOUBLE),
    FLOAT_LIST(19, zzaju.VECTOR, zzakh.FLOAT),
    INT64_LIST(20, zzaju.VECTOR, zzakh.LONG),
    UINT64_LIST(21, zzaju.VECTOR, zzakh.LONG),
    INT32_LIST(22, zzaju.VECTOR, zzakh.INT),
    FIXED64_LIST(23, zzaju.VECTOR, zzakh.LONG),
    FIXED32_LIST(24, zzaju.VECTOR, zzakh.INT),
    BOOL_LIST(25, zzaju.VECTOR, zzakh.BOOLEAN),
    STRING_LIST(26, zzaju.VECTOR, zzakh.STRING),
    MESSAGE_LIST(27, zzaju.VECTOR, zzakh.MESSAGE),
    BYTES_LIST(28, zzaju.VECTOR, zzakh.BYTE_STRING),
    UINT32_LIST(29, zzaju.VECTOR, zzakh.INT),
    ENUM_LIST(30, zzaju.VECTOR, zzakh.ENUM),
    SFIXED32_LIST(31, zzaju.VECTOR, zzakh.INT),
    SFIXED64_LIST(32, zzaju.VECTOR, zzakh.LONG),
    SINT32_LIST(33, zzaju.VECTOR, zzakh.INT),
    SINT64_LIST(34, zzaju.VECTOR, zzakh.LONG),
    DOUBLE_LIST_PACKED(35, zzaju.PACKED_VECTOR, zzakh.DOUBLE),
    FLOAT_LIST_PACKED(36, zzaju.PACKED_VECTOR, zzakh.FLOAT),
    INT64_LIST_PACKED(37, zzaju.PACKED_VECTOR, zzakh.LONG),
    UINT64_LIST_PACKED(38, zzaju.PACKED_VECTOR, zzakh.LONG),
    INT32_LIST_PACKED(39, zzaju.PACKED_VECTOR, zzakh.INT),
    FIXED64_LIST_PACKED(40, zzaju.PACKED_VECTOR, zzakh.LONG),
    FIXED32_LIST_PACKED(41, zzaju.PACKED_VECTOR, zzakh.INT),
    BOOL_LIST_PACKED(42, zzaju.PACKED_VECTOR, zzakh.BOOLEAN),
    UINT32_LIST_PACKED(43, zzaju.PACKED_VECTOR, zzakh.INT),
    ENUM_LIST_PACKED(44, zzaju.PACKED_VECTOR, zzakh.ENUM),
    SFIXED32_LIST_PACKED(45, zzaju.PACKED_VECTOR, zzakh.INT),
    SFIXED64_LIST_PACKED(46, zzaju.PACKED_VECTOR, zzakh.LONG),
    SINT32_LIST_PACKED(47, zzaju.PACKED_VECTOR, zzakh.INT),
    SINT64_LIST_PACKED(48, zzaju.PACKED_VECTOR, zzakh.LONG),
    GROUP_LIST(49, zzaju.VECTOR, zzakh.MESSAGE),
    MAP(50, zzaju.MAP, zzakh.VOID);
    
    private static final zzajs[] zzaz = null;
    private final int zzbb;

    public final int zza() {
        return this.zzbb;
    }

    static {
        int i;
        zzajs[] values = values();
        zzaz = new zzajs[values.length];
        for (zzajs zzajs : values) {
            zzaz[zzajs.zzbb] = zzajs;
        }
    }

    private zzajs(int i, zzaju zzaju, zzakh zzakh) {
        this.zzbb = i;
        int ordinal = zzaju.ordinal();
        if (ordinal == 1) {
            zzakh.zza();
        } else if (ordinal == 3) {
            zzakh.zza();
        }
        if (zzaju == zzaju.SCALAR) {
            int i2 = zzajv.zza[zzakh.ordinal()];
        }
    }
}
