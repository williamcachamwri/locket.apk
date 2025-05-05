package com.google.android.tv.ads.controls;

import android.animation.AnimatorSet;
import android.view.View;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final /* synthetic */ class zzb implements View.OnClickListener {
    public final /* synthetic */ AnimatorSet zza;

    public /* synthetic */ zzb(AnimatorSet animatorSet) {
        this.zza = animatorSet;
    }

    public final void onClick(View view) {
        AnimatorSet animatorSet = this.zza;
        int i = SideDrawerFragment.zza;
        animatorSet.start();
    }
}
