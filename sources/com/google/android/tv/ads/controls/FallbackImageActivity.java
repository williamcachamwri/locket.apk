package com.google.android.tv.ads.controls;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.gms.internal.atv_ads_framework.zzf;
import com.google.android.gms.internal.atv_ads_framework.zzm;
import com.google.android.gms.internal.atv_ads_framework.zzn;
import com.google.android.tv.ads.IconClickFallbackImage;
import com.google.android.tv.ads.IconClickFallbackImages;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class FallbackImageActivity extends AppCompatActivity {
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        Bundle bundle2 = new Bundle();
        if (extras != null) {
            bundle2.putBoolean("render_error_message", extras.getBoolean("render_error_message"));
            IconClickFallbackImages iconClickFallbackImages = (IconClickFallbackImages) extras.getParcelable("icon_click_fallback_images");
            if (iconClickFallbackImages == null || iconClickFallbackImages.getIconClickFallbackImageList().isEmpty() || iconClickFallbackImages.getIconClickFallbackImageList().get(0).getStaticResourceUri() == null) {
                zzf zza = zzf.zza(this);
                zzm zza2 = zzn.zza();
                zza2.zza(2);
                zza2.zzc(2);
                zza2.zzb(6);
                zza.zzb((zzn) zza2.zzi());
                bundle2.putBoolean("render_error_message", true);
            } else {
                IconClickFallbackImage iconClickFallbackImage = iconClickFallbackImages.getIconClickFallbackImageList().get(0);
                bundle2.putString("wta_uri", iconClickFallbackImage.getStaticResourceUri());
                bundle2.putString("wta_alt_text", iconClickFallbackImage.getAltText());
            }
        } else {
            zzf zza3 = zzf.zza(this);
            zzm zza4 = zzn.zza();
            zza4.zza(2);
            zza4.zzc(2);
            zza4.zzb(5);
            zza3.zzb((zzn) zza4.zzi());
            bundle2.putBoolean("render_error_message", true);
        }
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(16908290, (Class<? extends Fragment>) SideDrawerFragment.class, bundle2).commit();
    }
}
