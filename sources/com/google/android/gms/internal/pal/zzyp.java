package com.google.android.gms.internal.pal;

import java.security.SecureRandom;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzyp extends ThreadLocal {
    zzyp() {
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object initialValue() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }
}
