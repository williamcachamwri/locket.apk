package expo.modules.devlauncher.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import expo.modules.devlauncher.R;

public final class ErrorFragmentBinding implements ViewBinding {
    public final TextView errorDetails;
    public final LinearLayout errorFooter;
    public final LinearLayout errorMainContent;
    public final ListView errorStack;
    public final Button homeButton;
    public final Button reloadButton;
    private final RelativeLayout rootView;

    private ErrorFragmentBinding(RelativeLayout relativeLayout, TextView textView, LinearLayout linearLayout, LinearLayout linearLayout2, ListView listView, Button button, Button button2) {
        this.rootView = relativeLayout;
        this.errorDetails = textView;
        this.errorFooter = linearLayout;
        this.errorMainContent = linearLayout2;
        this.errorStack = listView;
        this.homeButton = button;
        this.reloadButton = button2;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ErrorFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ErrorFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.error_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ErrorFragmentBinding bind(View view) {
        int i = R.id.errorDetails;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i);
        if (textView != null) {
            i = R.id.error_footer;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i);
            if (linearLayout != null) {
                i = R.id.error_main_content;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i);
                if (linearLayout2 != null) {
                    i = R.id.error_stack;
                    ListView listView = (ListView) ViewBindings.findChildViewById(view, i);
                    if (listView != null) {
                        i = R.id.homeButton;
                        Button button = (Button) ViewBindings.findChildViewById(view, i);
                        if (button != null) {
                            i = R.id.reloadButton;
                            Button button2 = (Button) ViewBindings.findChildViewById(view, i);
                            if (button2 != null) {
                                return new ErrorFragmentBinding((RelativeLayout) view, textView, linearLayout, linearLayout2, listView, button, button2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
