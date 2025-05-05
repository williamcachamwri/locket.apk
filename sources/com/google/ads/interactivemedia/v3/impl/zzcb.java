package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import android.net.Uri;
import androidx.webkit.ProxyConfig;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.player.ResizablePlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.api.player.VideoStreamPlayer;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzbu;
import com.google.ads.interactivemedia.v3.impl.data.zzc;
import com.google.ads.interactivemedia.v3.impl.data.zzce;
import com.google.ads.interactivemedia.v3.impl.data.zzcj;
import com.google.ads.interactivemedia.v3.impl.data.zzcp;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.ads.interactivemedia.v3.internal.zzfq;
import com.google.ads.interactivemedia.v3.internal.zzgh;
import com.google.ads.interactivemedia.v3.internal.zzqm;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzcb implements VideoStreamPlayer.VideoStreamPlayerCallback, zzcc, zzbv, zzbh {
    private final VideoStreamPlayer zza;
    private final zzbi zzb;
    private final zzat zzc;
    private boolean zzd = false;
    private final zzar zze;
    private final zzcd zzf;
    private final String zzg;
    private final String zzh;
    private final StreamDisplayContainer zzi;

    zzcb(String str, zzbi zzbi, zzat zzat, StreamDisplayContainer streamDisplayContainer, String str2, zzar zzar, zzcd zzcd, Context context) {
        this.zza = streamDisplayContainer.getVideoStreamPlayer();
        this.zzc = zzat;
        this.zzg = str;
        this.zzb = zzbi;
        this.zzh = str2;
        this.zzd = false;
        this.zzi = streamDisplayContainer;
        this.zze = zzar;
        this.zzf = zzcd;
    }

    private final void zzn(JavaScriptMessage.MsgType msgType, Object obj) {
        this.zzb.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.videoDisplay1, msgType, this.zzg, obj));
    }

    public final VideoProgressUpdate getAdProgress() {
        return this.zza.getContentProgress();
    }

    public final void onContentComplete() {
        this.zzb.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.adsLoader, JavaScriptMessage.MsgType.contentComplete, ProxyConfig.MATCH_ALL_SCHEMES, (Object) null));
    }

    public final void onPause() {
        zzn(JavaScriptMessage.MsgType.pause, (Object) null);
    }

    public final void onResume() {
        zzn(JavaScriptMessage.MsgType.play, (Object) null);
    }

    public final void onUserTextReceived(String str) {
        if (!zzqm.zzc(str)) {
            zzn(JavaScriptMessage.MsgType.timedMetadata, zzca.create(str));
        }
    }

    public final void onVolumeChanged(int i) {
        zzn(JavaScriptMessage.MsgType.volumeChange, zzcp.builder().volumePercentage(i).build());
    }

    public final void zza(JavaScriptMessage javaScriptMessage) {
        String str;
        JavaScriptMessage.MsgType zzb2 = javaScriptMessage.zzb();
        zzbu zzbu = (zzbu) javaScriptMessage.zzc();
        JavaScriptMessage.MsgType msgType = JavaScriptMessage.MsgType.activate;
        int ordinal = zzb2.ordinal();
        if (ordinal != 47) {
            if (ordinal == 55) {
                this.zza.pause();
            } else if (ordinal == 56) {
                this.zza.resume();
            }
        } else if (zzbu == null || (str = zzbu.streamUrl) == null) {
            this.zzc.zzc(new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Load message must contain video url.")));
        } else {
            int i = 0;
            this.zzd = false;
            String str2 = this.zzh;
            if (!(str2 == null || str2.length() == 0)) {
                String str3 = "";
                String replaceAll = str2.trim().replaceAll("\\s+", str3);
                if (replaceAll.charAt(0) == '?') {
                    replaceAll = replaceAll.substring(1);
                }
                if (replaceAll.length() != 0) {
                    Map zzc2 = zzgh.zzc(Uri.parse(str));
                    HashMap hashMap = new HashMap();
                    Uri.Builder buildUpon = Uri.parse(str).buildUpon();
                    buildUpon.clearQuery();
                    Map zzc3 = zzgh.zzc(Uri.parse("http://www.dom.com/path?".concat(String.valueOf(replaceAll))));
                    hashMap.putAll(zzc3);
                    if (!zzc2.isEmpty()) {
                        for (String str4 : zzc2.keySet()) {
                            if (!zzc3.containsKey(str4)) {
                                hashMap.put(str4, (String) zzc2.get(str4));
                            }
                        }
                    }
                    if (!hashMap.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        for (Map.Entry entry : hashMap.entrySet()) {
                            sb.append((String) entry.getKey());
                            sb.append("=");
                            sb.append((String) entry.getValue());
                            if (i < hashMap.size() - 1) {
                                sb.append("&");
                            }
                            i++;
                        }
                        str3 = sb.toString();
                    }
                    buildUpon.encodedQuery(str3);
                    str = buildUpon.build().toString();
                }
            }
            this.zza.loadUrl(str, zzbu.subtitles);
        }
    }

    public final void zzb() {
        this.zza.onAdBreakEnded();
        this.zzf.zza();
    }

    public final void zzc() {
        zzfk.zzc("Destroying StreamVideoDisplay");
        this.zza.removeCallback(this);
        this.zze.zzf();
        this.zze.zzd(this);
        this.zzf.zza();
    }

    public final void zzd() {
        this.zze.zzc(this);
        this.zze.zze();
    }

    public final void zze() {
        this.zza.onAdBreakStarted();
    }

    public final void zzf(zzce zzce) {
        if (!(this.zza instanceof ResizablePlayer)) {
            zzfk.zza("Stream player does not support resizing.");
        } else if (!zzfq.zza(this.zzi, zzce)) {
            zzfk.zza("Video resize parameters were not within the container bounds.");
        } else {
            int width = this.zzi.getAdContainer().getWidth();
            int height = this.zzi.getAdContainer().getHeight();
            ((ResizablePlayer) this.zza).resize(zzce.x().intValue(), zzce.y().intValue(), (width - zzce.x().intValue()) - zzce.width().intValue(), (height - zzce.y().intValue()) - zzce.height().intValue());
        }
    }

    public final void zzg() {
        VideoStreamPlayer videoStreamPlayer = this.zza;
        if (videoStreamPlayer instanceof ResizablePlayer) {
            ((ResizablePlayer) videoStreamPlayer).resize(0, 0, 0, 0);
        }
    }

    public final void zzh() {
        this.zza.onAdPeriodEnded();
    }

    public final void zzi() {
        this.zza.onAdPeriodStarted();
    }

    public final void zzj() {
        this.zza.addCallback(this);
    }

    public final void zzk(long j) {
        this.zza.seek(j);
    }

    /* access modifiers changed from: package-private */
    public final void zzl(zzc zzc2) {
        if (zzc2.isLinear()) {
            this.zzf.zzb();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzm() {
        this.zzf.zza();
    }

    public final void zzw(VideoProgressUpdate videoProgressUpdate) {
        if (!this.zzd) {
            zzn(JavaScriptMessage.MsgType.start, zzcp.builder().volumePercentage(this.zza.getVolume()).build());
            this.zzd = true;
        }
        zzn(JavaScriptMessage.MsgType.timeupdate, zzcj.create(videoProgressUpdate));
    }
}
