package com.google.android.tv.ads.controls;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.gms.internal.atv_ads_framework.zzav;
import com.google.android.gms.internal.atv_ads_framework.zzby;
import com.google.android.tv.ads.R;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class SideDrawerFragment extends Fragment {
    public static final /* synthetic */ int zza = 0;
    private ConstraintLayout zzb;
    private ConstraintLayout zzc;
    private ConstraintLayout zzd;
    /* access modifiers changed from: private */
    public ImageView zze;
    private Button zzf;
    private ConstraintLayout zzg;
    private Button zzh;

    public SideDrawerFragment() {
        super(R.layout.fragment_side_drawer);
    }

    /* access modifiers changed from: private */
    public final void zzc() {
        this.zzd.setVisibility(8);
        this.zzg.setVisibility(0);
        this.zzh.requestFocus();
    }

    public float getBackgroundAlpha() {
        return this.zzb.getAlpha();
    }

    public float getDrawerTranslationX() {
        return this.zzc.getTranslationX() / ((float) this.zzc.getWidth());
    }

    public void setBackgroundAlpha(float f) {
        this.zzb.setAlpha(f);
        this.zzb.invalidate();
    }

    public void setDrawerTranslationX(float f) {
        ConstraintLayout constraintLayout = this.zzc;
        constraintLayout.setTranslationX(((float) constraintLayout.getWidth()) * f);
        this.zzc.invalidate();
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        viewGroup.getClass();
        View inflate = layoutInflater.inflate(R.layout.fragment_side_drawer, viewGroup, false);
        ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.side_drawer_base_layout);
        constraintLayout.getClass();
        this.zzb = constraintLayout;
        ConstraintLayout constraintLayout2 = (ConstraintLayout) inflate.findViewById(R.id.side_drawer_frame_layout);
        constraintLayout2.getClass();
        this.zzc = constraintLayout2;
        ConstraintLayout constraintLayout3 = (ConstraintLayout) inflate.findViewById(R.id.why_this_ad_layout);
        constraintLayout3.getClass();
        this.zzd = constraintLayout3;
        ConstraintLayout constraintLayout4 = (ConstraintLayout) inflate.findViewById(R.id.error_message_layout);
        constraintLayout4.getClass();
        this.zzg = constraintLayout4;
        ImageView imageView = (ImageView) inflate.findViewById(R.id.wta_image_view);
        imageView.getClass();
        this.zze = imageView;
        Button button = (Button) inflate.findViewById(R.id.why_this_ad_back_button);
        button.getClass();
        this.zzf = button;
        Button button2 = (Button) inflate.findViewById(R.id.error_message_back_button);
        button2.getClass();
        this.zzh = button2;
        boolean z = requireArguments().getBoolean("render_error_message");
        String string = requireArguments().getString("wta_uri");
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(requireContext(), R.animator.animator_drawer_in);
        animatorSet.setTarget(this);
        animatorSet.start();
        AnimatorSet animatorSet2 = (AnimatorSet) AnimatorInflater.loadAnimator(requireContext(), R.animator.animator_drawer_out);
        animatorSet2.setTarget(this);
        animatorSet2.addListener(new zzc(this));
        this.zzf.setOnClickListener(new zza(animatorSet2));
        this.zzh.setOnClickListener(new zzb(animatorSet2));
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new zzd(this, true, animatorSet2));
        if (z || string == null) {
            zzc();
        } else {
            this.zzd.setVisibility(0);
            this.zzf.requestFocus();
            String zzb2 = zzav.zzb(requireArguments().getString("wta_uri"));
            String string2 = requireArguments().getString("wta_alt_text");
            if (!TextUtils.isEmpty(string2)) {
                this.zze.setContentDescription(string2);
            }
            ((RequestBuilder) ((RequestBuilder) Glide.with((Fragment) this).load(zzby.zza(zzb2, "zTvAdsFrameworkz")).placeholder(getResources().getDrawable(R.drawable.placeholder_image, requireContext().getTheme()))).fitCenter()).into(new zze(this, this.zze));
        }
        return inflate;
    }
}
