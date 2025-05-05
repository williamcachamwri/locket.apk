package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzpp extends RuntimeException {
    public static <T> T zza(zzpo<T> zzpo) {
        try {
            return zzpo.zza();
        } catch (Exception e) {
            throw new zzpp((Throwable) e);
        }
    }

    public zzpp(String str) {
        super(str);
    }

    private zzpp(Throwable th) {
        super(th);
    }

    public zzpp(String str, Throwable th) {
        super(str, th);
    }
}
