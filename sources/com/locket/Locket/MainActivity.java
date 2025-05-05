package com.locket.Locket;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactActivityDelegate;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.locket.Locket.Analytics.Analytics;
import expo.modules.ReactActivityDelegateWrapper;
import io.invertase.firebase.crashlytics.ReactNativeFirebaseCrashlyticsNativeHelper;
import io.sentry.android.core.SentryLogcatAdapter;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;

public class MainActivity extends ReactActivity {
    private static final String TAG = "com.locket.Locket.MainActivity";
    private AppUpdateManager appUpdateManager;
    private Analytics mAnalytics;
    private AppWidgetManager mAppWidgetManager;

    /* access modifiers changed from: protected */
    public String getMainComponentName() {
        return SentryThread.JsonKeys.MAIN;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        setTheme(R.style.AppTheme);
        String str = TAG;
        Log.d(str, "Initializing analytics...");
        Analytics analytics = new Analytics(this);
        this.mAnalytics = analytics;
        analytics.enableForegroundTracking(getApplication());
        Log.d(str, "Initialized analytics!");
        this.mAppWidgetManager = AppWidgetManager.getInstance(this);
        this.appUpdateManager = AppUpdateManagerFactory.create(this);
        Intent intent = getIntent();
        if (intent != null && Widget.ACTION_LAUNCH_MAIN_ACTIVITY.equals(intent.getAction())) {
            this.mAnalytics.logOpenedFromWidget();
        }
        EdgeToEdge.enable(this);
        if (Build.VERSION.SDK_INT >= 29) {
            getWindow().setNavigationBarContrastEnforced(false);
        }
        super.onCreate((Bundle) null);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mAnalytics.updateActiveWidgetCount(Util.getWidgetCount(this, this.mAppWidgetManager));
        String widgetFrame = Util.getWidgetFrame(this);
        ArrayList arrayList = new ArrayList();
        if (widgetFrame != null && !widgetFrame.equals("none")) {
            arrayList.add(widgetFrame);
        }
        this.mAnalytics.updateCustomWidgetFrameProperties(arrayList);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new MainActivity$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$0(AppUpdateInfo appUpdateInfo) {
        if (appUpdateInfo.installStatus() == 11) {
            Log.i(TAG, "Downloaded app update is available - completing update flow");
            this.appUpdateManager.completeUpdate();
        } else if (appUpdateInfo.updateAvailability() == 3) {
            Log.i(TAG, "Developer triggered update in progress - resuming stalled immediate update");
            try {
                this.appUpdateManager.startUpdateFlowForResult(appUpdateInfo, 1, (Activity) this, (int) AppUpdateModule.IN_APP_UPDATE_REQUEST_CODE);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
                ReactNativeFirebaseCrashlyticsNativeHelper.recordNativeException(e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegateWrapper(this, false, new DefaultReactActivityDelegate(this, getMainComponentName(), DefaultNewArchitectureEntryPoint.getFabricEnabled()));
    }

    public void invokeDefaultOnBackPressed() {
        if (Build.VERSION.SDK_INT > 30) {
            super.invokeDefaultOnBackPressed();
        } else if (!moveTaskToBack(false)) {
            super.invokeDefaultOnBackPressed();
        }
    }

    public void onNewIntent(Intent intent) {
        if (intent.getStringExtra("conversationId") != null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("url", "/conversation/" + intent.getStringExtra("conversationId"));
            ReactContext currentReactContext = getReactInstanceManager().getCurrentReactContext();
            if (currentReactContext != null) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("url", createMap);
            }
        } else if (intent.getStringExtra("moment") != null) {
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putString("url", intent.getStringExtra("moment"));
            ReactContext currentReactContext2 = getReactInstanceManager().getCurrentReactContext();
            if (currentReactContext2 != null) {
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext2.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("url", createMap2);
            }
        }
        if (Widget.ACTION_LAUNCH_MAIN_ACTIVITY.equals(intent.getAction())) {
            this.mAnalytics.logOpenedFromWidget();
        }
        super.onNewIntent(intent);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1234 && i2 != -1) {
            String str = "Update flow failed, result code: " + i2;
            SentryLogcatAdapter.e(TAG, str);
            ReactNativeFirebaseCrashlyticsNativeHelper.recordNativeException(new Exception(str));
        }
    }
}
