package com.google.android.gms.internal.measurement;

import io.sentry.android.core.SentryLogcatAdapter;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzhq extends zzhj<Boolean> {
    /* access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzgi.zzc.matcher(str).matches()) {
                return true;
            }
            if (zzgi.zzd.matcher(str).matches()) {
                return false;
            }
        }
        String zzb = super.zzb();
        SentryLogcatAdapter.e("PhenotypeFlag", "Invalid boolean value for " + zzb + ": " + String.valueOf(obj));
        return null;
    }

    zzhq(zzhr zzhr, String str, Boolean bool, boolean z) {
        super(zzhr, str, bool);
    }
}
