package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.player.AdMediaInfo;
import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzcj;
import com.google.ads.interactivemedia.v3.impl.data.zzcp;
import com.google.ads.interactivemedia.v3.internal.zzrz;
import java.util.HashMap;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzg implements VideoAdPlayer.VideoAdPlayerCallback {
    private final HashMap zza = zzrz.zzb(2);
    private zzf zzb;
    private boolean zzc = false;

    zzg() {
    }

    private final void zzd(JavaScriptMessage.MsgType msgType, AdMediaInfo adMediaInfo, Object obj) {
        zzf zzf = this.zzb;
        if (zzf != null) {
            zzf.zzb(msgType, adMediaInfo, obj);
        }
    }

    public final void onAdProgress(AdMediaInfo adMediaInfo, VideoProgressUpdate videoProgressUpdate) {
        if (this.zzc && videoProgressUpdate != null && videoProgressUpdate.getDuration() > 0.0f) {
            if (this.zza.get(adMediaInfo) == null && videoProgressUpdate.getCurrentTime() > 0.0f) {
                zzd(JavaScriptMessage.MsgType.start, adMediaInfo, (Object) null);
                this.zza.put(adMediaInfo, true);
            }
            zzd(JavaScriptMessage.MsgType.timeupdate, adMediaInfo, zzcj.create(videoProgressUpdate));
        }
    }

    public final void onBuffering(AdMediaInfo adMediaInfo) {
        if (this.zzc) {
            zzd(JavaScriptMessage.MsgType.waiting, adMediaInfo, (Object) null);
        }
    }

    public final void onContentComplete() {
        zzf zzf = this.zzb;
        if (zzf != null) {
            zzf.zzv(JavaScriptMessage.MsgChannel.adsLoader, JavaScriptMessage.MsgType.contentComplete);
        }
    }

    public final void onEnded(AdMediaInfo adMediaInfo) {
        if (this.zzc) {
            zzd(JavaScriptMessage.MsgType.end, adMediaInfo, (Object) null);
            this.zza.remove(adMediaInfo);
        }
    }

    public final void onError(AdMediaInfo adMediaInfo) {
        if (this.zzc) {
            zzd(JavaScriptMessage.MsgType.error, adMediaInfo, (Object) null);
            this.zza.remove(adMediaInfo);
        }
    }

    public final void onLoaded(AdMediaInfo adMediaInfo) {
        if (this.zzc) {
            zzd(JavaScriptMessage.MsgType.loaded, adMediaInfo, (Object) null);
        }
    }

    public final void onPause(AdMediaInfo adMediaInfo) {
        if (this.zzc) {
            zzd(JavaScriptMessage.MsgType.pause, adMediaInfo, (Object) null);
        }
    }

    public final void onPlay(AdMediaInfo adMediaInfo) {
    }

    public final void onResume(AdMediaInfo adMediaInfo) {
        if (this.zzc) {
            zzd(JavaScriptMessage.MsgType.play, adMediaInfo, (Object) null);
        }
    }

    public final void onVolumeChanged(AdMediaInfo adMediaInfo, int i) {
        if (this.zzc) {
            zzd(JavaScriptMessage.MsgType.volumeChange, adMediaInfo, zzcp.builder().volumePercentage(i).build());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzf zzf) {
        this.zzb = zzf;
    }

    /* access modifiers changed from: package-private */
    public final void zzb() {
        this.zzc = true;
    }

    /* access modifiers changed from: package-private */
    public final void zzc() {
        this.zzc = false;
    }
}
