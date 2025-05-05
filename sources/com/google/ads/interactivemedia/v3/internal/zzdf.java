package com.google.ads.interactivemedia.v3.internal;

import android.os.Build;
import android.webkit.WebView;
import com.amplitude.api.Constants;
import com.google.ads.interactivemedia.omid.library.adsession.zzb;
import com.google.ads.interactivemedia.omid.library.adsession.zzc;
import com.google.ads.interactivemedia.omid.library.adsession.zze;
import com.google.ads.interactivemedia.omid.library.adsession.zzk;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.ProfilingTraceData;
import io.sentry.protocol.App;
import io.sentry.protocol.OperatingSystem;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public class zzdf {
    private final String zza;
    private zzeh zzb = new zzeh((WebView) null);
    private long zzc;
    private int zzd;

    public zzdf(String str) {
        zzb();
        this.zza = str;
    }

    public final WebView zza() {
        return (WebView) this.zzb.get();
    }

    public final void zzb() {
        this.zzc = System.nanoTime();
        this.zzd = 1;
    }

    public void zzc() {
        this.zzb.clear();
    }

    public final void zzd(String str, long j) {
        if (j >= this.zzc && this.zzd != 3) {
            this.zzd = 3;
            zzcy.zza().zzg(zza(), this.zza, str);
        }
    }

    public final void zze() {
        zzcy.zza().zzc(zza(), this.zza);
    }

    public final void zzf(zzb zzb2) {
        zzcy.zza().zzd(zza(), this.zza, zzb2.zzb());
    }

    public final void zzg(Date date) {
        if (date != null) {
            JSONObject jSONObject = new JSONObject();
            zzdl.zze(jSONObject, "timestamp", Long.valueOf(date.getTime()));
            zzcy.zza().zzf(zza(), jSONObject);
        }
    }

    public final void zzh(String str, long j) {
        if (j >= this.zzc) {
            this.zzd = 2;
            zzcy.zza().zzg(zza(), this.zza, str);
        }
    }

    public void zzi(zze zze, zzc zzc2) {
        zzj(zze, zzc2, (JSONObject) null);
    }

    /* access modifiers changed from: protected */
    public final void zzj(zze zze, zzc zzc2, JSONObject jSONObject) {
        String zzi = zze.zzi();
        JSONObject jSONObject2 = new JSONObject();
        zzdl.zze(jSONObject2, "environment", App.TYPE);
        zzdl.zze(jSONObject2, "adSessionType", zzc2.zzc());
        JSONObject jSONObject3 = new JSONObject();
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        zzdl.zze(jSONObject3, "deviceType", str + "; " + str2);
        zzdl.zze(jSONObject3, "osVersion", Integer.toString(Build.VERSION.SDK_INT));
        zzdl.zze(jSONObject3, OperatingSystem.TYPE, Constants.PLATFORM);
        zzdl.zze(jSONObject2, "deviceInfo", jSONObject3);
        zzdl.zze(jSONObject2, "deviceCategory", zzdk.zza().toString());
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("clid");
        jSONArray.put("vlid");
        zzdl.zze(jSONObject2, "supports", jSONArray);
        JSONObject jSONObject4 = new JSONObject();
        zzdl.zze(jSONObject4, "partnerName", zzc2.zzd().zzb());
        zzdl.zze(jSONObject4, "partnerVersion", zzc2.zzd().zzc());
        zzdl.zze(jSONObject2, "omidNativeInfo", jSONObject4);
        JSONObject jSONObject5 = new JSONObject();
        zzdl.zze(jSONObject5, "libraryVersion", "1.4.10-google_20240110");
        zzdl.zze(jSONObject5, RemoteConfigConstants.RequestFieldKey.APP_ID, zzcw.zzb().zza().getApplicationContext().getPackageName());
        zzdl.zze(jSONObject2, App.TYPE, jSONObject5);
        if (zzc2.zze() != null) {
            zzdl.zze(jSONObject2, "contentUrl", zzc2.zze());
        }
        zzdl.zze(jSONObject2, "customReferenceData", zzc2.zzf());
        JSONObject jSONObject6 = new JSONObject();
        Iterator it = zzc2.zzg().iterator();
        if (!it.hasNext()) {
            zzcy.zza().zzi(zza(), zzi, jSONObject2, jSONObject6, jSONObject);
        } else {
            zzk zzk = (zzk) it.next();
            throw null;
        }
    }

    public final void zzk(boolean z) {
        if (this.zzb.get() != null) {
            zzcy.zza().zzh(zza(), this.zza, true != z ? ProfilingTraceData.TRUNCATION_REASON_BACKGROUNDED : "foregrounded");
        }
    }

    public final void zzl(float f) {
        zzcy.zza().zze(zza(), this.zza, f);
    }

    /* access modifiers changed from: package-private */
    public final void zzm(WebView webView) {
        this.zzb = new zzeh(webView);
    }

    public void zzn() {
    }
}
