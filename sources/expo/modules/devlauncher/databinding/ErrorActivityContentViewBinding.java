package expo.modules.devlauncher.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager.widget.ViewPager;
import expo.modules.devlauncher.R;

public final class ErrorActivityContentViewBinding implements ViewBinding {
    public final ViewPager errorViewPager;
    private final ViewPager rootView;

    private ErrorActivityContentViewBinding(ViewPager viewPager, ViewPager viewPager2) {
        this.rootView = viewPager;
        this.errorViewPager = viewPager2;
    }

    public ViewPager getRoot() {
        return this.rootView;
    }

    public static ErrorActivityContentViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ErrorActivityContentViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.error_activity_content_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ErrorActivityContentViewBinding bind(View view) {
        if (view != null) {
            ViewPager viewPager = (ViewPager) view;
            return new ErrorActivityContentViewBinding(viewPager, viewPager);
        }
        throw new NullPointerException("rootView");
    }
}
