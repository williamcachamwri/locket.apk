package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.internal.zzfd;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzat {
    private final List zza;
    private final zzfd zzb;

    public zzat() {
        this.zza = Collections.synchronizedList(new ArrayList(1));
        this.zzb = null;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        return "ErrorListenerSupport [errorListeners=" + valueOf + "]";
    }

    public final void zza(AdErrorEvent.AdErrorListener adErrorListener) {
        this.zza.add(adErrorListener);
    }

    public final void zzb() {
        this.zza.clear();
    }

    public final void zzc(AdErrorEvent adErrorEvent) {
        for (AdErrorEvent.AdErrorListener onAdError : this.zza) {
            onAdError.onAdError(adErrorEvent);
        }
        zzfd zzfd = this.zzb;
        if (zzfd != null) {
            zzfd.zzf(adErrorEvent);
        }
    }

    public final void zzd(AdErrorEvent.AdErrorListener adErrorListener) {
        this.zza.remove(adErrorListener);
    }

    public zzat(zzfd zzfd) {
        this.zza = Collections.synchronizedList(new ArrayList(1));
        this.zzb = zzfd;
    }
}
