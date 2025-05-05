package com.locket.Locket;

import android.content.SharedPreferences;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class SharedStorage extends ReactContextBaseJavaModule {
    ReactApplicationContext context;

    public String getName() {
        return "SharedStorage";
    }

    public SharedStorage(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.context = reactApplicationContext;
    }

    private void sendWidgetUpdateIntent() {
        Util.sendWidgetUpdateIntent(getCurrentActivity().getApplicationContext());
    }

    @ReactMethod
    public void set(String str, String str2) {
        try {
            SharedPreferences.Editor edit = this.context.getSharedPreferences("DATA", 0).edit();
            edit.putString(str, str2);
            edit.apply();
            sendWidgetUpdateIntent();
        } catch (Exception unused) {
        }
    }

    @ReactMethod
    public void get(String str, String str2, Promise promise) {
        try {
            promise.resolve(this.context.getSharedPreferences("DATA", 0).getString(str, str2));
        } catch (Exception e) {
            promise.reject("Failed to retrieve key", (Throwable) e);
        }
    }

    @ReactMethod
    public void clear(String str) {
        try {
            SharedPreferences.Editor edit = this.context.getSharedPreferences("DATA", 0).edit();
            edit.remove(str);
            edit.apply();
            sendWidgetUpdateIntent();
        } catch (Exception unused) {
        }
    }

    @ReactMethod
    public void clearAll() {
        try {
            SharedPreferences.Editor edit = this.context.getSharedPreferences("DATA", 0).edit();
            edit.clear();
            edit.apply();
            sendWidgetUpdateIntent();
        } catch (Exception unused) {
        }
    }

    @ReactMethod
    public void setBoolean(String str, boolean z) {
        try {
            SharedPreferences.Editor edit = this.context.getSharedPreferences("DATA", 0).edit();
            edit.putBoolean(str, z);
            edit.apply();
            sendWidgetUpdateIntent();
        } catch (Exception unused) {
        }
    }

    @ReactMethod
    public void getBoolean(String str, boolean z, Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(this.context.getSharedPreferences("DATA", 0).getBoolean(str, z)));
        } catch (Exception e) {
            promise.reject("Failed to retrieve key", (Throwable) e);
        }
    }
}
