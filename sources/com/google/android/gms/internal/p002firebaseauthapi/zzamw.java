package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public enum zzamw {
    DOUBLE(zzand.DOUBLE, 1),
    FLOAT(zzand.FLOAT, 5),
    INT64(zzand.LONG, 0),
    UINT64(zzand.LONG, 0),
    INT32(zzand.INT, 0),
    FIXED64(zzand.LONG, 1),
    FIXED32(zzand.INT, 5),
    BOOL(zzand.BOOLEAN, 0),
    STRING(zzand.STRING, (zzand) null),
    GROUP(zzand.MESSAGE, (zzand) null),
    MESSAGE(zzand.MESSAGE, (zzand) null),
    BYTES(zzand.BYTE_STRING, (zzand) null),
    UINT32(zzand.INT, 0),
    ENUM(zzand.ENUM, 0),
    SFIXED32(zzand.INT, 5),
    SFIXED64(zzand.LONG, 1),
    SINT32(zzand.INT, 0),
    SINT64(zzand.LONG, 0);
    
    private final zzand zzt;
    private final int zzu;

    public final int zza() {
        return this.zzu;
    }

    public final zzand zzb() {
        return this.zzt;
    }

    private zzamw(zzand zzand, int i) {
        this.zzt = zzand;
        this.zzu = i;
    }
}
