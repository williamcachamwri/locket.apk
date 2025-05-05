package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zztz extends zztq {
    /* access modifiers changed from: private */
    @CheckForNull
    public zzty zza;

    zztz(zzri zzri, boolean z, Executor executor, Callable callable) {
        super(zzri, false, false);
        this.zza = new zztx(this, callable, executor);
        zzt();
    }

    /* access modifiers changed from: protected */
    public final void zzp() {
        zzty zzty = this.zza;
        if (zzty != null) {
            zzty.zzh();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzs() {
        zzty zzty = this.zza;
        if (zzty != null) {
            zzty.zzf();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzv(int i) {
        super.zzv(i);
        if (i == 1) {
            this.zza = null;
        }
    }
}
