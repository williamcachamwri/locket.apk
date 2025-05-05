package com.google.ads.interactivemedia.pal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.pal.zzagb;
import com.google.android.gms.internal.pal.zzid;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzaj implements zzagb {
    zzaj() {
    }

    public static final Handler zza() {
        return new zzid(Looper.getMainLooper());
    }
}
