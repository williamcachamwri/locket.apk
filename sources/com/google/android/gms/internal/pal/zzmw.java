package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzmw extends zzmu {
    public zzmw(byte[] bArr) throws GeneralSecurityException {
        super(bArr);
    }

    /* access modifiers changed from: package-private */
    public final zzms zza(byte[] bArr, int i) throws InvalidKeyException {
        return new zzmv(bArr, i);
    }
}
