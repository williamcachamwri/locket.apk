package com.facebook.react.views.modal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.JSPointerDispatcher;
import com.facebook.react.uimanager.JSTouchDispatcher;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.common.ContextUtils;
import com.facebook.react.views.view.ReactViewGroup;
import java.util.ArrayList;

public class ReactModalHostView extends ViewGroup implements LifecycleEventListener {
    private static final String TAG = "ReactModalHost";
    private String mAnimationType;
    private Dialog mDialog;
    private boolean mHardwareAccelerated;
    private DialogRootViewGroup mHostView;
    /* access modifiers changed from: private */
    public OnRequestCloseListener mOnRequestCloseListener;
    private DialogInterface.OnShowListener mOnShowListener;
    private boolean mPropertyRequiresNewDialog;
    private boolean mStatusBarTranslucent;
    private boolean mTransparent;

    public interface OnRequestCloseListener {
        void onRequestClose(DialogInterface dialogInterface);
    }

    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public void onHostPause() {
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    public ReactModalHostView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        themedReactContext.addLifecycleEventListener(this);
        this.mHostView = new DialogRootViewGroup(themedReactContext);
    }

    public void dispatchProvideStructure(ViewStructure viewStructure) {
        this.mHostView.dispatchProvideStructure(viewStructure);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismiss();
    }

