package com.google.android.gms.internal.fido;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public final class zzdl extends IOException {
    public zzdl(String str) {
        super(str);
    }

    public zzdl(String str, Throwable th) {
        super("Error in decoding CborValue from bytes", th);
    }
}
