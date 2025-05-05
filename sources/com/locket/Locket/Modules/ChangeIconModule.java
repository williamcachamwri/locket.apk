package com.locket.Locket.Modules;

import android.app.Activity;
import android.content.ComponentName;
import android.util.Log;
import androidx.webkit.Profile;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import expo.modules.core.errors.InvalidArgumentException;

public class ChangeIconModule extends ReactContextBaseJavaModule {
    public static final String NAME = "ChangeIconModule";
    private static final String PACKAGE_NAME = "com.locket.Locket";
    private String currentComponentClass = "";

    public String getName() {
        return NAME;
    }

    public ChangeIconModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getIcon(Promise promise) {
        String str;
        try {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                if (!this.currentComponentClass.isEmpty()) {
                    str = this.currentComponentClass;
                } else {
                    str = currentActivity.getComponentName().getClassName();
                }
                if (str.endsWith("MainActivity")) {
                    promise.resolve(Profile.DEFAULT_PROFILE_NAME);
                    return;
                }
                String[] split = str.split("MainActivity");
                if (split.length == 2) {
                    promise.resolve(split[1]);
                    return;
                }
                throw new AssertionError("Could not extract icon name from: " + str);
            }
            throw new AssertionError("Activity not found");
        } catch (Exception e) {
            promise.reject(e.getMessage(), (Throwable) e);
        }
    }

    @ReactMethod
    public void changeIcon(String str, Promise promise) {
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    Activity currentActivity = getCurrentActivity();
                    if (currentActivity != null) {
                        if (this.currentComponentClass.isEmpty()) {
                            this.currentComponentClass = currentActivity.getComponentName().getClassName();
                        }
                        String str2 = "com.locket.Locket.MainActivity";
                        if (!str.equals(Profile.DEFAULT_PROFILE_NAME)) {
                            str2 = str2 + str;
                        }
                        if (this.currentComponentClass.equals(str2)) {
                            promise.resolve(str);
                            return;
                        }
                        String packageName = getReactApplicationContext().getPackageName();
                        Log.d(NAME, "Enabling new activity alias: " + str2);
                        currentActivity.getPackageManager().setComponentEnabledSetting(new ComponentName(packageName, str2), 1, 1);
                        Log.d(NAME, "Disabling current activity alias: " + this.currentComponentClass);
                        currentActivity.getPackageManager().setComponentEnabledSetting(new ComponentName(packageName, this.currentComponentClass), 2, 1);
                        this.currentComponentClass = str2;
                        promise.resolve(str);
                        return;
                    }
                    throw new AssertionError("Activity not found");
                }
            } catch (Exception e) {
                throw new InvalidArgumentException("Invalid icon name: " + str, e);
            } catch (Exception e2) {
                throw new InvalidArgumentException("Could not disable previous activity alias: " + this.currentComponentClass, e2);
            } catch (Exception e3) {
                promise.reject(e3.getMessage(), (Throwable) e3);
                return;
            }
        }
        throw new InvalidArgumentException("Icon cannot be null or empty");
    }
}
