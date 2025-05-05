package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.impl.AdsRequestImpl;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzrp;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzw implements zzbi {
    private zzrp<String, String> adTagParameters;
    private String adTagUrl;
    private String adsResponse;
    private String apiKey;
    private String assetKey;
    private String authToken;
    private zzrp<String, String> companionSlots;
    private zzrp<String, String> consentSettings;
    private Float contentDuration;
    private zzrm<String> contentKeywords;
    private String contentSourceId;
    private String contentSourceUrl;
    private String contentTitle;
    private String contentUrl;
    private String customAssetKey;
    private Boolean enableNonce;
    private String env;
    private String format;
    private zzbn identifierInfo;
    private Boolean isAndroidTvAdsFramework;
    private Boolean isTv;
    private Integer linearAdSlotHeight;
    private Integer linearAdSlotWidth;
    private String liveStreamEventId;
    private Float liveStreamPrefetchSeconds;
    private com.google.ads.interactivemedia.v3.impl.zzw marketAppInfo;
    private String msParameter;
    private String network;
    private String networkCode;
    private String oAuthToken;
    private Boolean omidAdSessionsOnStartedOnly;
    private zzrp<String, String> platformSignals;
    private String projectNumber;
    private String region;
    private int rubidiumApiVersion;
    private zzrm<zzcf> secureSignals;
    private byte set$0;
    private ImaSdkSettings settings;
    private String streamActivityMonitorId;
    private Boolean supportsExternalNavigation;
    private Boolean supportsIconClickFallback;
    private Boolean supportsNativeNetworking;
    private Boolean supportsResizing;
    private Boolean useQAStreamBaseUrl;
    private Boolean usesCustomVideoPlayback;
    private Float vastLoadTimeout;
    private AdsRequestImpl.ContinuousPlayState videoContinuousPlay;
    private zzcn videoEnvironment;
    private String videoId;
    private AdsRequestImpl.AutoPlayState videoPlayActivation;
    private AdsRequestImpl.MutePlayState videoPlayMuted;
    private zzrp<String, Object> videoStitcherSessionOptions;
    private String vodConfigId;

    zzw() {
    }

    public zzbi adTagParameters(Map<String, String> map) {
        this.adTagParameters = map == null ? null : zzrp.zzc(map);
        return this;
    }

    public zzbi adTagUrl(String str) {
        this.adTagUrl = str;
        return this;
    }

    public zzbi adsResponse(String str) {
        this.adsResponse = str;
        return this;
    }

    public zzbi apiKey(String str) {
        this.apiKey = str;
        return this;
    }

    public zzbi assetKey(String str) {
        this.assetKey = str;
        return this;
    }

    public zzbi authToken(String str) {
        this.authToken = str;
        return this;
    }

    public zzbj build() {
        if (this.set$0 == 1) {
            return new zzy(this.adTagParameters, this.adTagUrl, this.adsResponse, this.apiKey, this.assetKey, this.authToken, this.companionSlots, this.contentDuration, this.contentKeywords, this.contentSourceUrl, this.contentTitle, this.contentUrl, this.contentSourceId, this.consentSettings, this.customAssetKey, this.enableNonce, this.env, this.secureSignals, this.format, this.identifierInfo, this.isTv, this.isAndroidTvAdsFramework, this.linearAdSlotWidth, this.linearAdSlotHeight, this.liveStreamEventId, this.liveStreamPrefetchSeconds, this.marketAppInfo, this.msParameter, this.network, this.videoEnvironment, this.networkCode, this.oAuthToken, this.omidAdSessionsOnStartedOnly, this.platformSignals, this.projectNumber, this.region, this.settings, this.supportsExternalNavigation, this.supportsIconClickFallback, this.supportsNativeNetworking, this.streamActivityMonitorId, this.supportsResizing, this.useQAStreamBaseUrl, this.usesCustomVideoPlayback, this.vastLoadTimeout, this.videoId, this.videoPlayActivation, this.videoContinuousPlay, this.videoPlayMuted, this.videoStitcherSessionOptions, this.vodConfigId, this.rubidiumApiVersion);
        }
        throw new IllegalStateException("Missing required properties: rubidiumApiVersion");
    }

    public zzbi companionSlots(Map<String, String> map) {
        this.companionSlots = map == null ? null : zzrp.zzc(map);
        return this;
    }

    public zzbi consentSettings(Map<String, String> map) {
        this.consentSettings = map == null ? null : zzrp.zzc(map);
        return this;
    }

    public zzbi contentDuration(Float f) {
        this.contentDuration = f;
        return this;
    }

    public zzbi contentKeywords(List<String> list) {
        this.contentKeywords = list == null ? null : zzrm.zzk(list);
        return this;
    }

    public zzbi contentSourceId(String str) {
        this.contentSourceId = str;
        return this;
    }

    public zzbi contentSourceUrl(String str) {
        this.contentSourceUrl = str;
        return this;
    }

    public zzbi contentTitle(String str) {
        this.contentTitle = str;
        return this;
    }

    public zzbi contentUrl(String str) {
        this.contentUrl = str;
        return this;
    }

    public zzbi customAssetKey(String str) {
        this.customAssetKey = str;
        return this;
    }

    public zzbi enableNonce(Boolean bool) {
        this.enableNonce = bool;
        return this;
    }

    public zzbi env(String str) {
        this.env = str;
        return this;
    }

    public zzbi format(String str) {
        this.format = str;
        return this;
    }

    public zzbi identifierInfo(zzbn zzbn) {
        this.identifierInfo = zzbn;
        return this;
    }

    public zzbi isAndroidTvAdsFramework(Boolean bool) {
        this.isAndroidTvAdsFramework = bool;
        return this;
    }

    public zzbi isTv(Boolean bool) {
        this.isTv = bool;
        return this;
    }

    public zzbi linearAdSlotHeight(Integer num) {
        this.linearAdSlotHeight = num;
        return this;
    }

    public zzbi linearAdSlotWidth(Integer num) {
        this.linearAdSlotWidth = num;
        return this;
    }

    public zzbi liveStreamEventId(String str) {
        this.liveStreamEventId = str;
        return this;
    }

    public zzbi liveStreamPrefetchSeconds(Float f) {
        this.liveStreamPrefetchSeconds = f;
        return this;
    }

    public zzbi marketAppInfo(com.google.ads.interactivemedia.v3.impl.zzw zzw) {
        this.marketAppInfo = zzw;
        return this;
    }

    public zzbi msParameter(String str) {
        this.msParameter = str;
        return this;
    }

    public zzbi network(String str) {
        this.network = str;
        return this;
    }

    public zzbi networkCode(String str) {
        this.networkCode = str;
        return this;
    }

    public zzbi oAuthToken(String str) {
        this.oAuthToken = str;
        return this;
    }

    public zzbi omidAdSessionsOnStartedOnly(Boolean bool) {
        this.omidAdSessionsOnStartedOnly = bool;
        return this;
    }

    public zzbi platformSignals(Map<String, String> map) {
        this.platformSignals = map == null ? null : zzrp.zzc(map);
        return this;
    }

    public zzbi projectNumber(String str) {
        this.projectNumber = str;
        return this;
    }

    public zzbi region(String str) {
        this.region = str;
        return this;
    }

    public zzbi rubidiumApiVersion(int i) {
        this.rubidiumApiVersion = i;
        this.set$0 = (byte) (this.set$0 | 1);
        return this;
    }

    public zzbi secureSignals(List<zzcf> list) {
        this.secureSignals = list == null ? null : zzrm.zzk(list);
        return this;
    }

    public zzbi settings(ImaSdkSettings imaSdkSettings) {
        this.settings = imaSdkSettings;
        return this;
    }

    public zzbi streamActivityMonitorId(String str) {
        this.streamActivityMonitorId = str;
        return this;
    }

    public zzbi supportsExternalNavigation(Boolean bool) {
        this.supportsExternalNavigation = bool;
        return this;
    }

    public zzbi supportsIconClickFallback(Boolean bool) {
        this.supportsIconClickFallback = bool;
        return this;
    }

    public zzbi supportsNativeNetworking(Boolean bool) {
        this.supportsNativeNetworking = bool;
        return this;
    }

    public zzbi supportsResizing(Boolean bool) {
        this.supportsResizing = bool;
        return this;
    }

    public zzbi useQAStreamBaseUrl(Boolean bool) {
        this.useQAStreamBaseUrl = bool;
        return this;
    }

    public zzbi usesCustomVideoPlayback(Boolean bool) {
        this.usesCustomVideoPlayback = bool;
        return this;
    }

    public zzbi vastLoadTimeout(Float f) {
        this.vastLoadTimeout = f;
        return this;
    }

    public zzbi videoContinuousPlay(AdsRequestImpl.ContinuousPlayState continuousPlayState) {
        this.videoContinuousPlay = continuousPlayState;
        return this;
    }

    public zzbi videoEnvironment(zzcn zzcn) {
        this.videoEnvironment = zzcn;
        return this;
    }

    public zzbi videoId(String str) {
        this.videoId = str;
        return this;
    }

    public zzbi videoPlayActivation(AdsRequestImpl.AutoPlayState autoPlayState) {
        this.videoPlayActivation = autoPlayState;
        return this;
    }

    public zzbi videoPlayMuted(AdsRequestImpl.MutePlayState mutePlayState) {
        this.videoPlayMuted = mutePlayState;
        return this;
    }

    public zzbi videoStitcherSessionOptions(Map<String, Object> map) {
        this.videoStitcherSessionOptions = map == null ? null : zzrp.zzc(map);
        return this;
    }

    public zzbi vodConfigId(String str) {
        this.vodConfigId = str;
        return this;
    }
}
