package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.ActionCodeEmailInfo;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzr extends ActionCodeEmailInfo {
    private final String zza;

    public final String getPreviousEmail() {
        return this.zza;
    }

    public zzr(String str, String str2) {
        this.email = Preconditions.checkNotEmpty(str);
        this.zza = Preconditions.checkNotEmpty(str2);
    }
}
