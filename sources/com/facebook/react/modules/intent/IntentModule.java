package com.facebook.react.modules.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.facebook.fbreact.specs.NativeIntentAndroidSpec;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "IntentAndroid")
public class IntentModule extends NativeIntentAndroidSpec {
    private static final String EXTRA_MAP_KEY_FOR_VALUE = "value";
    /* access modifiers changed from: private */
    public LifecycleEventListener mInitialURLListener = null;

    public IntentModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public void invalidate() {
        if (this.mInitialURLListener != null) {
            getReactApplicationContext().removeLifecycleEventListener(this.mInitialURLListener);
            this.mInitialURLListener = null;
        }
        super.invalidate();
    }

    public void getInitialURL(Promise promise) {
        try {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity == null) {
                waitForActivityAndGetInitialURL(promise);
                return;
            }
            Intent intent = currentActivity.getIntent();
            String action = intent.getAction();
            Uri data = intent.getData();
            promise.resolve((data == null || (!"android.intent.action.VIEW".equals(action) && !"android.nfc.action.NDEF_DISCOVERED".equals(action))) ? null : data.toString());
        } catch (Exception e) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Could not get the initial URL : " + e.getMessage()));
        }
    }

    private void waitForActivityAndGetInitialURL(final Promise promise) {
        if (this.mInitialURLListener != null) {
            promise.reject((Throwable) new IllegalStateException("Cannot await activity from more than one call to getInitialURL"));
            return;
        }
        this.mInitialURLListener = new LifecycleEventListener() {
            public void onHostDestroy() {
            }

            public void onHostPause() {
            }

            public void onHostResume() {
                IntentModule.this.getInitialURL(promise);
                IntentModule.this.getReactApplicationContext().removeLifecycleEventListener(this);
                IntentModule.this.mInitialURLListener = null;
            }
        };
        getReactApplicationContext().addLifecycleEventListener(this.mInitialURLListener);
    }

    public void openURL(String str, Promise promise) {
        if (str == null || str.isEmpty()) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Invalid URL: " + str));
            return;
        }
        try {
            sendOSIntent(new Intent("android.intent.action.VIEW", Uri.parse(str).normalizeScheme()), false);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Could not open URL '" + str + "': " + e.getMessage()));
        }
    }

    public void canOpenURL(String str, Promise promise) {
        if (str == null || str.isEmpty()) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Invalid URL: " + str));
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.addFlags(268435456);
            promise.resolve(Boolean.valueOf(intent.resolveActivity(getReactApplicationContext().getPackageManager()) != null));
        } catch (Exception e) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Could not check if URL '" + str + "' can be opened: " + e.getMessage()));
        }
    }

    public void openSettings(Promise promise) {
        try {
            Intent intent = new Intent();
            Activity currentActivity = getCurrentActivity();
            String packageName = getReactApplicationContext().getPackageName();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("package:" + packageName));
            intent.addFlags(268435456);
            intent.addFlags(1073741824);
            intent.addFlags(8388608);
            currentActivity.startActivity(intent);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Could not open the Settings: " + e.getMessage()));
        }
    }

    public void sendIntent(String str, ReadableArray readableArray, Promise promise) {
        if (str == null || str.isEmpty()) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Invalid Action: " + str + "."));
            return;
        }
        Intent intent = new Intent(str);
        if (intent.resolveActivity(getReactApplicationContext().getPackageManager()) == null) {
            promise.reject((Throwable) new JSApplicationIllegalArgumentException("Could not launch Intent with action " + str + "."));
            return;
        }
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                ReadableMap map = readableArray.getMap(i);
                String string = map.getString("key");
                int i2 = AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[map.getType("value").ordinal()];
                if (i2 == 1) {
                    intent.putExtra(string, map.getString("value"));
                } else if (i2 == 2) {
                    intent.putExtra(string, Double.valueOf(map.getDouble("value")));
                } else if (i2 != 3) {
                    promise.reject((Throwable) new JSApplicationIllegalArgumentException("Extra type for " + string + " not supported."));
                    return;
                } else {
                    intent.putExtra(string, map.getBoolean("value"));
                }
            }
        }
        sendOSIntent(intent, true);
    }

    /* renamed from: com.facebook.react.modules.intent.IntentModule$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$ReadableType = r0
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.intent.IntentModule.AnonymousClass2.<clinit>():void");
        }
    }

    private void sendOSIntent(Intent intent, Boolean bool) {
        Activity currentActivity = getCurrentActivity();
        String packageName = getReactApplicationContext().getPackageName();
        ComponentName resolveActivity = intent.resolveActivity(getReactApplicationContext().getPackageManager());
        String packageName2 = resolveActivity != null ? resolveActivity.getPackageName() : "";
        if (bool.booleanValue() || currentActivity == null || !packageName.equals(packageName2)) {
            intent.addFlags(268435456);
        }
        if (currentActivity != null) {
            currentActivity.startActivity(intent);
        } else {
            getReactApplicationContext().startActivity(intent);
        }
    }
}
