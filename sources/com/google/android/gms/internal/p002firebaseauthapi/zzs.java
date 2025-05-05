package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.regex.Matcher;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzs extends zzo {
    private final Matcher zza;

    public final int zza() {
        return this.zza.end();
    }

    public final int zzb() {
        return this.zza.start();
    }

    zzs(Matcher matcher) {
        this.zza = (Matcher) zzy.zza(matcher);
    }

    public final boolean zza(int i) {
        return this.zza.find(i);
    }

    public final boolean zzc() {
        return this.zza.matches();
    }
}
