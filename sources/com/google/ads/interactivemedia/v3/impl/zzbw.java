package com.google.ads.interactivemedia.v3.impl;

import android.os.Handler;
import android.os.Looper;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
abstract class zzbw {
    private final Handler zza = new Handler(Looper.getMainLooper());
    private final List zzb = new ArrayList(1);

    zzbw(long j) {
    }

    /* access modifiers changed from: private */
    public final void zzg() {
        List<zzbv> list = this.zzb;
        VideoProgressUpdate zza2 = zza();
        for (zzbv zzw : list) {
            zzw.zzw(zza2);
        }
        this.zza.postDelayed(new zzbu(this), 200);
    }

    /* access modifiers changed from: package-private */
    public abstract VideoProgressUpdate zza();

    /* access modifiers changed from: package-private */
    public final void zzc(zzbv zzbv) {
        this.zzb.add(zzbv);
    }

    /* access modifiers changed from: package-private */
    public final void zzd(zzbv zzbv) {
        this.zzb.remove(zzbv);
    }

    /* access modifiers changed from: package-private */
    public final void zze() {
        this.zza.removeCallbacksAndMessages((Object) null);
        zzg();
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zza.removeCallbacksAndMessages((Object) null);
    }
}
