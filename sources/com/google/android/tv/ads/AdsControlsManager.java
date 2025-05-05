package com.google.android.tv.ads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.internal.atv_ads_framework.zza;
import com.google.android.gms.internal.atv_ads_framework.zzad;
import com.google.android.gms.internal.atv_ads_framework.zzf;
import com.google.android.gms.internal.atv_ads_framework.zzm;
import com.google.android.gms.internal.atv_ads_framework.zzn;
import com.google.android.tv.ads.controls.FallbackImageActivity;
import java.util.Iterator;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class AdsControlsManager {
    private final Context zza;
    private CustomFallbackImageRenderer zzb;

    public AdsControlsManager(Context context) {
        context.getClass();
        this.zza = context;
    }

    private final void zza() {
        this.zza.startActivity(new Intent().setClassName(this.zza.getPackageName(), FallbackImageActivity.class.getName()).setFlags(268435456).putExtra("render_error_message", true));
    }

    public void setCustomFallbackImageRenderer(CustomFallbackImageRenderer customFallbackImageRenderer) {
        customFallbackImageRenderer.getClass();
        this.zzb = customFallbackImageRenderer;
    }

    public void handleIconClick(IconClickFallbackImages iconClickFallbackImages) {
        String str;
        String queryParameter;
        iconClickFallbackImages.getClass();
        Iterator<IconClickFallbackImage> it = iconClickFallbackImages.getIconClickFallbackImageList().iterator();
        while (true) {
            if (!it.hasNext()) {
                str = null;
                break;
            }
            IconClickFallbackImage next = it.next();
            String staticResourceUri = next.getStaticResourceUri();
            if (staticResourceUri != null && (queryParameter = Uri.parse(staticResourceUri).getQueryParameter("atvatc")) != null && queryParameter.equals("1")) {
                str = next.getStaticResourceUri();
                break;
            }
        }
        if (str != null) {
            try {
                Intent putExtra = new Intent().setAction("com.google.android.tv.ads.intent.action.LAUNCH_ATC_MENU").setFlags(268435456).putExtra("extra_atc_uri", str).putExtra("extra_publisher_package", this.zza.getPackageName());
                zza zza2 = zza.TV_LAUNCHER;
                int ordinal = zzad.zza(this.zza).ordinal();
                if (ordinal != 0) {
                    if (ordinal == 1) {
                        zzf zza3 = zzf.zza(this.zza);
                        zzm zza4 = zzn.zza();
                        zza4.zza(2);
                        zza4.zzc(3);
                        zza3.zzb((zzn) zza4.zzi());
                        this.zza.startActivity(putExtra.setPackage("com.google.android.apps.tv.launcherx"));
                        return;
                    } else if (ordinal != 2) {
                        if (ordinal == 3 || ordinal == 4 || ordinal == 5) {
                            zzf zza5 = zzf.zza(this.zza);
                            zzm zza6 = zzn.zza();
                            zza6.zza(2);
                            zza6.zzc(3);
                            zza6.zzb(3);
                            zza5.zzb((zzn) zza6.zzi());
                            zza();
                            return;
                        }
                        return;
                    }
                }
                zzf zza7 = zzf.zza(this.zza);
                zzm zza8 = zzn.zza();
                zza8.zza(2);
                zza8.zzc(3);
                zza7.zzb((zzn) zza8.zzi());
                this.zza.startActivity(putExtra.setPackage("com.google.android.tvrecommendations"));
            } catch (ActivityNotFoundException unused) {
                zzf zza9 = zzf.zza(this.zza);
                zzm zza10 = zzn.zza();
                zza10.zza(2);
                zza10.zzc(3);
                zza10.zzb(2);
                zza9.zzb((zzn) zza10.zzi());
                zza();
            }
        } else if (iconClickFallbackImages.getIconClickFallbackImageList().isEmpty()) {
            zzf zza11 = zzf.zza(this.zza);
            zzm zza12 = zzn.zza();
            zza12.zza(2);
            zza12.zzc(2);
            zza12.zzb(6);
            zza11.zzb((zzn) zza12.zzi());
            zza();
        } else if (this.zzb != null) {
            zzf zza13 = zzf.zza(this.zza);
            zzm zza14 = zzn.zza();
            zza14.zza(3);
            zza14.zzc(2);
            zza13.zzb((zzn) zza14.zzi());
            this.zzb.render(iconClickFallbackImages);
        } else {
            this.zza.startActivity(new Intent().setClassName(this.zza.getPackageName(), FallbackImageActivity.class.getName()).setFlags(268435456).putExtra("icon_click_fallback_images", iconClickFallbackImages));
        }
    }
}
