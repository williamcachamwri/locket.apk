package com.reactnativecommunity.picker;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSpinner;
import com.facebook.react.uimanager.StateWrapper;

public abstract class FabricEnabledPicker extends AppCompatSpinner {
    /* access modifiers changed from: protected */
    public void setMeasuredHeight(int i) {
    }

    public void setStateWrapper(StateWrapper stateWrapper) {
    }

    public FabricEnabledPicker(Context context) {
        super(context);
    }

    public FabricEnabledPicker(Context context, int i) {
        super(context, i);
    }

    public FabricEnabledPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FabricEnabledPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public FabricEnabledPicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public FabricEnabledPicker(Context context, AttributeSet attributeSet, int i, int i2, Resources.Theme theme) {
        super(context, attributeSet, i, i2, theme);
    }
}
