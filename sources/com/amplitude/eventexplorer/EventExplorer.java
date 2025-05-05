package com.amplitude.eventexplorer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.amplitude.R;
import com.amplitude.api.DeviceInfo;

public class EventExplorer {
    private View bubbleView;
    private String instanceName;

    public EventExplorer(String str) {
        this.instanceName = str;
    }

    public void show(Activity activity) {
        if (this.bubbleView == null) {
            new Handler(Looper.getMainLooper()).post(new EventExplorer$$ExternalSyntheticLambda0(this, activity));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$show$0$com-amplitude-eventexplorer-EventExplorer  reason: not valid java name */
    public /* synthetic */ void m1168lambda$show$0$comamplitudeeventexplorerEventExplorer(Activity activity) {
        WindowManager windowManager = activity.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager.getDefaultDisplay() != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        WindowManager.LayoutParams prepareWindowManagerLayoutParams = prepareWindowManagerLayoutParams(activity, displayMetrics);
        View inflate = activity.getLayoutInflater().inflate(R.layout.amp_bubble_view, (ViewGroup) null);
        this.bubbleView = inflate;
        windowManager.addView(inflate, prepareWindowManagerLayoutParams);
        this.bubbleView.setOnTouchListener(new EventExplorerTouchHandler(windowManager, prepareWindowManagerLayoutParams, this.instanceName));
    }

    private WindowManager.LayoutParams prepareWindowManagerLayoutParams(Context context, DisplayMetrics displayMetrics) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", DeviceInfo.OS_NAME);
        int dimensionPixelSize = identifier > 0 ? resources.getDimensionPixelSize(identifier) : 0;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2;
        layoutParams.format = -3;
        layoutParams.flags = 40;
        layoutParams.y = (displayMetrics.heightPixels - dimensionPixelSize) / 2;
        layoutParams.x = displayMetrics.widthPixels / 2;
        layoutParams.height = -2;
        layoutParams.width = -2;
        return layoutParams;
    }
}
