package com.locket.Locket;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import io.sentry.android.core.SentryLogcatAdapter;

public class WidgetPinner extends ReactContextBaseJavaModule {
    public static String ACTION_RECEIVE_WIDGET_PINNED = "com.locket.Locket.action.RECEIVE_WIDGET_PINNED";
    Callback callback;
    ReactApplicationContext context;

    public String getName() {
        return "WidgetPinner";
    }

    public WidgetPinner(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        reactApplicationContext.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                Log.d("Locket", "Received pin result on broadcast");
                if (WidgetPinner.this.callback != null) {
                    try {
                        WidgetPinner.this.callback.invoke(new Object[0]);
                    } catch (Exception e) {
                        SentryLogcatAdapter.e("Locket", "Calling pin callback failed", e);
                    }
                }
            }
        }, new IntentFilter(ACTION_RECEIVE_WIDGET_PINNED), 2);
        this.context = reactApplicationContext;
    }

    @ReactMethod
    public void isPinWidgetSupported(Promise promise) {
        try {
            promise.resolve(Boolean.valueOf(AppWidgetManager.getInstance(this.context).isRequestPinAppWidgetSupported()));
        } catch (Exception e) {
            promise.reject("Failed to check for widget pinning support", (Throwable) e);
        }
    }

    @ReactMethod
    public void tryPinWidget(Callback callback2, Callback callback3) {
        try {
            AppWidgetManager instance = AppWidgetManager.getInstance(this.context);
            ComponentName componentName = new ComponentName(this.context, Widget.class);
            if (instance.isRequestPinAppWidgetSupported()) {
                Log.d("Locket", "Storing callback");
                this.callback = callback2;
                Intent intent = new Intent(ACTION_RECEIVE_WIDGET_PINNED);
                intent.setPackage(this.context.getPackageName());
                instance.requestPinAppWidget(componentName, (Bundle) null, PendingIntent.getBroadcast(this.context, 0, intent, 67108864));
                return;
            }
            callback3.invoke(new Exception("Widget pinning is not supported"));
        } catch (Exception e) {
            callback3.invoke(e);
        }
    }

    @ReactMethod
    public void getWidgetCount(Promise promise) {
        try {
            promise.resolve(Integer.valueOf(Util.getWidgetCount(this.context, AppWidgetManager.getInstance(this.context))));
        } catch (Exception e) {
            promise.reject("Failed to get widget count", (Throwable) e);
        }
    }
}
