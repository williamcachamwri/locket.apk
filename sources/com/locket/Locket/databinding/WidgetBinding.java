package com.locket.Locket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.locket.Locket.R;

public final class WidgetBinding implements ViewBinding {
    public final ImageView locketCaptionView;
    public final ImageView locketFrame;
    public final FrameLayout locketFrameView;
    public final ImageView locketImageView;
    public final ImageView locketMissedMomentsBadge;
    public final ImageView locketMissedMomentsBadgeMax;
    public final ImageView locketOverlayGradientBgView;
    public final ImageView locketOverlayTextView;
    public final ImageView locketProfileImageBorder;
    public final ImageView locketProfileImageView;
    public final ImageView locketProfilePlaceholderBg;
    public final FrameLayout locketProfileView;
    public final ImageView locketRemoteFrame;
    public final FrameLayout locketStreakFrame;
    public final ImageView locketStreakIcon;
    public final ImageView locketStreakText;
    private final RelativeLayout rootView;

    private WidgetBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, FrameLayout frameLayout, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, FrameLayout frameLayout2, ImageView imageView11, FrameLayout frameLayout3, ImageView imageView12, ImageView imageView13) {
        this.rootView = relativeLayout;
        this.locketCaptionView = imageView;
        this.locketFrame = imageView2;
        this.locketFrameView = frameLayout;
        this.locketImageView = imageView3;
        this.locketMissedMomentsBadge = imageView4;
        this.locketMissedMomentsBadgeMax = imageView5;
        this.locketOverlayGradientBgView = imageView6;
        this.locketOverlayTextView = imageView7;
        this.locketProfileImageBorder = imageView8;
        this.locketProfileImageView = imageView9;
        this.locketProfilePlaceholderBg = imageView10;
        this.locketProfileView = frameLayout2;
        this.locketRemoteFrame = imageView11;
        this.locketStreakFrame = frameLayout3;
        this.locketStreakIcon = imageView12;
        this.locketStreakText = imageView13;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static WidgetBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static WidgetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.widget, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static WidgetBinding bind(View view) {
        View view2 = view;
        int i = R.id.locket_caption_view;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view2, i);
        if (imageView != null) {
            i = R.id.locket_frame;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view2, i);
            if (imageView2 != null) {
                i = R.id.locket_frame_view;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view2, i);
                if (frameLayout != null) {
                    i = R.id.locket_image_view;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view2, i);
                    if (imageView3 != null) {
                        i = R.id.locket_missed_moments_badge;
                        ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view2, i);
                        if (imageView4 != null) {
                            i = R.id.locket_missed_moments_badge_max;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view2, i);
                            if (imageView5 != null) {
                                i = R.id.locket_overlay_gradient_bg_view;
                                ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view2, i);
                                if (imageView6 != null) {
                                    i = R.id.locket_overlay_text_view;
                                    ImageView imageView7 = (ImageView) ViewBindings.findChildViewById(view2, i);
                                    if (imageView7 != null) {
                                        i = R.id.locket_profile_image_border;
                                        ImageView imageView8 = (ImageView) ViewBindings.findChildViewById(view2, i);
                                        if (imageView8 != null) {
                                            i = R.id.locket_profile_image_view;
                                            ImageView imageView9 = (ImageView) ViewBindings.findChildViewById(view2, i);
                                            if (imageView9 != null) {
                                                i = R.id.locket_profile_placeholder_bg;
                                                ImageView imageView10 = (ImageView) ViewBindings.findChildViewById(view2, i);
                                                if (imageView10 != null) {
                                                    i = R.id.locket_profile_view;
                                                    FrameLayout frameLayout2 = (FrameLayout) ViewBindings.findChildViewById(view2, i);
                                                    if (frameLayout2 != null) {
                                                        i = R.id.locket_remote_frame;
                                                        ImageView imageView11 = (ImageView) ViewBindings.findChildViewById(view2, i);
                                                        if (imageView11 != null) {
                                                            i = R.id.locket_streak_frame;
                                                            FrameLayout frameLayout3 = (FrameLayout) ViewBindings.findChildViewById(view2, i);
                                                            if (frameLayout3 != null) {
                                                                i = R.id.locket_streak_icon;
                                                                ImageView imageView12 = (ImageView) ViewBindings.findChildViewById(view2, i);
                                                                if (imageView12 != null) {
                                                                    i = R.id.locket_streak_text;
                                                                    ImageView imageView13 = (ImageView) ViewBindings.findChildViewById(view2, i);
                                                                    if (imageView13 != null) {
                                                                        return new WidgetBinding((RelativeLayout) view2, imageView, imageView2, frameLayout, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, frameLayout2, imageView11, frameLayout3, imageView12, imageView13);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
