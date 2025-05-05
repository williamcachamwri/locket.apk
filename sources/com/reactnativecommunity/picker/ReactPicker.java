package com.reactnativecommunity.picker;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.UIManagerModule;
import javax.annotation.Nullable;

public class ReactPicker extends FabricEnabledPicker {
    private boolean mIsOpen = false;
    private final AdapterView.OnItemSelectedListener mItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (ReactPicker.this.mOnSelectListener != null) {
                ReactPicker.this.mOnSelectListener.onItemSelected(i);
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            if (ReactPicker.this.mOnSelectListener != null) {
                ReactPicker.this.mOnSelectListener.onItemSelected(-1);
            }
        }
    };
    private int mMode = 0;
    private int mOldElementSize = Integer.MIN_VALUE;
    @Nullable
    private OnFocusListener mOnFocusListener;
    /* access modifiers changed from: private */
    @Nullable
    public OnSelectListener mOnSelectListener;
    @Nullable
    private Integer mPrimaryColor;
    @Nullable
    private Integer mStagedSelection;
    private final Runnable measureAndLayout = new Runnable() {
        public void run() {
            ReactPicker reactPicker = ReactPicker.this;
            reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
            ReactPicker reactPicker2 = ReactPicker.this;
            reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
        }
    };

    public interface OnFocusListener {
        void onPickerBlur();

        void onPickerFocus();
    }

    public interface OnSelectListener {
        void onItemSelected(int i);
    }

    public ReactPicker(Context context) {
        super(context);
        handleRTL(context);
        setSpinnerBackground();
    }

    public ReactPicker(Context context, int i) {
        super(context, i);
        this.mMode = i;
        handleRTL(context);
        setSpinnerBackground();
    }

    public ReactPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        handleRTL(context);
        setSpinnerBackground();
    }

    public ReactPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        handleRTL(context);
        setSpinnerBackground();
    }

    public ReactPicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mMode = i2;
        handleRTL(context);
        setSpinnerBackground();
    }

    private void setSpinnerBackground() {
        setBackgroundResource(R.drawable.spinner_dropdown_background);
        setBackgroundColor(0);
    }

    private void handleRTL(Context context) {
        if (I18nUtil.getInstance().isRTL(context)) {
            setLayoutDirection(1);
            setTextDirection(4);
            return;
        }
        setLayoutDirection(0);
        setTextDirection(3);
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.measureAndLayout);
    }

    public boolean performClick() {
        this.mIsOpen = true;
        OnFocusListener onFocusListener = this.mOnFocusListener;
        if (onFocusListener != null) {
            onFocusListener.onPickerFocus();
        }
        return super.performClick();
    }

    public void onWindowFocusChanged(boolean z) {
        if (this.mIsOpen && z) {
            this.mIsOpen = false;
            OnFocusListener onFocusListener = this.mOnFocusListener;
            if (onFocusListener != null) {
                onFocusListener.onPickerBlur();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (getOnItemSelectedListener() == null) {
            setOnItemSelectedListener(this.mItemSelectedListener);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        int selectedItemPosition = getSelectedItemPosition();
        if (selectedItemPosition < 0 || getAdapter() == null || selectedItemPosition >= getAdapter().getCount()) {
            i3 = (int) TypedValue.applyDimension(1, 50.0f, Resources.getSystem().getDisplayMetrics());
        } else {
            View view = getAdapter().getView(selectedItemPosition, (View) null, this);
            measureChild(view, View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            i3 = view.getMeasuredHeight();
        }
        if (i3 != this.mOldElementSize) {
            UIManagerModule uIManagerModule = (UIManagerModule) getReactContext().getNativeModule(UIManagerModule.class);
            if (uIManagerModule != null) {
                uIManagerModule.setViewLocalData(getId(), new ReactPickerLocalData(i3));
            }
            this.mOldElementSize = i3;
            setMeasuredHeight(i3);
        }
    }

    public void measureItem(View view, int i, int i2) {
        measureChild(view, i, i2);
    }

    public void clearFocus() {
        super.setFocusableInTouchMode(true);
        super.setFocusable(true);
        super.onDetachedFromWindow();
    }

    public void setOnSelectListener(@Nullable OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    public void setOnFocusListener(@Nullable OnFocusListener onFocusListener) {
        this.mOnFocusListener = onFocusListener;
    }

    @Nullable
    public OnSelectListener getOnSelectListener() {
        return this.mOnSelectListener;
    }

    @Nullable
    public OnFocusListener getOnFocusListener() {
        return this.mOnFocusListener;
    }

    public void setStagedSelection(int i) {
        this.mStagedSelection = Integer.valueOf(i);
    }

    public void updateStagedSelection() {
        Integer num = this.mStagedSelection;
        if (num != null) {
            setSelectionWithSuppressEvent(num.intValue());
            this.mStagedSelection = null;
        }
    }

    private void setSelectionWithSuppressEvent(int i) {
        if (i != getSelectedItemPosition()) {
            setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
            setSelection(i, false);
            setOnItemSelectedListener(this.mItemSelectedListener);
        }
    }

    @Nullable
    public Integer getPrimaryColor() {
        return this.mPrimaryColor;
    }

    public void setPrimaryColor(@Nullable Integer num) {
        this.mPrimaryColor = num;
    }

    public void setDropdownIconColor(@Nullable int i) {
        ((RippleDrawable) ((LayerDrawable) getBackground()).findDrawableByLayerId(R.id.dropdown_icon)).setColorFilter(i, PorterDuff.Mode.SRC_ATOP);
    }

    public void setDropdownIconRippleColor(@Nullable int i) {
        ((RippleDrawable) ((LayerDrawable) getBackground()).findDrawableByLayerId(R.id.dropdown_icon)).setColor(ColorStateList.valueOf(i));
    }

    public void setBackgroundColor(@Nullable int i) {
        ((GradientDrawable) ((LayerDrawable) getBackground()).findDrawableByLayerId(R.id.dropdown_background)).setColor(i);
    }

    @VisibleForTesting
    public int getMode() {
        return this.mMode;
    }

    private ReactContext getReactContext() {
        Context context = getContext();
        if (!(context instanceof ReactContext) && (context instanceof ContextWrapper)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (ReactContext) context;
    }
}
