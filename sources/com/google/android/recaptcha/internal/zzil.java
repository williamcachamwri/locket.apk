package com.google.android.recaptcha.internal;

import android.webkit.JavascriptInterface;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlinx.coroutines.CompletableDeferred;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzil {
    final /* synthetic */ zzjc zza;
    private Long zzb;
    private final zzjj zzc = zzjj.zzb();

    public zzil(zzjc zzjc) {
        this.zza = zzjc;
    }

    private final void zzb() {
        if (this.zzb == null) {
            this.zzc.zzf();
            this.zzb = Long.valueOf(this.zzc.zza(TimeUnit.MILLISECONDS));
        }
    }

    public final Long zza() {
        return this.zzb;
    }

    @JavascriptInterface
    public final void zzlce(String str) {
        zzjc zzjc = this.zza;
        if (zzjc.zzq().zzb == null) {
            zzep zzo = zzjc.zzp;
            if (zzo != null) {
                zzo.zza();
            }
            this.zza.zzp = null;
        }
        zzb();
        zzre zzl = zzre.zzl(zzbv.zza(str));
        zzty zzi = zztz.zzi();
        zzi.zzf(zzl);
        this.zza.zzj.zze((zztz) zzi.zzk());
    }

    @JavascriptInterface
    public final void zzlsm(String str) {
        zzb();
        zzty zzi = zztz.zzi();
        zzi.zzq(zzrt.zzi(zzbv.zza(str)));
        this.zza.zzj.zze((zztz) zzi.zzk());
    }

    @JavascriptInterface
    public final void zzoid(String str) {
        zzb();
        zztu zzg = zztu.zzg(zzbv.zza(str));
        zzg.zzi().name();
        if (zzg.zzi() == zztx.JS_CODE_SUCCESS) {
            this.zza.zzA().hashCode();
            if (!this.zza.zzA().complete(Unit.INSTANCE)) {
                this.zza.zzA().hashCode();
                return;
            }
            return;
        }
        zzg.zzi().name();
        int i = zzbf.zza;
        zzbf zza2 = zzbe.zza(zzg.zzi());
        this.zza.zzA().hashCode();
        this.zza.zzA().completeExceptionally(zza2);
    }

    @JavascriptInterface
    public final void zzrp(String str) {
        zzb();
        zzfq zzfq = this.zza.zzb;
        if (zzfq == null) {
            zzfq = null;
        }
        zzfq.zza(str);
    }

    @JavascriptInterface
    public final void zzscd(String str) {
        zzb();
        zzsk zzi = zzsk.zzi(zzbv.zza(str));
        zzi.toString();
        CompletableDeferred completableDeferred = (CompletableDeferred) this.zza.zze.remove(zzi.zzk());
        if (completableDeferred != null) {
            completableDeferred.complete(zzi);
        }
    }
}
