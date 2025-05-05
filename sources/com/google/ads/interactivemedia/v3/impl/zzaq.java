package com.google.ads.interactivemedia.v3.impl;

import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzcj;
import java.util.SortedSet;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzaq implements zzbv {
    private final SortedSet zza;
    private final zzbi zzb;
    private final String zzc;
    private long zzd = 0;
    private VideoProgressUpdate zze = new VideoProgressUpdate(0, 0);

    zzaq(zzbi zzbi, SortedSet sortedSet, String str) {
        this.zza = sortedSet;
        this.zzb = zzbi;
        this.zzc = str;
    }

    public final void zzw(VideoProgressUpdate videoProgressUpdate) {
        SortedSet sortedSet;
        if (videoProgressUpdate != null && videoProgressUpdate.getDuration() >= 0.0f && !videoProgressUpdate.equals(this.zze)) {
            float currentTime = this.zze.getCurrentTime();
            float currentTime2 = videoProgressUpdate.getCurrentTime();
            if (currentTime < currentTime2) {
                sortedSet = this.zza.subSet(Float.valueOf(currentTime), Float.valueOf(currentTime2));
            } else {
                sortedSet = this.zza.subSet(Float.valueOf(currentTime2), Float.valueOf(currentTime));
            }
            if (!sortedSet.isEmpty() || this.zza.contains(Float.valueOf(videoProgressUpdate.getCurrentTime())) || System.currentTimeMillis() - this.zzd >= 1000) {
                this.zzd = System.currentTimeMillis();
                this.zze = videoProgressUpdate;
                this.zzb.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.contentTimeUpdate, JavaScriptMessage.MsgType.contentTimeUpdate, this.zzc, zzcj.create(videoProgressUpdate)));
            }
        }
    }
}
