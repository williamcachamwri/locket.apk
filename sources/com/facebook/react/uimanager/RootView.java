package com.facebook.react.uimanager;

import android.view.MotionEvent;
import android.view.View;

public interface RootView {
    void handleException(Throwable th);

    void onChildEndedNativeGesture(View view, MotionEvent motionEvent);

    void onChildStartedNativeGesture(View view, MotionEvent motionEvent);

    @Deprecated
    void onChildStartedNativeGesture(MotionEvent motionEvent) {
        onChildStartedNativeGesture((View) null, motionEvent);
    }
}
