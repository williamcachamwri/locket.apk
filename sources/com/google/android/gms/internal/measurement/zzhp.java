package com.google.android.gms.internal.measurement;

import io.sentry.android.core.SentryLogcatAdapter;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzhp extends zzhj<Double> {
    /* access modifiers changed from: private */
    @Nullable
    /* renamed from: zzb */
    public final Double zza(Object obj) {
        if (obj instanceof Double) {
            return (Double) obj;
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if (obj instanceof String) {
            try {
                return Double.valueOf(Double.parseDouble((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String zzb = super.zzb();
        SentryLogcatAdapter.e("PhenotypeFlag", "Invalid double value for " + zzb + ": " + String.valueOf(obj));
        return null;
    }

    zzhp(zzhr zzhr, String str, Double d, boolean z) {
        super(zzhr, str, d);
    }
}
