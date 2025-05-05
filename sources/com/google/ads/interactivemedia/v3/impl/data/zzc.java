package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdPodInfo;
import com.google.ads.interactivemedia.v3.api.CompanionAd;
import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.api.UniversalAdId;
import com.google.ads.interactivemedia.v3.api.zza;
import com.google.ads.interactivemedia.v3.internal.zzaho;
import com.google.ads.interactivemedia.v3.internal.zzahp;
import com.google.ads.interactivemedia.v3.internal.zzahr;
import com.google.ads.interactivemedia.v3.internal.zzahs;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzc implements Ad {
    private String adId;
    @zzahs
    @zzahp
    private zzd adPodInfo = new zzd();
    private String adSystem;
    private zza adUi;
    @zzahs
    @zzahp
    private String[] adWrapperCreativeIds = null;
    @zzahs
    @zzahp
    private String[] adWrapperIds = null;
    @zzahs
    @zzahp
    private String[] adWrapperSystems = null;
    private String advertiserName;
    private String clickThroughUrl;
    @zzahs
    @zzahp
    private zzbc[] companions = null;
    private String contentType;
    private String creativeAdId;
    private String creativeId;
    private String dealId;
    private String description;
    private boolean disableUi;
    private double duration;
    private int height;
    private boolean linear = false;
    private double skipTimeOffset = -1.0d;
    private boolean skippable = false;
    private String surveyUrl;
    private String title;
    private String traffickingParameters;
    @zzahs
    @zzahp
    private Set<UiElement> uiElements;
    private String universalAdIdRegistry;
    private String universalAdIdValue;
    @zzahs
    @zzahp
    private zzcm[] universalAdIds = null;
    private int vastMediaBitrate;
    private int vastMediaHeight;
    private int vastMediaWidth;
    private int width;

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return zzaho.zzf(this, obj, false, (Class) null, false, "vastMediaBitrate", "vastMediaHeight", "vastMediaWidth");
    }

    public String getAdId() {
        return this.adId;
    }

    public AdPodInfo getAdPodInfo() {
        return this.adPodInfo;
    }

    public String getAdSystem() {
        return this.adSystem;
    }

    public zza getAdUi() {
        return this.adUi;
    }

    public String[] getAdWrapperCreativeIds() {
        return this.adWrapperCreativeIds;
    }

    public String[] getAdWrapperIds() {
        return this.adWrapperIds;
    }

    public String[] getAdWrapperSystems() {
        return this.adWrapperSystems;
    }

    public String getAdvertiserName() {
        return this.advertiserName;
    }

    public String getClickThruUrl() {
        return this.clickThroughUrl;
    }

    public List<CompanionAd> getCompanionAds() {
        zzbc[] zzbcArr = this.companions;
        if (zzbcArr != null) {
            return Arrays.asList(zzbcArr);
        }
        return Arrays.asList(new CompanionAd[0]);
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getCreativeAdId() {
        return this.creativeAdId;
    }

    public String getCreativeId() {
        return this.creativeId;
    }

    public String getDealId() {
        return this.dealId;
    }

    public String getDescription() {
        return this.description;
    }

    public double getDuration() {
        return this.duration;
    }

    public int getHeight() {
        return this.height;
    }

    public double getSkipTimeOffset() {
        return this.skipTimeOffset;
    }

    public String getSurveyUrl() {
        return this.surveyUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTraffickingParameters() {
        return this.traffickingParameters;
    }

    public Set<UiElement> getUiElements() {
        return this.uiElements;
    }

    public String getUniversalAdIdRegistry() {
        return this.universalAdIdRegistry;
    }

    public String getUniversalAdIdValue() {
        return this.universalAdIdValue;
    }

    public UniversalAdId[] getUniversalAdIds() {
        return this.universalAdIds;
    }

    public int getVastMediaBitrate() {
        return this.vastMediaBitrate;
    }

    public int getVastMediaHeight() {
        return this.vastMediaHeight;
    }

    public int getVastMediaWidth() {
        return this.vastMediaWidth;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return zzahr.zza(this, new String[0]);
    }

    public boolean isLinear() {
        return this.linear;
    }

    public boolean isSkippable() {
        return this.skippable;
    }

    public boolean isUiDisabled() {
        return this.disableUi;
    }

    public void setAdId(String str) {
        this.adId = str;
    }

    public void setAdPodInfo(zzd zzd) {
        this.adPodInfo = zzd;
    }

    public void setAdSystem(String str) {
        this.adSystem = str;
    }

    public void setAdUi(zza zza) {
        this.adUi = zza;
    }

    public void setAdWrapperCreativeIds(String[] strArr) {
        this.adWrapperCreativeIds = strArr;
    }

    public void setAdWrapperIds(String[] strArr) {
        this.adWrapperIds = strArr;
    }

    public void setAdWrapperSystems(String[] strArr) {
        this.adWrapperSystems = strArr;
    }

    public void setAdvertiserName(String str) {
        this.advertiserName = str;
    }

    public void setClickThruUrl(String str) {
        this.clickThroughUrl = str;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public void setCreativeAdId(String str) {
        this.creativeAdId = str;
    }

    public void setCreativeId(String str) {
        this.creativeId = str;
    }

    public void setDealId(String str) {
        this.dealId = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDuration(double d) {
        this.duration = d;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setLinear(boolean z) {
        this.linear = z;
    }

    public void setSkipTimeOffset(double d) {
        this.skipTimeOffset = d;
    }

    public void setSkippable(boolean z) {
        this.skippable = z;
    }

    public void setSurveyUrl(String str) {
        this.surveyUrl = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTraffickingParameters(String str) {
        this.traffickingParameters = str;
    }

    public void setUiDisabled(boolean z) {
        this.disableUi = z;
    }

    public void setUiElements(Set<UiElement> set) {
        this.uiElements = set;
    }

    public void setUniversalAdIdRegistry(String str) {
        this.universalAdIdRegistry = str;
    }

    public void setUniversalAdIdValue(String str) {
        this.universalAdIdValue = str;
    }

    public void setUniversalAdIds(zzcm[] zzcmArr) {
        this.universalAdIds = zzcmArr;
    }

    public void setVastMediaBitrate(int i) {
        this.vastMediaBitrate = i;
    }

    public void setVastMediaHeight(int i) {
        this.vastMediaHeight = i;
    }

    public void setVastMediaWidth(int i) {
        this.vastMediaWidth = i;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public String toString() {
        String str = this.adId;
        String str2 = this.creativeId;
        String str3 = this.creativeAdId;
        String str4 = this.universalAdIdValue;
        String str5 = this.universalAdIdRegistry;
        String str6 = this.title;
        String str7 = this.description;
        String str8 = this.contentType;
        String arrays = Arrays.toString(this.adWrapperIds);
        String arrays2 = Arrays.toString(this.adWrapperSystems);
        String arrays3 = Arrays.toString(this.adWrapperCreativeIds);
        String str9 = this.adSystem;
        String str10 = this.advertiserName;
        String str11 = this.surveyUrl;
        String str12 = this.dealId;
        boolean z = this.linear;
        boolean z2 = this.skippable;
        int i = this.width;
        int i2 = this.height;
        int i3 = this.vastMediaHeight;
        int i4 = this.vastMediaWidth;
        int i5 = this.vastMediaBitrate;
        String str13 = this.traffickingParameters;
        String str14 = str11;
        String str15 = this.clickThroughUrl;
        double d = this.duration;
        String valueOf = String.valueOf(this.adPodInfo);
        String valueOf2 = String.valueOf(this.uiElements);
        return "Ad [adId=" + str + ", creativeId=" + str2 + ", creativeAdId=" + str3 + ", universalAdIdValue=" + str4 + ", universalAdIdRegistry=" + str5 + ", title=" + str6 + ", description=" + str7 + ", contentType=" + str8 + ", adWrapperIds=" + arrays + ", adWrapperSystems=" + arrays2 + ", adWrapperCreativeIds=" + arrays3 + ", adSystem=" + str9 + ", advertiserName=" + str10 + ", surveyUrl=" + str14 + ", dealId=" + str12 + ", linear=" + z + ", skippable=" + z2 + ", width=" + i + ", height=" + i2 + ", vastMediaHeight=" + i3 + ", vastMediaWidth=" + i4 + ", vastMediaBitrate=" + i5 + ", traffickingParameters=" + str13 + ", clickThroughUrl=" + str15 + ", duration=" + d + ", adPodInfo=" + valueOf + ", uiElements=" + valueOf2 + ", disableUi=" + this.disableUi + ", skipTimeOffset=" + this.skipTimeOffset + "]";
    }
}
