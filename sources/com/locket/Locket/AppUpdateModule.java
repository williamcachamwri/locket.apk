package com.locket.Locket;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import io.invertase.firebase.crashlytics.ReactNativeFirebaseCrashlyticsNativeHelper;
import io.sentry.android.core.SentryLogcatAdapter;

public class AppUpdateModule extends ReactContextBaseJavaModule {
    public static final int IN_APP_UPDATE_REQUEST_CODE = 1234;
    private static final String TAG = "com.locket.Locket.AppUpdateModule";
    private final AppUpdateManager appUpdateManager;
    private final Context context;

    public String getName() {
        return "AppUpdateModule";
    }

    AppUpdateModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Context applicationContext = reactApplicationContext.getApplicationContext();
        this.context = applicationContext;
        this.appUpdateManager = AppUpdateManagerFactory.create(applicationContext);
    }

    @ReactMethod
    public void startUpdate(int i) {
        this.appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new AppUpdateModule$$ExternalSyntheticLambda0(this, i, i == 1 ? "immediate" : "flexible", getCurrentActivity())).addOnFailureListener(new AppUpdateModule$$ExternalSyntheticLambda1());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startUpdate$0(int i, String str, Activity activity, AppUpdateInfo appUpdateInfo) {
        try {
            if (appUpdateInfo.updateAvailability() != 2 || !appUpdateInfo.isUpdateTypeAllowed(i)) {
                String str2 = "Failed to start update flow: updateAvailability = " + appUpdateInfo.updateAvailability() + " urgency = " + i + " isUpdateTypeAllowed = " + appUpdateInfo.isUpdateTypeAllowed(i);
                SentryLogcatAdapter.e(TAG, str2);
                ReactNativeFirebaseCrashlyticsNativeHelper.recordNativeException(new Exception(str2));
                return;
            }
            Log.i(TAG, "Starting " + str + " update flow");
            this.appUpdateManager.startUpdateFlowForResult(appUpdateInfo, i, activity, (int) IN_APP_UPDATE_REQUEST_CODE);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
            ReactNativeFirebaseCrashlyticsNativeHelper.recordNativeException(e);
        }
    }

    static /* synthetic */ void lambda$startUpdate$1(Exception exc) {
        String str = "Failed to start update flow: " + exc;
        exc.printStackTrace();
        SentryLogcatAdapter.e(TAG, str);
        ReactNativeFirebaseCrashlyticsNativeHelper.recordNativeException(new Exception(str));
    }
}
