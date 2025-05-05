package com.google.android.recaptcha.internal;

import java.io.IOException;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public class zznp extends IOException {
    private boolean zza;

    public zznp(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zza = true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb() {
        return this.zza;
    }

    public zznp(String str) {
        super(str);
    }
}
