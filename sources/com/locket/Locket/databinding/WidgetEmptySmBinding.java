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

public final class WidgetEmptySmBinding implements ViewBinding {
    public final ImageView locketProfilePhotoImage;
    public final ImageView locketProfilePlaceholderBg;
    public final ImageView locketProfilePlaceholderIcon;
    public final ImageView locketWidgetEmptyBg;
    public final RelativeLayout locketWidgetEmptyContainer;
    public final FrameLayout locketWidgetEmptyImageContainer;
    public final ImageView locketWidgetEmptyText;
    private final RelativeLayout rootView;

    private WidgetEmptySmBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, RelativeLayout relativeLayout2, FrameLayout frameLayout, ImageView imageView5) {
        this.rootView = relativeLayout;
        this.locketProfilePhotoImage = imageView;
        this.locketProfilePlaceholderBg = imageView2;
        this.locketProfilePlaceholderIcon = imageView3;
        this.locketWidgetEmptyBg = imageView4;
        this.locketWidgetEmptyContainer = relativeLayout2;
        this.locketWidgetEmptyImageContainer = frameLayout;
        this.locketWidgetEmptyText = imageView5;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static WidgetEmptySmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static WidgetEmptySmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.widget_empty_sm, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static WidgetEmptySmBinding bind(View view) {
        int i = R.id.locket_profile_photo_image;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
        if (imageView != null) {
            i = R.id.locket_profile_placeholder_bg;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i);
            if (imageView2 != null) {
                i = R.id.locket_profile_placeholder_icon;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView3 != null) {
                    i = R.id.locket_widget_empty_bg;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, i);
                    if (imageView4 != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) view;
                        i = R.id.locket_widget_empty_image_container;
                        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i);
                        if (frameLayout != null) {
                            i = R.id.locket_widget_empty_text;
                            ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, i);
                            if (imageView5 != null) {
                                return new WidgetEmptySmBinding(relativeLayout, imageView, imageView2, imageView3, imageView4, relativeLayout, frameLayout, imageView5);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
