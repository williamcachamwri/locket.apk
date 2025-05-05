package com.locket.Locket.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.locket.Locket.R;

public final class ActivityCameraSplashBinding implements ViewBinding {
    private final ConstraintLayout rootView;

    private ActivityCameraSplashBinding(ConstraintLayout constraintLayout) {
        this.rootView = constraintLayout;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCameraSplashBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ActivityCameraSplashBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_camera_splash, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityCameraSplashBinding bind(View view) {
        if (view != null) {
            return new ActivityCameraSplashBinding((ConstraintLayout) view);
        }
        throw new NullPointerException("rootView");
    }
}
