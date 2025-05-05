package expo.modules.devlauncher.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import expo.modules.devlauncher.R;

public final class ErrorConsoleListItemBinding implements ViewBinding {
    public final TextView rnFrameFile;
    public final TextView rnFrameMethod;
    private final LinearLayout rootView;

    private ErrorConsoleListItemBinding(LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.rnFrameFile = textView;
        this.rnFrameMethod = textView2;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ErrorConsoleListItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ErrorConsoleListItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.error_console_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ErrorConsoleListItemBinding bind(View view) {
        int i = R.id.rn_frame_file;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.rn_frame_method;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i);
            if (textView2 != null) {
                return new ErrorConsoleListItemBinding((LinearLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
