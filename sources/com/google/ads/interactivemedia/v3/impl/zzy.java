package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import androidx.webkit.ProxyConfig;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdsLoader;
import com.google.ads.interactivemedia.v3.api.AdsManagerLoadedEvent;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.BaseRequest;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.zzbj;
import com.google.ads.interactivemedia.v3.impl.data.zzbn;
import com.google.ads.interactivemedia.v3.impl.data.zzbu;
import com.google.ads.interactivemedia.v3.impl.data.zzcn;
import com.google.ads.interactivemedia.v3.internal.zzahg;
import com.google.ads.interactivemedia.v3.internal.zzahh;
import com.google.ads.interactivemedia.v3.internal.zzahj;
import com.google.ads.interactivemedia.v3.internal.zzel;
import com.google.ads.interactivemedia.v3.internal.zzes;
import com.google.ads.interactivemedia.v3.internal.zzet;
import com.google.ads.interactivemedia.v3.internal.zzew;
import com.google.ads.interactivemedia.v3.internal.zzez;
import com.google.ads.interactivemedia.v3.internal.zzfd;
import com.google.ads.interactivemedia.v3.internal.zzfj;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.ads.interactivemedia.v3.internal.zzfl;
import com.google.ads.interactivemedia.v3.internal.zzfp;
import com.google.ads.interactivemedia.v3.internal.zzgd;
import com.google.ads.interactivemedia.v3.internal.zzgg;
import com.google.ads.interactivemedia.v3.internal.zzpr;
import com.google.ads.interactivemedia.v3.internal.zzqf;
import com.google.ads.interactivemedia.v3.internal.zzqm;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzrp;
import com.google.ads.interactivemedia.v3.internal.zzuk;
import com.google.ads.interactivemedia.v3.internal.zzuu;
import com.google.ads.interactivemedia.v3.internal.zzuv;
import com.google.ads.interactivemedia.v3.internal.zzvb;
import com.google.ads.interactivemedia.v3.internal.zzvd;
import com.google.android.gms.tasks.CancellationTokenSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzy implements AdsLoader {
    /* access modifiers changed from: private */
    public final Context zza;
    private final zzvd zzb;
    /* access modifiers changed from: private */
    public final zzba zzc;
    private final zzv zzd = new zzv(this);
    /* access modifiers changed from: private */
    public final zzat zze;
    private final List zzf = Collections.synchronizedList(new ArrayList(1));
    /* access modifiers changed from: private */
    public final Map zzg = new HashMap();
    /* access modifiers changed from: private */
    public final Map zzh = new HashMap();
    /* access modifiers changed from: private */
    public final zzbt zzi;
    private final ImaSdkSettings zzj;
    /* access modifiers changed from: private */
    public final BaseDisplayContainer zzk;
    private final zzfp zzl;
    private final zzgd zzm;
    private final zzgg zzn;
    /* access modifiers changed from: private */
    public final zzuv zzo;
    /* access modifiers changed from: private */
    public final zzfd zzp;
    private final TestingConfiguration zzq;
    private zzet zzr;

    private zzy(zzba zzba, Context context, ImaSdkSettings imaSdkSettings, BaseDisplayContainer baseDisplayContainer, zzfj zzfj, ExecutorService executorService, zzfl zzfl) {
        this.zzc = zzba;
        this.zza = context;
        this.zzj = imaSdkSettings == null ? ImaSdkFactory.getInstance().createImaSdkSettings() : imaSdkSettings;
        this.zzk = baseDisplayContainer;
        zzuv zza2 = zzvb.zza(executorService);
        this.zzo = zza2;
        TestingConfiguration testingConfig = imaSdkSettings.getTestingConfig();
        this.zzq = testingConfig;
        this.zzi = new zzbt(zzba, zzba.zzb(), zzfl);
        zzfd zzfd = new zzfd(zzba, zzfj);
        this.zzp = zzfd;
        this.zze = new zzat(zzfd);
        baseDisplayContainer.claim();
        this.zzl = new zzfp(context, zza2, zzfd, testingConfig);
        this.zzm = new zzgd(context, zza2, zzfd);
        this.zzn = new zzgg(context, zza2, testingConfig, zzfd);
        this.zzb = zzvd.zzs();
    }

    public static zzy zzc(zzba zzba, Context context, ImaSdkSettings imaSdkSettings, BaseDisplayContainer baseDisplayContainer, zzfj zzfj, ExecutorService executorService, zzfl zzfl) {
        zzy zzy = new zzy(zzba, context, imaSdkSettings, baseDisplayContainer, zzfj, executorService, zzfl);
        zzba.zze().zzo(new zzo(zzy), executorService);
        return zzy;
    }

    static /* bridge */ /* synthetic */ void zzn(zzy zzy, AdsManagerLoadedEvent adsManagerLoadedEvent) {
        for (AdsLoader.AdsLoadedListener onAdsManagerLoaded : zzy.zzf) {
            onAdsManagerLoaded.onAdsManagerLoaded(adsManagerLoadedEvent);
        }
    }

    static /* synthetic */ void zzo(zzy zzy) {
        List<String> list;
        zzba zzba = zzy.zzc;
        long currentTimeMillis = System.currentTimeMillis();
        zzbu zzbu = (zzbu) zzs(zzba.zze());
        if (zzbu == null) {
            zzy.zze.zzc(new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "core component initialization failed")));
            return;
        }
        zzy.zzp.zzh(zzbu.enableInstrumentation);
        Integer num = zzbu.espAdapterTimeoutMs;
        if (!(num == null || (list = zzbu.espAdapters) == null)) {
            zzy.zzm.zzc(list, num);
            zzy.zzm.zzb();
        }
        zzy.zzl.zzb(zzbu.platformSignalCollectorTimeoutMs);
        zzet zzet = new zzet(zzy.zzc, zzy.zza, zzy.zzo, zzes.zza(zzbu), zzy.zzp);
        zzy.zzr = zzet;
        zzet.zze();
        zzy.zzp.zzb().zze(zzfd.zza(currentTimeMillis, System.currentTimeMillis()));
        zzy.zzb.zzc(zzbu);
    }

    static final Object zzs(Future future) {
        if (future == null) {
            return null;
        }
        try {
            return zzuk.zzd(future);
        } catch (Exception e) {
            zzfk.zzb("Error during initialization", e);
            return null;
        } catch (Throwable th) {
            zzfk.zzb("Error during initialization", new Exception(th));
            return null;
        }
    }

    static final Object zzt(Future future, Object obj) {
        return zzqf.zzg(zzs(future)).zzc(obj);
    }

    private final zzw zzu() {
        ActivityInfo activityInfo;
        PackageManager packageManager = this.zza.getPackageManager();
        ResolveInfo resolveActivity = packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.ads.interactivemedia.v3")), 65536);
        if (resolveActivity == null || (activityInfo = resolveActivity.activityInfo) == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(activityInfo.packageName, 0);
            if (packageInfo != null) {
                return zzw.create(packageInfo.versionCode, activityInfo.packageName);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private final zzuu zzw(BaseRequest baseRequest, zzbu zzbu, String str) {
        zzbu zzbu2 = zzbu;
        String str2 = str;
        long currentTimeMillis = System.currentTimeMillis();
        zzahj zzc2 = this.zzp.zzc(str2);
        zzuu zza2 = this.zzo.zza(new zzq(this, new zzez(this.zza, new zzew(zzbu2), this.zzp, this.zzj.getFeatureFlags(), this.zzq), baseRequest, str2));
        zza2.zzo(new zzr(zzc2, currentTimeMillis), this.zzo);
        zzuu zza3 = this.zzo.zza(new zzs(this, zzbu2));
        zza3.zzo(new zzt(zzc2, currentTimeMillis), this.zzo);
        zzgd zzgd = this.zzm;
        Objects.requireNonNull(zzgd);
        zzuu zza4 = this.zzo.zza(new zzu(zzgd));
        zza4.zzo(new zzj(zzc2, currentTimeMillis), this.zzo);
        zzuu zza5 = zzpr.zza(this.zzl.zza(), (CancellationTokenSource) null);
        zza5.zzo(new zzk(zzc2, currentTimeMillis), this.zzo);
        return zzuk.zza(zza2, zza3, zza4, zza5).zza(new zzl(this, baseRequest, zza4, zzc2, currentTimeMillis, zza2, zza3, zza5), this.zzo);
    }

    private final String zzx() {
        TestingConfiguration testingConfiguration = this.zzq;
        if (testingConfiguration == null || !testingConfiguration.ignoreStrictModeFalsePositives()) {
            return UUID.randomUUID().toString();
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitDiskReads().build());
        String uuid = UUID.randomUUID().toString();
        StrictMode.setThreadPolicy(threadPolicy);
        return uuid;
    }

    private final String zzy() {
        return String.format("android%s:%s:%s", new Object[]{Build.VERSION.RELEASE, "3.35.1", this.zza.getPackageName()});
    }

    public final void addAdErrorListener(AdErrorEvent.AdErrorListener adErrorListener) {
        this.zze.zza(adErrorListener);
    }

    public final void addAdsLoadedListener(AdsLoader.AdsLoadedListener adsLoadedListener) {
        this.zzf.add(adsLoadedListener);
    }

    public final void contentComplete() {
        this.zzc.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.adsLoader, JavaScriptMessage.MsgType.contentComplete, ProxyConfig.MATCH_ALL_SCHEMES, (Object) null));
    }

    public final ImaSdkSettings getSettings() {
        return this.zzj;
    }

    public final void release() {
        this.zzk.destroy();
        zzba zzba = this.zzc;
        if (zzba != null) {
            zzba.zzk();
        }
        this.zzg.clear();
        this.zzf.clear();
        this.zze.zzb();
        this.zzh.clear();
        this.zzp.zzd();
    }

    public final void removeAdErrorListener(AdErrorEvent.AdErrorListener adErrorListener) {
        this.zze.zzd(adErrorListener);
    }

    public final void removeAdsLoadedListener(AdsLoader.AdsLoadedListener adsLoadedListener) {
        this.zzf.remove(adsLoadedListener);
    }

    public final void requestAds(AdsRequest adsRequest) {
        String zzx = zzx();
        long currentTimeMillis = System.currentTimeMillis();
        adsRequest.zzc(currentTimeMillis);
        this.zzb.zzo(new zzn(this, adsRequest, zzx), this.zzo);
        this.zzp.zzc(zzx).zzc(zzfd.zza(currentTimeMillis, System.currentTimeMillis()));
    }

    public final String requestStream(StreamRequest streamRequest) {
        String zzx = zzx();
        long currentTimeMillis = System.currentTimeMillis();
        streamRequest.zzc(currentTimeMillis);
        this.zzb.zzo(new zzi(this, streamRequest, zzx), this.zzo);
        this.zzp.zzc(zzx).zzc(zzfd.zza(currentTimeMillis, System.currentTimeMillis()));
        return zzx;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzbn zzg(zzez zzez, BaseRequest baseRequest, String str) throws Exception {
        return zzez.zza(baseRequest, this.zzr, str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzj(zzbu zzbu) throws Exception {
        return this.zzn.zza(zzbu.msParameterTimeoutMs);
    }

    /* access modifiers changed from: package-private */
    public final String zzk(StreamRequest streamRequest, String str) {
        zzd zzd2;
        zzbu zzbu = (zzbu) zzs(this.zzb);
        if (zzbu == null) {
            zzd2 = new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Error initializing the SDK."), new Object());
        } else if (streamRequest == null) {
            zzd2 = new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "StreamRequest cannot be null."));
        } else {
            BaseDisplayContainer baseDisplayContainer = this.zzk;
            if (!(baseDisplayContainer instanceof StreamDisplayContainer)) {
                zzd2 = new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "AdsLoader must be constructed with StreamDisplayContainer."));
            } else {
                zzd2 = ((StreamDisplayContainer) baseDisplayContainer).getVideoStreamPlayer() == null ? new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "Stream requests must specify a player.")) : null;
            }
        }
        if (zzd2 != null) {
            this.zze.zzc(zzd2);
            return str;
        }
        this.zzh.put(str, streamRequest);
        this.zzc.zzi(str, JavaScriptMessage.MsgChannel.adsLoader, this.zzd);
        zzuu zzw = zzw(streamRequest, zzbu, str);
        zzw.zzo(new zzm(this, zzw, streamRequest, str), this.zzo);
        return str;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzp(zzuu zzuu, AdDisplayContainer adDisplayContainer, AdsRequest adsRequest, String str) {
        String str2 = str;
        zzx zzx = (zzx) zzt(zzuu, zzx.zza());
        zzbn zzbn = (zzbn) zzx.zzb().zzd();
        String zze2 = zzx.zze();
        zzrm zzc2 = zzx.zzc();
        zzrp zzd2 = zzx.zzd();
        zzet zzet = this.zzr;
        zzet.getClass();
        zzet zzet2 = zzet;
        Map zza2 = zzet.zza();
        String zzy = zzy();
        zzcn zzv = zzv();
        ImaSdkSettings imaSdkSettings = this.zzj;
        zzw zzu = zzu();
        Context context = this.zza;
        TestingConfiguration testingConfiguration = this.zzq;
        JavaScriptMessage javaScriptMessage = new JavaScriptMessage(JavaScriptMessage.MsgChannel.adsLoader, JavaScriptMessage.MsgType.requestAds, str2, zzbj.create(adsRequest, zzy, zza2, zzc2, zzd2, "android:0", zzv, imaSdkSettings, zzu, zzel.zzd(context, testingConfiguration), zzel.zzc(context, testingConfiguration), zze2, zzbn, adDisplayContainer));
        this.zzc.zzj(zzbn);
        this.zzc.zzn(javaScriptMessage);
        zzahg zzc3 = zzahh.zzc();
        zzc3.zza(System.currentTimeMillis());
        if (adsRequest.zzb().zze()) {
            zzc3.zzb(((Long) adsRequest.zzb().zzb()).longValue());
        }
        this.zzp.zzc(str2).zzf(zzc3);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzq(zzuu zzuu, StreamRequest streamRequest, String str) {
        String str2 = str;
        zzx zzx = (zzx) zzt(zzuu, zzx.zza());
        zzbn zzbn = (zzbn) zzx.zzb().zzd();
        String zze2 = zzx.zze();
        zzrm zzc2 = zzx.zzc();
        zzrp zzd2 = zzx.zzd();
        zzet zzet = this.zzr;
        zzet.getClass();
        zzet zzet2 = zzet;
        Map zza2 = zzet.zza();
        String zzy = zzy();
        zzcn zzv = zzv();
        ImaSdkSettings imaSdkSettings = this.zzj;
        zzw zzu = zzu();
        Context context = this.zza;
        TestingConfiguration testingConfiguration = this.zzq;
        boolean zzd3 = zzel.zzd(context, testingConfiguration);
        boolean zzc3 = zzel.zzc(context, testingConfiguration);
        JavaScriptMessage javaScriptMessage = new JavaScriptMessage(JavaScriptMessage.MsgChannel.adsLoader, JavaScriptMessage.MsgType.requestStream, str2, zzbj.createFromStreamRequest(streamRequest, zzy, zza2, zzc2, zzd2, "android:0", zzv, imaSdkSettings, zzu, zzd3, zzc3, zze2, zzbn, (StreamDisplayContainer) this.zzk));
        this.zzc.zzj(zzbn);
        this.zzc.zzn(javaScriptMessage);
        zzahg zzc4 = zzahh.zzc();
        zzc4.zza(System.currentTimeMillis());
        if (streamRequest.zzb().zze()) {
            zzc4.zzb(((Long) streamRequest.zzb().zzb()).longValue());
        }
        this.zzp.zzc(str2).zzf(zzc4);
    }

    /* access modifiers changed from: package-private */
    public final void zzr(AdsRequest adsRequest, String str) {
        zzd zzd2;
        zzbu zzbu = (zzbu) zzs(this.zzb);
        if (zzbu == null) {
            zzd2 = new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Error initializing the SDK."), new Object());
        } else if (adsRequest == null) {
            zzd2 = new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "AdsRequest cannot be null."));
        } else {
            BaseDisplayContainer baseDisplayContainer = this.zzk;
            if (!(baseDisplayContainer instanceof AdDisplayContainer)) {
                zzd2 = new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "AdsLoader must be constructed with AdDisplayContainer."));
            } else if (baseDisplayContainer.getAdContainer() == null) {
                zzd2 = new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "Ad display container must have a UI container."));
            } else {
                zzd2 = (!zzqm.zzc(adsRequest.getAdTagUrl()) || !zzqm.zzc(adsRequest.getAdsResponse())) ? null : new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INVALID_ARGUMENTS, "Either ad tag url or ads response must non-null and non empty."));
            }
        }
        if (zzd2 != null) {
            this.zze.zzc(zzd2);
            return;
        }
        AdDisplayContainer adDisplayContainer = (AdDisplayContainer) this.zzk;
        if (adDisplayContainer.getPlayer() == null) {
            this.zze.zzc(new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.ADS_PLAYER_NOT_PROVIDED, "VideoAdPlayer must be set on AdDisplayContainer before requesting ads."), new Object()));
            return;
        }
        this.zzg.put(str, adsRequest);
        this.zzc.zzi(str, JavaScriptMessage.MsgChannel.adsLoader, this.zzd);
        zzuu zzw = zzw(adsRequest, zzbu, str);
        zzw.zzo(new zzp(this, zzw, adDisplayContainer, adsRequest, str), this.zzo);
    }

    private final zzcn zzv() {
        Integer num;
        boolean z;
        NetworkCapabilities networkCapabilities;
        if (this.zza.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            zzfk.zzd("Host application doesn't have ACCESS_NETWORK_STATE permission");
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.zza.getSystemService("connectivity");
            if (!(connectivityManager == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork())) == null)) {
                num = Integer.valueOf(networkCapabilities.getLinkDownstreamBandwidthKbps());
                Map<String, String> featureFlags = this.zzj.getFeatureFlags();
                z = false;
                if (!(featureFlags == null || featureFlags.get("NATIVE_UI") == null)) {
                    z = true;
                }
                if (num == null || z) {
                    return zzcn.create(num, z);
                }
                return null;
            }
        }
        num = null;
        Map<String, String> featureFlags2 = this.zzj.getFeatureFlags();
        z = false;
        z = true;
        if (num == null) {
        }
        return zzcn.create(num, z);
    }
}
