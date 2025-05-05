package org.reactnative.maskedview;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNCMaskedViewManager extends ViewGroupManager<RNCMaskedView> {
    private static final String REACT_CLASS = "RNCMaskedView";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public RNCMaskedView createViewInstance(ThemedReactContext themedReactContext) {
        return new RNCMaskedView(themedReactContext);
    }

    @ReactProp(name = "androidRenderingMode")
    public void setAndroidRenderingMode(RNCMaskedView rNCMaskedView, String str) {
        if (str != null) {
            rNCMaskedView.setRenderingMode(str);
        }
    }
}
