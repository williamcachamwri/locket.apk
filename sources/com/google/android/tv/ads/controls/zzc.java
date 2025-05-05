package com.google.android.tv.ads.controls;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzc extends AnimatorListenerAdapter {
    final /* synthetic */ SideDrawerFragment zza;

    zzc(SideDrawerFragment sideDrawerFragment) {
        this.zza = sideDrawerFragment;
    }

    public final void onAnimationEnd(Animator animator) {
        this.zza.requireActivity().finish();
    }
}
