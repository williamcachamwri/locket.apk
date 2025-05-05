package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.webkit.WebView;
import com.google.ads.interactivemedia.omid.library.adsession.zzc;
import com.google.ads.interactivemedia.omid.library.adsession.zze;
import com.google.ads.interactivemedia.omid.library.adsession.zzk;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzdj extends zzdf {
    /* access modifiers changed from: private */
    public WebView zza;
    private Long zzb = null;
    private final Map zzc;

    public zzdj(String str, Map map, String str2) {
        super(str);
        this.zzc = map;
    }

    public final void zzc() {
        long j;
        super.zzc();
        if (this.zzb == null) {
            j = 4000;
        } else {
            j = TimeUnit.MILLISECONDS.convert(System.nanoTime() - this.zzb.longValue(), TimeUnit.NANOSECONDS);
        }
        new Handler().postDelayed(new zzdi(this), Math.max(4000 - j, 2000));
        this.zza = null;
    }

    public final void zzi(zze zze, zzc zzc2) {
        JSONObject jSONObject = new JSONObject();
        Map zzh = zzc2.zzh();
        Iterator it = zzh.keySet().iterator();
        if (!it.hasNext()) {
            zzj(zze, zzc2, jSONObject);
        } else {
            zzk zzk = (zzk) zzh.get((String) it.next());
            throw null;
        }
    }

    public final void zzn() {
        WebView webView = new WebView(zzcw.zzb().zza());
        this.zza = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        this.zza.getSettings().setAllowContentAccess(false);
        this.zza.getSettings().setAllowFileAccess(false);
        this.zza.setWebViewClient(new zzdh(this));
        zzm(this.zza);
        zzcy.zzj(this.zza, (String) null);
        Iterator it = this.zzc.keySet().iterator();
        if (!it.hasNext()) {
            this.zzb = Long.valueOf(System.nanoTime());
            return;
        }
        zzk zzk = (zzk) this.zzc.get((String) it.next());
        throw null;
    }
}
