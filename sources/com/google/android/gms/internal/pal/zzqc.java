package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzqc extends RuntimeException {
    public zzqc(String str) {
        super(str);
    }

    public zzqc(String str, Throwable th) {
        super("Creating a LegacyProtoKey failed", th);
    }
}
