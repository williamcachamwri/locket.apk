package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.ActionCodeInfo;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzu extends ActionCodeInfo {
    public zzu(String str) {
        this.email = Preconditions.checkNotEmpty(str);
    }
}
