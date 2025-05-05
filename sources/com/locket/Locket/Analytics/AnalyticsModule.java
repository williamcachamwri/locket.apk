package com.locket.Locket.Analytics;

import android.app.Activity;
import android.util.Log;
import com.amplitude.api.Amplitude;
import com.amplitude.api.AmplitudeClient;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.invertase.firebase.app.ReactNativeFirebaseApp;

public class AnalyticsModule extends ReactContextBaseJavaModule {
    private static final String TAG = "com.locket.Locket.Analytics.AnalyticsModule";
    private AmplitudeClient mAmplitudeInstance = Amplitude.getInstance().trackSessionEvents(true).initialize(ReactNativeFirebaseApp.getApplicationContext(), "25340d2798ee21af509991697882791d");
    private FirebaseAnalytics mFirebaseAnalytics;

    public String getName() {
        return "AnalyticsModule";
    }

    public AnalyticsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        String str = TAG;
        Log.d(str, "Initializing analytics...");
        this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(reactApplicationContext);
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            this.mAmplitudeInstance.enableForegroundTracking(currentActivity.getApplication());
        }
        Log.d(str, "Initialized analytics!");
    }

    @ReactMethod
    public void setUserId(String str) {
        this.mFirebaseAnalytics.setUserId(str);
        this.mAmplitudeInstance.setUserId(str);
        Log.d(TAG, "setUserId " + str);
    }
}
