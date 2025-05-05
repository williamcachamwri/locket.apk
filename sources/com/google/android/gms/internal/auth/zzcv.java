package com.google.android.gms.internal.auth;

import io.sentry.android.core.SentryLogcatAdapter;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
final class zzcv extends zzdc {
    zzcv(zzcz zzcz, String str, Long l, boolean z) {
        super(zzcz, str, l, true, (zzdb) null);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object zza(Object obj) {
        try {
            return Long.valueOf(Long.parseLong((String) obj));
        } catch (NumberFormatException unused) {
            String str = this.zzc;
            SentryLogcatAdapter.e("PhenotypeFlag", "Invalid long value for " + str + ": " + ((String) obj));
            return null;
        }
    }
}
