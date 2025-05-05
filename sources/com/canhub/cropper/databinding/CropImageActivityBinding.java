package com.canhub.cropper.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import com.canhub.cropper.CropImageView;
import com.canhub.cropper.R;

public final class CropImageActivityBinding implements ViewBinding {
    public final CropImageView cropImageView;
    private final CropImageView rootView;

    private CropImageActivityBinding(CropImageView cropImageView2, CropImageView cropImageView3) {
        this.rootView = cropImageView2;
        this.cropImageView = cropImageView3;
    }

    public CropImageView getRoot() {
        return this.rootView;
    }

    public static CropImageActivityBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static CropImageActivityBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.crop_image_activity, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static CropImageActivityBinding bind(View view) {
        if (view != null) {
            CropImageView cropImageView2 = (CropImageView) view;
            return new CropImageActivityBinding(cropImageView2, cropImageView2);
        }
        throw new NullPointerException("rootView");
    }
}
