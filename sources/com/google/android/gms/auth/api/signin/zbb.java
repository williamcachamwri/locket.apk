package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil;

/* compiled from: com.google.android.gms:play-services-auth@@21.2.0 */
final class zbb implements PendingResultUtil.ResultConverter {
    private zbb() {
        throw null;
    }

    /* synthetic */ zbb(zba zba) {
    }

    public final /* synthetic */ Object convert(Result result) {
        return ((GoogleSignInResult) result).getSignInAccount();
    }
}
