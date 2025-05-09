package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.ActionCodeMultiFactorInfo;
import com.google.firebase.auth.MultiFactorInfo;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzt extends ActionCodeMultiFactorInfo {
    private final MultiFactorInfo zza;

    public final MultiFactorInfo getMultiFactorInfo() {
        return this.zza;
    }

    public zzt(String str, MultiFactorInfo multiFactorInfo) {
        this.email = Preconditions.checkNotEmpty(str);
        this.zza = (MultiFactorInfo) Preconditions.checkNotNull(multiFactorInfo);
    }
}
