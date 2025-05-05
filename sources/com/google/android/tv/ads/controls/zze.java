package com.google.android.tv.ads.controls;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.internal.atv_ads_framework.zzf;
import com.google.android.gms.internal.atv_ads_framework.zzm;
import com.google.android.gms.internal.atv_ads_framework.zzn;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zze extends CustomViewTarget {
    final /* synthetic */ SideDrawerFragment zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zze(SideDrawerFragment sideDrawerFragment, ImageView imageView) {
        super(imageView);
        this.zza = sideDrawerFragment;
    }

    public final void onLoadFailed(Drawable drawable) {
        zzf zza2 = zzf.zza(this.zza.requireContext());
        zzm zza3 = zzn.zza();
        zza3.zza(2);
        zza3.zzc(2);
        zza3.zzb(4);
        zza2.zzb((zzn) zza3.zzi());
        this.zza.zzc();
    }

    /* access modifiers changed from: protected */
    public final void onResourceCleared(Drawable drawable) {
        this.zza.zze.setImageDrawable(drawable);
    }

    public final /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
        zzf zza2 = zzf.zza(this.zza.requireContext());
        zzm zza3 = zzn.zza();
        zza3.zza(2);
        zza3.zzc(2);
        zza2.zzb((zzn) zza3.zzi());
        this.zza.zze.setImageDrawable((Drawable) obj);
    }
}
