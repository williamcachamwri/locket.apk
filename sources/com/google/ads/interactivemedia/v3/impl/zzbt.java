package com.google.ads.interactivemedia.v3.impl;

import android.view.View;
import android.webkit.WebView;
import com.facebook.hermes.intl.Constants;
import com.google.ads.interactivemedia.omid.library.adsession.zza;
import com.google.ads.interactivemedia.omid.library.adsession.zzb;
import com.google.ads.interactivemedia.omid.library.adsession.zzc;
import com.google.ads.interactivemedia.omid.library.adsession.zzf;
import com.google.ads.interactivemedia.omid.library.adsession.zzh;
import com.google.ads.interactivemedia.omid.library.adsession.zzi;
import com.google.ads.interactivemedia.omid.library.adsession.zzj;
import com.google.ads.interactivemedia.v3.api.AdErrorEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.FriendlyObstruction;
import com.google.ads.interactivemedia.v3.impl.JavaScriptMessage;
import com.google.ads.interactivemedia.v3.impl.data.zzcd;
import com.google.ads.interactivemedia.v3.internal.zzfl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzbt implements AdEvent.AdEventListener, AdErrorEvent.AdErrorListener, zzae {
    private final zzbi zza;
    private final WebView zzb;
    private final zzfl zzc;
    private View zzd;
    private String zze;
    private final Set zzf;
    private boolean zzg = false;
    private String zzh = null;
    private zza zzi;

    zzbt(zzbi zzbi, WebView webView, zzfl zzfl) {
        this.zza = zzbi;
        this.zzb = webView;
        this.zzc = zzfl;
        this.zzf = new HashSet();
    }

    private final void zzh(List list) {
        zzcd zzcd;
        if (list == null) {
            zzcd = null;
        } else if (!list.isEmpty()) {
            zzcd = zzcd.builder().friendlyObstructions(list).build();
        } else {
            return;
        }
        this.zza.zzn(new JavaScriptMessage(JavaScriptMessage.MsgChannel.omid, JavaScriptMessage.MsgType.registerFriendlyObstructions, this.zze, zzcd));
    }

    public final void onAdError(AdErrorEvent adErrorEvent) {
        zza zza2;
        if (this.zzc.zzc() && (zza2 = this.zzi) != null) {
            zza2.zzc();
            this.zzi = null;
        }
    }

    public final void onAdEvent(AdEvent adEvent) {
        if (this.zzc.zzc()) {
            AdEvent.AdEventType adEventType = AdEvent.AdEventType.ALL_ADS_COMPLETED;
            int ordinal = adEvent.getType().ordinal();
            if (ordinal == 3 || ordinal == 14) {
                zzg();
            } else if (ordinal == 15 && this.zzc.zzc() && this.zzi == null && this.zzd != null) {
                zzb zza2 = zzb.zza(zzf.DEFINED_BY_JAVASCRIPT, zzh.DEFINED_BY_JAVASCRIPT, zzi.JAVASCRIPT, zzi.JAVASCRIPT, true);
                WebView webView = this.zzb;
                zzj zza3 = zzj.zza("Google1", "3.35.1");
                String str = this.zzh;
                String str2 = true != this.zzg ? Constants.CASEFIRST_FALSE : "true";
                zza zza4 = zza.zza(zza2, zzc.zzb(zza3, webView, str, "{ssai:" + str2 + "}"));
                zza4.zzd(this.zzd);
                for (FriendlyObstruction friendlyObstruction : this.zzf) {
                    zza4.zzb(friendlyObstruction.getView(), friendlyObstruction.getPurpose().getOmidPurpose(), friendlyObstruction.getDetailedReason());
                }
                zzh(new ArrayList(this.zzf));
                zza4.zzf();
                this.zzi = zza4;
            }
        }
    }

    public final void zza(FriendlyObstruction friendlyObstruction) {
        if (!this.zzf.contains(friendlyObstruction)) {
            this.zzf.add(friendlyObstruction);
            zza zza2 = this.zzi;
            if (zza2 != null) {
                zza2.zzb(friendlyObstruction.getView(), friendlyObstruction.getPurpose().getOmidPurpose(), friendlyObstruction.getDetailedReason());
                zzh(Arrays.asList(new FriendlyObstruction[]{friendlyObstruction}));
            }
        }
    }

    public final void zzb(View view) {
        this.zzd = view;
    }

    public final void zzc(String str) {
        this.zzh = str;
    }

    public final void zzd(boolean z) {
        this.zzg = true;
    }

    public final void zze(String str) {
        this.zze = str;
    }

    public final void zzf() {
        this.zzf.clear();
        zza zza2 = this.zzi;
        if (zza2 != null) {
            zza2.zze();
            zzh((List) null);
        }
    }

    public final boolean zzg() {
        zza zza2;
        if (!this.zzc.zzc() || (zza2 = this.zzi) == null) {
            return false;
        }
        zza2.zzc();
        this.zzi = null;
        return true;
    }
}
