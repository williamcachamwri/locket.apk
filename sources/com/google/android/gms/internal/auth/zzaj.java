package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzaj implements Result {
    private final Status zza;

    public zzaj(Status status) {
        this.zza = status;
    }

    public final Status getStatus() {
        return this.zza;
    }
}
