package com.google.android.gms.internal.auth;

import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.Nonnull;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
public final class zzbv implements ProxyApi.SpatulaHeaderResult {
    private final Status zza;
    private final String zzb;

    public zzbv(@Nonnull Status status) {
        this.zza = (Status) Preconditions.checkNotNull(status);
        this.zzb = "";
    }

    public final String getSpatulaHeader() {
        return this.zzb;
    }

    public final Status getStatus() {
        return this.zza;
    }

    public zzbv(@Nonnull String str) {
        this.zzb = (String) Preconditions.checkNotNull(str);
        this.zza = Status.RESULT_SUCCESS;
    }
}
