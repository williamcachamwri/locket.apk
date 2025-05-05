package io.invertase.firebase.appcheck;

import android.content.pm.PackageManager;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.FirebaseAppCheck;
import io.invertase.firebase.common.ReactNativeFirebaseEvent;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import io.invertase.firebase.common.ReactNativeFirebaseJSON;
import io.invertase.firebase.common.ReactNativeFirebaseMeta;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.common.ReactNativeFirebasePreferences;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;

public class ReactNativeFirebaseAppCheckModule extends ReactNativeFirebaseModule {
    private static final String KEY_APPCHECK_TOKEN_REFRESH_ENABLED = "app_check_token_auto_refresh";
    private static final String LOGTAG = "RNFBAppCheck";
    private static final String TAG = "AppCheck";
    private static HashMap<String, FirebaseAppCheck.AppCheckListener> mAppCheckListeners = new HashMap<>();
    ReactNativeFirebaseAppCheckProviderFactory providerFactory = new ReactNativeFirebaseAppCheckProviderFactory();

    static boolean isAppCheckTokenRefreshEnabled() {
        boolean z;
        ReactNativeFirebaseJSON sharedInstance = ReactNativeFirebaseJSON.getSharedInstance();
        ReactNativeFirebaseMeta sharedInstance2 = ReactNativeFirebaseMeta.getSharedInstance();
        ReactNativeFirebasePreferences sharedInstance3 = ReactNativeFirebasePreferences.getSharedInstance();
        if (sharedInstance3.contains(KEY_APPCHECK_TOKEN_REFRESH_ENABLED)) {
            z = sharedInstance3.getBooleanValue(KEY_APPCHECK_TOKEN_REFRESH_ENABLED, true);
            Log.d(LOGTAG, "isAppCheckCollectionEnabled via RNFBPreferences: " + z);
        } else if (sharedInstance.contains(KEY_APPCHECK_TOKEN_REFRESH_ENABLED)) {
            z = sharedInstance.getBooleanValue(KEY_APPCHECK_TOKEN_REFRESH_ENABLED, true);
            Log.d(LOGTAG, "isAppCheckCollectionEnabled via RNFBJSON: " + z);
        } else {
            z = sharedInstance2.getBooleanValue(KEY_APPCHECK_TOKEN_REFRESH_ENABLED, true);
            Log.d(LOGTAG, "isAppCheckCollectionEnabled via RNFBMeta: " + z);
        }
        Log.d(LOGTAG, "isAppCheckTokenRefreshEnabled final value: " + z);
        return z;
    }

    private boolean isAppDebuggable() throws Exception {
        PackageManager packageManager = getContext().getPackageManager();
        boolean z = false;
        if (!(packageManager == null || (packageManager.getApplicationInfo(getContext().getPackageName(), 0).flags & 2) == 0)) {
            z = true;
        }
        Log.d(LOGTAG, "debuggable status? " + z);
        return z;
    }

    ReactNativeFirebaseAppCheckModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, TAG);
        FirebaseAppCheck.getInstance().setTokenAutoRefreshEnabled(isAppCheckTokenRefreshEnabled());
    }

    public void invalidate() {
        super.invalidate();
        Log.d(TAG, "instance-destroyed");
        Iterator<Map.Entry<String, FirebaseAppCheck.AppCheckListener>> it = mAppCheckListeners.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            FirebaseAppCheck.getInstance(FirebaseApp.getInstance((String) next.getKey())).removeAppCheckListener((FirebaseAppCheck.AppCheckListener) next.getValue());
            it.remove();
        }
    }

    @ReactMethod
    public void configureProvider(String str, String str2, String str3, Promise promise) {
        Log.d(LOGTAG, "configureProvider - appName/providerName/debugToken: " + str + "/" + str2 + (str3 != null ? "/(not shown)" : "/null"));
        try {
            this.providerFactory.configure(str, str2, str3);
            FirebaseAppCheck.getInstance(FirebaseApp.getInstance(str)).installAppCheckProviderFactory(this.providerFactory);
            promise.resolve((Object) null);
        } catch (Exception e) {
            rejectPromiseWithCodeAndMessage(promise, "unknown", "internal-error", e.getMessage());
        }
    }

    @ReactMethod
    public void activate(String str, String str2, boolean z, Promise promise) {
        try {
            FirebaseAppCheck.getInstance(FirebaseApp.getInstance(str)).setTokenAutoRefreshEnabled(z);
            if (isAppDebuggable()) {
                Log.d(LOGTAG, "app is debuggable, configuring AppCheck for testing mode");
                Log.d(LOGTAG, "no debug app check token found in BuildConfig. Check Log for dynamic test token to configure in console.");
                this.providerFactory.configure(str, "debug", (String) null);
            } else {
                this.providerFactory.configure(str, "safetyNet", (String) null);
            }
            FirebaseAppCheck.getInstance(FirebaseApp.getInstance(str)).installAppCheckProviderFactory(this.providerFactory);
            promise.resolve((Object) null);
        } catch (Exception e) {
            rejectPromiseWithCodeAndMessage(promise, "unknown", "internal-error", e.getMessage());
        }
    }

    @ReactMethod
    public void setTokenAutoRefreshEnabled(String str, boolean z) {
        FirebaseAppCheck.getInstance(FirebaseApp.getInstance(str)).setTokenAutoRefreshEnabled(z);
    }

    @ReactMethod
    public void getToken(String str, boolean z, Promise promise) {
        Log.d(LOGTAG, "getToken appName/forceRefresh: " + str + "/" + z);
        Tasks.call(getExecutor(), new ReactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda2(FirebaseApp.getInstance(str), z)).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda3(promise));
    }

    static /* synthetic */ AppCheckToken lambda$getToken$0(FirebaseApp firebaseApp, boolean z) throws Exception {
        return (AppCheckToken) Tasks.await(FirebaseAppCheck.getInstance(firebaseApp).getAppCheckToken(z));
    }

    static /* synthetic */ void lambda$getToken$1(Promise promise, Task task) {
        if (task.isSuccessful()) {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("token", ((AppCheckToken) task.getResult()).getToken());
            promise.resolve(createMap);
            return;
        }
        SentryLogcatAdapter.e(LOGTAG, "RNFB: Unknown error while fetching AppCheck token " + task.getException().getMessage());
        rejectPromiseWithCodeAndMessage(promise, "token-error", task.getException().getMessage());
    }

    @ReactMethod
    public void getLimitedUseToken(String str, Promise promise) {
        Log.d(LOGTAG, "getLimitedUseToken appName: " + str);
        Tasks.call(getExecutor(), new ReactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda0(FirebaseApp.getInstance(str))).addOnCompleteListener((Executor) getExecutor(), new ReactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda1(promise));
    }

    static /* synthetic */ AppCheckToken lambda$getLimitedUseToken$2(FirebaseApp firebaseApp) throws Exception {
        return (AppCheckToken) Tasks.await(FirebaseAppCheck.getInstance(firebaseApp).getLimitedUseAppCheckToken());
    }

    static /* synthetic */ void lambda$getLimitedUseToken$3(Promise promise, Task task) {
        if (task.isSuccessful()) {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("token", ((AppCheckToken) task.getResult()).getToken());
            promise.resolve(createMap);
            return;
        }
        SentryLogcatAdapter.e(LOGTAG, "Unknown error while fetching limited-use AppCheck token " + task.getException().getMessage());
        rejectPromiseWithCodeAndMessage(promise, "token-error", task.getException().getMessage());
    }

    @ReactMethod
    public void addAppCheckListener(String str) {
        Log.d(TAG, "addAppCheckListener " + str);
        FirebaseAppCheck instance = FirebaseAppCheck.getInstance(FirebaseApp.getInstance(str));
        if (mAppCheckListeners.get(str) == null) {
            ReactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda4 reactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda4 = new ReactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda4(str);
            instance.addAppCheckListener(reactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda4);
            mAppCheckListeners.put(str, reactNativeFirebaseAppCheckModule$$ExternalSyntheticLambda4);
        }
    }

    static /* synthetic */ void lambda$addAppCheckListener$4(String str, AppCheckToken appCheckToken) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("appName", str);
        createMap.putString("token", appCheckToken.getToken());
        createMap.putDouble("expireTimeMillis", (double) appCheckToken.getExpireTimeMillis());
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseEvent("appCheck_token_changed", createMap, str));
    }

    @ReactMethod
    public void removeAppCheckListener(String str) {
        Log.d(TAG, "removeAppCheckListener " + str);
        FirebaseAppCheck instance = FirebaseAppCheck.getInstance(FirebaseApp.getInstance(str));
        FirebaseAppCheck.AppCheckListener appCheckListener = mAppCheckListeners.get(str);
        if (appCheckListener != null) {
            instance.removeAppCheckListener(appCheckListener);
            mAppCheckListeners.remove(str);
        }
    }
}
