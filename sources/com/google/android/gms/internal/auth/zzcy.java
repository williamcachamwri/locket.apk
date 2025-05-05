package com.google.android.gms.internal.auth;

import android.util.Base64;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzcy extends zzdc {
    final /* synthetic */ zzhy zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcy(zzcz zzcz, String str, Object obj, boolean z, zzhy zzhy) {
        super(zzcz, "getTokenRefactor__blocked_packages", obj, true, (zzdb) null);
        this.zza = zzhy;
    }

    /* access modifiers changed from: package-private */
    public final Object zza(Object obj) {
        try {
            return zzhs.zzp(Base64.decode((String) obj, 3));
        } catch (IOException | IllegalArgumentException unused) {
            String str = this.zzc;
            SentryLogcatAdapter.e("PhenotypeFlag", "Invalid byte[] value for " + str + ": " + ((String) obj));
            return null;
        }
    }
}
