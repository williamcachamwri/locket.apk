package com.google.ads.interactivemedia.v3.internal;

import android.util.Base64;
import androidx.webkit.ProxyConfig;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzbp;
import com.google.ads.interactivemedia.v3.impl.data.zzbq;
import com.google.ads.interactivemedia.v3.impl.data.zzbr;
import com.google.ads.interactivemedia.v3.impl.zzbi;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzfd {
    private final Queue zza = new ConcurrentLinkedQueue();
    private final zzbi zzb;
    private final zzfj zzc;
    private int zzd = 1;

    public zzfd(zzbi zzbi, zzfj zzfj) {
        this.zzb = zzbi;
        this.zzc = zzfj;
    }

    public static zzahh zza(long j, long j2) {
        zzahg zzc2 = zzahh.zzc();
        zzc2.zzb(j);
        zzc2.zza(j2);
        return (zzahh) zzc2.zzal();
    }

    private final void zzi(zzbr zzbr) {
        JavaScriptMessage javaScriptMessage = new JavaScriptMessage(JavaScriptMessage.MsgChannel.adsLoader, JavaScriptMessage.MsgType.nativeInstrumentation, ProxyConfig.MATCH_ALL_SCHEMES, zzbr);
        int i = this.zzd;
        int i2 = i - 1;
        if (i == 0) {
            throw null;
        } else if (i2 != 0) {
            if (i2 == 1) {
                this.zzb.zzn(javaScriptMessage);
            }
        } else if (this.zza.size() > 6) {
            this.zzd = 3;
        } else {
            this.zza.add(javaScriptMessage);
        }
    }

    public final zzahj zzb() {
        return this.zzc.zzb();
    }

    public final zzahj zzc(String str) {
        return this.zzc.zzc(str);
    }

    public final void zzd() {
        this.zzc.zzd();
    }

    public final void zze(String str) {
        zzqf zza2 = this.zzc.zza(str);
        if (zza2.zze()) {
            zzi(zzbr.create(System.currentTimeMillis(), zzbp.LATENCY_MEASUREMENT_TRACKER, zzbq.FLUSH_LATENCY_MEASUREMENT, Base64.encodeToString(((zzahe) zza2.zzb()).zzav(), 0)));
            this.zzc.zze(str);
        }
    }

    public final void zzf(AdErrorEvent adErrorEvent) {
        zzi(zzbr.create(System.currentTimeMillis(), adErrorEvent));
    }

    public final void zzg(zzbp zzbp, zzbq zzbq, Exception exc) {
        zzi(zzbr.create(System.currentTimeMillis(), zzbp, zzbq, (Throwable) exc));
    }

    public final void zzh(boolean z) {
        if (z) {
            this.zzd = 2;
            JavaScriptMessage javaScriptMessage = (JavaScriptMessage) this.zza.poll();
            while (javaScriptMessage != null) {
                this.zzb.zzn(javaScriptMessage);
                javaScriptMessage = (JavaScriptMessage) this.zza.poll();
            }
            return;
        }
        this.zzd = 3;
        this.zza.clear();
    }
}