    public void addView(View view, int i) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.addView(view, i);
    }

    public int getChildCount() {
        DialogRootViewGroup dialogRootViewGroup = this.mHostView;
        if (dialogRootViewGroup == null) {
            return 0;
        }
        return dialogRootViewGroup.getChildCount();
    }

    public View getChildAt(int i) {
        return this.mHostView.getChildAt(i);
    }

    public void removeView(View view) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.removeView(view);
    }

    public void removeViewAt(int i) {
        UiThreadUtil.assertOnUiThread();
        this.mHostView.removeView(getChildAt(i));
    }

    public void onDropInstance() {
        ((ThemedReactContext) getContext()).removeLifecycleEventListener(this);
        dismiss();
    }

    private void dismiss() {
        Activity activity;
        UiThreadUtil.assertOnUiThread();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            if (dialog.isShowing() && ((activity = (Activity) ContextUtils.findContextOfType(this.mDialog.getContext(), Activity.class)) == null || !activity.isFinishing())) {
                this.mDialog.dismiss();
            }
            this.mDialog = null;
            ((ViewGroup) this.mHostView.getParent()).removeViewAt(0);
        }
    }

    /* access modifiers changed from: protected */
    public void setOnRequestCloseListener(OnRequestCloseListener onRequestCloseListener) {
        this.mOnRequestCloseListener = onRequestCloseListener;
    }

    /* access modifiers changed from: protected */
    public void setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.mOnShowListener = onShowListener;
    }

    /* access modifiers changed from: protected */
    public void setTransparent(boolean z) {
        this.mTransparent = z;
    }

    /* access modifiers changed from: protected */
    public void setStatusBarTranslucent(boolean z) {
        this.mStatusBarTranslucent = z;
        this.mPropertyRequiresNewDialog = true;
    }

    /* access modifiers changed from: protected */
    public void setAnimationType(String str) {
        this.mAnimationType = str;
        this.mPropertyRequiresNewDialog = true;
    }

    /* access modifiers changed from: protected */
    public void setHardwareAccelerated(boolean z) {
        this.mHardwareAccelerated = z;
        this.mPropertyRequiresNewDialog = true;
    }

    /* access modifiers changed from: package-private */
    public void setEventDispatcher(EventDispatcher eventDispatcher) {
        this.mHostView.setEventDispatcher(eventDispatcher);
    }

    public void onHostResume() {
        showOrUpdate();
    }

    public void onHostDestroy() {
        onDropInstance();
    }

    @VisibleForTesting
    public Dialog getDialog() {
        return this.mDialog;
    }

    private Activity getCurrentActivity() {
        return ((ThemedReactContext) getContext()).getCurrentActivity();
    }

    /* access modifiers changed from: protected */
    public void showOrUpdate() {
        UiThreadUtil.assertOnUiThread();
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            Context context = (Context) ContextUtils.findContextOfType(dialog.getContext(), Activity.class);
            FLog.e(TAG, "Updating existing dialog with context: " + context + "@" + context.hashCode());
            if (this.mPropertyRequiresNewDialog) {
                dismiss();
            } else {
                updateProperties();
                return;
            }
        }
        this.mPropertyRequiresNewDialog = false;
        int i = R.style.Theme_FullScreenDialog;
        if (this.mAnimationType.equals("fade")) {
            i = R.style.Theme_FullScreenDialogAnimatedFade;
        } else if (this.mAnimationType.equals("slide")) {
            i = R.style.Theme_FullScreenDialogAnimatedSlide;
        }
        Activity currentActivity = getCurrentActivity();
        Context context2 = currentActivity == null ? getContext() : currentActivity;
        Dialog dialog2 = new Dialog(context2, i);
        this.mDialog = dialog2;
        dialog2.getWindow().setFlags(8, 8);
        FLog.e(TAG, "Creating new dialog from context: " + context2 + "@" + context2.hashCode());
        this.mDialog.setContentView(getContentView());
        updateProperties();
        this.mDialog.setOnShowListener(this.mOnShowListener);
        this.mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 1) {
                    return false;
                }
                if (i == 4 || i == 111) {
                    Assertions.assertNotNull(ReactModalHostView.this.mOnRequestCloseListener, "setOnRequestCloseListener must be called by the manager");
                    ReactModalHostView.this.mOnRequestCloseListener.onRequestClose(dialogInterface);
                    return true;
                }
                Activity currentActivity = ((ReactContext) ReactModalHostView.this.getContext()).getCurrentActivity();
                if (currentActivity != null) {
                    return currentActivity.onKeyUp(i, keyEvent);
                }
                return false;
            }
        });
        this.mDialog.getWindow().setSoftInputMode(16);
        if (this.mHardwareAccelerated) {
            this.mDialog.getWindow().addFlags(16777216);
        }
        if (currentActivity != null && !currentActivity.isFinishing()) {
            this.mDialog.show();
            if (context2 instanceof Activity) {
                if (Build.VERSION.SDK_INT > 30) {
                    int systemBarsAppearance = ((Activity) context2).getWindow().getInsetsController().getSystemBarsAppearance();
                    this.mDialog.getWindow().getInsetsController().setSystemBarsAppearance(systemBarsAppearance, systemBarsAppearance);
                } else {
                    this.mDialog.getWindow().getDecorView().setSystemUiVisibility(((Activity) context2).getWindow().getDecorView().getSystemUiVisibility());
                }
            }
            this.mDialog.getWindow().clearFlags(8);
        }
    }

    private View getContentView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(this.mHostView);
        if (this.mStatusBarTranslucent) {
            frameLayout.setSystemUiVisibility(1024);
        } else {
            frameLayout.setFitsSystemWindows(true);
        }
        return frameLayout;
    }

    private void updateProperties() {
        Assertions.assertNotNull(this.mDialog, "mDialog must exist when we call updateProperties");
        Activity currentActivity = getCurrentActivity();
        Window window = this.mDialog.getWindow();
        if (currentActivity != null && !currentActivity.isFinishing() && window.isActive()) {
            if ((currentActivity.getWindow().getAttributes().flags & 1024) != 0) {
                window.addFlags(1024);
            } else {
                window.clearFlags(1024);
            }
            if (this.mTransparent) {
                window.clearFlags(2);
                return;
            }
            window.setDimAmount(0.5f);
            window.setFlags(2, 2);
        }
    }

    public StateWrapper getStateWrapper() {
        return this.mHostView.getStateWrapper();
    }

    public void setStateWrapper(StateWrapper stateWrapper) {
        this.mHostView.setStateWrapper(stateWrapper);
    }

    public void updateState(int i, int i2) {
        this.mHostView.updateState(i, i2);
    }

    static class DialogRootViewGroup extends ReactViewGroup implements RootView {
        private boolean hasAdjustedSize = false;
        private EventDispatcher mEventDispatcher;
        private JSPointerDispatcher mJSPointerDispatcher;
        private final JSTouchDispatcher mJSTouchDispatcher = new JSTouchDispatcher(this);
        private StateWrapper mStateWrapper = null;
        /* access modifiers changed from: private */
        public int viewHeight;
        /* access modifiers changed from: private */
        public int viewWidth;

        public void requestDisallowInterceptTouchEvent(boolean z) {
        }

        public DialogRootViewGroup(Context context) {
            super(context);
            if (ReactFeatureFlags.dispatchPointerEvents) {
                this.mJSPointerDispatcher = new JSPointerDispatcher(this);
            }
        }

        /* access modifiers changed from: private */
        public void setEventDispatcher(EventDispatcher eventDispatcher) {
            this.mEventDispatcher = eventDispatcher;
        }

        /* access modifiers changed from: protected */
        public void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            this.viewWidth = i;
            this.viewHeight = i2;
            updateFirstChildView();
        }

        private void updateFirstChildView() {
            if (getChildCount() > 0) {
                this.hasAdjustedSize = false;
                final int id = getChildAt(0).getId();
                if (this.mStateWrapper != null) {
                    updateState(this.viewWidth, this.viewHeight);
                    return;
                }
                ThemedReactContext reactContext = getReactContext();
                reactContext.runOnNativeModulesQueueThread(new GuardedRunnable(reactContext) {
                    public void runGuarded() {
                        UIManagerModule uIManagerModule = (UIManagerModule) DialogRootViewGroup.this.getReactContext().getReactApplicationContext().getNativeModule(UIManagerModule.class);
                        if (uIManagerModule != null) {
                            uIManagerModule.updateNodeSize(id, DialogRootViewGroup.this.viewWidth, DialogRootViewGroup.this.viewHeight);
                        }
                    }
                });
                return;
            }
            this.hasAdjustedSize = true;
        }

        public void updateState(int i, int i2) {
            float dIPFromPixel = PixelUtil.toDIPFromPixel((float) i);
            float dIPFromPixel2 = PixelUtil.toDIPFromPixel((float) i2);
            ReadableNativeMap stateData = this.mStateWrapper.getStateData();
            if (stateData != null) {
                float f = 0.0f;
                float f2 = stateData.hasKey("screenHeight") ? (float) stateData.getDouble("screenHeight") : 0.0f;
                if (stateData.hasKey("screenWidth")) {
                    f = (float) stateData.getDouble("screenWidth");
                }
                if (Math.abs(f - dIPFromPixel) < 0.9f && Math.abs(f2 - dIPFromPixel2) < 0.9f) {
                    return;
                }
            }
            if (this.mStateWrapper != null) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putDouble("screenWidth", (double) dIPFromPixel);
                writableNativeMap.putDouble("screenHeight", (double) dIPFromPixel2);
                this.mStateWrapper.updateState(writableNativeMap);
            }
        }

        public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
            super.addView(view, i, layoutParams);
            if (this.hasAdjustedSize) {
                updateFirstChildView();
            }
        }

        public void handleException(Throwable th) {
            getReactContext().getReactApplicationContext().handleException(new RuntimeException(th));
        }

        /* access modifiers changed from: private */
        public ThemedReactContext getReactContext() {
            return (ThemedReactContext) getContext();
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, this.mEventDispatcher);
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.handleMotionEvent(motionEvent, this.mEventDispatcher, true);
            }
            return super.onInterceptTouchEvent(motionEvent);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.mJSTouchDispatcher.handleTouchEvent(motionEvent, this.mEventDispatcher);
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.handleMotionEvent(motionEvent, this.mEventDispatcher, false);
            }
            super.onTouchEvent(motionEvent);
            return true;
        }

        public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.handleMotionEvent(motionEvent, this.mEventDispatcher, true);
            }
            return super.onHoverEvent(motionEvent);
        }

        public boolean onHoverEvent(MotionEvent motionEvent) {
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.handleMotionEvent(motionEvent, this.mEventDispatcher, false);
            }
            return super.onHoverEvent(motionEvent);
        }

        public void onChildStartedNativeGesture(View view, MotionEvent motionEvent) {
            this.mJSTouchDispatcher.onChildStartedNativeGesture(motionEvent, this.mEventDispatcher);
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.onChildStartedNativeGesture(view, motionEvent, this.mEventDispatcher);
            }
        }

        public void onChildEndedNativeGesture(View view, MotionEvent motionEvent) {
            this.mJSTouchDispatcher.onChildEndedNativeGesture(motionEvent, this.mEventDispatcher);
            JSPointerDispatcher jSPointerDispatcher = this.mJSPointerDispatcher;
            if (jSPointerDispatcher != null) {
                jSPointerDispatcher.onChildEndedNativeGesture();
            }
        }

        public StateWrapper getStateWrapper() {
            return this.mStateWrapper;
        }

        public void setStateWrapper(StateWrapper stateWrapper) {
            this.mStateWrapper = stateWrapper;
        }
    }
}
