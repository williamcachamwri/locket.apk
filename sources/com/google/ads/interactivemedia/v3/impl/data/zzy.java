package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.impl.AdsRequestImpl;
import com.google.ads.interactivemedia.v3.impl.zzw;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzrp;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzy extends zzbj {
    private final zzrp<String, String> adTagParameters;
    private final String adTagUrl;
    private final String adsResponse;
    private final String apiKey;
    private final String assetKey;
    private final String authToken;
    private final zzrp<String, String> companionSlots;
    private final zzrp<String, String> consentSettings;
    private final Float contentDuration;
    private final zzrm<String> contentKeywords;
    private final String contentSourceId;
    private final String contentSourceUrl;
    private final String contentTitle;
    private final String contentUrl;
    private final String customAssetKey;
    private final Boolean enableNonce;
    private final String env;
    private final String format;
    private final zzbn identifierInfo;
    private final Boolean isAndroidTvAdsFramework;
    private final Boolean isTv;
    private final Integer linearAdSlotHeight;
    private final Integer linearAdSlotWidth;
    private final String liveStreamEventId;
    private final Float liveStreamPrefetchSeconds;
    private final zzw marketAppInfo;
    private final String msParameter;
    private final String network;
    private final String networkCode;
    private final String oAuthToken;
    private final Boolean omidAdSessionsOnStartedOnly;
    private final zzrp<String, String> platformSignals;
    private final String projectNumber;
    private final String region;
    private final int rubidiumApiVersion;
    private final zzrm<zzcf> secureSignals;
    private final ImaSdkSettings settings;
    private final String streamActivityMonitorId;
    private final Boolean supportsExternalNavigation;
    private final Boolean supportsIconClickFallback;
    private final Boolean supportsNativeNetworking;
    private final Boolean supportsResizing;
    private final Boolean useQAStreamBaseUrl;
    private final Boolean usesCustomVideoPlayback;
    private final Float vastLoadTimeout;
    private final AdsRequestImpl.ContinuousPlayState videoContinuousPlay;
    private final zzcn videoEnvironment;
    private final String videoId;
    private final AdsRequestImpl.AutoPlayState videoPlayActivation;
    private final AdsRequestImpl.MutePlayState videoPlayMuted;
    private final zzrp<String, Object> videoStitcherSessionOptions;
    private final String vodConfigId;

    private zzy(zzrp<String, String> zzrp, String str, String str2, String str3, String str4, String str5, zzrp<String, String> zzrp2, Float f, zzrm<String> zzrm, String str6, String str7, String str8, String str9, zzrp<String, String> zzrp3, String str10, Boolean bool, String str11, zzrm<zzcf> zzrm2, String str12, zzbn zzbn, Boolean bool2, Boolean bool3, Integer num, Integer num2, String str13, Float f2, zzw zzw, String str14, String str15, zzcn zzcn, String str16, String str17, Boolean bool4, zzrp<String, String> zzrp4, String str18, String str19, ImaSdkSettings imaSdkSettings, Boolean bool5, Boolean bool6, Boolean bool7, String str20, Boolean bool8, Boolean bool9, Boolean bool10, Float f3, String str21, AdsRequestImpl.AutoPlayState autoPlayState, AdsRequestImpl.ContinuousPlayState continuousPlayState, AdsRequestImpl.MutePlayState mutePlayState, zzrp<String, Object> zzrp5, String str22, int i) {
        this.adTagParameters = zzrp;
        this.adTagUrl = str;
        this.adsResponse = str2;
        this.apiKey = str3;
        this.assetKey = str4;
        this.authToken = str5;
        this.companionSlots = zzrp2;
        this.contentDuration = f;
        this.contentKeywords = zzrm;
        this.contentSourceUrl = str6;
        this.contentTitle = str7;
        this.contentUrl = str8;
        this.contentSourceId = str9;
        this.consentSettings = zzrp3;
        this.customAssetKey = str10;
        this.enableNonce = bool;
        this.env = str11;
        this.secureSignals = zzrm2;
        this.format = str12;
        this.identifierInfo = zzbn;
        this.isTv = bool2;
        this.isAndroidTvAdsFramework = bool3;
        this.linearAdSlotWidth = num;
        this.linearAdSlotHeight = num2;
        this.liveStreamEventId = str13;
        this.liveStreamPrefetchSeconds = f2;
        this.marketAppInfo = zzw;
        this.msParameter = str14;
        this.network = str15;
        this.videoEnvironment = zzcn;
        this.networkCode = str16;
        this.oAuthToken = str17;
        this.omidAdSessionsOnStartedOnly = bool4;
        this.platformSignals = zzrp4;
        this.projectNumber = str18;
        this.region = str19;
        this.settings = imaSdkSettings;
        this.supportsExternalNavigation = bool5;
        this.supportsIconClickFallback = bool6;
        this.supportsNativeNetworking = bool7;
        this.streamActivityMonitorId = str20;
        this.supportsResizing = bool8;
        this.useQAStreamBaseUrl = bool9;
        this.usesCustomVideoPlayback = bool10;
        this.vastLoadTimeout = f3;
        this.videoId = str21;
        this.videoPlayActivation = autoPlayState;
        this.videoContinuousPlay = continuousPlayState;
        this.videoPlayMuted = mutePlayState;
        this.videoStitcherSessionOptions = zzrp5;
        this.vodConfigId = str22;
        this.rubidiumApiVersion = i;
    }

    public zzrp<String, String> adTagParameters() {
        return this.adTagParameters;
    }

    public String adTagUrl() {
        return this.adTagUrl;
    }

    public String adsResponse() {
        return this.adsResponse;
    }

    public String apiKey() {
        return this.apiKey;
    }

    public String assetKey() {
        return this.assetKey;
    }

    public String authToken() {
        return this.authToken;
    }

    public zzrp<String, String> companionSlots() {
        return this.companionSlots;
    }

    public zzrp<String, String> consentSettings() {
        return this.consentSettings;
    }

    public Float contentDuration() {
        return this.contentDuration;
    }

    public zzrm<String> contentKeywords() {
        return this.contentKeywords;
    }

    public String contentSourceId() {
        return this.contentSourceId;
    }

    public String contentSourceUrl() {
        return this.contentSourceUrl;
    }

    public String contentTitle() {
        return this.contentTitle;
    }

    public String contentUrl() {
        return this.contentUrl;
    }

    /* access modifiers changed from: package-private */
    public String customAssetKey() {
        return this.customAssetKey;
    }

    public Boolean enableNonce() {
        return this.enableNonce;
    }

    public String env() {
        return this.env;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzbj) {
            zzbj zzbj = (zzbj) obj;
            zzrp<String, String> zzrp = this.adTagParameters;
            if (zzrp != null ? zzrp.equals(zzbj.adTagParameters()) : zzbj.adTagParameters() == null) {
                String str = this.adTagUrl;
                if (str != null ? str.equals(zzbj.adTagUrl()) : zzbj.adTagUrl() == null) {
                    String str2 = this.adsResponse;
                    if (str2 != null ? str2.equals(zzbj.adsResponse()) : zzbj.adsResponse() == null) {
                        String str3 = this.apiKey;
                        if (str3 != null ? str3.equals(zzbj.apiKey()) : zzbj.apiKey() == null) {
                            String str4 = this.assetKey;
                            if (str4 != null ? str4.equals(zzbj.assetKey()) : zzbj.assetKey() == null) {
                                String str5 = this.authToken;
                                if (str5 != null ? str5.equals(zzbj.authToken()) : zzbj.authToken() == null) {
                                    zzrp<String, String> zzrp2 = this.companionSlots;
                                    if (zzrp2 != null ? zzrp2.equals(zzbj.companionSlots()) : zzbj.companionSlots() == null) {
                                        Float f = this.contentDuration;
                                        if (f != null ? f.equals(zzbj.contentDuration()) : zzbj.contentDuration() == null) {
                                            zzrm<String> zzrm = this.contentKeywords;
                                            if (zzrm != null ? zzrm.equals(zzbj.contentKeywords()) : zzbj.contentKeywords() == null) {
                                                String str6 = this.contentSourceUrl;
                                                if (str6 != null ? str6.equals(zzbj.contentSourceUrl()) : zzbj.contentSourceUrl() == null) {
                                                    String str7 = this.contentTitle;
                                                    if (str7 != null ? str7.equals(zzbj.contentTitle()) : zzbj.contentTitle() == null) {
                                                        String str8 = this.contentUrl;
                                                        if (str8 != null ? str8.equals(zzbj.contentUrl()) : zzbj.contentUrl() == null) {
                                                            String str9 = this.contentSourceId;
                                                            if (str9 != null ? str9.equals(zzbj.contentSourceId()) : zzbj.contentSourceId() == null) {
                                                                zzrp<String, String> zzrp3 = this.consentSettings;
                                                                if (zzrp3 != null ? zzrp3.equals(zzbj.consentSettings()) : zzbj.consentSettings() == null) {
                                                                    String str10 = this.customAssetKey;
                                                                    if (str10 != null ? str10.equals(zzbj.customAssetKey()) : zzbj.customAssetKey() == null) {
                                                                        Boolean bool = this.enableNonce;
                                                                        if (bool != null ? bool.equals(zzbj.enableNonce()) : zzbj.enableNonce() == null) {
                                                                            String str11 = this.env;
                                                                            if (str11 != null ? str11.equals(zzbj.env()) : zzbj.env() == null) {
                                                                                zzrm<zzcf> zzrm2 = this.secureSignals;
                                                                                if (zzrm2 != null ? zzrm2.equals(zzbj.secureSignals()) : zzbj.secureSignals() == null) {
                                                                                    String str12 = this.format;
                                                                                    if (str12 != null ? str12.equals(zzbj.format()) : zzbj.format() == null) {
                                                                                        zzbn zzbn = this.identifierInfo;
                                                                                        if (zzbn != null ? zzbn.equals(zzbj.identifierInfo()) : zzbj.identifierInfo() == null) {
                                                                                            Boolean bool2 = this.isTv;
                                                                                            if (bool2 != null ? bool2.equals(zzbj.isTv()) : zzbj.isTv() == null) {
                                                                                                Boolean bool3 = this.isAndroidTvAdsFramework;
                                                                                                if (bool3 != null ? bool3.equals(zzbj.isAndroidTvAdsFramework()) : zzbj.isAndroidTvAdsFramework() == null) {
                                                                                                    Integer num = this.linearAdSlotWidth;
                                                                                                    if (num != null ? num.equals(zzbj.linearAdSlotWidth()) : zzbj.linearAdSlotWidth() == null) {
                                                                                                        Integer num2 = this.linearAdSlotHeight;
                                                                                                        if (num2 != null ? num2.equals(zzbj.linearAdSlotHeight()) : zzbj.linearAdSlotHeight() == null) {
                                                                                                            String str13 = this.liveStreamEventId;
                                                                                                            if (str13 != null ? str13.equals(zzbj.liveStreamEventId()) : zzbj.liveStreamEventId() == null) {
                                                                                                                Float f2 = this.liveStreamPrefetchSeconds;
                                                                                                                if (f2 != null ? f2.equals(zzbj.liveStreamPrefetchSeconds()) : zzbj.liveStreamPrefetchSeconds() == null) {
                                                                                                                    zzw zzw = this.marketAppInfo;
                                                                                                                    if (zzw != null ? zzw.equals(zzbj.marketAppInfo()) : zzbj.marketAppInfo() == null) {
                                                                                                                        String str14 = this.msParameter;
                                                                                                                        if (str14 != null ? str14.equals(zzbj.msParameter()) : zzbj.msParameter() == null) {
                                                                                                                            String str15 = this.network;
                                                                                                                            if (str15 != null ? str15.equals(zzbj.network()) : zzbj.network() == null) {
                                                                                                                                zzcn zzcn = this.videoEnvironment;
                                                                                                                                if (zzcn != null ? zzcn.equals(zzbj.videoEnvironment()) : zzbj.videoEnvironment() == null) {
                                                                                                                                    String str16 = this.networkCode;
                                                                                                                                    if (str16 != null ? str16.equals(zzbj.networkCode()) : zzbj.networkCode() == null) {
                                                                                                                                        String str17 = this.oAuthToken;
                                                                                                                                        if (str17 != null ? str17.equals(zzbj.oAuthToken()) : zzbj.oAuthToken() == null) {
                                                                                                                                            Boolean bool4 = this.omidAdSessionsOnStartedOnly;
                                                                                                                                            if (bool4 != null ? bool4.equals(zzbj.omidAdSessionsOnStartedOnly()) : zzbj.omidAdSessionsOnStartedOnly() == null) {
                                                                                                                                                zzrp<String, String> zzrp4 = this.platformSignals;
                                                                                                                                                if (zzrp4 != null ? zzrp4.equals(zzbj.platformSignals()) : zzbj.platformSignals() == null) {
                                                                                                                                                    String str18 = this.projectNumber;
                                                                                                                                                    if (str18 != null ? str18.equals(zzbj.projectNumber()) : zzbj.projectNumber() == null) {
                                                                                                                                                        String str19 = this.region;
                                                                                                                                                        if (str19 != null ? str19.equals(zzbj.region()) : zzbj.region() == null) {
                                                                                                                                                            ImaSdkSettings imaSdkSettings = this.settings;
                                                                                                                                                            if (imaSdkSettings != null ? imaSdkSettings.equals(zzbj.settings()) : zzbj.settings() == null) {
                                                                                                                                                                Boolean bool5 = this.supportsExternalNavigation;
                                                                                                                                                                if (bool5 != null ? bool5.equals(zzbj.supportsExternalNavigation()) : zzbj.supportsExternalNavigation() == null) {
                                                                                                                                                                    Boolean bool6 = this.supportsIconClickFallback;
                                                                                                                                                                    if (bool6 != null ? bool6.equals(zzbj.supportsIconClickFallback()) : zzbj.supportsIconClickFallback() == null) {
                                                                                                                                                                        Boolean bool7 = this.supportsNativeNetworking;
                                                                                                                                                                        if (bool7 != null ? bool7.equals(zzbj.supportsNativeNetworking()) : zzbj.supportsNativeNetworking() == null) {
                                                                                                                                                                            String str20 = this.streamActivityMonitorId;
                                                                                                                                                                            if (str20 != null ? str20.equals(zzbj.streamActivityMonitorId()) : zzbj.streamActivityMonitorId() == null) {
                                                                                                                                                                                Boolean bool8 = this.supportsResizing;
                                                                                                                                                                                if (bool8 != null ? bool8.equals(zzbj.supportsResizing()) : zzbj.supportsResizing() == null) {
                                                                                                                                                                                    Boolean bool9 = this.useQAStreamBaseUrl;
                                                                                                                                                                                    if (bool9 != null ? bool9.equals(zzbj.useQAStreamBaseUrl()) : zzbj.useQAStreamBaseUrl() == null) {
                                                                                                                                                                                        Boolean bool10 = this.usesCustomVideoPlayback;
                                                                                                                                                                                        if (bool10 != null ? bool10.equals(zzbj.usesCustomVideoPlayback()) : zzbj.usesCustomVideoPlayback() == null) {
                                                                                                                                                                                            Float f3 = this.vastLoadTimeout;
                                                                                                                                                                                            if (f3 != null ? f3.equals(zzbj.vastLoadTimeout()) : zzbj.vastLoadTimeout() == null) {
                                                                                                                                                                                                String str21 = this.videoId;
                                                                                                                                                                                                if (str21 != null ? str21.equals(zzbj.videoId()) : zzbj.videoId() == null) {
                                                                                                                                                                                                    AdsRequestImpl.AutoPlayState autoPlayState = this.videoPlayActivation;
                                                                                                                                                                                                    if (autoPlayState != null ? autoPlayState.equals(zzbj.videoPlayActivation()) : zzbj.videoPlayActivation() == null) {
                                                                                                                                                                                                        AdsRequestImpl.ContinuousPlayState continuousPlayState = this.videoContinuousPlay;
                                                                                                                                                                                                        if (continuousPlayState != null ? continuousPlayState.equals(zzbj.videoContinuousPlay()) : zzbj.videoContinuousPlay() == null) {
                                                                                                                                                                                                            AdsRequestImpl.MutePlayState mutePlayState = this.videoPlayMuted;
                                                                                                                                                                                                            if (mutePlayState != null ? mutePlayState.equals(zzbj.videoPlayMuted()) : zzbj.videoPlayMuted() == null) {
                                                                                                                                                                                                                zzrp<String, Object> zzrp5 = this.videoStitcherSessionOptions;
                                                                                                                                                                                                                if (zzrp5 != null ? zzrp5.equals(zzbj.videoStitcherSessionOptions()) : zzbj.videoStitcherSessionOptions() == null) {
                                                                                                                                                                                                                    String str22 = this.vodConfigId;
                                                                                                                                                                                                                    if (str22 != null ? str22.equals(zzbj.vodConfigId()) : zzbj.vodConfigId() == null) {
                                                                                                                                                                                                                        if (this.rubidiumApiVersion == zzbj.rubidiumApiVersion()) {
                                                                                                                                                                                                                            return true;
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public String format() {
        return this.format;
    }

    public zzbn identifierInfo() {
        return this.identifierInfo;
    }

    public Boolean isAndroidTvAdsFramework() {
        return this.isAndroidTvAdsFramework;
    }

    public Boolean isTv() {
        return this.isTv;
    }

    public Integer linearAdSlotHeight() {
        return this.linearAdSlotHeight;
    }

    public Integer linearAdSlotWidth() {
        return this.linearAdSlotWidth;
    }

    public String liveStreamEventId() {
        return this.liveStreamEventId;
    }

    public Float liveStreamPrefetchSeconds() {
        return this.liveStreamPrefetchSeconds;
    }

    public zzw marketAppInfo() {
        return this.marketAppInfo;
    }

    public String msParameter() {
        return this.msParameter;
    }

    public String network() {
        return this.network;
    }

    public String networkCode() {
        return this.networkCode;
    }

    public String oAuthToken() {
        return this.oAuthToken;
    }

    public Boolean omidAdSessionsOnStartedOnly() {
        return this.omidAdSessionsOnStartedOnly;
    }

    public zzrp<String, String> platformSignals() {
        return this.platformSignals;
    }

    public String projectNumber() {
        return this.projectNumber;
    }

    public String region() {
        return this.region;
    }

    public int rubidiumApiVersion() {
        return this.rubidiumApiVersion;
    }

    public zzrm<zzcf> secureSignals() {
        return this.secureSignals;
    }

    public ImaSdkSettings settings() {
        return this.settings;
    }

    public String streamActivityMonitorId() {
        return this.streamActivityMonitorId;
    }

    public Boolean supportsExternalNavigation() {
        return this.supportsExternalNavigation;
    }

    public Boolean supportsIconClickFallback() {
        return this.supportsIconClickFallback;
    }

    public Boolean supportsNativeNetworking() {
        return this.supportsNativeNetworking;
    }

    public Boolean supportsResizing() {
        return this.supportsResizing;
    }

    public String toString() {
        zzrp<String, Object> zzrp = this.videoStitcherSessionOptions;
        AdsRequestImpl.MutePlayState mutePlayState = this.videoPlayMuted;
        AdsRequestImpl.ContinuousPlayState continuousPlayState = this.videoContinuousPlay;
        AdsRequestImpl.AutoPlayState autoPlayState = this.videoPlayActivation;
        ImaSdkSettings imaSdkSettings = this.settings;
        zzrp<String, String> zzrp2 = this.platformSignals;
        zzcn zzcn = this.videoEnvironment;
        zzw zzw = this.marketAppInfo;
        zzbn zzbn = this.identifierInfo;
        zzrm<zzcf> zzrm = this.secureSignals;
        zzrp<String, String> zzrp3 = this.consentSettings;
        zzrm<String> zzrm2 = this.contentKeywords;
        zzrp<String, String> zzrp4 = this.companionSlots;
        String valueOf = String.valueOf(this.adTagParameters);
        String valueOf2 = String.valueOf(zzrp4);
        String valueOf3 = String.valueOf(zzrm2);
        String valueOf4 = String.valueOf(zzrp3);
        String valueOf5 = String.valueOf(zzrm);
        String valueOf6 = String.valueOf(zzbn);
        String valueOf7 = String.valueOf(zzw);
        String valueOf8 = String.valueOf(zzcn);
        String valueOf9 = String.valueOf(zzrp2);
        String valueOf10 = String.valueOf(imaSdkSettings);
        String valueOf11 = String.valueOf(autoPlayState);
        String valueOf12 = String.valueOf(continuousPlayState);
        String valueOf13 = String.valueOf(mutePlayState);
        String valueOf14 = String.valueOf(zzrp);
        StringBuilder sb = new StringBuilder("GsonAdsRequest{adTagParameters=");
        sb.append(valueOf);
        sb.append(", adTagUrl=");
        sb.append(this.adTagUrl);
        sb.append(", adsResponse=");
        sb.append(this.adsResponse);
        sb.append(", apiKey=");
        sb.append(this.apiKey);
        sb.append(", assetKey=");
        sb.append(this.assetKey);
        sb.append(", authToken=");
        sb.append(this.authToken);
        sb.append(", companionSlots=");
        sb.append(valueOf2);
        sb.append(", contentDuration=");
        sb.append(this.contentDuration);
        sb.append(", contentKeywords=");
        sb.append(valueOf3);
        sb.append(", contentSourceUrl=");
        sb.append(this.contentSourceUrl);
        sb.append(", contentTitle=");
        sb.append(this.contentTitle);
        sb.append(", contentUrl=");
        sb.append(this.contentUrl);
        Float f = this.vastLoadTimeout;
        Boolean bool = this.usesCustomVideoPlayback;
        Boolean bool2 = this.useQAStreamBaseUrl;
        Boolean bool3 = this.supportsResizing;
        String str = valueOf13;
        String str2 = this.streamActivityMonitorId;
        String str3 = valueOf12;
        Boolean bool4 = this.supportsNativeNetworking;
        String str4 = valueOf11;
        Boolean bool5 = this.supportsIconClickFallback;
        Float f2 = f;
        Boolean bool6 = this.supportsExternalNavigation;
        Boolean bool7 = bool;
        String str5 = this.region;
        Boolean bool8 = bool2;
        String str6 = this.projectNumber;
        Boolean bool9 = bool3;
        Boolean bool10 = this.omidAdSessionsOnStartedOnly;
        String str7 = str2;
        String str8 = this.oAuthToken;
        Boolean bool11 = bool4;
        String str9 = this.networkCode;
        Boolean bool12 = bool5;
        String str10 = this.network;
        Boolean bool13 = bool6;
        String str11 = this.msParameter;
        String str12 = valueOf10;
        Float f3 = this.liveStreamPrefetchSeconds;
        String str13 = str5;
        String str14 = this.liveStreamEventId;
        String str15 = str6;
        Integer num = this.linearAdSlotHeight;
        String str16 = valueOf9;
        Integer num2 = this.linearAdSlotWidth;
        Boolean bool14 = bool10;
        Boolean bool15 = this.isAndroidTvAdsFramework;
        String str17 = str8;
        Boolean bool16 = this.isTv;
        String str18 = str9;
        String str19 = this.format;
        String str20 = valueOf8;
        String str21 = this.env;
        String str22 = str10;
        Boolean bool17 = this.enableNonce;
        String str23 = str11;
        String str24 = this.customAssetKey;
        String str25 = valueOf7;
        String str26 = this.contentSourceId;
        sb.append(", contentSourceId=");
        sb.append(str26);
        sb.append(", consentSettings=");
        sb.append(valueOf4);
        sb.append(", customAssetKey=");
        sb.append(str24);
        sb.append(", enableNonce=");
        sb.append(bool17);
        sb.append(", env=");
        sb.append(str21);
        sb.append(", secureSignals=");
        sb.append(valueOf5);
        sb.append(", format=");
        sb.append(str19);
        sb.append(", identifierInfo=");
        sb.append(valueOf6);
        sb.append(", isTv=");
        sb.append(bool16);
        sb.append(", isAndroidTvAdsFramework=");
        sb.append(bool15);
        sb.append(", linearAdSlotWidth=");
        sb.append(num2);
        sb.append(", linearAdSlotHeight=");
        sb.append(num);
        sb.append(", liveStreamEventId=");
        sb.append(str14);
        sb.append(", liveStreamPrefetchSeconds=");
        sb.append(f3);
        sb.append(", marketAppInfo=");
        sb.append(str25);
        sb.append(", msParameter=");
        sb.append(str23);
        sb.append(", network=");
        sb.append(str22);
        sb.append(", videoEnvironment=");
        sb.append(str20);
        sb.append(", networkCode=");
        sb.append(str18);
        sb.append(", oAuthToken=");
        sb.append(str17);
        sb.append(", omidAdSessionsOnStartedOnly=");
        sb.append(bool14);
        sb.append(", platformSignals=");
        sb.append(str16);
        sb.append(", projectNumber=");
        sb.append(str15);
        sb.append(", region=");
        sb.append(str13);
        sb.append(", settings=");
        sb.append(str12);
        sb.append(", supportsExternalNavigation=");
        sb.append(bool13);
        sb.append(", supportsIconClickFallback=");
        sb.append(bool12);
        sb.append(", supportsNativeNetworking=");
        sb.append(bool11);
        sb.append(", streamActivityMonitorId=");
        sb.append(str7);
        sb.append(", supportsResizing=");
        sb.append(bool9);
        sb.append(", useQAStreamBaseUrl=");
        sb.append(bool8);
        sb.append(", usesCustomVideoPlayback=");
        sb.append(bool7);
        sb.append(", vastLoadTimeout=");
        sb.append(f2);
        int i = this.rubidiumApiVersion;
        String str27 = this.vodConfigId;
        String str28 = this.videoId;
        sb.append(", videoId=");
        sb.append(str28);
        sb.append(", videoPlayActivation=");
        sb.append(str4);
        sb.append(", videoContinuousPlay=");
        sb.append(str3);
        sb.append(", videoPlayMuted=");
        sb.append(str);
        sb.append(", videoStitcherSessionOptions=");
        sb.append(valueOf14);
        sb.append(", vodConfigId=");
        sb.append(str27);
        sb.append(", rubidiumApiVersion=");
        sb.append(i);
        sb.append("}");
        return sb.toString();
    }

    public Boolean useQAStreamBaseUrl() {
        return this.useQAStreamBaseUrl;
    }

    public Boolean usesCustomVideoPlayback() {
        return this.usesCustomVideoPlayback;
    }

    public Float vastLoadTimeout() {
        return this.vastLoadTimeout;
    }

    public AdsRequestImpl.ContinuousPlayState videoContinuousPlay() {
        return this.videoContinuousPlay;
    }

    public zzcn videoEnvironment() {
        return this.videoEnvironment;
    }

    public String videoId() {
        return this.videoId;
    }

    public AdsRequestImpl.AutoPlayState videoPlayActivation() {
        return this.videoPlayActivation;
    }

    public AdsRequestImpl.MutePlayState videoPlayMuted() {
        return this.videoPlayMuted;
    }

    public zzrp<String, Object> videoStitcherSessionOptions() {
        return this.videoStitcherSessionOptions;
    }

    public String vodConfigId() {
        return this.vodConfigId;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        zzrp<String, String> zzrp = this.adTagParameters;
        int i5 = 0;
        int hashCode = zzrp == null ? 0 : zzrp.hashCode();
        String str = this.adTagUrl;
        int hashCode2 = str == null ? 0 : str.hashCode();
        int i6 = hashCode ^ 1000003;
        String str2 = this.adsResponse;
        int hashCode3 = ((((i6 * 1000003) ^ hashCode2) * 1000003) ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.apiKey;
        int hashCode4 = (hashCode3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.assetKey;
        int hashCode5 = (hashCode4 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.authToken;
        int hashCode6 = (hashCode5 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        zzrp<String, String> zzrp2 = this.companionSlots;
        int hashCode7 = (hashCode6 ^ (zzrp2 == null ? 0 : zzrp2.hashCode())) * 1000003;
        Float f = this.contentDuration;
        int hashCode8 = (hashCode7 ^ (f == null ? 0 : f.hashCode())) * 1000003;
        zzrm<String> zzrm = this.contentKeywords;
        int hashCode9 = (hashCode8 ^ (zzrm == null ? 0 : zzrm.hashCode())) * 1000003;
        String str6 = this.contentSourceUrl;
        int hashCode10 = (hashCode9 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.contentTitle;
        int hashCode11 = (hashCode10 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        String str8 = this.contentUrl;
        int hashCode12 = (hashCode11 ^ (str8 == null ? 0 : str8.hashCode())) * 1000003;
        String str9 = this.contentSourceId;
        int hashCode13 = (hashCode12 ^ (str9 == null ? 0 : str9.hashCode())) * 1000003;
        zzrp<String, String> zzrp3 = this.consentSettings;
        int hashCode14 = (hashCode13 ^ (zzrp3 == null ? 0 : zzrp3.hashCode())) * 1000003;
        String str10 = this.customAssetKey;
        int hashCode15 = (hashCode14 ^ (str10 == null ? 0 : str10.hashCode())) * 1000003;
        Boolean bool = this.enableNonce;
        int hashCode16 = (hashCode15 ^ (bool == null ? 0 : bool.hashCode())) * 1000003;
        String str11 = this.env;
        int hashCode17 = (hashCode16 ^ (str11 == null ? 0 : str11.hashCode())) * 1000003;
        zzrm<zzcf> zzrm2 = this.secureSignals;
        int hashCode18 = (hashCode17 ^ (zzrm2 == null ? 0 : zzrm2.hashCode())) * 1000003;
        String str12 = this.format;
        int hashCode19 = (hashCode18 ^ (str12 == null ? 0 : str12.hashCode())) * 1000003;
        zzbn zzbn = this.identifierInfo;
        int hashCode20 = (hashCode19 ^ (zzbn == null ? 0 : zzbn.hashCode())) * 1000003;
        Boolean bool2 = this.isTv;
        int hashCode21 = (hashCode20 ^ (bool2 == null ? 0 : bool2.hashCode())) * 1000003;
        Boolean bool3 = this.isAndroidTvAdsFramework;
        int hashCode22 = (hashCode21 ^ (bool3 == null ? 0 : bool3.hashCode())) * 1000003;
        Integer num = this.linearAdSlotWidth;
        int hashCode23 = (hashCode22 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Integer num2 = this.linearAdSlotHeight;
        int hashCode24 = (hashCode23 ^ (num2 == null ? 0 : num2.hashCode())) * 1000003;
        String str13 = this.liveStreamEventId;
        int hashCode25 = (hashCode24 ^ (str13 == null ? 0 : str13.hashCode())) * 1000003;
        Float f2 = this.liveStreamPrefetchSeconds;
        int hashCode26 = (hashCode25 ^ (f2 == null ? 0 : f2.hashCode())) * 1000003;
        zzw zzw = this.marketAppInfo;
        int hashCode27 = (hashCode26 ^ (zzw == null ? 0 : zzw.hashCode())) * 1000003;
        String str14 = this.msParameter;
        int hashCode28 = (hashCode27 ^ (str14 == null ? 0 : str14.hashCode())) * 1000003;
        String str15 = this.network;
        int hashCode29 = (hashCode28 ^ (str15 == null ? 0 : str15.hashCode())) * 1000003;
        zzcn zzcn = this.videoEnvironment;
        int hashCode30 = (hashCode29 ^ (zzcn == null ? 0 : zzcn.hashCode())) * 1000003;
        String str16 = this.networkCode;
        int hashCode31 = (hashCode30 ^ (str16 == null ? 0 : str16.hashCode())) * 1000003;
        String str17 = this.oAuthToken;
        int hashCode32 = (hashCode31 ^ (str17 == null ? 0 : str17.hashCode())) * 1000003;
        Boolean bool4 = this.omidAdSessionsOnStartedOnly;
        int hashCode33 = (hashCode32 ^ (bool4 == null ? 0 : bool4.hashCode())) * 1000003;
        zzrp<String, String> zzrp4 = this.platformSignals;
        int hashCode34 = (hashCode33 ^ (zzrp4 == null ? 0 : zzrp4.hashCode())) * 1000003;
        String str18 = this.projectNumber;
        int hashCode35 = (hashCode34 ^ (str18 == null ? 0 : str18.hashCode())) * 1000003;
        String str19 = this.region;
        int hashCode36 = (hashCode35 ^ (str19 == null ? 0 : str19.hashCode())) * 1000003;
        ImaSdkSettings imaSdkSettings = this.settings;
        int hashCode37 = (hashCode36 ^ (imaSdkSettings == null ? 0 : imaSdkSettings.hashCode())) * 1000003;
        Boolean bool5 = this.supportsExternalNavigation;
        int hashCode38 = (hashCode37 ^ (bool5 == null ? 0 : bool5.hashCode())) * 1000003;
        Boolean bool6 = this.supportsIconClickFallback;
        int hashCode39 = (hashCode38 ^ (bool6 == null ? 0 : bool6.hashCode())) * 1000003;
        Boolean bool7 = this.supportsNativeNetworking;
        int hashCode40 = (hashCode39 ^ (bool7 == null ? 0 : bool7.hashCode())) * 1000003;
        String str20 = this.streamActivityMonitorId;
        int hashCode41 = (hashCode40 ^ (str20 == null ? 0 : str20.hashCode())) * 1000003;
        Boolean bool8 = this.supportsResizing;
        int hashCode42 = (hashCode41 ^ (bool8 == null ? 0 : bool8.hashCode())) * 1000003;
        Boolean bool9 = this.useQAStreamBaseUrl;
        int hashCode43 = (hashCode42 ^ (bool9 == null ? 0 : bool9.hashCode())) * 1000003;
        Boolean bool10 = this.usesCustomVideoPlayback;
        int hashCode44 = (hashCode43 ^ (bool10 == null ? 0 : bool10.hashCode())) * 1000003;
        Float f3 = this.vastLoadTimeout;
        int hashCode45 = (hashCode44 ^ (f3 == null ? 0 : f3.hashCode())) * 1000003;
        String str21 = this.videoId;
        int hashCode46 = (hashCode45 ^ (str21 == null ? 0 : str21.hashCode())) * 1000003;
        AdsRequestImpl.AutoPlayState autoPlayState = this.videoPlayActivation;
        if (autoPlayState == null) {
            i = 0;
        } else {
            i = autoPlayState.hashCode();
        }
        int i7 = (hashCode46 ^ i) * 1000003;
        AdsRequestImpl.ContinuousPlayState continuousPlayState = this.videoContinuousPlay;
        if (continuousPlayState == null) {
            i2 = 0;
        } else {
            i2 = continuousPlayState.hashCode();
        }
        int i8 = (i7 ^ i2) * 1000003;
        AdsRequestImpl.MutePlayState mutePlayState = this.videoPlayMuted;
        if (mutePlayState == null) {
            i3 = 0;
        } else {
            i3 = mutePlayState.hashCode();
        }
        int i9 = (i8 ^ i3) * 1000003;
        zzrp<String, Object> zzrp5 = this.videoStitcherSessionOptions;
        if (zzrp5 == null) {
            i4 = 0;
        } else {
            i4 = zzrp5.hashCode();
        }
        int i10 = (i9 ^ i4) * 1000003;
        String str22 = this.vodConfigId;
        if (str22 != null) {
            i5 = str22.hashCode();
        }
        return ((i10 ^ i5) * 1000003) ^ this.rubidiumApiVersion;
    }
}
