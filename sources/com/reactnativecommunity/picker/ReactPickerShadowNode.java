package com.reactnativecommunity.picker;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.LayoutShadowNode;

public class ReactPickerShadowNode extends LayoutShadowNode {
    public void setLocalData(Object obj) {
        Assertions.assertCondition(obj instanceof ReactPickerLocalData);
        setStyleMinHeight((float) ((ReactPickerLocalData) obj).getHeight());
    }
}
