package com.locket.Locket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.react.ReactApplication;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class CameraSplashActivity extends Activity {
    public static final String ACTION_CAMERA_SPLASH = "com.locket.Locket.action.CAMERA_SPLASH";
    public static final String OPENED_FROM_WIDGET_EVENT = "opened-from-widget";
    private static final int SPLASH_DELAY_MILLIS = 300;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera_splash);
        ReactContext currentReactContext = ((ReactApplication) getApplication()).getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
        if (currentReactContext != null) {
            Log.d("CameraSplash", "Sending opened-from-widget-event");
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(OPENED_FROM_WIDGET_EVENT, (Object) null);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    CameraSplashActivity.this.finish();
                }
            }, 300);
            return;
        }
        finish();
    }
}
