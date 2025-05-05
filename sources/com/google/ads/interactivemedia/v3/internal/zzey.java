package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzey implements zzex {
    public final boolean zza(zzew zzew, Context context, boolean z, boolean z2) {
        String packageName = context.getApplicationContext().getPackageName();
        List list = zzew.zzd;
        return (list == null || !list.contains(packageName)) && !z && z2;
    }
}
