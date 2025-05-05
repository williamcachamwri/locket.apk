package com.reactnativecommunity.picker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.reactnativecommunity.picker.ReactPicker;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class ReactPickerManager extends BaseViewManager<ReactPicker, ReactPickerShadowNode> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int BLUR_PICKER = 2;
    private static final ReadableArray EMPTY_ARRAY = Arguments.createArray();
    private static final int FOCUS_PICKER = 1;
    private static final int SET_NATIVE_SELECTED = 3;

    public void updateExtraData(ReactPicker reactPicker, Object obj) {
    }

    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put(PickerItemSelectEvent.EVENT_NAME, MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onSelect", "captured", "onSelectCapture"))).put("topFocus", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onFocus", "captured", "onFocusCapture"))).put("topBlur", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onBlur", "captured", "onBlurCapture"))).build();
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("focus", 1, "blur", 2, "setNativeSelected", 3);
    }

    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, float[] fArr) {
        int i;
        View view;
        ReactPicker reactPicker = new ReactPicker(context);
        ReactPickerAdapter reactPickerAdapter = new ReactPickerAdapter(context, readableMap2.getArray(FirebaseAnalytics.Param.ITEMS));
        int i2 = readableMap2.getInt(ViewProps.NUMBER_OF_LINES);
        if (i2 > 0) {
            reactPickerAdapter.setNumberOfLines(i2);
        }
        int i3 = readableMap2.getInt("selected");
        if (i3 < 0 || i3 >= reactPickerAdapter.getCount()) {
            i = (int) TypedValue.applyDimension(1, 50.0f, Resources.getSystem().getDisplayMetrics());
        } else {
            if ("dropdown".equals(readableMap2.getString("mode"))) {
                view = reactPickerAdapter.getDropDownView(i3, (View) null, reactPicker);
            } else {
                view = reactPickerAdapter.getView(i3, (View) null, reactPicker);
            }
            reactPicker.measureItem(view, View.MeasureSpec.makeMeasureSpec(reactPicker.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            i = view.getMeasuredHeight();
        }
        return YogaMeasureOutput.make(0.0f, PixelUtil.toDIPFromPixel((float) i));
    }

    @ReactProp(name = "items")
    public void setItems(ReactPicker reactPicker, @Nullable ReadableArray readableArray) {
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) reactPicker.getAdapter();
        if (reactPickerAdapter == null) {
            ReactPickerAdapter reactPickerAdapter2 = new ReactPickerAdapter(reactPicker.getContext(), readableArray);
            reactPickerAdapter2.setPrimaryTextColor(reactPicker.getPrimaryColor());
            reactPicker.setAdapter((SpinnerAdapter) reactPickerAdapter2);
            return;
        }
        reactPickerAdapter.setItems(readableArray);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactPicker reactPicker, @Nullable Integer num) {
        reactPicker.setPrimaryColor(num);
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) reactPicker.getAdapter();
        if (reactPickerAdapter != null) {
            reactPickerAdapter.setPrimaryTextColor(num);
        }
    }

    @ReactProp(name = "prompt")
    public void setPrompt(ReactPicker reactPicker, @Nullable String str) {
        reactPicker.setPrompt(str);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactPicker reactPicker, boolean z) {
        reactPicker.setEnabled(z);
    }

    @ReactProp(name = "selected")
    public void setSelected(ReactPicker reactPicker, int i) {
        reactPicker.setStagedSelection(i);
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(ReactPicker reactPicker, @Nullable int i) {
        reactPicker.setBackgroundColor(i);
    }

    @ReactProp(name = "dropdownIconColor")
    public void setDropdownIconColor(ReactPicker reactPicker, @Nullable int i) {
        reactPicker.setDropdownIconColor(i);
    }

    @ReactProp(name = "dropdownIconRippleColor")
    public void setDropdownIconRippleColor(ReactPicker reactPicker, @Nullable int i) {
        reactPicker.setDropdownIconRippleColor(i);
    }

    @ReactProp(defaultInt = 1, name = "numberOfLines")
    public void setNumberOfLines(ReactPicker reactPicker, int i) {
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) reactPicker.getAdapter();
        if (reactPickerAdapter == null) {
            ReactPickerAdapter reactPickerAdapter2 = new ReactPickerAdapter(reactPicker.getContext(), EMPTY_ARRAY);
            reactPickerAdapter2.setPrimaryTextColor(reactPicker.getPrimaryColor());
            reactPickerAdapter2.setNumberOfLines(i);
            reactPicker.setAdapter((SpinnerAdapter) reactPickerAdapter2);
            return;
        }
        reactPickerAdapter.setNumberOfLines(i);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(ReactPicker reactPicker) {
        super.onAfterUpdateTransaction(reactPicker);
        reactPicker.updateStagedSelection();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactPicker reactPicker) {
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, reactPicker.getId());
        if (eventDispatcherForReactTag != null) {
            PickerEventEmitter pickerEventEmitter = new PickerEventEmitter(reactPicker, eventDispatcherForReactTag);
            reactPicker.setOnSelectListener(pickerEventEmitter);
            reactPicker.setOnFocusListener(pickerEventEmitter);
        }
    }

    public void receiveCommand(ReactPicker reactPicker, int i, ReadableArray readableArray) {
        if (i == 1) {
            reactPicker.performClick();
        } else if (i == 2) {
            reactPicker.clearFocus();
        } else if (i == 3) {
            Assertions.assertNotNull(readableArray);
            setNativeSelected(reactPicker, readableArray.getInt(0));
        }
    }

    public void receiveCommand(ReactPicker reactPicker, String str, ReadableArray readableArray) {
        Assertions.assertNotNull(reactPicker);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 3027047:
                if (str.equals("blur")) {
                    c = 0;
                    break;
                }
                break;
            case 97604824:
                if (str.equals("focus")) {
                    c = 1;
                    break;
                }
                break;
            case 361157844:
                if (str.equals("setNativeSelected")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                blur(reactPicker);
                return;
            case 1:
                focus(reactPicker);
                return;
            case 2:
                Assertions.assertNotNull(readableArray);
                setNativeSelected(reactPicker, readableArray.getInt(0));
                return;
            default:
                return;
        }
    }

    public void focus(ReactPicker reactPicker) {
        reactPicker.performClick();
    }

    public void blur(ReactPicker reactPicker) {
        reactPicker.clearFocus();
    }

    public void setNativeSelected(ReactPicker reactPicker, int i) {
        reactPicker.setStagedSelection(i);
    }

    public ReactPickerShadowNode createShadowNodeInstance() {
        return new ReactPickerShadowNode();
    }

    public Class<? extends ReactPickerShadowNode> getShadowNodeClass() {
        return ReactPickerShadowNode.class;
    }

    public Object updateState(ReactPicker reactPicker, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        reactPicker.setStateWrapper(stateWrapper);
        return null;
    }

    private static class ReactPickerAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        @Nullable
        private ReadableArray mItems;
        private int mNumberOfLines = 1;
        @Nullable
        private Integer mPrimaryTextColor;

        public long getItemId(int i) {
            return (long) i;
        }

        public ReactPickerAdapter(Context context, @Nullable ReadableArray readableArray) {
            this.mItems = readableArray;
            this.mInflater = (LayoutInflater) Assertions.assertNotNull(context.getSystemService("layout_inflater"));
        }

        public void setItems(@Nullable ReadableArray readableArray) {
            this.mItems = readableArray;
            notifyDataSetChanged();
        }

        public int getCount() {
            ReadableArray readableArray = this.mItems;
            if (readableArray == null) {
                return 0;
            }
            return readableArray.size();
        }

        public ReadableMap getItem(int i) {
            ReadableArray readableArray = this.mItems;
            if (readableArray == null) {
                return null;
            }
            return readableArray.getMap(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return getView(i, view, viewGroup, false);
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return getView(i, view, viewGroup, true);
        }

        private View getView(int i, View view, ViewGroup viewGroup, boolean z) {
            Integer num;
            int i2;
            ReadableMap item = getItem(i);
            ReadableMap map = item.hasKey(TtmlNode.TAG_STYLE) ? item.getMap(TtmlNode.TAG_STYLE) : null;
            if (view == null) {
                if (z) {
                    i2 = R.layout.simple_spinner_dropdown_item;
                } else {
                    i2 = R.layout.simple_spinner_item;
                }
                view = this.mInflater.inflate(i2, viewGroup, false);
            }
            boolean z2 = item.hasKey(ViewProps.ENABLED) ? item.getBoolean(ViewProps.ENABLED) : true;
            view.setEnabled(z2);
            view.setClickable(!z2);
            TextView textView = (TextView) view;
            textView.setText(item.getString(Constants.ScionAnalytics.PARAM_LABEL));
            textView.setMaxLines(this.mNumberOfLines);
            if (map != null) {
                if (!map.hasKey("backgroundColor") || map.isNull("backgroundColor")) {
                    view.setBackgroundColor(0);
                } else {
                    view.setBackgroundColor(map.getInt("backgroundColor"));
                }
                if (map.hasKey("color") && !map.isNull("color")) {
                    textView.setTextColor(map.getInt("color"));
                }
                if (map.hasKey("fontSize") && !map.isNull("fontSize") && map.getDouble("fontSize") > 0.1d) {
                    textView.setTextSize((float) map.getDouble("fontSize"));
                }
                if (map.hasKey("fontFamily") && !map.isNull("fontFamily") && map.getString("fontFamily").length() > 0) {
                    textView.setTypeface(Typeface.create(map.getString("fontFamily"), 0));
                }
            }
            if (!z && (num = this.mPrimaryTextColor) != null) {
                textView.setTextColor(num.intValue());
            } else if (item.hasKey("color") && !item.isNull("color")) {
                textView.setTextColor(item.getInt("color"));
            }
            if (item.hasKey("contentDescription") && !item.isNull("contentDescription")) {
                textView.setContentDescription(item.getString("contentDescription"));
            }
            if (item.hasKey("fontFamily") && !item.isNull("fontFamily")) {
                textView.setTypeface(Typeface.create(item.getString("fontFamily"), 0));
            }
            if (I18nUtil.getInstance().isRTL(view.getContext())) {
                view.setLayoutDirection(1);
                view.setTextDirection(4);
            } else {
                view.setLayoutDirection(0);
                view.setTextDirection(3);
            }
            return view;
        }

        public void setPrimaryTextColor(@Nullable Integer num) {
            this.mPrimaryTextColor = num;
            notifyDataSetChanged();
        }

        public void setNumberOfLines(int i) {
            this.mNumberOfLines = i;
            notifyDataSetChanged();
        }
    }

    private static class PickerEventEmitter implements ReactPicker.OnSelectListener, ReactPicker.OnFocusListener {
        private final EventDispatcher mEventDispatcher;
        private final ReactPicker mReactPicker;

        public PickerEventEmitter(ReactPicker reactPicker, EventDispatcher eventDispatcher) {
            this.mReactPicker = reactPicker;
            this.mEventDispatcher = eventDispatcher;
        }

        public void onItemSelected(int i) {
            this.mEventDispatcher.dispatchEvent(new PickerItemSelectEvent(this.mReactPicker.getId(), i));
        }

        public void onPickerBlur() {
            this.mEventDispatcher.dispatchEvent(new PickerBlurEvent(this.mReactPicker.getId()));
        }

        public void onPickerFocus() {
            this.mEventDispatcher.dispatchEvent(new PickerFocusEvent(this.mReactPicker.getId()));
        }
    }
}
