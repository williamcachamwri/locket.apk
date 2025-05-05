package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import androidx.webkit.ProxyConfig;
import com.google.ads.interactivemedia.v3.api.AdDisplayContainer;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.player.AdMediaInfo;
import com.google.ads.interactivemedia.v3.api.player.ResizablePlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzbu;
import com.google.ads.interactivemedia.v3.impl.data.zzce;
import com.google.ads.interactivemedia.v3.impl.data.zzd;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.ads.interactivemedia.v3.internal.zzfq;
import com.google.ads.interactivemedia.v3.internal.zzrf;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbl implements zzcc, zzf, zzbh {
    private final AdDisplayContainer zza;
    private final VideoAdPlayer zzb;
    private final zzat zzc;
    private final zzbi zzd;
    private final String zze;
    private final zzg zzf;
    private final zzrf zzg = zzrf.zzf(2);

    public zzbl(String str, zzbi zzbi, zzat zzat, AdDisplayContainer adDisplayContainer, Context context) {
        this.zza = adDisplayContainer;
        VideoAdPlayer player = adDisplayContainer.getPlayer();
        this.zzb = player;
        this.zzc = zzat;
        this.zzd = zzbi;
        this.zze = str;
        zzg zzg2 = new zzg();
        this.zzf = zzg2;
        zzg2.zza(this);
        player.addCallback(zzg2);
    }

    public final VideoProgressUpdate getAdProgress() {
        return this.zzb.getAdProgress();
    }

    public final void zza(JavaScriptMessage javaScriptMessage) {
        JavaScriptMessage.MsgChannel zza2 = javaScriptMessage.zza();
        JavaScriptMessage.MsgType zzb2 = javaScriptMessage.zzb();
        zzbu zzbu = (zzbu) javaScriptMessage.zzc();
        AdMediaInfo adMediaInfo = (AdMediaInfo) this.zzg.get(zza2);
        JavaScriptMessage.MsgType msgType = JavaScriptMessage.MsgType.activate;
        int ordinal = zzb2.ordinal();
        if (ordinal != 34) {
            if (ordinal != 45) {
                if (ordinal != 75) {
                    if (ordinal == 55) {
                        this.zzb.pauseAd(adMediaInfo);
                        return;
                    } else if (ordinal == 56) {
                        this.zzb.playAd(adMediaInfo);
                        this.zzf.zzb();
                        return;
                    } else {
                        return;
                    }
                }
            } else if (zzbu == null || zzbu.videoUrl == null) {
                this.zzc.zzc(new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Load message must contain video url.")));
                return;
            } else {
                this.zzf.zzb();
                AdMediaInfo adMediaInfo2 = new AdMediaInfo(zzbu.videoUrl);
                zzd zzd2 = zzbu.adPodInfo;
                if (zzd2 == null) {
                    zzd2 = null;
                }
                this.zzg.put(zza2, adMediaInfo2);
                this.zzb.loadAd(adMediaInfo2, zzd2);
                return;
            }
        }
        this.zzb.stopAd(adMediaInfo);
        this.zzg.remove(zza2);
    }

    public final void zzb(JavaScriptMessage.MsgType msgType, AdMediaInfo adMediaInfo, Object obj) {
        JavaScriptMessage.MsgChannel msgChannel = (JavaScriptMessage.MsgChannel) this.zzg.zze().get(adMediaInfo);
        if (msgChannel == null) {
            String valueOf = String.valueOf(msgType);
            zzfk.zzd("The adMediaInfo for the " + valueOf + " event is not active. This may occur if callbacks are triggered after the ad is unloaded.");
            return;
        }
        this.zzd.zzn(new JavaScriptMessage(msgChannel, msgType, this.zze, obj));
    }

    public final void zzc() {
        zzfk.zzc("Destroying NativeVideoDisplay");
        this.zzb.removeCallback(this.zzf);
        this.zzb.release();
    }

    public final void zzd() {
    }

    public final void zze() {
        this.zzf.zzc();
    }

    public final void zzf(zzce zzce) {
        if (!(this.zzb instanceof ResizablePlayer)) {
            zzfk.zza("Video player does not support resizing.");
        } else if (!zzfq.zza(this.zza, zzce)) {
            zzfk.zza("Creative resize parameters were not within the containers bounds.");
        } else {
            int width = this.zza.getAdContainer().getWidth();
            int height = this.zza.getAdContainer().getHeight();
            ((ResizablePlayer) this.zzb).resize(zzce.x().intValue(), zzce.y().intValue(), (width - zzce.x().intValue()) - zzce.width().intValue(), (height - zzce.y().intValue()) - zzce.height().intValue());
        }
    }

    public final void zzg() {
        VideoAdPlayer videoAdPlayer = this.zzb;
        if (videoAdPlayer instanceof ResizablePlayer) {
            ((ResizablePlayer) videoAdPlayer).resize(0, 0, 0, 0);
        }
    }

    public final void zzv(JavaScriptMessage.MsgChannel msgChannel, JavaScriptMessage.MsgType msgType) {
        this.zzd.zzn(new JavaScriptMessage(msgChannel, msgType, ProxyConfig.MATCH_ALL_SCHEMES, (Object) null));
    }
}
