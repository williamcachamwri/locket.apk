package com.canhub.cropper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.canhub.cropper.CropOverlayView;
import com.canhub.cropper.R;

public final class CropImageViewBinding implements ViewBinding {
    public final CropOverlayView CropOverlayView;
    public final ProgressBar CropProgressBar;
    public final ImageView ImageViewImage;
    private final View rootView;

    private CropImageViewBinding(View view, CropOverlayView cropOverlayView, ProgressBar progressBar, ImageView imageView) {
        this.rootView = view;
        this.CropOverlayView = cropOverlayView;
        this.CropProgressBar = progressBar;
        this.ImageViewImage = imageView;
    }

    public View getRoot() {
        return this.rootView;
    }

    public static CropImageViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        if (viewGroup != null) {
            layoutInflater.inflate(R.layout.crop_image_view, viewGroup);
            return bind(viewGroup);
        }
        throw new NullPointerException("parent");
    }

    public static CropImageViewBinding bind(View view) {
        int i = R.id.CropOverlayView;
        CropOverlayView cropOverlayView = (CropOverlayView) ViewBindings.findChildViewById(view, i);
        if (cropOverlayView != null) {
            i = R.id.CropProgressBar;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, i);
            if (progressBar != null) {
                i = R.id.ImageView_image;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i);
                if (imageView != null) {
                    return new CropImageViewBinding(view, cropOverlayView, progressBar, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
