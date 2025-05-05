package com.google.ads.interactivemedia.pal;

import android.os.Handler;
import com.google.android.gms.internal.pal.zzagc;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzav {
    /* access modifiers changed from: private */
    public final Handler zza;
    /* access modifiers changed from: private */
    public final zzagc zzb;
    private Runnable zzc;

    public zzav(Handler handler, zzagc zzagc) {
        this.zza = handler;
        this.zzb = zzagc;
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        zzd();
        super.finalize();
    }

    public final void zzc(Runnable runnable) {
        if (this.zzc == null) {
            zzau zzau = new zzau(this, runnable);
            this.zzc = zzau;
            this.zza.postDelayed(zzau, this.zzb.zzd());
        }
    }

    public final void zzd() {
        Runnable runnable = this.zzc;
        if (runnable != null) {
            this.zza.removeCallbacks(runnable);
            this.zzc = null;
        }
    }
}
