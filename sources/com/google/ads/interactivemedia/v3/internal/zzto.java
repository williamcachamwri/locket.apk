package com.google.ads.interactivemedia.v3.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.annotation.CheckForNull;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
abstract class zzto extends zzub implements Runnable {
    public static final /* synthetic */ int zze = 0;
    @CheckForNull
    zzuu zza;
    @CheckForNull
    Object zzd;

    zzto(zzuu zzuu, Object obj) {
        zzuu zzuu2 = zzuu;
        this.zza = zzuu;
        this.zzd = obj;
    }

    public final void run() {
        zzuu zzuu = this.zza;
        Object obj = this.zzd;
        boolean z = true;
        boolean isCancelled = isCancelled() | (zzuu == null);
        if (obj != null) {
            z = false;
        }
        if (!isCancelled && !z) {
            this.zza = null;
            if (zzuu.isCancelled()) {
                zzq(zzuu);
                return;
            }
            try {
                try {
                    Object zzs = zzs(obj, zzuk.zzd(zzuu));
                    this.zzd = null;
                    zzt(zzs);
                } catch (Throwable th) {
                    this.zzd = null;
                    throw th;
                }
            } catch (CancellationException unused) {
                cancel(false);
            } catch (ExecutionException e) {
                zzd(e.getCause());
            } catch (Exception e2) {
                zzd(e2);
            } catch (Error e3) {
                zzd(e3);
            }
        }
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final String zza() {
        String str;
        zzuu zzuu = this.zza;
        Object obj = this.zzd;
        String zza2 = super.zza();
        if (zzuu != null) {
            str = "inputFuture=[" + zzuu.toString() + "], ";
        } else {
            str = "";
        }
        if (obj != null) {
            return str + "function=[" + obj.toString() + "]";
        } else if (zza2 != null) {
            return str.concat(zza2);
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        zzuu zzuu = this.zza;
        if ((zzuu != null) && isCancelled()) {
            zzuu.cancel(zzr());
        }
        this.zza = null;
        this.zzd = null;
    }

    /* access modifiers changed from: package-private */
    public abstract Object zzs(Object obj, Object obj2) throws Exception;

    /* access modifiers changed from: package-private */
    public abstract void zzt(Object obj);
}
