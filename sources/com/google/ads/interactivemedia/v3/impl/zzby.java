package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import android.util.Log;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.CuePoint;
import com.google.ads.interactivemedia.v3.api.StreamDisplayContainer;
import com.google.ads.interactivemedia.v3.api.StreamManager;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.internal.zzro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzby extends zzak implements StreamManager {
    private final String zza;
    private List zzb = new ArrayList();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzby(String str, zzba zzba, StreamDisplayContainer streamDisplayContainer, zzcb zzcb, zzb zzb2, zzbt zzbt, zzat zzat, ExecutorService executorService, Context context, String str2, boolean z) {
        super(str, zzba, zzcb, streamDisplayContainer, zzb2, zzbt, zzat, executorService, context, z);
        this.zza = str2;
        zzcb.zzj();
    }

    public final void destroy() {
        super.destroy();
        zzr(JavaScriptMessage.MsgType.contentComplete);
        zzp();
    }

    public final double getContentTimeForStreamTime(double d) {
        double d2 = d;
        for (CuePoint cuePoint : this.zzb) {
            if (cuePoint.getStartTime() > cuePoint.getEndTime()) {
                return 0.0d;
            }
            if (d >= cuePoint.getEndTime()) {
                d2 -= cuePoint.getEndTime() - cuePoint.getStartTime();
            } else if (d < cuePoint.getEndTime() && d > cuePoint.getStartTime()) {
                d2 -= d - cuePoint.getStartTime();
            }
        }
        return d2;
    }

    public final long getContentTimeMsForStreamTimeMs(long j) {
        long j2 = j;
        for (CuePoint cuePoint : this.zzb) {
            if (cuePoint.getStartTimeMs() > cuePoint.getEndTimeMs()) {
                return 0;
            }
            if (j >= cuePoint.getEndTimeMs()) {
                j2 -= cuePoint.getEndTimeMs() - cuePoint.getStartTimeMs();
            } else if (j < cuePoint.getEndTimeMs() && j > cuePoint.getStartTimeMs()) {
                j2 -= j - cuePoint.getStartTimeMs();
            }
        }
        return j2;
    }

    public final List<CuePoint> getCuePoints() {
        return Collections.unmodifiableList(this.zzb);
    }

    public final CuePoint getPreviousCuePointForStreamTime(double d) {
        CuePoint cuePoint = null;
        for (CuePoint cuePoint2 : this.zzb) {
            if (cuePoint2.getStartTime() < d) {
                cuePoint = cuePoint2;
            }
        }
        return cuePoint;
    }

    public final CuePoint getPreviousCuePointForStreamTimeMs(long j) {
        CuePoint cuePoint = null;
        for (CuePoint cuePoint2 : this.zzb) {
            if (cuePoint2.getStartTimeMs() < j) {
                cuePoint = cuePoint2;
            }
        }
        return cuePoint;
    }

    public final String getStreamId() {
        return this.zza;
    }

    public final double getStreamTimeForContentTime(double d) {
        double d2 = d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        for (CuePoint cuePoint : this.zzb) {
            if (cuePoint.getStartTime() > cuePoint.getEndTime()) {
                return 0.0d;
            }
            d3 += cuePoint.getStartTime() - d4;
            if (d3 > d) {
                return d2;
            }
            d2 += cuePoint.getEndTime() - cuePoint.getStartTime();
            d4 = cuePoint.getEndTime();
        }
        return d2;
    }

    public final long getStreamTimeMsForContentTimeMs(long j) {
        long j2 = j;
        long j3 = 0;
        long j4 = 0;
        for (CuePoint cuePoint : this.zzb) {
            if (cuePoint.getStartTimeMs() > cuePoint.getEndTimeMs()) {
                return 0;
            }
            j3 += cuePoint.getStartTimeMs() - j4;
            if (j3 > j) {
                return j2;
            }
            j2 += cuePoint.getEndTimeMs() - cuePoint.getStartTimeMs();
            j4 = cuePoint.getEndTimeMs();
        }
        return j2;
    }

    public final boolean isCustomPlaybackUsed() {
        return true;
    }

    public final void loadThirdPartyStream(String str, List<? extends Map<String, String>> list) {
        zzro zzro = new zzro();
        zzro.zza("streamUrl", str);
        zzro.zza("subtitles", list);
        zzq(JavaScriptMessage.MsgChannel.adsManager, JavaScriptMessage.MsgType.loadStreamMetadata, zzro.zzc());
    }

    public final void replaceAdTagParameters(Map<String, String> map) {
        if (map != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("adTagParameters", map);
            zzq(JavaScriptMessage.MsgChannel.adsManager, JavaScriptMessage.MsgType.replaceAdTagParameters, hashMap);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(zzag zzag) {
        zzcb zzcb = (zzcb) zzg();
        AdEvent.AdEventType adEventType = AdEvent.AdEventType.ALL_ADS_COMPLETED;
        int ordinal = zzag.zza.ordinal();
        if (ordinal == 3) {
            zzcb.zzm();
        } else if (ordinal == 4) {
            this.zzb = zzag.zzd;
        } else if (ordinal == 14) {
            double d = zzag.zzg;
            Log.i("IMASDK", "Seek time when ad is skipped: " + d);
            zzcb.zzk(Math.round(zzag.zzg * 1000.0d));
        } else if (ordinal != 15) {
            switch (ordinal) {
                case 23:
                    zzcb.zze();
                    break;
                case 24:
                    zzcb.zzb();
                    break;
                case 25:
                    zzcb.zzi();
                    break;
                case 26:
                    zzcb.zzh();
                    break;
            }
        } else {
            zzcb.zzl(zzag.zzb);
        }
        super.zzc(zzag);
    }
}
