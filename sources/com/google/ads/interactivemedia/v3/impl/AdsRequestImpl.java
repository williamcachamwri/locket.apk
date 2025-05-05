package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.AdsRequest;
import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import com.google.ads.interactivemedia.v3.api.signals.SecureSignals;
import com.google.ads.interactivemedia.v3.internal.zzev;
import com.google.ads.interactivemedia.v3.internal.zzex;
import com.google.ads.interactivemedia.v3.internal.zzqf;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class AdsRequestImpl implements AdsRequest {
    private String zza;
    private String zzb;
    private ContentProgressProvider zzc;
    private AutoPlayState zzd = AutoPlayState.UNKNOWN;
    private MutePlayState zze = MutePlayState.UNKNOWN;
    private ContinuousPlayState zzf = ContinuousPlayState.UNKNOWN;
    private Float zzg;
    private List zzh;
    private String zzi;
    private String zzj;
    private Float zzk;
    private Float zzl;
    private SecureSignals zzm;
    private zzqf zzn = zzqf.zzf();
    private transient Object zzo;

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    public enum AutoPlayState {
        AUTO,
        CLICK,
        UNKNOWN
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    public enum ContinuousPlayState {
        OFF,
        ON,
        UNKNOWN
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    public enum MutePlayState {
        MUTED,
        UNKNOWN,
        UNMUTED
    }

    public final String getAdTagUrl() {
        return this.zza;
    }

    public final String getAdsResponse() {
        return this.zzb;
    }

    public final ContentProgressProvider getContentProgressProvider() {
        return this.zzc;
    }

    public final String getContentUrl() {
        return this.zzj;
    }

    public final String getExtraParameter(String str) {
        return null;
    }

    public final Map<String, String> getExtraParameters() {
        return null;
    }

    public final SecureSignals getSecureSignals() {
        return this.zzm;
    }

    public final Object getUserRequestContext() {
        return this.zzo;
    }

    public final void setAdTagUrl(String str) {
        this.zza = str;
    }

    public final void setAdWillAutoPlay(boolean z) {
        this.zzd = z ? AutoPlayState.AUTO : AutoPlayState.CLICK;
    }

    public final void setAdWillPlayMuted(boolean z) {
        this.zze = z ? MutePlayState.MUTED : MutePlayState.UNMUTED;
    }

    public final void setAdsResponse(String str) {
        this.zzb = str;
    }

    public final void setContentDuration(float f) {
        this.zzg = Float.valueOf(f);
    }

    public final void setContentKeywords(List<String> list) {
        this.zzh = list;
    }

    public final void setContentProgressProvider(ContentProgressProvider contentProgressProvider) {
        this.zzc = contentProgressProvider;
    }

    public final void setContentTitle(String str) {
        this.zzi = str;
    }

    public final void setContentUrl(String str) {
        this.zzj = str;
    }

    public final void setContinuousPlayback(boolean z) {
        this.zzf = z ? ContinuousPlayState.ON : ContinuousPlayState.OFF;
    }

    public final void setExtraParameter(String str, String str2) {
    }

    public final void setLiveStreamPrefetchSeconds(float f) {
        this.zzl = Float.valueOf(f);
    }

    public final void setSecureSignals(SecureSignals secureSignals) {
        this.zzm = secureSignals;
    }

    public final void setUserRequestContext(Object obj) {
        this.zzo = obj;
    }

    public final void setVastLoadTimeout(float f) {
        this.zzk = Float.valueOf(f);
    }

    public final zzex zza() {
        return new zzev(this.zza);
    }

    public final zzqf zzb() {
        return this.zzn;
    }

    public final void zzc(long j) {
        this.zzn = zzqf.zzh(Long.valueOf(j));
    }

    public final AutoPlayState zzd() {
        return this.zzd;
    }

    public final ContinuousPlayState zze() {
        return this.zzf;
    }

    public final MutePlayState zzf() {
        return this.zze;
    }

    public final Float zzg() {
        return this.zzg;
    }

    public final Float zzh() {
        return this.zzl;
    }

    public final Float zzi() {
        return this.zzk;
    }

    public final String zzj() {
        return this.zzi;
    }

    public final List zzk() {
        return this.zzh;
    }
}
