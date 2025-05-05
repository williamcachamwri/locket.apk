package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzmt extends zzmu {
    public zzmt(byte[] bArr) throws GeneralSecurityException {
        super(bArr);
    }

    /* access modifiers changed from: package-private */
    public final zzms zza(byte[] bArr, int i) throws InvalidKeyException {
        return new zzmr(bArr, i);
    }
}
