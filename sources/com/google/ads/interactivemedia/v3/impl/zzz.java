package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdsManager;
import com.google.ads.interactivemedia.v3.api.AdsRenderingSettings;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzcj;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzz extends zzak implements AdsManager, AdErrorEvent.AdErrorListener {
    private final List zza;
    private zzcd zzb;
    private zzaq zzc;
    private zzar zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzz(String str, zzba zzba, AdDisplayContainer adDisplayContainer, List list, zzbl zzbl, zzar zzar, zzb zzb2, zzcd zzcd, zzbt zzbt, zzat zzat, ExecutorService executorService, Context context, boolean z) {
        super(str, zzba, zzbl, adDisplayContainer, zzb2, zzbt, zzat, executorService, context, z);
        this.zza = list;
        this.zzd = zzar;
        this.zzb = zzcd;
    }

    static zzz zza(String str, zzba zzba, zzcd zzcd, AdDisplayContainer adDisplayContainer, zzar zzar, List list, SortedSet sortedSet, zzbt zzbt, zzat zzat, ExecutorService executorService, Context context, boolean z) {
        zzz zzz;
        zzba zzba2 = zzba;
        String str2 = str;
        zzba zzba3 = zzba;
        zzz zzz2 = r0;
        zzz zzz3 = new zzz(str2, zzba3, adDisplayContainer, list, new zzbl(str2, zzba3, zzat, adDisplayContainer, context), zzar, new zzb(str, zzba2, adDisplayContainer.getAdContainer()), zzcd, zzbt, zzat, executorService, context, z);
        zzar zzar2 = zzz2.zzd;
        if (zzar2 != null) {
            zzz = zzz2;
            zzaq zzaq = new zzaq(zzba2, sortedSet, str);
            zzz.zzc = zzaq;
            zzar2.zzc(zzaq);
            zzz.zzd.zze();
        } else {
            zzz = zzz2;
        }
        zzz.addAdErrorListener(zzz);
        return zzz;
    }

    public final void clicked() {
        zzr(JavaScriptMessage.MsgType.click);
    }

    public final void destroy() {
        super.destroy();
        zzar zzar = this.zzd;
        if (zzar != null) {
            zzar.zzf();
            this.zzd = null;
        }
        zzcd zzcd = this.zzb;
        if (zzcd != null) {
            zzcd.zza();
            this.zzb = null;
        }
        zzr(JavaScriptMessage.MsgType.destroy);
    }

    public final void discardAdBreak() {
        zzr(JavaScriptMessage.MsgType.discardAdBreak);
    }

    public final List<Float> getAdCuePoints() {
        return this.zza;
    }

    public final boolean isCustomPlaybackUsed() {
        zzbl zzbl = (zzbl) zzg();
        return true;
    }

    public final void onAdError(AdErrorEvent adErrorEvent) {
        zzcd zzcd = this.zzb;
        if (zzcd != null) {
            zzcd.zza();
        }
    }

    public final void pause() {
        zzr(JavaScriptMessage.MsgType.pause);
    }

    public final void requestNextAdBreak() {
        if (this.zzd != null) {
            zzq(JavaScriptMessage.MsgChannel.contentTimeUpdate, JavaScriptMessage.MsgType.contentTimeUpdate, zzcj.create(this.zzd.zza()));
            zzr(JavaScriptMessage.MsgType.requestNextAdBreak);
        }
    }

    public final void resume() {
        zzr(JavaScriptMessage.MsgType.resume);
    }

    public final void skip() {
        zzr(JavaScriptMessage.MsgType.skip);
    }

    public final void start() {
        zzr(JavaScriptMessage.MsgType.start);
    }

    /* access modifiers changed from: package-private */
    public final Map zzb(AdsRenderingSettings adsRenderingSettings) {
        Map zzb2 = super.zzb(adsRenderingSettings);
        zzar zzar = this.zzd;
        if (zzar != null) {
            VideoProgressUpdate zza2 = zzar.zza();
            if (!zza2.equals(VideoProgressUpdate.VIDEO_TIME_NOT_READY)) {
                StringBuilder sb = new StringBuilder("AdsManager.init -> Setting contentStartTime ");
                double currentTimeMs = (double) (((float) zza2.getCurrentTimeMs()) / 1000.0f);
                sb.append(currentTimeMs);
                zzfk.zzc(sb.toString());
                zzb2.put("contentStartTime", Double.valueOf(currentTimeMs));
            }
        }
        return zzb2;
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzag zzag) {
        zzcd zzcd;
        zzbl zzbl = (zzbl) zzg();
        AdEvent.AdEventType adEventType = AdEvent.AdEventType.ALL_ADS_COMPLETED;
        int ordinal = zzag.zza.ordinal();
        if (ordinal != 0) {
            if (ordinal == 5) {
                zzar zzar = this.zzd;
                if (zzar != null) {
                    zzar.zzf();
                }
            } else if (ordinal == 6) {
                zzcd zzcd2 = this.zzb;
                if (zzcd2 != null) {
                    zzcd2.zza();
                }
                zzbl.zze();
                zzar zzar2 = this.zzd;
                if (zzar2 != null) {
                    zzar2.zze();
                }
            } else if (ordinal == 14) {
                zzcd zzcd3 = this.zzb;
                if (zzcd3 != null) {
                    zzcd3.zza();
                }
            } else if (ordinal == 15 && (zzcd = this.zzb) != null) {
                zzcd.zzb();
            }
            super.zzc(zzag);
            return;
        }
        super.destroy();
        zzar zzar3 = this.zzd;
        if (zzar3 != null) {
            zzar3.zzf();
            this.zzd = null;
        }
        zzcd zzcd4 = this.zzb;
        if (zzcd4 != null) {
            zzcd4.zza();
            this.zzb = null;
        }
        zzr(JavaScriptMessage.MsgType.destroy);
        super.zzc(zzag);
        zzp();
    }
}
