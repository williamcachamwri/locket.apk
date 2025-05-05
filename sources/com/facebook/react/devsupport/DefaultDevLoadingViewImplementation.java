package com.facebook.react.devsupport;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import java.util.Locale;

public class DefaultDevLoadingViewImplementation implements DevLoadingViewManager {
    private static boolean sEnabled = true;
    private PopupWindow mDevLoadingPopup;
    /* access modifiers changed from: private */
    public TextView mDevLoadingView;
    private final ReactInstanceDevHelper mReactInstanceDevHelper;

    public static void setDevLoadingEnabled(boolean z) {
        sEnabled = z;
    }

    public DefaultDevLoadingViewImplementation(ReactInstanceDevHelper reactInstanceDevHelper) {
        this.mReactInstanceDevHelper = reactInstanceDevHelper;
    }

    public void showMessage(final String str) {
        if (sEnabled) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    DefaultDevLoadingViewImplementation.this.showInternal(str);
                }
            });
        }
    }

    public void updateProgress(final String str, final Integer num, final Integer num2) {
        if (sEnabled) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    Integer num;
                    StringBuilder sb = new StringBuilder();
                    String str = str;
                    if (str == null) {
                        str = "Loading";
                    }
                    sb.append(str);
                    if (!(num == null || (num = num2) == null || num.intValue() <= 0)) {
                        sb.append(String.format(Locale.getDefault(), " %.1f%%", new Object[]{Float.valueOf((((float) num.intValue()) / ((float) num2.intValue())) * 100.0f)}));
                    }
                    sb.append("…");
                    if (DefaultDevLoadingViewImplementation.this.mDevLoadingView != null) {
                        DefaultDevLoadingViewImplementation.this.mDevLoadingView.setText(sb);
                    }
                }
            });
        }
    }

    public void hide() {
        if (sEnabled) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    DefaultDevLoadingViewImplementation.this.hideInternal();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showInternal(String str) {
        PopupWindow popupWindow = this.mDevLoadingPopup;
        if (popupWindow == null || !popupWindow.isShowing()) {
            Activity currentActivity = this.mReactInstanceDevHelper.getCurrentActivity();
            if (currentActivity == null) {
                FLog.e(ReactConstants.TAG, "Unable to display loading message because react activity isn't available");
                return;
            }
            try {
                Rect rect = new Rect();
                currentActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int i = rect.top;
                TextView textView = (TextView) ((LayoutInflater) currentActivity.getSystemService("layout_inflater")).inflate(R.layout.dev_loading_view, (ViewGroup) null);
                this.mDevLoadingView = textView;
                textView.setText(str);
                PopupWindow popupWindow2 = new PopupWindow(this.mDevLoadingView, -1, -2);
                this.mDevLoadingPopup = popupWindow2;
                popupWindow2.setTouchable(false);
                this.mDevLoadingPopup.showAtLocation(currentActivity.getWindow().getDecorView(), 0, 0, i);
            } catch (WindowManager.BadTokenException unused) {
                FLog.e(ReactConstants.TAG, "Unable to display loading message because react activity isn't active, message: " + str);
            }
        }
    }

    /* access modifiers changed from: private */
    public void hideInternal() {
        PopupWindow popupWindow = this.mDevLoadingPopup;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.mDevLoadingPopup.dismiss();
            this.mDevLoadingPopup = null;
            this.mDevLoadingView = null;
        }
    }

    private Context getContext() {
        return this.mReactInstanceDevHelper.getCurrentActivity();
    }
}
