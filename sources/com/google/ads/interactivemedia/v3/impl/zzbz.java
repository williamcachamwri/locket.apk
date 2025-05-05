package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.ads.interactivemedia.v3.api.signals.SecureSignals;
import com.google.ads.interactivemedia.v3.internal.zzex;
import com.google.ads.interactivemedia.v3.internal.zzey;
import com.google.ads.interactivemedia.v3.internal.zzqf;
import com.google.ads.interactivemedia.v3.internal.zzwm;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbz implements StreamRequest {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private boolean zzf;
    private String zzg;
    private String zzh;
    private String zzi;
    private String zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private Map zzn;
    private String zzo;
    private String zzp;
    private String zzq;
    private String zzr;
    private StreamRequest.StreamFormat zzs;
    private String zzt;
    private Map zzu;
    private zzqf zzv = zzqf.zzf();
    @zzwm(zza = "useQAStreamBaseUrl")
    private Boolean zzw;
    private SecureSignals zzx;
    private transient Object zzy;

    public final Map<String, String> getAdTagParameters() {
        return this.zzn;
    }

    public final String getAdTagUrl() {
        return this.zza;
    }

    public final String getApiKey() {
        return this.zzc;
    }

    public final String getAssetKey() {
        return this.zzb;
    }

    public final String getAuthToken() {
        return this.zzq;
    }

    public final String getContentSourceId() {
        return this.zzd;
    }

    public final String getContentSourceUrl() {
        return this.zze;
    }

    public final String getContentUrl() {
        return this.zzp;
    }

    public final String getCustomAssetKey() {
        return this.zzi;
    }

    public final boolean getEnableNonce() {
        return this.zzf;
    }

    public final StreamRequest.StreamFormat getFormat() {
        return this.zzs;
    }

    public final String getLiveStreamEventId() {
        return this.zzj;
    }

    public final String getManifestSuffix() {
        return this.zzo;
    }

    public final String getNetworkCode() {
        return this.zzh;
    }

    public final String getOAuthToken() {
        return this.zzm;
    }

    public final String getProjectNumber() {
        return this.zzl;
    }

    public final String getRegion() {
        return this.zzk;
    }

    public final SecureSignals getSecureSignals() {
        return this.zzx;
    }

    public final String getStreamActivityMonitorId() {
        return this.zzr;
    }

    public final Boolean getUseQAStreamBaseUrl() {
        return this.zzw;
    }

    public final Object getUserRequestContext() {
        return this.zzy;
    }

    public final String getVideoId() {
        return this.zzg;
    }

    public final Map<String, Object> getVideoStitcherSessionOptions() {
        return this.zzu;
    }

    public final String getVodConfigId() {
        return this.zzt;
    }

    public final void setAdTagParameters(Map<String, String> map) {
        this.zzn = map;
    }

    public final void setAuthToken(String str) {
        this.zzq = str;
    }

    public final void setContentUrl(String str) {
        this.zzp = str;
    }

    public final void setEnableNonce(boolean z) {
        this.zzf = z;
    }

    public final void setFormat(StreamRequest.StreamFormat streamFormat) {
        this.zzs = streamFormat;
    }

    public final void setManifestSuffix(String str) {
        this.zzo = str;
    }

    public final void setSecureSignals(SecureSignals secureSignals) {
        this.zzx = secureSignals;
    }

    public final void setStreamActivityMonitorId(String str) {
        this.zzr = str;
    }

    public final void setUseQAStreamBaseUrl(Boolean bool) {
        this.zzw = bool;
    }

    public final void setUserRequestContext(Object obj) {
        this.zzy = obj;
    }

    public final void setVideoStitcherSessionOptions(Map<String, Object> map) {
        this.zzu = map;
    }

    public final zzex zza() {
        return new zzey();
    }

    public final zzqf zzb() {
        return this.zzv;
    }

    public final void zzc(long j) {
        this.zzv = zzqf.zzh(Long.valueOf(j));
    }

    public final void zzd(String str) {
        this.zza = str;
    }

    public final void zze(String str) {
        this.zzc = str;
    }

    public final void zzf(String str) {
        this.zzb = str;
    }

    public final void zzg(String str) {
        this.zzd = str;
    }

    public final void zzh(String str) {
        this.zze = str;
    }

    public final void zzi(String str) {
        this.zzi = str;
    }

    public final void zzj(String str) {
        this.zzj = str;
    }

    public final void zzk(String str) {
        this.zzh = str;
    }

    public final void zzl(String str) {
        this.zzm = str;
    }

    public final void zzm(String str) {
        this.zzl = str;
    }

    public final void zzn(String str) {
        this.zzk = str;
    }

    public final void zzo(String str) {
        this.zzg = str;
    }

    public final void zzp(String str) {
        this.zzt = str;
    }
}
