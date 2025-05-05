package com.google.android.gms.internal.measurement;

import io.sentry.android.core.SentryLogcatAdapter;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzhn extends zzhj<Long> {
    /* access modifiers changed from: private */
    @Nullable
    /* renamed from: zzb */
    public final Long zza(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String zzb = super.zzb();
        SentryLogcatAdapter.e("PhenotypeFlag", "Invalid long value for " + zzb + ": " + String.valueOf(obj));
        return null;
    }

    zzhn(zzhr zzhr, String str, Long l, boolean z) {
        super(zzhr, str, l);
    }
}
