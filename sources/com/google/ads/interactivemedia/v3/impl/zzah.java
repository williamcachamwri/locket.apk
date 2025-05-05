package com.google.ads.interactivemedia.v3.impl;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzbf;
import com.google.ads.interactivemedia.v3.impl.data.zzbu;
import com.google.ads.interactivemedia.v3.impl.data.zzc;
import com.google.ads.interactivemedia.v3.internal.zzfk;
import com.google.ads.interactivemedia.v3.internal.zzrp;
import java.util.ArrayList;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzah implements zzbh {
    final /* synthetic */ zzak zza;

    zzah(zzak zzak) {
        this.zza = zzak;
    }

    public final void zza(JavaScriptMessage javaScriptMessage) {
        zzc zzc;
        JavaScriptMessage.MsgType zzb = javaScriptMessage.zzb();
        zzbu zzbu = (zzbu) javaScriptMessage.zzc();
        if (zzbu == null || (zzc = zzbu.adData) == null) {
            zzc = null;
        }
        AdEvent.AdEventType adEventType = AdEvent.AdEventType.ALL_ADS_COMPLETED;
        JavaScriptMessage.MsgType msgType = JavaScriptMessage.MsgType.activate;
        int ordinal = zzb.ordinal();
        if (ordinal == 1) {
            this.zza.zzc(new zzag(AdEvent.AdEventType.AD_BREAK_ENDED, zzc));
        } else if (ordinal == 2) {
            zzag zzag = new zzag(AdEvent.AdEventType.AD_BREAK_FETCH_ERROR, (zzc) null);
            zzag.zzc = zzrp.zze("adBreakTime", zzbu.adBreakTime);
            this.zza.zzc(zzag);
        } else if (ordinal == 3) {
            zzag zzag2 = new zzag(AdEvent.AdEventType.AD_BREAK_READY, (zzc) null);
            zzag2.zzc = zzrp.zze("adBreakTime", zzbu.adBreakTime);
            this.zza.zzc(zzag2);
        } else if (ordinal == 4) {
            this.zza.zzc(new zzag(AdEvent.AdEventType.AD_BREAK_STARTED, zzc));
        } else if (ordinal == 5) {
            this.zza.zzc(new zzag(AdEvent.AdEventType.AD_BUFFERING, (zzc) null));
        } else if (ordinal == 12) {
            this.zza.zzc(new zzag(AdEvent.AdEventType.ALL_ADS_COMPLETED, (zzc) null));
        } else if (ordinal == 16) {
            this.zza.zzc(new zzag(AdEvent.AdEventType.CLICKED, zzc));
        } else if (ordinal == 18) {
            this.zza.zzc(new zzag(AdEvent.AdEventType.COMPLETED, zzc));
        } else if (ordinal == 25) {
            zzag zzag3 = new zzag(AdEvent.AdEventType.CUEPOINTS_CHANGED, (zzc) null);
            zzag3.zzd = new ArrayList();
            for (zzbf next : zzbu.cuepoints) {
                zzag3.zzd.add(new zzas(next.start(), next.end(), next.played()));
            }
            this.zza.zzc(zzag3);
        } else if (ordinal == 40) {
            this.zza.zzc(new zzag(AdEvent.AdEventType.ICON_FALLBACK_IMAGE_CLOSED, (zzc) null));
        } else if (ordinal != 46) {
            if (ordinal == 51) {
                this.zza.zzn(zzbu.url);
            } else if (ordinal == 55) {
                this.zza.zzc(new zzag(AdEvent.AdEventType.PAUSED, zzc));
            } else if (ordinal == 64) {
                this.zza.zzc(new zzag(AdEvent.AdEventType.RESUMED, zzc));
            } else if (ordinal == 72) {
                this.zza.zzc(new zzag(AdEvent.AdEventType.THIRD_QUARTILE, zzc));
            } else if (ordinal == 20) {
                this.zza.zzc(new zzag(AdEvent.AdEventType.CONTENT_PAUSE_REQUESTED, (zzc) null));
            } else if (ordinal == 21) {
                this.zza.zzc(new zzag(AdEvent.AdEventType.CONTENT_RESUME_REQUESTED, (zzc) null));
            } else if (ordinal == 31) {
                this.zza.zza(AdError.AdErrorType.PLAY, AdError.AdErrorCode.getErrorCodeByNumber(zzbu.errorCode), zzba.zzf(zzbu.errorMessage, zzbu.innerError));
            } else if (ordinal == 32) {
                this.zza.zzc(new zzag(AdEvent.AdEventType.FIRST_QUARTILE, zzc));
            } else if (ordinal == 48) {
                zzag zzag4 = new zzag(AdEvent.AdEventType.LOG, zzc);
                zzag4.zzc = zzbu.logData.constructMap();
                this.zza.zzc(zzag4);
            } else if (ordinal == 49) {
                this.zza.zzc(new zzag(AdEvent.AdEventType.MIDPOINT, zzc));
            } else if (ordinal == 78) {
                this.zza.zzc(new zzag(AdEvent.AdEventType.TAPPED, zzc));
            } else if (ordinal != 79) {
                switch (ordinal) {
                    case 8:
                        this.zza.zzc(new zzag(AdEvent.AdEventType.AD_PERIOD_ENDED, (zzc) null));
                        return;
                    case 9:
                        this.zza.zzc(new zzag(AdEvent.AdEventType.AD_PERIOD_STARTED, (zzc) null));
                        return;
                    case 10:
                        zzag zzag5 = new zzag(AdEvent.AdEventType.AD_PROGRESS, zzc);
                        zzag5.zze = new zzh(zzbu.currentTime, zzbu.duration, zzbu.adPosition, zzbu.totalAds, zzbu.adBreakDuration, zzbu.adPeriodDuration, zzbu.adsDurationsMs);
                        this.zza.zzc(zzag5);
                        return;
                    default:
                        switch (ordinal) {
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                                zzag zzag6 = new zzag(AdEvent.AdEventType.SKIPPED, (zzc) null);
                                zzag6.zzg = zzbu.seekTime;
                                this.zza.zzc(zzag6);
                                return;
                            case 67:
                                this.zza.zzc(new zzag(AdEvent.AdEventType.SKIPPABLE_STATE_CHANGED, zzc));
                                return;
                            case 68:
                                this.zza.zzc(new zzag(AdEvent.AdEventType.STARTED, zzc));
                                return;
                            default:
                                return;
                        }
                }
            } else {
                zzag zzag7 = new zzag(AdEvent.AdEventType.ICON_TAPPED, (zzc) null);
                if (zzbu != null) {
                    zzag7.zzf = zzbu.iconClickFallbackImages;
                }
                this.zza.zzc(zzag7);
            }
        } else if (zzc != null) {
            this.zza.zzc(new zzag(AdEvent.AdEventType.LOADED, zzc));
        } else {
            zzfk.zza("Ad loaded message requires adData");
            this.zza.zza(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Ad loaded message did not contain adData.");
        }
    }
}
