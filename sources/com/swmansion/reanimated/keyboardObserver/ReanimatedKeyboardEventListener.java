package com.swmansion.reanimated.keyboardObserver;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.R;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.reanimated.nativeProxy.KeyboardEventDataUpdater;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

public class ReanimatedKeyboardEventListener {
    private boolean isStatusBarTranslucent = false;
    private final HashMap<Integer, KeyboardEventDataUpdater> listeners = new HashMap<>();
    private int nextListenerId = 0;
    private final WeakReference<ReactApplicationContext> reactContext;
    /* access modifiers changed from: private */
    public KeyboardState state;

    enum KeyboardState {
        UNKNOWN(0),
        OPENING(1),
        OPEN(2),
        CLOSING(3),
        CLOSED(4);
        
        private final int value;

        private KeyboardState(int i) {
            this.value = i;
        }

        public int asInt() {
            return this.value;
        }
    }

    public ReanimatedKeyboardEventListener(WeakReference<ReactApplicationContext> weakReference) {
        this.reactContext = weakReference;
    }

    private View getRootView() {
        return ((ReactApplicationContext) this.reactContext.get()).getCurrentActivity().getWindow().getDecorView();
    }

    /* access modifiers changed from: private */
    public void setupWindowInsets() {
        View rootView = getRootView();
        WindowCompat.setDecorFitsSystemWindows(((ReactApplicationContext) this.reactContext.get()).getCurrentActivity().getWindow(), false);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, new ReanimatedKeyboardEventListener$$ExternalSyntheticLambda1(this, rootView));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat lambda$setupWindowInsets$0(View view, View view2, WindowInsetsCompat windowInsetsCompat) {
        if (this.state == KeyboardState.OPEN) {
            updateKeyboard((int) PixelUtil.toDIPFromPixel((float) Math.max(0, windowInsetsCompat.getInsets(WindowInsetsCompat.Type.ime()).bottom - windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars()).bottom)));
        }
        int i = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars()).top;
        View findViewById = view.getRootView().findViewById(R.id.action_bar_root);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        if (this.isStatusBarTranslucent) {
            layoutParams.setMargins(0, 0, 0, 0);
        } else {
            layoutParams.setMargins(0, i, 0, 0);
        }
        findViewById.setLayoutParams(layoutParams);
        return windowInsetsCompat;
    }

    /* access modifiers changed from: private */
    public void updateKeyboard(int i) {
        for (KeyboardEventDataUpdater keyboardEventDataUpdater : this.listeners.values()) {
            keyboardEventDataUpdater.keyboardEventDataUpdater(this.state.asInt(), i);
        }
    }

    private class WindowInsetsCallback extends WindowInsetsAnimationCompat.Callback {
        private int keyboardHeight = 0;

        public WindowInsetsCallback() {
            super(1);
        }

        public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
            ReanimatedKeyboardEventListener.this.state = this.keyboardHeight == 0 ? KeyboardState.OPENING : KeyboardState.CLOSING;
            ReanimatedKeyboardEventListener.this.updateKeyboard(this.keyboardHeight);
            return super.onStart(windowInsetsAnimationCompat, boundsCompat);
        }

        public WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List<WindowInsetsAnimationCompat> list) {
            int dIPFromPixel = (int) PixelUtil.toDIPFromPixel((float) Math.max(0, windowInsetsCompat.getInsets(WindowInsetsCompat.Type.ime()).bottom - windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars()).bottom));
            this.keyboardHeight = dIPFromPixel;
            ReanimatedKeyboardEventListener.this.updateKeyboard(dIPFromPixel);
            return windowInsetsCompat;
        }

        public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
            ReanimatedKeyboardEventListener.this.state = this.keyboardHeight == 0 ? KeyboardState.CLOSED : KeyboardState.OPEN;
            ReanimatedKeyboardEventListener.this.updateKeyboard(this.keyboardHeight);
        }
    }

    private void setUpCallbacks() {
        View rootView = getRootView();
        new Handler(Looper.getMainLooper()).post(new ReanimatedKeyboardEventListener$$ExternalSyntheticLambda2(this));
        ViewCompat.setWindowInsetsAnimationCallback(rootView, new WindowInsetsCallback());
    }

    public int subscribeForKeyboardEvents(KeyboardEventDataUpdater keyboardEventDataUpdater, boolean z) {
        int i = this.nextListenerId;
        this.nextListenerId = i + 1;
        if (this.listeners.isEmpty()) {
            this.isStatusBarTranslucent = z;
            setUpCallbacks();
        }
        this.listeners.put(Integer.valueOf(i), keyboardEventDataUpdater);
        return i;
    }

    /* access modifiers changed from: private */
    public void bringBackWindowInsets() {
        WindowCompat.setDecorFitsSystemWindows(((ReactApplicationContext) this.reactContext.get()).getCurrentActivity().getWindow(), !this.isStatusBarTranslucent);
        ViewCompat.setOnApplyWindowInsetsListener(getRootView(), (OnApplyWindowInsetsListener) null);
        ViewCompat.setWindowInsetsAnimationCallback(getRootView(), (WindowInsetsAnimationCompat.Callback) null);
        View findViewById = getRootView().getRootView().findViewById(R.id.action_bar_root);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, 0, 0, 0);
        findViewById.setLayoutParams(layoutParams);
    }

    private void removeCallbacks() {
        View rootView = getRootView();
        new Handler(Looper.getMainLooper()).post(new ReanimatedKeyboardEventListener$$ExternalSyntheticLambda0(this));
        ViewCompat.setWindowInsetsAnimationCallback(rootView, (WindowInsetsAnimationCompat.Callback) null);
    }

    public void unsubscribeFromKeyboardEvents(int i) {
        this.listeners.remove(Integer.valueOf(i));
        if (this.listeners.isEmpty()) {
            removeCallbacks();
        }
    }
}
