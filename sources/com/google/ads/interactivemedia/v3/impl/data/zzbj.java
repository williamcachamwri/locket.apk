package com.google.ads.interactivemedia.v3.impl.data;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.api.player.ResizablePlayer;
import com.google.ads.interactivemedia.v3.impl.AdsRequestImpl;
import com.google.ads.interactivemedia.v3.impl.zzaf;
import com.google.ads.interactivemedia.v3.impl.zzbx;
import com.google.ads.interactivemedia.v3.impl.zzc;
import com.google.ads.interactivemedia.v3.impl.zzw;
import com.google.ads.interactivemedia.v3.internal.zzel;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzro;
import com.google.ads.interactivemedia.v3.internal.zzrp;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzbj {
    private static final boolean SUPPORTS_NATIVE_NETWORKING = true;

    public static zzbi builder() {
        return new zzw();
    }

    public static zzbj create(AdsRequest adsRequest, String str, Map<String, String> map, List<zzcf> list, Map<String, String> map2, String str2, zzcn zzcn, ImaSdkSettings imaSdkSettings, zzw zzw, boolean z, boolean z2, String str3, zzbn zzbn, AdDisplayContainer adDisplayContainer) {
        String adTagUrl = adsRequest.getAdTagUrl();
        String adsResponse = adsRequest.getAdsResponse();
        AdsRequestImpl adsRequestImpl = (AdsRequestImpl) adsRequest;
        AdsRequestImpl.AutoPlayState zzd = adsRequestImpl.zzd();
        AdsRequestImpl.MutePlayState zzf = adsRequestImpl.zzf();
        AdsRequestImpl.ContinuousPlayState zze = adsRequestImpl.zze();
        Float zzg = adsRequestImpl.zzg();
        List zzk = adsRequestImpl.zzk();
        String zzj = adsRequestImpl.zzj();
        String contentUrl = adsRequest.getContentUrl();
        Float zzi = adsRequestImpl.zzi();
        Float zzh = adsRequestImpl.zzh();
        Map<String, String> companionSlots = getCompanionSlots((zzc) adDisplayContainer);
        ViewGroup adContainer = adDisplayContainer.getAdContainer();
        zzbi builder = builder();
        builder.adTagUrl(adTagUrl);
        builder.adsResponse(adsResponse);
        builder.companionSlots(companionSlots);
        builder.consentSettings(map);
        builder.contentDuration(zzg);
        builder.contentKeywords(zzk);
        builder.contentTitle(zzj);
        builder.contentUrl(contentUrl);
        String str4 = str;
        builder.env(str);
        builder.secureSignals(list);
        builder.identifierInfo(zzbn);
        Boolean valueOf = Boolean.valueOf(z);
        builder.isTv(valueOf);
        builder.isAndroidTvAdsFramework(Boolean.valueOf(z2));
        builder.linearAdSlotWidth(Integer.valueOf(adContainer.getWidth()));
        builder.linearAdSlotHeight(Integer.valueOf(adContainer.getHeight()));
        builder.liveStreamPrefetchSeconds(zzh);
        builder.marketAppInfo(zzw);
        builder.msParameter(str3);
        builder.network(str2);
        builder.videoEnvironment(zzcn);
        builder.omidAdSessionsOnStartedOnly(true);
        builder.platformSignals(map2);
        builder.settings(imaSdkSettings);
        builder.supportsExternalNavigation(Boolean.valueOf(!z));
        builder.supportsIconClickFallback(valueOf);
        builder.supportsNativeNetworking(true);
        builder.supportsResizing(Boolean.valueOf(adDisplayContainer.getPlayer() instanceof ResizablePlayer));
        builder.usesCustomVideoPlayback(true);
        builder.vastLoadTimeout(zzi);
        builder.videoContinuousPlay(zze);
        builder.videoPlayActivation(zzd);
        builder.videoPlayMuted(zzf);
        builder.rubidiumApiVersion(zzel.zza());
        return builder.build();
    }

    public static zzbj createFromStreamRequest(StreamRequest streamRequest, String str, Map<String, String> map, List<zzcf> list, Map<String, String> map2, String str2, zzcn zzcn, ImaSdkSettings imaSdkSettings, zzw zzw, boolean z, boolean z2, String str3, zzbn zzbn, StreamDisplayContainer streamDisplayContainer) {
        Map<String, String> companionSlots = getCompanionSlots((zzbx) streamDisplayContainer);
        ViewGroup adContainer = streamDisplayContainer.getAdContainer();
        StreamRequest.StreamFormat format = streamRequest.getFormat();
        StreamRequest.StreamFormat streamFormat = StreamRequest.StreamFormat.DASH;
        zzbi builder = builder();
        builder.adTagParameters(streamRequest.getAdTagParameters());
        builder.apiKey(streamRequest.getApiKey());
        builder.assetKey(streamRequest.getAssetKey());
        builder.authToken(streamRequest.getAuthToken());
        builder.companionSlots(companionSlots);
        Map<String, String> map3 = map;
        builder.consentSettings(map);
        builder.contentSourceId(streamRequest.getContentSourceId());
        builder.contentUrl(streamRequest.getContentUrl());
        builder.customAssetKey(streamRequest.getCustomAssetKey());
        builder.enableNonce(Boolean.valueOf(streamRequest.getEnableNonce()));
        String str4 = str;
        builder.env(str);
        List<zzcf> list2 = list;
        builder.secureSignals(list);
        builder.format(format == streamFormat ? "dash" : "hls");
        builder.identifierInfo(zzbn);
        Boolean valueOf = Boolean.valueOf(z);
        builder.isTv(valueOf);
        builder.isAndroidTvAdsFramework(Boolean.valueOf(z2));
        builder.linearAdSlotWidth(Integer.valueOf(adContainer.getWidth()));
        builder.linearAdSlotHeight(Integer.valueOf(adContainer.getHeight()));
        builder.liveStreamEventId(streamRequest.getLiveStreamEventId());
        zzw zzw2 = zzw;
        builder.marketAppInfo(zzw);
        builder.msParameter(str3);
        String str5 = str2;
        builder.network(str2);
        zzcn zzcn2 = zzcn;
        builder.videoEnvironment(zzcn);
        builder.networkCode(streamRequest.getNetworkCode());
        builder.contentSourceUrl(streamRequest.getContentSourceUrl());
        builder.adTagUrl(streamRequest.getAdTagUrl());
        builder.oAuthToken(streamRequest.getOAuthToken());
        builder.omidAdSessionsOnStartedOnly(true);
        Map<String, String> map4 = map2;
        builder.platformSignals(map2);
        builder.projectNumber(streamRequest.getProjectNumber());
        builder.region(streamRequest.getRegion());
        ImaSdkSettings imaSdkSettings2 = imaSdkSettings;
        builder.settings(imaSdkSettings);
        builder.streamActivityMonitorId(streamRequest.getStreamActivityMonitorId());
        builder.supportsExternalNavigation(Boolean.valueOf(true ^ z));
        builder.supportsIconClickFallback(valueOf);
        builder.supportsNativeNetworking(true);
        builder.supportsResizing(Boolean.valueOf(streamDisplayContainer.getVideoStreamPlayer() instanceof ResizablePlayer));
        builder.useQAStreamBaseUrl(streamRequest.getUseQAStreamBaseUrl());
        builder.videoId(streamRequest.getVideoId());
        builder.videoStitcherSessionOptions(streamRequest.getVideoStitcherSessionOptions());
        builder.vodConfigId(streamRequest.getVodConfigId());
        builder.rubidiumApiVersion(zzel.zza());
        return builder.build();
    }

    private static Map<String, String> getCompanionSlots(zzaf zzaf) {
        Map zza = zzaf.zza();
        if (zza == null || zza.isEmpty()) {
            return null;
        }
        zzro zzro = new zzro();
        for (String str : zza.keySet()) {
            CompanionAdSlot companionAdSlot = (CompanionAdSlot) zza.get(str);
            int width = companionAdSlot.getWidth();
            int height = companionAdSlot.getHeight();
            zzro.zza(str, width + ViewHierarchyNode.JsonKeys.X + height);
        }
        return zzro.zzc();
    }

    public abstract zzrp<String, String> adTagParameters();

    public abstract String adTagUrl();

    public abstract String adsResponse();

    public abstract String apiKey();

    public abstract String assetKey();

    public abstract String authToken();

    public abstract zzrp<String, String> companionSlots();

    public abstract zzrp<String, String> consentSettings();

    public abstract Float contentDuration();

    public abstract zzrm<String> contentKeywords();

    public abstract String contentSourceId();

    public abstract String contentSourceUrl();

    public abstract String contentTitle();

    public abstract String contentUrl();

    /* access modifiers changed from: package-private */
    public abstract String customAssetKey();

    public abstract Boolean enableNonce();

    public abstract String env();

    public abstract String format();

    public abstract zzbn identifierInfo();

    public abstract Boolean isAndroidTvAdsFramework();

    public abstract Boolean isTv();

    public abstract Integer linearAdSlotHeight();

    public abstract Integer linearAdSlotWidth();

    public abstract String liveStreamEventId();

    public abstract Float liveStreamPrefetchSeconds();

    public abstract zzw marketAppInfo();

    public abstract String msParameter();

    public abstract String network();

    public abstract String networkCode();

    public abstract String oAuthToken();

    public abstract Boolean omidAdSessionsOnStartedOnly();

    public abstract zzrp<String, String> platformSignals();

    public abstract String projectNumber();

    public abstract String region();

    public abstract int rubidiumApiVersion();

    public abstract zzrm<zzcf> secureSignals();

    public abstract ImaSdkSettings settings();

    public abstract String streamActivityMonitorId();

    public abstract Boolean supportsExternalNavigation();

    public abstract Boolean supportsIconClickFallback();

    public abstract Boolean supportsNativeNetworking();

    public abstract Boolean supportsResizing();

    public abstract Boolean useQAStreamBaseUrl();

    public abstract Boolean usesCustomVideoPlayback();

    public abstract Float vastLoadTimeout();

    public abstract AdsRequestImpl.ContinuousPlayState videoContinuousPlay();

    public abstract zzcn videoEnvironment();

    public abstract String videoId();

    public abstract AdsRequestImpl.AutoPlayState videoPlayActivation();

    public abstract AdsRequestImpl.MutePlayState videoPlayMuted();

    public abstract zzrp<String, Object> videoStitcherSessionOptions();

    public abstract String vodConfigId();
}
