package com.google.android.gms.internal.auth;

import io.sentry.android.core.SentryLogcatAdapter;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzcx extends zzdc {
    zzcx(zzcz zzcz, String str, Double d, boolean z) {
        super(zzcz, str, d, true, (zzdb) null);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object zza(Object obj) {
        try {
            return Double.valueOf(Double.parseDouble((String) obj));
        } catch (NumberFormatException unused) {
            String str = this.zzc;
            SentryLogcatAdapter.e("PhenotypeFlag", "Invalid double value for " + str + ": " + ((String) obj));
            return null;
        }
    }
}
