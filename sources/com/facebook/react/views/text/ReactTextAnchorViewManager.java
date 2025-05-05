package com.facebook.react.views.text;

import android.text.TextUtils;
import android.view.View;
import androidx.autofill.HintConstants;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.yoga.YogaConstants;
import com.google.firebase.dynamiclinks.DynamicLink;
import io.sentry.ProfilingTraceData;

public abstract class ReactTextAnchorViewManager<T extends View, C extends ReactBaseTextShadowNode> extends BaseViewManager<T, C> {
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private static final String TAG = "ReactTextAnchorViewManager";

    @ReactProp(name = "accessible")
    public void setAccessible(ReactTextView reactTextView, boolean z) {
        reactTextView.setFocusable(z);
    }

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(ReactTextView reactTextView, int i) {
        reactTextView.setNumberOfLines(i);
    }

    @ReactProp(name = "ellipsizeMode")
    public void setEllipsizeMode(ReactTextView reactTextView, String str) {
        if (str == null || str.equals("tail")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.END);
        } else if (str.equals(TtmlNode.TAG_HEAD)) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.START);
        } else if (str.equals("middle")) {
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.MIDDLE);
        } else if (str.equals("clip")) {
            reactTextView.setEllipsizeLocation((TextUtils.TruncateAt) null);
        } else {
            FLog.w(ReactConstants.TAG, "Invalid ellipsizeMode: " + str);
            reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.END);
        }
    }

    @ReactProp(name = "adjustsFontSizeToFit")
    public void setAdjustFontSizeToFit(ReactTextView reactTextView, boolean z) {
        reactTextView.setAdjustFontSizeToFit(z);
    }

    @ReactProp(name = "fontSize")
    public void setFontSize(ReactTextView reactTextView, float f) {
        reactTextView.setFontSize(f);
    }

    @ReactProp(defaultFloat = Float.NaN, name = "letterSpacing")
    public void setLetterSpacing(ReactTextView reactTextView, float f) {
        reactTextView.setLetterSpacing(f);
    }

    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(ReactTextView reactTextView, String str) {
        if (str == null || "auto".equals(str)) {
            reactTextView.setGravityVertical(0);
        } else if (ViewProps.TOP.equals(str)) {
            reactTextView.setGravityVertical(48);
        } else if (ViewProps.BOTTOM.equals(str)) {
            reactTextView.setGravityVertical(80);
        } else if (TtmlNode.CENTER.equals(str)) {
            reactTextView.setGravityVertical(16);
        } else {
            FLog.w(ReactConstants.TAG, "Invalid textAlignVertical: " + str);
            reactTextView.setGravityVertical(0);
        }
    }

    @ReactProp(name = "selectable")
    public void setSelectable(ReactTextView reactTextView, boolean z) {
        reactTextView.setTextIsSelectable(z);
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactTextView reactTextView, Integer num) {
        if (num == null) {
            reactTextView.setHighlightColor(DefaultStyleValuesUtil.getDefaultTextColorHighlight(reactTextView.getContext()));
        } else {
            reactTextView.setHighlightColor(num.intValue());
        }
    }

    @ReactProp(name = "android_hyphenationFrequency")
    public void setAndroidHyphenationFrequency(ReactTextView reactTextView, String str) {
        if (str == null || str.equals("none")) {
            reactTextView.setHyphenationFrequency(0);
        } else if (str.equals("full")) {
            reactTextView.setHyphenationFrequency(2);
        } else if (str.equals(ProfilingTraceData.TRUNCATION_REASON_NORMAL)) {
            reactTextView.setHyphenationFrequency(1);
        } else {
            FLog.w(ReactConstants.TAG, "Invalid android_hyphenationFrequency: " + str);
            reactTextView.setHyphenationFrequency(0);
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactTextView reactTextView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactTextView.setBorderRadius(f);
        } else {
            reactTextView.setBorderRadius(f, i - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactTextView reactTextView, String str) {
        reactTextView.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactTextView reactTextView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        reactTextView.setBorderWidth(SPACING_TYPES[i], f);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactTextView reactTextView, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & ViewCompat.MEASURED_SIZE_MASK);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        reactTextView.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(ReactTextView reactTextView, boolean z) {
        reactTextView.setIncludeFontPadding(z);
    }

    @ReactProp(defaultBoolean = false, name = "disabled")
    public void setDisabled(ReactTextView reactTextView, boolean z) {
        reactTextView.setEnabled(!z);
    }

    @ReactProp(name = "dataDetectorType")
    public void setDataDetectorType(ReactTextView reactTextView, String str) {
        if (str != null) {
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1192969641:
                    if (str.equals(HintConstants.AUTOFILL_HINT_PHONE_NUMBER)) {
                        c = 0;
                        break;
                    }
                    break;
                case 96673:
                    if (str.equals("all")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3321850:
                    if (str.equals(DynamicLink.Builder.KEY_LINK)) {
                        c = 2;
                        break;
                    }
                    break;
                case 96619420:
                    if (str.equals("email")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    reactTextView.setLinkifyMask(4);
                    return;
                case 1:
                    reactTextView.setLinkifyMask(15);
                    return;
                case 2:
                    reactTextView.setLinkifyMask(1);
                    return;
                case 3:
                    reactTextView.setLinkifyMask(2);
                    return;
            }
        }
        reactTextView.setLinkifyMask(0);
    }

    @ReactProp(name = "onInlineViewLayout")
    public void setNotifyOnInlineViewLayout(ReactTextView reactTextView, boolean z) {
        reactTextView.setNotifyOnInlineViewLayout(z);
    }
}
